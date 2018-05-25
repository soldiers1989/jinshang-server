package project.jinshang.mod_fx.bean;

import java.math.BigDecimal;

/**
 * null
 * 
 * @author zzy
 * 
 * @date 2018-05-05
 */
public class Fxcategories {
    /**
     * ID自增
     */
    private Long id;

    /**
     * 分类ID
     */
    private Long cid;

    /**
     * 佣金比例
     */
    private BigDecimal ratio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public BigDecimal getRatio() {
        return ratio;
    }

    public void setRatio(BigDecimal ratio) {
        this.ratio = ratio;
    }
}