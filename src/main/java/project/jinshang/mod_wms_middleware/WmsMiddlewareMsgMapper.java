package project.jinshang.mod_wms_middleware;

import mizuki.project.core.restserver.config.mybatis.provider.PGBaseSqlProvider;
import org.apache.ibatis.annotations.*;
import project.jinshang.mod_wms_middleware.bean.WmsMidMsg;

import java.util.List;
import java.util.Map;

@Mapper
public interface WmsMiddlewareMsgMapper {

    @InsertProvider(type = PGBaseSqlProvider.class,method = "insert")
    void save(WmsMidMsg wmsMidMsg);

    @UpdateProvider(type = PGBaseSqlProvider.class,method = "updateAll")
    void update(WmsMidMsg wmsMidMsg);

    @Select("select * from wms_middleware_msg where status=#{param1} order by id")
    List<WmsMidMsg> list(int status);

    @Delete("delete from wms_middleware_msg where id=#{param1}")
    void del(int id);


    @Select("select pst.pdno,pst.pdid,pst.storeid,pst.storename,p.memberid from productstore pst,productinfo p where " +
            "pst.pdid=p.id and p.memberid=#{memberid} and p.pdstate<>6 order by pst.id desc ")
    List<Map<String,Object>> getProdStoreInfo(@Param("memberid") long memberid);
}
