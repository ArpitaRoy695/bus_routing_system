package com.example.bus_routing_system;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@WebServlet(name = "Router", value = "/routes")
public class RouteController extends HttpServlet{
    Route route = new Route();
    Map<String,Integer> routeErrors = new HashMap<String,Integer>();

    public void init(){

    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException{
        request.setAttribute("title", "Edit Routes");
        try{
            request.setAttribute("routeErrors",routeErrors);
            request.getRequestDispatcher("editRoutes.jsp").forward(request,response);
        }catch(Exception e){

        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        try {
            request.setAttribute("title", "Edit Routes");
            request.setAttribute("routeErrors",routeErrors);
            if(request.getParameterMap().containsKey("route_update") &&
                    request.getParameter("route_update") != null){

                request.setAttribute("route_update", null);

                if(request.getParameter("routeID_update") != null){

                    int routeID = Integer.parseInt(request.getParameter("routeID_update"));
                    if(route.exists(routeID))
                        routeErrors.put("routeIDUpdateInvalid",0);
                    else
                        routeErrors.put("routeIDUpdateInvalid",1);


                    if(request.getParameter("acCostUpdate") != null ||
                            request.getParameter("nonACCostUpdate") != null){

                        routeErrors.put("costUpdate",0);

                        if(request.getParameter("acCostUpdate") != null)
                            route.Update("AC_Cost",request.getParameter("acCostUpdate"),routeID);

                        if(request.getParameter("nonACCostUpdate") != null)
                            route.Update("Non_AC_Cost",request.getParameter("nonACCostUpdate"),routeID);

                    }else{
                        routeErrors.put("costUpdate",1);
                    }
                    request.setAttribute("title", "Edit Routes");
                    request.getRequestDispatcher("editRoutes.jsp").forward(request,response);
                }else
                    routeErrors.put("routeIDUpdateInvalid",1);

                request.setAttribute("title", "Edit Routes");
                request.getRequestDispatcher("editRoutes.jsp").forward(request,response);


            }else if (request.getParameterMap().containsKey("route_create") &&
                request.getParameter("route_create") != null){
                request.setAttribute("route_create", null);
                String starPoint = "";
                String endPoint = "";
                String startTime = "";
                String endTime = "";
                int acCost = -1;
                int nonACCost = -1;

                if(request.getParameter("route_Start") != null){
                    starPoint = request.getParameter("route_Start");
                    routeErrors.put("routeCreate_startPoint",0);
                }else
                    routeErrors.put("routeCreate_startPoint",1);

                if(request.getParameter("route_End") != null){
                    endPoint = request.getParameter("route_End");
                    routeErrors.put("routeCreate_route_End",0);
                }else
                    routeErrors.put("routeCreate_route_End",1);

                if(request.getParameter("route_StartTime") != null){
                    startTime = request.getParameter("route_StartTime");
                    routeErrors.put("routeCreate_StartTime",0);
                }else
                    routeErrors.put("routeCreate_StartTime",1);

                if(request.getParameter("route_EndTime") != null){
                    endTime = request.getParameter("route_EndTime");
                    routeErrors.put("routeCreate_EndTime",0);
                }else
                    routeErrors.put("routeCreate_EndTime",1);

                if(request.getParameter("ac_Cost") != null){
                    acCost = Integer.parseInt(request.getParameter("ac_Cost"));
                    routeErrors.put("routeCreate_ac_Cost",0);
                }else
                    routeErrors.put("routeCreate_ac_Cost",1);

                if(request.getParameter("nonac_Cost") != null){
                    nonACCost = Integer.parseInt(request.getParameter("nonac_Cost"));
                    routeErrors.put("routeCreate_nonac_Cost",0);
                }else
                    routeErrors.put("routeCreate_nonac_Cost",1);

                if(routeErrors.get("routeCreate_nonac_Cost") == 0 &&
                        routeErrors.get("routeCreate_ac_Cost") == 0 &&
                        routeErrors.get("routeCreate_EndTime") == 0 &&
                        routeErrors.get("routeCreate_StartTime") == 0 &&
                        routeErrors.get("routeCreate_route_End") == 0 &&
                        routeErrors.get("routeCreate_StartTime") == 0 )
                    route.Create(starPoint,endPoint,startTime,endTime,acCost,nonACCost);

                request.getRequestDispatcher("editRoutes.jsp").forward(request,response);


            }
        }catch (Exception e){

        }
    }
}