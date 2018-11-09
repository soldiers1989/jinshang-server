package project.jinshang.mod_member;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import project.jinshang.common.bean.PageRet;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.constant.SellerAuthorityConst;
import project.jinshang.common.utils.WaterMarkUtils;
import project.jinshang.config.OssManageUtil;
import project.jinshang.mod_common.bean.BasicExtRet;
import project.jinshang.mod_member.bean.*;
import project.jinshang.mod_member.service.ImageGroupService;
import project.jinshang.mod_member.service.ImageRecordService;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/rest/seller/image")
@Api(tags = "卖家端图片库上传模块",description = "卖家端图片上传接口")
@SessionAttributes(AppConstant.MEMBER_SESSION_NAME)
public class SellerImageUploadAction {
    private static Logger logger = LoggerFactory.getLogger(SellerImageUploadAction.class);

    @Autowired
    private OssManageUtil ossManageUtil;

    @Autowired
    private ImageGroupService imageGroupService;

    @Autowired
    private ImageRecordService imageRecordService;


    /***
     * 单图片上传
     *
     * @param file
     * @return
     */
    @PostMapping("/uploadOneImage")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "flag", value = "是否需要加水印", required = true, paramType = "query", dataType = "boolean"),
            @ApiImplicitParam(name = "imagegroupid", value = "图片分组名id", required = true, paramType = "query", dataType = "long"),
    })
    @PreAuthorize(" hasAuthority('"+ SellerAuthorityConst.ALL+"')")
    public OneImageRet watermark(@RequestParam(value = "file", required = true) CommonsMultipartFile file,@RequestParam(value = "flag", required = true) boolean flag,@RequestParam(value = "imagegroupid", required = true) long imagegroupid,Model model) throws IOException, ParseException {
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        OneImageRet oneImageRet = new OneImageRet();
        ImageGroup imageGroup = imageGroupService.selectById(imagegroupid);
        if(imageGroup == null){
            oneImageRet.setResult(BasicRet.ERR);
            oneImageRet.setMessage("分组名不存在");
            return oneImageRet;
        }
        if(!imageGroup.getMemberid().equals(member.getId())){
            oneImageRet.setResult(BasicRet.ERR);
            oneImageRet.setMessage("上传的图片分组不是你的");
        }
        if(file !=null && file.getSize()>0) {
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            String nowDate = dateFormat.format(new Date());
          String ossurl =  ossManageUtil.uploadImage(file,flag,member.getId(),nowDate);
          oneImageRet.data.setOssurl(ossurl);

          ImageRecord imageRecord = new ImageRecord();
          imageRecord.setMemberid(member.getId());
          imageRecord.setImagegroupid(imagegroupid);
          imageRecord.setImageurl(ossurl);
          imageRecord.setCreatetime(new Date());
          imageRecord.setUpdatetime(new Date());
          imageRecord.setStatus(Quantity.STATE_1);
          imageRecordService.insertImageRecord(imageRecord);
        }else{
            oneImageRet.setResult(BasicRet.ERR);
            oneImageRet.setMessage("上传单张图片不能为空");
        }
            oneImageRet.setResult(BasicRet.SUCCESS);
            oneImageRet.setMessage("上传单张图片成功");

        return oneImageRet;
    }


    /**
     * 图片批量上传
     *
     * @param
     * @param
     * @return
     */
    @PostMapping("/uploadeMoreImage")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "flag", value = "是否需要加水印", required = true, paramType = "query", dataType = "boolean"),
            @ApiImplicitParam(name = "imagegroupid", value = "图片分组名id", required = true, paramType = "query", dataType = "long"),
    })
    @PreAuthorize(" hasAuthority('"+ SellerAuthorityConst.ALL+"')")
    public MoreImageRet morewatermark(@RequestParam(value = "files", required = true)List<CommonsMultipartFile> files,@RequestParam(value = "flag", required = false) boolean flag,@RequestParam(value = "imagegroupid", required = true) long imagegroupid,Model model) throws IOException, ParseException {

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        MoreImageRet moreImageRet = new MoreImageRet();
        if (files != null && files.size() > 0) {
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            String nowDate = dateFormat.format(new Date());
            List<ImageInfo> imageInfoList = new ArrayList<ImageInfo>();
            for (CommonsMultipartFile file : files) {
                if(file == null || file.getSize() < 1){
                    continue;
                }
                ImageInfo imageInfo = new ImageInfo();
                DiskFileItem fi = null;
                InputStream is = null;
                ByteArrayOutputStream bs = null;
                ImageOutputStream imOut = null;
                //如果为true是需要加水印 如果false为不需要加水印 上传
                if(flag) {
                    //CommonsMultipartFile转File
                    fi = (DiskFileItem) file.getFileItem();
                    File imagefile = fi.getStoreLocation();
                    //获取水印图片模板
                    //String watermarkfileName = this.getClass().getClassLoader().getResource("images/watermark.png").getPath();
                    //String watermarkfileName = this.getClass().getResource("/").getPath()+"images/"+"watermark.png";
                    // String watermarkfileName = this.getClass().getResource(File.separator).getPath()+"images"+File.separator+"watermark.png";

                    /*Resource resource = new ClassPathResource("images/watermark.png");
                    BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()));*/

                    ClassPathResource classPathResource = new ClassPathResource("images/watermark.png");
                    InputStream inputStream = classPathResource.getInputStream();

                    /*System.out.println("watermarkfileNamewwwwww"+watermarkfileName);
                    logger.info("watermarkfileNamewwwwww"+watermarkfileName);*/

                    //添加水印过程
                    //获取文件流 给下面转换用
                    BufferedImage bufferedImage = WaterMarkUtils.watermakeOneImage1(imagefile, file.getOriginalFilename(), inputStream);
                    bs = new ByteArrayOutputStream();

                    imOut = ImageIO.createImageOutputStream(bs);

                    ImageIO.write(bufferedImage, "jpg", imOut); //scaledImage1为BufferedImage，jpg为图像的类型
                    is = new ByteArrayInputStream(bs.toByteArray());
                }
                //上传到oss上
                try {
                    String ossurl;
                    //如果为true是需要加水印 如果false为不需要加水印 上传
                    if(flag) {
                        // 流转换 转换为oss所需的InputStream
                        InputStream fileContent = is;
                        String fileName = fi.getName();
                        ossurl = ossManageUtil.uploadFileImage(fileContent, "images/data"+"/"+member.getId(), fileName,member.getId(),nowDate);
                    }
                    else{
                        // 流转换 转换为oss所需的InputStream
                        DiskFileItem fi2 = (DiskFileItem) file.getFileItem();
                        InputStream fileContent = fi2.getInputStream();
                        String fileName = fi2.getName();
                        //传入文件名 进行重命名，返回oss url 且根据用户id作为文件夹命名 判断文件夹是否已经存在
                        ossurl = ossManageUtil.uploadFileImage(fileContent, "images/data"+"/"+member.getId(), fileName,member.getId(),nowDate);
                    }

                    ImageRecord imageRecord = new ImageRecord();
                    imageRecord.setMemberid(member.getId());
                    imageRecord.setImagegroupid(imagegroupid);
                    imageRecord.setImageurl(ossurl);
                    imageRecord.setCreatetime(new Date());
                    imageRecord.setUpdatetime(new Date());
                    imageRecord.setStatus(Quantity.STATE_1);
                    imageRecordService.insertImageRecord(imageRecord);

                    imageInfo.setOssurl(ossurl);
                    imageInfoList.add(imageInfo);
                    moreImageRet.data.setImageInfoList(imageInfoList);

                } catch (Exception e) {
                    throw new RuntimeException("上传失败");
                }finally {
                    if(flag) {
                        bs.close();
                        imOut.close();
                        is.close();
                    }
                }
            }
        }else{
            moreImageRet.setResult(BasicRet.ERR);
            moreImageRet.setMessage("上传多张图片不能为空");
        }
        moreImageRet.setResult(BasicRet.SUCCESS);
        moreImageRet.setMessage("上传多张图片成功");
        return moreImageRet;
    }


    private class OneImageRet extends BasicRet {
        private class OneImageData {
            private String watermarkimageurl;

            private String ossurl;

            public String getWatermarkimageurl() {
                return watermarkimageurl;
            }

            public void setWatermarkimageurl(String watermarkimageurl) {
                this.watermarkimageurl = watermarkimageurl;
            }

            public String getOssurl() {
                return ossurl;
            }

            public void setOssurl(String ossurl) {
                this.ossurl = ossurl;
            }
        }

        private OneImageRet.OneImageData data = new OneImageRet.OneImageData();

        public OneImageRet.OneImageData getData() {
            return data;
        }

        public void setData(OneImageRet.OneImageData data) {
            this.data = data;
        }
    }

    private class MoreImageRet extends BasicRet {
        private class MoreImageData {
            List<ImageInfo> imageInfoList = new ArrayList<ImageInfo>();

            public List<ImageInfo> getImageInfoList() {
                return imageInfoList;
            }

            public void setImageInfoList(List<ImageInfo> imageInfoList) {
                this.imageInfoList = imageInfoList;
            }
        }

        private MoreImageRet.MoreImageData data = new MoreImageRet.MoreImageData();

        public MoreImageRet.MoreImageData getData() {
            return data;
        }

        public void setData(MoreImageRet.MoreImageData data) {
            this.data = data;
        }
    }


    @PostMapping(value = "/insertgroup")
    @ApiOperation(value = "新增分组")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "groupname", value = "分组名", required = true, paramType = "query", dataType = "string"),
    })
    @PreAuthorize(" hasAuthority('"+ SellerAuthorityConst.ALL+"')")
    public BasicRet insertImageGroupInfo(ImageGroup imageGroup, Model model) {
        BasicRet basicRet = new BasicRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        ImageGroupExample example = new ImageGroupExample();
        ImageGroupExample.Criteria criteria = example.createCriteria();
        criteria.andGroupnameEqualTo(imageGroup.getGroupname()).andStatusEqualTo(Quantity.STATE_1).andMemberidEqualTo(member.getId());
        long count = imageGroupService.countByExample(example);
        if (count > 0) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("新增失败,分组名已存在");
            return basicRet;
        }

        imageGroup.setMemberid(member.getId());
        imageGroup.setCreatetime(new Date());
        imageGroup.setUpdatetime(new Date());
        imageGroup.setStatus(Quantity.STATE_1);
        imageGroupService.insertImageGroupInfo(imageGroup);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("新增成功");
        return basicRet;
    }

    @PostMapping(value = "/updategroup")
    @ApiOperation(value = "修改分组名称")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "groupname", value = "分组名", required = true, paramType = "query", dataType = "string"),
    })
    @PreAuthorize(" hasAuthority('"+ SellerAuthorityConst.ALL+"')")
    public BasicRet updateImageGroupInfo(ImageGroup imageGroup,Model model) {
        BasicRet basicRet = new BasicRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        ImageGroup oldImageGroup = imageGroupService.selectById(imageGroup.getId());

        ImageGroupExample example = new ImageGroupExample();
        ImageGroupExample.Criteria criteria = example.createCriteria();
        criteria.andGroupnameEqualTo(imageGroup.getGroupname()).andStatusEqualTo(Quantity.STATE_1).andMemberidEqualTo(member.getId());
        long count = imageGroupService.countByExample(example);
        Date nowdate = new Date();
        if (oldImageGroup == null) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("不存在该分组");
        }else if(!oldImageGroup.getMemberid().equals(member.getId())){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("这个分组不属于你,不允许修改");
        }else if (count > 0) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("新增失败,分组名已存在");
            return basicRet;
        }else {
            imageGroup.setUpdatetime(new Date());
            imageGroupService.updateImageGroupInfo(imageGroup);
            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("修改成功");
        }
        return basicRet;
    }

    @PostMapping(value = "/deletegroup")
    @ApiOperation(value = "删除分组名称")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", required = true, paramType = "query", dataType = "long"),
    })
    @PreAuthorize(" hasAuthority('"+ SellerAuthorityConst.ALL+"')")
    public BasicRet deleteImageGroupInfo(long id,Model model) {
        BasicRet basicRet = new BasicRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        ImageGroup oldImageGroup = imageGroupService.selectById(id);
        //查询到如果imagerecord表还有这个分组的id且图片状态为1=正常的则不允许删除该分组且是此用户的id
        List<ImageRecord> imageRecordList = imageRecordService.selectByGroupIdAndStatus(id,member.getId());
        if (oldImageGroup == null) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("不存在该分组");
        }else if(!oldImageGroup.getMemberid().equals(member.getId())){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("这个分组不属于你,不允许删除");
        }else if(imageRecordList != null && imageRecordList.size()>0){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("该分组还有图片,不允许删除该分组");
        }else {
            //该分组下的将图片记录的全部设置为已删除状态
            List<ImageRecord> list = imageRecordService.selectByGroupIdAndStatus(id,member.getId());
            if(list!=null & list.size()>0) {
                for (ImageRecord imageRecord:list){
                    ImageRecord updateImageRecord = new ImageRecord();
                    updateImageRecord.setId(imageRecord.getId());
                    updateImageRecord.setStatus(Quantity.STATE_2);
                    imageRecordService.updateImageRecord(updateImageRecord);
                }
            }
            ImageGroup updateImageGroup = new ImageGroup();
            updateImageGroup.setId(id);
            updateImageGroup.setStatus(Quantity.STATE_2);
            updateImageGroup.setUpdatetime(new Date());
            imageGroupService.updateImageGroupInfo(updateImageGroup);

            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("删除成功");
        }
        return basicRet;
    }


    @PostMapping(value = "/getdetail")
    @ApiOperation(value = "获取分组详情")
    @ApiImplicitParam(name = "id", value = "分组id", required = true, paramType = "query", dataType = "int")
    @PreAuthorize(" hasAuthority('"+ SellerAuthorityConst.ALL+"')")
    public BasicRet getdetail(Long id,Model model) {
        BasicExtRet basicRet = new BasicExtRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        ImageGroup imageGroup = imageGroupService.selectById(id);
        if (imageGroup == null) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("不存在该分组");
        } else if(!imageGroup.getMemberid().equals(member.getId())){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("这个分组不属于你,不允许查看详情");
        }else {
            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("获取成功");
            basicRet.setData(imageGroup);
        }
        return basicRet;
    }

    @PostMapping("/grouplist")
    @ApiOperation(value = "查看自己所有分组信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "图片库分组表主键id", required = false, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "groupname", value = "分组名", required = false, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "pageNo", value = "页码(值为-1不分页)", required = true, paramType = "query", dataType = "int", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页数", required = false, paramType = "query", dataType = "int", defaultValue = "20"),
    })
    @PreAuthorize(" hasAuthority('"+ SellerAuthorityConst.ALL+"')")
    public PageRet grouplist(ImageGroup imageGroup,Model model,int pageNo, int pageSize) {
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        imageGroup.setMemberid(member.getId());
        imageGroup.setStatus(Quantity.STATE_1);
        PageInfo pageInfo = imageGroupService.selectByObject(imageGroup,pageNo,pageSize);
        PageRet pageRet = new PageRet();
        pageRet.data.setPageInfo(pageInfo);
        pageRet.setMessage("返回成功");
        pageRet.setResult(BasicRet.SUCCESS);
        return pageRet;
    }


    @PostMapping(value = "/deleteimagerecord")
    @ApiOperation(value = "删除某一条图片记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", required = true, paramType = "query", dataType = "long"),
    })
    @PreAuthorize(" hasAuthority('"+ SellerAuthorityConst.ALL+"')")
    public BasicRet deleteImageRecordInfo(long id,Model model) {
        BasicRet basicRet = new BasicRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        ImageRecord oldImageRecord = imageRecordService.selectById(id);
        if (oldImageRecord == null) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("不存在该图片记录");
        }else if(!oldImageRecord.getMemberid().equals(member.getId())){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("这个图片记录不属于你,不允许删除");
        }else if(oldImageRecord.getStatus() == Quantity.STATE_2 ){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("该图片已经是删除状态，不需要再删除了");
        }else {
            ImageRecord updateImageRecord = new ImageRecord();
            updateImageRecord.setId(id);
            updateImageRecord.setStatus(Quantity.STATE_2);
            updateImageRecord.setUpdatetime(new Date());
            imageRecordService.updateImageRecord(updateImageRecord);
            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("删除成功");
        }
        return basicRet;
    }


    @PostMapping(value = "/deletemoreimagerecord")
    @ApiOperation(value = "删除多条图片记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "图片记录ID集合", required = true, paramType = "query", dataType = "Array"),
    })
    @PreAuthorize(" hasAuthority('"+ SellerAuthorityConst.ALL+"')")
    public BasicRet deleteMoreImageRecord(Long[] ids,Model model) {
        BasicRet basicRet = new BasicRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        List<ImageRecord> list = imageRecordService.selectByIdsAndMemberid(Arrays.asList(ids),member.getId());
        for (ImageRecord imageRecord: list) {
            ImageRecord updateImageRecord = new ImageRecord();
            updateImageRecord.setId(imageRecord.getId());
            updateImageRecord.setStatus(Quantity.STATE_2);
            updateImageRecord.setUpdatetime(new Date());
            imageRecordService.updateImageRecord(updateImageRecord);
        }
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("删除多条图片记录成功");
        return basicRet;
    }


    @PostMapping("/list")
    @ApiOperation(value = "查看全部图片或者查看一个分组下的图片")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "imagegroupid", value = "图片分组id(不传为查看全部图片)", required = false, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "pageNo", value = "页码(值为-1不分页)", required = true, paramType = "query", dataType = "int", defaultValue = "-1"),
            @ApiImplicitParam(name = "pageSize", value = "页数", required = false, paramType = "query", dataType = "int", defaultValue = "20"),
    })
    public PageRet getList(int pageNo, int pageSize, ImageRecord imageRecord,Model model) {
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        imageRecord.setStatus(Quantity.STATE_1);
        imageRecord.setMemberid(member.getId());
        PageInfo pageInfo = imageRecordService.selectByObejct(pageNo, pageSize,imageRecord);
        PageRet pageRet = new PageRet();
        pageRet.data.setPageInfo(pageInfo);
        pageRet.setMessage("返回成功");
        pageRet.setResult(BasicRet.SUCCESS);
        return pageRet;
    }

}
