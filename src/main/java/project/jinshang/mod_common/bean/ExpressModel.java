package project.jinshang.mod_common.bean;

import io.swagger.annotations.ApiModelProperty;

public class ExpressModel {

    @ApiModelProperty(notes = "物充公司中文名称")
    private String name;

    @ApiModelProperty(notes = "物充公司英文代码")
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
