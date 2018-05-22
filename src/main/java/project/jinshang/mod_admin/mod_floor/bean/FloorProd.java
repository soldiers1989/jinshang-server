package project.jinshang.mod_admin.mod_floor.bean;

/**
 * create : wyh
 * date : 2018/2/7
 */
public class FloorProd {

        private  String img;

        private  String catename;

        private  Long cateid;

        private  String type;

        private  Object[] cateidpath;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getCatename() {
            return catename;
        }

        public void setCatename(String catename) {
            this.catename = catename;
        }

        public Long getCateid() {
            return cateid;
        }

        public void setCateid(Long cateid) {
            this.cateid = cateid;
        }

    public Object[] getCateidpath() {
        return cateidpath;
    }

    public void setCateidpath(Object[] cateidpath) {
        this.cateidpath = cateidpath;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
