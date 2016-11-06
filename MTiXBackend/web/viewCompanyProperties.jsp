<%-- 
    Document   : viewCompanyProperties
    Created on : Nov 5, 2016, 1:21:37 AM
    Author     : catherinexiong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:useBean id="properties" class="java.util.List<entity.PropertyEntity>" scope="request"/>

<jsp:include page="header.jsp" />
<!-- Main Content -->
<div class="side-body">


    <div class="container"> 
        <div class="page-title">
            <span class="title">Display All Properties in ${companyName}</span>
        </div>

        <div class="row">
            <c:forEach items="${properties}" var="property">
                <div class="col-sm-6 col-md-4">
                    <div class="thumbnail">
                        <img src="contentImageController?id=${property.mainFileName}"  style="width:300 px;height:300px">
                        <div class="caption">
                            <h3 >${property.propertyName}</h3>
                            <!--                        <p style="text-align: justify">The Concert Hall is Merlion's crown jewel, which can seat <span style="font-weight:bold;">2,000</span> spectators. It is one of only five such halls in the world with similar state-of-the-art features, which include reverberation chambers and an acoustic canopy that adapts the hall for different musical performances, producing optimum sound at every concert. </p>-->
                            <br>

                            <p><c:url var="linkHref" value="/BackPropertyController?action=venueLayout&id=${property.id}" /><a href="${linkHref}" class="btn btn-primary"  role="button">View Seating Plan</a> </p>
                        </div>
                    </div>
                </div>
            </c:forEach>

        </div>
    </div> 

    <jsp:include page="footer.jsp" />
