package com.aotal.gauge.endpoints.web;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.aotal.gauge.boilerplate.TASController;
import com.aotal.gauge.boilerplate.UnauthorizedException;
import com.aotal.gauge.boilerplate.api.pojos.SamlDetail;
import com.aotal.gauge.boilerplate.api.pojos.Tenant;

/**
 * Web traffic to do with the account page
 * 
 * @author abraae
 *
 */
@Controller
public class AccountController  extends TASController {

	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

	// call core APIs to fetch tenant and signed in user details, and attach them to the model
	// TODO caching
	private void populateModel(String tenant, String samlKey, Model model) {
		
		// get tenant details and attach
		{
			String url = outBase + "/core/tenants/" + tenant;
			Tenant tenantObject = restTemplate.exchange(url, HttpMethod.GET, null, Tenant.class).getBody(); 
			model.addAttribute("tenant", tenantObject);
		}

		// get saml details for user and attach
		{
			String url = outBase + "/core/tenants/" + tenant + "/saml/assertions/byKey/" + samlKey + "/json";
			SamlDetail sam = restTemplate.exchange(url, HttpMethod.GET, null, SamlDetail.class).getBody();
			model.addAttribute("samlDetail", sam);
		}
	}
	
	// when user views their account
//    @GetMapping("/t/{tenant}/account")
//  	public String getAccountDetail(Model model, @PathVariable String tenant, @RequestHeader("tazzy-saml") String tazzySaml) {

	@GetMapping("/account")
	public String getAccountDetail(Model model) {
		// add loads of data to the model
//		populateModel(tenant, tazzySaml, model);
		return "account";
	}

    
}
