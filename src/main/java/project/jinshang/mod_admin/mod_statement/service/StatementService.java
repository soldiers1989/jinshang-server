package project.jinshang.mod_admin.mod_statement.service;

import mizuki.project.core.restserver.config.BasicRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.utils.JinshangUtils;
import project.jinshang.common.utils.PayType;
import project.jinshang.common.utils.StatementType;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_admin.mod_statement.BuyerStatementMapper;
import project.jinshang.mod_admin.mod_statement.bean.BuyerStatement;
import project.jinshang.mod_admin.mod_statement.bean.BuyerStatementExample;
import project.jinshang.mod_admin.mod_statement.bean.dto.BuyerStamentQueryDto;
import project.jinshang.mod_admin.mod_statement.bean.dto.BuyerStatementDto;
import project.jinshang.mod_cash.bean.dto.BuyerCapitalAccountDto;
import project.jinshang.mod_cash.bean.dto.BuyerCapitalAccountQueryDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 下单客户对账单service
 *
 * @author xiazy
 * @create 2018-09-13 16:22
 **/
@Service
public class StatementService {

    @Autowired
    private BuyerStatementMapper buyerStatementMapper;
    public List<BuyerStatementDto> listForBuyerStatement(BuyerStamentQueryDto dto){
        List<BuyerStatementDto>  buyerStatementDtoList=new ArrayList<>();
        //上期结转
        BuyerStatementDto buyerStatementDtoSQ=new BuyerStatementDto();
        buyerStatementDtoSQ.setReceivableamount(new BigDecimal("0.00"));
        buyerStatementDtoSQ.setInvoicebalance(new BigDecimal("0.00"));
        if (dto.getStatementStartTime()!=null){
            BuyerStatementDto previousPeriod=buyerStatementMapper.queryStatePrePeriod(dto.getStatementStartTime(),dto);
            if (previousPeriod!=null){
                buyerStatementDtoList.add(previousPeriod);
            }else {
                buyerStatementDtoList.add(buyerStatementDtoSQ);
            }
        }else {
            buyerStatementDtoList.add(buyerStatementDtoSQ);
        }
        List<BuyerStatementDto> currentList=buyerStatementMapper.listForBuyerStatement(dto);
        buyerStatementDtoList.addAll(currentList);
        if (buyerStatementDtoList!=null&&buyerStatementDtoList.size()>0){
            int size=buyerStatementDtoList.size();
            final BigDecimal[] sumDeliveryAmount = {new BigDecimal(0.00)};
            final BigDecimal[] sumReceiptAmount = {new BigDecimal(0.00)};
            final BigDecimal[] sumInvoiceAmount = {new BigDecimal(0.00)};
            sumDeliveryAmount[0].setScale(2,BigDecimal.ROUND_HALF_UP);
            sumReceiptAmount[0].setScale(2,BigDecimal.ROUND_HALF_UP);
            sumInvoiceAmount[0].setScale(2,BigDecimal.ROUND_HALF_UP);
            currentList.stream().forEach(buyerStatementDto -> {
                sumDeliveryAmount[0] = buyerStatementDto.getDeliveryamount().compareTo(Quantity.BIG_DECIMAL_0)==0 ? sumDeliveryAmount[0] : sumDeliveryAmount[0].add(buyerStatementDto.getDeliveryamount());
                sumReceiptAmount[0] = buyerStatementDto.getReceiptamount().compareTo(Quantity.BIG_DECIMAL_0)==0? sumReceiptAmount[0] : sumReceiptAmount[0].add(buyerStatementDto.getReceiptamount());
                sumInvoiceAmount[0] = buyerStatementDto.getInvoiceamount().compareTo(Quantity.BIG_DECIMAL_0)==0? sumInvoiceAmount[0] : sumInvoiceAmount[0].add(buyerStatementDto.getInvoiceamount());
            });
            BuyerStatementDto buyerStatementDto=new BuyerStatementDto();
            buyerStatementDto.setDeliveryamount(sumDeliveryAmount[0]);
            buyerStatementDto.setReceiptamount(sumReceiptAmount[0]);
            buyerStatementDto.setInvoiceamount(sumInvoiceAmount[0]);
            buyerStatementDto.setReceivableamount(buyerStatementDtoList.get(size-1).getReceivableamount());
            buyerStatementDto.setInvoicebalance(buyerStatementDtoList.get(size-1).getInvoicebalance());
            buyerStatementDto.setContractno("当期结算");
            buyerStatementDtoList.add(buyerStatementDto);
            buyerStatementDtoList.get(0).setContractno("上期结转");
        }
        return buyerStatementDtoList;
    }

    /**
     * 买家对账单列表查询结果导出
     * @param dto
     * @return
     */
    public List<Map<String,Object>>  excelExportListForAccount(BuyerStamentQueryDto dto){
        List<Map<String,Object>> result=new ArrayList<Map<String,Object>>();

        if((dto.getMemberid()==null||dto.getMemberid()<0) && StringUtils.isEmpty(dto.getUsername()) && StringUtils.isEmpty(dto.getCompanyname()) && StringUtils.isEmpty(dto.getRealname())){
            return result;
        }
        String[] rowTitles = new String[]{"日期", "合同编号", "类别", "发货金额", "收款金额", "应收账款", "开票金额", "发票结余", "支付方式", "支付单号", "备注"};
        List<BuyerStatementDto> buyerStatementDtoList=null;
        buyerStatementDtoList=this.listForBuyerStatement(dto);
        if (buyerStatementDtoList!=null&&buyerStatementDtoList.size()>0){
            Map<String,Object> map=new HashMap<>();
            for (BuyerStatementDto dto1:buyerStatementDtoList){
                map=new HashMap<>();
                map.put("日期",dto1.getCreatetime());
                map.put("合同编号",dto1.getContractno());
                map.put("类别", dto1.getType()==null?"":StatementType.transfortype(dto1.getType()));
                map.put("发货金额",dto1.getDeliveryamount());
                map.put("收款金额",dto1.getReceiptamount());
                map.put("应收账款",dto1.getReceivableamount());
                map.put("开票金额",dto1.getInvoiceamount());
                map.put("发票结余",dto1.getInvoicebalance());
                map.put("支付方式",dto1.getPaytype()==null?"":PayType.transfortype(dto1.getPaytype()));
                map.put("支付单号",dto1.getPayno());
                map.put("备注",dto1.getRemark()==null?"":dto1.getRemark());
                result.add(map);
            }
        }
        return result;
    }


    public int insertStatementAll(List<BuyerStatement> list){
        int i=0;
        if (list!=null&&list.size()>0){
            for (BuyerStatement buyerStatement:list){
                this.insertStatement(buyerStatement);
                i++;
            }
        }
        return  i;
    }

    public int insertStatement(BuyerStatement buyerStatement){
//        return  buyerStatementMapper.insertSelective(buyerStatement);
        return  buyerStatementMapper.insertBySelective(buyerStatement);
    }


    public int insertStatementForTest(BuyerStatement buyerStatement){
        return  buyerStatementMapper.insertSelective(buyerStatement);
//        return  buyerStatementMapper.insertBySelective(buyerStatement);
    }


    public List<BuyerStatement>  queryByExample(BuyerStatementExample example){
         return buyerStatementMapper.selectByExample(example);
    }
}
