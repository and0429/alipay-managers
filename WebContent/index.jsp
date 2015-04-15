<%@ page language="java" pageEncoding="utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户登录</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href='assets/stylesheets/bootstrap/bootstrap.css' media='all' rel='stylesheet' type='text/css' />
<link href='assets/stylesheets/light-theme.css' id='color-settings-body-color' media='all' rel='stylesheet' type='text/css' />
<style type="text/css">
.login {
	height: 100%;
	width: 100%;
	background-image: url(assets/images/login/background.jpg);
}

input[type="image"] {
	margin-left: 657px;
	margin-top: 11px;
}

.loginMessage {
	margin-left: 660px;
	margin-top: -125px;
	color: red;
}

input#password {
	background-color: transparent;
	border: 0px;
	width: 154px;
	height: 13px;
	margin-top: -1px;
	margin-left: 636px;
}

input#username {
	background-color: transparent;
	border: 0px;
	width: 154px;
	height: 13px;
	margin-top: 328px;
	margin-left: 636px;
}
</style>
</head>
</head>
<body>
	<div class='login'>
		<div class='form'>
			<sf:form modelAttribute="loginer" method="post" action="login.do">
				<sf:input autofocus="autofocus" required="required" path="username" placeholder="用户名" />
				<sf:password required="required" path="password" placeholder="密码" />
				<input type="image" src="assets/images/login/submint.png" border="0">
				<div class='loginMessage'>${loginer.loginMessage }</div>
			</sf:form>
		</div>
	</div>
</body>
</html>