package project.jinshang.mod_product.bean.dto;

import io.swagger.annotations.ApiModelProperty;
import project.jinshang.mod_product.bean.LogisticsInfo;

public class LogisticsInfoDto extends LogisticsInfo {
    @ApiModelProperty(notes = "物流url")
    private String expressurl;

    public String getExpressurl() {
        return expressurl;
    }

    public void setExpressurl(String expressurl) {
        this.expressurl = expressurl;
    }

}
