<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="com.tss.helper.DataHelper"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
	<!--
	@import url("style.css");
	-->
</style>
</head>
<body>
	<table  id="box-table-b" border="1" cellpadding="30" cellspacing="0" style="text-align:center">
		<%
			String listGroup = DataHelper.getListGroups();
			if(listGroup==null){
				out.print("No Data Available");
				return;
			}
			String rows[] = listGroup.split("@");
			for(int i=0;i<rows.length;i++){
				out.print("<tr>"+rows[i]+"</tr>");
			}
		%>
	</table>
</body>
</html>