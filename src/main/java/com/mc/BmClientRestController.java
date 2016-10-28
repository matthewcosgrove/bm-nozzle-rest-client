package com.mc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BmClientRestController {
	
	@Autowired
	private BmNozzleRestAPIClient apiClient;

	@RequestMapping(value="/reps", method=RequestMethod.GET)
	@ResponseBody
	public String getReps(){
		return apiClient.getReps();
	}
	@RequestMapping(value="/cc", method=RequestMethod.GET)
	@ResponseBody
	public String getCCs(){
		return apiClient.getCloudControllers();
	}
	@RequestMapping(value="/go", method=RequestMethod.GET)
	@ResponseBody
	public String getGoRouters(){
		return apiClient.getGoRouters();
	}
	@RequestMapping(value="/tc", method=RequestMethod.GET)
	@ResponseBody
	public String getTrafficControllers(){
		return apiClient.getTrafficControllers();
	}
	@RequestMapping(value="/doppler", method=RequestMethod.GET)
	@ResponseBody
	public String getDoppler(){
		return apiClient.getDopplerServers();
	}

	@RequestMapping(value="/metron", method=RequestMethod.GET)
	@ResponseBody
	public String getMetron(){
		return apiClient.getMetron();
	}
	
	@RequestMapping(value="/misc", method=RequestMethod.GET)
	@ResponseBody
	public String getMisc(){
		StringBuilder sb = new StringBuilder();
		processResponse(sb, apiClient.getReceptors(), "receptors");
		processResponse(sb, apiClient.getRouteEmitters(), "route_emitters");
		processResponse(sb, apiClient.getSenders(), "senders");
		processResponse(sb, apiClient.getSshProxies(), "ssh_proxies");
		processResponse(sb, apiClient.getStagers(), "stagers");
		processResponse(sb, apiClient.getSysLogDrains(), "syslog_drains");
		processResponse(sb, apiClient.getTpsListeners(), "tps_listeners");
		processResponse(sb, apiClient.getTpsWatchers(), "tps_watchers");
		return sb.toString();
	}
	
	private void processResponse(StringBuilder sb, String responseString, String name) {
		sb.append(name);
		sb.append(System.lineSeparator());
		sb.append(responseString);
		sb.append(System.lineSeparator());
	}
}
