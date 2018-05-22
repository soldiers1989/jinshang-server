package project.jinshang.mod_product.bean;

public class OrderProductLog {
    private Long id;

    private Long orderproductid;

    private Object productinfojson;

    private Object productstorejson;

    private Object productattrjson;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderproductid() {
        return orderproductid;
    }

    public void setOrderproductid(Long orderproductid) {
        this.orderproductid = orderproductid;
    }

    public Object getProductinfojson() {
        return productinfojson;
    }

    public void setProductinfojson(Object productinfojson) {
        this.productinfojson = productinfojson;
    }

    public Object getProductstorejson() {
        return productstorejson;
    }

    public void setProductstorejson(Object productstorejson) {
        this.productstorejson = productstorejson;
    }

    public Object getProductattrjson() {
        return productattrjson;
    }

    public void setProductattrjson(Object productattrjson) {
        this.productattrjson = productattrjson;
    }
}