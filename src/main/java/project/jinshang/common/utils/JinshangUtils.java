package project.jinshang.common.utils;

import io.swagger.models.auth.In;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.client.RestTemplate;
import project.jinshang.common.bean.Packing;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.constant.Quantity;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * create : wyh
 * date : 2017/11/28
 */
public class JinshangUtils {


    public final static  List<String> fastenerTopCateNameList =  new ArrayList<>();
    static {
        fastenerTopCateNameList.add("螺栓");
        fastenerTopCateNameList.add("螺母");
        fastenerTopCateNameList.add("螺钉");
        fastenerTopCateNameList.add("垫圈、挡圈");
        fastenerTopCateNameList.add("铆钉");
        fastenerTopCateNameList.add("螺柱、牙条");
        fastenerTopCateNameList.add("开口销");
        fastenerTopCateNameList.add("膨胀系列");
        fastenerTopCateNameList.add("其他螺丝");
    }

    public  static  String getCateType(String topName){
        if(fastenerTopCateNameList.contains(topName)){
            return  "紧固件";
        }
        return  "其他";
    }


    /**
     * 转化为 简单好看的数量
     * @param packagetype
     * @param num
     * @param unit
     * @return
     */
    public  static  String packageToString(String packagetype,BigDecimal num,String unit){

        if(packagetype== null || unit == null){
          return  null;
        }

        Map<String,Object> map = toLowerUnit(packagetype,num,unit);

        Packing basePacking = new Packing((String) map.get("unit"),(BigDecimal) map.get("num"));

        List<Packing> basePackingList = toBasePacking(packagetype);

        StringBuilder sb = new StringBuilder();
        DecimalFormat decimalFormat1 = new DecimalFormat("0");//保留三位小数
        DecimalFormat decimalFormat = new DecimalFormat("0.000");//保留三位小数

        for(int i=basePackingList.size()-1;i>=0;i--){
            Packing packing =  basePackingList.get(i);

            if(basePacking.getNum().compareTo(packing.getNum()) >0 && i !=0){

                BigDecimal[] bigDecimals = basePacking.getNum().divideAndRemainder(packing.getNum());

                sb.append(decimalFormat1.format(bigDecimals[0])+packing.getUnit());
                if(bigDecimals[1].compareTo(new BigDecimal(0)) ==0){
                    break;
                }else{
                    basePacking.setNum(bigDecimals[1]);
                }
            }

            if(i==0 && basePacking.getNum().compareTo(new BigDecimal(0))>0){
                String minNumStr =  decimalFormat.format(basePacking.getNum());
                if(!minNumStr.equals("0.000")) {
                    sb.append(minNumStr + "" + basePacking.getUnit());
                }
            }

        }

       return sb.toString();
    }


    public static void main(String[] args) {

//        System.out.println(basePackingList);




    }



    public  static  List<Packing> toBasePacking(String packages){
        List<Packing> list = toCovertPacking(packages);
        List<Packing> baseList = new ArrayList<>();

        for(int i=0;i<list.size();i++){
            Packing packing = list.get(i);
            if(i==0){
                baseList.add(new Packing(packing.getUnit(),new BigDecimal(0)));
            }else{
                BigDecimal aa = new BigDecimal(1);
                for(int j=0;j<i;j++){
                    aa = aa.multiply(list.get(j).getNum());
                }
                baseList.add(new Packing(packing.getUnit(),aa));
            }
        }
        return  baseList;

    }



    /**
     * 转换包装方式
     * @param packages
     * @return
     */
    public static List<Packing> toCovertPacking(String packages){



        String[] aaArray = packages.split("\\|");
        List<Packing> list = new ArrayList<>();
        for(int j=0;j<aaArray.length;j++){
            String a = aaArray[j];
            String[] ssArray = a.split("\\/");
            for(int i=0;i<ssArray.length;i++){
                Pattern pattern =  Pattern.compile("[0-9\\.]+");
                Matcher matcher = pattern.matcher(ssArray[i]);
                if(matcher.find()){
                    Packing units = new Packing();
                    units.setNum(new BigDecimal(matcher.group()));
                    units.setUnit(ssArray[i].replace(matcher.group(),"").trim());
                    list.add(units);
                }else if(j== (aaArray.length-1)){
                    Packing units = new Packing();
                    units.setNum(new BigDecimal(0));
                    units.setUnit(ssArray[i].trim());
                    list.add(units);
                }
            }
        }

        return  list;
    }


    /**
     * 转化为基础单位
     * @param packages 包装方式
     * @param num   数量
     * @param unit  单位
     * @return
     */
    public static Map<String,Object> toLowerUnit(String packages,BigDecimal num,String unit){

        Map map = new HashMap<String,Object>();

        List<Packing> packingList =  toCovertPacking(packages);
        Collections.reverse(packingList);

        String baseUnit = "";

        for(int i=0;i<packingList.size();i++){
            Packing packing = packingList.get(i);
            if(packing.getUnit().equals(unit) && (i+1)<packingList.size()){
                Packing nextPacking = packingList.get(i+1);
                unit = nextPacking.getUnit();
                num = num.multiply(nextPacking.getNum());
            }
        }

        map.put("unit",unit);
        map.put("num",num);

        return  map;
    }

    /**
     * 紧固件上架保证金
      * @param productPrice 商品单价
     * @param storeNum  数量
     * @param spotalesmargin  比率
     * @return
     */
   public static BigDecimal productBill(BigDecimal productPrice,BigDecimal storeNum,BigDecimal spotalesmargin){
      return  productPrice.multiply(storeNum).multiply(spotalesmargin.divide(new BigDecimal(100)));
   }


    /**
     *
     */
    public  static void  addViewCookie(HttpServletRequest request,HttpServletResponse response,Long id){
        Cookie[] cookies = request.getCookies();
        if(CookieUtils.exisCookie(cookies, AppConstant.PRODUCT_VIEW_COOKIE_ID)){
             String value = CookieUtils.getValue(cookies,AppConstant.PRODUCT_VIEW_COOKIE_ID);
             String[] valueArr =  value.split("#");
             List<String>  valueList =  Arrays.asList(valueArr);


             List<String> tempList = new ArrayList<>();
             tempList.addAll(valueList);

             tempList.remove(String.valueOf(id));
             tempList.remove("");
             if(tempList.size()>5) {
                 tempList =  tempList.subList(0, 4);
             }

             tempList.add(String.valueOf(id));
             StringBuilder valuesb = new StringBuilder();
             for(int i =  tempList.size()-1;i>=0;i--){
                 valuesb.append(tempList.get(i));
                 if(i != 0){
                     valuesb.append("#");
                 }
             }

            Cookie cookie =  new Cookie(AppConstant.PRODUCT_VIEW_COOKIE_ID,valuesb.toString());
            cookie.setPath("/");
            cookie.setValue(valuesb.toString());
            cookie.setMaxAge(365*24*3600);
            response.addCookie(cookie);

        }else{
            Cookie cookie =  new Cookie(AppConstant.PRODUCT_VIEW_COOKIE_ID,String.valueOf(id));
            cookie.setPath("/");
            cookie.setMaxAge(365*24*3600);
            response.addCookie(cookie);
        }
   }

    //商品状态0=放入仓库1=待审核2=审核通过3=未通过4=已上架5=下架6=删除7=违规下架
    public  static  String prodState(int state){
        switch (state){
            case 0:
                return  "放入仓库";
            case 1:
                return  "待审核";
            case 2:
                return "审核通过";
            case 3:
                return  "审核未通过";
            case 4:
                return  "已上架";
            case 5:
                return  "下架";
            case 6:
                return  "删除";
            case 7:
                return  "违规下架";
        }
        return null;
    }


    /**
     * 限时购活动状态
     * @param state  状态 0=待审核，1=审核通过，2=审核未通过,3=活动终止，4=活动中，5=活动结束,6=用户取消,7=删除
     * @return
     */
    public  static  String limitProdState(int state){
        switch (state){
            case 0:
                return  "待审核";
            case 1:
                return  "审核通过";
            case 2:
                return "审核未通过";
            case 3:
                return  "活动终止";
            case 4:
                return  "活动中";
            case 5:
                return  "活动结束";
            case 6:
                return  "用户取消";
            case 7:
                return  "删除";
        }
        return null;
    }


    /**
     * 买家资金明细类型
     * @param state
     * @return 类别0=消费1=充值2=退款3=提现4=授信5=授信还款6=违约金7=远期定金8=远期余款9=远期全款10=卖家违约金11=授信未出账单还款
     */
    public  static  String buyerCapitalType(Short state){
        if(state == null) return  "";

        switch (state){
            case 0:
                return  "消费";
            case 1:
                return  "充值";
            case 2:
                return "退款";
            case 3:
                return  "提现";
            case 4:
                return  "授信";
            case 5:
                return  "授信还款";
            case 6:
                return  "违约金";
            case 7:
                return  "远期定金";
            case 8:
                return "远期余款";
            case 9:
                return "远期全款";
            case  10:
                return "卖家违约金";
            case 11:
                return  "授信未出账单还款";
        }
        return null;
    }







    /**
     * 买家资金明细状态
     * @param state
     * @return 状态0=待处理1=成功2=失败3=待审核4=审核通过5=审核不通过
     */
    public  static  String buyerCapitalState(Short state){
        if(state == null) return  "";
        switch (state){
            case 0:
                return  "待处理";
            case 1:
                return  "成功";
            case 2:
                return "失败";
            case 3:
                return  "待审核";
            case 4:
                return  "审核通过";
            case 5:
                return  "审核不通过";
        }
        return null;
    }



    /**
     * 买家资金明细支付方式
     * @param type
     * @return 支付方式0=支付宝1=微信2=银行卡3=余额4=授信
     */
    public  static  String buyerCapitalPaytype(Short type){

        if(type ==null) return "";

        switch (type){
            case 0:
                return  "支付宝";
            case 1:
                return  "微信";
            case 2:
                return "银行卡";
            case 3:
                return  "余额";
            case 4:
                return  "授信";
        }
        return null;
    }


    /**
     * 充值平台
     * @param type
     * @return 充值平台0=微信1=支付宝2=线下平台3=银行卡
     */
    public  static  String buyerCapitalrechargeperform(Short type){

        if(type ==null) return "";

        switch (type){
            case 0:
                return  "微信";
            case 1:
                return  "支付宝";
            case 2:
                return "线下平台";
            case 3:
                return  "银行卡";
        }
        return null;
    }



    /**
     * 卖家资金明细类型
     * @param state
     * @return 资金类型0=订单金额1=上架保证金2=下架保证金3=退款金额4=充值5=余额提现6=买家违约金7=卖家违约金8=余款9=全款10=定金 11=货款提现
     */
    public  static  String sellerCapitalType(Short state){

        if(state == null) return  "";

        switch (state){
            case 0:
                return  "订单金额";
            case 1:
                return  "上架保证金";
            case 2:
                return "下架保证金";
            case 3:
                return  "退款金额";
            case 4:
                return  "充值";
            case 5:
                return  "余额提现";
            case 6:
                return  "买家违约金";
            case 7:
                return  "卖家违约金";
            case 8:
                return "远期余款";
            case 9:
                return "远期全款";
            case  10:
                return "远期定金";
            case 11:
                return  "货款提现";
        }
        return null;
    }


    //状态0=待审核1=待受理2=受理中3=待复核4=已开通5=已拒绝6=已撤消
    public  static  String creditstate(Short state){
        switch (state){
            case 0:
                return  "待审核";
            case  1:
                return  "待受理";
            case  2:
                return  "受理中";
            case  3:
                return  "待复核";
            case  4:
                return  "已开通";
            case 5:
                return  "已拒绝";
            case 6:
                return  "已撤消";
        }

        return  null;
    }



    /**
     * 卖家资金明细状态
     * @param state
     * @return 状态0=待处理1=成功2=失败3=待审核4=审核通过5=审核不通过
     */
    public  static  String sellerCapitalState(Short state){
        if(state == null) return  "";
        switch (state){
            case 0:
                return  "待处理";
            case 1:
                return  "成功";
            case 2:
                return "失败";
            case 3:
                return  "待审核";
            case 4:
                return  "审核通过";
            case 5:
                return  "审核不通过";
        }
        return null;
    }


    /**
     * 订单状态0=待付款1=待发货3=待收货4=待验货5=已完成7=已关闭8=备货中9=备货完成
     * @param state
     * @return
     */
    public  static  String orderState(Short state){
        switch (state){
            case 0:
                return  "待付款";
            case  1:
                return  "待发货";
            case  3:
                return  "待收货";
            case  4:
                return  "待验货";
            case 5:
                return  "已完成";
            case 7:
                return  "已关闭";
            case 8:
                return  "备货中";
            case 9:
                return  "备货完成";
        }

        return  null;
    }




    //商品类型
    public  static  Short productType(String type){
        if("紧固件".equals(type)){
            return Quantity.STATE_0;
        }else if("工业品".equals(type)){
            return  Quantity.STATE_1;
        }else if("生活类".equals(type)){
            return  Quantity.STATE_2;
        }

        return  null;
    }


    /**
     * 退货状态
     * @param state
     * @return
     */
    public static String orderProductBackStateMess(final short state){

        switch (state){
            case 1:
                return "卖家同意待收货";
            case 2:
                return "卖家同意直接退款";
            case 3:
                return "卖家收到货同意退款";
            case 4:
                return  "卖家不同意退货";
            case 5:
                return "买家同意验货";
            case 6:
                return "买家申请异议";
            case 7:
                return "平台同意退货不扣违约金";
            case 8:
                return "平台同意退货扣违约金";
            case 9:
                return "平台转入待验收";
            case 10:
                return "退货成功";
            case 11:
                return  "退货撤消";
            case 12:
                return "卖家待收货";
        }

        return  null;
    }




    public  static String fastenSortType(Integer sorttype){
       String type = "";
       if(sorttype == 0){
           type = " stand asc ";
       }else if(sorttype == 1){
           type =  " stand desc ";
       }
       return  type;
   }



    public  static String otherProdSortType(int sorttype){
        String type = "";
        if(sorttype == 1){
            type = " p.minprice asc ";
        }else if(sorttype == 2){
            type =  " p.minprice desc ";
        }
        return  type;
    }


}
