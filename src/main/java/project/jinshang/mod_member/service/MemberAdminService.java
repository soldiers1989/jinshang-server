package project.jinshang.mod_member.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_member.MemberMapper;
import project.jinshang.mod_member.bean.MemberExample;
import project.jinshang.mod_member.bean.dto.MemberAdminQueryDto;
import project.jinshang.mod_member.bean.dto.MemberAdminViewDto;

import java.awt.geom.RectangularShape;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create : wyh
 * date : 2017/12/9
 */

@Service
public class MemberAdminService {

    @Autowired
    private MemberMapper memberMapper;

    /**
     * 获取消费转化率
     * @return
     */
    public BigDecimal getPercentConversion(MemberAdminQueryDto dto){


        BigDecimal percentConversion = new BigDecimal(0);

        MemberExample memberExample1 = new MemberExample();

        MemberExample memberExample2 = new MemberExample();

        MemberExample.Criteria criteria1 = memberExample1.createCriteria();

        criteria1.andIsbuyEqualTo(Quantity.STATE_2);

        MemberExample.Criteria criteria2 = memberExample2.createCriteria();

        criteria2.andIsbuyEqualTo(Quantity.STATE_1);

        if(dto.getRegistDateStart()!=null){
            criteria1.andCreatedateGreaterThanOrEqualTo(dto.getRegistDateStart());
            criteria2.andCreatedateGreaterThanOrEqualTo(dto.getRegistDateStart());
        }
        if(dto.getRegistDateEnd()!=null){
            criteria1.andCreatedateLessThanOrEqualTo(dto.getRegistDateEnd());
            criteria2.andCreatedateLessThanOrEqualTo(dto.getRegistDateEnd());
        }

        int count1 = memberMapper.countByExample(memberExample1);

        int count2 = memberMapper.countByExample(memberExample2);

        percentConversion = new BigDecimal(count1).divide(new BigDecimal(count2),2,BigDecimal.ROUND_HALF_UP);
        return percentConversion.multiply(new BigDecimal(100));
    }


    /**
     * 后台根据条件分页查询用户信息
     * @param dto
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageInfo list(MemberAdminQueryDto dto,int pageNo,int pageSize){
        PageHelper.startPage(pageNo,pageSize);

        if(StringUtils.hasText(dto.getUsername())){
            dto.setUsername("%"+dto.getUsername()+"%");
        }

        if(StringUtils.hasText(dto.getClerkname())){
            dto.setClerkname("%"+dto.getClerkname()+"%");
        }

        if(StringUtils.hasText(dto.getMobile())){
            dto.setMobile("%"+dto.getMobile()+"%");
        }

        List<MemberAdminViewDto> list =  memberMapper.adminListMemberInfo(dto);

        for(MemberAdminViewDto view : list){
            if(view.getLabelid() != null){
                view.setLabelidArr(view.getLabelid().split(","));
            }
        }

        return  new PageInfo(list);
    }




    public List<Map<String,Object>> listForExportExcel(MemberAdminQueryDto dto){


        if(StringUtils.hasText(dto.getUsername())){
            dto.setUsername("%"+dto.getUsername()+"%");
        }

        if(StringUtils.hasText(dto.getClerkname())){
            dto.setClerkname("%"+dto.getClerkname()+"%");
        }

        if(StringUtils.hasText(dto.getMobile())){
            dto.setMobile("%"+dto.getMobile()+"%");
        }


        List<Map<String,Object>> list =  memberMapper.adminListMemberInfoForExcelExport(dto);

        List<Map<String,Object>> data = new ArrayList<>();



        for(Map map : list){
            Map<String,Object> resMap = new HashMap<>();

            resMap.put("会员编号",map.get("id"));
            resMap.put("会员全称",map.get("nick"));
            resMap.put("会员类型",(boolean)map.get("company") ? "公司":"个人");
            resMap.put("真实姓名",map.get("realname"));
            resMap.put("会员等级",map.get("gradename"));
            resMap.put("注册时间",map.get("createdate"));
            resMap.put("介绍人",map.get("waysalesman"));
            resMap.put("会员账号",map.get("username"));
            resMap.put("手机号码",map.get("mobile"));
            resMap.put("邮箱",map.get(""));
            resMap.put("传真",map.get("fax"));
            resMap.put("固定电话",map.get("telephone"));
            resMap.put("邮编",map.get("postcode"));
            resMap.put("邀请码",map.get("invitecode"));
            resMap.put("QQ",map.get("qq"));
            resMap.put("微信",map.get("wxpay"));
            resMap.put("支付宝",map.get("alipay"));
            resMap.put("爱好",map.get("hobby"));
            resMap.put("联系地址", StringUtils.nvl(map.get("address")));
            resMap.put("授信额度",map.get("creditlimit"));


            resMap.put("结算日（每月）",25);
            resMap.put("业务员",map.get("clerkname"));
            resMap.put("是否卖家",(Integer)map.get("type") == 1 ? "是":"否");
            resMap.put("单位简称",map.get("shortname"));
            resMap.put("单位全称",map.get("companyname"));
            resMap.put("单位编号",map.get("companyid"));
            resMap.put("所在地",StringUtils.nvl(map.get("companyprovince"))+StringUtils.nvl(map.get("companycity"))+StringUtils.nvl(map.get("commpanysmallcity")));
            resMap.put("详细地址",map.get("companyaddress"));
            resMap.put("单位电话",map.get("worktelephone"));
            resMap.put("联系手机",map.get("companymobile"));
            resMap.put("结算方式",map.get("methodsettingaccount"));
            resMap.put("开户行",map.get("bankdeposit"));
            resMap.put("开户银行",map.get("bankname"));
            resMap.put("银行账号",map.get("bankaccount"));
            resMap.put("纳税号",map.get("taxregistrationcertificate"));

            data.add(resMap);
        }


        return  data;
    }







}
