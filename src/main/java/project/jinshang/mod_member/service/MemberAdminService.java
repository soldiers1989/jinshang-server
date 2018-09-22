package project.jinshang.mod_member.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_admin.mod_excelexport.ExcepExportAction;
import project.jinshang.mod_member.AdminUserMapper;
import project.jinshang.mod_member.MemberLabelMapper;
import project.jinshang.mod_member.MemberMapper;
import project.jinshang.mod_member.RegistersourceMapper;
import project.jinshang.mod_member.bean.*;
import project.jinshang.mod_member.bean.dto.MemberAdminQueryDto;
import project.jinshang.mod_member.bean.dto.MemberAdminViewDto;

import java.math.BigDecimal;
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
    @Autowired
    private AdminUserMapper adminUserMapper;
    @Autowired
    private MemberLabelMapper memberLabelMapper;
    @Autowired
    private RegistersourceMapper registersourceMapper;
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
    public PageInfo list(String adminrealname,MemberAdminQueryDto dto,int pageNo,int pageSize){
        PageHelper.startPage(pageNo,pageSize);

        if(StringUtils.hasText(dto.getUsername())){
            dto.setUsername("%"+dto.getUsername()+"%");
        }

        if(StringUtils.hasText(dto.getClerkname())){
            dto.setClerkname("%"+dto.getClerkname()+"%");
        }

        if(StringUtils.hasText(dto.getWaysalesman())){
            dto.setWaysalesman("%"+dto.getWaysalesman()+"%");
        }

        if(StringUtils.hasText(dto.getMobile())){
            dto.setMobile("%"+dto.getMobile()+"%");
        }

        if(StringUtils.hasText(dto.getRegistersourcelabel())){
            dto.setRegistersourcelabel("%"+dto.getRegistersourcelabel()+"%");
        }

        if(StringUtils.hasText(dto.getRegistertypelabel())){
            dto.setRegistertypelabel("%"+dto.getRegistertypelabel()+"%");
        }

        if(StringUtils.hasText(dto.getRegisterchannellabel())){
            dto.setRegisterchannellabel("%"+dto.getRegisterchannellabel()+"%");
        }



        List<MemberAdminViewDto> list =  memberMapper.adminListMemberInfo(adminrealname,dto);
        for (int i=0;i<list.size();i++){
            if(list.get(i).getLabelid() != null){
                list.get(i).setLabelidArr(list.get(i).getLabelid().split(","));
            }
            if(list.get(i).getLabelname() != null){
                list.get(i).setLabelnameArr(list.get(i).getLabelname().split(","));
            }

            AdminUser adminUser= adminUserMapper.getAdminUserByUserid(list.get(i).getId());
            System.out.println(list.get(i).getId());
            if (adminUser!=null){
                list.get(i).setManageState(adminUser.getAdminid());
            }
        }

        return  new PageInfo(list);
    }




    /**
     * 后台根据条件分页导出用户信息Excel
     * @param dto
     * @return
     */
    public List<Map<String,Object>> listForExportExcel(String adminrealname,MemberAdminQueryDto dto){


        if(StringUtils.hasText(dto.getUsername())){
            dto.setUsername("%"+dto.getUsername()+"%");
        }

        if(StringUtils.hasText(dto.getClerkname())){
            dto.setClerkname("%"+dto.getClerkname()+"%");
        }

        if(StringUtils.hasText(dto.getWaysalesman())){
            dto.setWaysalesman("%"+dto.getWaysalesman()+"%");
        }

        if(StringUtils.hasText(dto.getMobile())){
            dto.setMobile("%"+dto.getMobile()+"%");
        }

        if(StringUtils.hasText(dto.getRegistersourcelabel())){
            dto.setRegistersourcelabel("%"+dto.getRegistersourcelabel()+"%");
        }

        if(StringUtils.hasText(dto.getRegistertypelabel())){
            dto.setRegistertypelabel("%"+dto.getRegistertypelabel()+"%");
        }

        if(StringUtils.hasText(dto.getRegisterchannellabel())){
            dto.setRegisterchannellabel("%"+dto.getRegisterchannellabel()+"%");
        }




        List<Map<String,Object>> list =  memberMapper.adminListMemberInfoForExcelExport(adminrealname,dto);

        List<Map<String,Object>> data = new ArrayList<>();


        for (Map map : list) {
            Map<String, Object> resMap = new HashMap<>();

           /* String templabelid = (String) map.get("labelid");
            if(StringUtils.hasText(templabelid)) {
                String[] array = templabelid.split(",");
                String labelid = null;
                for (int i = 0; i < array.length; i++) {
                    labelid = array[0];
                }
                    MemberLabel memberLabel = memberLabelMapper.selectByPrimaryKey(Long.parseLong(labelid));
                    resMap.put("标签", Long.parseLong(labelid) == memberLabel.getId() ? memberLabel.getLabelname() : "");
            }else{
                resMap.put("标签","");
            }*/

            try{
                //注册类型查询
                Registersource registersourceDto = new Registersource();
                short type = 2;
                int pageNo = -1;
                int pageSize = 20;
                registersourceDto.setType(type);
                List<Registersource> list1 = registersourceMapper.selectRegistersourceByType(registersourceDto);
                for (Registersource registersource : list1) {
                    if (map.get("registertypelabel") != null && !"".equals(map.get("registertypelabel")) && map.get("registertypelabel") instanceof Number) {
                        if (Integer.valueOf((String) map.get("registertypelabel")) == Integer.valueOf(registersource.getLabel())) {
                            resMap.put("注册类型", registersource.getLabelname());
                        }
                    } else {
                        resMap.put("注册类型", "");
                    }
                }
                //注册渠道查询
                Registersource registersourceDto1 = new Registersource();
                short type1 = 3;
                registersourceDto1.setType(type1);
                List<Registersource> list2 = registersourceMapper.selectRegistersourceByType(registersourceDto1);
                for (Registersource registersource1 : list2) {
                    if (map.get("registerchannellabel") != null && !"".equals(map.get("registerchannellabel")) && registersource1.getLabel() != null && !"".equals(registersource1.getLabel())) {
                        if (Integer.valueOf(map.get("registerchannellabel").toString()) == Integer.valueOf(registersource1.getLabel())) {
                            resMap.put("注册渠道", registersource1.getLabelname());
                        }
                    } else {
                        resMap.put("注册渠道", "");
                    }
                }


                resMap.put("会员编号", map.get("id"));
                resMap.put("会员全称", map.get("username"));
                resMap.put("会员类型", (boolean) map.get("company") ? "公司" : "个人");
                resMap.put("真实姓名", map.get("realname"));
                resMap.put("会员等级", map.get("gradename"));
                resMap.put("标签", map.get("labelname"));

                resMap.put("注册时间", map.get("createdate"));
                resMap.put("业务员", map.get("waysalesman"));
                resMap.put("会员账号", map.get("username"));
                resMap.put("手机号码", map.get("mobile"));
                resMap.put("邮箱", map.get(""));
                resMap.put("传真", map.get("fax"));
                resMap.put("固定电话", map.get("telephone"));
                resMap.put("邮编", map.get("postcode"));
                resMap.put("邀请码", map.get("invitecode"));
                resMap.put("QQ", map.get("qq"));
                resMap.put("微信", map.get("wxpay"));
                resMap.put("支付宝", map.get("alipay"));
                resMap.put("爱好", map.get("hobby"));
                resMap.put("联系地址", StringUtils.nvl(map.get("address")));
                resMap.put("授信额度", map.get("creditlimit"));


                resMap.put("结算日（每月）", 25);
                resMap.put("客服人员", map.get("clerkname"));
                resMap.put("是否卖家", (Integer) map.get("type") == 1 ? "是" : "否");
                resMap.put("单位简称", map.get("shortname"));
                resMap.put("单位全称", map.get("companyname"));
                resMap.put("单位编号", map.get("companyid"));
                resMap.put("所在地", StringUtils.nvl(map.get("companyprovince")) + StringUtils.nvl(map.get("companycity")) + StringUtils.nvl(map.get("commpanysmallcity")));
                resMap.put("详细地址", map.get("companyaddress"));
                resMap.put("单位电话", map.get("worktelephone"));
                resMap.put("联系手机", map.get("companymobile"));
                resMap.put("结算方式", map.get("methodsettingaccount"));
                resMap.put("开户行", map.get("bankdeposit"));
                resMap.put("开户银行", map.get("bankname"));
                resMap.put("银行账号", map.get("bankaccount"));
                resMap.put("纳税号", map.get("taxregistrationcertificate"));
                if (map.get("registersourcelabel") != null && !"".equals(map.get("registersourcelabel"))) {
                    resMap.put("注册来源", Integer.valueOf((String) map.get("registersourcelabel")) == 1 ? "PC端" : Integer.valueOf((String) map.get("registersourcelabel")) == 2 ? "微信端" : Integer.valueOf((String) map.get("registersourcelabel")) == 3 ? "IOS" : "安卓");
                } else {
                    resMap.put("注册来源", "");
                }
                data.add(resMap);
            }catch(Exception e){
                ExcepExportAction eea = new ExcepExportAction();
                eea.map.remove("memberinfo");
            }
        }

        return  data;
    }





    /**
     * 查询没有添加管理发会员
     * @param id
     * @param companyname
     * @param realname
     * @param mobile
     * @return
     */
    public PageInfo findNotAddMembers(Long id,String companyname,String realname,String mobile,Long disStatus,int pageNo,int pageSize){
        PageHelper.startPage(pageNo,pageSize);
        List<MemberAdminViewDto> list =  memberMapper.findNotAddMembers(id, companyname,realname,mobile,disStatus);
        return  new PageInfo(list);
    }
    /**
     * 查询没有添加管理发会员
     * @param id
     * @param companyname
     * @param realname
     * @param mobile
     * @return
     */
    public PageInfo findManageMemberList(Long id,String companyname,String clerkname,String realname,String mobile,long adminid,int pageNo,int pageSize){
        PageHelper.startPage(pageNo,pageSize);
        List<MemberAdminViewDto> list =  memberMapper.findManageMemberList(id, companyname, clerkname,realname,mobile,adminid);
        return  new PageInfo(list);
    }

    /**
     * 查询添加业务员的
     * @param id
     * @param companyname
     * @param realname
     * @param mobile
     * @param adminid
     * @param labelname
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageInfo findManageWaysalesmanList(Long id, String companyname, String realname, String mobile, Long adminid, String labelname, int pageNo, int pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        List<MemberAdminViewDto> list =  memberMapper.findManageWaysalesmanList(id, companyname, realname,mobile,adminid,labelname);
        return  new PageInfo(list);
    }

    /**
     * 查询未添加的业务员
     * @param id
     * @param companyname
     * @param realname
     * @param mobile
     * @param labelname
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageInfo findNotAddWaysalesman(Long id, String companyname, String realname, String mobile, String labelname,Long disStatus, int pageNo, int pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        List<MemberAdminViewDto> list =  memberMapper.findNotAddWaysalesman(id, companyname, realname,mobile,labelname,disStatus);
        return  new PageInfo(list);
    }
}
