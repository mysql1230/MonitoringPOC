<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="org.json.JSONObject"%>
<%@page import="com.tss.helper.DataHelper"%>
<%@page import="org.json.JSONArray"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Monitoring POC - Listing Tasks Info</title>
<style type="text/css">
	<!--
	@import url("style.css");
	-->
</style>
</head>
<body>
	<table  id="box-table-b"  border="1" cellpadding="30" cellspacing="0" style="text-align:center">
	
	<%
		String listGroup = DataHelper.getListGroups();
		String cityId ="0";
		String groupId ="0";
		String taskId ="0";
		String groupName = "";
		String color ="#FFFFFF";
		String[] cellArr = null;
		if(listGroup==null){
			out.print("No Data Available");
			return;
		}
		String rows[] = listGroup.split("@");
		String cols[] = null;
		String taskName = "";
		String taskStatus = "";
		String taskDesc = "";
		for(int i=0;i<rows.length;i++){
			out.print("<tr>");
			cols = rows[i].split("\\|");
			
				//green - #A1FFA1
				//orange - #FFBD26
				//red - #FF3636
				//cellArr = cols[j].split(",");
				taskName = cols[0];
				taskStatus = cols[1];
				taskDesc = cols[2];
				if(i==0){
					out.print("<td bgcolor=\""+color+"\"><b>"+taskName+"</b></td>");
					out.print("<td><b>"+taskStatus+"</b></td>");
					out.print("<td><b>"+taskDesc+"</b></td>");
					out.print("<td><b>"+cols[3]+"</b></td>");
				}else{
					out.print("<td bgcolor=\""+(taskStatus.equalsIgnoreCase("ok")?"#A1FFA1":(taskStatus.equalsIgnoreCase("running")?"#FFBD26":(taskStatus.equalsIgnoreCase("failed")?"#FF3636":"")))+"\">"+taskName+"</td>");
					out.print("<td>"+taskStatus+"</td>");
					out.print("<td>"+taskDesc+"</td>");
					out.print("<td>"+cols[3].replace(",","<br>")+"</td>");
				}
				//groupId = cellArr[1];
				//groupName = cellArr[2];
				//color = cellArr[3];
				//out.println("<td bgcolor=\""+color+"\"><a href=\"APIRequest?req=getExeList&cityId="+cityId+"&groupId="+groupId+"&taskid="+taskId+"\">"+groupName+"</a></td>");
			
		
			out.print("</tr>");
		}
		//out.print(listGroup);
	%>
	</table>	
</body>
</html>