package project.jinshang.mod_coupon.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_coupon.YhqGetpacksMapper;
import project.jinshang.mod_coupon.bean.YhqGetpacks;
import project.jinshang.mod_coupon.bean.YhqGetpacksExample;
import project.jinshang.mod_member.MemberMapper;

import java.util.List;
import java.util.Map;

/**
 * create : zzy
 * date : 2018/05/26
 */
@Service
public class YhqGetPacksService {
    @Autowired
    private YhqGetpacksMapper yhqGetpacksMapper;
    @Autowired
    private MemberMapper memberMapper;

    public YhqGetpacks getYhqGetpacksInfoById(Long id) {
        return  yhqGetpacksMapper.selectByPrimaryKey(id);
    }

    public PageInfo getListByPage(int pageNo, int pageSize, YhqGetpacks yhqGetpacks,String membername) {
        if(pageNo != -1){
            PageHelper.startPage(pageNo,pageSize);
        }
        List<Map<String,Object>> list = yhqGetpacksMapper.selectByObject(yhqGetpacks,membername);
        PageInfo pageInfo = new PageInfo(list);
        return  pageInfo;
    }

    public PageInfo getListByPage1(int pageNo, int pageSize, YhqGetpacks yhqGetpacks) {
        if(pageNo != -1){
            PageHelper.startPage(pageNo,pageSize);
        }
        List<Map<String,Object>> list = yhqGetpacksMapper.selectByObject1(yhqGetpacks);
        PageInfo pageInfo = new PageInfo(list);
        return  pageInfo;
    }

    public long countByExample(YhqGetpacksExample example) {
        return yhqGetpacksMapper.countByExample(example);
    }

    public void isnertYhqGetpacks(YhqGetpacks yhqGetpacks) {
        yhqGetpacksMapper.insertSelective(yhqGetpacks);
    }
}
