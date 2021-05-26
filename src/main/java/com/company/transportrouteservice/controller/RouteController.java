package com.company.transportrouteservice.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import com.company.transportrouteservice.config.MapperConfig;
import com.company.transportrouteservice.model.ErrorResponse;
import com.company.transportrouteservice.model.Request;
import com.company.transportrouteservice.service.RouteService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RouteController {

	private static final Logger LOGGER = Logger.getLogger(RouteController.class);
	private static ObjectMapper mapper = MapperConfig.getMapper();
	private static RouteService routeService = RouteService.getRouteService();

	@POST
	public Response addRoute(String payload) {
		LOGGER.info("Request received for assigning route :: payload " + payload);
		Request request = new Request();
		try {
			request = mapper.readValue(payload, Request.class);
			routeService.addRoute(request);
			LOGGER.info("Request processed successfully");
		} catch (Exception exception) {
			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setStatus("Error");
			errorResponse.setMessage(exception.getMessage());
			LOGGER.error("Error response :: " + errorResponse);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorResponse).build();
		}
		return Response.status(Status.CREATED).build();
	}

}
