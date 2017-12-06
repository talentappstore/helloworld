package com.aotal.gauge.endpoints.api;

import java.io.IOException;

//import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aotal.gauge.boilerplate.TASController;
import com.aotal.gauge.boilerplate.api.pojos.AppStatus;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * Serve up incoming tenant API calls
 * 
 * @author abraae
 *
 */
@RestController
public class TenantAPIController extends TASController {

	private static final Logger logger = LoggerFactory.getLogger(TenantAPIController.class);

	// respond with details of our app, e.g. its landing page (when user clicks "open" on the app in the storefront) 
	@RequestMapping(value = "/t/{tenant}/tas/devs/tas/appStatus", method = RequestMethod.GET)
	public AppStatus appStatus(@PathVariable String tenant) throws JsonParseException, JsonMappingException, IOException {

		AppStatus response = new AppStatus();
//		response.landingPage = inBase + "/t/" + tenant + "/account";
//		response.settingsPage = inBase + "/t/" + tenant + "/account";
		response.landingPage = inBase + "/account";
		response.settingsPage = inBase + "/account";
		response.setupRequired = true;

		return response;
	}

	
}

