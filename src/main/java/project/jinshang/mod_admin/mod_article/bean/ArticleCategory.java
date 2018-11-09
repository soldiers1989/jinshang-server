package project.jinshang.mod_admin.mod_article.bean;

import java.util.List;

public class ArticleCategory{
    private Long id;

    private Long praentid;

    private String docname;

    private Integer docorder;

    private Short docislist;

    private List<ArticleCategory> childrenArticleCategory;

    private List<Article> articleList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPraentid() {
        return praentid;
    }

    public void setPraentid(Long praentid) {
        this.praentid = praentid;
    }

    public String getDocname() {
        return docname;
    }

    public void setDocname(String docname) {
        this.docname = docname == null ? null : docname.trim();
    }

    public Integer getDocorder() {
        return docorder;
    }

    public void setDocorder(Integer docorder) {
        this.docorder = docorder;
    }

    public Short getDocislist() {
        return docislist;
    }

    public void setDocislist(Short docislist) {
        this.docislist = docislist;
    }

    public List<ArticleCategory> getChildrenArticleCategory() {
        return childrenArticleCategory;
    }

    public void setChildrenArticleCategory(List<ArticleCategory> childrenArticleCategory) {
        this.childrenArticleCategory = childrenArticleCategory;
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }


}