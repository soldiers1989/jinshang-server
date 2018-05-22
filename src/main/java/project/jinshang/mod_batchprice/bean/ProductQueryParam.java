package project.jinshang.mod_batchprice.bean;

public class ProductQueryParam {

    private String productName;

    private Long level;

    private Long level1;

    private Long level2;

    private Long level3;

    private String stand;

    private String material;

    private String cardnum;

    private Long sellerId;

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getLevel1() {
        return level1;
    }

    public void setLevel1(Long level1) {
        this.level1 = level1;
    }

    public Long getLevel2() {
        return level2;
    }

    public void setLevel2(Long level2) {
        this.level2 = level2;
    }

    public Long getLevel3() {
        return level3;
    }

    public void setLevel3(Long level3) {
        this.level3 = level3;
    }

    public String getStand() {
        return stand;
    }

    public void setStand(String stand) {
        this.stand = stand;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getCardnum() {
        return cardnum;
    }

    public void setCardnum(String cardnum) {
        this.cardnum = cardnum;
    }

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }
}
