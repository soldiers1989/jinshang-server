package project.jinshang.mod_admin.mod_article.bean;


import java.util.List;

public class ArticleListCategory {

    private  long id;
    private  String docname;
    private  int docorder;
    private  long  praentid;
    private short docislist;

    public short getDocislist() {
        return docislist;
    }

    public void setDocislist(short docislist) {
        this.docislist = docislist;
    }

    private List<ArticleListCategory> list;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDocname() {
        return docname;
    }

    public void setDocname(String docname) {
        this.docname = docname;
    }

    public int getDocorder() {
        return docorder;
    }

    public void setDocorder(int docorder) {
        this.docorder = docorder;
    }

    public long getPraentid() {
        return praentid;
    }

    public void setPraentid(long praentid) {
        this.praentid = praentid;
    }

    public List<ArticleListCategory> getList() {
        return list;
    }

    public void setList(List<ArticleListCategory> list) {
        this.list = list;
    }
}
