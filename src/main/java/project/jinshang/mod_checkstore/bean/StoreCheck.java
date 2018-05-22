package project.jinshang.mod_checkstore.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2018/4/21.
 */
@ApiModel(description = "盘库搜索条件")
public class StoreCheck {
    @ApiModelProperty(notes = "pageNo")
    private int pageNo = 1;
    @ApiModelProperty(notes = "pageSize")
    private int pageSize = 20;
    @ApiModelProperty(notes = "searchKey")
    private String searchKey = "";
    @ApiModelProperty(notes = "level1")
    private String level1;
    @ApiModelProperty(notes = "level2")
    private String level2;
    @ApiModelProperty(notes = "level3")
    private String level3;
    @ApiModelProperty(notes = "productname")
    private String productname;
    @ApiModelProperty(notes = "brand")
    private String brand;
    @ApiModelProperty(notes = "cardnum")
    private String cardnum;
    @ApiModelProperty(notes = "material")
    private String material;
    @ApiModelProperty(notes = "searchJson")
    private String searchJson;
    @ApiModelProperty(notes = "surfacetreatment")
    private String surfacetreatment;


    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public String getLevel1() {
        return level1;
    }

    public void setLevel1(String level1) {
        this.level1 = level1;
    }

    public String getLevel2() {
        return level2;
    }

    public void setLevel2(String level2) {
        this.level2 = level2;
    }

    public String getLevel3() {
        return level3;
    }

    public void setLevel3(String level3) {
        this.level3 = level3;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCardnum() {
        return cardnum;
    }

    public void setCardnum(String cardnum) {
        this.cardnum = cardnum;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getSearchJson() {
        return searchJson;
    }

    public void setSearchJson(String searchJson) {
        this.searchJson = searchJson;
    }

    public String getSurfacetreatment() {
        return surfacetreatment;
    }

    public void setSurfacetreatment(String surfacetreatment) {
        this.surfacetreatment = surfacetreatment;
    }

}
