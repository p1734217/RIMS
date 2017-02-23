/**
 * This jQuery plugin displays pagination links inside the selected elements.
 *
 * @author Gabriel Birke (birke *at* d-scribe *dot* de)
 * @version 1.2
 * @param {int} maxentries Number of entries to paginate
 * @param {Object} opts Several options (see README for documentation)
 * @return {Object} jQuery Object
 */
jQuery.fn.pagination = function(maxentries, opts){
	opts = jQuery.extend({
		items_per_page:($.cookie(window.location.pathname + "chunksize")?$.cookie(window.location.pathname + "chunksize")*1:10),
		num_display_entries:0,
		current_page:0,
		num_edge_entries:0,
		link_to:"#",
		first_text:""+getI18N("first")+"", // Added by Amit
		prev_text:""+getI18N("previous")+"",
		next_text:""+getI18N("next")+"",
		last_text:""+getI18N("last")+"", // Added by Amit
		show_page_text:""+getI18N("label.showing")+"{0} "+getI18N("label.to")+" {1} "+getI18N("label.of")+" {2} "+getI18N("label.records"),
		ellipse_text:"...",
		first_show_always:true, // Added by Amit
		prev_show_always:true,
		next_show_always:true,
		show_item_per_page:false,
		show_page:true,
		last_show_always:true,	// Added by Amit
		linked:[],
		callback:function(){return false;}
	},opts||{});
	var called = false;
	this.getChunkSize = function() {
		return opts.items_per_page;
	}
	return this.each(function() {
		/**
		 * Calculate the maximum number of pages
		 */
		function numPages() {
			if (maxentries > 0) {
				return Math.ceil(maxentries/opts.items_per_page);
			}
			return 1;
		}
		
		/**
		 * Calculate start and end point of pagination links depending on 
		 * current_page and num_display_entries.
		 * @return {Array}
		 */
		function getInterval()  {
			var ne_half = Math.ceil(opts.num_display_entries/2);
			var np = numPages();
			var upper_limit = np-opts.num_display_entries;
			var start = current_page>ne_half?Math.max(Math.min(current_page-ne_half, upper_limit), 0):0;
			var end = current_page>ne_half?Math.min(current_page+ne_half, np):Math.min(opts.num_display_entries, np);
			return [start,end];
		}
		
		/**
		 * This is the event handling function for the pagination links. 
		 * @param {int} page_id The new page number
		 */
		function pageSelected(page_id, evt){
			current_page = page_id;
			drawLinks();
			var continuePropagation = opts.callback(page_id, panel);
			if (!continuePropagation) {
				if (evt.stopPropagation) {
					evt.stopPropagation();
				}
				else {
					evt.cancelBubble = true;
				}
			}
			return continuePropagation;
		}
		
		function setLinkedPanels(){
			if (opts.linked && opts.linked.length >0){
				for (var i=0; i<opts.linked.length; i++){
					if (panel.attr("id") != opts.linked[i]) {
						panels.push(jQuery($("#"+opts.linked[i])));
					}
				}
			}
		}
		
		/**
		 * This function inserts the pagination links into the container element
		 */
		function drawLinks() {
			panel.empty();
			if (panels.length > 0) {
				for (var i=0; i<panels.length; i++){
					panels[i].empty();
				}
			}
			var interval = getInterval();
			var i =0;
			var np = numPages();
			// This helper function returns a handler function that calls pageSelected with the right page_id
			var getClickHandler = function(page_id) {
				return function(evt){ return pageSelected(page_id,evt); };
			};
			// Helper function for generating a single link (or a span tag if it's the current page)
			var appendItem = function(page_id, appendopts){
				page_id = page_id<0?0:(page_id<np?page_id:np-1); // Normalize page id to sane value
				appendopts = jQuery.extend({text:page_id+1, classes:""}, appendopts||{});
				var lnk; 
				if(page_id === current_page){
					lnk = jQuery("<span class='current'>"+(appendopts.text)+"</span>");
				} else {
					lnk = jQuery("<a>"+(appendopts.text)+"</a>")
						.bind("click", getClickHandler(page_id))
						.attr('href', opts.link_to.replace(/__id__/,page_id));
				}
				if(appendopts.classes){lnk.addClass(appendopts.classes);}
				panel.append(lnk);
				if (panels.length > 0) {
					for (var i=0; i<panels.length; i++){
						var lnk1;
						if(page_id === current_page){
							lnk1 = jQuery("<span class='current'>"+(appendopts.text)+"</span>");
						} else {
							lnk1 = jQuery("<a>"+(appendopts.text)+"</a>")
								.bind("click", getClickHandler(page_id))
								.attr('href', opts.link_to.replace(/__id__/,page_id));
						}
						if(appendopts.classes){lnk1.addClass(appendopts.classes);}
						panels[i].append(lnk1);
					}
				}
			};
			// Generate "Last"-Link
			if(opts.last_text && (np > 0 || opts.last_show_always)){
				appendItem(np-1,{text:opts.last_text, classes:"last"});
			}
			// Generate "Next"-Link
			if(opts.next_text && (current_page < np-1 || opts.next_show_always)){
				appendItem(current_page+1,{text:opts.next_text, classes:"next"});
			}
			// Generate starting points
			if (interval[0] > 0 && opts.num_edge_entries > 0)
			{
				var end = Math.min(opts.num_edge_entries, interval[0]);
				for(i=0; i<end; i++) {
					appendItem(i);
				}
				if(opts.num_edge_entries < interval[0] && opts.ellipse_text)
				{
					jQuery("<span>"+opts.ellipse_text+"</span>").appendTo(panel);
					if (panels.length > 0) {
						for (var i=0; i<panels.length; i++){
							jQuery("<span>"+opts.ellipse_text+"</span>").appendTo(panels[i]);
						}
					}
				}
			}
			// Generate interval links
			for(i=interval[0]; i<interval[1]; i++) {
				appendItem(i);
			}
			// Generate ending points
			if (interval[1] < np && opts.num_edge_entries > 0)
			{
				if(np-opts.num_edge_entries > interval[1]&& opts.ellipse_text)
				{
					jQuery("<span>"+opts.ellipse_text+"</span>").appendTo(panel);
					if (panels.length > 0) {
						for (var i=0; i<panels.length; i++){
							jQuery("<span>"+opts.ellipse_text+"</span>").appendTo(panels[i]);
						}
					}
				}
				var begin = Math.max(np-opts.num_edge_entries, interval[1]);
				for(i=begin; i<np; i++) {
					appendItem(i);
				}
				
			}
			
			// Generate "Previous"-Link
			if(opts.prev_text && (current_page > 0 || opts.prev_show_always)){
				appendItem(current_page-1,{text:opts.prev_text, classes:"prev"});
			}
			// Generate "First"-Link
			if(opts.first_text && (np > 0 || opts.first_show_always)){
				appendItem(0,{text:opts.first_text, classes:"first"});
			}		

			if(opts.show_page) {
				var txt = opts.show_page_text;
				if (org_total > 0){
					var startFrom = opts.items_per_page*current_page + 1;
					var recordOnPage = (maxentries == 0 || maxentries >= opts.items_per_page*(current_page + 1))?opts.items_per_page: (maxentries - opts.items_per_page*current_page);
					txt = txt.replace("{0}", startFrom).replace("{1}", (recordOnPage + startFrom -1)).replace("{2}", maxentries);
				} else {
					txt = txt.replace("{0}", 0).replace("{1}", 0).replace("{2}", 0);
				}
				panel.append("<span>" + txt + "</span>");
				if (panels.length > 0) {
					for (var i=0; i<panels.length; i++){
						panels[i].append("<span>" + txt + "</span>");
					}
				}
			}
			if(opts.show_item_per_page) {
				var options = "<select style='float:right'><option value='10'>10</option><option value='20'>20</option><option value='50'>50</option><option value='100'>100</option></select>";
				panel.append(options);
				panel.find("select").each(function(){
					$(this).val(opts.items_per_page); 
				});
				panel.find("select").change(function(event){
					var old_val = opts.items_per_page;
					opts.items_per_page = $(this).val()*1;
					var expires = new Date();
					expires.setDate(expires.getDate() + 365);

					$.cookie(window.location.pathname + "chunksize", opts.items_per_page, {expires:expires});
						 //10 < 100
					if(old_val < opts.items_per_page) {
						current_page = Math.floor(current_page/(opts.items_per_page/old_val));
					}
					pageSelected(current_page, event);
				}).val(opts.items_per_page);
			}
		}
		
		// Extract current_page from options
		var current_page = opts.current_page;
		// Create a sane value for maxentries and items_per_page
		var org_total = (!maxentries || maxentries < 0)? 0: maxentries;
		maxentries = (!maxentries || maxentries < 0)?0:maxentries;
		opts.items_per_page = (!opts.items_per_page || opts.items_per_page < 0)?1:opts.items_per_page;
		// Store DOM element for easy access from all inner functions
		var panel = jQuery(this);
		var panels = [];
		setLinkedPanels();
		// Attach control functions to the DOM element 
		this.selectPage = function(page_id){ pageSelected(page_id);};
		this.prevPage = function(){ 
			if (current_page > 0) {
				pageSelected(current_page - 1);
				return true;
			}
			else {
				return false;
			}
		};
		this.nextPage = function(){ 
			if(current_page < numPages()-1) {
				pageSelected(current_page+1);
				return true;
			}
			else {
				return false;
			}
		};
		// When all initialisation is done, draw the links
		drawLinks();
	});
};