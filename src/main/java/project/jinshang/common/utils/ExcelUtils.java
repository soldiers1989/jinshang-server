package project.jinshang.common.utils;

import jxl.CellView;
import jxl.Workbook;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description: Excel导出操作
 * @Author: GoldFu
 * @CreateDate: 2018年5月23日
 * @Version: 1.0
 */
public class ExcelUtils {


    /**
     * jxl指定列合并单元格
     *
     * @param os     流
     * @param header 表头
     * @param list   内容
     * @param col    参考列
     * @param sumCol 统计的列
     * @return
     */
    public static WritableWorkbook jxlSteColumnMergCeellExcel(OutputStream os, String[] header, List<LinkedHashMap<String, Object>> list, Integer col, Integer sumCol) {
        try {
            // 打开文件
            WritableWorkbook book = Workbook.createWorkbook(os);
            // 生成名为“第一页”的工作表，参数0表示这是第一页
            WritableSheet sheetOne = book.createSheet("第一页", 0);
            //   sheetOne.setColumnView(0, 10); // 设置列的宽度
            WritableCellFormat headerFormat = new WritableCellFormat();
            //水平居中对齐
            //headerFormat.setAlignment(Alignment.CENTRE);
            //竖直方向居中对齐
            headerFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            CellView cv = new CellView();
            // 自适应宽度
            cv.setAutosize(true);
            // 动态数据 方法1：表头
            for (int i = 0; i < header.length; i++) {
                Label column = new Label(i, 0, header[i]);
                sheetOne.addCell(column);
            }
            // 用list去记录列的初始值
            List<Integer> recordList = new ArrayList<>(header.length);
            // 循环所有的列 并且都初始值为0
            for (int i = 0; i < header.length; i++) {
                recordList.add(0);// 添加到list
            }
            int row = 1;// 行
            double sum = 0;
            for (Map<String, Object> map : list) {
                int column = 0;// 列

                for (Map.Entry<String, Object> m : map.entrySet()) {// 循环列
                    if (m.getValue() != null && !"".equals(String.valueOf(m.getValue()).trim())) {
                        Label cell = new Label(column, row, String.valueOf(m.getValue()).trim(), headerFormat);// 创建数据
                        // 生成表格
                        sheetOne.addCell(cell);
                        //列宽
                        sheetOne.setColumnView(column, ((String.valueOf(m.getValue()).trim())).getBytes().length + 4);
                        sheetOne.setRowView(0, 1600, false); //设置行高
                    } else {
                        Label cell = new Label(column, row, "", headerFormat);// 创建数据
                        // 生成表格
                        sheetOne.addCell(cell);
                    }
                    if (column == sumCol) {

                        if (m.getValue() != null && !"".equals(String.valueOf(m.getValue()).trim())) {
                            double value = Double.parseDouble(String.valueOf(m.getValue()).trim());
                            sum = value + sum;
                        } else {
                            sum = sum + 0;
                        }

                    }
                    if (sheetOne.getCell(col, (row - 1)).getContents().equals(sheetOne.getCell(col, row).getContents())) {
                        // 判断当前行与上一行的内容是不是一样
                        if ((row) != list.size() && sheetOne.getCell(column, (row - 1)).getContents().equals(String.valueOf(m.getValue()).trim())) {
                            // 内容一样要合并的单元格数+1
                            recordList.set(column, recordList.get(column) + 1);// list中第几个列表的数值累加
                        }
                    } else { // 判断当前行的内容与上行内容不一样
                        if (sheetOne.getCell(column, (row - 1)).getContents() != String.valueOf(m.getValue()).trim()) {
                            // 当前行内容不一样 合并上面的一样的
                            int record = recordList.get(column);
                            sheetOne.mergeCells(column, (row - record - 1), column, (row - 1));
                            recordList.set(column, 0);// list中第几个列表的数值归零
                        }
                        // 判断是不是最后一行
                        if (row == list.size() && sheetOne.getCell(column, (row - 1)).getContents() != String.valueOf(m.getValue()).trim()) {
                            int record = recordList.get(column);
                            // 如果是最后一行并且内容与上行一样 直接合并
                            sheetOne.mergeCells(column, (row - record - 1), column, row);
                            recordList.set(column, 0);// list中第几个列表的数值归零
                        }
                    }
                    column++;
                }
                row++;
            }
            Label cell = new Label(sumCol, row + 1, String.valueOf(sum), headerFormat);// 创建数据
            sheetOne.addCell(cell);
            return book;
        } catch (Exception e) {
            e.toString();
            return null;
        }
    }

    /**
     * jxl导出表格
     *
     * @param os     流
     * @param header 表头
     * @param list 内容
     * @return
     */
    public static WritableWorkbook jxlExportExcel(OutputStream os, String[] header, List<LinkedHashMap<String, Object>> list) {
        try {
            // 打开文件
            WritableWorkbook book = Workbook.createWorkbook(os);
            // 生成名为“第一页”的工作表，参数0表示这是第一页
            WritableSheet sheetOne = book.createSheet("第一页", 0);
            //   sheetOne.setColumnView(0, 10); // 设置列的宽度
            WritableCellFormat headerFormat = new WritableCellFormat();
            //水平居中对齐
            //headerFormat.setAlignment(Alignment.CENTRE);
            //竖直方向居中对齐
            headerFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            CellView cv = new CellView();
            // 自适应宽度
            cv.setAutosize(true);
            // 动态数据 方法1：表头
            for (int i = 0; i < header.length; i++) {
                Label column = new Label(i, 0, header[i]);
                sheetOne.addCell(column);
            }
            int row = 1;// 行
            for (Map<String, Object> map : list) {
                int column = 0;// 列
                for (Map.Entry<String, Object> m : map.entrySet()) {// 循环列
                    if (m.getValue() != null && !"".equals(String.valueOf(m.getValue()).trim())) {
                        Label cell = new Label(column, row, String.valueOf(m.getValue()).trim(), headerFormat);// 创建数据
                        // 生成表格
                        sheetOne.addCell(cell);
                        //列宽
                        sheetOne.setColumnView(column, ((String.valueOf(m.getValue()).trim())).getBytes().length + 4);
                        sheetOne.setRowView(0, 1600, false); //设置行高
                    }
                    column++;
                }
                row++;
            }
            return book;
        } catch (Exception e) {
            e.toString();
            return null;
        }
    }

    /**
     * poi合并单元格
     *
     * @param titleNmae 名字
     * @param header    表头
     * @param list      内容
     * @param referCol  参考列一般是第一列
     * @param sumCols   统计数组
     * @return
     */
    public static XSSFWorkbook poiSteColumnMergCeellExcel(String titleNmae, String[] header, List<LinkedHashMap<String, Object>> list, Integer referCol, String[] sumCols) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();
        XSSFRow row = sheet.createRow(0);
        XSSFCell cell = row.createCell(0);
        cell.setCellValue(titleNmae);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, header.length - 1));
        // 设置列宽
        for (int i = 0; i < header.length; i++) {
            sheet.setColumnWidth(i, 18 * 256);
        }
        row = sheet.createRow(1);
        for (int i = 0; i < header.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(header[i]);
        }
        // 用list去记录列的初始值
        List<Integer> recordList = new ArrayList<>(header.length);
        // 循环所有的列 并且都初始值为0
        for (int i = 0; i < header.length; i++) {
            recordList.add(0);// 添加到list
        }
        int r = 2;
        for (Map<String, Object> map : list) {
            row = sheet.createRow(r);
            int j = 0;
            for (Map.Entry<String, Object> m : map.entrySet()) {// 循环列
                Object value = m.getValue();
                cell = row.createCell(j);
                Object e = null;
                if (value instanceof Date) {
                    cell.setCellValue((Date) value);
                    CellStyle dateStyle = workbook.createCellStyle();
                    dateStyle.setDataFormat(workbook.createDataFormat().getFormat("yyyy/MM/dd"));
                    cell.setCellStyle(dateStyle);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                    e = sdf.format(value);
                    cell.setCellValue((String) e);
                } else if (value instanceof Double) {
                    cell.setCellValue((Double) value);
                    CellStyle dateStyle = workbook.createCellStyle();
                    dateStyle.setDataFormat(workbook.createDataFormat().getFormat("0.00"));
                    cell.setCellStyle(dateStyle);
                    DecimalFormat df = new DecimalFormat("0.00");
                    e = df.format(value);
                } else if (value instanceof String) {
                    cell.setCellValue((String) value);
                    e = String.valueOf(value);
                } else if (value instanceof Integer) {
                    cell.setCellValue((Integer) value);
                    e = String.valueOf(value);
                } else if (value instanceof BigDecimal) {
                    cell.setCellValue(((BigDecimal) value).setScale(2, RoundingMode.HALF_UP).doubleValue());
                    CellStyle dateStyle = workbook.createCellStyle();
                    dateStyle.setDataFormat(workbook.createDataFormat().getFormat("0.00"));
                    cell.setCellStyle(dateStyle);
                    DecimalFormat format = new DecimalFormat("0.00");
                    e = format.format(value);
                } else if (value instanceof Long) {
                    cell.setCellValue((Long) value);
                    e = String.valueOf(value);
                }
                //判断上一行指定列的内容是不是和当前行的指定列是不是一样
                if (String.valueOf(sheet.getRow(r - 1).getCell(referCol)).equals(String.valueOf(sheet.getRow(r).getCell(referCol)))) {
                    // 判断当前行与上一行的内容是不是一样
                    if ((r) != list.size() && String.valueOf(sheet.getRow((r - 1)).getCell(j)).equals(String.valueOf(e))) {
                        recordList.set(j, recordList.get(j) + 1);// list中第几个列表的数值累加
                    }
                } else {
                    //判断要合并的单元格是否是一个
                    if ((r - (r - recordList.get(j))) > 0) {
                        //需要合并的单元个数
                        int record = recordList.get(j);
                        //合并的单元格
                        sheet.addMergedRegion(new CellRangeAddress(((r - 1) - record), (r - 1), j, j));
                        recordList.set(j, 0);//当前列的合并单元格归零
                    } else {
                        recordList.set(j, 0);//当前列的合并单元格归零
                    }
                }
                j++;
            }
            r++;
        }
        // 总计
        if (sumCols != null) {
            row = sheet.createRow(r);
            for (int i = 0; i < header.length; i++) {
                cell = row.createCell(i);
                if (i == 0) cell.setCellValue("合计");
            }
            for (String value : sumCols) {
                int index = findIndex(header, value);
                if (index > 0) {
                    cell = row.getCell(index);
                    cell.setCellFormula("SUM(" + (char) ('A' + index) + "3:" + (char) ('A' + index) + (3 + list.size() - 1) + ")");
                    cell.setCellType(CellType.FORMULA);
                }
            }
        }
        for (int i = 0; i < header.length; i++) {
            // 宽度自适应
            sheet.autoSizeColumn(i);
        }
        return workbook;
    }

    private static int findIndex(String[] list, String e) {
        for (int i = 0; i < list.length; i++) {
            if (e.equals(list[i])) {
                return i;
            }
        }
        return -1;
    }

    /**
     * poi导出表格
     *
     * @param titleNmae
     * @param headColumnName
     * @param mapList
     * @param sumCols
     * @return
     */
    public static XSSFWorkbook poiExportExcel(String titleNmae, String[] headColumnName, List<LinkedHashMap<String, Object>> mapList, String[] sumCols) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();
        XSSFRow row = sheet.createRow(0);
        XSSFCell cell = row.createCell(0);
        cell.setCellValue(titleNmae);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, headColumnName.length - 1));
        // 设置列宽
        for (int i = 0; i < headColumnName.length; i++) {
            sheet.setColumnWidth(i, 18 * 256);
        }
        for (int i = 0; i < headColumnName.length; i++) {
            cell = sheet.createRow(1).createCell(i);
            cell.setCellValue(headColumnName[i]);
        }
        // 设置正文内容
        int r = 2;
        for (int i = 0, len = mapList.size(); i < len; i++) {// 循环创建数据行
            row = sheet.createRow(r);
            for (int j = 0; j < headColumnName.length; j++) {
                Object value = mapList.get(i).get(headColumnName[j]);
                cell = row.createCell(j);
                if (value instanceof Date) {
                    cell.setCellValue((Date) value);
                    CellStyle dateStyle = workbook.createCellStyle();
                    dateStyle.setDataFormat(workbook.createDataFormat().getFormat("yyyy/MM/dd"));
                    cell.setCellStyle(dateStyle);
                } else if (value instanceof Double) {
                    cell.setCellValue((Double) value);
                    CellStyle dateStyle = workbook.createCellStyle();
                    dateStyle.setDataFormat(workbook.createDataFormat().getFormat("0.00"));
                    cell.setCellStyle(dateStyle);
                } else if (value instanceof String) {
                    cell.setCellValue((String) value);
                } else if (value instanceof Integer) {
                    cell.setCellValue((Integer) value);
                } else if (value instanceof BigDecimal) {
                    cell.setCellValue(((BigDecimal) value).setScale(2, RoundingMode.HALF_UP).doubleValue());
                    CellStyle dateStyle = workbook.createCellStyle();
                    dateStyle.setDataFormat(workbook.createDataFormat().getFormat("0.00"));
                    cell.setCellStyle(dateStyle);
                } else if (value instanceof Long) {
                    cell.setCellValue((Long) value);
                }
            }
            r++;
        }
        // 总计
        if (sumCols != null) {
            row = sheet.createRow(r);
            for (int i = 0; i < headColumnName.length; i++) {
                cell = row.createCell(i);
                if (i == 0) cell.setCellValue("合计");
            }
            for (String value : sumCols) {
                int index = findIndex(headColumnName, value);
                if (index > 0) {
                    cell = row.getCell(index);
                    cell.setCellFormula("SUM(" + (char) ('A' + index) + "3:" + (char) ('A' + index) + (3 + mapList.size() - 1) + ")");
                    cell.setCellType(CellType.FORMULA);
                }
            }
        }
        for (int i = 0; i < headColumnName.length; i++) {
            // 宽度自适应
            sheet.autoSizeColumn(i);
        }
        return workbook;
    }

    /**
     * 向excel中插入一列
     * @param xwb XSSFWorkbook 对象
     * @param sheetName sheet名称
     * @param insertStartRow 从第几行插入新的一行
     * @param insertCell 向第几个单元格插入值
     * @param content 要插入的值
     * @return
     */
    public static XSSFWorkbook insertRows(XSSFWorkbook xwb,String sheetName,int insertStartRow,short insertCell,String content){
        XSSFSheet sheet = StringUtils.isEmpty(sheetName)?xwb.getSheetAt(0):xwb.getSheet(sheetName);
        XSSFRow row = null;
        if (sheet.getRow(insertStartRow) != null) {
            int lastRowNo = sheet.getLastRowNum();
            sheet.shiftRows(insertStartRow, lastRowNo, 1);
        }
        row = sheet.createRow(insertStartRow);
        XSSFCell cell = row.createCell((short) insertCell);
        cell.setCellValue(content);
        return xwb;
    }

    /**
     * 删除excel指定的一列
     * @param xwb XSSFWorkbook对象
     * @param sheetName sheet名称
     * @param deleteRow 要删除的哪一行
     * @return
     */
    public static XSSFWorkbook deleteRow(XSSFWorkbook xwb,String sheetName,int deleteRow){
        XSSFSheet sheet = StringUtils.isEmpty(sheetName)?xwb.getSheetAt(0):xwb.getSheet(sheetName);
        XSSFRow row = null;
        if (sheet.getRow(deleteRow) != null) {
            int lastRowNo = sheet.getLastRowNum();
            sheet.shiftRows(deleteRow, lastRowNo, -1);
        }
        return xwb;
    }

}