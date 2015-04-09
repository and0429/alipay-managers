(function() {
	$(document).ready(function() {
		var body, content, nav, nav_closed_width, nav_open, nav_toggler;

		nav_toggler = $("header .toggle-nav");
		nav = $("#main-nav");
		content = $("#content");
		body = $("body");
		nav_closed_width = 50;
		nav_open = body.hasClass("main-nav-opened") || nav.width() > nav_closed_width;
		$("#main-nav .dropdown-collapse").on("click", function(e) {
			var link, list;
			e.preventDefault();
			link = $(this);
			list = link.parent().find("> ul");
			if (list.is(":visible")) {
				if (body.hasClass("main-nav-closed") && link.parents("li").length === 1) {
					false;
				} else {
					link.removeClass("in");
					list.slideUp(300, function() {
						return $(this).removeClass("in");
					});
				}
			} else {
				link.addClass("in");
				list.slideDown(300, function() {
					return $(this).addClass("in");
				});
			}
			return false;
		});
		nav.swiperight(function(event, touch) {
			return $(document).trigger("nav-open");
		});
		nav.swipeleft(function(event, touch) {
			return $(document).trigger("nav-close");
		});
		nav_toggler.on("click", function() {
			if (nav_open) {
				$(document).trigger("nav-close");
			} else {
				$(document).trigger("nav-open");
			}
			return false;
		});
		$(document).bind("nav-close", function(event, params) {
			body.removeClass("main-nav-opened").addClass("main-nav-closed");
			return nav_open = false;
		});

		// 页面控制
		pageControl = new Object();
		// 点击按钮获取页面名称
		$(".pageLi").on('click', function() {
			var pageName = $(this).attr("pageName");
			pageControl.load(pageName);

		})
		// 加载页面
		pageControl.load = function(pageName) {
			$.ajax({
				type : "GET",
				url : pageControl.pageMap["" + pageName + ""],
				async : false,
				dataType : "html",
				beforeSend : function() {
					$.ajax({
						type : "GET",
						url : "../getloginer.do",
						dataType : "json",
						async : false,
						success : function(data) {
							if (data !== null) {
								return true;
							} else {
								window.location = '../login.do';
							}
						}
					});
				},
				success : function(data) {
					$("#content").empty();
					$("#content").append(data);
				}
			});
		};
		// 页面索引
		pageControl.pageMap = {
			custManage : '../html/cust/cust.html',
			distributorManage : '../html/distributor/distributor.html',
			loginer : '../html/loginer/loginer.html',
			goodsCategory : '../html/goodsCategory/goodsCategory.html',
			payOnline : '../html/payOnline/payOnline.html',
			sellDetail : '../html/selldetail/sellDetail.html',
			paymonth4loginer : '../html/PayMonth4Loginer/paymonth4loginer.html',
			paymonth4distributor : '../html/paymonth4distributor/paymonth4distributor.html',
			sellDetail4shangtong : '../html/sellDetail4shangtong/sellDetail4shangtong.html'
		};

		// 默认首先加载信息看板页面
		pageControl.load("distributorManage");

		return $(document).bind("nav-open", function(event, params) {
			body.addClass("main-nav-opened").removeClass("main-nav-closed");
			return nav_open = true;
		});
	});

}).call(this);