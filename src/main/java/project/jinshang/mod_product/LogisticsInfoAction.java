package project.jinshang.mod_product;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.mod_common.bean.BasicExtRet;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_product.bean.LogisticsInfo;
import project.jinshang.mod_product.bean.OrderProduct;
import project.jinshang.mod_product.bean.Orders;
import project.jinshang.mod_product.bean.dto.LogisticsInfoDto;
import project.jinshang.mod_product.service.LogisticsInfoService;
import project.jinshang.mod_product.service.OrderProductServices;
import project.jinshang.mod_product.service.OrdersService;

import java.util.List;

@RestController
@RequestMapping("/rest/admin/logisticsinfo")
@SessionAttributes({AppConstant.ADMIN_SESSION_NAME})
@Api(tags = "后台物流信息模块", description = "后台物流信息接口")
@Transactional(rollbackFor = Exception.class)
public class LogisticsInfoAction {

    @Autowired
    private LogisticsInfoService logisticsInfoService;

}
