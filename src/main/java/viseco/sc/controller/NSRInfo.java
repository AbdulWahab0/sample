package viseco.sc.controller;

import java.util.List;

/**
 * @author Tran Quang Thanh
 *
 */
public class NSRInfo {
	String id;
	String name;
	String descriptor_reference;
	String updatedAt;
	String vendor;
	String state;
	
	List<viseco.nfvo.VNFDInfo> vnfr;
	
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
	public String getDescriptor_reference() {
		return descriptor_reference;
	}
	public void setDescriptor_reference(String descriptor_reference) {
		this.descriptor_reference = descriptor_reference;
	}
	public String getUpdateAt() {
		return updatedAt;
	}
	public void setUpdateAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public List<viseco.nfvo.VNFDInfo> getVnfr() {
		return vnfr;
	}
	public void setVnfr(List<viseco.nfvo.VNFDInfo> vnfr) {
		this.vnfr = vnfr;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
}
