package project.jinshang.mod_front.bean;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * create : wyh
 * date : 2018/2/1
 */
public class ShowCateFastenerDetail implements Serializable{

    @ApiModelProperty(notes = "材质")
    private  String material;


    @ApiModelProperty(notes = "材质图片")
    private  String materialImg;


    @ApiModelProperty(notes = "该材质下的所有2级分类")
    private List<ShowCateFastenerLevel2> showCateFastenerStandList;


    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getMaterialImg() {
        return materialImg;
    }

    public void setMaterialImg(String materialImg) {
        this.materialImg = materialImg;
    }

    public List<ShowCateFastenerLevel2> getShowCateFastenerStandList() {
        return showCateFastenerStandList;
    }

    public void setShowCateFastenerStandList(List<ShowCateFastenerLevel2> showCateFastenerStandList) {
        this.showCateFastenerStandList = showCateFastenerStandList;
    }
}
