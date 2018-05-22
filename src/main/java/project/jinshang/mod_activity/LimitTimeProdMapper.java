package project.jinshang.mod_activity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import project.jinshang.mod_activity.bean.LimitTimeProd;
import project.jinshang.mod_activity.bean.LimitTimeProdExample;
import project.jinshang.mod_activity.bean.dto.LimitTimeProdQuery;
import project.jinshang.mod_activity.provider.LimitTimeActivityProvider;

public interface LimitTimeProdMapper {
    int countByExample(LimitTimeProdExample example);

    @CacheEvict(value = "project.jinshang.mod_activity.LimitTimeProdMapper",allEntries = true)
    int deleteByExample(LimitTimeProdExample example);

    @CacheEvict(value = "project.jinshang.mod_activity.LimitTimeProdMapper",key = "'project.jinshang.mod_activity.LimitTimeProdMapper.selectByPrimaryKey:' + #p0")
    int deleteByPrimaryKey(Long id);

    int insert(LimitTimeProd record);

    int insertSelective(LimitTimeProd record);

    List<LimitTimeProd> selectByExample(LimitTimeProdExample example);


    @Cacheable(value = "project.jinshang.mod_activity.LimitTimeProdMapper",key = "'project.jinshang.mod_activity.LimitTimeProdMapper.selectByPrimaryKey:' + #p0")
    LimitTimeProd selectByPrimaryKey(Long id);

    @CacheEvict(value = "project.jinshang.mod_activity.LimitTimeProdMapper",allEntries = true)
    int updateByExampleSelective(@Param("record") LimitTimeProd record, @Param("example") LimitTimeProdExample example);

    @CacheEvict(value = "project.jinshang.mod_activity.LimitTimeProdMapper",allEntries = true)
    int updateByExample(@Param("record") LimitTimeProd record, @Param("example") LimitTimeProdExample example);

    @CacheEvict(value = "project.jinshang.mod_activity.LimitTimeProdMapper",key = "'project.jinshang.mod_activity.LimitTimeProdMapper.selectByPrimaryKey:' + #p0.id")
    int updateByPrimaryKeySelective(LimitTimeProd record);

    @CacheEvict(value = "project.jinshang.mod_activity.LimitTimeProdMapper",key = "'project.jinshang.mod_activity.LimitTimeProdMapper.selectByPrimaryKey:' + #p0.id")
    int updateByPrimaryKey(LimitTimeProd record);


    /**
     * 卖家端列表查询
     * @param query
     * @return
     */
    @SelectProvider(type = LimitTimeActivityProvider.class,method = "listBuyPageForSeller")
    List<Map> listBuyPageForSeller(LimitTimeProdQuery query);


    /**
     * 卖家端导出限时购活动excel
     * @param query
     * @return
     */
    @SelectProvider(type = LimitTimeActivityProvider.class,method = "listBuyPageForSellerExportExcel")
    List<Map<String,Object>> listBuyPageForSellerExportExcel(LimitTimeProdQuery query);



    /**
     * 后台列表查询
     * @param query
     * @return
     */
    @SelectProvider(type = LimitTimeActivityProvider.class,method = "listBuyPageForAdmin")
    List<Map> listBuyPageForAdmin(LimitTimeProdQuery query);



    /**
     * 后台导出限时购活动excel
     * @param query
     * @return
     */
    @SelectProvider(type = LimitTimeActivityProvider.class,method = "listBuyPageForAdminExportExcel")
    List<Map<String,Object>> listBuyPageForAdminExportExcel(LimitTimeProdQuery query);

    /**
     * 前台列表查询
     * @param query
     * @return
     */
    @SelectProvider(type = LimitTimeActivityProvider.class,method = "listBuyPageForFront")
    List<Map> listBuyPageForFront(@Param("query") LimitTimeProdQuery query,@Param("befoBuytime") int befoBuytime);


    @Update("update limittimeprod set state=4 where state=1 and begintime<now() and endtime>now()")
    void setToStart();


    @Update("update limittimeprod set state=5 where state=4 and endtime<now()")
    void setToEnd();

    @Update("update limittimeprod set salestotalnum = salestotalnum + #{num} where id=#{id}")
    void updateSalestotalnumInDB(@Param("id") Long id,@Param("num") BigDecimal num);

}