package project.jinshang.mod_admin.mod_article;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import project.jinshang.mod_admin.mod_article.bean.ArticlePic;
import project.jinshang.mod_admin.mod_article.bean.ArticlePicExample;

public interface ArticlePicMapper {
    int countByExample(ArticlePicExample example);

    int deleteByExample(ArticlePicExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ArticlePic record);

    int insertSelective(ArticlePic record);

    List<ArticlePic> selectByExample(ArticlePicExample example);

    ArticlePic selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ArticlePic record, @Param("example") ArticlePicExample example);

    int updateByExample(@Param("record") ArticlePic record, @Param("example") ArticlePicExample example);

    int updateByPrimaryKeySelective(ArticlePic record);

    int updateByPrimaryKey(ArticlePic record);

    @Delete("delete from articlepic where articleid=#{articleid}")
    int deleteByArticlePicId(long articleid);

    @Select("select id,description,path from articlepic where articleid=#{articleId}")
    List<ArticlePic> selectByArticleId(@Param("articleId")Long articleId);
}