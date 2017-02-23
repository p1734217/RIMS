	function Wizard() {
	    /*
		 Contains all the wizard window object
		*/
		var _winArray = new Array();
		
		var _initWinArray = new Array();
		
		this.addWin = function(win) {
			_winArray.push(win);
			_initWinArray.push(false);
		};

		// Will be call on next button click
		var _next = function(nextBtn) {
			hideAllNow(); // Hide all success or error msg on clicking next
			var winIndex = $(nextBtn).attr("index")*1;
			var win = _winArray[winIndex];
			_initWin(win);
			var nextWin = win.next();
			if(nextWin != null) {
				_showWin(nextWin);
			}
		};
		
		// Will be call on back button click
		var _back = function(backBtn) {
			var winIndex = $(backBtn).attr("index")*1;
			var win = _winArray[winIndex];

			var backWin = win.back();
			
			if(backWin != null) {
				_showWin(backWin);
			}
		};
		
		// Initialize wizard 
		this.init = function() {
			for(var winIndex = 0; winIndex < _winArray.length; winIndex++) {
				var win = _winArray[winIndex];
				var jWin = $(_jId(win.id));
				$(".next", jWin).attr("index", winIndex);
				$(".next", jWin).click(function(){
					_next(this);
				});
				
				$(".back", jWin).attr("index", winIndex);	
				$(".back", jWin).click(function(){
					_back(this)
				});
			}
		};

		var _jId = function(id){
			return "#" + id;
		};

		var _hideAllWin = function() {
			for(var i = 0; i < _winArray.length; i++) {
				$(_jId(_winArray[i].id)).hide();
			}
		};

		var _initWin = function(win) {
			// Make sure init method is called for this window
			for(var i = 0; i < _winArray.length; i++) {
				if(_winArray[i].id == win.id) {
					if(!_initWinArray[i]) {
						_initWinArray[i] = true;
						win.init();
					} 
					break;
				}
			}
		}

		var _showWin = function(win, args) {
			hideAllNow(); // Hide all success or error msg on clicking back
			try {
				$("#loading").jqm({modal:true}).jqmShow()	
			} catch (e) {
				// Ignore
			}
			_initWin(win);
			_hideAllWin();
			$(_jId(win.id)).show();	
			try {
				$("#loading").jqmHide();	
			} catch (e) {
				// Ignore
			}
			if(args) {
				win.setParams(args);
			}
			win.show();
		}
		
		this.showWin = function(win, args) {
			_showWin(win, args);
		};
	}

/*
 * Wizard object expect following type of window object
 */

/*
var xWin = {
		args:{}, // Argument passed to it; will be used passed to show method
		id: "xWin",
		init:function(){
			// function1()
		},
		setParams:function(args) {
			this.args = args;
		}
		show:function(){
			// function2()
		},
		next:function() {
			// function3()	
			return nextWin or null;
		},
		back:function() {
			// function3()	
			return backWin or null;
		},
		reset:function() {
			// statements
		}
	};
*/