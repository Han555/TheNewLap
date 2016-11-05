<%-- 
    Document   : createSections
    Created on : Nov 3, 2016, 1:21:09 PM
    Author     : catherinexiong
--%>

<%@page import="java.util.ArrayList"%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!doctype html>
<jsp:include page="header.jsp" />
<!-- Main Content -->


<div class="container-fluid">


    <div class="side-body ">

        <div class="row">
            <div class="col-xs-12" style="padding-top:30px">
                <div class="card" >
                    <div class="card-header">
                        <div class="card-title">
                            <div class="title">Please fill up the * fields below to create sections and categories under ${propertyName}</div>
                            <!--<div class="description">Please fill up the fields below to complete creation of Manpower</div>-->
                        </div>
                    </div>
                    <div class="card-body">
                        <c:url var="formAction"  value="/BackPropertyController?action=propertyCreated" />
                        <form class="form-horizontal" id="formSubmit" action="${formAction}" enctype="multipart/form-data" method="post" >
                            <div class="form-group" style="padding-bottom: 20px;" >
                                <label for="filePhoto" class="col-sm-2 control-label">Property Seat Plan with Numbers <span style="color:#FF0000">*</span></label>
                                <div class="col-sm-4">
                                    <img id="previewHolder" src="img/contentManagement/placeholder.png" alt="Place your image here" width="250" height="250"/>
                                    <input type="file" name="filePhoto" id="filePhoto" required />
                                </div> 
                            </div>

                            <%Integer categories = (Integer) request.getAttribute("categories");
                                Integer sections = (Integer) request.getAttribute("sections"); 
                            for (int m = 0; m < categories; m++) {%>
                             <div class="form-group" style="padding-bottom: 20px;" >
                                <label for="section<%=m+1%>" class="col-sm-2 control-label">Section Coordinates:<span style="color:#FF0000">*</span></label>
                                <div class="col-lg-8">
                                    <textarea  type="text" required name="section<%=m+1%>" id="section<%=m+1%>" style="width: 50%" row="3"/></textarea>
                                </div> 
                            </div>
                            
                            
                            <%}%>
                                <% for (int i = 0; i < categories; i++) {%>
                            <div class="form-group" style="padding-bottom: 20px;" >
                                <label for="cname" class="col-sm-2 control-label">Category Name:<span style="color:#FF0000">*</span></label>
                                <div class="col-lg-8">
                                    <input type="text" name="cname<%=i+1%>" required id="cname<%=i+1%>" style="width: 50%" />
                                </div> 
                            </div>
                            <div class="form-group" style="padding-bottom: 20px;" >
                                <label for="csections<%=i+1%>" class="col-sm-2 control-label">Category Sections:<span style="color:#FF0000">*</span></label>
                                <div class="col-lg-8">
                                    <select class="js-example-basic-multiple" multiple="multiple" style="width: 100%" name="csections<%=i+1%>" id="csections<%=i+1%>" >
                                        <%for (int j = 0; j < sections; j++) {%>
                                        <option value="<%=j+1%>">Section <%=j+1%></option>
                                        <%}%>

                                    </select>
                                </div> 
                            </div>
                            <%}%>
                           
                            
                           
                            <input type="hidden" id="ext" name="ext" />
                            <input type="hidden" name="propertyId" value="${propertyId}" />
                            <input type="hidden" name="categories" value="${categories}" />
                            <input type="hidden" name="sections" value="${sections}" />

                            <div class="form-group" style="padding-bottom: 20px;">
                                <div class="col-sm-offset-2 col-sm-10">
                                    <button type="submit" class="btn btn-default" value="formSubmit">Submit</button><span style="color:#FF0000;">${errormsg}</span>
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
    function readURL(input) {
        var ext = $('#filePhoto').val().split('.').pop().toLowerCase();
        if ($.inArray(ext, ['gif', 'png', 'jpg']) == -1) {
            alert('Invalid File Type! Please upload an image file');
            $("#filePhoto").val('');

        } else {
            if (input.files && input.files[0]) {
                $("#ext").val(ext);
                var reader = new FileReader();
                reader.onload = function (e) {
                    $('#previewHolder').attr('src', e.target.result);
                }

                reader.readAsDataURL(input.files[0]);
            }
        }
    }



    $("#filePhoto").change(function () {
        readURL(this);
    });


</script>


<jsp:include page="footer.jsp" />



