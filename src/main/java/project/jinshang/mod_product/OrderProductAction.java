package project.jinshang.mod_product;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import project.jinshang.common.bean.PageRet;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.mod_product.bean.OrderProduct;
import project.jinshang.mod_product.service.OrderProductServices;

@RestController
@RequestMapping("/rest/buyer/orderproduct")
@SessionAttributes({AppConstant.MEMBER_SESSION_NAME})
@Api(tags ="买家订单商品模块", description = "买家订单商品模块")
@Transactional(rollbackFor = Exception.class)
public class OrderProductAction {

    @Autowired
    private OrderProductServices orderProductServices;

    @PostMapping("/selectByDeliveryid")
    @ApiOperation(value = "根据物流id查询发货的商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", required = false, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "deliveryid", value = "deliveryid", required = false, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "pageNo", value = "页码(值为-1不分页)", required = true, paramType = "query", dataType = "int", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页数", required = false, paramType = "query", dataType = "int", defaultValue = "20"),
    })
    public PageRet getList(int pageNo, int pageSize, OrderProduct orderProduct) {
        PageRet pageRet = new PageRet();
        PageInfo pageInfo = orderProductServices.selectByObject(pageNo, pageSize, orderProduct);
        pageRet.data.setPageInfo(pageInfo);
        pageRet.setMessage("返回成功");
        pageRet.setResult(BasicRet.SUCCESS);
        return pageRet;
    }

}
