package com.company.transportrouteservice.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.company.transportrouteservice.dao.RouteDao;
import com.company.transportrouteservice.dao.RouteDaoImplementation;
import com.company.transportrouteservice.model.Request;

public class RouteService {

	private static RouteService routeService = null;
	private static final Logger LOGGER = Logger.getLogger(RouteService.class);
	private static RouteDao dao;
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	private RouteService() {

	}

	public static RouteService getRouteService() {
		if (routeService != null) {
			return routeService;
		} else {
			routeService = new RouteService();
		}
		return routeService;
	}

	public void addRoute(Request request) throws Exception {

		Date date = null;
		try {
			dao = new RouteDaoImplementation();
			date = format.parse(request.getRouteDate());
			if (!dao.checkRoute(request.getVehicleRouteMap().getRoute().getRouteId(),
					request.getVehicleRouteMap().getVehicle().getVehicleId(), new java.sql.Date(date.getTime()))) {
				LOGGER.info("Route not present proceeding to add routes");
				dao.addRoute(request.getVehicleRouteMap(), new java.sql.Date(date.getTime()));
			} else {
				LOGGER.error("Exceptin :: Rout present for route : "
						+ request.getVehicleRouteMap().getRoute().getRouteId() + " for vehicle "
						+ request.getVehicleRouteMap().getVehicle().getVehicleId() + " on route date :" + date);
				throw new Exception("Rout present for route : " + request.getVehicleRouteMap().getRoute().getRouteId()
						+ " for vehicle " + request.getVehicleRouteMap().getVehicle().getVehicleId()
						+ " on route date :" + date);
			}

		} catch (ParseException e) {
			LOGGER.error("ParseException :: " + e);
			throw new Exception("recived incorrect date format, verify and try again");
		} catch (NullPointerException e) {
			LOGGER.error("NullPointerException :: " + e);
			throw new Exception("[ vehicle , route] details are mandatory, verify and try again");
		} catch (Exception e) {
			LOGGER.error("Exception :: " + e);
			throw new Exception(e.getMessage());
		}

	}

}
