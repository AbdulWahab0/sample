package viseco.sc.controller;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Future;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;

import org.openbaton.catalogue.mano.descriptor.NetworkServiceDescriptor;
import org.openbaton.catalogue.mano.descriptor.PhysicalNetworkFunctionDescriptor;
import org.openbaton.catalogue.mano.descriptor.VirtualNetworkFunctionDescriptor;
import org.openbaton.catalogue.mano.record.NetworkServiceRecord;
import org.openbaton.catalogue.mano.record.Status;
import org.openbaton.catalogue.nfvo.Action;
import org.openbaton.catalogue.nfvo.EndpointType;
import org.openbaton.catalogue.nfvo.EventEndpoint;
import org.openbaton.sdk.NFVORequestor;
import org.openbaton.sdk.NfvoRequestorBuilder;
import org.openbaton.sdk.api.exception.SDKException;
import org.openbaton.sdk.api.rest.EventAgent;
import org.openbaton.sdk.api.rest.NetworkServiceDescriptorAgent;
import org.openbaton.sdk.api.rest.NetworkServiceRecordAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import viseco.nfvo.VNFDInfo;

/**
 * @author Tran Quang Thanh
 *
 */
@Component
@org.springframework.web.bind.annotation.RestController
public class NFVOController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(NFVOController.class);
	
	@Autowired
	NFVOConf nfvo;
	
	
	private NFVORequestor requestor = null;
	private CloseableHttpAsyncClient client = Util.trustedHTTPClient();
	
	private List<NSDInfo> lnsd_info = new ArrayList<NSDInfo>();
	
	// current nsr
	private NSRInfo cur_nsr_info = new NSRInfo();
	private String cur_nsr_id = "";
	private String state = "NULL";

	private boolean runOnce=true;
	private String manEndpoint="http://localhost:7750";
	

	
	//receive notification
	@RequestMapping(value = "/event/**", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> eventProcessing(@RequestBody String json) {
		return new ResponseEntity<String>("OK",HttpStatus.OK);	
	}
	
	
	// deploy nsd
	@RequestMapping(value = "/nfvo/deploy/{nsd}", method = RequestMethod.GET)
	public ResponseEntity<String> deploy(@PathVariable("nsd")  String nsd) {
		this.state = "NULL";
		LOGGER.info("Received NSD <" + nsd + ">");
		if (this.deployNSD(nsd))
			return new ResponseEntity<String>("OK",HttpStatus.OK);
		else
			return new ResponseEntity<String>("SERVICE UNAVAILABLE!!!",HttpStatus.SERVICE_UNAVAILABLE);
	}		
	
	public boolean deployNSD(String nsd_id) {
	    try {
	    	if (requestor == null) 
	    		requestor = NfvoRequestorBuilder.create()
			 		.serviceName("nfvo")
		            .nfvoIp(nfvo.getIp())
		            .nfvoPort(Integer.parseInt(nfvo.getPort()))
		            .projectId(nfvo.getProjectid())
		            .sslEnabled(false)
		            .version("1")
		            .serviceKey(nfvo.getServicekey())
		            .build();
	    	
	    		NetworkServiceRecord nsr = requestor.getNetworkServiceRecordAgent().create(nsd_id, null, null, null);
	    		this.cur_nsr_id = nsr.getId();
	    		
                LOGGER.info("NSR: " + this.cur_nsr_id);
                //if (nsr.getStatus()==Status.ACTIVE)
                LOGGER.info("STATUS: " + nsr.getStatus());
            
		} catch (Exception e) {
			LOGGER.warn(e.toString());
			return false;
		}	
		
		return true;	
	}	
	
	// get NSDs
	@RequestMapping(value = "/nfvo/nsd", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE) //
	public List<NSDInfo> getNSD() {
		List<VNFDInfo> lvnfd_info = new ArrayList<VNFDInfo>();
		
	    try {
	    	if (requestor == null) 
	    		requestor = NfvoRequestorBuilder.create()
			 		.serviceName("nfvo")
		            .nfvoIp(nfvo.getIp())
		            .nfvoPort(Integer.parseInt(nfvo.getPort()))
		            .projectId(nfvo.getProjectid())
		            .sslEnabled(false)
		            .version("1")
		            .serviceKey(nfvo.getServicekey())
		            .build();
	    	
	    		NetworkServiceDescriptorAgent nsda = requestor.getNetworkServiceDescriptorAgent();


			List<NetworkServiceDescriptor> networkServiceDescriptor= nsda.findAll();
	    		//PhysicalNetworkFunctionDescriptor physicalNetworkFunctionDescriptor =nsda.getPhysicalNetworkFunctionDescriptors()

	    		//List<NetworkServiceDescriptor> networkServiceDescriptors= (List<NetworkServiceDescriptor>) new NetworkServiceDescriptor();


			System.out.println(nsda);


	    		/*for (int i=0;i<lnsd.size();i++) {
	    			
	    			LOGGER.info("NSD: " + lnsd.get(i).getName());
	    			
	    			
	    			NetworkServiceDescriptor rnsd = lnsd.get(i);
					NSDInfo rnsd_info = new NSDInfo();
	    			rnsd_info.setId(rnsd.getId());
	    			rnsd_info.setName(rnsd.getName());
	    			rnsd_info.setUpdateAt(rnsd.getUpdatedAt());
	    			rnsd_info.setVendor(rnsd.getVendor());
	    			rnsd_info.setVnf_dependency(rnsd.getVnf_dependency().toString());
	    			
	    			Set<VirtualNetworkFunctionDescriptor> rlvnfd = rnsd.getVnfd();
	    			
	    			lvnfd_info.clear();
	    			for (Iterator<VirtualNetworkFunctionDescriptor> iterator = rlvnfd.iterator(); iterator.hasNext();) {
	    				VirtualNetworkFunctionDescriptor s =  iterator.next();
	    				LOGGER.info("VNF: " + s.getName());
						viseco.nfvo.VNFDInfo a = new viseco.nfvo.VNFDInfo();
	    				a.setId(s.getId());
	    				a.setName(s.getName());
	    				a.setLifecycle_event(s.getLifecycle_event().toString());
	    				a.setVendor(s.getVendor());
	    				a.setVersion(s.getVersion());
	    				lvnfd_info.add(a);
	    			}
	    			rnsd_info.setVnfd(lvnfd_info);
	    			
	    			this.lnsd_info.add(rnsd_info);
	    		}*/
            
		} catch (Exception e) {
			LOGGER.warn(e.toString());

		}
		
		return this.lnsd_info;	
	}	
	
	
	@RequestMapping(value = "/nfvo/nsr/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE) //
	public NetworkServiceRecord getNSR(@PathVariable("id")  String id) {
		NetworkServiceRecord nsr = null;
	    try {
	    	if (requestor == null) 
	    		requestor = NfvoRequestorBuilder.create()
			 		.serviceName("nfvo")
		            .nfvoIp(nfvo.getIp())
		            .nfvoPort(Integer.parseInt(nfvo.getPort()))
		            .projectId(nfvo.getProjectid())
		            .sslEnabled(false)
		            .version("1")
		            .serviceKey(nfvo.getServicekey())
		            .build();
	    	
	    		NetworkServiceRecordAgent nsra = requestor.getNetworkServiceRecordAgent();
	    		nsr = nsra.findById(id);
    			LOGGER.info("NSR: " + nsr.getName());
    			
    			//TODO: update current data
    			this.cur_nsr_info.setState(nsr.getStatus().toString());
    			this.cur_nsr_info.setUpdateAt(nsr.getUpdatedAt());
            
		} catch (Exception e) {
			LOGGER.warn(e.toString());
		}	
		
		return nsr;	
	}	
	
	public Status getStatus(String nsr_id){
		  NetworkServiceRecord networkServiceRecord = null;	
		  Status s = Status.NULL;
		  LOGGER.debug("Check Status of NSR with id: " + nsr_id);
		  try {
			  if (requestor == null) 
		    		requestor = NfvoRequestorBuilder.create()
				 		.serviceName("nfvo")
			            .nfvoIp(nfvo.getIp())
			            .nfvoPort(Integer.parseInt(nfvo.getPort()))
			            .projectId(nfvo.getProjectid())
			            .sslEnabled(false)
			            .version("1")
			            .serviceKey(nfvo.getServicekey())
			            .build();
			  networkServiceRecord = requestor.getNetworkServiceRecordAgent().findById(nsr_id);
		 
			  if (networkServiceRecord == null || networkServiceRecord.getStatus() == null) {
			    return Status.NULL;
			  }
			  s = networkServiceRecord.getStatus();
		  } catch (SDKException e) {
			    LOGGER.warn(e.getMessage(), e);
			    return Status.NULL;
		  }
		  return s;
	}
	
	void registerNFVO(NFVOConf nfvo){
		try {
		 
			if (requestor == null) 
				requestor = NfvoRequestorBuilder.create()
				 		.serviceName(nfvo.getServicename())
			            .nfvoIp(nfvo.getIp())
			            .nfvoPort(Integer.parseInt(nfvo.getPort()))
			            .projectId(nfvo.getProjectid())
			            .sslEnabled(false)
			            .version("1")
			            .serviceKey(nfvo.getServicekey())
			            .build();
		 
		  EventAgent eventAgent = requestor.getEventAgent();
		  
		  EventEndpoint evStart = new EventEndpoint();
		  evStart.setName("StartEvent");
		  evStart.setEvent(Action.START);
		  evStart.setType(EndpointType.REST);
		  evStart.setEndpoint(this.nfvo.getServiceuri() + "/nfvo/event/START");
		  
		  EventEndpoint evNSRelease = new EventEndpoint();
		  evNSRelease.setName("NSReleaseEvent");
		  evNSRelease.setEvent(Action.RELEASE_RESOURCES_FINISH);
		  evNSRelease.setType(EndpointType.REST);
		  evNSRelease.setEndpoint(this.nfvo.getServiceuri() + "/nfvo/event/RELEASE_RESOURCES_FINISH");
		  
		  EventEndpoint evNSFinish = new EventEndpoint();
	      evNSFinish.setName("NSFinishedEvent");
		  evNSFinish.setEvent(Action.INSTANTIATE_FINISH);
	      evNSFinish.setType(EndpointType.REST);
	      evNSFinish.setEndpoint(this.nfvo.getServiceuri() + "/nfvo/event/INSTANTIATE_FINISH");

	      
	      EventEndpoint evScaleOut = new EventEndpoint();
	      evScaleOut.setName("ScaleOutEvent");
	      evScaleOut.setEvent(Action.SCALE_OUT);
	      evScaleOut.setType(EndpointType.REST);
	      evScaleOut.setEndpoint(this.nfvo.getServiceuri() + "/nfvo/event/SCALE_OUT");
		  
		  eventAgent.create(evStart);
		  eventAgent.create(evNSRelease);
		  eventAgent.create(evScaleOut);
	      //eventAgent.create(evScaleIn);
	      eventAgent.create(evNSFinish);
		  
		      
		} catch (Exception e)   { 
			LOGGER.warn("Can not register !!!");
		}
	}
	
	/*void subscribeAction(String target, String metric) {
		String data = "{\"entities\":[{\"type\":\"" + metric + "\",\"isPattern\":\"true\",\"id\":\".*\"}],\"attributes\":[\"count\"],"
			+ "\"reference\":\"" + target + "\",\"duration\": \"P1M\",\"notifyConditions\":[{\"type\": \"ONCHANGE\",\"condValues\": [\"count\"]}],\"throttling\": \"PT5S\"}";

       	LOGGER.info(data);
    	String href = "/v1/subscribeContext";
    	HttpPost http = new HttpPost(href);
    	HttpResponse response = null;
    	http.addHeader("Content-Type", "application/json");
    	http.addHeader("Accept", "application/json");
    	StringEntity params;
		try {
			params = new StringEntity(data);
			http.setEntity(params);
    		client.start();
    		Future<HttpResponse> future = client.execute(http, null);
    		response = future.get();
    		int code = response.getStatusLine().getStatusCode();
    		if (code == 200) {
    			LOGGER.info("Subscribe: Success");
    		}
    	} catch (Exception e) {  
    		LOGGER.info(e.toString());
    	}

	}*/
	
	String toNGSI(int stype, String data, String src) {
    	String str="";
    	long millis = System.currentTimeMillis();
   		str = "{\"contextElements\":[{\"type\":\"" + stype + "\",\"isPattern\":false,\"id\":\"" + src + "\",\"attributes\":[{\"name\":\"endpoint\",\"type\":\"string\",\"value\":\"" + data + "\"},{\"name\":\"time\",\"type\":\"integer\",\"value\":\"" + millis + "\"}]}],\"updateAction\":\"APPEND\"}";
    	return str;
    }
	
	
	/*@RequestMapping(value = "/nfvo/event/INSTANTIATE_FINISH", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	  public void nsr_finish(@RequestBody String msg){
		//LOGGER.info(msg);
		JsonParser jsonParser = new JsonParser();
		JsonObject json = jsonParser.parse(msg).getAsJsonObject();
		Gson mapper = new GsonBuilder().create();
		NetworkServiceRecord nsr = mapper.fromJson(json.get("payload"), NetworkServiceRecord.class);
		LOGGER.info("[NSR_ID]= " + nsr.getId());
		
		//if (this.cur_nsr_id.equals(nsr.getId())) return;
		
		this.cur_nsr_id = nsr.getId();
		this.cur_nsr_info.setId(nsr.getId());
		this.cur_nsr_info.setName(nsr.getName());
		this.cur_nsr_info.setState(nsr.getStatus().toString());
		this.cur_nsr_info.setUpdateAt(nsr.getUpdatedAt());
		
		JsonArray vnfrs = json.get("payload").getAsJsonObject().get("vnfr").getAsJsonArray();
		List<viseco.nfvo.VNFRInfo> lvnfr_info = new ArrayList<viseco.nfvo.VNFRInfo>();
		  for (int i = 0; i < vnfrs.size(); i++) {
			  viseco.nfvo.VNFRInfo a = new viseco.nfvo.VNFRInfo();
			  JsonObject b = vnfrs.get(i).getAsJsonObject();
			  a.setId(b.get("id").getAsString());
			  a.setName(b.get("name").getAsString());
			  a.setVnf_address(b.get("vnf_address").getAsString());
			  
			  JsonArray vdus = b.get("vdu").getAsJsonArray();
			  int instances = 0;
				  for (int j = 0; j < vdus.size(); j++) {
					  JsonArray vnfcs = vdus.get(j).getAsJsonObject().get("vnfc_instance").getAsJsonArray();
					  instances += vnfcs.size();

				  }
			  a.setNum_instance(instances);	   
			  lvnfr_info.add(a);
		  }
		  
		  LOGGER.info("Number of VNFRs: " + vnfrs.size());
		  Gson gson = new Gson();
		  //LOGGER.info(gson.toJson(lvnfr_info));
		  this.cur_nsr_info.setVnfr(lvnfr_info);
		  LOGGER.info(gson.toJson(cur_nsr_info));
		  this.sendNotification("INSTANTIATE_FINISH", "" + vnfrs.size());
		  this.sendNotification("NOTIFY", "Deployment:Success");

	  }*/
	
	/*@RequestMapping(value = "/nfvo/event/SCALE_OUT", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	  public void scale_out(@RequestBody String msg){
		  LOGGER.info("[SCALE OUT]");
			JsonParser jsonParser = new JsonParser();
			JsonObject json = jsonParser.parse(msg).getAsJsonObject();
			//Gson mapper = new GsonBuilder().create();
			//NetworkServiceRecord nsr = mapper.fromJson(json.get("payload"), NetworkServiceRecord.class);

			JsonArray vnfrs = json.get("payload").getAsJsonObject().get("vnfr").getAsJsonArray();
			List<VNFRInfo> lvnfr_info = cur_nsr_info.getVnfr();
			
			  for (int i = 0; i < vnfrs.size(); i++) {
				  JsonObject b = vnfrs.get(i).getAsJsonObject();
				  String id = b.get("id").getAsString();
				  int instances = 0;
				  JsonArray vdus = b.get("vdu").getAsJsonArray();
					// calculate instances  
				  	for (int j = 0; j < vdus.size(); j++) {
						  JsonArray vnfcs = vdus.get(j).getAsJsonObject().get("vnfc_instance").getAsJsonArray();
						  instances += vnfcs.size();
					  }
					  
					  
					  for (int k=0;k<lvnfr_info.size();k++) {
			    		if (lvnfr_info.get(k).getId().equals(id) && (lvnfr_info.get(k).getNum_instance() != instances)) {
			    			LOGGER.info("Scaling ... " + lvnfr_info.get(k).getName());
			    			this.sendNotification("SCALE_OUT", "Scaling ... " + lvnfr_info.get(k).getName());
			    			lvnfr_info.get(k).setNum_instance(instances);
			    		}
					  }	  
			  }
			  this.cur_nsr_info.setVnfr(lvnfr_info);
			  
			  Gson gson = new Gson();
			  LOGGER.info(gson.toJson(cur_nsr_info.getVnfr()));
	  }	
	*/
	
	/*@RequestMapping(value = "/nfvo/event/START", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void start(@RequestBody String msg){
		  JsonParser jsonParser = new JsonParser();
		  JsonObject json = jsonParser.parse(msg).getAsJsonObject();
		  JsonObject tmp = json.get("payload").getAsJsonObject();
		  JsonArray ips = tmp.get("vnf_address").getAsJsonArray();
		  for (int k = 0; k < ips.size(); k++) {
			  	LOGGER.info(tmp.get("name").getAsString() + ": " +  ips.get(k).getAsString());
			  	//this.sendNotification("START", tmp.get("name").getAsString() + ":" +  ips.get(k).getAsString());
			  	this.sendNotification("NOTIFY", tmp.get("name").getAsString() + ":" +  ips.get(k).getAsString());
		  }	
	  }*/
	
	@RequestMapping(value = "/nfvo/event/RELEASE_RESOURCES_FINISH", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	  public void nsr_release(@RequestBody String msg){
			this.cur_nsr_id = "";
			this.cur_nsr_info.setId("");
			try {
				EventAgent eventAgent = requestor.getEventAgent();
				List<EventEndpoint> ev = eventAgent.findAll();
				for (int i=0;i<ev.size();i++) 
					eventAgent.delete(ev.get(i).getId());
			} catch (SDKException e) {
				LOGGER.warn(e.toString());
			}
			//this.runOnce = true;
	  }
	
	
	 @Scheduled(fixedRate = 60000)
	 public void printCurrentNSR() {
		 if (!(this.cur_nsr_id.equals(""))) {
			 LOGGER.info("NSR:" + this.cur_nsr_id);
		 }
	 }
	 
    @Scheduled(fixedRate = 5000)
    public void statusReport() throws SDKException {
    	if (this.runOnce) {
    		registerNFVO(this.nfvo);
    		//this.deployNSD("a26f93f1-f512-4e17-b380-130bd2c786d9");
    		this.runOnce = false;
    	}

    	//LOGGER.info("Number of Application(s): " + this.lnsd_info.size());
    	

    }
    
    void sendNotification(String event, String data) {
        RestTemplate restTemplate = new RestTemplate();
        try {
        	ResponseEntity<String> response = restTemplate.getForEntity(manEndpoint + "/notification/" + event + "/" + data, String.class);
        	LOGGER.info(response.getBody());
        } catch (Exception ex) {
	    	   LOGGER.warn("Can not send data to " + manEndpoint);
	    }       	
    }
    


}
