<%-- 
    Document   : seatingMain
    Created on : Nov 2, 2016, 7:42:23 PM
    Author     : catherinexiong
--%>


<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!doctype html>
<jsp:include page="header.jsp" />
<!-- Main Content -->
<!-- Main Content -->
<div class="container-fluid">
    <div class="side-body padding-top">
        <div class="row" >
            <div class="col-sm-6">
                <!--    <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12"> -->
                <c:url var="createP" value="/BackPropertyController?action=createProperty" />
                <a href="${createP}">
                    <div class="card green summary-inline">
                        <div class="card-body">
                            <i class="icon fa fa-share-alt fa-4x"></i>
                            <div class="content">
                                <div class="title" style="font-size:25px;">Create New Property</div>
<!--                                <div class ="description" style="color:#000000;">Click here if the event doesn't have any sub events</div>-->
                            </div>
                            <div class="clear-both"></div>
                        </div>
                    </div>
                </a>
                <!--    </div> -->
            </div>
            <div class="col-sm-6">
                <!--   <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12"> -->
                <c:url var="seatingP" value="/BackPropertyController?action=viewProperty" />
                <a href="${seatingP}">
                    <div class="card green summary-inline">
                        <div class="card-body">
                            <i class="icon fa fa-share-alt fa-4x"></i>
                            <div class="content">
                                <div class="title" style="font-size:25px;">View Properties</div>
<!--                                <div class ="description" style="color:#000000;">Click here if the event has any sub events</div>-->
                            </div>
                            <div class="clear-both"></div>
                        </div>
                    </div>
                </a>
                <!--    </div> -->
            </div>
        </div>


     
    </div>
</div>



<jsp:include page="footer.jsp" />
