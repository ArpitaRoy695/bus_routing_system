package com.example.bus_routing_system;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "bus", value = "/bus")
public class BusController extends HttpServlet {
    Bus bus = new Bus();
    Route route = new Route();
    Map<String,Integer> busErrors = new HashMap<String,Integer>();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        request.setAttribute("title","Edit Buses");
        try {
            request.getRequestDispatcher("editBuses.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException{
        request.setAttribute("title","Edit Buses");
        request.setAttribute("busErrors",busErrors);
        if(request.getParameterMap().containsKey("bus_update") &&
                request.getParameter("bus_update") != null){

            request.setAttribute("bus_update", null);
            busErrors.put("busCreateRoute", 0);
            busErrors.put("busCreateType", 0);

            if(request.getParameter("busID_update") == null ||
                    request.getParameter("routeID_update") == null) {
                try {
                    request.getRequestDispatcher("editBuses.jsp").forward(request,response);
                } catch (ServletException e) {
                    e.printStackTrace();
                }
            }

            int busID = Integer.parseInt(request.getParameter("busID_update"));
            int routeID = Integer.parseInt(request.getParameter("routeID_update"));

            if(bus.exists(busID))
                busErrors.put("busUpdateBus", 0);
            else
                busErrors.put("busUpdateBus", 1);

            if(route.exists(routeID))
                busErrors.put("busUpdateRoute", 0);
            else
                busErrors.put("busUpdateRoute", 1);

            if(busErrors.get("busUpdateBus") == 0 && busErrors.get("busUpdateRoute") == 0){
                bus.update(routeID, busID);
            }

            try {
                request.getRequestDispatcher("editBuses.jsp").forward(request,response);
            } catch (ServletException e) {
                e.printStackTrace();
            }

        }else {
            busErrors.put("busUpdateRoute", 0);
            busErrors.put("busUpdateBus", 0);
            if(request.getParameter("routeID_create") == null ||
                request.getParameter("busType_create") == null) {
                try {
                    request.getRequestDispatcher("editBuses.jsp").forward(request,response);
                } catch (ServletException e) {
                    e.printStackTrace();
                }
            }

            int routeID = Integer.parseInt(request.getParameter("routeID_create"));
            if (route.exists(routeID))
                busErrors.put("busCreateRoute", 0);
            else
                busErrors.put("busCreateRoute", 1);

            String busType = request.getParameter("busType_create").toLowerCase();
            if (busType.equals("ac") || busType.equals("non-ac"))
                busErrors.put("busCreateType", 0);
            else
                busErrors.put("busCreateType", 1);

            if (busErrors.get("busCreateRoute") == 0 && busErrors.get("busCreateType") == 0)
                bus.create(routeID, busType);

            try {
                request.getRequestDispatcher("editBuses.jsp").forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }

    }

}
