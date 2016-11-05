<%-- 
    Document   : createNewProperty
    Created on : Nov 1, 2016, 1:19:29 AM
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
                            <div class="title">Please fill up the * fields below to create a new property</div>
                            <!--<div class="description">Please fill up the fields below to complete creation of Manpower</div>-->
                        </div>
                    </div>
                    <div class="card-body">
                        <c:url var="formAction"  value="/BackPropertyController?action=propertyCreated" />
                        <form class="form-horizontal" id="formSubmit" action="${formAction}" enctype="multipart/form-data" method="post" >
                            <div class="form-group" style="padding-bottom: 20px;" >
                                <label for="filePhoto1" class="col-sm-2 control-label">Property Appearance Photo <span style="color:#FF0000">*</span></label>
                                <div class="col-sm-4">
                                    <img id="previewHolder1" src="img/contentManagement/placeholder.png" alt="Place your image here" width="250" height="250"/>
                                    <input  type="file" name="filePhoto1" id="filePhoto1"  required onclick="readURL1(this)" />
                                </div> 
                            </div>
                            <script>
                                function readURL1(input) {
                                    var ext1 = $('#filePhoto1').val().split('.').pop().toLowerCase();
                                    console.log("===ext1: "+ext1)
                                    if(ext1==""){
                                        
                                    } else {
                                    if ($.inArray(ext1, ['gif', 'png', 'jpg']) == -1) {
                                        alert('Invalid File Type! Please upload an image file');
                                        $("#filePhoto1").val('');

                                    } else {
                                        if (input.files && input.files[0]) {
                                            $("#ext1").val(ext1);
                                            console.log($("#ext1").val());
                                            var reader = new FileReader();
                                            reader.onload = function (e) {
                                                $('#previewHolder1').attr('src', e.target.result);
                                                
                                            }

                                            reader.readAsDataURL(input.files[0]);
                                        }
                                    }
                                }
                            }

                                $("#filePhoto1").change(function () {
                                    readURL1(this);
                                });
                            </script>

                            <div class="form-group" style="padding-bottom: 20px;" >
                                <label for="filePhoto2" class="col-sm-2 control-label">Property Image with Numbers<span style="color:#FF0000">*</span></label>
                                <div class="col-sm-4">
                                    <img id="previewHolder2" src="img/contentManagement/placeholder.png" alt="Place your image here" width="250" height="250"/>
                                    <input  type="file" name="filePhoto2" id="filePhoto2" required onclick="readURL2(this)" />
                                </div> 
                            </div>
                            <script>
                                function readURL2(input) {
                                    var ext2 = $('#filePhoto2').val().split('.').pop().toLowerCase();
                                    console.log("===ext2: "+ext2)
                                    if(ext2==""){
                                        
                                    } else {
                                    if ($.inArray(ext2, ['gif', 'png', 'jpg']) == -1) {
                                        alert('Invalid File Type! Please upload an image file');
                                        $("#filePhoto2").val('');

                                    } else {
                                        if (input.files && input.files[0]) {
                                            $("#ext2").val(ext2);
                                            console.log($("#ext2").val());
                                            var reader = new FileReader();
                                            reader.onload = function (e) {
                                                $('#previewHolder2').attr('src', e.target.result);
                                            }

                                            reader.readAsDataURL(input.files[0]);
                                        }
                                    }
                                }
                            }

                                $("#filePhoto2").change(function () {
                                    readURL2(this);
                                });
                            </script>

                            <div class="form-group" style="padding-bottom: 20px;" >
                                <label for="pname" class="col-sm-2 control-label">Property Name:<span style="color:#FF0000">*</span></label>
                                <div class="col-lg-8">
                                    <input type="text" name="title" required id="pname" style="width: 50%" />
                                </div> 
                            </div>
                            <div class="form-group" style="padding-bottom: 20px;" >
                                <label for="cap" class="col-sm-2 control-label">Property Capacity:<span style="color:#FF0000">*</span></label>
                                <div class="col-lg-8">
                                    <input  type="number" required name="capacity" id="cap" style="width: 50%" />
                                </div> 
                            </div>
                            <div class="form-group" style="padding-bottom: 20px;" >
                                <label for="types" class="col-sm-2 control-label">Event Types it is Capable of handling : <span style="color:#FF0000">*</span></label>
                                <div class="col-sm-4">
                                    <select class="js-example-basic-multiple" multiple="multiple" style="width: 100%" name="types" id="types" >

                                        <option value="dance">Dance</option>
                                        <option value="concert">Concert</option>
                                        <option value="sports">Sports</option>
                                    </select>
                                </div> 
                            </div>
                            <div class="form-group" style="padding-bottom: 20px;" >
                                <label for="recommend" class="col-sm-2 control-label">Recommend Event Type it Can Hold  : <span style="color:#FF0000">*</span></label>
                                <div class="col-sm-4">
                                    <select class="js-example-basic-single js-states" style="width: 100%" name="recommend" id="recommend" >

                                        <option value="dance">Dance</option>
                                        <option value="concert">Concert</option>
                                        <option value="sports">Sports</option>
                                    </select>
                                </div> 
                            </div>
                            
                            <div class="form-group" style="padding-bottom: 20px;" >
                                <label for="fileData" class="col-sm-2 control-label">Property Categories,Sections and Seats Data File<span style="color:#FF0000">*</span></label>
                                <div class="col-sm-4">
                                    
                                    <input  type="file" name="fileData" id="fileData" required onclick="readURL3(this)"  />
                                </div> 
                            </div>
                            
                            <div class="form-group" style="padding-bottom: 20px;" >
                                <label for="rental" class="col-sm-2 control-label">Property Rental (Per Day):<span style="color:#FF0000">*</span></label>
                                <div class="col-lg-8">
                                    <input  type="number" required name="rental" id="rental" style="width: 50%" />
                                </div> 
                            </div>
                            <script>
                                function readURL3(input) {
                                    var ext3 = $('#fileData').val().split('.').pop().toLowerCase();
                                    console.log("===ext3: "+ext3)
                                    if(ext3==""){
                                        
                                    } else {
                                    if ($.inArray(ext3, ['txt', 'pdf', 'docx', 'doc']) == -1) {
                                        alert('Invalid File Type! Please upload an document file with txt,pdf,docx or doc format');
                                        $("#fileData").val('');

                                    } else {
                                        if (input.files && input.files[0]) {
                                            $("#ext3").val(ext3);
                                            console.log($("#ext3").val());
//                                            var reader = new FileReader();
//                                            reader.onload = function (e) {
//                                                $('#previewHolder2').attr('src', e.target.result);
//                                            }
//
//                                            reader.readAsDataURL(input.files[0]);
                                        }
                                    }
                                }
                            }

                                $("#fileData").change(function () {
                                    readURL3(this);
                                });
                            </script>
<!--                            <div class="form-group" style="padding-bottom: 20px;" >
                                <label for="cate" class="col-sm-2 control-label">Number of Section Categories:<span style="color:#FF0000">*</span></label>
                                <div class="col-lg-8">
                                    <input  type="number" required name="categories" id="cate" style="width: 50%" />
                                </div> 
                            </div>
                            <div class="form-group" style="padding-bottom: 20px;" >
                                <label for="numS" class="col-sm-2 control-label">Number of Sections:<span style="color:#FF0000">*</span></label>
                                <div class="col-lg-8">
                                    <input  type="number" required name="numOfSections" id="numS" style="width: 50%" />
                                </div> 
                            </div>-->
                            <input type="hidden" id="ext1" name="ext1" />
                            <input type="hidden" id="ext2" name="ext2" />
                            <input type="hidden" id="ext3" name="ext3" />

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



<jsp:include page="footer.jsp" />


