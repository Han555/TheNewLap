<%-- 
    Document   : searchEngine
    Created on : 4 Nov, 2016, 4:51:13 PM
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
        List<ArrayList> byTypes = (List<ArrayList>) request.getAttribute("byTypes");
        List<ArrayList> byEvents = (List<ArrayList>) request.getAttribute("byEvents");
        String companyName = request.getAttribute("companyname").toString();
    %>
    <div class="row">
    <div class="panel panel-default col-lg-4" style="box-shadow: 0px 3px 10px 1px rgba(119, 119, 119, 0.75);
         -moz-box-shadow: 0px 3px 10px 1px rgba(119, 119, 119, 0.75);
         -webkit-box-shadow: 0px 3px 10px 1px rgba(119, 119, 119, 0.75);margin-bottom:10px;">
        <div class="panel-heading" style="height:40px;text-align:center;font-size:20px;margin-left: -15px; margin-right: -15px;background: repeating-linear-gradient(
             -45deg,
             transparent,
             transparent,
             #EEE 2px,
             transparent 3px
             );">Find Events Based on Types</div> 
        <div class="check">
            <div class="row" style="margin-bottom:20px">
                <div class="col-md-6" style="margin-top:15px"><p><b>Type</b></p></div>
                <div class="col-md-6" style="margin-top:15px"><b><p>Link</p></b></div>
            </div>
            <%if (byTypes.size() == 0){%>
            <div class="row">
                    <div class="col-md-4" style="margin-top:15px"></div>
                    <div class="col-md-4" style="margin-top:15px"><p>No Type Found</p></div>
                    <div class="col-md-4" style="margin-top:15px"></div>
            </div>
            
            <%} else {
                for (int i = 0; i < byTypes.size(); i++){%>
            <div class="row">
                <div class="col-md-6" style="margin-top:15px"><%=byTypes.get(i).get(0)%></div>
                <div class="col-md-6" style="margin-top:15px"><p><a href="<%=byTypes.get(i).get(1)%>">Visit page</a></p></div>
            </div>
            <%}}%>
        </div>
    </div>
        <div class="col-lg-2"></div>
    <div class="panel panel-default col-lg-6" style="box-shadow: 0px 3px 10px 1px rgba(119, 119, 119, 0.75);
         -moz-box-shadow: 0px 3px 10px 1px rgba(119, 119, 119, 0.75);
         -webkit-box-shadow: 0px 3px 10px 1px rgba(119, 119, 119, 0.75);margin-bottom:10px;">
        <div class="panel-heading" style="height:40px;text-align:center;font-size:20px;margin-left: -15px; margin-right: -15px;background: repeating-linear-gradient(
             -45deg,
             transparent,
             transparent,
             #EEE 2px,
             transparent 3px
             );">Events</div> 
        <div class="check"><div class="row" style="margin-bottom:20px">
                <div class="col-md-4" style="margin-top:15px"><p></p></div>
                <div class="col-md-3" style="margin-top:15px"><p><b>Event Name</b></p></div>
                <div class="col-md-3" style="margin-top:15px"><b><p>Start Date</p></b></div>
                <div class="col-md-2" style="margin-top:15px"><b><p>Event Type</p></b></div>
            </div>
            <%if (byEvents.size() == 0){%>
            <div class="row">
                    <div class="col-md-4" style="margin-top:15px"></div>
                    <div class="col-md-4" style="margin-top:15px"><p>No Event Found</div>
                    <div class="col-md-4" style="margin-top:15px"></div>
            </div>
            
            <%} else {
            for (int i = 0; i < byEvents.size(); i++){ %>
            <div class="row">
                <div class="col-md-4" style="margin-top:15px">
                    <a href="/MTiX-war/ContentController/<%=companyName%>/viewEventWebpage/<%=byEvents.get(i).get(0)%>/<%=byEvents.get(i).get(1).toString().replaceAll("\\s","")%>"><img src="/MTiX-war/ContentImageController?id=<%=byEvents.get(i).get(2) %>" width="150px" height="150px"></a></div>
                    <div class="col-md-3" style="margin-top:15px"><p><%=byEvents.get(i).get(1)%></p></div>
                    <div class="col-md-3" style="margin-top:15px"><p><%=byEvents.get(i).get(3)%></p></div>
                    <div class="col-md-2" style="margin-top:15px"><p><%=byEvents.get(i).get(4)%></p></div>
            </div>
            <%}}%>
            

        </div>
    </div>
    </div>
    
</div>

<jsp:include page="footer.jsp" />
