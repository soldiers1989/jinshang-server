package project.jinshang.mod_sale_rank;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import project.jinshang.mod_sale_rank.bean.SaleRankModel;
import project.jinshang.mod_sale_rank.service.SaleRankService;

import java.util.List;

@RestController
@RequestMapping(value ="/rest/sale/rank" )
@Api(tags = "首页排行榜",description = "首页排行榜")
public class SaleRankAction {

    @Autowired
    private SaleRankService saleRankService;

    private class SaleRankRet extends BasicRet{

        private class Data{

            private List<SaleRankModel> list1;
            private List<SaleRankModel> list2;
            private List<SaleRankModel> list3;
            private List<SaleRankModel> list4;

            public List<SaleRankModel> getList4() {
                return list4;
            }

            public void setList4(List<SaleRankModel> list4) {
                this.list4 = list4;
            }

            public List<SaleRankModel> getList1() {
                return list1;
            }

            public void setList1(List<SaleRankModel> list1) {
                this.list1 = list1;
            }

            public List<SaleRankModel> getList2() {
                return list2;
            }

            public void setList2(List<SaleRankModel> list2) {
                this.list2 = list2;
            }

            public List<SaleRankModel> getList3() {
                return list3;
            }

            public void setList3(List<SaleRankModel> list3) {
                this.list3 = list3;
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
     * 获取螺栓的排行榜
     * @return
     */
    @RequestMapping(value = "/getLuoSuanRank",method = RequestMethod.POST)

    @ApiOperation(value = "获取首页排行榜")
    public SaleRankRet getLuoSuanRank(){
        SaleRankRet saleRankRet = new SaleRankRet();
        saleRankRet.data.list1 = saleRankService.getLuoSuanRank();
        saleRankRet.data.list2 = saleRankService.getLuoMuRank();
        saleRankRet.data.list3 = saleRankService.getLuoDingRank();
        saleRankRet.data.list4 = saleRankService.getDangQuanRank();
        saleRankRet.setMessage("返回成功");
        saleRankRet.setResult(BasicRet.SUCCESS);
        return saleRankRet;
    }

}
