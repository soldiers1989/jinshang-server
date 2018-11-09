package project.jinshang.mod_member;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import project.jinshang.mod_member.bean.ImageRecord;
import project.jinshang.mod_member.bean.ImageRecordExample;

public interface ImageRecordMapper {
    int countByExample(ImageRecordExample example);

    int deleteByExample(ImageRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ImageRecord record);

    int insertSelective(ImageRecord record);

    List<ImageRecord> selectByExample(ImageRecordExample example);

    ImageRecord selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ImageRecord record, @Param("example") ImageRecordExample example);

    int updateByExample(@Param("record") ImageRecord record, @Param("example") ImageRecordExample example);

    int updateByPrimaryKeySelective(ImageRecord record);

    int updateByPrimaryKey(ImageRecord record);

    @Select("select i.*  from imagerecord i where i.imagegroupid = #{groupid} and i.memberid = #{memberid} and i.status = 1 order by i.id desc ")
    List<ImageRecord> selectByGroupIdAndStatus(@Param("groupid") long groupid,@Param("memberid") Long memberid);

    @Select("<script>select i.* where imagerecord i in <foreach collection=\"orderproductids\" item=\"item\" index=\"index\" " +
            "open=\"(\" separator=\",\" close=\")\">#{item}</foreach>  and i.memberid =#{memberid}</script>")
    List<ImageRecord> selectByIdsAndMemberid(@Param("ids") List<Long> ids,@Param("memberid") Long memberid);

    @Select("<script>select i.*  " +
            " from imagerecord i " +
            "<where> 1=1 " +
            "<if test=\"imageRecord.memberid != null \">and i.memberid = #{imageRecord.memberid} </if>" +
            "<if test=\"imageRecord.imagegroupid != null \">and i.imagegroupid = #{imageRecord.imagegroupid} </if>" +
            "<if test=\"imageRecord.status != null \">and i.status = #{imageRecord.status} </if>" +
            "</where> order by i.id desc" +
            "</script>")
    List<Map<String,Object>> selectByObject(@Param("imageRecord") ImageRecord imageRecord);
}