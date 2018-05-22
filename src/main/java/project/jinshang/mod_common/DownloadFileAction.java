package project.jinshang.mod_common;

import io.swagger.annotations.Api;
import mizuki.project.core.restserver.config.BasicRet;
import mizuki.project.core.restserver.util.JsonUtil;
import mizuki.project.core.restserver.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * create : wyh
 * date : 2018/1/18
 */
@Controller
@RequestMapping("/rest")
@Api(tags = "文件下载")
public class DownloadFileAction {
    @Autowired
    private ApplicationContext context;

    /**
     * 文件下载
     */
    @RequestMapping("/download")
    public ResponseEntity<InputStreamResource> downloadFile(
            @RequestParam("fileName") String fileName
    ) throws IOException {
        try {
            Resource resource = context.getResource("classpath:download/"+fileName);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            fileName=new String(fileName.getBytes("utf-8"),"iso-8859-1"); //解决中文乱码问题
            headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", fileName));
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");
            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(new InputStreamResource(resource.getInputStream()));
        }catch (Exception e){
            return null;
        }


//        System.out.println(fileName);
//        File file = null;
//        try {
//            Resource resource = context.getResource("classpath:download/"+fileName);
//            System.out.println(resource);
//            file = resource.getFile();
////            file = ResourceUtils.getFile("classpath:download/"+fileName);
//            System.out.println("文件路径："+file.getAbsolutePath());
//        } catch (FileNotFoundException e) {
//            ResponseUtil.setCross(response,request);
//            response.getWriter().print(JsonUtil.toJson(new BasicRet(BasicRet.ERR,"文件不存在")));
//            return;
//        }
//
//        fileName=new String(file.getName().getBytes("utf-8"),"iso-8859-1"); //解决中文乱码问题
//
//        if (file.exists()) {
//            response.setContentType("application/force-download");// 设置强制下载不打开
//            response.addHeader("Content-Disposition",
//                    "attachment;fileName=" + fileName);// 设置文件名
//            byte[] buffer = new byte[1024];
//            FileInputStream fis = null;
//            BufferedInputStream bis = null;
//            try {
//                fis = new FileInputStream(file);
//                bis = new BufferedInputStream(fis);
//                OutputStream os = response.getOutputStream();
//                int i = bis.read(buffer);
//                while (i != -1) {
//                    os.write(buffer, 0, i);
//                    i = bis.read(buffer);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                if (bis != null) {
//                    try {
//                        bis.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//                if (fis != null) {
//                    try {
//                        fis.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }

    }

}
