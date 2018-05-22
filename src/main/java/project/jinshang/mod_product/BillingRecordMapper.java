package project.jinshang.mod_product;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import project.jinshang.mod_product.bean.BillingRecord;
import project.jinshang.mod_product.bean.BillingRecordExample;

import java.math.BigDecimal;
import java.util.List;

public interface BillingRecordMapper {
    int countByExample(BillingRecordExample example);

    int deleteByExample(BillingRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BillingRecord record);

    int insertSelective(BillingRecord record);

    List<BillingRecord> selectByExample(BillingRecordExample example);

    BillingRecord selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BillingRecord record, @Param("example") BillingRecordExample example);

    int updateByExample(@Param("record") BillingRecord record, @Param("example") BillingRecordExample example);

    int updateByPrimaryKeySelective(BillingRecord record);

    int updateByPrimaryKey(BillingRecord record);

    @Select("select * from BillingRecord where id in (${ids})")
    List<BillingRecord> getBillingRecordListByIds(@Param("ids") String ids);

    /**
     * 订单完成后，将提交订单时就申请开票的设置为待开票状态
     * @param orderid
     * @param memberid
     * @param billcash (增加的开票金额)
     * @return
     */
    @Update("update billingrecord set state=0,billcash=billcash + #{billcash} where memberid=#{memberid} and orderno=#{orderid} and state=-1")
    int updateForwordBillingState(@Param("orderid") String orderid, @Param("memberid") Long memberid, @Param("billcash")BigDecimal billcash);


    /**
     * 后台对订单商品修改，对开票金额要同步修改
     * @param orderid
     * @param memberid
     * @param billcash
     * @return
     */
    @Update("update billingrecord set billcash=billcash + #{billcash} where memberid=#{memberid} and orderno=#{orderid}")
    int  updateAdminDecOrderProductnum(@Param("orderid") String orderid,@Param("memberid") Long memberid,@Param("billcash") BigDecimal billcash);


    @Select("select * from billingrecord WHERE (orderno=#{orderno} or orderno like '${orderno},%' or " +
            " orderno like '%,${orderno}' or orderno like ',%${orderno}%,') order by id desc limit 1")
    BillingRecord getBillingRecordByOrderno(@Param("orderno") String orderno);

}