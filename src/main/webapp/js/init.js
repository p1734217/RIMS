var productName;
var productNameCode;
var isAdminUser = false;
var isOwnerUser = false;
function PageLayout(options) {
	this.defaults = {
		mainMenu: {
			menu : true,
			selectedItem : ""
		},
		subMenu: {
			menu:true,
			get : "",
			selectedItem : "",
			paramList : [],
			savedCookie : ""
		},
		leftPanel : "main-left-panel",
		footer : "main-footer",
		header : "main-header",
		menu : "main-menu",
		showBottomBar:false,
		mainContent : "main-content",
		coverFullPage:false,
		contentBody : "content-body",
		minSupportedResolution:1024,
		maxSupportedResolution:1280
	};

	if(options) {
		$.extend(true, this.defaults, options);
	}

	// Global setting http://api.jquery.com/jQuery.param/
	jQuery.ajaxSettings.traditional = true;
	// For ajax timeout handling
	$(document).ajaxSend(function(xhr,options){
		try {
			options.setRequestHeader("requestType", "AJAX");
		} catch(e) {
			// ignore
		}
	});

	$(document).ajaxComplete(function(request, settings){
		try {
			var msg = settings.responseText;
			if(msg.indexOf("cccurl") == 0) {
				window.location = "index.html?sessionTimeout=true&preventCache=" + Math.floor(Math.random()*100000);
			}
		 } catch(e) {
			// ignore
 		 }
	});

	this.jHeader = "." + this.defaults.header;
	this.jTopMessage = ".top-message";
	this.jFooter = "." + this.defaults.footer;
	this.jBottomMessage = ".bottom-message";
	this.jContentBody = "." + this.defaults.contentBody;
	this.jMainContent = "." + this.defaults.mainContent;
	this.jSubMenu = ".sub-menu";
	this.jBody = "body";
	this.jMenu = "." + this.defaults.menu;

	if(!PageLayout.initialized) {
		this.init();
		PageLayout.initialized = true;
	}
}

PageLayout.initialized = false;
PageLayout.product = {};
PageLayout.prototype.init = function() {
	var thisObj = this;
	try {
		if($(thisObj.jBody)) {
			if(thisObj.defaults.mainMenu.menu) {
				$(thisObj.jBody).prepend("<div class='" + thisObj.defaults.menu + "'></div>");
			}
			$(thisObj.jBody).prepend("<div class='" + thisObj.defaults.header + "'></div>");
			if(thisObj.defaults.subMenu.menu) {
				$(thisObj.jContentBody).prepend("<div class='sub-menu'></div>");
			} else {
				$(thisObj.jMainContent).css("margin-left", "0");
				$(thisObj.jMainContent).css("margin", "0px auto");
				$(thisObj.jContentBody).css('background-color', "#FFFFFF");
				$(thisObj.jContentBody).css('width', "100%");
			}
			$(thisObj.jMainContent).append("<div class='bottom-message'>&nbsp;</div>");
			$(thisObj.jBody).append("<div class='" + thisObj.defaults.footer + "'></div>");
			if(!thisObj.defaults.coverFullPage) {
				$(thisObj.jMainContent).width(800);
			}

			if(thisObj.defaults.showBottomBar) {
				$(thisObj.jBody).append("<div id='wizard_div' />");
			}
		}

		var maxWidth = screen.width > thisObj.defaults.maxSupportedResolution ? thisObj.defaults.maxSupportedResolution : screen.width;
		var minWidth = screen.width < thisObj.defaults.minSupportedResolution ? thisObj.defaults.minSupportedResolution : screen.width;
		if(screen.width > thisObj.defaults.maxSupportedResolution) {
			$(thisObj.jHeader).width(thisObj.defaults.maxSupportedResolution);
			$(thisObj.jMenu).width(thisObj.defaults.maxSupportedResolution);
			$(thisObj.jFooter).width(thisObj.defaults.maxSupportedResolution);

		}
		var minHeight = $(window).height() - ($(thisObj.jHeader).height() + $(thisObj.jFooter).height() + 80);
		$(thisObj.jContentBody).css("min-height", minHeight + "px");

		//Progress.show();
		var url = "../portal/menu?q=getMenu&subMenu=" + thisObj.defaults.subMenu.get;
		$.ajax({
			type: "GET",
			url: url,
			cache: false,
			dataType: 'json',
			async:false,
			success: function(jsonResp) {
				PageLayout.product = jsonResp.product;
				productName = PageLayout.product.name;
				productNameCode = PageLayout.product.nameCode;

				document.title = productName + ' Inc.';

				var html = "<div class='logo'><a href='#'><img src=" + PageLayout.product.imageName + " border='0'/></a></div><div class='top-message'>&nbsp;</div><div class='header-right'></div>";
				$(thisObj.jHeader).html(html);

				var userDetail = jsonResp.userDetail;

				thisObj.createMainMenu(jsonResp.mainMenu);
				// Set width for message box
				//$(".top-message").css("min-width", (minWidth - ($(".logo").width() || 200) - ($(".header-right").width() || 300) - 40) + "px");

				if(userDetail !== undefined && userDetail !== null) {
					isAdminUser = userDetail.isAdminUser;
					isOwnerUser = userDetail.isOwner;

					html = "<ul><li class='welcome-message'>" + getI18N("header.welcome") + "&nbsp;" + userDetail.name + "&nbsp;|&nbsp;<a href='../portal/j_acegi_logout'>" + getI18N("header.signout") +" </a></li></ul>";

					$(".header-right").html(html);
				}

				// Submenu
				if(thisObj.defaults.subMenu !== undefined && thisObj.defaults.subMenu.get !== undefined) {
					thisObj.makeSubMenu(jsonResp[thisObj.defaults.subMenu.get.replace(/get/, "")]);
				}

				// Run a timer to keep session live
				setInterval(function(){
					$(thisObj.jFooter).append('<img class="keep-live-session" src="../portal/keepLiveSession?preventCache=' + Math.floor(Math.random()*100000) + '" width="0px" height="0px" alt="" />');
					$(".keep-live-session", thisObj.jFooter).remove();
				},600000); // 10 min

				// Footer
				var footer = jsonResp.footer;
				$(thisObj.jFooter).append('<a href="'+ footer.termsURL + '" target="_blank"><br>' + getI18N("footer.link.term") +
						'</a> | <a href="' + footer.privacyURL + '" target="_blank">' + getI18N("footer.link.privacystatement") +
						' </a> | <a href="'+ footer.aboutusURL + '" target="_blank">' + getI18N("footer.link.aboutus") +
						' </a> | <a href="' + footer.contactusURL + '" target="_blank">' + getI18N("footer.link.contactus") + ' </a>' +
						' <br/>  ' + footer.copyright);

				thisObj.setProductDetail();
				//Progress.hide();
			},
			error: function(msg) {
				//Progress.hide();
			}
		});
	} catch(e) {
		//Progress.hide();
		//console.log(e);
	}
	this.initMessage();
};

PageLayout.prototype.setProductDetail = function() {
	$(".productPath").html(PageLayout.product.path);
	$(".productName").html(PageLayout.product.name);
	$(".productCompanyName").html(PageLayout.product.companyName);
	$(".productCompanyAddress").html(PageLayout.product.companyName);
	$(".productSupportPhone").html(PageLayout.product.supportPhone);
	$(".productSupportEmail").html(PageLayout.product.supportEmail);
	$(".productNameUpper").html(PageLayout.product.nameUpper);
};

PageLayout.prototype.createMainMenu = function(menu) {

	var headerHTML = '<div class="NavStretch"><div class="HeaderNav"><ul class="Navigation">';
	if(this.defaults.mainMenu.menu == "false"){
		headerHTML += '</ul></div></div>';
		$(this.jMenu).html(headerHTML);
	}else{
		if(menu !== null) {
			if(menu.ADMIN_ACCOUNT !== undefined) {
				headerHTML += '<li><a id="menu_adminaccounts" href="../static/' + menu.ADMIN_ACCOUNT +'">' + getI18N("header.menu.manageaccount") + '</a></li>';
			}
			/*
			if(menu.ADMIN_PACKAGE !== undefined) {
				headerHTML += '<li><a id="menu_adminpackages" href="../static/' + menu.ADMIN_PACKAGE +'">' + getI18N("header.menu.package") + '</a></li>';
			}
			*/
			if(menu.ADMIN_NUMBER !== undefined) {
				headerHTML += '<li><a id="menu_numbers" href="../static/' + menu.ADMIN_NUMBER +'">' + getI18N("header.menu.numbers") + '</a></li>';
			}
			if(menu.ADMIN_SERVER !== undefined) {
				headerHTML += '<li><a id="menu_adminservers" href="../static/' + menu.ADMIN_SERVER +'">' + getI18N("header.menu.server") + '</a></li>';
			}
			if(menu.ADMIN_CONFIGURATION !== undefined) {
				headerHTML += '<li><a id="menu_adminconfiguration" href="../static/' + menu.ADMIN_CONFIGURATION +'">' + getI18N("header.menu.configuration") + '</a></li>';
			}
			if(menu.ADMIN_AMAZON !== undefined) {
				headerHTML += '<li><a id="menu_adminamazon" href="../static/' + menu.ADMIN_AMAZON +'">' + getI18N("header.menu.amazon") + '</a></li>';
			}
			if(menu.ADMIN_TICKET !== undefined) {
				headerHTML += '<li><a id="menu_adminticket" href="../static/' + menu.ADMIN_TICKET +'">' + getI18N("header.menu.support") + '</a></li>';
			}
			if(menu.DISABLED_ACCOUNT !== undefined) {
				headerHTML += '<li><a id="menu_unverified" href="../static/' + menu.DISABLED_ACCOUNT +'">' + getI18N("header.menu.center") + '</a></li>';
			}
			if(menu.UNVERIFIED_ACCOUNT !== undefined) {
				headerHTML += '<li><a id="menu_unverified" href="../static/' + menu.UNVERIFIED_ACCOUNT +'">' + getI18N("header.menu.center") + '</a></li>';
			}
			if(menu.ADMIN_CLIENT_RELEASE !== undefined) {
				headerHTML += '<li><a id="menu_adminclientupload" href="../static/' + menu.ADMIN_CLIENT_RELEASE +'">' + getI18N("header.menu.uploadclient") + '</a></li>';
			}
			headerHTML += '</ul></div></div>';
			$(this.jMenu).html(headerHTML);

			$("#menu_" + this.defaults.mainMenu.selectedItem).addClass("CurPage");
		}
	}
};

PageLayout.prototype.makeSubMenu = function(subMenuArr) {
	if(subMenuArr !== undefined) {
		var subMenuHtml = "<ul class='nav'>";

	    var i, j;
		for(i = subMenuArr.length-1 ; i >=0 ; i--) {
			var id = subMenuArr[i][0];
			id = id.replace(/\s+/g, '').toUpperCase() + "_SUBMENU";
			subMenuHtml += "<li id='" + id + "'> <a href='" + subMenuArr[i][2] + "'>" + getI18N(subMenuArr[i][1]) + "</a ></li>";
		}
		subMenuHtml  += "</ul>";

		// Replace parameters
		if(this.defaults.subMenu.paramList !== undefined) {
			for(j = 0; j < this.defaults.subMenu.paramList.length; j++) {
				var token = new RegExp("\\$" + this.defaults.subMenu.paramList[j], "gi");
				subMenuHtml = subMenuHtml.replace(token, $().getUrlParam(this.defaults.subMenu.paramList[j]));
			}
		}

		$(this.jSubMenu).html(subMenuHtml);

		$("#" + this.defaults.subMenu.selectedItem.toUpperCase() + "_SUBMENU").addClass("m-select");
	}
};

PageLayout.prototype.initMessage = function() {
	$(this.jBottomMessage).html("<div class='message-box'><span class='message-image'></span><span class='message-text'></span></span></div>");
	$(this.jTopMessage).html("<div class='message-box'><span class='message-image'></span><span class='message-text'></span></span></div>");
};
function _showMessage(msg, isHide, showTop) {
	$(".message-box .message-text").html(msg);
	var showTop =  typeof showTop == 'boolean' ? showTop : false;
	if(showTop) {
		$(".top-message .message-box").show();
	}
	$(".bottom-message .message-box").show();
	var isHide =  typeof isHide == 'boolean' ? isHide : true;
	if(isHide) {
		setTimeout("hideAllNow();", 20000);
	}
}
function showWarning(msg, isHide, showTop) {
	hideAllNow();
	$(".message-box").addClass("displaywarning");
	_showMessage(msg, isHide, showTop);
}

function showErrorMsg(msg, isHide, showTop) {
	hideAllNow();
	$(".message-box").addClass("displayerror");
	_showMessage(msg, isHide, showTop);
}

function showSuccessMsg(msg, isHide, showTop) {
	hideAllNow();
	$(".message-box").addClass("displayreport");
	_showMessage(msg, isHide, showTop);
}

//Show Alert Message
function showAlert(msg, isHide, showTop) {
	hideAllNow();
	$(".message-box").addClass("displayalert");
	_showMessage(msg, isHide, showTop);
}

function showProgress(msg, isHide, showTop) {
	hideAllNow();
	$(".message-box").addClass("displayprogress");
	_showMessage(msg, isHide, showTop);
}


function hideAllNow() {
	$(".message-box").removeClass("displayprogress");
	$(".message-box").removeClass("displayreport");
	$(".message-box").removeClass("displayerror");
	$(".message-box").removeClass("displaywarning");
	$(".message-box").removeClass("displayalert");
	$(".message-box .message-text").html("");
	$(".message-box").hide();
}