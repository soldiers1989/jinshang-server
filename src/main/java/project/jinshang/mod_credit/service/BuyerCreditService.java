package project.jinshang.mod_credit.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.utils.DateUtils;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_cash.BuyerCapitalMapper;
import project.jinshang.mod_cash.bean.BuyerCapital;
import project.jinshang.mod_credit.BillCreateMapper;
import project.jinshang.mod_credit.CreditApplyRecordMapper;
import project.jinshang.mod_credit.bean.*;
import project.jinshang.mod_member.bean.Member;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/11/22.
 */
@Service
public class BuyerCreditService {

    @Autowired
    private CreditApplyRecordMapper creditApplyRecordMapper;

    @Autowired
    private BillCreateMapper billCreateMapper;

    @Autowired
    private BuyerCapitalMapper buyerCapitalMapper;

    public CreditApplyRecord getCreditApplyRecord(Long id){
        return creditApplyRecordMapper.selectByPrimaryKey(id);
    }

    /**
     * 还款保存资金明细
     * @param buyerCapital
     */
    public void saveBuyerCapital(BuyerCapital buyerCapital){
        buyerCapitalMapper.insertSelective(buyerCapital);
    }


    /**
     * 获取未出账单总金额
     * @param memberid
     * @return
     */
    public BigDecimal getTotalNotOutBillMoney(long memberid){
        return buyerCapitalMapper.getTotalNotOutBillMoney(memberid);
    }

    /**
     * 获取账单的资金明细
     * @param ordernos
     * @return
     */
    public List<BuyerCapital> getBuyerCapitalList(String ordernos){
        return buyerCapitalMapper.getBuyerCapitalList(ordernos);
    }

    /**
     * 授信获取未出账单明细
     * @param memberid
     * @return
     */
    public PageInfo getNotOutBuyerCapitalList(long memberid,int pageNo,int pageSize){
        PageHelper.startPage(pageNo,pageSize);
        PageInfo pageInfo = new PageInfo(buyerCapitalMapper.getNotOutBuyerCapitalList(memberid));
        return pageInfo;
    }

    /**
     * 获取未出账单总金额
     * @param member
     * @return
     */
    public BigDecimal getNotOutCreditSum(Member member){
//       List<BuyerCapital> list = buyerCapitalMapper.getNotOutBuyerCapitalListSum(memberid);
//        BigDecimal sumout =  new BigDecimal(0);
//        BigDecimal sumin = new BigDecimal(0);
//       for(BuyerCapital buyerCapital:list){
//           if(buyerCapital.getCapitaltype()==Quantity.STATE_0){
//               sumout = sumout.add(buyerCapital.getCapital());
//           }
//           if(buyerCapital.getCapitaltype()==Quantity.STATE_2){
//               sumin = sumin.add(buyerCapital.getCapital());
//           }
//       }
//
//       return sumout.subtract(sumin);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH,27);
        Date buyerinspectiontimeEndDate = calendar.getTime();
        calendar.add(Calendar.MONTH,-1);
        calendar.add(Calendar.DATE,1);
        Date buyerinspectiontimeStartDate =  calendar.getTime();

//        //正式环境
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.DAY_OF_MONTH,27);
//        Date buyerinspectiontimeEndDate = calendar.getTime();
//        calendar.add(Calendar.MONTH,-1);
//        Date buyerinspectiontimeStartDate =  calendar.getTime();


        /*
        //测试环境
        Calendar calendar = Calendar.getInstance();
        //calendar.add(Calendar.DAY_OF_MONTH,-1);
        Date buyerinspectiontimeEndDate = calendar.getTime();
        Date buyerinspectiontimeStartDate =  calendar.getTime();
        */

        String buyerinspectiontimeStart = DateUtils.format(buyerinspectiontimeStartDate,"yyyy-MM-dd")+" 00:00:00";
        String buyerinspectiontimeEnd =  DateUtils.format(buyerinspectiontimeEndDate,"yyyy-MM-dd")+" 23:59:59.999";

        Date start = DateUtils.StrToDate(buyerinspectiontimeStart);
        Date end = DateUtils.StrToDate(buyerinspectiontimeEnd);


//        已用金额 = 消费总金额 - 退款总金额 - 已还款总金额(提前还款)

         //消费总金额
         //BigDecimal xfTotal = buyerCapitalMapper.getTotal(member.getId(),Quantity.STATE_0,Quantity.STATE_4,Quantity.STATE_,start,end);
         BigDecimal xfTotal = buyerCapitalMapper.getTotalByCredit(member.getId(),start,end);
         xfTotal =  xfTotal==null? new BigDecimal(0) : xfTotal;

         //退款总金额
        BigDecimal tkTotal =  buyerCapitalMapper.getTotal(member.getId(),Quantity.STATE_2,Quantity.STATE_4,Quantity.STATE_0,start,end);
        tkTotal = tkTotal == null ? new BigDecimal(0) : tkTotal;

        //已还款总金额（提前还款）
        BigDecimal hkTotal =  buyerCapitalMapper.getTotal1(member.getId(),Quantity.STATE_11,Quantity.STATE_3,start,end);
        hkTotal = hkTotal == null ? new BigDecimal(0) : hkTotal;

        return  xfTotal.subtract(tkTotal).subtract(hkTotal);
       // return  xfTotal.subtract(tkTotal);
    }


    /**
     * 获取用户账单
     * @param queryDto
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageInfo getBillCreateList(BillCreateQueryDto queryDto, int pageNo, int pageSize){
        PageHelper.startPage(pageNo,pageSize);

        List<BillCreate> list = billCreateMapper.list(queryDto);

        PageInfo pageInfo =  new PageInfo(list);

        return pageInfo;
    }

    /**
     * 新增授信申请记录
     * @param creditApplyRecord
     */
    public void insertCreditRecord(CreditApplyRecord creditApplyRecord){
        creditApplyRecordMapper.insertSelective(creditApplyRecord);
    }

    /**
     * 查询用户所有授信记录
     * @return
     */
    public PageInfo getCreditRecordList(long memberid, CreditApplyRecord creditApplyRecord,int pageNo,int pageSize){

        PageHelper.startPage(pageNo,pageSize);
        CreditApplyRecordExample creditApplyRecordExample = new CreditApplyRecordExample();
        CreditApplyRecordExample.Criteria criteria = creditApplyRecordExample.createCriteria();
        criteria.andMemberidEqualTo(memberid);

        if(creditApplyRecord.getId()!=null){
            criteria.andIdEqualTo(creditApplyRecord.getId());
        }
        if(StringUtils.hasText(creditApplyRecord.getContract())){
            criteria.andContractLike(creditApplyRecord.getContract());
        }
        if(StringUtils.hasText(creditApplyRecord.getPhone())){
            criteria.andPhoneLike(creditApplyRecord.getPhone());
        }
        if(creditApplyRecord.getState()!= Quantity.STATE_){
            criteria.andStateEqualTo(creditApplyRecord.getState());
        }

        creditApplyRecordExample.setOrderByClause(" id desc ");

        List<CreditApplyRecord> list = creditApplyRecordMapper.selectByExample(creditApplyRecordExample);

        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 修改授信申请
     * @param creditApplyRecord
     */
    public void updateCreditRecord(CreditApplyRecord creditApplyRecord){
        creditApplyRecordMapper.updateByPrimaryKeySelective(creditApplyRecord);
    }


    public  int countByExample(CreditApplyRecordExample example){
      return   creditApplyRecordMapper.countByExample(example);
    }





}
