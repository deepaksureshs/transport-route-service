package com.company.transportrouteservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Request {

	private String routeDate;
	private Route route;
	private Vehicle vehicle;
	private VehicleRouteMap vehicleRouteMap;

	public VehicleRouteMap getVehicleRouteMap() {
		return vehicleRouteMap;
	}

	public void setVehicleRouteMap(VehicleRouteMap vehicleRouteMap) {
		this.vehicleRouteMap = vehicleRouteMap;
	}

	public String getRouteDate() {
		return routeDate;
	}

	public void setRouteDate(String routeDate) {
		this.routeDate = routeDate;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	@Override
	public String toString() {
		return "Request [routeDate=" + routeDate + ", route=" + route + ", vehicle=" + vehicle + ", vehicleRouteMap="
				+ vehicleRouteMap + "]";
	}

}
