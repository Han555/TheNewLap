<%-- 
    Document   : companyStory
    Created on : 30 Oct, 2016, 3:37:34 PM
    Author     : JingYing
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!doctype html>
<jsp:include page="header.jsp" />
<!--<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.css" />
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>-->

<!-- Main Content -->
<div class="container">
    <%
        ArrayList data = (ArrayList) request.getAttribute("data");
    %>
    <div class="row">
        <div class="col-md-1"></div>
        <div class="col-md-7">
            <div class="row" style="margin-bottom:20px">
                <div class="col-md-4" style="margin-top:15px  "><img border="1" src="/MTiX-war/images/Content/aboutus.png" width="210px" height="210px"></div></div><br>
            <div class ="row" style="background-color:#F1F1F1;">
                <p><b>Mission : <%=data.get(3)%></b></p><br>
                <p><b>Vision : <%=data.get(5)%></b></p><br>
                <p><b>About us</b></p>
                <p><%=data.get(0)%></p><br>
                <p><b>Other Details</b></p>
                <p><%=data.get(4)%></p><br>
            </div>
        </div>
        <div class="col-md-4">
             <div class="ui-widget-content">
                 <p><b>Career Opportunities : </b></p>
                 <p><%=data.get(1)%></p><br>
                 <p><b>Contact Details : </b></p>
                 <p><%=data.get(2)%></p><br>  
             </div></div>
    </div>
    <jsp:include page="footer.jsp" />
