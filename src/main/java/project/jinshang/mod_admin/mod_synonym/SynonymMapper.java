package project.jinshang.mod_admin.mod_synonym;

import mizuki.project.core.restserver.config.mybatis.provider.PGBaseSqlProvider;
import mizuki.project.core.restserver.config.mybatis.typeHandler.array.StringArrayHandler;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SynonymMapper {

    @InsertProvider(type = PGBaseSqlProvider.class,method = "insert")
    void save(Synonym synonym);

    @UpdateProvider(type = PGBaseSqlProvider.class,method = "updateAll")
    void update(Synonym synonym);

    @Select("select * from synonym order by id desc")
    @Results({
            @Result(property = "words",column = "words",typeHandler = StringArrayHandler.class)
    })
    List<Synonym> listAll();

    @Delete("delete from synonym where id=#{param1}")
    void del(int id);

    @Select("select * from synonym where id=#{param1}")
    @Results({
            @Result(property = "words",column = "words",typeHandler = StringArrayHandler.class)
    })
    Synonym findById(int id);

    @Select("select * from synonym where to_tsvector(array_to_string(words, ' '))" +
            " @@ to_tsquery('${params}')")
    @Results({
            @Result(property = "words",column = "words",typeHandler = StringArrayHandler.class)
    })
    List<Synonym> searchForQuery(@Param("params") String params);
}
