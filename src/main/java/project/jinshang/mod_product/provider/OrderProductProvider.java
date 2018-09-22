package project.jinshang.mod_product.provider;

import org.apache.ibatis.jdbc.SQL;
import project.jinshang.mod_product.bean.OrderProduct;
import project.jinshang.mod_product.bean.OrderProductModel;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

/**
 * create : wyh
 * date : 2018/8/8
 */
public class OrderProductProvider {
    public String insertAll(Map map) {
        List<OrderProduct> list = (List<OrderProduct>) map.get("list");
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO orderproduct ");
        sb.append("(orderno,pdid,pdno,pdname,pddesc,price,unit,num,storename,storeid,mailornot,ismailornot,standard,mark,brand,material,gradeno,classify,freight,orderid,actualpayment,pdpic,pic,sellerid,producttype,protype,attrjson,deliverytime,allpay,partpay,yupay,classifyid,limitid) ");
        sb.append("VALUES ");
        MessageFormat mf = new MessageFormat("(#'{'list[{0}].orderno},#'{'list[{0}].pdid},#'{'list[{0}].pdno},#'{'list[{0}].pdname},#'{'list[{0}].pddesc},#'{'list[{0}].price},#'{'list[{0}].unit},#'{'list[{0}].num},#'{'list[{0}].storename},#'{'list[{0}].storeid},#'{'list[{0}].mailornot},#'{'list[{0}].ismailornot},#'{'list[{0}].standard},#'{'list[{0}].mark},#'{'list[{0}].brand},#'{'list[{0}].material},#'{'list[{0}].gradeno},#'{'list[{0}].classify},#'{'list[{0}].freight},#'{'list[{0}].orderid},#'{'list[{0}].actualpayment},#'{'list[{0}].pdpic},#'{'list[{0}].pic},#'{'list[{0}].sellerid},#'{'list[{0}].producttype},#'{'list[{0}].protype},#'{'list[{0}].attrjson},#'{'list[{0}].deliverytime},#'{'list[{0}].allpay},#'{'list[{0}].partpay},#'{'list[{0}].yupay},#'{'list[{0}].classifyid},#'{'list[{0}].limitid})");
        for (int i = 0; i < list.size(); i++) {
            sb.append(mf.format(new Object[]{i}));
            if (i < list.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }


    public String updateOrderProductForModifyProductnum(OrderProductModel orderProductModel){
        SQL sql = new SQL();
        sql.UPDATE("orderproduct");
        sql.SET("num=#{num}");
        if(orderProductModel.getPrice() != null){
            sql.SET("price=#{price}");
        }
        if(orderProductModel.getFreight() != null){
            sql.SET("freight=#{freight}");
        }
        if(orderProductModel.getActualpayment() != null){
            sql.SET("actualpayment = #{actualpayment}");
        }
        sql.WHERE("id=#{id} and num=#{oldProductNum}");
        return sql.toString();
    }


}