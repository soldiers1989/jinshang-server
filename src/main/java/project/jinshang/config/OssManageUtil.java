package project.jinshang.config;


import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import mizuki.project.core.restserver.modules.oss_ali.AliOSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.jinshang.common.utils.DateUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 * 对OSS服务器进行上传删除等的处理 目前只用于apk上传
 *
 * @ClassName: OSSManageUtil
 * @Description:
 * @author zzy
 * @date 2018-8-13 上午10:47:00
 **/
@Component
public class OssManageUtil {
    @Autowired
    private AliOSS aliOSS;


    /**
     *
     * @param fileContent
     * @param remotePath  上传的文件 remotePath @param oss服务器二级目录
     * @param fileName  上传名称
     * @return
     * @throws Exception
     */
    public  String uploadFile(InputStream fileContent, String remotePath, String fileName,String versionname,String channel) throws Exception {
        //随机名处理

        fileName =   "JsAndroid_" + DateUtils.format(new Date(),"yyyyMMdd")+"_"+ versionname+"_"+channel+fileName.substring(fileName.lastIndexOf("."));

        // 加载配置文件，初始化OSSClient
       // OSSConfigure ossConfigure = new OSSConfigure("/system.properties");
       // OSSConfigure ossConfigure = new OSSConfigure();


        OSSClient ossClient = new OSSClient(aliOSS.getEndpoint(), aliOSS.getAccessKey(),
                aliOSS.getAccessKeySecret());
        // 定义二级目录
        String remoteFilePath = remotePath.substring(0, remotePath.length()).replaceAll("\\\\", "/") + "/";
        // 创建上传Object的Metadata
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(fileContent.available());
        objectMetadata.setContentEncoding("utf-8");
        objectMetadata.setCacheControl("no-cache");
        objectMetadata.setHeader("Pragma", "no-cache");
        objectMetadata.setContentType(contentType(fileName.substring(fileName.lastIndexOf("."))));
        objectMetadata.setContentDisposition("inline;filename=" + fileName);
        // 上传文件
        PutObjectResult putObjectResult = ossClient.putObject(aliOSS.getBucketName(), remoteFilePath + fileName, fileContent, objectMetadata);

        // 关闭OSSClient
        ossClient.shutdown();
        // 关闭io流
        fileContent.close();
        String newendPoint = null;
        //https和http判断
        if(aliOSS.getEndpoint().substring(0,5).equals("https")){
            newendPoint = aliOSS.getEndpoint().substring(0, 8) + aliOSS.getBucketName() + "." + aliOSS.getEndpoint().substring(8);
        }else {
            newendPoint = aliOSS.getEndpoint().substring(0, 7) + aliOSS.getBucketName() + "." + aliOSS.getEndpoint().substring(7);
        }
        return newendPoint + "/" + remoteFilePath + fileName;
    }


    /**
     * 下载文件
      */
    @SuppressWarnings("unused")
    public  void downloadFile(AliOSS aliOSS, String key, String filename)
            throws OSSException, ClientException, IOException {
        // 初始化OSSClient
        OSSClient ossClient = new OSSClient(aliOSS.getEndpoint(), aliOSS.getAccessKey(),
                aliOSS.getAccessKeySecret());
        OSSObject object = ossClient.getObject(aliOSS.getBucketName(), key);
        // 获取ObjectMeta
        ObjectMetadata meta = object.getObjectMetadata();

        // 获取Object的输入流
        InputStream objectContent = object.getObjectContent();

        ObjectMetadata objectData = ossClient.getObject(new GetObjectRequest(aliOSS.getBucketName(), key),
                new File(filename));
        // 关闭数据流
        objectContent.close();

    }

    /**
     * 根据key删除OSS服务器上的文件
     * @param filePath
     * @throws IOException
     */
    public  void deleteFile( String filePath) throws IOException {
        // 加载配置文件，初始化OSSClient
        //OSSConfigure ossConfigure = new OSSConfigure("/system.properties");
        //OSSConfigure ossConfigure = new OSSConfigure();
        AliOSS aliOSS = new AliOSS();
        OSSClient ossClient = new OSSClient(aliOSS.getEndpoint(), aliOSS.getAccessKey(),
                aliOSS.getAccessKeySecret());
        filePath=filePath.substring(45);
        ossClient.deleteObject(aliOSS.getBucketName(), filePath);

    }


    /**
     * Description: 判断OSS服务文件上传时文件的contentType @Version1.0
     *
     * @param FilenameExtension
     *            文件后缀
     * @return String
     */
    public  String contentType(String FilenameExtension) {
        if (FilenameExtension.equals(".BMP") || FilenameExtension.equals(".bmp")) {
            return "image/bmp";
        }
        if (FilenameExtension.equals(".GIF") || FilenameExtension.equals(".gif")) {
            return "image/gif";
        }
        if (FilenameExtension.equals(".JPEG") || FilenameExtension.equals(".jpeg") || FilenameExtension.equals(".JPG")
                || FilenameExtension.equals(".jpg") || FilenameExtension.equals(".PNG")
                || FilenameExtension.equals(".png")) {
            return "image/jpeg";
        }
        if (FilenameExtension.equals(".HTML") || FilenameExtension.equals(".html")) {
            return "text/html";
        }
        if (FilenameExtension.equals(".TXT") || FilenameExtension.equals(".txt")) {
            return "text/plain";
        }
        if (FilenameExtension.equals(".VSD") || FilenameExtension.equals(".vsd")) {
            return "application/vnd.visio";
        }
        if (FilenameExtension.equals(".PPTX") || FilenameExtension.equals(".pptx") || FilenameExtension.equals(".PPT")
                || FilenameExtension.equals(".ppt")) {
            return "application/vnd.ms-powerpoint";
        }
        if (FilenameExtension.equals(".DOCX") || FilenameExtension.equals(".docx") || FilenameExtension.equals(".DOC")
                || FilenameExtension.equals(".doc")) {
            return "application/msword";
        }
        if (FilenameExtension.equals(".XML") || FilenameExtension.equals(".xml")) {
            return "text/xml";
        }
        if (FilenameExtension.equals(".apk") || FilenameExtension.equals(".APK")) {
            return "application/octet-stream";
        }
        return "text/html";
    }





}
