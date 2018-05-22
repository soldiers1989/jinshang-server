package project.jinshang.mod_activity.bean;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class LimitTimeStoreView {
    private Long id;

    private Long limittimeid;

    private Long pdid;

    private String pdno;

    private BigDecimal originalprice;

    private BigDecimal limitprice;

    @ApiModelProperty(notes = "参加活动的库存数量")
    private BigDecimal storenum;

    private BigDecimal salesnum;


    @ApiModelProperty(notes = "仓库中的数量")
    private  BigDecimal pdstorenum;



    @ApiModelProperty(notes = "总数量")
    private  BigDecimal totalstorenum;



    List<Map<String,Object>> limitTimeProdAttrList;


    private  String attrStr;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLimittimeid() {
        return limittimeid;
    }

    public void setLimittimeid(Long limittimeid) {
        this.limittimeid = limittimeid;
    }

    public Long getPdid() {
        return pdid;
    }

    public void setPdid(Long pdid) {
        this.pdid = pdid;
    }

    public String getPdno() {
        return pdno;
    }

    public void setPdno(String pdno) {
        this.pdno = pdno == null ? null : pdno.trim();
    }

    public BigDecimal getOriginalprice() {
        return originalprice;
    }

    public void setOriginalprice(BigDecimal originalprice) {
        this.originalprice = originalprice;
    }

    public BigDecimal getLimitprice() {
        return limitprice;
    }

    public void setLimitprice(BigDecimal limitprice) {
        this.limitprice = limitprice;
    }

    public BigDecimal getStorenum() {
        return storenum;
    }

    public void setStorenum(BigDecimal storenum) {
        this.storenum = storenum;
    }

    public BigDecimal getSalesnum() {
        return salesnum;
    }

    public void setSalesnum(BigDecimal salesnum) {
        this.salesnum = salesnum;
    }

    public BigDecimal getPdstorenum() {
        return pdstorenum;
    }

    public void setPdstorenum(BigDecimal pdstorenum) {
        this.pdstorenum = pdstorenum;
    }

    public List<Map<String, Object>> getLimitTimeProdAttrList() {
        return limitTimeProdAttrList;
    }

    public void setLimitTimeProdAttrList(List<Map<String, Object>> limitTimeProdAttrList) {
        this.limitTimeProdAttrList = limitTimeProdAttrList;
    }

    public String getAttrStr() {
        return attrStr;
    }

    public void setAttrStr(String attrStr) {
        this.attrStr = attrStr;
    }


    public BigDecimal getTotalstorenum() {
        return totalstorenum;
    }

    public void setTotalstorenum(BigDecimal totalstorenum) {
        this.totalstorenum = totalstorenum;
    }
}