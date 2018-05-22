package project.jinshang.mod_advertis;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import project.jinshang.mod_advertis.bean.Advertisement;
import project.jinshang.mod_advertis.bean.AdvertisementExample;

public interface AdvertisementMapper {

    int countByExample(AdvertisementExample example);


    int deleteByExample(AdvertisementExample example);


    int deleteByPrimaryKey(Long id);


    int insert(Advertisement record);


    int insertSelective(Advertisement record);


    List<Advertisement> selectByExample(AdvertisementExample example);


    Advertisement selectByPrimaryKey(Long id);


    int updateByExampleSelective(@Param("record") Advertisement record, @Param("example") AdvertisementExample example);


    int updateByExample(@Param("record") Advertisement record, @Param("example") AdvertisementExample example);


    int updateByPrimaryKeySelective(Advertisement record);


    int updateByPrimaryKey(Advertisement record);

    @Select("select count(*) from advertisement where placeid = #{advertisingPlaceId , jdbcType=BIGINT}")
    int countByAdvertisingPlaceId(@Param("advertisingPlaceId") Long advertisingPlaceId);


    @Select("select am.* from advertisement am\n" +
            "left join advertisingplace ap\n" +
            "on ap.id=am.placeid\n" +
            "where ap.position=#{position , jdbcType=VARCHAR}\n" +
            "and am.endtime > now() \n" +
            "and ap.stop =0 order by am.sort asc limit #{count}")
    List<Advertisement> getAdvertisment(@Param("position") String position, @Param("count") int count);

}