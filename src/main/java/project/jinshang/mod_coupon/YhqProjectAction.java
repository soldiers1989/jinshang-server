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
import project.jinshang.common.constant.SellerAuthorityConst;
import project.jinshang.common.utils.GenerateNo;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_common.bean.BasicExtRet;
import project.jinshang.mod_coupon.bean.YhqProject;
import project.jinshang.mod_coupon.bean.YhqProjectExample;
import project.jinshang.mod_coupon.bean.YhqTicket;
import project.jinshang.mod_coupon.bean.YhqTicketset;
import project.jinshang.mod_coupon.service.YhqProjectService;
import project.jinshang.mod_member.bean.Admin;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_member.service.MemberService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = {"/rest/admin/yhqproject"})
@Api(tags = "后台优惠券分发管理", description = "优惠券分发接口")
@SessionAttributes(AppConstant.ADMIN_SESSION_NAME)
public class YhqProjectAction {

    @Autowired
    private YhqProjectService yhqProjectService;

    @Autowired
    private MemberService memberService;

    @ApiOperation(value = "优惠券分发列表/搜索")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "名称", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "status", value = "状态", required = false, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "pageNo", value = "页码(值为-1不分页)", required = true, paramType = "query", dataType = "int", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页数", required = false, paramType = "query", dataType = "int", defaultValue = "20"),
    })
    @PostMapping("/list")
    @PreAuthorize("hasAuthority('"+ AdminAuthorityConst.COUPONDISTRIBUTION+"') || hasAuthority('"+ SellerAuthorityConst.ALL+"')")
    public PageRet getList(int pageNo, int pageSize, YhqProject yhqProject) {
        PageInfo pageInfo = yhqProjectService.getListByPage(pageNo, pageSize,yhqProject);

        PageRet pageRet = new PageRet();
        pageRet.data.setPageInfo(pageInfo);
        pageRet.setMessage("返回成功");
        pageRet.setResult(BasicRet.SUCCESS);
        return pageRet;
    }



    @PostMapping(value = "/insert")
    @ApiOperation(value = "添加优惠券分发")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "no", value = "编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "name", value = "名称", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "about", value = "简介", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "ticketlist", value = "发放方案", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "type", value = "发放类型", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "rule", value = "发放规则", required = true, paramType = "query", dataType = "string"),
    })
    public BasicRet insertYhqProjectInfo(YhqProject yhqProject, Model model) {
        BasicRet basicRet = new BasicRet();
        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
        yhqProject.setNo(GenerateNo.getYhqFFNo());
        yhqProject.setStatus(1l);
        yhqProject.setUsersid(admin.getId());
        yhqProject.setCreatetime(new Date());
        yhqProjectService.insertYhqProjectInfo(yhqProject);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("新增成功");
        return basicRet;
    }



    @PostMapping(value = "/get")
    @ApiOperation(value = "查看优惠券分发")
    @ApiImplicitParam(name = "id", value = "优惠券分发项目Id", required = true, paramType = "query", dataType = "long")
    public BasicRet getYhqProjectInfoDetail(Long id) {
        BasicExtRet basicRet = new BasicExtRet();
        YhqProject yhqProject = yhqProjectService.getYhqProjectInfoById(id);
        if (yhqProject == null) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("不存在该优惠券分发记录");
        } else {
            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("获取成功");
            basicRet.setData(yhqProject);
        }
        return basicRet;
    }



    @PostMapping(value = "/update")
    @ApiOperation(value = "编辑优惠券分发项目")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "优惠券Id", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "name", value = "名称", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "about", value = "简介", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "type", value = "发放类型1：批量发放2：指定人发放", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "rule", value = "发放规则", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "ticketlist", value = "发放方案", required = true, paramType = "query", dataType = "string"),
    })
    public BasicRet updateYhqProjectInfo(YhqProject yhqProject) {
        BasicRet basicRet = new BasicRet();
        YhqProject oldYhqProject = yhqProjectService.getYhqProjectInfoById(yhqProject.getId());
        if (oldYhqProject == null) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("不存在该优惠券分发项目");
        } else {
            yhqProjectService.updateYhqProjectInfo(yhqProject);
            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("修改成功");
        }
        return basicRet;
    }



  /*  @ApiOperation(value = "查看优惠券项目下分发记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "名称", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "type", value = "类型", required = false, paramType = "query", dataType = "long", defaultValue = "0"),
            @ApiImplicitParam(name = "planid", value = "优惠券方案ID", required = false, paramType = "query", dataType = "long", defaultValue = "0"),
            @ApiImplicitParam(name = "ticketsetid", value = "优惠券配置ID", required = false, paramType = "query", dataType = "long", defaultValue = "0"),
            @ApiImplicitParam(name = "status", value = "状态", required = false, paramType = "query", dataType = "string" , defaultValue = "0"),
            @ApiImplicitParam(name = "pageNo", value = "页码(值为-1不分页)", required = true, paramType = "query", dataType = "int", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页数", required = false, paramType = "query", dataType = "int", defaultValue = "20"),
    })
    @PostMapping("/list1")
    public PageRet getList1(int pageNo, int pageSize, YhqProject yhqProject) {
        PageInfo pageInfo = yhqProjectService.getListByPage1(pageNo, pageSize,yhqProject);

        PageRet pageRet = new PageRet();
        pageRet.data.setPageInfo(pageInfo);
        pageRet.setMessage("返回成功");
        pageRet.setResult(BasicRet.SUCCESS);
        return pageRet;
    }*/



    @PostMapping(value = "/delete")
    @ApiOperation(value = "删除优惠券分发项目")
    @ApiImplicitParam(name = "id", value = "优惠券分发项目Id", required = true, paramType = "query", dataType = "long")
    public BasicRet deleteYhqTicketSetInfo(Long id) {
        BasicRet basicRet = new BasicRet();
        YhqProject oldYhqProject = yhqProjectService.getYhqProjectInfoById(id);
        if (oldYhqProject == null) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("不存在优惠券分发项目");
        } else {
            //优惠券分发表
            YhqProjectExample yhqProjectExample = new YhqProjectExample();
            YhqProjectExample.Criteria criteria = yhqProjectExample.createCriteria();
            criteria.andIdEqualTo(id);
            criteria.andStatusEqualTo(99l);
            long count = yhqProjectService.countByExample(yhqProjectExample);
            if (count > 0) {
                basicRet.setResult(BasicRet.ERR);
                basicRet.setMessage("删除失败,该优惠券分发项目已审核无法再删除");
                return basicRet;
            }
            yhqProjectService.deleteYhqProjectInfoById(id);
            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("删除成功");
        }
        return basicRet;
    }


    @PostMapping(value = "/getUsername")
    @ApiOperation(value = "校验用户名/ID存不存在")
    @ApiImplicitParam(name = "membername", value = "用户名/ID", required = true, paramType = "query", dataType = "string")
    public BasicRet getUserInfoDetail(String membername) {
        BasicExtRet basicRet = new BasicExtRet();
        long id = 0;
        String newmembername = "";
     //根据传进来到根据传进来的搜索关键字 判断是id还是用户名
        if(StringUtils.isNumeric(membername)){
            id = Long.parseLong(membername);
            newmembername ="";
        }else{
            newmembername = membername;
        }
        Member member = memberService.getMemberByUsernameOrId(newmembername,id);
        if (member == null) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("不存在该用户名");
        } else {
            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("获取成功");
            basicRet.setData(member);
        }
        return basicRet;
    }




    /*@PostMapping(value = "/insert")
    @ApiOperation(value = "添加优惠券分发项目")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "no", value = "编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "name", value = "名称", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "about", value = "简介", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "ticketlist", value = "发放方案", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "type", value = "发放类型", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "rule", value = "发放规则", required = true, paramType = "query", dataType = "string"),
    })
    public BasicRet insert(YhqProject yhqProject, Model model) {
        BasicRet basicRet = new BasicRet();
        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
        yhqProject.setNo(GenerateNo.getYhqFFNo());
        yhqProject.setStatus(1l);
        yhqProject.setUsersid(admin.getId());
        yhqProject.setCreatetime(new Date());
        yhqProjectService.insertYhqProjectInfo(yhqProject);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("新增成功");
        return basicRet;
    }*/


  /*  @PostMapping(value = "/executeticket")
    @ApiOperation(value = "优惠券执行分发")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "优惠券分发方案id", required = true, paramType = "query", dataType = "long"),
    })
    public BasicRet executeticket(YhqProject yhqProject,Model model) {
        BasicRet basicRet = new BasicRet();

        List<YhqProject> list = yhqProjectService.getYhqProjectInfoById(yhqProject.getId());
        for (YhqProject yhqProject1: list) {
            ///获取优惠券分发方案
            yhqProject1.getTicketlist();
            JSONArray jsonArray = JSONArray.fromObject(yhqProject1.getTicketlist());
            JSONArray jsonArray1 = JSONArray.fromObject(yhqProject1.getRule());
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
                        YhqTicket yhqTicket = yhqTicketService.updateByYhqTicket(no,createtime,);
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
                    yhqTicket.setOrdersid(0l);
                    yhqTicket.setUsersid(0l);
                    yhqTicket.setCreatetime(new Date());
                    yhqTicketService.insertYhqTicketInfo(yhqTicket);
                    basicRet.setResult(BasicRet.SUCCESS);
                    basicRet.setMessage("优惠券生成到会员下成功");

                }

            }

        }


        return basicRet;

    }*/











}
