package com.company.transportrouteservice.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

public class MapperConfig {

	private static ObjectMapper mapper = null;

	private MapperConfig() {
	}

	public static ObjectMapper getMapper() {
		if (mapper != null) {
			return mapper;
		} else {
			mapper = new ObjectMapper();
			mapper.setPropertyNamingStrategy(PropertyNamingStrategy.KEBAB_CASE);
			mapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		}
		return mapper;
	}

}
