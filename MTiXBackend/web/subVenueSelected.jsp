<%-- 
    Document   : subVenueSelected
    Created on : Nov 6, 2016, 5:12:15 PM
    Author     : catherinexiong
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="header.jsp" />
<!-- Main Content -->

<div class="side-body">
    <div class="container">
        <div class="page-title">
            <span class="title" style="font-size: 40px;color: #22A7F0">${property.propertyName}</span>
        </div>
        <div class="row">
            <div class="col-sm-6">

                <img src="contentImageController?id=${property.layoutFileName}" alt="concert_main" style="width:100%;"> </div>
            <div class="col-sm-6">
                <div class="card">
                    <div class="card-header">
                        <div class="card-title">
                            <div class="title">Reserve ${property.propertyName}</div>
                            <div class="description"> </div>
                        </div>
                    </div>
                    <div class="card-body">
                        <div class="sub-title">Please fill in the form below to reserve this venue <span class="description">( Include the email of Event Organizer who requires the reservation )</span>
                            <c:url var="formAction" value="/BackPropertyController?action=saveNewSubEvent" />
                            <form class="form-horizontal" id="formSubmit" action="${formAction}" method="post">
                                <div class="form-group" style="padding-bottom: 20px;" >
                                    <label for="eventname" class="col-sm-4 control-label">Sub Event Name<span style="color:#FF0000;">*</span></label>
                                    <div class="col-sm-8">
                                        <Input type="text" id="eventname" name="eventname" value="${eventname}" required>
                                    </div> 
                                </div>


                                <div class="form-group" style="padding-bottom: 20px;">

                                    <label for="eventdes" class="col-sm-4 control-label">Event Description<span style="color:#FF0000;">*</span></label>
                                    <div class="col-sm-8">
                                        <textarea class="form-control" rows="3" required id="eventdes" name="eventdes" >${eventdes}</textarea>
                                    </div>
                                </div>
                                <div class="form-group" style="padding-bottom: 20px;" >
                                    <label for="eoemail" class="col-sm-4 control-label">Event Organizer Email<span style="color:#FF0000;">*</span></label>
                                    <div class="col-sm-8">

                                    <select class="js-example-basic-single js-states" id="eoemail" name="eoemail">
                                        <c:forEach items="${organizers}" var="organizer">
                                            <option value="${organizer.userId}">${organizer.username}</option>
                                        </c:forEach>	
                                    </select>


                                </div> 
                                </div>
                                <div class="form-group" style="padding-bottom: 20px;">


                                    <input type="hidden" name="pid" value="${property.id}">

                                </div>
                                    <div class="form-group">


                                    <input type="hidden" name="eventid" value="${eventid}">

                                </div>
                                <div class="form-group" >


                                    <input type="hidden" name="daterange" value="${daterange}">

                                </div>
                                <div class="form-group" >


                                    <input type="hidden" name="type" value="${type}">

                                </div>
                                <div class="form-group" >
                                    <div class="col-sm-10">
                                        <button type="submit" class="btn btn-default" value="formSubmit">Reserve Venue</button><span>${msg}</span>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
  


        <jsp:include page="footer.jsp" />
