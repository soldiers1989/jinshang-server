package project.jinshang.common.utils;





import com.google.gson.JsonParser;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * create : wyh
 * date : 2017/10/28
 */
public class CommonUtils {

    private static final  String MOBILE_REG = "(1)(\\d{10})";

    /**
     * 检测字符串是否是手机号
     * @param mobile
     * @return
     */
    public static boolean isMobile(String mobile){
        if(!StringUtils.hasText(mobile)){
            return  false;
        }

        Pattern pattern = Pattern.compile(MOBILE_REG);
        Matcher m = pattern.matcher(mobile);

        if(m.find()) {
            return true;
        }

        return  false;
    }

    /**
     * 生成一个n位数的随机数字验证码
     * @param n
     * @return
     */
    public static String genVerificationCode(int n){
        return  String.valueOf ((int)((Math.random()*9+1)* Math.pow(10,n) ));
    }


    /**
     * 生成密码，jingshang 统一使用此加密方式
     * @param pwd
     * @param salt
     * @return
     */
    public static String genMd5Password(String pwd,String salt){
        return  MD5Tools.MD5(MD5Tools.MD5(pwd)+salt);
    }


    /**
     * 生成加密盐
     * @return
     */
    public static String genSalt(){
        return UUID.randomUUID().toString().replaceAll("-","").toLowerCase().substring(0,20);
    }



    public static boolean isGoodJson(String json) {
        try {
            new JsonParser().parse(json);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public  static BigDecimal nullToDefault(BigDecimal num){
        return  num == null ? new BigDecimal(0): num;
    }

    public  static  Integer nullToDefault(Integer num){
        return  num == null ? 0 : num;
    }




}
