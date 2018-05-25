package project.jinshang.mod_admin.mod_inte.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_admin.mod_inte.IntegralRecordMapper;
import project.jinshang.mod_admin.mod_inte.IntegralSetMapper;
import project.jinshang.mod_admin.mod_inte.bean.*;
import project.jinshang.mod_member.MemberMapper;
import project.jinshang.mod_member.bean.Member;

import java.math.BigDecimal;
import java.util.*;

@Service
public class IntegralService {

    @Autowired
    private IntegralSetMapper integralSetMapper;

    @Autowired
    private IntegralRecordMapper integralRecordMapper;

    @Autowired
    private MemberMapper memberMapper;


    public IntegralRecord getIntegralRecordByOrderId(Long id){
        IntegralRecordExample integralRecordExample = new IntegralRecordExample();
        integralRecordExample.createCriteria().andOrderidEqualTo(id);
        List<IntegralRecord> list = integralRecordMapper.selectByExample(integralRecordExample);
        if(list.size()>0){
            return list.get(0);
        }
        return null;
    }

    /**
     * 获取所有积分设置
     * @return
     */
    public List<IntegralSet> getAllIntegralSet(){
        return integralSetMapper.selectByExample(new IntegralSetExample());
    }

    /**
     * 修改积分
     * @param integralSet
     */
    public void updateIntegralSet(IntegralSet integralSet){
        integralSetMapper.updateByPrimaryKeySelective(integralSet);
    }

    /**
     * 保存积分记录
     * @param record
     */
    public void saveIntegralRecord(IntegralRecord record){
        integralRecordMapper.insertSelective(record);
    }

    /**
     * 获取积分记录
     * @param pageNo
     * @param pageSize
     * @param type
     * @param memberid
     * @param startTime
     * @param endTime
     * @return
     */
    public PageInfo getIntegralRecord(int pageNo, int pageSize, List<Short> type, Long memberid,String membername, Date startTime, Date endTime){
        PageHelper.startPage(pageNo,pageSize);
        IntegralRecordExample integralRecordExample = new IntegralRecordExample();
        IntegralRecordExample.Criteria criteria = integralRecordExample.createCriteria();
        criteria.andScopeNotEqualTo(new BigDecimal(0));
        if(type!=null&&type.size()>0){
            criteria.andTypeIn(type);
        }
        if(memberid!=null){
            criteria.andMemberidEqualTo(memberid);
        }
        if(membername!=null){
            criteria.andMembernameLike("%"+membername+"%");
        }

        if(startTime!=null){
            criteria.andCreatetimeGreaterThanOrEqualTo(startTime);
        }
        if(endTime!=null){
            Calendar c = Calendar.getInstance();
            c.setTime(endTime);
            c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
            Date tomorrow = c.getTime();
            criteria.andCreatetimeLessThanOrEqualTo(tomorrow);
        }
        PageInfo pageInfo = new PageInfo(integralRecordMapper.selectByExample(integralRecordExample));
        return pageInfo;
    }

    /**
     * 查询用户积分信息
     * @param param
     * @return
     */
    public PageInfo getIntegralPageModle(IntegralQueryParam param,int pageNo,int pageSize){
        PageHelper.startPage(pageNo,pageSize);
        PageInfo pageInfo = new PageInfo(integralRecordMapper.getIntegralPageModle(param));
        return  pageInfo;
    }

    /**
     * 导出exel
     * @param param
     * @return
     */
    public List<Map<String,Object>> getIntegralExcel(IntegralQueryParam param){

        List<Map<String,Object>> list = integralRecordMapper.getIntegralExcel(param);
        List<Map<String,Object>> list2 = new ArrayList<>();

        for(Map<String,Object> map:list){
            Map<String,Object> maptemp = new HashMap<>();
            maptemp.put("会员编号",map.get("id"));
            maptemp.put("会员名",map.get("username"));
            maptemp.put("可用积分",map.get("availableintegral"));
            maptemp.put("会员等级",map.get("gradename"));
            maptemp.put("历史积分",map.get("integrals"));
            maptemp.put("会员注册时间",map.get("createdate"));
            list2.add(maptemp);
        }

        return list2;
    }

    /**
     * 后台更新积分
     * @param member
     * @param record
     */
    public void updateMemberIntegral(Member member,IntegralRecord record){
        memberMapper.updateByPrimaryKeySelective(member);
        integralRecordMapper.insertSelective(record);
    }


    public  IntegralSet  getIntegralSetByType(Short type){
        IntegralSetExample example = new IntegralSetExample();
        IntegralSetExample.Criteria criteria = example.createCriteria();
        criteria.andTypeEqualTo(type);
        List<IntegralSet> list = integralSetMapper.selectByExample(example);
        if(list.size()==1){
            return  list.get(0);
        }

        return  null;
    }



}
