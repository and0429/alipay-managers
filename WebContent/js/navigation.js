var navigation = Object();

/**
 * js主方法
 */
navigation.main = function() {
	navigation.setUser();
};

$.ajaxSetup({
	beforeSend : function() {

	}
});

/**
 * 获取在线用户的用户名
 */
navigation.setUser = function() {

	$.ajax({
		type : "GET",
		url : "../getloginer.do",
		dataType : "json",
		success : function(data) {
			if (data !== null) {
				if (data.role !== 3) {
					$('.cust').remove();
				} else {
					$('.distributor').remove();
				}
				$('.user-name').html(data.username);
				$('.nav').show();
			}
		}
	});
};

/**
 * 调用
 */
navigation.main();
