package project.jinshang.common.utils;

import com.shcm.send.OpenApi;

import java.io.UnsupportedEncodingException;

/**
 * create : wyh
 * date : 2017/10/31
 */
public class SmsSend {
    private static String sOpenUrl = "http://smsapi.c123.cn/OpenPlatform/OpenApi";

    // 接口帐号
    private static final String account = "1001@501132200009";

    // 接口密钥
    private static final String authkey = "92AE26B45EDA09A56CB1CFFF3BC4C702";

    // 通道组编号
    private static final int cgid = 7359;

    // 默认使用的签名编号(未指定签名编号时传此值到服务器)
    private static final int csid = 0;


    public static boolean send(String mobile,String content) throws UnsupportedEncodingException, InterruptedException {
        // 发送参数
        OpenApi.initialzeAccount(sOpenUrl, account, authkey, cgid, csid);

        // 取帐户余额
//        double dReamin = OpenApi.getBalance();
//        System.out.println("可用余额: " + dReamin);

        // 发送短信
        content = new String(content.getBytes("utf-8"),"utf-8");
        int nRet = OpenApi.sendOnce(mobile, content, cgid, 0, null);
        System.out.println(nRet);
        return nRet == 1;

    }


    public static void main(String[] args) {
        try {
            send("18663868251","fdsafasf");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
