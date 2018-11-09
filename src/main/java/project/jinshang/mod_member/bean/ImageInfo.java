package project.jinshang.mod_member.bean;

import io.swagger.annotations.ApiModelProperty;

public class ImageInfo {

    @ApiModelProperty(notes = "oss返回的图片地址")
    private String ossurl;

    public String getOssurl() {
        return ossurl;
    }

    public void setOssurl(String ossurl) {
        this.ossurl = ossurl;
    }
}
