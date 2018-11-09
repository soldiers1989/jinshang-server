package project.jinshang.mod_product;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.utils.GenerateNo;
import project.jinshang.mod_admin.mod_upload.ProductNameAttrImport;
import project.jinshang.mod_product.bean.Attributetbl;
import project.jinshang.mod_product.bean.Attvalue;
import project.jinshang.mod_product.service.AttributetblService;
import project.jinshang.mod_product.service.AttvalueService;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/rest/admin/attvalue")
@SessionAttributes({AppConstant.ADMIN_SESSION_NAME})
@Api(tags = "后台品名属性管理属性excel导入", description = "后台品名属性管理属性导入接口")
@Transactional(rollbackFor = Exception.class)
public class AttvalueAction {

    @Autowired
    private AttributetblService attributetblService;

    @Autowired
    private AttvalueService attvalueService;

    @Autowired
    private ProductNameAttrImport productNameAttrImport;

    @Value("${upload.dir.moduleIcon}")
    private  String uploadPath;



    @PostMapping("/excelimport")
    @ApiOperation("excel导入品名管理属性")
    //@PreAuthorize("hasAuthority('" + AdminAuthorityConst.RETURNVISITEXCEL + "')")
    public synchronized BasicRet addProductsByMould(@RequestParam("file") CommonsMultipartFile file, @RequestParam("attributetblid") long attributetblid) throws Exception {
        if(!file.getOriginalFilename().endsWith(".xlsx") && !file.getOriginalFilename().endsWith(".xls")){
            return  new BasicRet(BasicRet.ERR,"请上传Excel文件");
        }
        Attributetbl attributetbl = attributetblService.selectByPrimaryKey(attributetblid);
        if(attributetbl == null){
            return  new BasicRet(BasicRet.ERR,"属性名不存在");
        }

        String fileName= GenerateNo.getUUID()+file.getOriginalFilename();

        File dir =  new File(uploadPath);
        if(!dir.exists()){
            dir.mkdirs();
        }

        File newFile= new File(dir,fileName);
        //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
        List<Attvalue> attvalueList;
        try {
            file.transferTo(newFile);
            attvalueList =  productNameAttrImport.excelAttvalueTo(newFile.getAbsolutePath());
        } catch (Exception e) {
            throw  e;
        } finally {
            newFile.delete();
        }

        if(attvalueList.size()>0){
            for(Attvalue attvalue : attvalueList){
                   //同一个属性id 里面“值”重复的内容的话就去更新
                   Attvalue oldAttvalue =  attvalueService.getByAttidAndValue(attributetbl.getId(),attvalue.getParamvalue());
                    if(oldAttvalue !=null){
                        Attvalue newAttvalue = new Attvalue();
                        newAttvalue.setId(oldAttvalue.getId());
                        newAttvalue.setParamvalue(attvalue.getParamvalue());
                        newAttvalue.setMark(attvalue.getMark());
                        newAttvalue.setSort(attvalue.getSort());
                        attvalueService.updateById(newAttvalue);
                    }else {
                        Attvalue newAttvalue = new Attvalue();
                        newAttvalue.setParamvalue(attvalue.getParamvalue());
                        newAttvalue.setAttid(attributetbl.getId());
                        newAttvalue.setMark(attvalue.getMark());
                        newAttvalue.setSort(attvalue.getSort());
                        attvalueService.insertSelective(newAttvalue);
                    }

                }
          }

        return  new BasicRet(BasicRet.SUCCESS,"导入成功");
    }
}
