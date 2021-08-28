/**
 * 
 */
package com.rest.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.rest.util.Context;

/**
 * @author Pratik Dutta
 *
 */
@Component
public class MdcAndConextInitilizerFilter implements Filter{
	
	private Logger LOG;
	
	@Value("${filter.whitelisted.endpoints}")
	private String whitelistedEndpoints;
	
	@Autowired
	public MdcAndConextInitilizerFilter(Logger LOG) {
		this.LOG = LOG;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest=(HttpServletRequest) request;
		HttpServletResponse httpResponse=(HttpServletResponse)response;
		
		//Transaction Id
		
		String transactionId=httpRequest.getHeader("x-request-id");
		String serviceUrl=httpRequest.getRequestURL().toString();
		
		if(StringUtils.isEmpty(transactionId)) {
			transactionId=UUID.randomUUID().toString().replace("-", "");
		}
		
		List<String> whitelists=Arrays.asList((whitelistedEndpoints.split("\\|")));
		
		boolean isWhitelisted=whitelists.parallelStream().anyMatch(whitelist->StringUtils.contains(serviceUrl, whitelist));
		
		if(!isWhitelisted) {
			LOG.info(" MDC initialised with x-request-id : {} and Service URL: {}",transactionId,serviceUrl);
		}
		
		
		httpResponse.addHeader("x-request-id", transactionId);
		
		MDC.put("requestId", transactionId);
		MDC.put("serviceURL", serviceUrl);
		
		Context.transactionId(transactionId);
		
		try {
			chain.doFilter(request, response);
		} finally {
			MDC.clear();
			Context.purgeContext();
		}
	}
	

}
