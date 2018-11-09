package project.jinshang.mod_member.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_member.ImageRecordMapper;
import project.jinshang.mod_member.bean.ImageRecord;

import java.util.List;
import java.util.Map;

@Service
public class ImageRecordService {
    @Autowired
    private ImageRecordMapper imageRecordMapper;

    /**
     * 查询 该用户该分组下的图片是否还有正常的状态 则不允许删除分组
     * @param groupid
     * @param memberid
     * @return
     */
    public List<ImageRecord> selectByGroupIdAndStatus(long groupid, Long memberid) {
        return imageRecordMapper.selectByGroupIdAndStatus(groupid,memberid);
    }

    public ImageRecord selectById(long id) {
        return imageRecordMapper.selectByPrimaryKey(id);
    }

    public void updateImageRecord(ImageRecord updateImageRecord) {
        imageRecordMapper.updateByPrimaryKeySelective(updateImageRecord);
    }


    public List<ImageRecord> selectByIdsAndMemberid(List<Long> ids,Long memberid) {
        return imageRecordMapper.selectByIdsAndMemberid(ids,memberid);
    }

    public void insertImageRecord(ImageRecord imageRecord) {
        imageRecordMapper.insertSelective(imageRecord);
    }

    public PageInfo selectByObejct(int pageNo, int pageSize, ImageRecord imageRecord) {
        if(pageNo != -1){
            PageHelper.startPage(pageNo, pageSize);
        }
        List<Map<String,Object>> list = imageRecordMapper.selectByObject(imageRecord);

        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }
}
