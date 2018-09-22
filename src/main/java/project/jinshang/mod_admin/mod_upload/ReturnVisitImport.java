package project.jinshang.mod_admin.mod_upload;


import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_member.bean.dto.ReturnVisitDto;
import project.jinshang.mod_member.service.MemberService;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class ReturnVisitImport {

    @Autowired
    private MemberService memberService;

    /**
     * 回访记录批量导入
     * @param URL
     * @return
     * @throws IOException
     */
    public ArrayList<ReturnVisitDto> excelReturnVisitTo(String URL) throws Exception {
        //创建list集合存放对象
        ArrayList<ReturnVisitDto> list = new ArrayList<ReturnVisitDto>();

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

            //手机号	标签	回访类型	回访时间	回访结果	回访内容
            Map<String,Integer> map = new HashMap<>();

            Row titleRow =  sheet.getRow(0);
            int cellNum = titleRow.getLastCellNum();
            for(int i=0;i<cellNum;i++){
                map.put(titleRow.getCell(i).getStringCellValue(),i);
            }

            Set<String> keySet = map.keySet();
            if(!keySet.contains("手机号") ||
                    !keySet.contains("标签") ||
                    !keySet.contains("回访类型") ||
                    !keySet.contains("回访时间") ||
                    !keySet.contains("回访结果") ||
                    !keySet.contains("回访内容")){
                throw  new Exception("模版不正确，手机号\t标签\t回访类型\t回访时间\t回访结果\t回访内容\n");
            }

            //循环所有行  最后一行是备注不处理
            for (int i = 1; i <= lastRowNum-1; i++) {
                //获取当前行中的内容
                Row row = sheet.getRow(i);


                short cell = row.getLastCellNum();
                ReturnVisitDto returnVisitDto = new ReturnVisitDto();
                if(row !=null && cell!=0){
                    String newmobile ;
                    String newreturntime;
                    try {
                        //处理手机号  数字变成科学计数法
                        Cell mobile =row.getCell(0);
                        BigDecimal bigDecimal = new BigDecimal(mobile.toString());//创建BigDecimal对象，把科学计数转成数字
                        newmobile = bigDecimal.toPlainString();
                        //处理时间  数字变成科学计数法
                        Cell returntime =row.getCell(3);
                        Date d = returntime.getDateCellValue();
                        DateFormat sf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
                        newreturntime = sf.format(d);
                    }catch (Exception e){
                        throw  new RuntimeException("手机号或者回访时间为空!");
                    }





                    if(newmobile == null || newmobile ==""){
                        throw  new RuntimeException("第"+ (i) +"行手机号必须填写");
                    }

                    if(!StringUtils.isNumeric(newmobile.trim())){
                        throw  new RuntimeException("第"+ (i) +"行手机号请填写数字");
                    }
                    //根据手机号查询 会员id
                    Member member = memberService.selectMemberByMobile(newmobile.trim());
                    if(member == null){
                        throw  new RuntimeException("检测到其中一行根据手机号没有查到会员，请检查手机号");
                    }
                    returnVisitDto.setMemberid(member.getId());



                    String labelname =row.getCell(map.get("标签")).getStringCellValue();
                    if(labelname == null || labelname ==""){
                        throw  new RuntimeException("第"+ (i+1) +"行标签必须填写");
                    }
                    returnVisitDto.setLabelname(labelname.trim());


                    String type = this.getValue(row.getCell(map.get("回访类型")));
                    if(type == null || type == ""){
                        throw  new RuntimeException("第"+ (i+2) +"行回访类型必须填写");
                    }
                    returnVisitDto.setType(type.trim());

                   // String returntime = this.getValue(row.getCell(map.get("回访时间")));



                    if(newreturntime == null || newreturntime == ""){
                        throw  new RuntimeException("第"+ (i+3) +"行回访时间必须填写");
                    }
                    Timestamp timestamp = Timestamp.valueOf(newreturntime);
                    returnVisitDto.setReturntime(timestamp);

                    String result = this.getValue(row.getCell(map.get("回访结果")));
                    if(result == null || result ==""){
                        throw  new RuntimeException("第"+ (i+4) +"行回访结果必须填写");
                    }
                    returnVisitDto.setResult(result.trim());

                    String content = this.getValue(row.getCell(map.get("回访内容")));

                    if(content == null || content ==""){
                        throw  new Exception("第"+ (i+5) +"行回访内容必须填写");
                    }
                    returnVisitDto.setContent(content.trim());
                    list.add(returnVisitDto);
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
