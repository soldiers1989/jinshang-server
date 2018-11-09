package project.jinshang.test;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.utils.DateUtils;
import project.jinshang.mod_admin.mod_statement.bean.BuyerStatement;
import project.jinshang.mod_admin.mod_statement.service.StatementService;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * TestForStatement
 *
 * @author xiazy
 * @create 2018-09-14 10:30
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestForStatement {

    @Autowired
    private StatementService statementService;
    @Test
    public void  Test() throws InvalidFormatException {
        String filepath="C:\\Users\\Administrator\\Desktop\\newstatement\\newStatement.xlsx";
        File file = new File(filepath);
        Workbook workbook=null;

        try {
            workbook = WorkbookFactory.create(file);
            //读取默认第一个工作表sheet
            Sheet sheet =  workbook.getSheetAt(0);
            Map<String,Integer> map = new HashMap<>();
            Date date=null;
            String tradeno="";
            int type=0;
            BigDecimal fahuoamount= Quantity.BIG_DECIMAL_0;
            BigDecimal shoukuanamount=Quantity.BIG_DECIMAL_0;
            BigDecimal yingshouamount=Quantity.BIG_DECIMAL_0;
            BigDecimal kaipiaoamount=Quantity.BIG_DECIMAL_0;
            BigDecimal fapiaobanlance=Quantity.BIG_DECIMAL_0;
            int paytype=0;
            String payno="";
            String remark="";
            long memberid=107;
            BuyerStatement buyerStatementDto=new BuyerStatement();
            buyerStatementDto.setMemberid(memberid);
            Row titleRow =  sheet.getRow(2);
            int cellNum = titleRow.getLastCellNum();
            for(int i=0;i<cellNum;i++){
                map.put(titleRow.getCell(i).getStringCellValue(),i);
            }
            for (int i=4;i<31;i++){
                Row row=sheet.getRow(i);
                if (row!=null){
                    String dateStr=row.getCell(map.get("日期")).getStringCellValue();
                    date=DateUtils.StrToDate(dateStr);
                    tradeno=row.getCell(map.get("合同编号")).getStringCellValue();
                    type=TYPE.transfortype(row.getCell(map.get("类别")).getStringCellValue());
                    fahuoamount= new BigDecimal(row.getCell(map.get("发货金额")).getNumericCellValue());
                    shoukuanamount=new BigDecimal(row.getCell(map.get("收款金额")).getNumericCellValue());
                    yingshouamount=new BigDecimal(row.getCell(map.get("应收账款")).getNumericCellValue());
                    kaipiaoamount=new BigDecimal(row.getCell(map.get("开票金额")).getNumericCellValue());
                    fapiaobanlance=new BigDecimal(row.getCell(map.get("发票结余")).getNumericCellValue());
                    paytype=PAYTYPE.transforpaytype(row.getCell(map.get("支付方式")).getStringCellValue());
                    payno=row.getCell(map.get("支付单号")).getNumericCellValue()>0?new BigDecimal(row.getCell(map.get("支付单号")).getNumericCellValue()).toPlainString():"";
                    remark=row.getCell(map.get("备注")).getStringCellValue();
                    buyerStatementDto.setCreatetime(date);
                    buyerStatementDto.setContractno(tradeno);
                    buyerStatementDto.setType((short) type);
                    buyerStatementDto.setDeliveryamount(fahuoamount);
                    buyerStatementDto.setReceiptamount(shoukuanamount);
                    buyerStatementDto.setReceivableamount(yingshouamount);
                    buyerStatementDto.setInvoiceamount(kaipiaoamount);
                    buyerStatementDto.setInvoicebalance(fapiaobanlance);
                    buyerStatementDto.setPaytype((short) paytype);
                    buyerStatementDto.setPayno(payno);
                    buyerStatementDto.setRemark(remark);
                    statementService.insertStatement(buyerStatementDto);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }



    private enum  TYPE
    {
        TYPE01("收款",1),
        TYPE02("发货",2),
        TYPE03("退货",3),
        TYPE04("违约金",4),
        TYPE05("退款",5),
        TYPE06("充值",6),
        TYPE07("提现",7),
        TYPE08("开票",8);
         int tyep ;
         String typename;

        TYPE(String typename,int tyep) {
            this.tyep = tyep;
            this.typename = typename;
        }


        private  static  int transfortype(String typename){
            for (TYPE one:TYPE.values()){
                if (typename.equals(one.typename)){
                    return one.tyep;
                }
            }
            return 0;
        }


        public int getTyep() {
            return tyep;
        }

        public void setTyep(int tyep) {
            this.tyep = tyep;
        }

        public String getTypename() {
            return typename;
        }

        public void setTypename(String typename) {
            this.typename = typename;
        }
    }


    private enum PAYTYPE{
        PAYTYPE01(1,"支付宝"),
        PAYTYPE02(2,"微信"),
        PAYTYPE03(3,"银联"),
        PAYTYPE04(4,"余额"),
        PAYTYPE05(5,"授信"),
        PAYTYPE06(6,"线下平台");
        int paytype;
        String paytypeStr;

        PAYTYPE(int paytype, String paytypeStr) {
            this.paytype = paytype;
            this.paytypeStr = paytypeStr;
        }



        private  static int transforpaytype(String paytypeStr){
            for (PAYTYPE paytype:PAYTYPE.values()){
                if (paytypeStr.equals(paytype.paytypeStr)){
                    return paytype.paytype;
                }

            }
            return 0;
        }
        public int getPaytype() {
            return paytype;
        }

        public void setPaytype(int paytype) {
            this.paytype = paytype;
        }

        public String getPaytypeStr() {
            return paytypeStr;
        }

        public void setPaytypeStr(String paytypeStr) {
            this.paytypeStr = paytypeStr;
        }
    }




}
