package project.jinshang.common.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class ExcelGen {

    /**
     * 解析 通用生成的excel
     * map的类型包括：Date，double，等
     */
    public static List<Map<String,Object>> parseCommon(InputStream inputStream) throws IOException {
        List<Map<String,Object>> list = new ArrayList<>();
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);
        XSSFRow row1 = sheet.getRow(1);
        // 最后一行不算
        for(int i=2;i<sheet.getLastRowNum();i++){
            Map<String,Object> map = new HashMap<>();
            XSSFRow row = sheet.getRow(i);
            for(int j=0;j<row.getLastCellNum();j++){
                String key = row1.getCell(j).getStringCellValue();
                Object val = null;
                XSSFCell cell = row.getCell(j);
                if(cell.getCellTypeEnum().name().equals(CellType.STRING.name())){
                    val = cell.getStringCellValue();
                }else if(cell.getCellTypeEnum().name().equals(CellType.BOOLEAN.name())){
                    val = cell.getBooleanCellValue();
                }else if(cell.getCellTypeEnum().name().equals(CellType.NUMERIC.name())){
                    // 判断日期
                    if(DateUtil.isCellDateFormatted(cell)){
                        val = DateUtil.getJavaDate(cell.getNumericCellValue());
                    }else{
                        val = cell.getNumericCellValue();
                    }
                }
                map.put(key,val);
            }
            list.add(map);
        }
        return list;
    }

    public static void main(String[] args) throws IOException {
        parseCommon(new FileInputStream(new File("/Users/ycj/Downloads/订单列表 (3).xlsx")));
    }

    /**
     * 通用excel生成：
     * title标题，rowTitles第一行标题，data-一行数组（和rowTitles对应）
     * sumCols：需要总计列名
     * 注：时间必须是Date类型; 暂限制为10万条
     */
    public static XSSFWorkbook common(
            String title,
            String[] rowTitles,
            List<Map<String,Object>> data,
            String[] sumCols
    ) {
        if(rowTitles==null || data==null || data.size()>100000){
            return null;
        }
        XSSFWorkbook workbook = new XSSFWorkbook();
        CellStyle styleCommon = workbook.createCellStyle();
        setCommonCellStyle(styleCommon);

        XSSFSheet sheet = workbook.createSheet();
        XSSFRow row = sheet.createRow(0);
        XSSFCell cell;
        cell = row.createCell(0);
//        cell.setCellStyle(styleCommon);
        cell.setCellValue(title);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, rowTitles.length-1));

        row = sheet.createRow(1);
        CellStyle styleTitle = workbook.createCellStyle();
        for(int i=0;i<rowTitles.length;i++){
            cell = row.createCell(i);
            cell.setCellStyle(styleCommon);
            cell.setCellValue(rowTitles[i]);
            // title的cell style
            cell = sheet.getRow(0).getCell(i);
            if(cell==null) cell = sheet.getRow(0).createCell(i);
            //CellStyle styleTitle = workbook.createCellStyle();
            setCommonCellStyle(styleTitle);
            styleTitle.setAlignment(HorizontalAlignment.CENTER);
            cell.setCellStyle(styleTitle);
        }
        // 数据行
        int r=2;
        CellStyle dateStyle =null;
        for(Map<String,Object> map:data){
            row = sheet.createRow(r);
            for(int i=0;i<rowTitles.length;i++){
                Object e = data.get(r-2).get(rowTitles[i]);
                cell = row.createCell(i);
                cell.setCellStyle(styleCommon);
                if(e!=null) {
                    if (e instanceof Date) {
                        cell.setCellValue((Date) e);
                        dateStyle = workbook.createCellStyle();
                        setCommonCellStyle(dateStyle);
                        dateStyle.setDataFormat(workbook.createDataFormat().getFormat("yyyy/mm/dd"));
                        cell.setCellStyle(dateStyle);
                    } else if (e instanceof Double) {
                        cell.setCellValue((Double) e);
                        dateStyle = workbook.createCellStyle();
                        setCommonCellStyle(dateStyle);
                        dateStyle.setDataFormat(workbook.createDataFormat().getFormat("0.000"));
                        cell.setCellStyle(dateStyle);
                    } else if (e instanceof String) {
                        cell.setCellValue((String) e);
                    } else if (e instanceof Integer) {
                        cell.setCellValue((Integer) e);
                    }else if(e instanceof BigDecimal){
                        cell.setCellValue(((BigDecimal) e).setScale(3, RoundingMode.HALF_UP).doubleValue());
                        dateStyle = workbook.createCellStyle();
                        setCommonCellStyle(dateStyle);
                        dateStyle.setDataFormat(workbook.createDataFormat().getFormat("0.000"));
                        cell.setCellStyle(dateStyle);
                    }else if(e instanceof  Long){
                        cell.setCellValue((Long)e);
                    }
                }
            }
            r++;
        }
        // 总计
        if(sumCols!=null){
            row = sheet.createRow(r);
            for(int i=0;i<rowTitles.length;i++){
                cell = row.createCell(i);
                if(i==0) cell.setCellValue("合计");
                cell.setCellStyle(styleCommon);
            }
            for(String e:sumCols){
                int index = findIndex(rowTitles,e);
                if(index>0){
                    cell = row.getCell(index);
                    cell.setCellFormula("SUM("+(char)('A'+index)+"3:"+(char)('A'+index)+(3+data.size()-1)+")");
                    cell.setCellType(CellType.FORMULA);
                }
            }
        }
        for(int i=0;i<rowTitles.length;i++){
            // 宽度自适应
            sheet.autoSizeColumn(i);
        }
        return workbook;
    }

    private static int findIndex(String[] list, String e){
        for(int i=0;i<list.length;i++){
            if(e.equals(list[i])){
                return i;
            }
        }
        return -1;
    }

    private static void setCommonCellStyle(CellStyle cellStyle){
        // 样式
        cellStyle.setBorderBottom(BorderStyle.THIN); //下边框
        cellStyle.setBorderLeft(BorderStyle.THIN);//左边框
        cellStyle.setBorderTop(BorderStyle.THIN);//上边框
        cellStyle.setBorderRight(BorderStyle.THIN);//右边框
        // 自动换行
        cellStyle.setWrapText(true);
        // 缩小适应宽度
//        cellStyle.setShrinkToFit(true);
    }
}
