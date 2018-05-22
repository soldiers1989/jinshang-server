package project.jinshang.mod_product.bean;

/**
 * create : wyh
 * date : 2017/12/11
 */
public class Attribute {

    private  Long attributeid;

    private String attribute;

    private  String value;


    public Long getAttributeid() {
        return attributeid;
    }

    public void setAttributeid(Long attributeid) {
        this.attributeid = attributeid;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
