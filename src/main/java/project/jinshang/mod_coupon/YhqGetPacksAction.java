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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import project.jinshang.common.bean.PageRet;
import project.jinshang.common.constant.AdminAuthorityConst;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.utils.GenerateNo;
import project.jinshang.mod_common.bean.BasicExtRet;
import project.jinshang.mod_coupon.bean.*;
import project.jinshang.mod_coupon.service.YhqGetPacksService;
import project.jinshang.mod_coupon.service.YhqTicketPacksService;
import project.jinshang.mod_coupon.service.YhqTicketService;
import project.jinshang.mod_coupon.service.YhqTicketSetService;
import project.jinshang.mod_member.service.MemberService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = {"/rest/admin/yhqgetpacks"})
@Api(tags = "后台优惠券礼包领用记录管理", description = "优惠券礼包领用记录接口")
@SessionAttributes(AppConstant.ADMIN_SESSION_NAME)
public class YhqGetPacksAction {
    @Autowired
    private YhqGetPacksService yhqGetPacksService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private YhqTicketPacksService yhqTicketPacksService;

    @Autowired
    private YhqTicketService yhqTicketService;

    @Autowired
    private YhqTicketSetService yhqTicketSetService;



    @PostMapping(value = "/get")
    @ApiOperation(value = "获取优惠券礼包领用记录详情")
    @ApiImplicitParam(name = "id", value = "优惠券礼包领用记录Id", required = true, paramType = "query", dataType = "long")
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.COUPONRECEIVE  + "')")
    public BasicRet getYhqGetpacksInfoDetail(Long id) {
        BasicExtRet basicRet = new BasicExtRet();
        YhqGetpacks yhqGetpacks = yhqGetPacksService.getYhqGetpacksInfoById(id);
        if (yhqGetpacks == null) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("不存在该优惠券礼包领用记录");
        } else {
            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("获取成功");
            basicRet.setData(yhqGetpacks);
        }
        return basicRet;
    }


    @ApiOperation(value = "优惠券礼包领用记录列表/搜索")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "领取人Id", required = false, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "packsid", value = "优惠券礼包Id", required = false, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "membername", value = "用户名", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "pageNo", value = "页码(值为-1不分页)", required = true, paramType = "query", dataType = "int", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页数", required = false, paramType = "query", dataType = "int", defaultValue = "20"),
    })
    @PostMapping("/list")
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.COUPONRECEIVE  + "')")
    public PageRet getList(int pageNo, int pageSize, YhqGetpacks yhqGetpacks, String membername) {
        PageInfo pageInfo = null;
        if(membername != null) {
             pageInfo = yhqGetPacksService.getListByPage(pageNo, pageSize,yhqGetpacks,membername);
        }else{
            pageInfo = yhqGetPacksService.getListByPage1(pageNo, pageSize,yhqGetpacks);
        }


        PageRet pageRet = new PageRet();
        pageRet.data.setPageInfo(pageInfo);
        pageRet.setMessage("返回成功");
        pageRet.setResult(BasicRet.SUCCESS);
        return pageRet;
    }



    @PostMapping(value = "/use")
    @ApiOperation(value = "优惠券礼包领用")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "packsno", value = "优惠券礼包编码", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "memberid", value = "领取用户id", required = true, paramType = "query", dataType = "long"),
    })
    public BasicRet YhqGetPacksUse(YhqGetpacks yhqGetpacks, Model model) {
        BasicRet basicRet = new BasicRet();


        YhqGetpacksExample example = new YhqGetpacksExample();
        YhqGetpacksExample.Criteria criteria = example.createCriteria();
        criteria.andMemberidEqualTo(yhqGetpacks.getMemberid());
        criteria.andPacksnoEqualTo(yhqGetpacks.getPacksno());
        long num = yhqGetPacksService.countByExample(example);
        if(num >0){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("该用户已经领取过此优惠券礼包");
            return basicRet;
        }
        //查询礼包信息且剩余数量是大于0的
        List<YhqTicketpacks> list = yhqTicketPacksService.selectByNoAndSurplusCount(yhqGetpacks.getPacksno());
        for (YhqTicketpacks yhqTicketpacks: list) {
            ///获取优惠券发放方案
            yhqTicketpacks.getTicketlist();

            JSONArray jsonArray = JSONArray.fromObject(yhqTicketpacks.getTicketlist());
           // List<Map<String,Object>> list1 = null;
            for(int i= 0;i < jsonArray.size();i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                //里面json存的ticketid是ticketsetid其实
                String ticketid = jsonObject.getString("ticketid");
                //这里的no是优惠券配置的no
                //String no = jsonObject.getString("no");
                long ticketid1=Long.parseLong(ticketid);
                YhqTicketset yhqticketset = yhqTicketSetService.selectById(ticketid1);
                //提前生成
                if(yhqticketset.getCreatetype() ==1){
                    //查询优惠券 ticketsetid和 status为1 未领取的
                    int status = 1;
                    List<Map<String,Object>>  list1 = yhqTicketService.selectByTicketSetIdAndStatus(ticketid1,status);
                    for (Map<String,Object> map:list1) {
                        //更新到会员名下
                       String no = (String) map.get("no");
                        Date createtime = new Date();
                        YhqTicket yhqTicket = yhqTicketService.updateByYhqTicket(no,createtime,yhqGetpacks.getMemberid());
                        basicRet.setResult(BasicRet.SUCCESS);
                        basicRet.setMessage("优惠券更新到会员下成功");

                    }

                }else {
                    //领用生成
                    int status = 2;
                    //生成到会员名下
                    //优惠券配置里直接读取
                    YhqTicketset yhqticketset1 = yhqTicketSetService.selectById(ticketid1);
                    YhqTicket yhqTicket = new YhqTicket();
                    yhqTicket.setNo(GenerateNo.getYhqNo());
                    yhqTicket.setName(yhqticketset1.getName());
                    yhqTicket.setType(1l);
                    yhqTicket.setPlanid(yhqticketset1.getPlanid());
                    yhqTicket.setTicketsetid(ticketid1);
                    yhqTicket.setProjectid(0l);
                    yhqTicket.setAbout(yhqticketset1.getAbout());
                    BigDecimal zero = new BigDecimal(0);
                    yhqTicket.setCapital(zero);
                    yhqTicket.setAvailable(zero);
                    yhqTicket.setRule(yhqticketset1.getRule());
                    yhqTicket.setStarttime(yhqticketset1.getStarttime());
                    yhqTicket.setEndtime(yhqticketset1.getEndtime());
                    yhqTicket.setValiditystarttime(yhqticketset1.getValiditystarttime());
                    yhqTicket.setValidityendtime(yhqticketset1.getValidityendtime());
                    yhqTicket.setStatus(2l);
                    yhqTicket.setMemberid(yhqGetpacks.getMemberid());
                    yhqTicket.setGettime(new Date());
//                    yhqTicket.setOrdersid(0l);
                    yhqTicket.setUsersid(0l);
                    yhqTicket.setCreatetime(new Date());
                    yhqTicketService.insertYhqTicketInfo(yhqTicket);
                    basicRet.setResult(BasicRet.SUCCESS);
                    basicRet.setMessage("优惠券生成到会员下成功");

                }

            }

        }


        return basicRet;

    }










}
