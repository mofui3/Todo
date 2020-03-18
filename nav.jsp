<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">TODO管理</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ol class="nav navbar-nav">
				<li><a href="input">タスクの新規登録</a></li>
				<li><a href="search">一覧の再表示</a></li>
			</ol>
			<ol class="nav navbar-nav navbar-right">
				<li><a href="#">
					<c:out value="${pageContext.request.remoteUser}" />
				</a>
				</li>
			</ol>
		</div>
		<!-- /.navbar-collapse -->
	</div>
</nav>

</body>
</html>