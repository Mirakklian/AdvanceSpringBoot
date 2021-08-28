package com.rest.healthcheck;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health.Builder;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.rest.constant.Constant;

@Component
public class AppVersionInfo extends AbstractHealthIndicator {
	
	private final Environment env;
	
	@Autowired
	public AppVersionInfo(Environment env) {
		this.env=env;
	}

	@Override
	protected void doHealthCheck(Builder builder) throws Exception {
		
		builder.up()
			.withDetails(getAppVersionInfo());
		
	}

	private Map<String, String> getAppVersionInfo() {
		
		Map<String,String> appInfo=null;
		
		appInfo=new LinkedHashMap<>();
		
		appInfo.put(Constant.APP_NMAE, "My Test App");
		appInfo.put(Constant.ACTIVE_PROFILE, Arrays.toString(env.getActiveProfiles()));
		
		return appInfo;
	}
	
	

}
