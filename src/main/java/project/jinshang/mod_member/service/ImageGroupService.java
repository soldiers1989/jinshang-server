package project.jinshang.mod_member.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_member.ImageGroupMapper;
import project.jinshang.mod_member.bean.ImageGroup;
import project.jinshang.mod_member.bean.ImageGroupExample;

import java.util.List;
import java.util.Map;

@Service
public class ImageGroupService {
    @Autowired
    private ImageGroupMapper imageGroupMapper;

    public void insertImageGroupInfo(ImageGroup imageGroup) {
        imageGroupMapper.insertSelective(imageGroup);
    }

    public ImageGroup selectById(Long id) {
        return  imageGroupMapper.selectByPrimaryKey(id);
    }

    public void updateImageGroupInfo(ImageGroup imageGroup) {
        imageGroupMapper.updateByPrimaryKeySelective(imageGroup);
    }

    public PageInfo selectByObject(ImageGroup imageGroup, int pageNo, int pageSize) {
        if (pageNo != -1) {
            PageHelper.startPage(pageNo, pageSize);
        }
        List<Map<String, Object>> list = imageGroupMapper.selectObject(imageGroup);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    public long countByExample(ImageGroupExample example) {
        return  imageGroupMapper.countByExample(example);
    }
}
