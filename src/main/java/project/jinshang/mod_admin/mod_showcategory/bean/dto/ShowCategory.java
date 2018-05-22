package project.jinshang.mod_admin.mod_showcategory.bean.dto;

/**
 * create : wyh
 * date : 2018/2/1
 */


public class ShowCategory {

    private  String name;

    private  Long id;

    private  String img;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
