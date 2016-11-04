<%-- 
    Document   : copySetTickets
    Created on : Sep 22, 2016, 6:35:43 PM
    Author     : Student-ID
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList, java.util.List" %>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!doctype html>
<jsp:include page="header2.jsp" />
<!-- Main Content -->
<!-- Multidates -->
<script src="http://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.js"></script>


<div class="container-fluid">
    <%
        List<ArrayList> data = (List<ArrayList>) request.getAttribute("data");
        List<ArrayList> sectionData = (List<ArrayList>) request.getAttribute("sectionData");
        String date = (String) request.getAttribute("date");
        List<ArrayList> coordinates = (List<ArrayList>) request.getAttribute("coordinates");
    %>

    <c:url var="formAction" value="/BackController?action=addTickets" />
    <div class="side-body padding-top">

        <div class="row">
            <form id="contact_form" action="${formAction}" method="POST">
                <%
                    if (data == null) {
                %>
                <div align="center"><h3>No SESSION Found!</h3></div><br><br>
                <%} else {
                    ArrayList storeSection = new ArrayList();
                    String x = data.get(0).get(5).toString();
                %>

                <div class="col-sm-6">
                    <div class="header">
                        <h4 class="title">Reserve/Edit Sections</h4>   
                    </div> 
                    <%  if (x.equals("1")) {%>
                    <img id="shape1" src="img/property/ConcerthallNo.png" style="width:100%; height: 100%;" usemap="#concertHall" alt="" />
                    <map name="concertHall" id="concertHall">
                        <%} else {%>
                        <img id="shape1" src="img/property/TheatreNo.png" style="width:100%; height: 100%;" usemap="#theatre" alt="" />
                        <map name="theatre" id="theatre">
                            <%}%>
                            <area shape="rect" alt="Image Map" style="outline:none;" title="Image Map"/>
                            <%for (int i = 0; i < coordinates.size(); i++) {
                                    storeSection.add("#Seat_" + coordinates.get(i).get(1));
                            %>
                            <area id="Seat_<%=coordinates.get(i).get(1)%>" onclick="openNav(<%=coordinates.get(i).get(1)%>)" alt="<%=coordinates.get(i).get(1)%>" title="Seat_<%=coordinates.get(i).get(1)%>" color="<%=coordinates.get(i).get(2)%>" href="#" shape="poly" coords="<%=coordinates.get(i).get(0)%>" style="outline:none;" target="_self"/>
                            <%}%>

                        </map>
                        <script lang="javascript">
                            $(document).ready(function () {
                                $('#shape1').mapster({
                                    singleSelect: false,
                                    fillColor: 'ff000c',
                                    fillOpacity: 0.5
                                });

                                $('.dropdown-submenu a.test').on("click", function (e) {
                                    $(this).next('ul').toggle();
                                    e.stopPropagation();
                                    e.preventDefault();
                                });
                            });

                            function openNav(element) {
                                $.ajax({
                                    url: "SetTicketsController?id=" + element + "&sessionID=" + $('#id').val(),
                                    success: function (result) {
                                        $("#sectionData").val(result);
                                    }
                                });
                            }
                            ;

                            function myFunction() {
                                $.ajax({
                                    url: "SetTicketsController?id=-1&sessionID=-1",
                                    success: function (result) {
                                        $("#sectionData").val(result);
                            <%for (int i = 0; i < storeSection.size(); i++) {%>
                                    $('<%=storeSection.get(i)%>').mapster('deselect');
                            <%}%>
                                    }
                                });
                            };
                            $("form").submit(function (event) {
                                if ($("#sectionData").val() == "\"\"") {
                                    alert("Please click at least one of the section to reserve.");
                                    return false;
                                } else if ($("#sectionData").val() == "") {
                                    alert("Please click at least one of the section to reserve.");
                                    return false;
                                }
                            });
                        </script></div>
                <div class="col-sm-6">
                    <div class="dropdown">
                        <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown">Reserved Section
                            <span class="caret"></span></button>
                        <ul class="dropdown-menu">
                            <li><a tabindex="-1" href="#">SessionNo: </a></li>
                                <%
                                    for (int i = 0; i < sectionData.size(); i++) {
                                        if (sectionData.get(i).size() != 1) { // got reserved sections
                                %>
                            <li class="dropdown-submenu pull-left">
                                <a class="test" tabindex="-1" href="#"><%=sectionData.get(i).get(0)%><span class="caret"></span></a>
                                <ul class="dropdown-menu pull-left">
                                    <li><a tabindex="-1" href="#">SectionNo | Purpose | ReservedDate </a></li>
                                        <%
                                            for (int j = 1; j < sectionData.get(i).size(); j += 3) {
                                        %>
                                    <li><a tabindex="-1" href="#"><%=sectionData.get(i).get(j) + " | " + sectionData.get(i).get(j + 1) + " | " + sectionData.get(i).get(j + 2)%></a></li>
                                        <%}%>
                                </ul>
                            </li>
                            <%
                                    }
                                }%>
                        </ul>
                    </div>
                    <table align="center">
                        <tr>
                            <td>&nbsp</td> 
                        </tr>
                        <tr>
                            <td>&nbsp</td> 
                        </tr>
                        <tr>
                            <td align="right">Apply to all Event/Sub-Event Session : &nbsp;</td>
                            <td align="center"><input type="radio" required="true" name="apply" value="yes">Yes &nbsp;<br></td>
                            <td align="left"><input type="radio" required="true" name="apply" value="no">No<br></td>

                        </tr>
                        <tr>
                            <td>&nbsp</td> 
                        </tr>
                    </table>
                    <table align="center">
                        <tr>
                            <td>Session : &nbsp</td>
                            <td>
                                <select class="form-control" id="id" name="id" required="true">
                                    <%                                            for (int i = 0; i < data.size(); i++) {
                                    %>
                                    <option value="<%=data.get(i).get(0)%>"><%=data.get(i).get(0) + " | " + data.get(i).get(1) + " | " + data.get(i).get(3) + " | " + data.get(i).get(4)%></option>
                                    <% }%>
                                </select></td>
                        </tr>
                        <tr>
                            <td>&nbsp</td> 
                        </tr>
                        <tr>
                            <td>Purpose : &nbsp</td>
                            <td align="left"><input type="text" class="form-control" required="true" id="purpose" name="purpose"</td> 
                        </tr>
                        <tr>
                            <td>&nbsp</td> 
                        </tr>
                        <tr><td align="right">End Date : &nbsp</td>
                            <td align="left"><input type="text" class="form-control date" required="true" name="date"</td>  
                        <font color="red">From now till <%=date%> (Event Start Date)</font><br/>
                        <script>
                            $('.date').datepicker({
                                multidate: false,
                                format: 'yyyy-mm-dd',
                                startDate: '$.now()',
                                endDate: '<%=date%>'
                            });
                        </script>
                        </tr>
                        <tr>
                            <td>&nbsp</td> 
                        </tr>
                        <tr>
                            <td>&nbsp</td> 
                        </tr>
                        <tr>
                            <td>&nbsp</td> 
                            <td>&nbsp</td> 
                            <td><input type="submit" value="Submit" onclick="myFunction()"/></td>
                        </tr>
                    </table>
                    <input type="hidden" id="sectionData" name="sectionData">
                    <%}%>
                </div>
            </form>

        </div> 
    </div>

</div>

<jsp:include page="footer.jsp" />