<%@include file="header.jsp" %>
<%@include file="adminNav.jsp"%>

<h4>Edit An Existing Bus</h4>
<form action="bus" method="POST">

    <label for="busID_update">Bus ID</label>
    <input type="number" name="busID_update" id="busID_update" placeholder="Enter Bus ID"> &nbsp;
    <label for="routeID_update">Route ID</label>
    <input type="number" name="routeID_update" id="routeID_update" placeholder="Enter Route ID"> &nbsp;
    <input type="submit" name="bus_update" id="bus_update" value="Update">
    <c:if test="${busErrors.busUpdateRoute == 1}">
        <br><small>The Route Does Not Exist!</small><br>
    </c:if>
    <c:if test="${busErrors.busUpdateBus == 1}">
        <small>The Bus Does Not Exist!</small><br>
    </c:if>
</form>

<h4>Add A New Bus</h4>
<form action="bus" method="POST">
    <label for="routeID_create">Route ID</label>
    <input type="number" name="routeID_create" id="routeID_create" placeholder="Enter Route ID">
    <label for="busType_create">Type</label>
    <input type="text" name="busType_create" id="busType_create" placeholder="AC/Non-AC">&nbsp;
    <input type="submit" name="bus_create" id="bus_create" value="Create">
    <c:if test="${busErrors.busCreateType == 1}">
        <br><small>Wrong Bus Type!</small><br>
    </c:if>
    <c:if test="${busErrors.busCreateRoute == 1}">
        <small>Route Does Not Exist!</small><br>
    </c:if>
</form>




<%@include file="footer.jsp" %>