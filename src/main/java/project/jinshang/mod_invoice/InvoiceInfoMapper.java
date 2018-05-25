package project.jinshang.mod_invoice;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import project.jinshang.mod_invoice.bean.InvoiceInfo;
import project.jinshang.mod_invoice.bean.InvoiceInfoExample;

import java.util.List;

public interface InvoiceInfoMapper {

    int countByExample(InvoiceInfoExample example);

    int deleteByExample(InvoiceInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(InvoiceInfo record);

    int insertSelective(InvoiceInfo record);

    List<InvoiceInfo> selectByExample(InvoiceInfoExample example);

    InvoiceInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") InvoiceInfo record, @Param("example") InvoiceInfoExample example);

    int updateByExample(@Param("record") InvoiceInfo record, @Param("example") InvoiceInfoExample example);

    int updateByPrimaryKeySelective(InvoiceInfo record);

    int updateByPrimaryKey(InvoiceInfo record);

    /**
     * 获取某个用户的所有发票信息-买家中心用
     *
     * @param memberId
     * @return
     */
    @Select("select i.*,m.company from invoiceinfo i left join member m on i.memberid=m.id where i.memberid = #{memberid,jdbcType=BIGINT} order by i.id asc")
    List<InvoiceInfo> invoiceInfoListByMemberId(@Param(value = "memberid") Long memberId);

    /**
     * 获取某个用户的所有发票信息-订单信息用
     *
     * @param memberId
     * @return
     */
    @Select("select i.*,m.company from invoiceinfo i left join member m on i.memberid=m.id where i.memberid = #{memberid,jdbcType=BIGINT} and defaultbill=1 order by i.id asc")
    List<InvoiceInfo> invoiceInfoListByMemberId2(@Param(value = "memberid") Long memberId);

    /**
     * 获取某个用户的默认发票信息
     *
     * @param memberId
     * @return
     */
    @Select("select * from invoiceinfo where memberid = #{memberid,jdbcType=BIGINT} and defaultbill = 1")
    InvoiceInfo defaultInvoiceInfoByMemberId(@Param(value = "memberid") Long memberId);

    @Delete("delete from invoiceinfo where memberid = #{memberid,jdbcType=BIGINT} ")
    void deleteInvoiceInfoByMemberid(@Param(value = "memberid") Long memberId);

    @Update("update invoiceinfo set defaultbill=0 where memberid = #{memberid,jdbcType=BIGINT} ")
    int updateInvoiceInfoByMemberid(@Param(value = "memberid") Long memberId);

}
