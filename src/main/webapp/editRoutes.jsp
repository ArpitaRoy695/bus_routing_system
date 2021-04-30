<%@include file="header.jsp" %>
<%@include file="adminNav.jsp"%>

<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>

<table border="2">
    <tr>
        <td>Route ID</td>
        <td>Start Point</td>
        <td>End Point</td>
        <td>Start Time</td>
        <td>End Time</td>
        <td>AC Cost</td>
        <td>Non-AC Cost</td>
    </tr>
    <%
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/bus_routing_system";
            String username="root";
            String password="";
            String query="SELECT * FROM Routes";
            Connection conn=DriverManager.getConnection(url,username,password);
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery(query);
            while(rs.next())
            {

    %><tr>
    <td><%=rs.getInt("Route_ID") %></td>
    <td><%=rs.getString("Start_point") %></td>
    <td><%=rs.getString("End_point") %></td>
    <td><%=rs.getString("Start_time") %></td>
    <td><%=rs.getString("End_time") %></td>
    <td><%=rs.getString("Non_AC_Cost") %></td>
    <td><%=rs.getString("AC_Cost") %></td>
</tr>
    <%

        }
    %>
</table>
<%
        rs.close();
        stmt.close();
        conn.close();
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }



%>

<h4>Edit An Existing Route</h4>
<form action="routes" method="POST">
    <label for="routeID_update">Route ID</label>
    <input type="number" name="routeID_update" id="routeID_update" placeholder="Enter Route ID"> &nbsp;
    <label for="acCostUpdate">AC Cost</label>
    <input type="number" name="acCostUpdate" id="acCostUpdate" placeholder="Enter New AC Cost ">
    <label for="nonACCostUpdate">Non-AC Cost</label>
    <input type="number" name="nonACCostUpdate" id="nonACCostUpdate" placeholder="Enter New AC Cost ">&nbsp;
    <input type="submit" name="route_update" id="route_update" value="Update"><br>
    <c:if test="${routeErrors.routeIDUpdateInvalid == 1}">
        <small>Invalid Route ID!</small><br>
    </c:if>
    <c:if test="${routeErrors.costUpdate == 1}">
        <small>No Cost Field Selected</small><br>
    </c:if>
</form>

<h4>Add A New Route</h4>
<form action="routes" method="POST">
    <label for="route_Start">Start Point</label><br>
    <input type="text" name="route_Start" id="route_Start" placeholder="Enter Start Point"><br> &nbsp;
    <label for="route_End">End Point</label><br>
    <input type="text" name="route_End" id="route_End" placeholder="Enter End Point"><br>
    <label for="route_StartTime">Start Time</label><br>
    <input type="time" name="route_StartTime" id="route_StartTime"><br>
    <label for="route_EndTime">End Time</label><br>
    <input type="time" name="route_EndTime" id="route_EndTime"><br>
    <label for="ac_Cost">AC Cost</label><br>
    <input type="number" name="ac_Cost" id ="ac_Cost" placeholder="Enter AC Cost"><br>
    <label for="nonac_Cost">Non-AC Cost</label><br>
    <input type="number" name="nonac_Cost" id ="nonac_Cost" placeholder="Enter Non-AC Cost"><br>&nbsp;
    <input type="submit" name="route_create" id="route_create" value="Create"><br>
</form>


<%@include file="footer.jsp" %>