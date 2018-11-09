package project.jinshang.config;


import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import mizuki.project.core.restserver.modules.oss_ali.AliOSS;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import project.jinshang.common.utils.DateUtils;
import project.jinshang.common.utils.WaterMarkUtils;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.*;
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
     * 用于apk上传
     * @param fileContent
     * @param remotePath  上传的文件 remotePath @param oss服务器二级目录
     * @param fileName  上传名称
     * @return
     * @throws Exception
     */
    public  String uploadFile(InputStream fileContent, String remotePath, String fileName,String versionname,String channel) throws Exception {
        //随机名处理

        fileName =   "JsAndroid_" + DateUtils.format(new Date(),"yyyyMMdd")+"_"+ versionname+"_"+channel+fileName.substring(fileName.lastIndexOf("."));

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
                || FilenameExtension.equals(".jpg")) {
            return "image/jpeg";
        }
        if( FilenameExtension.equals(".PNG") || FilenameExtension.equals(".png")){
            return "image/png";
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



    /**
     * 图片库 上传图片且必须检查文件夹是否存在
     * @param fileContent
     * @param remotePath  上传的文件 remotePath @param oss服务器二级目录
     * @param fileName 上传名称
     * @param id 要创建的文件夹名为id
     * @return
     * @throws Exception
     */
    public  String uploadFileImage(InputStream fileContent, String remotePath, String fileName,long id,String nowDate) throws Exception {
        //随机名处理
        fileName = "js_" + new Date().getTime() + fileName.substring(fileName.lastIndexOf("."));



        OSSClient ossClient = new OSSClient(aliOSS.getEndpoint(), aliOSS.getAccessKey(),
                aliOSS.getAccessKeySecret());

        //检查二级路径是否存在
        OSSClient checkOssClient = new OSSClient(aliOSS.getEndpoint(), aliOSS.getAccessKey(), aliOSS.getAccessKeySecret());
        Boolean flag = checkFolder(checkOssClient,id,nowDate);
        String remoteFilePath;
        if(flag = true) {
            //存在则直接上传
            // 定义二级目录
            remoteFilePath = remotePath.substring(0, remotePath.length()).replaceAll("\\\\", "/") + "/";
        }else{
            //不存在则创建文件夹后再上传
            createFolder(checkOssClient,id,nowDate);
            remoteFilePath = remotePath.substring(0, remotePath.length()).replaceAll("\\\\", "/") + "/";
        }


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
        return remoteFilePath + fileName;
    }

    /**
     * 图片库功能 OSS创建形式上的文件夹
     * @param client
     */
    public void createFolder(OSSClient client,long id,String nowDate) {
        String bucketName = aliOSS.getBucketName();
        // 要创建的文件夹名称,在满足Object命名规则的情况下以"/"结尾
        String objectName = "data/images/"+id+"/"+nowDate+"/";
        ObjectMetadata objectMeta = new ObjectMetadata();
        /*
         * 这里的size为0,注意OSS本身没有文件夹的概念,这里创建的文件夹本质上是一个size为0的Object,dataStream仍然可以有数据
         * 照样可以上传下载,只是控制台会对以"/"结尾的Object以文件夹的方式展示,用户可以利用这种方式来实现文件夹模拟功能,创建形式上的文件夹
         */
        byte[] buffer = new byte[0];
        ByteArrayInputStream in = new ByteArrayInputStream(buffer);
        objectMeta.setContentLength(0);
        try {
            client.putObject(bucketName, objectName, in, objectMeta);
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 图片库功能  检查OSS上的文件夹是否存在
     * @param client
     */
    public Boolean checkFolder(OSSClient client,long id,String nowDate) {
        String bucketName = aliOSS.getBucketName();
        String objectName = "data/images/"+id+"/"+nowDate+"/";
        boolean isObjectExist = client.doesObjectExist(bucketName, objectName);
        //System.out.println("rs:"+isObjectExist);
        if (isObjectExist) {
            //存在,可以上传
            return isObjectExist;
        } else {
            //不存在，则需要进行创建
            return isObjectExist;
        }
    }


    /**
     * 图片库功能 直接上传图片或者加水印上传
     * @param file
     * @param flag
     * @param id
     * @return
     * @throws IOException
     */
    public String uploadImage(CommonsMultipartFile file, boolean flag, long id,String nowDate) throws IOException {
        DiskFileItem fi = null;
        InputStream is = null;
        ByteArrayOutputStream bs = null;
        ImageOutputStream imOut = null;
        String ossurl;
        //如果为true是需要加水印 如果false为不需要加水印 上传
        if(flag) {
            //CommonsMultipartFile转File
            fi = (DiskFileItem) file.getFileItem();
            File imagefile = fi.getStoreLocation();
            //获取水印图片模板
            //String watermarkfileName = this.getClass().getClassLoader().getResource("images/watermark.png").getPath();
            ClassPathResource classPathResource = new ClassPathResource("images/watermark.png");
            InputStream inputStream = classPathResource.getInputStream();
            //添加水印过程
            //获取文件流 给下面转换用
            BufferedImage bufferedImage = WaterMarkUtils.watermakeOneImage1(imagefile, file.getOriginalFilename(),inputStream);
            bs = new ByteArrayOutputStream();

            imOut = ImageIO.createImageOutputStream(bs);

            ImageIO.write(bufferedImage, "jpg", imOut); //scaledImage1为BufferedImage，jpg为图像的类型
            is = new ByteArrayInputStream(bs.toByteArray());
        }
        //上传到oss上
        try {
            //如果为true是需要加水印 如果false为不需要加水印 上传
            if(flag) {
                // 流转换 转换为oss所需的InputStream
                InputStream fileContent = is;
                String fileName = fi.getName();
                ossurl = uploadFileImage(fileContent, "images/data"+"/"+id+"/"+nowDate, fileName,id,nowDate);
            }
            else{
                // 流转换 转换为oss所需的InputStream
                DiskFileItem fi2 = (DiskFileItem) file.getFileItem();
                InputStream fileContent = fi2.getInputStream();
                String fileName = fi2.getName();
                //传入文件名 进行重命名，返回oss url 且根据用户id作为文件夹命名 判断文件夹是否已经存在
                ossurl = uploadFileImage(fileContent, "images/data"+"/"+id+"/"+nowDate, fileName,id,nowDate);
            }
        } catch (Exception e) {
            throw new RuntimeException("上传失败");
        }finally {
            if(flag) {
                bs.close();
                imOut.close();
                is.close();
            }
        }
        return  ossurl;
    }
}
