<%-- 
    Document   : addSubEventUnderMain
    Created on : Nov 7, 2016, 12:10:56 PM
    Author     : catherinexiong
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:useBean id="events" class="java.util.List<entity.Event>" scope="request"/>
<jsp:include page="header.jsp" />



<!-- Main Content -->
<div class="side-body">
    <div class="page-title">
        <span class="title"></span>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <div class="card">
                <div class="card-header">
                    <div class="card-title">
                        <div class="title">Events Suppose to Have Sub Events</div>
                        <div class="description"></div>
                    </div>
                </div>
                <div class="card-body">
                    <div style="padding-bottom: 20px;">
                        <form class="form-horizontal">
                        <table class=" table table-hover" cellspacing="0" width="100%">
                            <thead>
                                <tr>
                                    <th></th>
                                    <th>Event Name</th>
                                    <th>Event Description</th>
                                    <th>User Email</th>



                                </tr>
                            </thead>

                            <tbody>

                              
                                <c:forEach items="${events}" var="event">
                                    <tr>
                                        <td><div class="form-group" style="margin-top: 0;">
                                                <input type="radio" name='selectedevent' value="${event.id}">
                                            </div> </td>

                                        <td>${event.name}</td>
                                        <td>${event.description}</td>      
                                        <td>${event.user.username}</td>




                                    </tr>
                                </c:forEach>
                            </tbody>


                        </table>
                            <div class="form-group" style="padding-bottom: 60px;">
                            
                            <div class="col-sm-offset-2 col-sm-10">
                             
                                <button type="button" id="createSub" class="btn btn-default" value="seSubmit">Create Sub Event</button></a>
                            </div>
                        </div>
                            </form>
                    </div>

                </div>
            </div>
        </div>
    </div>


</div>


<script>

    $("#createSub").click(function () {
        var selected = $('input[name="selectedevent"]:checked').val();
        if (selected == null)
            alert("null");
        console.log("Selected: " + selected);
        window.location = "BackPropertyController?action=subReservationSearch&eventid=" + selected;
    });

   
</script>

<jsp:include page="footer.jsp" />



