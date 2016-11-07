<%-- 
    Document   : customerProfile
    Created on : Oct 28, 2016, 3:12:03 AM
    Author     : catherinexiong
--%>

<%@page import="java.util.Random"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="header.jsp" />
<!-- FormValidation CSS file -->
<link rel="stylesheet" href="/vendor/formvalidation/dist/css/formValidation.min.css">
<link rel="stylesheet" type="text/css" href="lib/css/intlTelInput.css">
<script type="text/javascript" src="lib/js/jquery.autotab.min.js"></script>
<script type="text/javascript" src="lib/js/intlTelInput.js"></script>

<div class="container">
    <% String companyName = request.getAttribute("companyname").toString();%>
    <div class="main " >
        <div class="panel panel-default col-lg-12" style="box-shadow: 0px 3px 10px 1px rgba(119, 119, 119, 0.75);
             -moz-box-shadow: 0px 3px 10px 1px rgba(119, 119, 119, 0.75);
             -webkit-box-shadow: 0px 3px 10px 1px rgba(119, 119, 119, 0.75);margin-bottom:50px;">
            <div class="panel-heading" style="height:40px;text-align:center;font-size:20px;margin-left: -15px; margin-right: -15px;color:#777777; background: repeating-linear-gradient(
                 -45deg,
                 transparent,
                 transparent,
                 #EEE 2px,
                 transparent 3px
                 );"><h2> <span> Your Profile </span></h2></div> 
            <!-- start registration -->
            <div class="registration">
                <div class="registration">



                    <script>
                        (function () {

                            // Create input element for testing
                            var inputs = document.createElement('input');

                            // Create the supports object
                            var supports = {};

                            supports.autofocus = 'autofocus' in inputs;
                            supports.required = 'required' in inputs;
                            supports.placeholder = 'placeholder' in inputs;

                            // Fallback for autofocus attribute
                            if (!supports.autofocus) {

                            }

                            // Fallback for required attribute
                            if (!supports.required) {

                            }

                            // Fallback for placeholder attribute
                            if (!supports.placeholder) {

                            }

                            // Change text inside send button on submit
                            var send = document.getElementById('register-submit');
                            if (send) {
                                send.onclick = function () {
                                    this.innerHTML = '...Sending';
                                }
                            }

                        })();
                    </script>
                    <div class="registration_form">
                        <!-- Form -->

                        <c:if test="${edit == 'true'}">
                            <div style="color:red">Your information has been successfully updated!</div>
                        </c:if>
                        <c:if test="${edit == 'false'}">
                            <div style="color:red">Errors happen when updating your profile</div>
                        </c:if>


                        <form id="registration_form" action="Controller?action=editProfile&company=<%=companyName%>" method="post">
                            <label for="username">Username:</label>
                            <div>

                                <input placeholder="Username:" type="email" id="username" name="email" value="${email}" disabled readonly>

                            </div>
                            <label for="first">First Name:</label>
                            <div>

                                <input placeholder="first name:" type="text" id="first" name="first" value="${first}" required autofocus>

                            </div>
                            <label for="last">Last Name:</label>
                            <div>

                                <input placeholder="last name:" type="text" id="last" name="last" value="${last}" required autofocus>

                            </div>
                            <label for="age">Age:</label>
                            <div>

                                <input placeholder="age:" type="text" id="age" name="age" value="${age}" required autofocus>

                            </div>



                            <label for="day">Date of Birth (autotab):</label>
                            <div id="date1" class="datefield" >


                                <input id="day" name="day" value="${day}" type="tel" maxlength="2" placeholder="DD" style="width:10%;display:inline;" required/> /              
                                <input id="month" name="month" value="${month}" type="tel" maxlength="2" placeholder="MM" style="width:10%;display:inline;" required/>/
                                <input id="year" name="year" value="${year}" type="tel" maxlength="4" placeholder="YYYY" style="width:15%;display:inline;" required/>

                            </div>
                            <label for="phone">Mobile Number :</label>
                            <div>
                                <input type="tel" name="phone" value="${phone}" id="phone" required> 


                            </div>

                            <div>
                                <input type="submit" value="Update Profile" id="register-submit">
                            </div>

                        </form>
                        <!-- /Form -->
                    </div>
                </div>



                <!-- /Form -->
            </div>
        </div>
    </div>

    <div class="clearfix"></div>
</div>

<script>
    $(document).ready(function () {
        $('#date1 input').autotab_magic().autotab_filter('numeric');
        $("#phone").intlTelInput({
            // allowDropdown: false,
            // autoHideDialCode: false,
            // autoPlaceholder: "off",
            // dropdownContainer: "body",
            // excludeCountries: ["us"],
            geoIpLookup: function (callback) {
                $.get("http://ipinfo.io", function () {
                }, "jsonp").always(function (resp) {
                    var countryCode = (resp && resp.country) ? resp.country : "";
                    callback(countryCode);
                });
            },
            initialCountry: "auto",
            // nationalMode: false,
            // numberType: "MOBILE",
            // onlyCountries: ['us', 'gb', 'ch', 'ca', 'do'],
            //preferredCountries: ['cn', 'jp'],
            // separateDialCode: true,
            utilsScript: "lib/js/utils.js"});




    });
</script>
<jsp:include page="footer.jsp" />