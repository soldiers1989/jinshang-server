package project.jinshang.mod_admin.mod_app;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import project.jinshang.mod_admin.mod_app.bean.AppVersion;
import project.jinshang.mod_admin.mod_app.bean.AppVersionExample;

public interface AppVersionMapper {
    int countByExample(AppVersionExample example);

    int deleteByExample(AppVersionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AppVersion record);

    int insertSelective(AppVersion record);

    List<AppVersion> selectByExample(AppVersionExample example);

    AppVersion selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AppVersion record, @Param("example") AppVersionExample example);

    int updateByExample(@Param("record") AppVersion record, @Param("example") AppVersionExample example);

    int updateByPrimaryKeySelective(AppVersion record);

    int updateByPrimaryKey(AppVersion record);



    @Select("<script>select a.*,ad.realname,ad.username  " +
            " from appversion a " +
            "left join admin ad on ad.id = a.adminid " +
            "<where> 1=1 " +
            "<if test=\"appVersion.apptype != null \">and a.apptype = #{appVersion.apptype} </if>" +
            "<if test=\"appVersion.version != null \">and a.version = #{appVersion.version} </if>" +
            "<if test=\"appVersion.type != null \">and a.type = #{appVersion.type} </if>" +
            "<if test=\"appVersion.url != null \">and a.url = #{appVersion.url} </if>" +
            "<if test=\"appVersion.downloadurl != null \">and a.downloadurl = #{appVersion.downloadurl} </if>" +
            "<if test=\"appVersion.channel != null \">and a.channel = #{appVersion.channel} </if>" +
            "<if test=\"appVersion.mobile != null \">and a.mobile = #{appVersion.mobile} </if>" +
            "<if test=\"appVersion.remark != null \">and a.remark = #{appVersion.remark} </if>" +
            "<if test=\"appVersion.type != null \">and a.type = #{appVersion.type} </if>" +
            "</where> order by a.id desc" +
            "</script>")
    List<Map<String,Object>> selectByObject(@Param("appVersion") AppVersion appVersion);

    @Update("update appversion set versionname =  #{appVersion.versionname},version = #{appVersion.version},updatetime = #{appVersion.updatetime},adminid = #{appVersion.adminid}  where apptype=#{appVersion.apptype}")
    void updateVersionOrVersionName(@Param("appVersion")AppVersion appVersion);


    @Select("<script>select a.* " +
            " from appversion a " +
            "<where> 1=1 " +
            "<if test=\"appVersion.apptype != null \">and a.apptype = #{appVersion.apptype} </if>" +
            "<if test=\"appVersion.type != null \">and a.type = #{appVersion.type} </if>" +
            "<if test=\"appVersion.url != null \">and a.url = #{appVersion.url} </if>" +
            "<if test=\"appVersion.downloadurl != null \">and a.downloadurl = #{appVersion.downloadurl} </if>" +
            "<if test=\"appVersion.channel != null \">and a.channel = #{appVersion.channel} </if>" +
            "<if test=\"appVersion.mobile != null \">and a.mobile = #{appVersion.mobile} </if>" +
            "<if test=\"appVersion.remark != null \">and a.remark = #{appVersion.remark} </if>" +
            "<if test=\"appVersion.type != null \">and a.type = #{appVersion.type} </if>" +
            "</where> limit 1 " +
            "</script>")
    AppVersion selectByAppVersion(@Param("appVersion") AppVersion appVersion);


}