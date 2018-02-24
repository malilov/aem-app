<%@include file="/libs/foundation/global.jsp"%>
<h1><%= properties.get("title", currentPage.getTitle()) %></h1>
<%
  
com.adobe.cq.KeyService keyService = sling.getService(com.adobe.cq.KeyService.class);
keyService.setKey(10) ; 
String ff = keyService.getKey();
  
%>
  
<h2>This page invokes the AEM KeyService</h2>
<h3>The value of the key is: <%=ff%></h3>