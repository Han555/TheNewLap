<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE HTML>
<html>
    <head>
        <title>MTiX Ticketing</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.css" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <!-- Custom Theme files -->

        <!-- Custom Theme files -->
        <!--//theme-style-->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="keywords" content="Gretong Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
              Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
        <link href='http://fonts.googleapis.com/css?family=Roboto:400,100,300,500,700,900' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Playfair+Display:400,700,900' rel='stylesheet' type='text/css'>
        <!-- start menu -->
        <link href="/MTiX-war/css/style.css" rel='stylesheet' type='text/css' />
        <link href="/MTiX-war/css/megamenu.css" rel="stylesheet" type="text/css" media="all" />
        <script type="text/javascript" src="/MTiX-war/js/megamenu.js"></script>
        <script>$(document).ready(function () {
        $(".megamenu").megamenu();
    });</script>
        <script src="/MTiX-war/js/menu_jquery.js"></script>
        <script src="/MTiX-war/js/simpleCart.min.js"></script>
        <!--<script type="text/javascript" src="js/jquery-scrollto.js"></script>-->
        <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
         <% String fileName = request.getAttribute("CompanyLogo").toString();
            List<ArrayList> propertyData = (List<ArrayList>) request.getAttribute("propertyData");
        %>
    </head>
    <body>
                <style>.search-form .form-group {
                float: right !important;
                transition: all 0.35s, border-radius 0s;
                width: 32px;
                height: 32px;
                background-color: #fff;
                box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset;
                border-radius: 25px;
                border: 1px solid #ccc;
            }
            .search-form .form-group input.form-control {
                padding-right: 20px;
                border: 0 none;
                background: transparent;
                box-shadow: none;
                display:block;
            }
            .search-form .form-group input.form-control::-webkit-input-placeholder {
                display: none;
            }
            .search-form .form-group input.form-control:-moz-placeholder {
                /* Firefox 18- */
                display: none;
            }
            .search-form .form-group input.form-control::-moz-placeholder {
                /* Firefox 19+ */
                display: none;
            }
            .search-form .form-group input.form-control:-ms-input-placeholder {
                display: none;
            }
            .search-form .form-group:hover,
            .search-form .form-group.hover {
                width: 100%;
                border-radius: 4px 25px 25px 4px;
            }
            .search-form .form-group span.form-control-feedback {
                position: absolute;
                top: -1px;
                right: -2px;
                z-index: 2;
                display: block;
                width: 34px;
                height: 34px;
                line-height: 34px;
                text-align: center;
                color: #3596e0;
                left: initial;
                font-size: 14px;
            }</style>
        <!-- header_top -->
        <div class="header_bg">
            <div class="container">

                <div class="header">
                    <div class="head-t">
                        <div class="logo">
                            <a href="/MTiX-war/ContentController/MTiX/Home"><img src="/MTiX-war/ContentImageController?id=<%=fileName%>" style="width:60px;height:60px;" class="img-responsive" alt=""/> </a>
                         </div>
                        <!--		<div class="logo">
                                                <a href="index.html"><img src="images/sistic.png" style="width:80px;height:80px;" class="img-responsive" alt=""/> </a>
                                        </div>-->
                        <!-- start header_right -->
                        <% String username = (String) session.getAttribute("username");
                            if (username == null) { %>
                        <div class="header_right" style="margin-right:-10px">
                            <div class="rgt-bottom" style="margin-top:40px;">

                                <div class="reg" style="float:right">
                                    <a href="/MTiX-war/Controller?action=loginCustomer"><span style=" color:#696763;">&nbsp&nbspREGISTER</span></a>
                                </div>
                                <div class="reg" style="float:right" >
                                    <div class="login"  >
                                        <div id="loginContainer"><a href="/MTiX-war/Controller?action=loginCustomer" ><span style=" color:#696763;text-align:center;">Login</span></a>

                                        </div>
                                    </div>
                                </div>

                                <div class="clearfix"> </div>
                                <div><form action="/MTiX-war/ContentSearchEventController" class="search-form">
                                    <div class="form-group has-feedback">
                                        <label for="search" class="sr-only">Search</label>
                                        <input type="text" class="form-control" name="search" id="search" placeholder="search">
                                        <span class="glyphicon glyphicon-search form-control-feedback"></span>
                                    </div>
                                </form></div>
                            </div>
                        </div>
                        <!--		<div class="search">
                                            <form>
                                                <input type="text" value="" placeholder="search...">
                                                        <input type="submit" value="">
                                                </form>
                                        </div>-->
                        <div class="clearfix"> </div>


                        <%} else {%>

                        <div class="header_right" >
                            <div class="rgt-bottom" >
                                <div class="log">
                                    <div class="login" >
                                        <span style=" color:#696763;">Welcome,</span>

                                    </div>
                                </div>

                                <div class="reg1">
                                    <div class="dropdown" style=" display: inline-block;margin-top:-10px">
                                        <button class="btn btn-sucess dropdown-toggle" type="button" data-toggle="dropdown"><%=username%>
                                            <span class="caret"></span></button>
                                        <ul class="dropdown-menu">
                                            <c:url var="profile" value="Controller?action=customerProfile" />
                                            <li><a href="${profile}">Profile</a></li>
                                                
                                                <c:url var="checkout" value="Controller?action=shopCart" />
                                            <li><a href="${checkout}">Check Out</a></li>
                                                <c:url var="logout" value="LogOutController" />
                                            <li><a href="${logout}">Log Out</a></li>

                                        </ul>
                                    </div>

                                </div>



                            </div>
                            <div style="float:right">
                                <div class="cart box_1" >
                                    <a href="/MTiX-war/Controller?action=shopCart">
                                        <!--                                <h3> <span class="simpleCart_total" style=" color:#696763;">$0.00</span> (<span id="simpleCart_quantity" class="simpleCart_quantity" style=" color:#696763;">0</span> <span style=" color:#696763;">items</span>)
                                        --><img src="images/bag.png" alt="">
                                    </a>	
                                    <!--                            <p ><a href="javascript:;" class="simpleCart_empty"><span style=" color:#696763;">(empty card)</span></a></p>-->
                                    <div class="clearfix"> </div><div class="clearfix"> </div>
                                </div>
                                <div class="create_btn" style="margin-left:10px">
                                    <a href="/MTiX-war/Controller?action=shopCart">CHECKOUT</a>
                                </div>
                            </div>
                            <div class="clearfix"> </div>
                        </div>
                    </div>
                    <!--		<div class="search">
                                        <form>
                                            <input type="text" value="" placeholder="search...">
                                                    <input type="submit" value="">
                                            </form>
                                    </div>-->
                    <div class="clearfix"> </div>


                    <%}%>


                    <!-- start header menu -->
                    <ul class="megamenu skyblue" style="background-color:white;">
                        <li class="grid"><a class="color1" href="/MTiX-war/ContentController/MTiX/Home"><span style=" color:#696763;">Home</span></a></li>
                        <li class="grid"><a class="color2" href="#"><span style=" color:#696763;">Find an event</span></a>
                            <div class="megapanel">
                                <div class="row">
                                    <div class="col1">
                                        <div class="h_nav">
                                            <h4 style=" color:#696763;"><b>Categories</b></h4>
                                            <ul>
                                                <li><a href="/MTiX-war/ContentController/MTIX/displayConcertEvents">Concert</a></li>
                                                <li><a href="/MTiX-war/ContentController/MTIX/displayDanceEvents">Dance</a></li>
                                                <li><a href="/MTiX-war/ContentController/MTIX/displaySportsEvents">Sports</a></li>
                                            </ul>	
                                        </div>							
                                    </div>
                                    <div class="col1">
                                        <div class="h_nav">
                                            <h4 style=" color:#696763;">Venues</h4>
                                            <ul>
                                                <%for (int i = 0; i < propertyData.size(); i++){ %>
                                                <li><a href="/MTiX-war/ContentController/MTIX/displayVenueEvents/<%=propertyData.get(i).get(0)%>/<%=propertyData.get(i).get(1).toString().replaceAll("\\s","")%>"><%=propertyData.get(i).get(1)%></a></li>
                                                <%}%>
                                            </ul>
                                        </div>												
                                    </div>
                                    <div class="col1">
                                        <div class="h_nav">
                                            <h4 style=" color:#696763;">Beyond Singapore</h4>
                                            <ul>
                                                <li><a href="#">login</a></li>
                                            </ul>	
                                        </div>						
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col2"></div>
                                    <div class="col1"></div>
                                    <div class="col1"></div>
                                    <div class="col1"></div>
                                    <div class="col1"></div>
                                </div>
                            </div>
                        </li>
                        <li><a class="color4" href="#"><span style=" color:#696763;">Museums</span></a>

                        </li>
                        <li><a class="color6" href="#"><span style=" color:#696763;">Group Deals</span></a>

                        </li>				

                        <li><a class="color8" href="#"><span style=" color:#696763;">Travel</span></a>

                        </li>
                        <!--                        <li><a class="color9" href="#"><span style=" color:#696763;">WATCHES</span></a>
                                                    
                                                </li>-->
                    </ul> 

                </div>
                <div class="clearfix"> </div>
            </div>
        </div>
    </div>
</div>
