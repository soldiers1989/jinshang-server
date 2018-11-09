package project.jinshang.common.constant;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2017/11/20.
 * 常量类
 */
public class Quantity {

    public static final short STATE_ = (short) -1;
    public static final short STATE_0 = (short) 0;
    public static final short STATE_1 = (short) 1;
    public static final short STATE_2 = (short) 2;
    public static final short STATE_3 = (short) 3;
    public static final short STATE_4 = (short) 4;
    public static final short STATE_5 = (short) 5;
    public static final short STATE_6 = (short) 6;
    public static final short STATE_7 = (short) 7;
    public static final short STATE_8 = (short) 8;
    public static final short STATE_9 = (short) 9;
    public static final short STATE_10 = (short) 10;
    public static final short STATE_11 = (short) 11;
    public static final short STATE_12 = (short) 12;

    public static final int INT_0 = 0;


    public static final int INT_ = -1;

    public static final int INT_1 = 1;

    public static final int INT_2 = 2;

    public static final String LIJIFAHUO = "立即发货";
    public static final String SANTIANFAHUO = "3天内发货";
    public static final String SANSHITIANFAHUO = "30天内发货";
    public static final String LIUSHITIANFAHUO = "60天内发货";
    public static final String JIUSHITIANFAHUO = "90天内发货";

    public static final String BUYER_BACK_REASON = "买方原因退货扣违约金";
    public static final String BUYER_CANCEL_REASON = "买方原因取消订单扣违约金";
    public static final String SELLER_CANCEL_REASON = "卖方原因取消订单扣违约金";


    public static final String SELLER_UPDATE_ORDERNUM_REASON="卖方修改订单数量违约金";


    public  static  final BigDecimal BIG_DECIMAL_0 = new BigDecimal(0);

    public  static  final  BigDecimal BIG_DECIMAL_MINUS_1 = new BigDecimal(-1);
    

    //优惠券状态列表
    //待领取
    public
    static final Long  YH_UNCLAIMED=0L;
    //已领用
    public static final Long  YH_CLAIMED=2L;
    //禁用
    public static final Long  YH_FORBIDDEN=97L;
    //已失效
    public static final Long  YH_INVALID=98L;
    //已使用
    public static final Long  YH_USED=99L;

    public static  final String BUYER_DEFAULT="买家违约";
    public static  final String SELLER_DEFAULT="卖家违约";

    public static  final String VIDEO="视频";
    public static  final String PICTURE="图片";
}
