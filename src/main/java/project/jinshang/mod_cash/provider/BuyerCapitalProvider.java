package project.jinshang.mod_cash.provider;

import org.apache.ibatis.annotations.Param;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_cash.bean.BuyerCapital;
import project.jinshang.mod_cash.bean.dto.BuyerCapitalAccountQueryDto;
import project.jinshang.mod_cash.bean.dto.BuyerCapitalQueryDto;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

/**
 * create : wyh
 * date : 2017/12/11
 */
public class BuyerCapitalProvider {


    public String insertAll(Map map) {
        List<BuyerCapital> list = (List<BuyerCapital>) map.get("list");
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO buyercapital ");
        sb.append("(orderno,tradeno,capital,paytype,memberid,rechargestate,tradetime,capitaltype,remark,transactionid) ");
        sb.append("VALUES ");
        MessageFormat mf = new MessageFormat("(#'{'list[{0}].orderno},#'{'list[{0}].tradeno},#'{'list[{0}].capital},#'{'list[{0}].paytype},#'{'list[{0}].memberid},#'{'list[{0}].rechargestate},#'{'list[{0}].tradetime},#'{'list[{0}].capitaltype},#'{'list[{0}].remark},#'{'list[{0}].transactionid})");
        for (int i = 0; i < list.size(); i++) {
            sb.append(mf.format(new Object[]{i}));
            if (i < list.size() - 1) {
                sb.append(",");
            }
        }
//        System.out.println(sb.toString());
        return sb.toString();
    }


    public  String list(@Param("dto")BuyerCapitalQueryDto dto){
        StringBuilder sql = new StringBuilder();

        sql.append("select * from buyercapital B where 1=1 ");

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
            sql.append(" and B.capitaltype=#{dto.capitaltype} ");
        }


        if(dto.getTradeno() != null && !dto.getTradeno().equals("")){
            dto.setTradeno("%"+dto.getTradeno()+"%");
            sql.append(" and B.tradeno like  #{dto.tradeno}");
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


        if(dto.getOrderno() != null && !dto.getOrderno().equals("")){
            dto.setOrderno("%"+dto.getOrderno()+"%");
            sql.append(" and B.orderno like #{orderno} ");
        }


        if(dto.getPresentationnumber() != null && !dto.getPresentationnumber().equals("")){
            dto.setPresentationnumber("%"+dto.getPresentationnumber()+"%");
            sql.append(" and B.presentationnumber like #{dto.presentationnumber} ");
        }


        if(dto.getBillcreateid() != null && dto.getBillcreateid()>0){
            sql.append(" and B.billcreateid=#{dto.billcreateid} ");
        }


        sql.append(" order by B.id desc ");

        return  sql.toString();
    }

    public String listForAccount(@Param("dto")BuyerCapitalAccountQueryDto dto){
        StringBuilder sql=new StringBuilder();
        sql.append("SELECT B.*,O.deliveryno,BG.invoiceheadup FROM buyercapital B\n"+
                "\tLEFT JOIN member M ON B.memberid = M.id LEFT JOIN buyercompanyinfo BC ON B.memberid = BC.memberid\n"+
                "\tLEFT JOIN orders O ON O.orderno = B.orderno LEFT JOIN billingrecord BG ON (BG.orderno LIKE concat( '', O.id, '' )\n"+
                 "\tOR BG.orderno LIKE concat( '', o.id, ',%' ) OR BG.orderno LIKE concat( '%,', o.id, ',%' ) )\n"+
                "WHERE 1=1");
        if (dto.getTradetimeStart()!=null){
            sql.append(" and B.tradetime >= #{dto.tradetimeStart} ");
        }
        if (dto.getTradetimeEnd()!=null){
            sql.append(" and B.tradetime <= #{dto.tradetimeEnd} ");
        }
        if (dto.getMemberid()!=null && dto.getMemberid()>0){
            sql.append(" and B.memberid = #{dto.memberid} ");
        }
        if (dto.getCompanyname()!=null && !"".equals(dto.getCompanyname())){
            dto.setCompanyname("%"+dto.getCompanyname()+"%");
            sql.append(" and BC.companyname like #{dto.companyname} ");
        }
        if (dto.getInvoicename()!=null && !"".equals(dto.getInvoicename())){
            sql.append(" and BG.invoiceheadup = #{dto.invoicename} ");
        }
        if (dto.getRealname()!=null && !"".equals(dto.getRealname())){
            sql.append(" and M.realname= #{dto.realname} ");
        }
        if (dto.getMobile()!=null && !"".equals(dto.getMobile())){
            sql.append(" and M.mobile= #{dto.mobile} ");
        }
        sql.append(" order by B.tradetime ASC ");
        return sql.toString();
    }
    public  String listForPurchaserExportExcel(@Param("dto")BuyerCapitalQueryDto dto){
        StringBuilder sql = new StringBuilder();

        sql.append("select OS.createtime,OS.code,OS.orderno,OS.transactionnumber,M.realname,M.username,C.companyname,OS.shopname,OS.sellername,B.capitaltype,OS.isonline,B.capital,\n" +
                "OS.isbilling,OS.orderstatus,OS.shipto,OS.province,OS.city,OS.area,OS.receivingaddress,OS.paytype,OS.logisticscompany,OS.couriernumber,B.operation,OS.transactionid,OS.uuid,B.memberid\n" +
                "from buyercapital B \n" +
                "left join buyercompanyinfo C on B.memberid=C.memberid \n" +
                "left join orders OS on B.orderno=OS.orderno \n" +
                "left join member M on C.memberid=M.id  where 1=1 ");

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
            sql.append(" and B.capitaltype=#{dto.capitaltype} ");
        }


        if(dto.getTradeno() != null && !dto.getTradeno().equals("")){
            dto.setTradeno("%"+dto.getTradeno()+"%");
            sql.append(" and B.tradeno like  #{dto.tradeno}");
        }

        //买家模糊搜索
        if(dto.getRealname() != null && !dto.getRealname().equals("")){
            dto.setRealname("%"+dto.getRealname()+"%");
            sql.append(" and (M.realname like  #{dto.realname} or M.username like  #{dto.realname})");
        }

        //卖家模糊搜索
        if(dto.getShopname() != null && !dto.getShopname().equals("")){
            dto.setShopname("%"+dto.getShopname()+"%");
            sql.append(" and (OS.membercompany like  #{dto.shopname} or OS.shopname like  #{dto.shopname} or OS.sellername like  #{dto.shopname})");
        }

        if(dto.getPaytype() != null && dto.getPaytype() !=-1){
            sql.append(" and B.paytype = #{dto.paytype} ");
        }


        if(dto.getRechargeperform() != null && dto.getRechargeperform() != -1){
            sql.append(" and B.rechargeperform=#{dto.rechargeperform} ");
        }


        if(dto.getOrderno() != null && !dto.getOrderno().equals("")){
            dto.setOrderno("%"+dto.getOrderno()+"%");
            sql.append(" and B.orderno like #{dto.orderno} ");
        }


        if(dto.getPresentationnumber() != null && !dto.getPresentationnumber().equals("")){
            dto.setPresentationnumber("%"+dto.getPresentationnumber()+"%");
            sql.append(" and B.presentationnumber like #{dto.presentationnumber} ");
        }


        if(dto.getBillcreateid() != null && dto.getBillcreateid()>0){
            sql.append(" and B.billcreateid=#{dto.billcreateid} ");
        }


        sql.append(" order by B.id desc ");

        return  sql.toString();
    }

    public  String ExcelExportUserCapitalManagement(@Param("dto")BuyerCapitalQueryDto dto){
        StringBuilder sql = new StringBuilder();

        sql.append("select bc.orderno,bc.rechargeperform,bc.tradetime,m.realname buyerrealname,m.username buyerusername,bci.companyname buyercompanyname," +
                "bc.capital,o.isonline,mm.realname salerealname,mm.username saleusername,bcii.companyname salecompanyname,bc.rechargenumber,o.uuid,bc.capitaltype \n" +
                "from buyercapital bc \n" +
                "left join member m on bc.memberid=m.id \n" +
                "left join buyercompanyinfo bci on bc.memberid=bci.memberid \n" +
                "left join orders o on bc.orderno=o.orderno \n" +
                "left join member mm on o.saleid=mm.id \n" +
                "left join buyercompanyinfo bcii on o.saleid=bcii.memberid where (bc.rechargeperform=0 or bc.rechargeperform=1) ");

        if(dto.getMemberid() != null && dto.getMemberid() >0){
            sql.append(" and bc.memberid=#{dto.memberid} ");
        }

        if(dto.getTradetimeStart() != null){
            sql.append(" and bc.tradetime >= #{dto.tradetimeStart} ");
        }

        if(dto.getTradetimeEnd() != null){
            sql.append(" and bc.tradetime <=#{dto.tradetimeEnd} ");
        }



        if(dto.getCapitaltype() != null && dto.getCapitaltype() != -1){
            sql.append(" and bc.capitaltype=#{dto.capitaltype} ");
        }


        if(dto.getTradeno() != null && !dto.getTradeno().equals("")){
            dto.setTradeno("%"+dto.getTradeno()+"%");
            sql.append(" and bc.tradeno like  #{dto.tradeno}");
        }

        //买家模糊搜索
        if(dto.getRealname() != null && !dto.getRealname().equals("")){
            dto.setRealname("%"+dto.getRealname()+"%");
            sql.append(" and (m.realname like  #{dto.realname} or m.username like  #{dto.realname} or bci.companyname like  #{dto.realname})");
        }

        //卖家模糊搜索
        if(dto.getShopname() != null && !dto.getShopname().equals("")){
            dto.setShopname("%"+dto.getShopname()+"%");
            sql.append(" and (mm.realname like  #{dto.shopname} or mm.username like  #{dto.shopname} or bcii.companyname like  #{dto.shopname})");
        }

        if(dto.getPaytype() != null && dto.getPaytype() !=-1){
            sql.append(" and bc.paytype = #{dto.paytype} ");
        }


        if(dto.getRechargeperform() != null && dto.getRechargeperform() != -1){
            sql.append(" and bc.rechargeperform=#{dto.rechargeperform} ");
        }


        if(dto.getOrderno() != null && !dto.getOrderno().equals("")){
            dto.setOrderno("%"+dto.getOrderno()+"%");
            sql.append(" and bc.orderno like #{dto.orderno} ");
        }


        if(dto.getPresentationnumber() != null && !dto.getPresentationnumber().equals("")){
            dto.setPresentationnumber("%"+dto.getPresentationnumber()+"%");
            sql.append(" and bc.presentationnumber like #{dto.presentationnumber} ");
        }


        if(dto.getBillcreateid() != null && dto.getBillcreateid()>0){
            sql.append(" and bc.billcreateid=#{dto.billcreateid} ");
        }


        sql.append(" order by bc.id desc ");

        return  sql.toString();
    }

    public  String breakContractListLogs(Map<String,Object> queryMap){
        StringBuilder sql =  new StringBuilder("select B.*,M.username,M.realname,MM.username as buyerusername,S.companyname from salercapital B left join member M on B.memberid=M.id " +
                "left join sellercompanyinfo S on B.memberid=S.memberid left join member MM on B.buyerid=MM.id where 1=1  ");


        if(queryMap.containsKey("memberid") && queryMap.get("memberid") != null){
            sql.append(" and B.memberid=#{memberid}");
        }

        if(queryMap.containsKey("flag") &&
                ((Short)queryMap.get("flag") == Quantity.STATE_6 || (Short) queryMap.get("flag") == Quantity.STATE_10)){
            sql.append(" and  B.capitaltype= #{flag}");
        }else{
            sql.append(" and B.capitaltype in (6,10) ");
        }

        if(queryMap.containsKey("sellername") && StringUtils.hasText((String) queryMap.get("sellername"))){
            sql.append(" and  S.companyname like '%"+queryMap.get("sellername")+"%' ");
        }

        if(queryMap.containsKey("buyername") && StringUtils.hasText((String) queryMap.get("buyername"))){
            sql.append(" and  M.realname like '%"+queryMap.get("buyername")+"%' ");
        }

        if(queryMap.containsKey("tradeno") && StringUtils.hasText((String) queryMap.get("tradeno"))){
            sql.append(" and B.tradeno=#{tradeno} ");
        }

        if(queryMap.containsKey("capital") && queryMap.get("capital") != null){
            sql.append(" and B.capital=#{capital} ");
        }

        if(queryMap.containsKey("tradetimeStart") && queryMap.get("tradetimeStart") != null){
            sql.append(" and B.tradetime>#{tradetimeStart} ");
        }

        if(queryMap.containsKey("tradetimeEnd") && queryMap.get("tradetimeEnd") != null){
            sql.append(" and B.tradetime < #{tradetimeEnd} ");
        }

        return  sql.toString()+" order by id desc ";

    }




    public  String breakContractListLogsForAdminExcel(Map<String,Object> queryMap){
        StringBuilder sql =  new StringBuilder("select B.*,M.username,M.realname,S.companyname,MM.username as buyerusername,MM.realname as buyerrealname,OS.actualpayment from salercapital B left join member M on B.memberid=M.id "+
                "left join sellercompanyinfo S on B.memberid=S.memberid left join orders OS on B.orderno=OS.orderno left join member MM on OS.memberid=MM.id where 1=1  ");

        if(queryMap.containsKey("flag") &&
                ((Short)queryMap.get("flag") == Quantity.STATE_6 || (Short) queryMap.get("flag") == Quantity.STATE_10)){
            sql.append(" and  B.capitaltype= #{flag}");
        }else{
            sql.append(" and B.capitaltype in (6,10) ");
        }

        if(queryMap.containsKey("sellername") && StringUtils.hasText((String) queryMap.get("sellername"))){
            sql.append(" and  S.companyname like '%"+queryMap.get("sellername")+"%' ");
        }

        if(queryMap.containsKey("buyername") && StringUtils.hasText((String) queryMap.get("buyername"))){
            sql.append(" and  M.realname like '%"+queryMap.get("buyername")+"%' ");
        }

        if(queryMap.containsKey("tradeno") && StringUtils.hasText((String) queryMap.get("tradeno"))){
            sql.append(" and B.tradeno=#{tradeno} ");
        }

        if(queryMap.containsKey("capital") && queryMap.get("capital") != null){
            sql.append(" and B.capital=#{capital} ");
        }

        if(queryMap.containsKey("tradetimeStart") && queryMap.get("tradetimeStart") != null){
            sql.append(" and B.tradetime>#{tradetimeStart} ");
        }

        if(queryMap.containsKey("tradetimeEnd") && queryMap.get("tradetimeEnd") != null){
            sql.append(" and B.tradetime < #{tradetimeEnd} ");
        }

        return  sql.toString()+" order by id desc ";

    }




    public  String breakContractListLogsForBuyerExcel(Map<String,Object> queryMap){
        StringBuilder sql =  new StringBuilder("select B.*,M.username,M.realname,MM.username as buyerusername,S.companyname from salercapital B left join member M on B.memberid=M.id " +
                " left join sellercompanyinfo S on B.memberid=S.memberid left join member MM on B.buyerid=MM.id where 1=1  ");

        if(queryMap.containsKey("memberid") && queryMap.get("memberid") != null){
            sql.append(" and B.memberid=#{memberid}");
        }

        if(queryMap.containsKey("flag") &&
                ((Short)queryMap.get("flag") == Quantity.STATE_6 || (Short) queryMap.get("flag") == Quantity.STATE_10)){
            sql.append(" and  B.capitaltype= #{flag}");
        }else{
            sql.append(" and B.capitaltype in (6,10) ");
        }

        if(queryMap.containsKey("sellername") && StringUtils.hasText((String) queryMap.get("sellername"))){
            sql.append(" and  S.companyname like '#{sellername}' ");
        }

        if(queryMap.containsKey("buyername") && StringUtils.hasText((String) queryMap.get("buyername"))){
            sql.append(" and  M.realname like '#{realname}' ");
        }

        if(queryMap.containsKey("tradeno") && StringUtils.hasText((String) queryMap.get("tradeno"))){
            sql.append(" and B.tradeno=#{tradeno} ");
        }

        if(queryMap.containsKey("capital") && queryMap.get("capital") != null){
            sql.append(" and B.capital=#{capital} ");
        }

        if(queryMap.containsKey("tradetimeStart") && queryMap.get("tradetimeStart") != null){
            sql.append(" and B.tradetime>#{tradetimeStart} ");
        }

        if(queryMap.containsKey("tradetimeEnd") && queryMap.get("tradetimeEnd") != null){
            sql.append(" and B.tradetime < #{tradetimeEnd} ");
        }

        return  sql.toString()+" order by id desc ";

    }





}
