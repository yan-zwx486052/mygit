<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>第一个 JSP 程序</title>
<script type="text/javascript">
//debugger;
document.write("<p>${pageContext.request.contextPath}</p>");

	function checkUser() {
		window.location.href = '${pageContext.request.contextPath}/view/stu_list.html';
		//window.event.returnValue = false;
	}
</script>
</head>
<body>
	<%
		out.println("Hello World！");
	%>
	<a onclick="checkUser()" href="/gnifweb/view/stu_list.html">跳转</a>
</body>
</html>