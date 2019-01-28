package viseco.nfvo;

/**
 * @author Tran Quang Thanh
 *
 */
public class VNFDInfo {
	String id;
	String name;
	String vendor;
	String version;
	String lifecycle_event; //list
	//String configurations;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getLifecycle_event() {
		return lifecycle_event;
	}
	public void setLifecycle_event(String lifecycle_event) {
		this.lifecycle_event = lifecycle_event;
	}
	
	
	
}
