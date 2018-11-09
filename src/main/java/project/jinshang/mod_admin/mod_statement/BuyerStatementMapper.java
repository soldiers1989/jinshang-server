package project.jinshang.mod_admin.mod_statement;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import project.jinshang.mod_admin.mod_statement.bean.BuyerStatement;
import project.jinshang.mod_admin.mod_statement.bean.BuyerStatementExample;
import project.jinshang.mod_admin.mod_statement.bean.dto.BuyerStamentQueryDto;
import project.jinshang.mod_admin.mod_statement.bean.dto.BuyerStatementDto;
import project.jinshang.mod_admin.mod_statement.provider.BuyerStatementProvider;
import project.jinshang.mod_cash.bean.dto.BuyerCapitalAccountQueryDto;
import project.jinshang.mod_cash.bean.dto.BuyerCapitalViewDto;
import project.jinshang.mod_cash.provider.BuyerCapitalProvider;

public interface BuyerStatementMapper {
    int countByExample(BuyerStatementExample example);

    int deleteByExample(BuyerStatementExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BuyerStatement record);

    int insertSelective(BuyerStatement record);

    List<BuyerStatement> selectByExample(BuyerStatementExample example);

    BuyerStatement selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BuyerStatement record, @Param("example") BuyerStatementExample example);

    int updateByExample(@Param("record") BuyerStatement record, @Param("example") BuyerStatementExample example);

    int updateByPrimaryKeySelective(BuyerStatement record);

    int updateByPrimaryKey(BuyerStatement record);

    /**
     * 下单客户对账单查询
     * @param dto
     * @return
     */
    @SelectProvider(type= BuyerStatementProvider.class,method = "listForBuyerStatement")
    List<BuyerStatementDto> listForBuyerStatement(@Param("dto")BuyerStamentQueryDto dto);


    @Select("<script> select bt.* from buyerstatement bt left join member mm on bt.memberid=mm.id left join buyercompanyinfo bc on bt.memberid=mm.id " +
            " where bt.createtime &lt; #{startTime}"+
            "<if test=\"dto.memberid!=null and dto.memberid!=''\"> and mm.id=#{dto.memberid} </if>"+
            "<if test=\"dto.username!=null and dto.username!=''\"> and mm.username=#{dto.username} </if>"+
            "<if test=\"dto.realname!=null and dto.realname!=''\"> and mm.realname=#{dto.realname} </if>"+
            "<if test=\"dto.companyname!=null and dto.companyname!=''\"> and bc.companyname=#{dto.companyname} </if>"+
            " ORDER BY bt.id desc limit 1 "+
            "</script>")
    BuyerStatementDto queryStatePrePeriod(@Param("startTime")Date statementStartTime,@Param("dto")BuyerStamentQueryDto dto);


    @InsertProvider(type=BuyerStatementProvider.class,method ="insertBySelective")
    int insertBySelective(@Param("bs") BuyerStatement buyerStatement);
}