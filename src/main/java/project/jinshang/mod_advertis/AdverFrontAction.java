package project.jinshang.mod_advertis;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import mizuki.project.core.restserver.config.BasicRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.jinshang.mod_advertis.bean.Advertisement;
import project.jinshang.mod_advertis.bean.AdvertisingPlace;
import project.jinshang.mod_advertis.service.AdvertisementService;
import project.jinshang.mod_advertis.service.AdvertisingPlaceService;

import java.util.List;

/**
 * create : wyh
 * date : 2017/12/26
 */

@RestController
@RequestMapping("/rest/front/adt")
@Api(tags = "前台广告")
public class AdverFrontAction {

    @Autowired
    private AdvertisementService advertisementService;

    @Autowired
    private AdvertisingPlaceService advertisingPlaceService;

    @PostMapping(value = "")
    @ApiParam("获取广告")
    public AdvertismentListRet getAdvertisment(@RequestParam(required = true) String position,
                                               @RequestParam(required = false, defaultValue = "1") int count) {
        AdvertismentListRet advertismentListRet = new AdvertismentListRet();
        AdvertisingPlace advertisingPlace = advertisingPlaceService.getAdvertisingPlaceByPosition(position);
        if (advertisingPlace == null) {
            advertismentListRet.setResult(BasicRet.ERR);
            advertismentListRet.setMessage("不存在该广告位");
            return advertismentListRet;
        } else if (advertisingPlace.getStop() == 1) {
            advertismentListRet.setResult(BasicRet.SUCCESS);
            advertismentListRet.setMessage("该广告位已停用");
            advertismentListRet.setShow(false);
            return advertismentListRet;
        }
        advertismentListRet.setWeight(advertisingPlace.getWeight());
        advertismentListRet.setHeight(advertisingPlace.getHeight());
        List<Advertisement> list = advertisementService.getAdvertisment(position, count);
        advertismentListRet.setShow(true);
        advertismentListRet.data = list;
        advertismentListRet.setMessage("成功");
        advertismentListRet.setResult(BasicRet.SUCCESS);
        return advertismentListRet;
    }


    private class AdvertismentListRet extends BasicRet {
        private List<Advertisement> data;
        private boolean show;
        private Float weight;
        private Float height;

        public boolean isShow() {
            return show;
        }

        public void setShow(boolean show) {
            this.show = show;
        }

        public List<Advertisement> getData() {
            return data;
        }

        public void setData(List<Advertisement> data) {
            this.data = data;
        }

        public Float getWeight() {
            return weight;
        }

        public void setWeight(Float weight) {
            this.weight = weight;
        }

        public Float getHeight() {
            return height;
        }

        public void setHeight(Float height) {
            this.height = height;
        }
    }


}
