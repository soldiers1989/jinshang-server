package project.jinshang.mod_fx.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_fx.FxcommisionMapper;
import project.jinshang.mod_fx.bean.Fxcommision;
import project.jinshang.mod_fx.dto.FxcommisionDto;
import project.jinshang.mod_member.bean.Member;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * create : zzy
 * date : 2018/05/05
 */
@Service
public class FxCommisionService {
    @Autowired
    private FxcommisionMapper fxcommisionMapper;

    public FxcommisionDto getCountCommisionPriceBycMemberId(Long cmemberid) {
        return  fxcommisionMapper.getCountCommisionPriceBycMemberId(cmemberid);
    }

    public FxcommisionDto getWaitCommisionPriceBycMemberId(Long cmemberid) {
        return  fxcommisionMapper.getWaitCommisionPriceBycMemberId(cmemberid);
    }


    public PageInfo getListByPage(Date startime, Date endtime, Fxcommision fxcommision,long cmemberid, int pageNo, int pageSize) {
        if (pageNo != -1) {
            PageHelper.startPage(pageNo, pageSize);
        }
        List<Map<String, Object>> list = fxcommisionMapper.selectObject(startime, endtime, fxcommision,cmemberid);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    public PageInfo getListByPage1(Date startime, Date endtime, Fxcommision fxcommision,Member member, int pageNo, int pageSize) {
        if (pageNo != -1) {
            PageHelper.startPage(pageNo, pageSize);
        }
        List<Map<String, Object>> list = fxcommisionMapper.selectObject1(startime, endtime, fxcommision,member);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    public FxcommisionDto getAllCountCommisionPrice() {
        return  fxcommisionMapper.getAllCountCommisionPrice();
    }

    public FxcommisionDto getAllWaitCommisionPrice() {
        return fxcommisionMapper.getAllWaitCommisionPrice();
    }

    public int insertFxcommision(Fxcommision fxcommision) {
        return fxcommisionMapper.insertSelective(fxcommision);
    }

    public List<Fxcommision> getFxcommisionList() {
        return fxcommisionMapper.getFxcommisionList();
    }

    public int updateByFxcommision(Fxcommision fxcommision) {
        return  fxcommisionMapper.updateByPrimaryKeySelective(fxcommision);
    }
}
