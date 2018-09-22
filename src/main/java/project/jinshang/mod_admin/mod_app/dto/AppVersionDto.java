package project.jinshang.mod_admin.mod_app.dto;

import io.swagger.annotations.ApiModelProperty;
import project.jinshang.mod_admin.mod_app.bean.AppVersion;

public class AppVersionDto extends AppVersion{

    @ApiModelProperty(notes = "是否可以需要升级")
    private Boolean flag;

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }
}
