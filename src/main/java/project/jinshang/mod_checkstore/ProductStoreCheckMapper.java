package project.jinshang.mod_checkstore;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import project.jinshang.mod_checkstore.bean.ProductStoreCheck;
import project.jinshang.mod_checkstore.bean.ProductStoreCheckExample;

public interface ProductStoreCheckMapper {
    int countByExample(ProductStoreCheckExample example);

    int deleteByExample(ProductStoreCheckExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ProductStoreCheck record);

    int insertSelective(ProductStoreCheck record);

    List<ProductStoreCheck> selectByExample(ProductStoreCheckExample example);

    ProductStoreCheck selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ProductStoreCheck record, @Param("example") ProductStoreCheckExample example);

    int updateByExample(@Param("record") ProductStoreCheck record, @Param("example") ProductStoreCheckExample example);

    int updateByPrimaryKeySelective(ProductStoreCheck record);

    int updateByPrimaryKey(ProductStoreCheck record);


    @Select("select * from productstorecheck where pdno = #{pdno} and pdid=#{pdid}")
    List<ProductStoreCheck> getProductStoreCheck(@Param("pdno") String pdno, @Param("pdid")int pdid);

    @Select("select * from productstorecheck where id=#{id}")
    ProductStoreCheck getProductStoreCheckById(@Param("id")int id);

    @Delete("delete from productstorecheck where id=#{id}")
    void deleteProductStoreCheckById(@Param("id")int id);

}