package project.jinshang.mod_admin.mod_floor.bean;

import io.swagger.annotations.ApiModelProperty;
import project.jinshang.mod_product.bean.Categories;

import java.util.List;
import java.util.Map;

public class FloorViewDto {
    private Long id;

    private String floorname;

    private Long level1id;

    private Long level2id;

    private String floorsubname;

    private String subjectimg;

    private String subjectimgurl;

    private Object categoryarray;

    private String floorproducts;

    private String floorproductsurl;

    private String rankingname;

    private Short rankingstop;

    private String rankingprodjson;

    private String floorcolor;

    private Short isshow;

    private Integer sort;


    @ApiModelProperty(notes = "楼层主题分类")
    private List<Map<String,Object>> floorCategories;


//    @ApiModelProperty(notes = "楼层商品列表")
//    private  List<Map<String,Object>> floorproductsList;


    @ApiModelProperty(notes = "排行榜列表")
    private List<Map<String,Object>> rankingprodList;



    @ApiModelProperty(notes = "楼层类别：紧固件、其它")
    private  String floortype;


    private  List<FloorProd> floorProds;


    public List<FloorProd> getFloorProds() {
        return floorProds;
    }

    public void setFloorProds(List<FloorProd> floorProds) {
        this.floorProds = floorProds;
    }

    public List<Map<String, Object>> getRankingprodList() {
        return rankingprodList;
    }

    public void setRankingprodList(List<Map<String, Object>> rankingprodList) {
        this.rankingprodList = rankingprodList;
    }

    public List<Map<String, Object>> getFloorCategories() {
        return floorCategories;
    }

    public void setFloorCategories(List<Map<String, Object>> floorCategories) {
        this.floorCategories = floorCategories;
    }


//    public List<Map<String, Object>> getFloorproductsList() {
//        return floorproductsList;
//    }
//
//    public void setFloorproductsList(List<Map<String, Object>> floorproductsList) {
//        this.floorproductsList = floorproductsList;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFloorname() {
        return floorname;
    }

    public void setFloorname(String floorname) {
        this.floorname = floorname == null ? null : floorname.trim();
    }

    public Long getLevel1id() {
        return level1id;
    }

    public void setLevel1id(Long level1id) {
        this.level1id = level1id;
    }

    public Long getLevel2id() {
        return level2id;
    }

    public void setLevel2id(Long level2id) {
        this.level2id = level2id;
    }

    public String getFloorsubname() {
        return floorsubname;
    }

    public void setFloorsubname(String floorsubname) {
        this.floorsubname = floorsubname == null ? null : floorsubname.trim();
    }

    public String getSubjectimg() {
        return subjectimg;
    }

    public void setSubjectimg(String subjectimg) {
        this.subjectimg = subjectimg == null ? null : subjectimg.trim();
    }

    public String getSubjectimgurl() {
        return subjectimgurl;
    }

    public void setSubjectimgurl(String subjectimgurl) {
        this.subjectimgurl = subjectimgurl == null ? null : subjectimgurl.trim();
    }

    public Object getCategoryarray() {
        return categoryarray;
    }

    public void setCategoryarray(Object categoryarray) {
        this.categoryarray = categoryarray;
    }

    public String getFloorproducts() {
        return floorproducts;
    }

    public void setFloorproducts(String floorproducts) {
        this.floorproducts = floorproducts == null ? null : floorproducts.trim();
    }

    public String getFloorproductsurl() {
        return floorproductsurl;
    }

    public void setFloorproductsurl(String floorproductsurl) {
        this.floorproductsurl = floorproductsurl == null ? null : floorproductsurl.trim();
    }

    public String getRankingname() {
        return rankingname;
    }

    public void setRankingname(String rankingname) {
        this.rankingname = rankingname == null ? null : rankingname.trim();
    }

    public Short getRankingstop() {
        return rankingstop;
    }

    public void setRankingstop(Short rankingstop) {
        this.rankingstop = rankingstop;
    }

    public String getRankingprodjson() {
        return rankingprodjson;
    }

    public void setRankingprodjson(String rankingprodjson) {
        this.rankingprodjson = rankingprodjson == null ? null : rankingprodjson.trim();
    }

    public String getFloorcolor() {
        return floorcolor;
    }

    public void setFloorcolor(String floorcolor) {
        this.floorcolor = floorcolor == null ? null : floorcolor.trim();
    }

    public Short getIsshow() {
        return isshow;
    }

    public void setIsshow(Short isshow) {
        this.isshow = isshow;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getFloortype() {
        return floortype;
    }

    public void setFloortype(String floortype) {
        this.floortype = floortype;
    }
}