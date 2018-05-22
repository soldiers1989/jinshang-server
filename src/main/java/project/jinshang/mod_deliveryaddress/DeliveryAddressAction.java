package project.jinshang.mod_deliveryaddress;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.jinshang.common.bean.PageRet;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.constant.Quantity;
import project.jinshang.mod_deliveryaddress.bean.DeliveryAddress;
import project.jinshang.mod_deliveryaddress.bean.DeliveryAddressExample;
import project.jinshang.mod_deliveryaddress.service.DeliveryAddressService;
import project.jinshang.mod_member.bean.Member;

import java.util.List;


@RestController
@RequestMapping("/rest/seller/DeliveryAddress")
@Api(tags = "卖家发获地址管理",description = "卖家发获地址相关接口")
@SessionAttributes({AppConstant.MEMBER_SESSION_NAME})
@Transactional(rollbackFor = Exception.class)
public class DeliveryAddressAction {
    @Autowired
    private DeliveryAddressService deliveryAddressService;


        @RequestMapping(value = "/addDeliveryAddress",method = RequestMethod.POST)
        @ApiOperation(value = "添加发货地址")
        @ApiImplicitParams({
                @ApiImplicitParam(name = "delivery",value = "发货人",required = true,paramType = "query"  ,dataType = "string"),
                @ApiImplicitParam(name = "province",value = "省",required = true,paramType = "query"  ,dataType = "string"),
                @ApiImplicitParam(name = "city",value = "市",required = true,paramType = "query"  ,dataType = "string"),
                @ApiImplicitParam(name = "citysmall",value = "区",required = true,paramType = "query"  ,dataType = "string"),
                @ApiImplicitParam(name = "address",value = "地址",required = true,paramType = "query"  ,dataType = "string"),
                @ApiImplicitParam(name = "phone",value = "手机号码",required = true,paramType = "query"  ,dataType = "int"),
                @ApiImplicitParam(name = "companyname",value = "公司名称",required = true,paramType = "query"  ,dataType = "string"),
                @ApiImplicitParam(name = "isdefault",value = "是否默认使用该地址{0:不是,1:是}",required = true,paramType = "query",dataType = "int"),
        })
        public BasicRet addDeliveryAddress(DeliveryAddress deliveryAddress,Model model){
            BasicRet basicRet=new BasicRet();
            Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

            deliveryAddress.setMemberid(member.getId());

            DeliveryAddress defaultDeliveryAddress = deliveryAddressService.getDefaultDeliveryAddress(member.getId());
            if(defaultDeliveryAddress == null){
                deliveryAddress.setIsdefault(Quantity.STATE_1);
            }

            //如果已经存在默认的了，并且要将新添加的设置为默认的，则将原来的取消默认
            if(defaultDeliveryAddress != null && deliveryAddress.getIsdefault() == Quantity.STATE_1){
                deliveryAddressService.updateIsdefault(defaultDeliveryAddress.getId(),Quantity.STATE_0);
            }

             deliveryAddressService.addDeliveryAddress(deliveryAddress);


             basicRet.setResult(BasicRet.SUCCESS);
             basicRet.setMessage("添加成功");
            return basicRet;
        }




    @RequestMapping(value = "/updateDeliveryAddress",method = RequestMethod.POST)
    @ApiOperation("修改地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "id",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "delivery",value = "收货人",required = true,paramType = "query"  ,dataType = "string"),
            @ApiImplicitParam(name = "province",value = "省",required = true,paramType = "query"  ,dataType = "string"),
            @ApiImplicitParam(name = "city",value = "市",required = true,paramType = "query"  ,dataType = "string"),
            @ApiImplicitParam(name = "citysmall",value = "区",required = true,paramType = "query"  ,dataType = "string"),
            @ApiImplicitParam(name = "address",value = "地址",required = true,paramType = "query"  ,dataType = "string"),
            @ApiImplicitParam(name = "phone",value = "手机号码",required = true,paramType = "query"  ,dataType = "int"),
            @ApiImplicitParam(name = "companyname",value = "公司名称",required = true,paramType = "query"  ,dataType = "string"),
            @ApiImplicitParam(name = "isdefault",value = "是否默认使用该地址{0:不是,1:是}",required = true,paramType = "query",dataType = "string"),
    })
    public  BasicRet updateDeliveryAddress(DeliveryAddress deliveryAddress,Model model) {
        BasicRet basicRet = new BasicRet();

        deliveryAddress.setMemberid(null);

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);


        DeliveryAddress dbDeliveryAddress = deliveryAddressService.getById(deliveryAddress.getId());

        if(dbDeliveryAddress == null){
            basicRet.setMessage("地址不存在");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }

        if(!dbDeliveryAddress.getMemberid().equals(member.getId())){
            basicRet.setMessage("该地址不属于你");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }


        DeliveryAddress defaultDeliveryAddress = deliveryAddressService.getDefaultDeliveryAddress(member.getId());
        if(defaultDeliveryAddress == null){
            deliveryAddress.setIsdefault(Quantity.STATE_1);
        }

        if(defaultDeliveryAddress.getId().equals(deliveryAddress.getId()) && deliveryAddress.getIsdefault() == Quantity.STATE_0 ){
            basicRet.setMessage("至少需要存在一个默认的地址");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }

        //如果已经存在默认的了，并且要将新添加的设置为默认的，则将原来的取消默认
        if(defaultDeliveryAddress != null && deliveryAddress.getIsdefault() == Quantity.STATE_1){
            deliveryAddressService.updateIsdefault(defaultDeliveryAddress.getId(),Quantity.STATE_0);
        }


        deliveryAddressService.updateByPrimaryKeySelective(deliveryAddress);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("地址修改成功");
        return basicRet;


    }




    @RequestMapping(value = "/deleteDeliveryAddress",method = RequestMethod.POST)
    @ApiOperation(value = "删除收货地址")
    public  BasicRet deleteDeliveryAddress(@RequestParam (required = true) long id, Model model){
            BasicRet basicRet=new BasicRet();
            Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

            DeliveryAddress deliveryAddress = deliveryAddressService.getById(id);

            if(deliveryAddress == null){
                basicRet.setMessage("地址不存在");
                basicRet.setResult(BasicRet.ERR);
                return  basicRet;
            }

            if(!deliveryAddress.getMemberid().equals(member.getId())){
                basicRet.setMessage("该地址不属于你");
                basicRet.setResult(BasicRet.ERR);
                return  basicRet;
            }


            if (deliveryAddress.getIsdefault() == 1) {
                basicRet.setResult(BasicRet.ERR);
                basicRet.setMessage("默认级别不可删除");
                return basicRet;
            }

            deliveryAddressService.deleteDeliveryAddress(id);
            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("删除成功");

            return  basicRet;

        }



    @RequestMapping(value = "/setDefault",method = RequestMethod.POST)
    @ApiOperation("设为默认地址")
    public  BasicRet setDefault(long id,Model model){
        BasicRet basicRet = new BasicRet();

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        DeliveryAddress deliveryAddress = deliveryAddressService.getById(id);

        if(deliveryAddress == null){
            basicRet.setMessage("地址不存在");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }

        if(!deliveryAddress.getMemberid().equals(member.getId())){
            basicRet.setMessage("该地址不属于你");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }

        if(deliveryAddress.getIsdefault() == Quantity.STATE_1){  //本来就是默认的
            basicRet.setMessage("修改成功");
            basicRet.setResult(BasicRet.SUCCESS);
            return  basicRet;
        }



        DeliveryAddress defaultDeliveryAddress = deliveryAddressService.getDefaultDeliveryAddress(member.getId());
        if(defaultDeliveryAddress != null){
            deliveryAddressService.updateIsdefault(defaultDeliveryAddress.getId(),Quantity.STATE_0);
        }


        DeliveryAddress updateAddr =  new DeliveryAddress();
        updateAddr.setId(id);
        updateAddr.setIsdefault(Quantity.STATE_1);
        deliveryAddressService.updateByPrimaryKeySelective(updateAddr);


        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("修改成功");
        return  basicRet;
    }






    @RequestMapping(value = "/listDeliveryAddress",method = RequestMethod.POST)
    @ApiOperation("列出所有地址列表")
    public PageRet  listAllDeliveryAddress(@RequestParam(required = true,defaultValue = "1") int pageNo,
                                           @RequestParam(required = true,defaultValue = "20")  int pageSize,Model model){
        PageRet pageRet = new PageRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        PageInfo pageInfo = deliveryAddressService.listDeliveryAddressByPage(pageNo,pageSize,member.getId());

        pageRet.setResult(BasicRet.SUCCESS);
        pageRet.setMessage("查询成功");
        pageRet.data.setPageInfo(pageInfo);
        return  pageRet;
    }

}
