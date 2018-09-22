package project.jinshang.mod_company;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import project.jinshang.mod_admin.mod_shopgroup.bean.dto.ShopGroupAndStoreView;
import project.jinshang.mod_admin.mod_store.bean.StoreManageQueryDto;
import project.jinshang.mod_admin.mod_store.provider.StoreManageProvider;
import project.jinshang.mod_company.bean.Store;
import project.jinshang.mod_company.bean.StoreExample;

public interface StoreMapper {
    int countByExample(StoreExample example);

    int deleteByExample(StoreExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Store record);

    int insertSelective(Store record);

    List<Store> selectByExample(StoreExample example);

    Store selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Store record, @Param("example") StoreExample example);

    int updateByExample(@Param("record") Store record, @Param("example") StoreExample example);

    int updateByPrimaryKeySelective(Store record);

    int updateByPrimaryKey(Store record);

    @Select("select * from store where memberid=#{memberid} and name=#{name}")
    Store getByName(@Param("memberid") Long memberid,@Param("name") String name);

    //提交
    @Select("select * from store where memberid=#{memberid} and id=#{id} limit 1")
    Store getByIdAndMemberId(@Param("id") long id,@Param("memberid") long memberid);


    @Select("select * from store where memberid=#{memberid} and name=#{name} limit 1")
    Store getByNameAndMemberId(@Param("name") String name,@Param("memberid") long memberid);



    @SelectProvider(type = StoreManageProvider.class,method = "searchManageList")
    List<Map<String,Object>> searchManageList(StoreManageQueryDto queryDto);

    @SelectProvider(type=StoreManageProvider.class,method ="getByShopGroupIdAndName")
    List<ShopGroupAndStoreView> getByShopGroupIdAndName(@Param("dto")Store dto);


    @Update("update store set shopgroupid=0 where shopgroupid=#{shopgroupid} ")
    int resertStoreShopGroup(@Param("shopgroupid")Long shopGroupId);


}