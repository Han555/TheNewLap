<%-- 
    Document   : deleteTickets
    Created on : Sep 22, 2016, 7:29:26 PM
    Author     : Student-ID
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!doctype html>
<jsp:include page="header2.jsp" />
<!-- Main Content -->
<!-- Main Content -->
<div class="container-fluid">
    <%
        List<ArrayList> data = (List<ArrayList>) request.getAttribute("data");
        String property = request.getAttribute("propertyID").toString();
        List<ArrayList> coordinates = (List<ArrayList>) request.getAttribute("coordinates");
        String filename = request.getAttribute("filename").toString();
    %>
    <c:url var="formAction" value="/BackController?action=deletedTickets" />
    <div class="side-body padding-top">

        <div class="row">
            <div class="col-sm-6">
                <div class="header">
                    <h4 class="title">Delete Reserved Section Tickets</h4>   
                </div>  <%
                    if (data.size() == 0 || data == null) {
                %>
                <div align="center"><h3>No Reserved Section Found!</h3></div><br><br>
                <%} else {
                %>
                <img id="shape1" src="contentImageController?id=<%=filename%>" style="width:100%; height: 100%;" usemap="#property" alt="" />
                    <map name="property" id="property">
                        <area shape="rect" alt="Image Map" style="outline:none;" title="Image Map"/>
                        <%for (int i = 0; i < coordinates.size(); i++) {
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
                        });
                    </script>
            </div>
            <div class="col-md-6">
                <table align="center">
                    <tr>
                        <td>&nbsp</td> 
                        <td>&nbsp</td> 
                    </tr>
                    <tr>
                        <td>&nbsp</td> 
                        <td>&nbsp</td> 
                    </tr>
                </table>
                <div class="card">                    
                    <form id="contact_form" action="${formAction}" method="POST">
                        <table id="table" class="table table-hover">
                            <thead>
                                <tr>
                                    <th data-radio="true"></th>
                                    <th>Section No.</th>
                                    <th>Purpose</th>
                                    <th>Reserved End Date</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    for (int i = 0; i < data.size(); i++) {
                                %>
                                <tr>
                                    <td><input type="checkbox" name="id" value="<%= data.get(i).get(0)%>"></td>
                                    <td><%= data.get(i).get(1)%></td>
                                    <td><%= data.get(i).get(2)%></td>
                                    <td><%= data.get(i).get(3)%></td></tr>
                                    <% }%>
                            </tbody>

                        </table>
                        <table align="center">
                            <tr>
                                <td>&nbsp</td> 
                                <td>&nbsp</td> 

                                <td> <c:url var="formAction" value="/BackController" /><input type="submit" value="Delete" id="button" disabled="false"/>

                            </tr>
                        </table>
                        <script>
                            var checkboxes = $("input[type='checkbox']"),
                                    submitButt = $("input[type='submit']");

                            checkboxes.click(function () {
                                submitButt.attr("disabled", !checkboxes.is(":checked"));
                            });

                            $("form").submit(function (event) {
                                if (confirm("You are about to delete the reserved sections you have selected. Are you sure about that?")) {
                                    return true;
                                } else {
                                    return false;
                                }
                            });
                        </script>
                        <%}%>
                    </form> 
                </div>
            </div>
        </div> 
    </div>
</div>

<jsp:include page="footer.jsp" />

