package project.jinshang.mod_coupon;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import project.jinshang.common.bean.PageRet;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.utils.GenerateNo;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_common.bean.BasicExtRet;
import project.jinshang.mod_coupon.bean.YhqTicketset;
import project.jinshang.mod_coupon.bean.YhqTicketsetExample;
import project.jinshang.mod_coupon.service.YhqTicketSetService;
import project.jinshang.mod_member.bean.Admin;

import java.math.BigDecimal;
import java.util.Date;

@RestController
@RequestMapping(value = {"/rest/admin/yhqticketset"})
@Api(tags = "后台优惠券配置", description = "优惠券配置接口")
@SessionAttributes(AppConstant.ADMIN_SESSION_NAME)
public class YhqTicketSetAction {

    @Autowired
    private YhqTicketSetService yhqTicketSetService;

    @PostMapping(value = "/insert")
    @ApiOperation(value = "新增优惠券配置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "名称", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "type", value = "类型", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "about", value = "简介", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "starttime", value = "发放开始时间", required = true, paramType = "query", dataType = "date"),
            @ApiImplicitParam(name = "endtime", value = "发放截止时间", required = true, paramType = "query", dataType = "date"),
            @ApiImplicitParam(name = "validitystarttime", value = "有效开始时间", required = true, paramType = "query", dataType = "date"),
            @ApiImplicitParam(name = "validityendtime", value = "有效截止时间", required = true, paramType = "query", dataType = "date"),
            @ApiImplicitParam(name = "ticketcount", value = "发放数量", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "createtype", value = "发放类型", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "rule", value = "优惠规则", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "validityrule", value = "有效规则", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "planid", value = "方案ID", required = true, paramType = "query", dataType = "long"),
    })
    public BasicRet insertYhqTicketSetInfo(YhqTicketset yhqTicketset, Model model) {
        BasicRet basicRet = new BasicRet();
        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
        //判断是否是json
        boolean flag = StringUtils.isJSON(yhqTicketset.getRule());
        if(!flag){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("非法的优惠规则,需要是json格式");
            return basicRet;
        }
        //判断rule1 rule2满的金额要大于减去的金额 不允许出现负数
        JSONObject jsonObject = JSONObject.fromObject(yhqTicketset.getRule());
        String rule1 = jsonObject.get("rule1").toString();
        String rule2 = jsonObject.get("rule2").toString();
        BigDecimal newrule1 = new BigDecimal(rule1);
        BigDecimal newrule2 = new BigDecimal(rule2);
        if(newrule2.compareTo(newrule1)>0){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("优惠的金额大于满减金额");
            return basicRet;
        }


        yhqTicketset.setNo(GenerateNo.getYhqNo());
        yhqTicketset.setSurpluscount(yhqTicketset.getTicketcount());
        yhqTicketset.setStatus(1l);
        yhqTicketset.setAuditingid(0l);
        yhqTicketset.setUsersid(admin.getId());
        yhqTicketset.setCreatetime(new Date());

        yhqTicketSetService.insertYhqTicketSetInfo(yhqTicketset);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("新增成功");
        return basicRet;
    }

    @PostMapping(value = "/update")
    @ApiOperation(value = "修改优惠券配置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "优惠券配置ID", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "name", value = "名称", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "type", value = "类型", required = false, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "about", value = "简介", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "starttime", value = "发放开始时间", required = false, paramType = "query", dataType = "date"),
            @ApiImplicitParam(name = "endtime", value = "发放截止时间", required = false, paramType = "query", dataType = "date"),
            @ApiImplicitParam(name = "validitystarttime", value = "有效开始时间", required = false, paramType = "query", dataType = "date"),
            @ApiImplicitParam(name = "validityendtime", value = "有效截止时间", required = false, paramType = "query", dataType = "date"),
            @ApiImplicitParam(name = "ticketcount", value = "发放数量", required = false, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "surpluscount", value = "剩余数量", required = false, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "createtype", value = "发放类型", required = false, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "rule", value = "优惠规则", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "validityrule", value = "有效规则", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "planid", value = "方案ID", required = false, paramType = "query", dataType = "long"),
    })
    public BasicRet updateYhqTicketSetInfo(YhqTicketset yhqTicketset) {
        BasicRet basicRet = new BasicRet();
        YhqTicketset oldyhqTicketset = yhqTicketSetService.getYhqTicketSetInfoById(yhqTicketset.getId());
        if (oldyhqTicketset == null) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("不存在该优惠券配置");
        } else {
            yhqTicketSetService.updateYhqTicketSetInfo(yhqTicketset);
            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("修改成功");
        }
        return basicRet;
    }



    @PostMapping(value = "/delete")
    @ApiOperation(value = "删除优惠券配置")
    @ApiImplicitParam(name = "id", value = "优惠券配置Id", required = true, paramType = "query", dataType = "long")
    public BasicRet deleteYhqTicketSetInfo(Long id) {
        BasicRet basicRet = new BasicRet();
        YhqTicketset yhqTicketset = yhqTicketSetService.getYhqTicketSetInfoById(id);
        if (yhqTicketset == null) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("不存在优惠券方案");
        } else {
            //优惠券配置表
            YhqTicketsetExample yhqTicketsetExample = new YhqTicketsetExample();
            YhqTicketsetExample.Criteria criteria = yhqTicketsetExample.createCriteria();
            criteria.andIdEqualTo(id);
            criteria.andStatusEqualTo(99l);
            long count = yhqTicketSetService.countByExample(yhqTicketsetExample);
            if (count > 0) {
                basicRet.setResult(BasicRet.ERR);
                basicRet.setMessage("删除失败,该优惠券配置已审核无法再删除");
                return basicRet;
            }
            yhqTicketSetService.deleteYhqTicketSetInfoById(id);
            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("删除成功");
        }
        return basicRet;
    }



    @PostMapping(value = "/get")
    @ApiOperation(value = "获取优惠券配置详情")
    @ApiImplicitParam(name = "id", value = "优惠券配置Id", required = true, paramType = "query", dataType = "long")
    public BasicRet getYhqTicketSetInfoDetail(Long id) {
        BasicExtRet basicRet = new BasicExtRet();
        YhqTicketset yhqTicketset = yhqTicketSetService.getYhqTicketSetInfoById(id);
        if (yhqTicketset == null) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("不存在该优惠券配置");
        } else {
            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("获取成功");
            basicRet.setData(yhqTicketset);
        }
        return basicRet;
    }


    @ApiOperation(value = "优惠券方案列表/搜索")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "名称", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "type", value = "类型", required = false, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "status", value = "状态", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "planid", value = "优惠券方案ID", required = false, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "pageNo", value = "页码(值为-1不分页)", required = true, paramType = "query", dataType = "int", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页数", required = false, paramType = "query", dataType = "int", defaultValue = "20"),
    })
    @PostMapping("/list")
    public PageRet getList(int pageNo, int pageSize, YhqTicketset yhqTicketset) {
        PageInfo pageInfo = yhqTicketSetService.getListByPage(pageNo, pageSize,yhqTicketset);

        PageRet pageRet = new PageRet();
        pageRet.data.setPageInfo(pageInfo);
        pageRet.setMessage("返回成功");
        pageRet.setResult(BasicRet.SUCCESS);
        return pageRet;
    }






}
