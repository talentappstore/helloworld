package com.aotal.gauge.endpoints.api;

//import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aotal.gauge.boilerplate.TASController;
import com.aotal.gauge.boilerplate.api.pojos.Tenant;

/**
 * Serve up core out APIs
 * 
 * @author abraae
 *
 */
@RestController
public class CoreOutAPIController extends TASController {

	private static final Logger logger = LoggerFactory.getLogger(CoreOutAPIController.class);

	// create account in local db
	@RequestMapping(value = "/tas/core/tenants", method = RequestMethod.POST)
	public void createTenant(@RequestBody Tenant tenant) {

		// create an account in our database, with 0 credits
		logger.info("New customer! " + tenant.name + " just installed our app");
	}

	// delete account & associated assessments from local db
	@RequestMapping(value = "/tas/core/tenants/{tenant}", method = RequestMethod.DELETE)
	public void deleteTenant(@PathVariable String tenant) {

		logger.info("Goodbye :( Customer just uninstalled our app");
	}

}

