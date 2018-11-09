package project.jinshang.mod_member;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import project.jinshang.mod_member.bean.ImageGroup;
import project.jinshang.mod_member.bean.ImageGroupExample;

public interface ImageGroupMapper {
    int countByExample(ImageGroupExample example);

    int deleteByExample(ImageGroupExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ImageGroup record);

    int insertSelective(ImageGroup record);

    List<ImageGroup> selectByExample(ImageGroupExample example);

    ImageGroup selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ImageGroup record, @Param("example") ImageGroupExample example);

    int updateByExample(@Param("record") ImageGroup record, @Param("example") ImageGroupExample example);

    int updateByPrimaryKeySelective(ImageGroup record);

    int updateByPrimaryKey(ImageGroup record);

    @Select("<script>select i.*  " +
            "from imagegroup i " +
            "<where> 1=1 " +
            "<if test=\"imageGroup.id != null  \">AND i.id = #{imageGroup.id} </if>" +
            "<if test=\"imageGroup.groupname != null \">AND i.groupname = #{imageGroup.groupname} </if>" +
            "<if test=\"imageGroup.memberid != null \">AND i.memberid = #{imageGroup.memberid} </if>" +
            "<if test=\"imageGroup.status != null \">AND i.status = #{imageGroup.status} </if>" +
            "</where> order by i.id desc" +
            "</script>")
    List<Map<String,Object>> selectObject(@Param("imageGroup") ImageGroup imageGroup);
}