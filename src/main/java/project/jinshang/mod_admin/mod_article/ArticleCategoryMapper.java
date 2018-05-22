package project.jinshang.mod_admin.mod_article;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import project.jinshang.mod_admin.mod_article.bean.ArticleCategory;
import project.jinshang.mod_admin.mod_article.bean.ArticleCategoryExample;

public interface ArticleCategoryMapper {
    int countByExample(ArticleCategoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ArticleCategory record);

    int insertSelective(ArticleCategory record);

    List<ArticleCategory> selectByExample(ArticleCategoryExample example);

    ArticleCategory selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ArticleCategory record, @Param("example") ArticleCategoryExample example);

    int updateByExample(@Param("record") ArticleCategory record, @Param("example") ArticleCategoryExample example);

    int updateByPrimaryKeySelective(ArticleCategory record);

    int updateByPrimaryKey(ArticleCategory record);

    @Select("select count(*) from articleCategory where praentid = #{id}")
    int selectCountByPraentId(long id);

    @Select("select * from articlecategory order by id asc")
    List<ArticleCategory> getAll();

    @Select("select * from articleCategory where id = #{id}")
    ArticleCategory selectById(Long id);

    @Select("select * from articlecategory where praentid in (select id from articlecategory where docname =#{docName}) order by docorder asc")
    List<ArticleCategory> getChildListByParentName(@Param("docName") String docName);

    @Select("select id from articlecategory where praentid = #{parentId}")
    List<Long> selectIdsByParentId(@Param("parentId") Long parentId);
}