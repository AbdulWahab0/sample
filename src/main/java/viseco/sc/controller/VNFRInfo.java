package viseco.sc.controller;

/**
 * @author Tran Quang Thanh
 *
 */
public class VNFRInfo {

		String id;
		String name;
		//String type;
		//String vendor;
		String version;
		String state;
		String vnf_address; //list
		
		// new
		int num_instance;

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

		public String getVersion() {
			return version;
		}

		public void setVersion(String version) {
			this.version = version;
		}

		

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public String getVnf_address() {
			return vnf_address;
		}

		public void setVnf_address(String vnf_address) {
			this.vnf_address = vnf_address;
		}

		public int getNum_instance() {
			return num_instance;
		}

		public void setNum_instance(int num_instance) {
			this.num_instance = num_instance;
		}
		
		
		
}
