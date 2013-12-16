<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="org.json.JSONObject"%>
<%@page import="com.tss.helper.DataHelper"%>
<%@page import="org.json.JSONArray"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Monitoring POC - Listing Group Info</title>
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
		String cityId ="0";
		String groupId ="0";
		String groupName = "";
		String color ="#00000";
		String[] cellArr = null;
		if(listGroup==null){
			out.print("No Data Available");
			return;
		}
		String rows[] = listGroup.split("@");
		String cols[] = null;
		for(int i=0;i<rows.length;i++){
			out.print("<tr>");
			cols = rows[i].split("\\|");
			for(int j=0;j<cols.length;j++){
				if(i==0){
					out.println("<td><b>"+cols[j]+"</b></td>");	
				}else{
					//green - #A1FFA1
					//orange - #FFBD26
					//red - #FF3636
					cellArr = cols[j].split(",");
					cityId = cellArr[0];
					groupId = cellArr[1];
					groupName = cellArr[2];
					color = cellArr[3];
					out.println("<td bgcolor=\""+color+"\"><a href=\"APIRequest?req=getTaskList&cityId="+cityId+"&groupId="+groupId+"\">"+groupName+"</a></td>");
				}
			}
			out.print("</tr>");
		}
		//out.print(listGroup);
	%>
			
	</table>	
	
	
</body>
</html>