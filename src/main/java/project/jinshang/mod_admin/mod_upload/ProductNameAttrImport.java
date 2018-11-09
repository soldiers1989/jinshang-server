package project.jinshang.mod_admin.mod_upload;


import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;
import project.jinshang.common.exception.MyException;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_product.bean.Attvalue;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

@Component
public class ProductNameAttrImport {



    /**
     * 品名管理 属性批量导入 排序，属性值，备注
     * @param URL
     * @return
     * @throws IOException
     */
    public ArrayList<Attvalue> excelAttvalueTo(String URL) throws Exception {
        //创建list集合存放对象
        ArrayList<Attvalue> list = new ArrayList<Attvalue>();

        File file = new File(URL);

        Workbook workbook=null;
        try {
            workbook = WorkbookFactory.create(file);
            //读取默认第一个工作表sheet
            Sheet sheet =  workbook.getSheetAt(0);
            //获取sheet中最后一行行号
            int lastRowNum = sheet.getLastRowNum();

            if(lastRowNum<1){
                throw  new RuntimeException("模版不正确");
            }

            //排序，属性值，备注
            Map<String,Integer> map = new HashMap<>();

            Row titleRow =  sheet.getRow(0);
            int cellNum = titleRow.getLastCellNum();
            for(int i=0;i<cellNum;i++){
                map.put(titleRow.getCell(i).getStringCellValue(),i);
            }

            Set<String> keySet = map.keySet();
            if(!keySet.contains("排序") ||
                    !keySet.contains("属性值") ||
                    !keySet.contains("备注")){
                throw  new Exception("模版不正确，排序\t属性值\t备注\n");
            }

            //循环所有行
            for (int i = 1; i <= lastRowNum; i++) {

                //获取当前行中的内容
                Row row = sheet.getRow(i);
                if(row ==null){
                    throw  new MyException("导入内容为空");
                }
                short cell = row.getLastCellNum();
                Attvalue attvalue = new Attvalue();
                if(row !=null && cell!=0){
                    String sort = this.getValue(row.getCell(map.get("排序")));
                    if(StringUtils.judgeIsDecimal(sort)){
                        throw  new RuntimeException("第"+ (i) +"行排序请填写整数,不要填写小数");
                    }
                    if(!StringUtils.isNumeric(sort)){
                        throw  new RuntimeException("第"+ (i) +"行排序请填写数字");
                    }
                    if(sort == null || sort ==""){
                        throw  new RuntimeException("第"+ (i) +"行排序必须填写");
                    }
                    attvalue.setSort(Integer.valueOf(sort));

                    //处理属性值为数字时候  数字变成科学计数法
                    Cell tempparamvalue =row.getCell(1);
                    String paramvalue;
                    String str =null;
                    if(StringUtils.isNumeric(tempparamvalue.toString())) {
                        BigDecimal paramvalue1 = new BigDecimal(tempparamvalue.toString());//创建BigDecimal对象，把科学计数转成数字
                        paramvalue = paramvalue1.toPlainString();
                        str = paramvalue.substring(paramvalue.lastIndexOf("."));
                    }else{
                        paramvalue = this.getValue(row.getCell(map.get("属性值")));
                    }

                    if(paramvalue == null || paramvalue == ""){
                        throw  new RuntimeException("第"+ (i+1) +"行属性值必须填写");
                    }
                    //传入的是3.0 需要截取3 但是不能误伤3.001 通过长度判断
                        if(StringUtils.hasText(str)){
                            if (str.length() == 2) {
                                String newparamvalue = paramvalue.substring(0, paramvalue.indexOf("."));
                                attvalue.setParamvalue(newparamvalue);
                            }else {
                                attvalue.setParamvalue(paramvalue.trim());
                            }
                        }
                         else {
                            attvalue.setParamvalue(paramvalue.trim());
                        }


                    //处理备注为数字时候  数字变成科学计数法
                    Cell tempmark =row.getCell(2);
                    String mark = null;
                    String str1 = null;
                    if(tempmark != null){
                        if(StringUtils.isNumeric(tempmark.toString())) {
                            BigDecimal tempmark1 = new BigDecimal(tempmark.toString());//创建BigDecimal对象，把科学计数转成数字
                            mark = tempmark1.toPlainString();
                            str1 = mark.substring(mark.lastIndexOf("."));
                        }
                        else {
                            mark = this.getValue(row.getCell(map.get("备注")));
                        }
                    }else {
                         mark = this.getValue(row.getCell(map.get("备注")));
                    }



                    if(StringUtils.hasText(mark)) {
                        if(StringUtils.hasText(str1)) {
                            if (str1.length() == 2) {
                                String newmark = mark.substring(0, mark.indexOf("."));
                                attvalue.setMark(newmark);
                            } else {
                                attvalue.setMark(mark.trim());
                            }
                        }else  {
                            attvalue.setMark(mark.trim());
                        }
                    }

                    list.add(attvalue);
                }
            }
            return list;
        } catch (InvalidFormatException e) {
            e.printStackTrace();
            throw e;
        }finally {
            if(workbook != null){
                workbook.close();
            }
        }
        // return null;
    }


    //取单元格中的值
    public String getValue(Cell cell){
        String result="";

        if(cell == null){
            return  null;
        }

        if(cell.getCellType() == cell.CELL_TYPE_BOOLEAN){
            //返回布尔类型的值
            result = cell.getBooleanCellValue() +"";
        }else if(cell.getCellType() == cell.CELL_TYPE_NUMERIC){
            //返回数值类型的值
            if(HSSFDateUtil.isCellDateFormatted(cell)){
                result = DateUtil.getJavaDate(cell.getNumericCellValue()).toString();
            }else{
                result = cell.getNumericCellValue()+"";
            }
            return String.valueOf(cell.getNumericCellValue());
        }else if(cell.getCellType() == cell.CELL_TYPE_FORMULA){
            result = cell.getCellFormula();
        }else if(cell.getCellType() == cell.CELL_TYPE_STRING){
            result = cell.getStringCellValue();
        }else{
            //返回字符口串类型的值
            result = cell.getStringCellValue();
        }
        return result;
    }

}
