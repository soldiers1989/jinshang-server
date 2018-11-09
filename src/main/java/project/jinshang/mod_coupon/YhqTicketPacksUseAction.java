package project.jinshang.mod_coupon;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import project.jinshang.common.bean.PageRet;
import project.jinshang.common.constant.AdminAuthorityConst;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.mod_common.bean.BasicExtRet;
import project.jinshang.mod_coupon.bean.YhqGetpacks;
import project.jinshang.mod_coupon.bean.YhqGetpacksExample;
import project.jinshang.mod_coupon.bean.YhqTicket;
import project.jinshang.mod_coupon.bean.YhqTicketpacks;
import project.jinshang.mod_coupon.service.YhqGetPacksService;
import project.jinshang.mod_coupon.service.YhqTicketPacksService;
import project.jinshang.mod_coupon.service.YhqTicketService;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_member.service.MemberService;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = {"/rest/front/yhqticketpacks"})
@Api(tags = "买家前台优惠券礼包注册领取管理", description = "买家前台优惠券礼包注册领取接口")
public class YhqTicketPacksUseAction {
    @Autowired
    private MemberService memberService;
    @Autowired
    private YhqGetPacksService yhqGetPacksService;
    @Autowired
    private YhqTicketPacksService yhqTicketPacksService;
    @Autowired
    private YhqTicketService yhqTicketService;

    @PostMapping(value = "/use")
    @ApiOperation(value = "优惠券礼包领用")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "no", value = "优惠券礼包编码", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "mobile", value = "手机号", required = true, paramType = "query", dataType = "long"),
    })
    public BasicRet YhqYhqTicketpacksUse(String no, String mobile, Model model) {
        BasicRet basicRet = new BasicRet();

        //查询手机号
        Member member = memberService.selectMemberByMobile(mobile);
        if(member==null){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("手机号不存在无法领取此优惠券礼包");
            return basicRet;
        }

        YhqGetpacksExample example = new YhqGetpacksExample();
        YhqGetpacksExample.Criteria criteria = example.createCriteria();
        criteria.andMemberidEqualTo(member.getId());
        criteria.andPacksnoEqualTo(no);
        long num = yhqGetPacksService.countByExample(example);
        if(num >0){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("该用户已经领取过此优惠券礼包");
            return basicRet;
        }

        //查询礼包信息且剩余数量是大于0的 如果no不正确size为0也不会去发放 且优惠券礼包会设置比较大的发放数量
        List<YhqTicketpacks> list = yhqTicketPacksService.selectByNoAndSurplusCount(no);
        if(list ==null ||list.size() ==0){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("没有查询到此优惠券礼包或者优惠券礼包数量不足");
            return basicRet;
        }else{
            for (YhqTicketpacks yhqTicketpacks: list) {
                if(yhqTicketpacks.getSurpluscount()!=0) {
                    //优惠券礼包领用记录
                    YhqGetpacks yhqGetpacks = new YhqGetpacks();
                    yhqGetpacks.setPacksid(yhqTicketpacks.getId());
                    yhqGetpacks.setPacksno(yhqTicketpacks.getNo());
                    yhqGetpacks.setMemberid(member.getId());
                    yhqGetpacks.setGettime(new Date());
                    yhqGetpacks.setTicketlist(yhqTicketpacks.getTicketlist());
                    yhqGetPacksService.isnertYhqGetpacks(yhqGetpacks);
                    //同时将礼包写入到优惠券表
                    yhqTicketService.insertYhqTicketForRegister(new YhqTicket(),yhqTicketpacks,member.getId());
                    //System.out.println("新用户优惠券发放完成");
                    basicRet.setResult(BasicRet.SUCCESS);
                    basicRet.setMessage("领取注册礼包成功,新用户优惠券发放完成");
                }
            }
        }

        return basicRet;

    }



  /*  @ApiOperation(value = "查询最新一条创建的礼包")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "名称", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "status", value = "状态1：待审核97：禁用98：审核不通过99：审核通过", required = false, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "pageNo", value = "页码(值为-1不分页)", required = true, paramType = "query", dataType = "int", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页数", required = false, paramType = "query", dataType = "int", defaultValue = "20"),
    })
    //@PreAuthorize("hasAuthority('" + AdminAuthorityConst.COUPONPACKAGEMANAGE + "')")
    @PostMapping("/list1")
    public PageRet getList1(int pageNo, int pageSize, YhqTicketpacks yhqTicketpacks) {
        PageInfo pageInfo = yhqTicketPacksService.getListByPage1(pageNo, pageSize,yhqTicketpacks);

        PageRet pageRet = new PageRet();
        pageRet.data.setPageInfo(pageInfo);
        pageRet.setMessage("返回成功");
        pageRet.setResult(BasicRet.SUCCESS);
        return pageRet;
    }*/


    @PostMapping(value = "/registersend")
    @ApiOperation(value = "查询一个礼包名叫注册礼包的")
    public BasicRet registersend() {
        BasicExtRet basicRet = new BasicExtRet();
        String name = "注册礼包";
        YhqTicketpacks yhqTicketpacks = yhqTicketPacksService.getYhqTicketpacksInfoByName(name);
        if (yhqTicketpacks == null) {
            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("不存在该优惠券礼包");
        } else {
            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("获取成功");
            basicRet.setData(yhqTicketpacks);
        }
        return basicRet;
    }

}
