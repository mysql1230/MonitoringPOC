
<%
	String url = (String)request.getAttribute("url");
%>

SauceLab Parsed console:<br/>
<iframe id="ifr" src="<%=url %>" width="99%" height="2000px" frameborder=1></iframe>


