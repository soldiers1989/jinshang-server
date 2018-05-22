package project.jinshang.mod_admin.mod_article;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import project.jinshang.mod_admin.mod_article.bean.Article;
import project.jinshang.mod_admin.mod_article.bean.ArticleExample;

public interface ArticleMapper {
    int countByExample(ArticleExample example);

    int deleteByPrimaryKey(long id);

    int insert(Article record);

    int insertSelective(Article record);

    List<Article> selectByExample(ArticleExample example);

    Article selectByPrimaryKey(long id);

    int updateByExampleSelective(@Param("record") Article record, @Param("example") ArticleExample example);

    int updateByExample(@Param("record") Article record, @Param("example") ArticleExample example);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKey(Article record);

    @Select("<script>SELECT * FROM Article al left join articlecategory ac on al.docid=ac.id" +
            "<where> 1=1" +
            "<if test=\"id != null and id !='' \">and al.id=#{id} </if>" +
            "<if test=\"docid != null and docid !='' \">and al.docid=#{docid} </if>" +
            "</where>  order by docorder desc " +
            "</script>")
    List<Map> getAllArticle(Map<String, Object> map);

    @Select("select al.id, al.docid, al.doctitle, al.doccontent, al.docorder, al.docaddress, al.docshow, al.creattime, al.updatetime,al.docstatus,al.sketch,al.pic from Article al inner join articlecategory ac on al.docid=ac.id where ac.docname = #{articleCategoryName} and al.docshow=1 order by al.creattime desc limit #{num}")
    List<Article> searchListByArticleCategoryName(@Param(value = "articleCategoryName") String articleCategoryName,
                                                  @Param(value = "num") int num);


    @Select("select article.* ,articlecategory.docname\n" +
            "from article\n" +
            "INNER JOIN articlecategory on articlecategory.id = article.docid\n" +
            "where article.docid in (select id from articlecategory where docname=#{docName}) \n" +
            "and article.docshow=1")
    List<Article> selectAppArticleList(@Param("docName") String docName);

    @Select("select article.*, articlecategory.docname from article \n" +
            "INNER join articlecategory on article.docid = articlecategory.id\n" +
            "where article.docid in (${ids}) \n" +
            "and doctitle like #{titleName}\n" +
            "order by docorder asc")
    List<Article> selectArticleListByTypeIds(@Param("titleName") String titleName,
                                             @Param("ids") String ids);
}