package project.jinshang.mod_admin.mod_shopgroup;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import project.jinshang.mod_admin.mod_shopgroup.bean.ShopGroup;
import project.jinshang.mod_admin.mod_shopgroup.bean.ShopGroupExample;
import project.jinshang.mod_admin.mod_shopgroup.provider.ShopGroupProvider;

@Mapper
public interface ShopGroupMapper {
    int countByExample(ShopGroupExample example);

    int deleteByExample(ShopGroupExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ShopGroup record);

    int insertSelective(ShopGroup record);

    List<ShopGroup> selectByExample(ShopGroupExample example);

    ShopGroup selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ShopGroup record, @Param("example") ShopGroupExample example);

    int updateByExample(@Param("record") ShopGroup record, @Param("example") ShopGroupExample example);

    int updateByPrimaryKeySelective(ShopGroup record);

    int updateByPrimaryKey(ShopGroup record);

    @SelectProvider(type = ShopGroupProvider.class,method = "getShopGroup")
    List<ShopGroup> selectShopGroup(@Param("dto")ShopGroup dto);



}