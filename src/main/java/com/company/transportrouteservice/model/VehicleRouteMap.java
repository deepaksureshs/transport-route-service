package com.company.transportrouteservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class VehicleRouteMap {

	private Integer vehicleRouteId;
	private String routeDate;
	private Integer totalCapacity;
	private Integer seatAvailable;
	private Long totalCollection;
	private String createdDate;
	private String updatedDate;

	private Vehicle vehicle;
	private Route route;
	private User createdBy;
	private User updatedBy;

	public Integer getVehicleRouteId() {
		return vehicleRouteId;
	}

	public void setVehicleRouteId(Integer vehicleRouteId) {
		this.vehicleRouteId = vehicleRouteId;
	}

	public String getRouteDate() {
		return routeDate;
	}

	public void setRouteDate(String routeDate) {
		this.routeDate = routeDate;
	}

	public Integer getTotalCapacity() {
		return totalCapacity;
	}

	public void setTotalCapacity(Integer totalCapacity) {
		this.totalCapacity = totalCapacity;
	}

	public Integer getSeatAvailable() {
		return seatAvailable;
	}

	public void setSeatAvailable(Integer seatAvailable) {
		this.seatAvailable = seatAvailable;
	}

	public Long getTotalCollection() {
		return totalCollection;
	}

	public void setTotalCollection(Long totalCollection) {
		this.totalCollection = totalCollection;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public User getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(User updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Override
	public String toString() {
		return "VehicleRouteMap [vehicleRouteId=" + vehicleRouteId + ", routeDate=" + routeDate + ", totalCapacity="
				+ totalCapacity + ", seatAvailable=" + seatAvailable + ", totalCollection=" + totalCollection
				+ ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + ", vehicle=" + vehicle + ", route="
				+ route + ", createdBy=" + createdBy + ", updatedBy=" + updatedBy + "]";
	}

}
