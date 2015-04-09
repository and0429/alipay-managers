<%@ page language="java" pageEncoding="utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户登录</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href='assets/stylesheets/bootstrap/bootstrap.css' media='all' rel='stylesheet' type='text/css' />
<link href='assets/stylesheets/light-theme.css' id='color-settings-body-color' media='all' rel='stylesheet' type='text/css' />
</head>
</head>
<body class='contrast-red sign-in contrast-background'>
	<div id='wrapper'>
		<div class='application'>
			<div class='application-content'>
				<a><div class='icon-heart'></div> <span>支付宝分销商管理系统</span></a>
			</div>
		</div>
		<div class='controls'>
			<div class='caret'></div>
			<div class='form-wrapper'>
				<h1 class='text-center'>登陆</h1>
				<sf:form modelAttribute="loginer" method="post">
					<div class='row-fluid'>
						<div class='span12 icon-over-input'>
							<sf:input autofocus="autofocus" required="required" path="username" class="span12" placeholder="用户名" />
							<i class='icon-user muted'><sf:errors path="username" style="color: red;" /></i>
						</div>
					</div>
					<div class='row-fluid'>
						<div class='span12 icon-over-input'>
							<sf:password required="required" path="password" class="span12" placeholder="密码" />
							<i class='icon-lock muted'><sf:errors path="password" style="color: red;" /></i>
						</div>
					</div>
					<div class='row-fluid'>
						<div class='span12 text-center' style='color: red;'>${loginer.loginMessage }</div>
					</div>
					<button class="btn btn-block" name="button" type="submit">登录</button>
				</sf:form>
			</div>
		</div>
	</div>
</body>
</html>