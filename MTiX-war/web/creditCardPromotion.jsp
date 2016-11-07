<%-- 
    Document   : creditCardPromotion
    Created on : 19 Oct, 2016, 3:22:20 PM
    Author     : JingYing
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!doctype html>
<jsp:include page="header.jsp" />

<!-- Main Content -->
<div class="container">
    <%
        List<ArrayList> data = (List<ArrayList>) request.getAttribute("data");
        String companyname = request.getAttribute("companyname").toString();
    %>
    <div class="row">
        <div class="col-md-1">
        </div>
        <div class="col-lg-11">
            <h4>Credit Card Promotion</h4>
        </div><br><br>
    </div>
    <%if (data.size() == 0) {%>
    <h4>No event register under this type of promotion</h4>
    <%} else {
        for (int i = 0; i < data.size(); i++) {
    %>
    <div class="row">
        <div class="col-md-1"></div>
        <div class="col-md-3">
            <img src="/MTiX-war/ContentImageController?id=<%=data.get(i).get(6)%>" width="210px" height="210px">
        </div>
        <div class="col-md-7">
            <p><h5><b>Event Name : </b><%=data.get(i).get(3)%></h5></p>
            <p><h5><b>Promotion Name : </b><%=data.get(i).get(0)%></h5></p>
            <p><h5><b>Promotion Requirement : </b><%=data.get(i).get(5)%></h5></p>
            <p><h5><b>Promotion Discount Rate : </b><%=data.get(i).get(2)%></h5></p>
            <p><h5><b>Promotion Description : </b><%=data.get(i).get(1)%></h5></p>
            <a href="/MTiX-war/ContentController/<%=companyname%>/viewEventWebpage/<%=data.get(i).get(4)%>/<%=data.get(i).get(3).toString().replaceAll("\\s","")%>"><button type="button" class="btn btn-info" style="width: 250px">View Event Webpage</button></a>
        </div></div><br>
        <%}
        }%>
</div>
<jsp:include page="footer.jsp" />

