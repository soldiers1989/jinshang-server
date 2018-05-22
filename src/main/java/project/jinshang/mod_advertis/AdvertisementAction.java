package project.jinshang.mod_advertis;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.jinshang.common.bean.MemberLogOperator;
import project.jinshang.common.bean.PageRet;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.mod_advertis.bean.Advertisement;
import project.jinshang.mod_advertis.service.AdvertisementService;
import project.jinshang.mod_common.bean.BasicExtRet;
import project.jinshang.mod_member.bean.Admin;
import project.jinshang.mod_product.service.MemberOperateLogService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/rest/admin/adt")
@SessionAttributes({AppConstant.ADMIN_SESSION_NAME})
@Api(tags = "广告管理相关", description = "广告")
public class AdvertisementAction {

    @Autowired
    AdvertisementService advertisementService;

    @Autowired
    MemberLogOperator memberLogOperator;

    @Autowired
    MemberOperateLogService memberOperateLogService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = " 添加广告")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "placeid", value = "广告位id", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "title", value = "广告标题", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "imgs", value = "图片地址", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "url", value = "广告关联url", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "starttime", value = "开始时间", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "endtime", value = "结束时间", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "sort", value = "排序", required = true, paramType = "query", dataType = "int"),
    })
    public BasicRet addAdvertisement(Advertisement advertisement,
                                     Model model,
                                     HttpServletRequest request) {
        BasicRet basicRet = new BasicRet();
        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);

        List<Advertisement> advertisementList = advertisementService.selectAdvertisementListByPlaceIdAndSortId(advertisement.getPlaceid(), advertisement.getSort());
        if (advertisementList.size() != 0) {
            Advertisement defaultAdvertisement = advertisementList.get(0);
            defaultAdvertisement.setSort(advertisementService.getMaxSortIdByAdvertisingPlaceId(advertisement.getPlaceid()) + 1);
            defaultAdvertisement.setUpdatetime(new Date());
            advertisementService.updateAdvertisement(defaultAdvertisement);
        }
        advertisement.setAdduser(admin.getRealname());
        advertisement.setCreatetime(new Date());
        advertisement.setUpdatetime(new Date());
        advertisementService.insertAdvertisement(advertisement);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("新增成功");
        memberLogOperator.saveMemberLog(null, admin, "新增广告：" + advertisement.getTitle(), "/add", request, memberOperateLogService);
        return basicRet;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ApiOperation(value = " 删除广告")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "广告id", required = true, paramType = "query", dataType = "long"),
    })
    public BasicRet deleteAdvertisement(@RequestParam(value = "id") Long id, Model model, HttpServletRequest request) {
        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
        BasicRet basicRet = new BasicRet();
        Advertisement advertisement = advertisementService.getAdvertisementById(id);
        if (advertisement == null) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("没有该广告");
            return basicRet;
        }
        advertisementService.deleteAdvertisementById(id);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("删除成功");
        memberLogOperator.saveMemberLog(null, admin, "删除广告：" + advertisement.getTitle(), "/delete", request, memberOperateLogService);
        return basicRet;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ApiOperation(value = " 修改广告")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "广告id", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "placeid", value = "广告位id", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "title", value = "广告标题", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "imgs", value = "图片地址", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "url", value = "广告关联url", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "starttime", value = "开始时间", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "endtime", value = "结束时间", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "sort", value = "排序", required = true, paramType = "query", dataType = "int"),
    })
    public BasicRet editAdvertisement(Advertisement advertisement, Model model, HttpServletRequest request) {
        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
        BasicRet basicRet = new BasicRet();
        Advertisement dbAdvertisement = advertisementService.getAdvertisementById(advertisement.getId());
        List<Advertisement> advertisementList = advertisementService.selectAdvertisementListByPlaceIdAndSortId(advertisement.getPlaceid(), advertisement.getSort());
        if (!dbAdvertisement.getPlaceid().equals(advertisement.getPlaceid())) {
            if (advertisementList.size() != 0) {
                Advertisement defaultAdvertisement = advertisementList.get(0);
                defaultAdvertisement.setSort(advertisementService.getMaxSortIdByAdvertisingPlaceId(advertisement.getPlaceid()) + 1);
                defaultAdvertisement.setUpdatetime(new Date());
                advertisementService.updateAdvertisement(defaultAdvertisement);
            }
        } else if (!dbAdvertisement.getSort().equals(advertisement.getSort())) {
            if (advertisementList.size() != 0) {
                Advertisement defaultAdvertisement = advertisementList.get(0);
                defaultAdvertisement.setSort(dbAdvertisement.getSort());
                defaultAdvertisement.setUpdatetime(new Date());
                advertisementService.updateAdvertisement(defaultAdvertisement);
            }
        }
        advertisement.setUpdatetime(new Date());
        advertisementService.updateAdvertisement(advertisement);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("成功");
        memberLogOperator.saveMemberLog(null, admin, "修改广告：" + advertisement.getTitle(), "/edit", request, memberOperateLogService);
        return basicRet;
    }

    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    @ApiOperation(value = " 广告详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "广告id", required = true, paramType = "query", dataType = "long"),
    })
    public BasicRet getAdvertisementDetail(@RequestParam(value = "id") Long id) {
        BasicExtRet basicRet = new BasicExtRet();
        Advertisement advertisement = advertisementService.getAdvertisementById(id);

        basicRet.setData(advertisement);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("删除成功");
        return basicRet;
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ApiOperation(value = " 广告列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "当前页", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "一页多少数据", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "title", value = "广告标题", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "placeId", value = "广告位id", required = false, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "overdue", value = "是否过期 0：不过期 1：过期", required = false, paramType = "query", dataType = "String"),
    })
    public PageRet getAdvertisementList(@RequestParam(value = "pageNo") int pageNo,
                                        @RequestParam(value = "pageSize") int pageSize,
                                        @RequestParam(value = "title", required = false, defaultValue = "") String title,
                                        @RequestParam(value = "placeId", required = false) Long placeId,
                                        @RequestParam(value = "overdue", required = false, defaultValue = "") String overdue) {
        PageRet basicRet = new PageRet();
        PageInfo pageInfo = advertisementService.getAdvertisementList(pageNo, pageSize, title, placeId, overdue);
        basicRet.data.setPageInfo(pageInfo);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("获取成功");
        return basicRet;
    }

    @RequestMapping(value = "/maxSort", method = RequestMethod.POST)
    @ApiOperation(value = "获取某个广告下最大的序列号")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "placeId", value = "广告位id", required = true, paramType = "query", dataType = "long"),
    })
    public BasicExtRet getMaxSort(Long placeId) {
        BasicExtRet basicRet = new BasicExtRet();
        basicRet.setData(advertisementService.getMaxSortIdByAdvertisingPlaceId(placeId));
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("获取成功");
        return basicRet;
    }
}
