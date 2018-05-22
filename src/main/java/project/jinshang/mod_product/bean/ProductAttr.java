package project.jinshang.mod_product.bean;

public class  ProductAttr {
    private Long id;

    private Long productid;

    private Long attributeid;

    private String attribute;

    private String value;

    private String pdno;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductid() {
        return productid;
    }

    public void setProductid(Long productid) {
        this.productid = productid;
    }

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
        this.attribute = attribute == null ? null : attribute.trim();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    public String getPdno() {
        return pdno;
    }

    public void setPdno(String pdno) {
        this.pdno = pdno;
    }
}