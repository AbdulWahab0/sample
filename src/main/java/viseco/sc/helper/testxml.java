package viseco.sc.helper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Descriptive")
public class testxml {
    @XmlElement(name = "des")
    protected String des;

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
