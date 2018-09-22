package project.jinshang.common.utils;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.PutObjectResult;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PicTransfer2Oss {

    public static void main(String[] args) throws IOException {
//        System.out.println(new PicTransfer2Oss().getImageFromNetByUrl("http://resources.jinshang9.com/Storage/Shop/4017/Products/54383/1.png").length);
        XSSFWorkbook workbook = new XSSFWorkbook("/Users/ycj/Downloads/品牌图片.xlsx");
        XSSFSheet sheet = workbook.getSheetAt(0);
        List<String> list = new ArrayList<>();
        for(int i=0;i<=sheet.getLastRowNum();i++){
            XSSFRow row = sheet.getRow(i);
            list.add(row.getCell(0).getStringCellValue());
        }
        go(list);
    }

    public static void go(List<String> pathes) throws IOException {
        String accessKey="LTAIJkBUMwwBJ5ur";
        String accessKeySecret= "mvFFxILHpMj62iEcuU6B4tahXykV9q";
        String endpoint= "http://oss-cn-hangzhou.aliyuncs.com";
        String bucketName= "jinshang-hz";
        String region= "oss-cn-hangzhou";
        OSSClient ossClient = new OSSClient(endpoint, accessKey, accessKeySecret);
        List<String> errs = new ArrayList<>();
        for (String path:pathes){
            try {
                ossClient.putObject(bucketName, "dataOld" + path, new URL("http://resources.jinshang9.com" + path).openStream());
//                System.out.println(result);
                ossClient.setObjectAcl(bucketName, "dataOld" + path, CannedAccessControlList.PublicRead);
            }catch (Exception e){
                e.printStackTrace();
                errs.add(path);
            }
        }
        //System.out.println(errs);
        // 关闭client
        ossClient.shutdown();
    }

}
