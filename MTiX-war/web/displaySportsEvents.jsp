<%-- 
    Document   : displaySportsEvents
    Created on : 4 Nov, 2016, 11:27:51 AM
    Author     : JingYing
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<jsp:include page="header.jsp" />

<div class="container">
    <%
        List<ArrayList> data = (List<ArrayList>) request.getAttribute("data");
    %>
    <div class="col-lg-2"></div>
    <div class="panel panel-default col-lg-8" style="box-shadow: 0px 3px 10px 1px rgba(119, 119, 119, 0.75);
         -moz-box-shadow: 0px 3px 10px 1px rgba(119, 119, 119, 0.75);
         -webkit-box-shadow: 0px 3px 10px 1px rgba(119, 119, 119, 0.75);margin-bottom:10px;">
        <div class="panel-heading" style="height:40px;text-align:center;font-size:20px;margin-left: -15px; margin-right: -15px;background: repeating-linear-gradient(
             -45deg,
             transparent,
             transparent,
             #EEE 2px,
             transparent 3px
             );">Events (SPORTS)</div> 
        <div class="check">
            <div class="row" style="margin-bottom:20px">
                <div class="col-md-4" style="margin-top:15px"><p></p></div>
                <div class="col-md-2" style="margin-top:15px"><p><b>Event Name</b></p></div>
                <div class="col-md-2" style="margin-top:15px"><b><p>Start Date</p></b></div>
                <div class="col-md-2" style="margin-top:15px"><b><p>Venue</p></b></div>
            </div>
            <%if (data.size() == 0){%>
            <div class="row">
                    <div class="col-md-4" style="margin-top:15px"></div>
                    <div class="col-md-4" style="margin-top:15px"><p>No Event Under This Type</div>
                    <div class="col-md-4" style="margin-top:15px"></div>
            </div>
            
            <%} else {
            for (int i = 0; i < data.size(); i++){ %>
            <div class="row">
                <div class="col-md-4" style="margin-top:15px">
                    <a href="/MTiX-war/ContentController/MTiX/viewEventWebpage/<%=data.get(i).get(0)%>/<%=data.get(i).get(1).toString().replaceAll("\\s","")%>"><img src="/MTiX-war/ContentImageController?id=<%=data.get(i).get(2) %>" width="150px" height="150px"></a></div>
                    <div class="col-md-2" style="margin-top:15px"><p><%=data.get(i).get(1)%></p></div>
                    <div class="col-md-2" style="margin-top:15px"><p><%=data.get(i).get(3)%></p></div>
                    <div class="col-md-2" style="margin-top:15px"><p><%=data.get(i).get(4)%></p></div>
            </div>
            <%}}%>

        </div>
    </div>
</div>

<jsp:include page="footer.jsp" />


