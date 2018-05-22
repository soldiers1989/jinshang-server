package project.jinshang.mod_product;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import project.jinshang.common.bean.PageRet;
import project.jinshang.mod_product.bean.EvaPageModel;
import project.jinshang.mod_product.service.OrdersService;


@RestController
@RequestMapping("/rest/producteva")
@Api(tags = "通用获取商品评价模块", description = "获取商品评价模块")
public class ProductEvaAction {

    @Autowired
    private OrdersService ordersService;


    private class ProductEvaRet extends BasicRet {
        private class Data {

            private Long num;
            private EvaPageModel evaPageModel;

            public EvaPageModel getEvaPageModel() {
                return evaPageModel;
            }

            public void setEvaPageModel(EvaPageModel evaPageModel) {
                this.evaPageModel = evaPageModel;
            }

            public Long getNum() {
                return num;
            }

            public void setNum(Long num) {
                this.num = num;
            }
        }

        private Data data = new Data();

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }
    }

    /**
     * 获取单个商品评价
     *
     * @param pdid
     * @return
     */
    @RequestMapping(value = "/getSingleProductEva", method = RequestMethod.POST)
    @ApiOperation(value = "获取单个商品评价")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pdid", value = "商品id", required = true, paramType = "query", dataType = "long"),
    })
    public ProductEvaRet getSingleProductEva(Long pdid) {
        ProductEvaRet productEvaRet = new ProductEvaRet();
        EvaPageModel evaPageModel = ordersService.getSingleProductEva(pdid);
        productEvaRet.setMessage("返回成功");
        productEvaRet.setResult(BasicRet.SUCCESS);
        productEvaRet.data.evaPageModel = evaPageModel;
        return productEvaRet;
    }

    /**
     * 获取单个商品评价数
     * @param pdid
     * @return
     */
    @RequestMapping(value = "/getSingleProductEvaNum", method = RequestMethod.POST)
    @ApiOperation(value = "获取单个商品评价数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pdid", value = "商品id", required = true, paramType = "query", dataType = "long"),
    })
    public ProductEvaRet getSingleProductEvaNum(Long pdid){
        ProductEvaRet productEvaRet = new ProductEvaRet();
        Long num = ordersService.getSingleProductEvaNum(pdid);
        productEvaRet.setMessage("返回成功");
        productEvaRet.setResult(BasicRet.SUCCESS);
        productEvaRet.data.num = num;
        return productEvaRet;
    }

    /**
     * 获取单个商品评价列表
     *
     * @param pdid
     * @return
     */
    @RequestMapping(value = "/getSingleProductEvaList", method = RequestMethod.POST)
    @ApiOperation(value = "获取单个商品评价列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "页数", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pdid", value = "商品id", required = true, paramType = "query", dataType = "string"),
    })
    public PageRet getSingleProductEvaList(int pageNo, int pageSize, Long pdid) {
        PageRet pageRet = new PageRet();
        PageInfo pageInfo = ordersService.getProductEvaListByPdId(pageNo, pageSize, pdid);
        pageRet.data.setPageInfo(pageInfo);
        pageRet.setMessage("返回成功");
        pageRet.setResult(BasicRet.SUCCESS);
        return pageRet;
    }
}
