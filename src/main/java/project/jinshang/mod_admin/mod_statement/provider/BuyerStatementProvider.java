package project.jinshang.mod_admin.mod_statement.provider;

import org.apache.ibatis.annotations.Param;
import project.jinshang.mod_admin.mod_statement.bean.BuyerStatement;
import project.jinshang.mod_admin.mod_statement.bean.dto.BuyerStamentQueryDto;
import project.jinshang.mod_cash.bean.dto.BuyerCapitalAccountQueryDto;

/**
 * sql provider
 *
 * @author xiazy
 * @create 2018-09-13 17:30
 **/
public class BuyerStatementProvider {


    public String listForBuyerStatement(@Param("dto")BuyerStamentQueryDto dto){
        StringBuilder sql=new StringBuilder();
        sql.append("SELECT B.*,BG.invoiceheadup,M.username,M.realname,BC.companyname FROM buyerstatement B\n"+
                "\tLEFT JOIN member M ON B.memberid = M.id LEFT JOIN buyercompanyinfo BC ON B.memberid = BC.memberid\n"+
                "\tLEFT JOIN billingrecord BG ON B.billrecoid=BG.id\n"+
                "WHERE 1=1");
        if (dto.getStatementStartTime()!=null){
            sql.append(" and B.createtime >= #{dto.statementStartTime} ");
        }
        if (dto.getStatementEndTime()!=null){
            sql.append(" and B.createtime <= #{dto.statementEndTime} ");
        }
        if (dto.getMemberid()!=null && dto.getMemberid()>0){
            sql.append(" and B.memberid = #{dto.memberid} ");
        }
        if (dto.getCompanyname()!=null && !"".equals(dto.getCompanyname())){
//            dto.setCompanyname("%"+dto.getCompanyname()+"%");
            sql.append(" and BC.companyname = #{dto.companyname} ");
        }
        if (dto.getInvoiceheadup()!=null && !"".equals(dto.getInvoiceheadup())){
            dto.setInvoiceheadup("%"+dto.getInvoiceheadup()+"%");
            sql.append(" and BG.invoiceheadup like #{dto.invoiceheadup} ");
        }
        if (dto.getRealname()!=null && !"".equals(dto.getRealname())){
//            dto.setRealname("%"+dto.getRealname()+"%");
            sql.append(" and M.realname = #{dto.realname} ");
        }
        if (dto.getUsername()!=null && !"".equals(dto.getUsername())){
//            dto.setUsername("%"+dto.getUsername()+"%");
            sql.append(" and M.username = #{dto.username} ");
        }
        sql.append(" order by B.id ASC ");
        return sql.toString();
    }



     public  String insertBySelective(@Param("bs")BuyerStatement bs){
        StringBuilder sql=new StringBuilder();
        sql.append("INSERT INTO buyerstatement ( memberid, contractno, type, deliveryamount, receiptamount, invoiceamount, receivableamount,invoicebalance,paytype, createtime ");
        if (bs.getBillrecoid()!=null){
            sql.append(",billrecoid ");
        }
        if (bs.getPayno()!=null){
            sql.append(",payno ");
        }
        if (bs.getRemark()!=null){
            sql.append(",remark ");
        }
        sql.append(" )");
        sql.append("values( #{bs.memberid},#{bs.contractno},#{bs.type},#{bs.deliveryamount},#{bs.receiptamount},#{bs.invoiceamount}, ");
        sql.append("( SELECT receivableamount FROM buyerstatement WHERE memberid = #{bs.memberid} ORDER BY id DESC LIMIT 1 )+#{bs.deliveryamount}-#{bs.receiptamount}, ");
        sql.append("( SELECT invoicebalance   FROM buyerstatement WHERE memberid = #{bs.memberid} ORDER BY id DESC LIMIT 1 )+#{bs.deliveryamount}-#{bs.invoiceamount}, ");
        sql.append("#{bs.paytype},#{bs.createtime} ");
         if (bs.getBillrecoid()!=null){
             sql.append(",#{bs.billrecoid} ");
         }
         if (bs.getPayno()!=null){
             sql.append(",#{bs.payno} ");
         }
         if (bs.getRemark()!=null){
             sql.append(",#{bs.remark} ");
         }
         sql.append(" )");
         return sql.toString();
     }

}
