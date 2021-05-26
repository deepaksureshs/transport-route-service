package com.company.transportrouteservice.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;

import com.company.transportrouteservice.config.DbConfig;
import com.company.transportrouteservice.model.Request;
import com.company.transportrouteservice.model.VehicleRouteMap;

public class RouteDaoImplementation implements RouteDao {
	public static Connection connection = null;
	private static final Logger LOGGER = Logger.getLogger(RouteDaoImplementation.class);
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public RouteDaoImplementation() throws Exception {
		if (connection == null) {
			connection = DbConfig.getConnection();
		}
	}

	public boolean checkRoute(int routeId, int vehicleId, Date routeDate) throws Exception {
		LOGGER.info("checking if route id " + routeId + " exist for vehicle id " + vehicleId + " on route date : "
				+ routeDate);
		PreparedStatement statement = connection.prepareStatement("SELECT count(vehicle_route_id)\r\n"
				+ "FROM vehicle_route_map WHERE route_id=? AND vehicle_id=? AND route_date=?");
		statement.setInt(1, routeId);
		statement.setInt(2, vehicleId);
		statement.setDate(3, routeDate);
		ResultSet rs = statement.executeQuery();
		int count = 0;
		while (rs.next()) {
			count = rs.getInt(1);
		}
		if (count == 0) {
			return false;
		} else {
			return true;
		}
	}

	public void addRoute(VehicleRouteMap vehicleRouteMap, Date date) throws Exception {
		LOGGER.info("Assigning route : " + vehicleRouteMap.getRoute().getRouteId() + " for vehicle "
				+ vehicleRouteMap.getVehicle().getVehicleId() + " on route date :" + date);
		try {
			PreparedStatement statementGetCapacity = connection
					.prepareStatement("SELECT capacity FROM vehicle WHERE vehicle_id=?");
			statementGetCapacity.setInt(1, vehicleRouteMap.getVehicle().getVehicleId());
			LOGGER.info("statementGetCapacity" + statementGetCapacity);
			ResultSet rs = statementGetCapacity.executeQuery();
			int capacity = 0;
			while (rs.next()) {
				capacity = rs.getInt(1);
			}

			PreparedStatement statementInsertRoute = connection.prepareStatement(
					"INSERT INTO VEHICLE_ROUTE_MAP (vehicle_id, route_id, route_date,total_capacity, seat_available, total_collection, created_date, updated_date,created_by,updated_by)\r\n"
							+ "VALUES (?,?,?,?,?,?,?,?,?,?)");
			java.util.Date createdDate = format.parse(vehicleRouteMap.getCreatedDate());
			java.util.Date updatDate = format.parse(vehicleRouteMap.getUpdatedDate());
			statementInsertRoute.setInt(1, vehicleRouteMap.getVehicle().getVehicleId());
			statementInsertRoute.setInt(2, vehicleRouteMap.getRoute().getRouteId());
			statementInsertRoute.setDate(3, date);
			statementInsertRoute.setInt(4, capacity);
			statementInsertRoute.setInt(5, capacity);
			statementInsertRoute.setLong(6, 0);
			statementInsertRoute.setTimestamp(7, new Timestamp(createdDate.getTime()));
			statementInsertRoute.setTimestamp(8, new Timestamp(updatDate.getTime()));
			statementInsertRoute.setString(9, vehicleRouteMap.getCreatedBy().getEmail());
			statementInsertRoute.setString(10, vehicleRouteMap.getUpdatedBy().getEmail());

			LOGGER.info("statementInsertRoute" + statementInsertRoute);
			statementInsertRoute.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("SQLException  check [routeid, vehicle id]" + e);
			throw new Exception("Exception  check [routeid, vehicle id] ");
		} catch (Exception e) {
			LOGGER.error("Exception Route not assigned " + e);
			throw new Exception("Exception Route not assigned check the details provided :: " + e.getMessage());
		}

	}

}
