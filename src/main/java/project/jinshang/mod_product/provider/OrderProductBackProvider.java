package project.jinshang.mod_product.provider;

import org.apache.ibatis.jdbc.SQL;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_product.bean.BackQueryParam;

import java.util.Calendar;
import java.util.Date;

/**
 * create : wyh
 * date : 2018/3/13
 */
public class OrderProductBackProvider {

    public String getOrderProductBackList(BackQueryParam backQueryParam) {
        SQL sql = new SQL();
        sql.SELECT("ob.*,op.classifyid,op.pdpic as \"pdPic\",op.standard,op.unit ");
        sql.FROM("orderProductBack ob ");
        sql.INNER_JOIN(" orderproduct op  on ob.orderpdid=op.id ");
        //sql.LEFT_OUTER_JOIN("productinfo p on ob.pdid=p.id");



        if (backQueryParam.getSellerid() != null) {
            //criteria.andSelleridEqualTo(backQueryParam.getSellerid());
            sql.WHERE("ob.sellerid=#{sellerid}");
        }

        if (backQueryParam.getMemberId() != null) {
            //criteria.andMemberidEqualTo(backQueryParam.getMemberId());
            sql.WHERE("ob.memberid=#{memberId}");
        }
        if (StringUtils.hasText(backQueryParam.getCode())) {
            String code = "%" + backQueryParam.getCode() + "%";
            backQueryParam.setCode(code);
            // criteria.andCodeLike(code);
            sql.WHERE("ob.code like #{code}");
        }
        if (StringUtils.hasText(backQueryParam.getOrderNo())) {
            String orderNo = "%" + backQueryParam.getOrderNo() + "%";
            backQueryParam.setOrderNo(orderNo);
            //criteria.andOrdernoLike(orderNo);
            sql.WHERE("ob.orderno like #{orderNo}");
        }
        if (StringUtils.hasText(backQueryParam.getBackNo())) {
            String backNo = "%" + backQueryParam.getBackNo() + "%";
            //criteria.andBacknoLike(backNo);
            backQueryParam.setBackNo(backNo);
            sql.WHERE("ob.backno like #{backNo}");
        }
        if (StringUtils.hasText(backQueryParam.getPdName())) {
            String pdName = "%" + backQueryParam.getPdName() + "%";
            //criteria.andPdnameLike(pdName);
            backQueryParam.setPdName(pdName);
            sql.WHERE("ob.pdname like #{pdName}");
        }
        if (StringUtils.hasText(backQueryParam.getMemberName())) {
            String memberName = "%" + backQueryParam.getMemberName() + "%";
            //criteria.andMembernameLike(memberName);
            backQueryParam.setMemberName(memberName);
            sql.WHERE("ob.membername like #{memberName}");
        }
        if (StringUtils.hasText(backQueryParam.getSellerName())) {
            String sellerName = "%" + backQueryParam.getSellerName() + "%";
            //criteria.andSellnameLike(sellerName);
            backQueryParam.setSellerName(sellerName);
            sql.WHERE("ob.sellername like #{sellerName}");

        }
        if (backQueryParam.getState() != Quantity.STATE_) {
            //criteria.andStateEqualTo(backQueryParam.getState());
            sql.WHERE("ob.state = #{state}");
        }
        if (backQueryParam.getStartTime() != null) {
            //criteria.andCreatetimeGreaterThanOrEqualTo(backQueryParam.getStartTime());
            sql.WHERE("ob.createtime >= #{startTime}");
        }
        if (backQueryParam.getEndTime() != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(backQueryParam.getEndTime());
            c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
            Date tomorrow = c.getTime();
            backQueryParam.setEndTime(tomorrow);
            sql.WHERE("ob.createtime <#{endTime}");
        }

        return sql.toString();
    }

}
