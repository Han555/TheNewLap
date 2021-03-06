
      <%-- 
    Document   : equipment
    Created on : Sep 20, 2016, 5:02:22 PM
    Author     : catherinexiong
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:useBean id="properties" class="java.util.List<entity.PropertyEntity>" scope="request"/>
<jsp:include page="header.jsp" />

<!-- Main Content -->
<div class="side-body">
    <div class="page-title">
        <span class="title">Equipments</span>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <div class="card">
                <div class="card-header">
                    <div class="card-title">
                        <div class="title">View, Update and Delete Equipments</div>
                        <div class="description">The table below shows equipments group by properties.</div>
                    </div>
                </div>
                <div class="card-body">
                    <div style="padding-bottom: 20px;">
                        <table class="table-hover" cellspacing="0" width="100%">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Equipment Name</th>
                                    <th>Location</th>
                                    <th>Standard</th>
                                    <th>Price</th>
                                    <th>Delete</th>
                                    <th>Update</th>
                                </tr>
                            </thead>
                            <tbody id="items"></tbody>
                        </table>
                    </div>
                   <div class="form-group" style="padding-bottom: 50px;" >
                        <label for="propertyList" class="col-sm-2 control-label">Choose a Property</label>
                        <div class="col-sm-6">
                            <select class="js-example-basic-single js-states" id="propertyList" name="propertyList">
                                <c:forEach items="${properties}" var="properties">
                                    <option value="${properties.id}">${properties.propertyName}</option>
                                </c:forEach>	
                            </select>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>


</div>





<div class="modal fade" id="delete-confirm" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-body">
                <p>Are you sure to delete?</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="deleteOk" data-dismiss="modal">Yes</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
            </div>
        </div>

    </div>
</div>

<div class="modal fade" id="update-data" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-body">
                    <div class="form-group" style="padding-bottom: 30px;" >
                        <label for="ename" class="col-sm-2 control-label">Equipment Name</label>
                        <div class="col-sm-6">
                            <input type="text" id="update-name" class="form-control" name="ename">
                            <div id="alert-empty"></div>
                        </div>
                    </div>
                    <div class="form-group" style="padding-bottom: 30px;" >
                        <label for="elocation" class="col-sm-2 control-label">Equipment Location</label>
                        <div class="col-sm-6">
                            <input type="text" id="update-location" class="form-control" name="elocation">
                            <div id="alert-empty"></div>
                        </div>
                    </div><br/>
               
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="updateOk">Ok</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
            </div>
        </div>

    </div>
</div>

<script>
    var propertyId = 1;
    
    var equipmentId;
    var equipmentList = [];
  
    var msg;
   

    
    
    
    
    

    function deleteEquipment(id) {
        equipmentId = id;
        $("#delete-confirm").modal();
    }
    
    $("#deleteOk").click(function() {
        $.ajax({
            url: "DeleteEquipment?id=" + equipmentId,
            success:function(result) {
                if (result == "\"success\"") {
                    getEquipment();
                }
            }
            
        });
    });
    
    function update(id) {
        equipmentId = id;
        
        console.log(equipmentList);
        var originalname;
        var originalloca;
        for (var i = 0; i < equipmentList.length; i++) {
           if (equipmentList[i].id == equipmentId) {
               originalname = equipmentList[i].name;
               originalloca = equipmentList[i].location;
           }   
        }
        $("#update-name").val(originalname);
        $("#update-location").val(originalloca);
        $("#update-data").modal();
    }
    
    $("#updateOk").click(function() {
        console.log("DAS");
        console.log($("#update-name").val());
        $.ajax({
            url: "UpdateEquipment?eid=" + equipmentId + "&propertyId=" + propertyId + "&ename=" + $("#update-name").val()+ "&elocation=" + $("#update-location").val(),
            success:function(result) {
                if (result == "\"success\"") {
                    getEquipment();
                    $("#update-data").modal('hide');
                } else {
                    $("#notifyPeak").html("Errors happened when updating data.").css("color", "red");
                    
                }
            }
            
        });
    });
    
    function getEquipment() {
        $.ajax({
            url: "equipmentList?id=" + propertyId,
            success: function (result) {
                var str = "";
                equipmentList = result;
                for (var i = 0; i < result.length; i++) {
                    console.log(result[i]);
                    str += "<tr><td>" + (i + 1) + "</td><td>" + result[i].name + "</td><td>" + result[i].location + "</td><td>" + result[i].standard +"</td><td>" + result[i].price +"</td>";
                    str += '<td><button type="button" class="btn btn-default" onclick="deleteEquipment(' + result[i].id + ');">Delete</button></td>';
                    str += '<td><button type="button" class="btn btn-default" onclick="update(' + result[i].id + ');">Update</button></td>';
                    str += '</tr>'
                }
                $('#items').html(str);
            }
        });
    }

    $(document).ready(function () {

       
        getEquipment();
        $('#propertyList').change(function () {
            propertyId = $(this).val();
            getEquipment();
           
        });
    });
  




</script>
<jsp:include page="footer.jsp" />


