package project.jinshang.mod_pay.mod_alipay;

import mizuki.project.core.restserver.config.WebConfBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */
@Component
public class AlipayConfig {
    @Autowired
    private WebConfBean webConfBean;

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public String app_id = "2018012001995610";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public String merchant_private_key = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDJiVJaerNlo75a2M6IYKcRuHqSsOoq+t+ARJkKghA0PG12ibkZ8DMDgxACJOSXu37CAysRgPXXqeNkmz/ZPe6xML79DnPIOo2Cbh34/wN8Wq7fKtL4d7YkO5Ifwl3Fm/wm0vNuTPOevKiKwGea5zDj9QVSTBxC8iALFCOdCzSleYdm1/+EhRQtmb2cIcQR6AWMQBbQG/+dbPuJLoGfX+/mJl+CrSmdSTt1IETxaBnxcsj4xdK6iRdDrCIHdppda9mXdjXNhXPKTE/v5pmttloSrbCyj5hCKTzxEvtYHuBsu9b2/2bTAC+UsKu/zK8sK6hyHZgQhfD2zs/Js7r/dreLAgMBAAECggEAW7HpEFWnbeU0ZK0kOxSOrxdaXYe0411Fd1y+rPNEYB/mRfqfh1esPjrRe8hBYW/jWm6Kl+ou8LLRTSL9x9/PzDq7ivOaZk2NuPxVEsckVN4FlnArkPUWoCGieGS8uX4hGaD9NQA6D80Seh7UhLwPmIgQpcMPUL5NKf09vuQsTeTQljsQv0nduWpbIIDpHSURJESfdc5OzsRWEzEy22PGVa/5fhHxjRUUyMJ9AlZEKTnnl9NlBa9kZ2bRZT6yD+ZWDfWKxwmWD6yREfCdZ+NgKXq/CRQnIDUZVnAAziEZau6poSiW+Rs9yIGkEh8qYYZ6ATerS08nMxwl/YGekK0GEQKBgQDqMWuGJ/sBEdjtJO5Hs7uErkzGrFxjGyebk2M/KDHumYKDC/JsuVs69R8/w+TRtJ8Lv58/2UrIl2Yz+aWUVLGZZmtGAUvGNLQ9Q9VJlI4zFiqyso4bLO8Sbp+8NMKun0TJRJF6mrexQrpE8xXjq9u8MKXf2seKDhZO+EHTkYobHQKBgQDcTXLe0ieBXldLfL85sWqgAODA8sd8zWIyjB0c0m/xhiR+texHanUwaHOsAKbSUApr1fakha9XmE9mv0shn10mnyVFPB/gxuc9xD8y8Bg8B81jlY24mAzFMD3rpwJ/e7JkHo1g6lwuRZq9GjIGlIPRMnaQkj09FCJaGdLIART0xwKBgQDMSW/SBu4dcMQZmS2BvnbEtoaih5fPm9BhFaBtZ1KnH6GJTtKZpNbABzLLHMZtZ3GVfgIcYB+r+uVcUVjumzKQ7ZaF6nt+2lqfVpynIr8oMR3t43OyoO+q1HTiZ+OrD6OhLf75RB0Ys2xo2OMvRqrW7ljDfMSy2Lb9C/Hf7ATcwQKBgQCOtmFetTdAWDQmw6ZBIoyXIFp4xA207HvbkObfH/fCt4p2xzVMQkuuU0vztexbu9w0ZURE6hFfplxxjCSIoGtAtMH5KuzfuRpd6f6i6eRZJxjHlT9cwKstJoZGXUeB/EjhEOW3TbrvgsmuDQUxPA50e8NQ/ltLcPqb8bSZOhIwSwKBgQDfUlvjHFP97oWSzJbRI+VekjWuFzLbhngbsD0/vmcbLR8hN6goURgTogSH7IBQwH3lfbPrrIrpNytdKtkrWh0k0j49ZWGk5xBA3wE8K9aZ4odASJOyHtx7L3Z8gD2607br5MOp3966pqbmRd7RHp/9rgYWQAs/sxajaGkMJlpMKg==";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApw0zb92iwM5QIftLxRmdDJuWupMmFnS4jnFEc1K3peZkxCnYbKM2A7m+J9oRRoHAUERnSZifV0bwH06dZsXMJRveLvVKBjRpU++3MelhtAVZDeIxXmvcPsMxorqKHNrLdsHgFDer/XSE51JHVZqsNYg4NuLAKD3rkhCke8cqKeq3puOR0YSZT1bluXi3WGOB5e4FHbtgOLM7GZz0Nha3KD/jFP/ROJ9q+sRVYtcJPDuHWMWUCCEI8kF+eGv57LA9rkKOUzenYz7TCH8ZUq8pM1x0OAWni9THM9p3TGUJsZYjFkF0GZ76QMq31Y6YY1eEWQAgyzYX5lvtgdexvWIJlwIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    private String notify_url;

    public String getNotify_url() {
        return webConfBean.getProjectDomain()+"/rest/alipay/notify";
    }

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    private String return_url;

    public String getReturn_url() {
        return webConfBean.getProjectDomain()+"/rest/alipay/notify";
    }

    // 签名方式
    public String sign_type = "RSA2";

    // 字符编码格式
    public String charset = "utf-8";

    // 支付宝网关
    public String gatewayUrl = "https://openapi.alipay.com/gateway.do";
//    public String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public String log_path = "logs";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

