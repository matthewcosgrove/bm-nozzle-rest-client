package com.mc;

import feign.RequestLine;

public interface BmNozzleRestAPIClient {

	@RequestLine("GET /reps")
	public String getReps();
	@RequestLine("GET /gorouters")
	public String getGoRouters();
	@RequestLine("GET /traffic_controllers")
	public String getTrafficControllers();
	@RequestLine("GET /cloud_controllers")
	public String getCloudControllers();
	@RequestLine("GET /doppler_servers")
	public String getDopplerServers();
	
	@RequestLine("GET /metron_agents")
	public String getMetron();
	@RequestLine("GET /syslog_drains")
	public String getSysLogDrains();
	@RequestLine("GET /tps_watchers")
	public String getTpsWatchers();
	@RequestLine("GET /tps_listeners")
	public String getTpsListeners();
	@RequestLine("GET /stagers")
	public String getStagers();
	@RequestLine("GET /ssh_proxies")
	public String getSshProxies();
	@RequestLine("GET /senders")
	public String getSenders();
	@RequestLine("GET /route_emitters")
	public String getRouteEmitters();
	@RequestLine("GET /receptors")
	public String getReceptors();
	
}
