package project.jinshang.mod_member.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * null
 * 
 * @author zzy
 * 
 * @date 2018-07-03
 */
@ApiModel(description = "注册来源信息表")
public class Registersource {

    @ApiModelProperty(notes = "ID自增")
    private Long id;

    @ApiModelProperty(notes = "标识")
    private String label;

    @ApiModelProperty(notes = "标识名称")
    private String labelname;

    @ApiModelProperty(notes = "类型：1.注册来源 2.注册类型 3.注册渠道")
    private Short type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label == null ? null : label.trim();
    }

    public String getLabelname() {
        return labelname;
    }

    public void setLabelname(String labelname) {
        this.labelname = labelname == null ? null : labelname.trim();
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }
}