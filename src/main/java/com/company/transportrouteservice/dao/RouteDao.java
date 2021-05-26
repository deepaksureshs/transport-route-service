package com.company.transportrouteservice.dao;

import java.sql.Date;

import com.company.transportrouteservice.model.VehicleRouteMap;

public interface RouteDao {

	public boolean checkRoute(int routeId, int vehicleId, Date routeDate) throws Exception;

	public void addRoute(VehicleRouteMap vehicleRouteMap, Date date) throws Exception;

}
