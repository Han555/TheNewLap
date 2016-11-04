<%-- 
    Document   : displaySeats
    Created on : 21 Sep, 2016, 10:37:50 PM
    Author     : JingYing
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList, java.util.List" %>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!doctype html>
<jsp:include page="header2.jsp" />
<!-- Main Content -->


<div class="container-fluid">
    <%
        List<ArrayList> data = (List<ArrayList>) request.getAttribute("data");
        List<ArrayList> price = (List<ArrayList>) request.getAttribute("price");
        List<ArrayList> coordinates = (List<ArrayList>) request.getAttribute("coordinates");
    %>

    <c:url var="formAction" value="/BackController?action=seatsPriceCreated" />
    <div class="side-body padding-top">

        <div class="row">
            <div class="col-sm-6">
                <div class="header">
                    <h4 class="title">Set Area Pricing</h4>   
                </div>
                <%
                    if (data == null) {
                %>
                <div align="center"><h3>No SESSION Found!</h3></div><br><br>
                <%} else {

                    ArrayList storeCategory = new ArrayList();
                    int storedCategory = 0;
                    int category = Integer.valueOf(coordinates.get(coordinates.size() - 1).get(2).toString());

                    String output = "ID | ";
                    for (int j = 1; j <= category; j++) {
                        output += "CAT" + j + " | ";
                    }
                    output += "Seats Option";
                    String x = data.get(0).get(5).toString();
                %>
                <table>
                    <tr>
                        <td>&nbsp; Previous Price : &nbsp;</td>
                        <td><select class="form-control" required="true">
                                <option><%=output%></option>
                                <%
                                    //price.get(i).get(0) + " | " + price.get(i).get(2) + " | " + price.get(i + 1).get(2) + " | " + price.get(i + 2).get(2) + " | " + price.get(i + 3).get(2) + " | " + price.get(i).get(3)
                                    for (int i = 0; i < price.size(); i = i + category) {
                                        output = "";
                                        output += price.get(i).get(0).toString() + " | ";
                                        for (int j = 0; j < category; j++) {
                                            output += price.get(i + j).get(2).toString() + " | ";
                                        }
                                        output += price.get(i).get(3).toString();
                                %>
                                <option><%=output%></option>
                                <%}%>
                            </select></td>
                    </tr>
                    <tr>
                        <td>&nbsp</td> 
                    </tr>
                </table>
                <%  if (x.equals("1")) {%>
                <img id="shape1" src="img/property/Concerthall_layout.png" style="width:100%; height: 100%;" usemap="#concertHall" alt="" />
                <map name="concertHall" id="concertHall">
                    <%} else {%>
                    <img id="shape1" src="img/property/Theatre.png" style="width:100%; height: 100%;" usemap="#theatre" alt="" />
                    <map name="theatre" id="theatre">
                        <%}%>
                        <area shape="rect" alt="Image Map" style="outline:none;" title="Image Map"/>
                        <%for (int i = 0; i < coordinates.size(); i++) {
                                if (storedCategory != Integer.valueOf(coordinates.get(i).get(2).toString())) { //Check the first id for the first category
                                    storedCategory = Integer.valueOf(coordinates.get(i).get(2).toString());
                                    storeCategory.add("#Seat_" + coordinates.get(i).get(1));
                                }
                        %>
                        <area id="Seat_<%=coordinates.get(i).get(1)%>" alt="" title="Seat_<%=coordinates.get(i).get(1)%>" color="<%=coordinates.get(i).get(2)%>" href="#" shape="poly" coords="<%=coordinates.get(i).get(0)%>" style="outline:none;" target="_self"/>
                        <%}%>

                    </map>
                    <script lang="javascript">
                        $(document).ready(function () {
                            $('#shape1').mapster({
                                singleSelect: true,
                                mapKey: 'color',
                                fillColor: 'ff000c',
                                fillOpacity: 0.5
                            });
                        });

                        <%for (int i = 0; i < storeCategory.size(); i++) {
                                String catString = "setCat" + (i + 1);%>
                        function <%=catString%>() {
                            $('<%=storeCategory.get(i)%>').mapster('select');
                        }
                        <%}%>

                    </script></div>
            <div class="col-sm-6">
                <form id="contact_form" action="${formAction}" method="POST">
                    <table align="center">
                        <tr>
                            <td>&nbsp</td> 
                        </tr>
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
                            <td>Session : &nbsp;</td>
                            <td>
                                <select class="form-control" id="id" name="id" required="true">
                                    <%
                                        for (int i = 0; i < data.size(); i++) {
                                    %>
                                    <option value="<%=data.get(i).get(0)%>"><%=data.get(i).get(0) + " | " + data.get(i).get(1) + " | " + data.get(i).get(3) + " | " + data.get(i).get(4)%></option>
                                    <% } %>
                                </select></td>
                        </tr>
                        <tr>
                            <td>&nbsp</td> 
                        </tr>
                    </table>
                    <table align="center">
                        <%
                            for (int j = 0; j < Integer.valueOf(data.get(0).get(6).toString()); j++) {
                        %>
                        <tr>
                            <td align="right">Category <%=j + 1%> : &nbsp</td>
                            <td align="left"><input type="number" class="form-control" required="true" id="cat<%=j + 1%>" name="cat<%=j + 1%>" onFocus="setCat<%=j + 1%>()"</td> 
                        </tr>
                        <tr>
                            <td>&nbsp</td> 
                        </tr>
                        <%}%>
                        <tr>
                            <td align="right">Seats Option : &nbsp</td>
                            <td><select class="form-control" required="true" id="seatsOption" name="seatsOption">
                                    <option value="Free Seating">Free Seating</option>
                                    <option value="Fix Seating">Fix Seating within Sector</option>
                                </select></td>
                        </tr>
                        <tr>
                            <td>&nbsp</td> 
                        </tr>
                        <tr>
                            <td>&nbsp</td> 
                            <td>&nbsp</td> 
                            <td><input type="submit" value="Submit"/></td>
                        </tr>
                    </table>
                    <input type="hidden" value=<%=data.get(0).get(6)%> name="noCat">
                    <script>
                        $("form").submit(function (event) {
                            var i;
                            var j;
                            if ($('#seatsOption').val() == "Free Seating") {
                               for (i = 1; i <= <%=Integer.valueOf(data.get(0).get(6).toString())%>; i++) {
                                   for (j = i + 1; j <= <%=Integer.valueOf(data.get(0).get(6).toString())%>; j++) {
                                        var x = document.getElementById("cat" + i).value;
                                        var y = document.getElementById("cat" + j).value;
                                        if (x !== y) {
                                            alert("For Free seating ticket price must be the same as all category.");
                                            return false;
                                        }
                                    }
                                }
                            }
                        });
                    </script>   <%}%>
                </form>
            </div>
        </div> 
    </div>

</div>

<jsp:include page="footer.jsp" />