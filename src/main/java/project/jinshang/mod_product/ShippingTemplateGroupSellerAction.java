package project.jinshang.mod_product;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.constant.SellerAuthorityConst;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_company.bean.SellerCompanyInfo;
import project.jinshang.mod_company.service.SellerCompanyInfoService;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_product.bean.ProductInfoExample;
import project.jinshang.mod_product.bean.ShippingTemplateGroup;
import project.jinshang.mod_product.service.ProductInfoService;
import project.jinshang.mod_product.service.ShippingTemplateGroupService;

import java.util.List;

/**
 * 卖家运费模板合集设置
 *
 * @author xiazy
 * @create 2018-07-17 18:46
 **/
@RestController
@RequestMapping("/rest/seller/shiptempgroup")
@Api(tags = "卖家运费模版合集设置")
@SessionAttributes({AppConstant.MEMBER_SESSION_NAME})
public class ShippingTemplateGroupSellerAction {
    @Autowired
    private ShippingTemplateGroupService shippingTemplateGroupService;
    @Autowired
    private SellerCompanyInfoService sellerCompanyInfoService;

    @Autowired
    private ProductInfoService productInfoService;

    @RequestMapping(value =  "/list",method = RequestMethod.POST)
    @ApiOperation("列出所有运费模板合集")
    @PreAuthorize("hasAuthority('"+ SellerAuthorityConst.SEND_WAY_COLLECTIONS+"') || hasAuthority('"+ SellerAuthorityConst.ALL+"')")
    public ShippingTemplateGroupListRet listShippingTemplateGroup(
            @RequestParam(required = true,defaultValue = "1")int pageNo,
            @RequestParam(required = true,defaultValue = "20") int pageSize,Model model){
        ShippingTemplateGroupListRet ret=new ShippingTemplateGroupListRet();
        Member member= (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        List<ShippingTemplateGroup> list=shippingTemplateGroupService.listShippingTemplateGroupByMemberid(pageNo,pageSize,member.getId());
        PageInfo pageInfo=new PageInfo(list);
        ret.data.setPageInfo(pageInfo);
        ret.setMessage("获取成功");
        ret.setResult(BasicRet.SUCCESS);
        return ret;
    }

    @RequestMapping(value =  "/add",method = RequestMethod.POST)
    @ApiOperation("新增运费模板合集")
    @PreAuthorize("hasAuthority('"+ SellerAuthorityConst.SEND_WAY_COLLECTIONS+"') || hasAuthority('"+ SellerAuthorityConst.ALL+"')")
    public BasicRet addShippingTemplateGroup(ShippingTemplateGroup shippingTemplateGroup,Model model){
        Member member= (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        BasicRet basicRet=new BasicRet();
        List<ShippingTemplateGroup> list=null;
        shippingTemplateGroup.setMemberid(member.getId());
        BasicRet basicRet1=this.checkIsSelfLifting(shippingTemplateGroup,member);
        if (basicRet1.getResult()!=1){
            return basicRet1;
        }
        String stgname=shippingTemplateGroup.getName();
        if (StringUtils.hasText(stgname)){
            list=shippingTemplateGroupService.getShippingTemplateGroupByName(stgname,member.getId());
        }
        if (list!=null&&list.size()>0){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("运费模板合集的名称不能重复");
            return basicRet;
        }
        shippingTemplateGroupService.addShippingTemplateGroup(shippingTemplateGroup);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("新增成功");
        return basicRet;
    }

    @RequestMapping(value =  "/get",method = RequestMethod.POST)
    @ApiOperation("获取运费模板合集详情")
    @PreAuthorize("hasAuthority('"+ SellerAuthorityConst.SEND_WAY_COLLECTIONS+"') || hasAuthority('"+ SellerAuthorityConst.ALL+"')")
    public ShippingTemplateGroupRet getShippingTemplateGroup(long id,Model model){
        ShippingTemplateGroupRet ret=new ShippingTemplateGroupRet();
        ShippingTemplateGroup shippingTemplateGroup=shippingTemplateGroupService.getShippingTemplateGroup(id);
        if (shippingTemplateGroup==null){
            ret.setMessage("运费模板合集不存在");
            ret.setResult(BasicRet.ERR);
            return ret;
        }
        ret.setData(shippingTemplateGroup);
        ret.setMessage("查询成功");
        ret.setResult(BasicRet.SUCCESS);
        return ret;

    }



    @RequestMapping(value =  "/update",method = RequestMethod.POST)
    @ApiOperation("修改运费模板合集")
    @PreAuthorize("hasAuthority('"+ SellerAuthorityConst.SEND_WAY_COLLECTIONS+"') || hasAuthority('"+ SellerAuthorityConst.ALL+"')")
    public BasicRet updateShippingTemplateGroup(ShippingTemplateGroup shippingTemplateGroup,Model model){
        Member member= (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        List<ShippingTemplateGroup> list=null;
        BasicRet basicRet=new BasicRet();
        BasicRet basicRet1=this.checkIsSelfLifting(shippingTemplateGroup,member);
        if (basicRet1.getResult()!=1){
            return basicRet1;
        }
        String stgname=shippingTemplateGroup.getName();
        if (StringUtils.hasText(stgname)){
            list=shippingTemplateGroupService.getShippingTemplateGroupByName(stgname,member.getId());
        }
        if (list!=null&&list.size()>0&&list.get(0).getId().compareTo(shippingTemplateGroup.getId())!=0){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("运费模板合集的名称不能重复");
            return basicRet;
        }
        shippingTemplateGroupService.updateShippingTemplateGroup(shippingTemplateGroup);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("更新成功");
        return basicRet;
    }

    @RequestMapping(value =  "/del",method = RequestMethod.POST)
    @ApiOperation("删除运费模板合集")
    @PreAuthorize("hasAuthority('"+ SellerAuthorityConst.SEND_WAY_COLLECTIONS+"') || hasAuthority('"+ SellerAuthorityConst.ALL+"')")
    public BasicRet delShippingTemplateGroup(long id,Model model){
        Member member= (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        ProductInfoExample example = new ProductInfoExample();
        ProductInfoExample.Criteria criteria = example.createCriteria();
        criteria.andShippingtemplatesgroupEqualTo(id);
        criteria.andPdstateNotEqualTo(Quantity.STATE_6);
        if(productInfoService.countByExample(example) >0){
            return  new BasicRet(BasicRet.ERR,"有商品正在使用该运费集合，不可删除");
        }

        shippingTemplateGroupService.delShippingTemplateGroup(id);
        BasicRet basicRet=new BasicRet();
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("更新成功");
        return basicRet;
    }



    public  BasicRet checkIsSelfLifting(ShippingTemplateGroup shippingTemplateGroup,Member member){
        BasicRet basicRet=new BasicRet();
        basicRet.setResult(BasicRet.SUCCESS);
        SellerCompanyInfo sellerCompanyInfo=sellerCompanyInfoService.getSellerCompanyByMemberid(member.getId());
        Boolean isselflifting=sellerCompanyInfo.getIsselflifting();
        Boolean isselflifting1=shippingTemplateGroup.getSelflifting();
//        if (isselflifting==null) isselflifting=false;
        if (sellerCompanyInfo!=null&&isselflifting.compareTo(false)==0){
            if (isselflifting1!=null&&isselflifting1.compareTo(true)==0){
                basicRet.setResult(BasicRet.ERR);
                basicRet.setMessage("商家不支持自提");
                return basicRet;
            }
        }
        if (shippingTemplateGroup.getSelflifting().compareTo(true)==0&&!StringUtils.hasText(shippingTemplateGroup.getSwitch1address())){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("自提开关已打开，需要填写自提地址");
            return basicRet;
        }
        return  basicRet;
    }


    private static class ShippingTemplateGroupListRet extends BasicRet {
        private class Data {
            private PageInfo pageInfo;
            public PageInfo getPageInfo() {
                return pageInfo;
            }

            public void setPageInfo(PageInfo pageInfo) {
                this.pageInfo = pageInfo;
            }
        }
        public  Data data=new Data();

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }
    }

    private static class ShippingTemplateGroupRet extends BasicRet{
        public ShippingTemplateGroup data;

        public ShippingTemplateGroup getData() {
            return data;
        }

        public void setData(ShippingTemplateGroup data) {
            this.data = data;
        }
    }
}
