package project.jinshang.mod_cash.provider;

import org.apache.ibatis.annotations.Param;
import project.jinshang.mod_cash.bean.SalerCapital;
import project.jinshang.mod_cash.bean.dto.SalerCapitalQueryDto;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

/**
 * create : wyh
 * date : 2017/12/11
 */
public class SalerCapitalProvider {

    public  String list(@Param("dto")SalerCapitalQueryDto dto){
        StringBuilder sql = new StringBuilder();

        sql.append("select * from salercapital B where 1=1 ");

        if(dto.getMemberid() != null && dto.getMemberid() >0){
            sql.append(" and B.memberid=#{dto.memberid} ");
        }

        if(dto.getTradetimeStart() != null){
            sql.append(" and B.tradetime >= #{dto.tradetimeStart} ");
        }

        if(dto.getTradetimeEnd() != null){
            sql.append(" and B.tradetime <=#{dto.tradetimeEnd} ");
        }


        if(dto.getCapitaltype() != null && dto.getCapitaltype() != -1){
            //sql.append(" and B.capitaltype=#{dto.capitaltype} ");
            if(dto.getCapitaltype() == 511){ //提现   余额提现、货款提现
                sql.append(" and B.capitaltype in (5,11) ");
            }else {
                sql.append(" and B.capitaltype=#{dto.capitaltype} ");
            }

        }


        if(dto.getTradeno() != null && !dto.getTradeno().equals("")){
            sql.append(" and B.tradeno like '%"+dto.getTradeno()+"%' ");
        }

        if(dto.getOrderno() != null && !dto.getOrderno().equals("")){
            sql.append(" and B.orderno like '%"+dto.getOrderno()+"%' ");
        }


        if(dto.getRechargenumber() != null && !dto.getRechargenumber().equals("")){
            sql.append(" and B.rechargenumber like '%"+dto.getRechargenumber()+"%' ");
        }

        if(dto.getPresentationnumber() != null && !dto.getPresentationnumber().equals("")){
            sql.append(" and B.presentationnumber like '%"+dto.getPresentationnumber()+"%' ");
        }


        if(dto.getPaytype() != null && dto.getPaytype() !=-1){
            sql.append(" and B.paytype = #{dto.paytype} ");
        }

        if(dto.getWithdrawtype() != null && dto.getWithdrawtype() !=-1){
            sql.append(" and B.withdrawtype = #{dto.withdrawtype} ");
        }

        if(dto.getRechargestate() != null && dto.getRechargestate() !=-1){
            sql.append(" and B.rechargestate = #{dto.rechargestate} ");
        }

        if(dto.getRechargeperform() != null && dto.getRechargeperform() != -1){
            sql.append(" and B.rechargeperform=#{dto.rechargeperform} ");
        }

        sql.append(" order by B.id desc ");

        return  sql.toString();
    }




    public  String listForSellerExportExcel(@Param("dto")SalerCapitalQueryDto dto){
        StringBuilder sql = new StringBuilder();

        sql.append("select * from salercapital B where 1=1 ");

        if(dto.getMemberid() != null && dto.getMemberid() >0){
            sql.append(" and B.memberid=#{dto.memberid} ");
        }

        if(dto.getTradetimeStart() != null){
            sql.append(" and B.tradetime >= #{dto.tradetimeStart} ");
        }

        if(dto.getTradetimeEnd() != null){
            sql.append(" and B.tradetime <=#{dto.tradetimeEnd} ");
        }


        if(dto.getCapitaltype() != null && dto.getCapitaltype() != -1){
//            sql.append(" and B.capitaltype=#{dto.capitaltype} ");
            if(dto.getCapitaltype() == 5){ //提现   余额提现、货款提现
                sql.append(" and B.capitaltype in (5,11) ");
            }else {
                sql.append(" and B.capitaltype=#{dto.capitaltype} ");
            }

        }


        if(dto.getTradeno() != null && !dto.getTradeno().equals("")){
            sql.append(" and B.tradeno like '%"+dto.getTradeno()+"%' ");
        }

        if(dto.getOrderno() != null && !dto.getOrderno().equals("")){
            sql.append(" and B.orderno like '%"+dto.getOrderno()+"%' ");
        }


        if(dto.getRechargenumber() != null && !dto.getRechargenumber().equals("")){
            sql.append(" and B.rechargenumber like '%"+dto.getRechargenumber()+"%' ");
        }

        if(dto.getPresentationnumber() != null && !dto.getPresentationnumber().equals("")){
            sql.append(" and B.presentationnumber like '%"+dto.getPresentationnumber()+"%' ");
        }


        if(dto.getPaytype() != null && dto.getPaytype() !=-1){
            sql.append(" and B.paytype = #{dto.paytype} ");
        }

        if(dto.getRechargeperform() != null && dto.getRechargeperform() != -1){
            sql.append(" and B.rechargeperform=#{dto.rechargeperform} ");
        }

        sql.append(" order by B.id desc ");

        return  sql.toString();
    }








    public String insertAll(Map map) {
        List<SalerCapital> list = (List<SalerCapital>) map.get("list");
        for(SalerCapital salerCapital : list){
            if(salerCapital.getOrdercapital()==null){
                salerCapital.setOrdercapital(new BigDecimal(0));
            }
            if(salerCapital.getRefundamount()==null){
                salerCapital.setRefundamount(new BigDecimal(0));
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO salercapital ");
        sb.append("(orderno,tradeno,ordercapital,memberid,rechargestate,capitaltype,tradetime,remark,refundamount,penalty,transactionid) ");
        sb.append("VALUES ");
        MessageFormat mf = new MessageFormat("(#'{'list[{0}].orderno},#'{'list[{0}].tradeno},#'{'list[{0}].ordercapital}," +
                "#'{'list[{0}].memberid},#'{'list[{0}].rechargestate},#'{'list[{0}].capitaltype},#'{'list[{0}].tradetime},#'{'list[{0}].remark}" +
                ",#'{'list[{0}].refundamount},#'{'list[{0}].penalty},#'{'list[{0}].transactionid})");
        for (int i = 0; i < list.size(); i++) {
            sb.append(mf.format(new Object[]{i}));
            if (i < list.size() - 1) {
                sb.append(",");
            }
        }
        System.out.println(sb.toString());
        return sb.toString();
    }
}
