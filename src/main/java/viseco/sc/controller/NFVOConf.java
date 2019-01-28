package viseco.sc.controller;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Tran Quang Thanh
 *
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "nfvo")
public class NFVOConf {
	String projectid= "";
	String apiuri = "";
	String ip = "";
	String port = "";
	String serviceuri = "";
	String servicename = "";
	String servicekey = "";
	public String getProjectid() {
		return projectid;
	}
	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}
	public String getApiuri() {
		return apiuri;
	}
	public void setApiuri(String apiuri) {
		this.apiuri = apiuri;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getServiceuri() {
		return serviceuri;
	}
	public void setServiceuri(String serviceuri) {
		this.serviceuri = serviceuri;
	}
	public String getServicename() {
		return servicename;
	}
	public void setServicename(String servicename) {
		this.servicename = servicename;
	}
	public String getServicekey() {
		return servicekey;
	}
	public void setServicekey(String servicekey) {
		this.servicekey = servicekey;
	}
	
	
	
}
