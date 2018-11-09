package project.jinshang.mod_smallprog;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_smallprog.service.WxSmallProgService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 微信小程序Action
 *
 * @author xiazy
 * @create 2018-08-02 11:50
 **/
@RestController
@RequestMapping("/rest/front/wxSmallProg")
@Api(tags = "微信小程序统一起调控制层")
@Transactional(rollbackFor = Exception.class)
@SessionAttributes({AppConstant.MEMBER_SESSION_NAME})
public class WxSmallProgAction {
    private static final Logger logger= LoggerFactory.getLogger(WxSmallProgAction.class);
    @Autowired
    private WxSmallProgService wxSmallProgService;

    @RequestMapping(value = "/getRankProductList",method = RequestMethod.POST)
    @ApiOperation("微信小程序的首页热销产品推荐")
    public RankProdRet getRankProductList(Model model,int count){
        RankProdRet rankProdRet=new RankProdRet();
        rankProdRet.setMessage("热销产品获取成功");
        rankProdRet.setResult(BasicRet.SUCCESS);
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        if(member == null){
            rankProdRet.data.showPrice = false;
        }else{
            rankProdRet.data.showPrice = true;
        }
        rankProdRet.data.setRankProdList(wxSmallProgService.getRankProductList(count));
        return rankProdRet;
    }
    public static class RankProdRet extends BasicRet{
        private class RankProdData{
            private List<Map<String,Object>> rankProdList= new ArrayList<>();
            private boolean showPrice;

            public List<Map<String, Object>> getRankProdList() {
                return rankProdList;
            }

            public void setRankProdList(List<Map<String, Object>> rankProdList) {
                this.rankProdList = rankProdList;
            }

            public boolean isShowPrice() {
                return showPrice;
            }

            public void setShowPrice(boolean showPrice) {
                this.showPrice = showPrice;
            }
        }

        private RankProdData data=new RankProdData();

        public RankProdData getData() {
            return data;
        }

        public void setData(RankProdData data) {
            this.data = data;
        }
    }
}
