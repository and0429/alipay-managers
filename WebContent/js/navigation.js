var navigation = Object();

/**
 * js主方法
 */
navigation.main = function() {
	navigation.setUser();
};

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
				$('.user-name').html(data.username);
			}
		}
	});
};

/**
 * 调用
 */
navigation.main();
