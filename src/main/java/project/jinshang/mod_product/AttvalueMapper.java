package project.jinshang.mod_product;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import project.jinshang.mod_product.bean.Attvalue;
import project.jinshang.mod_product.bean.AttvalueExample;

public interface AttvalueMapper {
    int countByExample(AttvalueExample example);

    int deleteByExample(AttvalueExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Attvalue record);

    int insertSelective(Attvalue record);

    List<Attvalue> selectByExample(AttvalueExample example);

    Attvalue selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Attvalue record, @Param("example") AttvalueExample example);

    int updateByExample(@Param("record") Attvalue record, @Param("example") AttvalueExample example);

    int updateByPrimaryKeySelective(Attvalue record);

    int updateByPrimaryKey(Attvalue record);

    @Select("select * from attvalue where attid=#{attid} and paramvalue=#{value} limit 1")
    Attvalue getByAttidAndValue(@Param("attid") long attid,@Param("value") String value );

    @Delete("delete from attvalue where attid in (select id from attributetbl where productnameid=#{productnameid})")
    void  deleteAttvalueByProductnameid(@Param("productnameid") long productnameid);

    @Select("select * from attvalue where attid = (select id from attributetbl where productnameid=#{productnameid} and name=#{name}) order by sort asc")
    List<Attvalue> listByProductnameidAndName(@Param("productnameid") long productnameid, @Param("name") String name);



    @Select("select * from attvalue where attid=#{attid} order by sort asc")
    List<Attvalue> getAttvalueByAttid(@Param("attid") long attid);


}