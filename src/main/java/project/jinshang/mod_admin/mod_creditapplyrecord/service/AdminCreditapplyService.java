package project.jinshang.mod_admin.mod_creditapplyrecord.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import mizuki.project.core.restserver.config.BasicRet;
import mizuki.project.core.restserver.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.common.utils.JinshangUtils;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_admin.mod_creditapplyrecord.bean.AccountDetailQuery;
import project.jinshang.mod_credit.CreditApplyRecordMapper;
import project.jinshang.mod_credit.bean.CreditApplyRecord;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create : wyh
 * date : 2017/12/1
 */

@Service
public class AdminCreditapplyService {

    @Autowired
    private CreditApplyRecordMapper creditApplyRecordMapper;


    /**
     * 后台列表显示
     * @param conditionMap
     * @return
     */
    public PageInfo listForAdmin(int pageNo,int pageSize,Map<String,Object> conditionMap){
        PageHelper.startPage(pageNo,pageSize);

        List<CreditApplyRecord> list =creditApplyRecordMapper.listForAdmin(conditionMap);

        PageInfo pageInfo = new PageInfo(list);

        return  pageInfo;
    }


    public  List<Map<String,Object>>  listForAdminExcel(Map<String,Object> conditionMap){

        List<CreditApplyRecord> list =creditApplyRecordMapper.listForAdminExcel(conditionMap);

        List<Map<String,Object>>  resList = new ArrayList<>();

        for(CreditApplyRecord record : list){
            Map<String,Object> map =  new HashMap<>();

            map.put("状态", JinshangUtils.creditstate(record.getState()));
            map.put("申请时间",record.getCreatetime());
            map.put("会员id",record.getMemberid());
            map.put("联系人",record.getContract());
            map.put("手机号",record.getPhone());
            map.put("单位名",record.getCompany());
            map.put("申请信用额度",record.getLimitmoney());


            resList.add(map);
        }


        return  resList;
    }


    public  CreditApplyRecord getById(long id){
        return  creditApplyRecordMapper.selectByPrimaryKey(id);
    }


    public  void  updateByPrimaryKeySelective(CreditApplyRecord record){
        creditApplyRecordMapper.updateByPrimaryKeySelective(record);
    }


    /**
     * 授信用户列表
     * @param conditMap
     * @param pageNo
     * @param pageSize
     * @return
     */
    public  PageInfo listCreditUser(Map<String,Object> conditMap,int pageNo,int pageSize){
        PageHelper.startPage(pageNo,pageSize);

        PageInfo pageInfo =new PageInfo(creditApplyRecordMapper.listCreditUser(conditMap));
        return  pageInfo;
    }


    /**
     * 后台授信会员导出Excel
     * @param conditMap 条件
     * @return
     */
    public  List<Map<String,Object>> listCreditUserForAdminExportExcel(Map<String,Object> conditMap){

        List<Map<String,Object>> list =creditApplyRecordMapper.listCreditUserForAdminExportExcle(conditMap);


        String[] rowTitles = new String[]{"会员全称","会员类型","真实姓名","会员等级",
                "联系手机","邮箱","单位全称","单位地址","授信额度","可用授信额度","最后还款日",
                "累计帐单数","累计违约数","最长违约天数", "累计违约金额"};

        List<Map<String,Object>> data = new ArrayList<>();

        for(Map<String,Object> map : list){
            Map<String,Object> resMap = new HashMap<>();

            resMap.put("会员全称",map.get("username"));
            resMap.put("会员类型",(boolean)map.get("company") ? "公司":"个人");
            resMap.put("真实姓名",map.get("realname"));
            resMap.put("会员等级",map.get("gradename"));
            resMap.put("联系手机",map.get("mobile"));
            resMap.put("邮箱",map.get("email"));
            resMap.put("单位全称",map.get("companyname"));
            resMap.put("单位地址", StringUtils.nvl(map.get("companyprovince"))+StringUtils.nvl(map.get("companycity"))
                        +StringUtils.nvl(map.get("companycitysmall"))+StringUtils.nvl(map.get("companyaddress")));
            resMap.put("授信额度",map.get("creditlimit"));
            resMap.put("可用授信额度",map.get("availablelimit"));
            resMap.put("最后还款日","15");
            resMap.put("累计帐单数",map.get("billcount"));
            resMap.put("累计违约数",map.get("totalillegalcount"));
            resMap.put("最长违约天数",map.get("maxillagedays"));
            resMap.put("累计违约金额",map.get("totalillegalmoney"));

            data.add(resMap);
        }


        return  data;

    }



    /**
     * 查询用户已出账单
     * @param memberid
     * @param pageNo
     * @param pageSize
     * @return
     */
    public  PageInfo listUserBillCreate(long memberid,int pageNo,int pageSize){
        PageHelper.startPage(pageNo,pageSize);
        return  new PageInfo(creditApplyRecordMapper.listUserBillCreate(memberid));
    }


    public  Map<String,Object> creditUser(long memberid){
        return  creditApplyRecordMapper.creditUser(memberid);
    }



    public  PageInfo getGroupSettlementtime(int pageNo,int pageSize ){
        PageHelper.startPage(pageNo,pageSize);
        List<String> list = creditApplyRecordMapper.getGroupSettlementtime();
        return  new PageInfo(list);
    }


    /**
     * 根据账期查询用户数
     * @param settlement
     * @return
     */
    public  int getUserMeberBySettlement(String settlement){
        return  creditApplyRecordMapper.getUserMeberBySettlement(settlement);
    }


    /**
     * 根据账期查询所有使用授信的金额
     * @param settlement
     * @return
     */
    public BigDecimal getOutMoneyBySettlement(String settlement){
        return creditApplyRecordMapper.getOutMoneyBySettlement(settlement);
    }


    /**
     * 根据账期查询所有使用授信已还款金额
     * @param settlement
     * @return
     */
    public BigDecimal getInMoneyBySettlement(String settlement){
        return creditApplyRecordMapper.getInMoneyBySettlement(settlement);
    }

    /**
     * 查询该账期内各种状态的用户个数
     * @param settlement
     * @return
     */
    public  int getCountBySettlementAndState( String settlement,Short state){
        return creditApplyRecordMapper.getCountBySettlementAndState(settlement,state);
    }



    public  PageInfo getAccountDetaiByPage(AccountDetailQuery query, int pageNo, int pageSize){
        PageHelper.startPage(pageNo,pageSize);
        List<Map> list =  creditApplyRecordMapper.getAccountDetaiByPage(query);
        list.stream().forEach(map -> {
            BigDecimal money= (BigDecimal) map.get("money");
            BigDecimal amountpaid= (BigDecimal) map.get("amountpaid");
            BigDecimal unamountpaid=money.subtract(amountpaid);
            map.put("unamountpaid",unamountpaid);
        });
        return  new PageInfo(list);
    }

    public  List getExportAccountDetaiByPage(AccountDetailQuery query){
        List<Map> mapList =  creditApplyRecordMapper.getAccountDetaiByPage(query);
        mapList.stream().forEach(map -> {
            BigDecimal money= (BigDecimal) map.get("money");
            BigDecimal amountpaid= (BigDecimal) map.get("amountpaid");
            BigDecimal unamountpaid=money.subtract(amountpaid);
            map.put("unamountpaid",unamountpaid);
        });
        List<Map>  result=new ArrayList<>();
        if(mapList!=null&&mapList.size()>0){
            mapList.stream().forEach(map -> {
                Map<String,Object> map1=new HashMap<>();
                map1.put("结算账单号",map.get("billno"));
                map1.put("会员ID",map.get("buyerid"));
                map1.put("单位名称",map.get("companyname"));
                map1.put("应缴授信金额",map.get("money"));
                map1.put("违约金额",map.get("illegalmoney"));
                map1.put("已缴金额",map.get("amountpaid"));
                map1.put("待还款金额",map.get("unamountpaid"));
                map1.put("客服人员",map.get("clerkname"));
                map1.put("每月还款日","15");
                String statename="";
                int state=(int)map.get("state");
                switch (state){
                    case 0:
                        statename="未缴清";
                        break;
                    case 1:
                        statename="已缴清";
                        break;
                    case 2:
                        statename="已逾期";
                        break;
                    default: ;
                }
                map1.put("状态",statename);
                result.add(map1);
            });
        }
        return result;
    }




    /**
     * 根据状态查询个数
     * @param state
     * @return
     */
    public  int getCountByStates(Short state){
        return  creditApplyRecordMapper.getCountByStates(state);
    }

    /**
     * 根据id更新复核备注信息
     * @param creditApplyRecord
     * @return
     */
    public  int updateCreditApplyRecordReviewnotesByid(CreditApplyRecord creditApplyRecord){
        return  creditApplyRecordMapper.updateByPrimaryKeySelective(creditApplyRecord);
    }

}
