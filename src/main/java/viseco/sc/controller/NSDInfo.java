package viseco.sc.controller;


import java.util.List;

/**
 * @author Tran Quang Thanh
 *
 */
public class NSDInfo {
	String id;
	String name;
	String vendor;
	String updateAt;
	String version;
	String vnf_dependency; //list
	List<viseco.nfvo.VNFDInfo> vnfd;
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
	public String getUpdateAt() {
		return updateAt;
	}
	public void setUpdateAt(String updateAt) {
		this.updateAt = updateAt;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getVnf_dependency() {
		return vnf_dependency;
	}
	public void setVnf_dependency(String vnf_dependency) {
		this.vnf_dependency = vnf_dependency;
	}
	public List<viseco.nfvo.VNFDInfo> getVnfd() {
		return vnfd;
	}
	public void setVnfd(List<viseco.nfvo.VNFDInfo> vnfd) {
		this.vnfd = vnfd;
	}
	
	
}
