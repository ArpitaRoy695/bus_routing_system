package com.example.bus_routing_system;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@WebServlet(name = "Login", value = "/login")
public class UserController extends HttpServlet {
    User user = new User();
    Map<String,Integer> userErrors = new HashMap<String,Integer>();
    Map<String,String> userInfo = new HashMap<String,String>();

    public void init(){

    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setAttribute("userErrors",userErrors);
        request.setAttribute("User", userInfo);
        try {
            if (request.getParameterMap().containsKey("loginButton")
                    && request.getParameter("loginButton") != null
            ){
                String email = request.getParameter("emailSI");
                int userID = user.exists("Email",request.getParameter("emailSI"));
                String password = request.getParameter("passwordSI");

                if(userID != -1 && user.confirmPassword(userID,password)) {
                    userInfo.put("User_ID", Integer.toString(userID));
                    userInfo.put("Role", "Passenger");


                    userErrors.put("login",0);
                    request.setAttribute("title","Dashboard");
                    if (user.isAdmin(email) != -1) {
                        userInfo.put("Role", "admin");
                        request.getRequestDispatcher("adminDashboard.jsp").forward(request, response);
                    }else
                        request.getRequestDispatcher("dashboard.jsp").forward(request,response);

                }else{
                    userErrors.put("login",1);
                    request.setAttribute("title","Login");
                    request.getRequestDispatcher("login.jsp").forward(request,response);
                }

            }else {
                request.setAttribute("title","Login");
                request.getRequestDispatcher("login.jsp").forward(request, response);

            }
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException{
        request.setAttribute("userErrors",userErrors);
        request.setAttribute("userInfo", userInfo);
        try {
            String email = request.getParameter("emailSU");
            String phoneNumber = request.getParameter("numberSU");

            if (user.exists("Email", email) != -1)
                userErrors.put("signUpMail", 1);
            else
                userErrors.put("signUpMail", 0);


            if (user.exists("Phone_number", phoneNumber) != -1)
                userErrors.put("signUpPhone", 1);
            else
                userErrors.put("signUpPhone", 0);

            if(userErrors.get("signUpMail") == 0
                    && userErrors.get("signUpPhone") == 0){
                String name = request.getParameter("name");
                String address = request.getParameter("addressSU");
                String password = request.getParameter("passwordSU");

                user.Create(name, address, phoneNumber,
                        email, "Passenger", password);
                userInfo.put("login", "Passenger");
                userInfo.put("userID", Integer.toString(user.exists("Email", request.getParameter("emailSI"))));
                request.setAttribute("title", "Dashboard");
                request.getRequestDispatcher("dashboard.jsp").forward(request, response);

            }else{
                request.setAttribute("title", "Login");
                request.getRequestDispatcher("login.jsp").forward(request,response);
            }
        } catch (ServletException e) {
            e.printStackTrace();
        }


    }


}
