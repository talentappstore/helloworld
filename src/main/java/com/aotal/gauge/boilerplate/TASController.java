package com.aotal.gauge.boilerplate;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.client.RestTemplate;

/**
 * Supertype for TAS endpoint controllers:
 * - checks that the incoming tazzy-secret key matches our own secret key
 * - injects beans 
 * - logs basic details of incoming API call (not the request though - oh no, Spring) 
 * 
 * @author abraae
 *
 */
public class TASController {

	@Autowired
	protected RestTemplate restTemplate;
	@Autowired
	protected Environment env;
	@Autowired
	protected String inBase;
	@Autowired
	protected String outBase;

	private static final Logger logger = LoggerFactory.getLogger(TASController.class);
	
    // CRITICAL: for every endpoint in this controller, check that the incoming tazzy-secret matches our secret key 
	// TODO: move to a filter instead
/*	
	@ModelAttribute
	private void verify(@RequestHeader("tazzy-secret") String secret) {
		if (! secret.equals(env.getProperty("tas.secret"))) throw new UnauthorizedException();
	}
*/
	// for every endpoint in this controller, log incoming API call details
	// TODO: move to a filter instead
	@ModelAttribute
	private void logIncomingCall(HttpServletRequest request) throws IOException {
	    logger.info("> " + request.getMethod() + " " + request.getRequestURL().toString()
	    										+ (request.getQueryString() != null ? ("?" + request.getQueryString()) : ""));
	}

	
}
