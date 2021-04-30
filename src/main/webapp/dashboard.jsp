<%@include file="header.jsp" %>

<form action="route" method="get"><label>Search</label>
    <input type="text" id="startPoint" name="startPoint" placeholder="From Where">
    <input type="text" id="endPoint" name="endPoint" placeholder="Where To">
    <input type="submit" value="Search"><br>
</form>

<table>
    <thead>
    <tr>
        <th>Origin</th>
        <th>Destination</th>
        <th>Start Time</th>
        <th>End Time</th>
    </tr>
    </thead>
</table>
<%@include file="footer.jsp" %>