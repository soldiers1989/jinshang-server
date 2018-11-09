package project.jinshang.mod_shippingaddress;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicMapDataRet;
import mizuki.project.core.restserver.config.BasicRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.jinshang.common.bean.PageRet;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.constant.SellerAuthorityConst;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_member.bean.MemberGrade;
import project.jinshang.mod_member.bean.MemberGradeExample;
import project.jinshang.mod_shippingaddress.bean.ShippingAddress;
import project.jinshang.mod_shippingaddress.bean.ShippingAddressExample;
import project.jinshang.mod_shippingaddress.service.ShippingAddressService;

import javax.persistence.Id;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@Api(tags = "买家、卖家收货地址管理",description = "买家、卖家收货地址相关接口")
@SessionAttributes({AppConstant.MEMBER_SESSION_NAME})
@Transactional(rollbackFor = Exception.class)
public class ShippingAddressbuyerAction {
    @Autowired
    private ShippingAddressService shippingAddressService;


@RequestMapping(value = "/rest/buyer/ShippingAddress/addShippingAddress",method = RequestMethod.POST)
@ApiOperation(value = "买家添加收货地址")
@ApiImplicitParams({
        @ApiImplicitParam(name = "shipto",value = "收货人",required = true,paramType = "query"  ,dataType = "string"),
        @ApiImplicitParam(name = "regionid",value = "地区代码",required = true,paramType = "query",dataType = "int"),
        @ApiImplicitParam(name = "province",value = "省",required = true,paramType = "query"  ,dataType = "string"),
        @ApiImplicitParam(name = "city",value = "市",required = true,paramType = "query"  ,dataType = "string"),
        @ApiImplicitParam(name = "citysmall",value = "区",required = true,paramType = "query"  ,dataType = "string"),
        @ApiImplicitParam(name = "address",value = "地址",required = true,paramType = "query"  ,dataType = "string"),
        @ApiImplicitParam(name = "zipcode",value = "邮编",required = true,paramType = "query",dataType = "string"),
        @ApiImplicitParam(name = "phone",value = "手机号码",required = true,paramType = "query"  ,dataType = "int"),
        @ApiImplicitParam(name = "isdefault",value = "是否默认使用该地址{0:不是,1:是}",required = true,paramType = "query",dataType = "int"),
})
        public BasicRet addBuyerShippingAddress(ShippingAddress shippingAddress,Model model) {
    BasicMapDataRet basicMapDataRet = new BasicMapDataRet();
    Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);


            ShippingAddressExample example =  new ShippingAddressExample();
            ShippingAddressExample.Criteria criteria = example.createCriteria();
            criteria.andMemberidEqualTo(member.getId()).andTypeEqualTo(Quantity.STATE_2);
            int count =  shippingAddressService.countByExample(example);
            if(count>=10){
                return  new BasicRet(BasicRet.ERR,"最多可添加10条");
            }

            if(shippingAddress.getIsdefault() == Quantity.STATE_1){  //如果设置为默认，首先全部取消默认
                shippingAddressService.upateAllToNotDefault(member.getId(),Quantity.STATE_2);
            }else{
                ShippingAddress defaultAddress =  shippingAddressService.getDefaultShippingAddress(member.getId(),Quantity.STATE_2);
                if(defaultAddress == null){  //如果不存在默认的，将此设置为默认的
                    shippingAddress.setIsdefault(Quantity.STATE_1);
                }
            }

            shippingAddress.setMemberid(member.getId());
            shippingAddress.setType(Quantity.STATE_2);
    Long id = shippingAddressService.addShippingAddress(shippingAddress);

    basicMapDataRet.setResult(BasicRet.SUCCESS);
    Map<String, Object> map = new HashMap<>();
    map.put("id",id);
    basicMapDataRet.setData(map);
    basicMapDataRet.setMessage("添加成功");
            return basicMapDataRet;
        }




    @RequestMapping(value = "/rest/seller/ShippingAddress/addShippingAddress",method = RequestMethod.POST)
    @ApiOperation(value = "卖家添加收货地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "shipto",value = "收货人",required = true,paramType = "query"  ,dataType = "string"),
            @ApiImplicitParam(name = "regionid",value = "地区代码",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "province",value = "省",required = true,paramType = "query"  ,dataType = "string"),
            @ApiImplicitParam(name = "city",value = "市",required = true,paramType = "query"  ,dataType = "string"),
            @ApiImplicitParam(name = "citysmall",value = "区",required = true,paramType = "query"  ,dataType = "string"),
            @ApiImplicitParam(name = "address",value = "地址",required = true,paramType = "query"  ,dataType = "string"),
            @ApiImplicitParam(name = "zipcode",value = "邮编",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "phone",value = "手机号码",required = true,paramType = "query"  ,dataType = "int"),
            @ApiImplicitParam(name = "isdefault",value = "是否默认使用该地址{0:不是,1:是}",required = true,paramType = "query",dataType = "int"),
    })
    @PreAuthorize("hasAuthority('"+ SellerAuthorityConst.SHIPPINGADDRESS+"') || hasAuthority('"+ SellerAuthorityConst.ALL+"')")
    public BasicRet addSellerShippingAddress(ShippingAddress shippingAddress,Model model) {
        BasicRet basicRet = new BasicRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        ShippingAddressExample example =  new ShippingAddressExample();
        ShippingAddressExample.Criteria criteria = example.createCriteria();
        criteria.andMemberidEqualTo(member.getId()).andTypeEqualTo(Quantity.STATE_1);
        int count =  shippingAddressService.countByExample(example);
        if(count>=10){
            return  new BasicRet(BasicRet.ERR,"最多可添加10条");
        }

        if(shippingAddress.getIsdefault() == Quantity.STATE_1){  //如果设置为默认，首先全部取消默认
            shippingAddressService.upateAllToNotDefault(member.getId(),Quantity.STATE_1);
        }else{
            ShippingAddress defaultAddress =  shippingAddressService.getDefaultShippingAddress(member.getId(),Quantity.STATE_1);
            if(defaultAddress == null){  //如果不存在默认的，将此设置为默认的
                shippingAddress.setIsdefault(Quantity.STATE_1);
            }
        }

        shippingAddress.setMemberid(member.getId());
        shippingAddress.setType(Quantity.STATE_1);
        shippingAddressService.addShippingAddress(shippingAddress);

        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("添加成功");
        return basicRet;
    }



    @RequestMapping(value = "/rest/buyer/ShippingAddress/deleteShippingAddress",method = RequestMethod.POST)
    @ApiOperation(value = "买家删除收货地址")
    public  BasicRet deleteBuerShippingAddress(@RequestParam (required = true) long id,Model model){
        BasicRet basicRet=new BasicRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        ShippingAddress shippingAdd =shippingAddressService.selectByidAndType(id,Quantity.STATE_2);
        if(shippingAdd==null||(long)shippingAdd.getMemberid()!=member.getId()){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("ID不正确，请确认需要删除的用户");
        }else {
            if (shippingAdd.getIsdefault() == Quantity.STATE_1) {
                basicRet.setResult(BasicRet.ERR);
                basicRet.setMessage("默认不可删除");
            }else {
                shippingAddressService.deleteShippingAddress(id);
                basicRet.setResult(BasicRet.SUCCESS);
                basicRet.setMessage("删除成功");
            }
        }

        return  basicRet;
    }



    @RequestMapping(value = "/rest/seller/ShippingAddress/deleteShippingAddress",method = RequestMethod.POST)
    @ApiOperation(value = "卖家删除收货地址")
    @PreAuthorize("hasAuthority('"+ SellerAuthorityConst.SHIPPINGADDRESS+"') || hasAuthority('"+ SellerAuthorityConst.ALL+"')")
    public  BasicRet deleteSellerShippingAddress(@RequestParam (required = true) long id,Model model){
        BasicRet basicRet=new BasicRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        ShippingAddress shippingAdd =shippingAddressService.selectByidAndType(id,Quantity.STATE_1);
        if(shippingAdd==null||(long)shippingAdd.getMemberid() != member.getId()){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("ID不正确，请确认需要删除的用户");
        }else {
            if (shippingAdd.getIsdefault() == Quantity.STATE_1) {
                basicRet.setResult(BasicRet.ERR);
                basicRet.setMessage("默认不可删除");
            }else {
                shippingAddressService.deleteShippingAddress(id);
                basicRet.setResult(BasicRet.SUCCESS);
                basicRet.setMessage("删除成功");
            }
        }

        return  basicRet;
    }




    @RequestMapping(value = "/rest/buyer/ShippingAddress/updateShippingAddress",method = RequestMethod.POST)
    @ApiOperation("买家修改收货地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "ID",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "shipto",value = "收货人",required = true,paramType = "query"  ,dataType = "string"),
            @ApiImplicitParam(name = "regionid",value = "地区代码",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "province",value = "省",required = true,paramType = "query"  ,dataType = "string"),
            @ApiImplicitParam(name = "city",value = "市",required = true,paramType = "query"  ,dataType = "string"),
            @ApiImplicitParam(name = "citysmall",value = "区",required = true,paramType = "query"  ,dataType = "string"),
            @ApiImplicitParam(name = "address",value = "地址",required = true,paramType = "query"  ,dataType = "string"),
            @ApiImplicitParam(name = "zipcode",value = "邮编",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "phone",value = "手机号码",required = true,paramType = "query"  ,dataType = "string"),
            @ApiImplicitParam(name = "isdefault",value = "是否默认使用该地址{0:不是,1:是}",required = true,paramType = "query",dataType = "string"),
    })
    public  BasicRet updateBuyerShippingAddress(ShippingAddress shippingAddress ,Model model) {
        BasicRet basicRet = new BasicRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        ShippingAddress dbShippingAddress =  shippingAddressService.selectByidAndType(shippingAddress.getId(),Quantity.STATE_2);
        if(dbShippingAddress == null){
            basicRet.setMessage("要修改的信息不存在");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }

        if(!dbShippingAddress.getMemberid().equals(member.getId())){
            basicRet.setMessage("要修改的信息不属于你");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }


        if(dbShippingAddress.getIsdefault() == Quantity.STATE_1 && shippingAddress.getIsdefault() != Quantity.STATE_1){
            basicRet.setMessage("必须保留一个默认的地址");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }



        if(shippingAddress.getIsdefault() == Quantity.STATE_1){  //如果设置为默认，首先全部取消默认
            shippingAddressService.upateAllToNotDefault(member.getId(),Quantity.STATE_2);
        }


        shippingAddress.setMemberid(member.getId());
        shippingAddress.setType(Quantity.STATE_2);
        shippingAddressService.updateByPrimaryKey(shippingAddress);

        basicRet.setMessage("修改成功");
        basicRet.setResult(BasicRet.SUCCESS);

        return basicRet;
    }




    @RequestMapping(value = "/rest/seller/ShippingAddress/updateShippingAddress",method = RequestMethod.POST)
    @ApiOperation("卖家修改收货地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "ID",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "shipto",value = "收货人",required = true,paramType = "query"  ,dataType = "string"),
            @ApiImplicitParam(name = "regionid",value = "地区代码",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "province",value = "省",required = true,paramType = "query"  ,dataType = "string"),
            @ApiImplicitParam(name = "city",value = "市",required = true,paramType = "query"  ,dataType = "string"),
            @ApiImplicitParam(name = "citysmall",value = "区",required = true,paramType = "query"  ,dataType = "string"),
            @ApiImplicitParam(name = "address",value = "地址",required = true,paramType = "query"  ,dataType = "string"),
            @ApiImplicitParam(name = "zipcode",value = "邮编",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "phone",value = "手机号码",required = true,paramType = "query"  ,dataType = "string"),
            @ApiImplicitParam(name = "isdefault",value = "是否默认使用该地址{0:不是,1:是}",required = true,paramType = "query",dataType = "string"),
    })
    @PreAuthorize("hasAuthority('"+ SellerAuthorityConst.SHIPPINGADDRESS+"') || hasAuthority('"+ SellerAuthorityConst.ALL+"')")
    public  BasicRet updateSellerShippingAddress(ShippingAddress shippingAddress ,Model model) {
        BasicRet basicRet = new BasicRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        ShippingAddress dbShippingAddress =  shippingAddressService.selectByidAndType(shippingAddress.getId(),Quantity.STATE_1);
        if(dbShippingAddress == null){
            basicRet.setMessage("要修改的信息不存在");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }

        if(!dbShippingAddress.getMemberid().equals(member.getId())){
            basicRet.setMessage("要修改的信息不属于你");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }


        if(dbShippingAddress.getIsdefault() == Quantity.STATE_1 && shippingAddress.getIsdefault() != Quantity.STATE_1){
            basicRet.setMessage("必须保留一个默认的地址");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }

        if(shippingAddress.getIsdefault() == Quantity.STATE_1){  //如果设置为默认，首先全部取消默认
            shippingAddressService.upateAllToNotDefault(member.getId(),Quantity.STATE_1);
        }

        shippingAddress.setMemberid(member.getId());
        shippingAddress.setType(Quantity.STATE_1);
        shippingAddressService.updateByPrimaryKey(shippingAddress);

        basicRet.setMessage("修改成功");
        basicRet.setResult(BasicRet.SUCCESS);
        return basicRet;
    }



    @RequestMapping(value = "/rest/seller/ShippingAddress/listShippingAddress",method = RequestMethod.POST)
    @ApiOperation("列出卖家收货地址列表")
    @PreAuthorize("hasAuthority('"+ SellerAuthorityConst.SHIPPINGADDRESS+"') || hasAuthority('"+ SellerAuthorityConst.ALL+"')")
    public PageRet  listSellerShippingAddress(@RequestParam(required = true,defaultValue = "1") int pageNo,
                                              @RequestParam(required = true,defaultValue = "10")  int pageSize, Model model){
        PageRet pageRet = new PageRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        PageInfo pageInfo = shippingAddressService.listAllShippingAddress(pageNo,pageSize,member.getId(),Quantity.STATE_1);
        pageRet.data.setPageInfo(pageInfo);
        pageRet.setResult(BasicRet.SUCCESS);
        return  pageRet;
    }


    @RequestMapping(value = "/rest/buyer/ShippingAddress/listShippingAddress",method = RequestMethod.POST)
    @ApiOperation("列出买家收货地址列表")
    public PageRet  listBuyerShippingAddress(@RequestParam(required = true,defaultValue = "1") int pageNo,
                                             @RequestParam(required = true,defaultValue = "10")  int pageSize, Model model){
        PageRet pageRet = new PageRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        PageInfo pageInfo = shippingAddressService.listAllShippingAddress(pageNo,pageSize,member.getId(),Quantity.STATE_2);
        pageRet.data.setPageInfo(pageInfo);
        pageRet.setMessage("获取买家收货地址列表成功");
        pageRet.setResult(BasicRet.SUCCESS);
        return  pageRet;
    }


    @PostMapping({"/rest/buyer/ShippingAddress/setDefault","/rest/seller/ShippingAddress/setDefault"})
    @ApiOperation("设置为默认")
    public  BasicRet setDefault(@RequestParam(required = true) long id,Model model){
        BasicRet basicRet =  new BasicRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        ShippingAddress address =  shippingAddressService.getById(id);

        if(address == null){
            basicRet.setMessage("地址不存在");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }

        if(!address.getMemberid().equals(member.getId())){
            basicRet.setMessage("该地址不属于你");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }


        if(address.getIsdefault() != Quantity.STATE_1){
            shippingAddressService.upateAllToNotDefault(member.getId(),address.getType());
            ShippingAddress updateAddr = new ShippingAddress();
            updateAddr.setIsdefault(Quantity.STATE_1);
            updateAddr.setId(id);
            shippingAddressService.updateByPrimaryKeySelective(updateAddr);
        }

        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("修改成功");
        return  basicRet;
    }

}
