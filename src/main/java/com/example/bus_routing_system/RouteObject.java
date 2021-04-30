package com.example.bus_routing_system;

public class RouteObject {
    int id;
    String startPoint;
    String endPoint;
    int acCost;
    int nonACCost;
    String startTime;
    String endTime;
    public RouteObject(int id, String startPoint, String endPoint, int acCost, int nonACCost, String startTime, String endTime) {
        this.id = id;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.acCost = acCost;
        this.nonACCost = nonACCost;
        this.startTime = startTime;
        this.endTime = endTime;
    }


}
