package project.jinshang.mod_admin.mod_synonym;

import mizuki.project.core.restserver.config.mybatis.provider.PGBaseSqlProvider;
import mizuki.project.core.restserver.config.mybatis.typeHandler.array.StringArrayHandler;
import org.apache.ibatis.annotations.*;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@Mapper
public interface SynonymMapper {

    @InsertProvider(type = PGBaseSqlProvider.class,method = "insert")
    void save(Synonym synonym);

    @UpdateProvider(type = PGBaseSqlProvider.class,method = "updateAll")
    void update(Synonym synonym);

    @Select("<script>select * from synonym " +
            "<if test=\"search != null and search!='' \"> where to_tsvector(array_to_string(words, ' '))  @@ to_tsquery(#{search})</if>" +
            " order by id desc</script>")
    @Results({
            @Result(property = "words",column = "words",typeHandler = StringArrayHandler.class)
    })
    List<Synonym> listAll(@Param("search") String search);

    @Delete("delete from synonym where id=#{param1}")
    void del(int id);

    @Select("select * from synonym where id=#{param1}")
    @Results({
            @Result(property = "words",column = "words",typeHandler = StringArrayHandler.class)
    })
    Synonym findById(int id);


    //@Cacheable(value = "Synonym",key = "'searchForQuery:'+#p0")
    @Select("select * from synonym where to_tsvector(array_to_string(words, ' '))" +
            " @@ to_tsquery(#{params})")
    @Results({
            @Result(property = "words",column = "words",typeHandler = StringArrayHandler.class)
    })
    List<Synonym> searchForQuery(@Param("params") String params);
}
