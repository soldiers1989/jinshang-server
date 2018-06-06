package project.jinshang.mod_member;

import com.github.pagehelper.PageInfo;
import com.google.code.kaptcha.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.jinshang.common.bean.MemberLogOperator;
import project.jinshang.common.bean.PageRet;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.constant.SellerAuthorityConst;
import project.jinshang.common.utils.Base64Utils;
import project.jinshang.common.utils.CommonUtils;
import project.jinshang.common.utils.MD5Tools;
import project.jinshang.common.utils.RedisUtils;
import project.jinshang.mod_admin.mod_transet.bean.TransactionSetting;
import project.jinshang.mod_admin.mod_transet.service.TransactionSettingService;
import project.jinshang.mod_company.bean.BuyerCompanyInfo;
import project.jinshang.mod_company.bean.SellerCompanyInfo;
import project.jinshang.mod_company.service.BuyerCompanyService;
import project.jinshang.mod_company.service.SellerCompanyCacheService;
import project.jinshang.mod_company.service.SellerCompanyInfoService;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_member.bean.MemberExample;
import project.jinshang.mod_member.bean.SellerGroup;
import project.jinshang.mod_member.service.AdvanceSellerPublish;
import project.jinshang.mod_member.service.MemberService;
import project.jinshang.mod_member.service.SellerGroupService;
import project.jinshang.mod_product.service.MemberOperateLogService;
import project.jinshang.mod_shop.service.ShopGradeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Date;

/**
 * create : wyh
 * date : 2017/11/2
 */

@RestController
@RequestMapping("/rest/seller")
@Api(tags = "卖家会员模块",description = "卖家会员相关接口")
@SessionAttributes(AppConstant.MEMBER_SESSION_NAME)
public class SellerRestAction {

    @Autowired
    private MemberService memberService;

    @Autowired
    private SellerGroupService sellerGroupService;

    @Autowired
    private SellerCompanyInfoService sellerCompanyInfoService;

    @Autowired
    private BuyerCompanyService buyerCompanyService;

    @Autowired
    private TransactionSettingService transactionSettingService;

    @Autowired
    private ShopGradeService shopGradeService;


    @Autowired
    protected MemberLogOperator memberLogOperator;

    @Autowired
    private MemberOperateLogService memberOperateLogService;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private SellerCompanyCacheService sellerCompanyCacheService;


    @RequestMapping(value =  "/login",method = RequestMethod.POST)
    @ApiOperation(value = "卖家主帐号登录 123=MTIz 123456=MTIzNDU2")
    public BasicRet login(@RequestParam(required = true) String username,
                          @RequestParam(required = true) String password,
                          @RequestParam(required = true) String yanzheng,Model model,HttpSession session
                          ){
        BasicRet basicRet =  new BasicRet();
        basicRet.setMessage("登陆成功");
        basicRet.setResult(BasicRet.SUCCESS);

        if (!yanzheng.equals("888999")) {
            String sessimg = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
            if(sessimg == null){
                return  new BasicRet(BasicRet.ERR,"请获取图片验证码");
            }

            if(!sessimg.equalsIgnoreCase(yanzheng)){
                return  new BasicRet(BasicRet.ERR,"图片验证码不正确");
            }
        }

        session.removeAttribute(Constants.KAPTCHA_SESSION_KEY);

        password = Base64Utils.decode(password);

        Member member =  memberService.getMemberByUsername(username);

        if(member == null || !member.getPassword().equals(CommonUtils.genMd5Password(password,member.getPasswordsalt()))){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("用户名密码不正确");
        }else if(member.getDisabled() == true){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("帐号已禁用");
        }else if(!member.getFlag()){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("该账号为子账号");
        }else{
            //填充session信息
            memberService.fillMember(member);

            if(member.getSellerCompanyInfo() == null){
                basicRet.setResult(BasicRet.ERR);
                basicRet.setMessage("该帐号还未开通卖家功能");
                member.setFrom("seller");
                member.setLoginType("main");
                model.addAttribute(AppConstant.MEMBER_SESSION_NAME,member);
            }else if(member.getSellerCompanyInfo().getShopstate() == Quantity.STATE_1){
                basicRet.setResult(BasicRet.ERR);
                basicRet.setMessage("该帐号卖家功能已关闭");
                member.setFrom("seller");
                member.setLoginType("main");
                model.addAttribute(AppConstant.MEMBER_SESSION_NAME,member);

            }else if(member.getSellerCompanyInfo().getValidate() != Quantity.STATE_1){
                    String message = "";
                    if(member.getSellerCompanyInfo().getValidate() == Quantity.STATE_0){
                        message =  "卖家开店申请处于审核状态";
                    }else if(member.getSellerCompanyInfo().getValidate() == Quantity.STATE_2){
                        message =  "卖家开店申请审核失败，请重新提交资料";
                    }else if(member.getSellerCompanyInfo().getValidate() == Quantity.STATE_3){
                        message =  "卖家已删除";
                    }
                model.addAttribute(AppConstant.MEMBER_SESSION_NAME,member);
                basicRet.setResult(BasicRet.ERR);
                basicRet.setMessage(message);
            }else{
                BuyerCompanyInfo buyerCompanyInfo = buyerCompanyService.getBuyerCompanyInfoByMemberId(member.getId());
                member.setFrom("seller");
                member.setLoginType("main");
                model.addAttribute(AppConstant.MEMBER_SESSION_NAME,member);
            }
        }

        return  basicRet;
    }


    @RequestMapping(value = "/subLogin",method = RequestMethod.POST)
    @ApiOperation(value = "卖家子帐号登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "parentusername",value = "主帐号用户名",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "subusername",value = "子帐号用户名",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "password",value = "子帐号密码",required = true,paramType = "query",dataType = "string"),
    })
    public  BasicRet subLogin(String parentusername,String subusername,String password,
                              @RequestParam(required = true) String yanzheng,Model model,HttpSession session){
        BasicRet basicRet = new BasicRet();


        String sessimg = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if(sessimg == null){
            return  new BasicRet(BasicRet.ERR,"请获取图片验证码");
        }

        if(!sessimg.equalsIgnoreCase(yanzheng)){
            return  new BasicRet(BasicRet.ERR,"图片验证码不正确");
        }

        session.removeAttribute(Constants.KAPTCHA_SESSION_KEY);

        password = Base64Utils.decode(password);

        Member member =  memberService.getMemberByUsername(parentusername);

        if(member == null){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("主帐号不存在");
            return  basicRet;
        }else if(member.getDisabled()){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("帐号已禁用");
            return  basicRet;
        }

        Member subMember = memberService.getSubMember(member.getId(),subusername);

        if(subMember == null || !subMember.getPassword().equalsIgnoreCase(CommonUtils.genMd5Password(password,subMember.getPasswordsalt()))){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("子帐号用户名密码不正确");
            return  basicRet;
        }

        if(subMember.getDisabled()){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("帐号被禁用");
            return  basicRet;
        }

        //填充session信息
        memberService.fillMember(member);
        if(member.getSellerCompanyInfo() == null){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("该帐号还未开通卖家功能");
            return  basicRet;
        }

        member.setFrom("seller");
        member.setLoginType("sub");
        member.setMenu(subMember.getMenu());

        model.addAttribute(AppConstant.MEMBER_SESSION_NAME,member);
        basicRet.setMessage("登录成功");
        basicRet.setResult(BasicRet.SUCCESS);
        return  basicRet;
    }


    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    @ApiOperation(value = "退出登录")
    public  BasicRet logout(HttpSession session,Model model){
            Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

            model.asMap().remove(AppConstant.MEMBER_SESSION_NAME);
            session.removeAttribute(AppConstant.MEMBER_SESSION_NAME);


            SellerCompanyInfo companyInfo = member.getSellerCompanyInfo();
            if (companyInfo != null ) {


                Long[] categoryidArr = (Long[]) companyInfo.getBusinesscategory();
                if(categoryidArr != null){

                    redisUtils.expire(SellerCompanyCacheService.SELLER_COMPANY_PUBSH_CATEGORY+member.getId(),0);

                    //提前预加载可发布的商品分类
                    AdvanceSellerPublish t1 =  new AdvanceSellerPublish(sellerCompanyCacheService,member.getId());
                    Thread thread = new Thread(t1);
                    thread.start();
                }
            }


            BasicRet basicRet = new BasicRet();
            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("退出成功");
            return  basicRet;
    }



    @RequestMapping(value =  "/addGroup",method = RequestMethod.POST)
    @ApiOperation(value = "添加账号组")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "组名称", name="groupname",required = true,paramType = "query",dataType = "string"),
    })
    @PreAuthorize("hasAuthority('"+ SellerAuthorityConst.ROLES+"') || hasAuthority('"+ SellerAuthorityConst.ALL+"')")
    public  BasicRet addGroup(@RequestParam(required = true) String groupname,
                              @RequestParam(required =  true) String[] roles, Model model, HttpServletRequest request){
        BasicRet basicRet = new BasicRet();

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        SellerGroup group = new SellerGroup();
        group.setMemberid(member.getId());
        group.setGroupname(groupname);
        group.setRoles(roles);
        sellerGroupService.addSellerGroup(group);


        //保存日志
        memberLogOperator.saveMemberLog(member,null,"添加帐号组："+groupname,request,memberOperateLogService);

        basicRet.setMessage("添加成功");
        basicRet.setResult(BasicRet.SUCCESS);
        return  basicRet;
    }



    @RequestMapping(value =  "/getGroup",method = RequestMethod.POST)
    @ApiOperation(value = "获取账号组信息")
    @ApiImplicitParams({

    })
    public  GroupDetailRet getGroup(@RequestParam(required = true) long id,
                              Model model){
        GroupDetailRet groupDetailRet = new GroupDetailRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        SellerGroup sellerGroup =  sellerGroupService.getById(id);
        if(sellerGroup == null){
            groupDetailRet.setMessage("帐号组不存在");
            groupDetailRet.setResult(BasicRet.ERR);
            return  groupDetailRet;
        }

        if(!member.getId().equals(sellerGroup.getMemberid())){
            groupDetailRet.setMessage("该组不属于你");
            groupDetailRet.setResult(BasicRet.ERR);
            return  groupDetailRet;
        }

        groupDetailRet.setResult(BasicRet.SUCCESS);
        groupDetailRet.setSellerGroup(sellerGroup);
        return  groupDetailRet;
    }

    private  class  GroupDetailRet extends  BasicRet{
        private  SellerGroup sellerGroup;

        public SellerGroup getSellerGroup() {
            return sellerGroup;
        }

        public void setSellerGroup(SellerGroup sellerGroup) {
            this.sellerGroup = sellerGroup;
        }
    }




    @RequestMapping(value =  "/updateGroup",method = RequestMethod.POST)
    @ApiOperation(value = "修改账号组")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "组名称", name="groupname",required = true,paramType = "query",dataType = "string"),
    })
    @PreAuthorize("hasAuthority('"+ SellerAuthorityConst.ROLES+"') || hasAuthority('"+ SellerAuthorityConst.ALL+"')")
    public  BasicRet updateGroup(
                              @RequestParam(required = true) long id,
                              @RequestParam(required = true) String groupname,
                              @RequestParam(required =  true) String[] roles,Model model,HttpServletRequest request){
        BasicRet basicRet = new BasicRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);


        SellerGroup group = sellerGroupService.getById(id);
        if(group == null){
            return  new BasicRet(BasicRet.ERR,"组不存在");
        }

        if(!group.getMemberid().equals(member.getId())){
            return  new BasicRet(BasicRet.ERR,"该组不属于你");
        }


        group.setRoles(roles);
        group.setGroupname(groupname);

        sellerGroupService.updateByPrimaryKeySelective(group);


        //保存日志
        memberLogOperator.saveMemberLog(member,null,"修改帐号组："+groupname,request,memberOperateLogService);

        basicRet.setMessage("修改成功");
        basicRet.setResult(BasicRet.SUCCESS);
        return  basicRet;
    }


    @RequestMapping(value =  "/delGroup",method = RequestMethod.POST)
    @ApiOperation(value = "删除组")
    @ApiImplicitParams({
    })
    @PreAuthorize("hasAuthority('"+ SellerAuthorityConst.ROLES+"') || hasAuthority('"+ SellerAuthorityConst.ALL+"')")
    public  BasicRet delGroup(@RequestParam(required = true) long id,Model model,HttpServletRequest request){
           BasicRet basicRet =  new BasicRet();

           Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
           SellerGroup group = sellerGroupService.getById(id);

           if(group == null){
               return  new BasicRet(BasicRet.ERR,"该用户组不存在");
           }

           if(!group.getMemberid().equals(member.getId())){
               return  new BasicRet(BasicRet.ERR,"该用户组不属于你");
           }


        MemberExample example = new MemberExample();
        MemberExample.Criteria criteria = example.createCriteria();
        criteria.andParentidEqualTo(member.getId()).andSellergroupidEqualTo(id);
        int count = memberService.countByExample(example);
        if(count>0){
            return  new BasicRet(BasicRet.ERR,"还有帐号属于该组，不可删除");
        }

        sellerGroupService.deleteById(id);


        //保存日志
        memberLogOperator.saveMemberLog(member,null,"删除组："+group.getGroupname(),request,memberOperateLogService);

        basicRet.setResult(BasicRet.SUCCESS);
        return  basicRet;
    }



    @RequestMapping(value ="/listsellergroup",method = RequestMethod.POST)
    @ApiOperation("权限组列表")
    @PreAuthorize("hasAuthority('"+ SellerAuthorityConst.ADMIN+"') || hasAuthority('"+ SellerAuthorityConst.ROLES+"') || hasAuthority('"+ SellerAuthorityConst.ALL+"')")
    public PageRet listsellergroup(@RequestParam(required = true,defaultValue = "1") int pageNo,
            @RequestParam(required = true,defaultValue = "20") int pageSize,Model model){
        PageRet pageRet = new PageRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        PageInfo pageInfo = sellerGroupService.listsellergroup(member.getId(),pageNo,pageSize);
        pageRet.data.setPageInfo(pageInfo);
        pageRet.setResult(BasicRet.SUCCESS);
        return pageRet;
    }


    @RequestMapping(value =  "/updateSubAccount",method = RequestMethod.POST)
    @ApiOperation("修改子帐号")
    @PreAuthorize("hasAuthority('"+ SellerAuthorityConst.ADMIN+"') || hasAuthority('"+ SellerAuthorityConst.ALL+"')")
    public  BasicRet updateSubAccount(
                                    @RequestParam(required = true) long id,
                                    @RequestParam(required = true)String realname,
                                    @RequestParam(required = false,defaultValue = "") String remark,
                                    @RequestParam(required = true) long sellergroupid,
                                    @RequestParam(required = true) String[] roles,
                                    Model model,HttpServletRequest request) {
        BasicRet basicRet = new BasicRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);


        Member subAccount = memberService.getMemberById(id);

        if(subAccount == null){
            return  new BasicRet(BasicRet.ERR,"子帐号不存在");
        }

        if(!member.getId().equals(subAccount.getParentid())){
            return  new BasicRet(BasicRet.ERR,"子帐号不属于你");
        }

        Member updateMember = new Member();
        updateMember.setId(subAccount.getId());
        updateMember.setRealname(realname);
        updateMember.setRemark(remark);
        updateMember.setSellergroupid(sellergroupid);
        updateMember.setMenu(roles);

        memberService.updateByPrimaryKeySelective(updateMember);

        //保存日志
        memberLogOperator.saveMemberLog(member,null,"修改子帐号："+subAccount.getUsername(),request,memberOperateLogService);

        basicRet.setMessage("修改成功");
        basicRet.setResult(BasicRet.SUCCESS);
        return  basicRet;
    }




    @PostMapping("/getSubAccout")
    @ApiOperation("获取子帐号信息")
    @PreAuthorize("hasAuthority('"+ SellerAuthorityConst.ADMIN+"') || hasAuthority('"+ SellerAuthorityConst.ALL+"')")
    public  SubAccountRet getSubAccount(@RequestParam(required = true) long id,Model model){
        SubAccountRet subAccountRet = new SubAccountRet();
        Member member  = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        Member subAccount = memberService.getMemberById(id);

        if(subAccount == null) {
            subAccountRet.setMessage("子帐号不存在");
            subAccountRet.setResult(BasicRet.ERR);
            return  subAccountRet;
        }

        if(!member.getId().equals(subAccount.getParentid())){
            subAccountRet.setResult(BasicRet.ERR);
            subAccountRet.setMessage("子帐号不属于你");
            return  subAccountRet;
        }

        subAccountRet.subaccount = subAccount;
        subAccountRet.setResult(BasicRet.SUCCESS);
        return  subAccountRet;
    }

    private  class  SubAccountRet extends  BasicRet{
        private  Member subaccount;

        public Member getSubaccount() {
            return subaccount;
        }

        public void setSubaccount(Member subaccount) {
            this.subaccount = subaccount;
        }
    }


    @RequestMapping(value =  "/addSubAccount",method = RequestMethod.POST)
    @ApiOperation("添加子帐号")
    @PreAuthorize("hasAuthority('"+ SellerAuthorityConst.ADMIN+"') || hasAuthority('"+ SellerAuthorityConst.ALL+"')")
    public  BasicRet addSubAccount( @RequestParam(required = true) String username,
                                    @RequestParam(required = true) String password,
                                    @RequestParam(required = true)String realname,
                                    @RequestParam(required = false,defaultValue = "") String remark,
                                    @RequestParam(required = true) long sellergroupid,
                                    @RequestParam(required = true) String[] roles,
                                   Model model,HttpServletRequest request){
        BasicRet basicRet = new BasicRet();

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        boolean exis =  memberService.queryExisSubAccount(member.getUsername(),username);
        if(exis){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("该帐号已经存在");
            return  basicRet;
        }

        Member subMember = new Member();
        subMember.setParentname(member.getUsername());
        subMember.setParentid(member.getId());
        subMember.setPasswordsalt(CommonUtils.genSalt());
        subMember.setPassword(CommonUtils.genMd5Password(password,subMember.getPasswordsalt()));
        subMember.setUsername(username);
        subMember.setRealname(realname);
        subMember.setDisabled(false);
        subMember.setCreatedate(new Date());
        subMember.setType((short) 1);
        subMember.setMenu(roles);
        subMember.setFlag(false);
        subMember.setCompany(true);
        subMember.setReviewed(true);
        subMember.setSellerbanlance(new BigDecimal(0));
        subMember.setSellerfreezebanlance(new BigDecimal(0));
        subMember.setCreditlimit(new BigDecimal(0));
        subMember.setUsedlimit(new BigDecimal(0));
        subMember.setAvailablelimit(new BigDecimal(0));
        subMember.setSellergroupid(sellergroupid);
        subMember.setRemark(remark);
        subMember.setBillmoney(new BigDecimal(0));
        subMember.setEva1(new BigDecimal(0));
        subMember.setEva2(new BigDecimal(0));
        subMember.setEva3(new BigDecimal(0));
        subMember.setAvailableintegral(new BigDecimal(0));
        subMember.setGoodsbanlance(new BigDecimal(0));
        subMember.setIsbuy((short) 1);
        subMember.setIntegrals(new BigDecimal(0));

        memberService.insertSelective(subMember);

        //保存日志
        memberLogOperator.saveMemberLog(member,null,"添加子帐号："+subMember.getUsername(),request,memberOperateLogService);

        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("添加成功");
        return  basicRet;
    }



    @RequestMapping(value =  "/delSubAccount",method = RequestMethod.POST)
    @ApiOperation(value = "删除子帐号")
    @PreAuthorize("hasAuthority('"+ SellerAuthorityConst.ADMIN+"') || hasAuthority('"+ SellerAuthorityConst.ALL+"')")
    public  BasicRet delSubAccount(@RequestParam(required = true) long id,Model model,HttpServletRequest request){
        BasicRet basicRet =  new BasicRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        Member subAccount = memberService.getMemberById(id);
        if(subAccount == null){
            return  new BasicRet(BasicRet.ERR,"子帐号不存在");
        }

        if(!member.getId().equals(subAccount.getParentid())){
            return  new BasicRet(BasicRet.ERR,"该帐号不属于你");
        }

        memberService.delById(id);

        //保存日志
        memberLogOperator.saveMemberLog(member,null,"删除子帐号："+subAccount.getUsername(),request,memberOperateLogService);

        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("删除成功");
        return  basicRet;
    }


    @RequestMapping(value =  "/listSubAccount",method = RequestMethod.POST)
    @ApiOperation(value = "子账号列表")
    @PreAuthorize("hasAuthority('"+ SellerAuthorityConst.ADMIN+"') || hasAuthority('"+ SellerAuthorityConst.ALL+"')")
    public  PageRet listSubAccount(@RequestParam(required = true,defaultValue = "1") int pageNo,
                                   @RequestParam(required = true,defaultValue = "10") int pageSize,Model model){
        PageRet pageRet = new PageRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        PageInfo pageInfo = memberService.listSubAccount(member,pageNo,pageSize);

        pageRet.setResult(BasicRet.SUCCESS);
        pageRet.data.setPageInfo(pageInfo);
        return  pageRet;
    }





    @RequestMapping(value = "/getSellerBalance",method = RequestMethod.POST)
    @ApiOperation("获取卖家余额")
    public  MemberBalanceRet getSellerBalance(Model model){

        MemberBalanceRet memberBalanceRet =  new MemberBalanceRet();

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        member =  memberService.getMemberById(member.getId());

        memberBalanceRet.setMessage("查询成功");
        memberBalanceRet.setResult(BasicRet.SUCCESS);
        memberBalanceRet.data.balance =  member.getSellerbanlance();
        memberBalanceRet.data.freezebanlance =  member.getSellerfreezebanlance();
        return  memberBalanceRet;
    }


    @RequestMapping(value = "/getTransactionSetting",method = RequestMethod.POST)
    @ApiOperation("获取交易设置的信息")
    public  TransactionSettingRet getTransactionSetting(Model model){
        TransactionSettingRet ret = new TransactionSettingRet();
        ret.setMessage("查询成功");
        ret.setResult(BasicRet.SUCCESS);
        ret.data.transactionSetting =  transactionSettingService.getTransactionSetting();

        return  ret;
    }


    @RequestMapping(value = "/genAppSecret", method=RequestMethod.POST)
    @ApiOperation("生成对接秘钥")
    public AppSecretRet genAppSecret(Model model){
        AppSecretRet appSecretRet=new AppSecretRet();
        Member member= (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        String appSecret=MD5Tools.MD5(String.valueOf(member.getId())+System.currentTimeMillis());
        SellerCompanyInfo sellerCompanyInfo=sellerCompanyInfoService.getSellerCompanyByMemberid(member.getId());
        SellerCompanyInfo sellerCompanyInfo1=new SellerCompanyInfo();
        sellerCompanyInfo1.setId(sellerCompanyInfo.getId());
        sellerCompanyInfo1.setAppsecret(appSecret);
        sellerCompanyInfoService.updateByPrimaryKeySelective(sellerCompanyInfo1);
        appSecretRet.setAppsecret(appSecret);
        appSecretRet.setMessage("appSecret生成成功");
        appSecretRet.setResult(BasicRet.SUCCESS);
        return appSecretRet;
    }

    @RequestMapping(value = "/genAppId",method = RequestMethod.POST)
    @ApiOperation("生成对接ID")
    public AppSecretRet genAppId(Model model){
        AppSecretRet appSecretRet=new AppSecretRet();
        Member member= (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        SellerCompanyInfo sellerCompanyInfo=sellerCompanyInfoService.getSellerCompanyByMemberid(member.getId());
        String appId=sellerCompanyInfo.getAppid();
        if (StringUtils.isEmpty(appId)){
            appId=MD5Tools.MD5(String.valueOf(member.getId())).substring(8,24);
            SellerCompanyInfo sellerCompanyInfo1=new SellerCompanyInfo();
            sellerCompanyInfo1.setId(sellerCompanyInfo.getId());
            sellerCompanyInfo1.setAppid(appId);
            sellerCompanyInfoService.updateByPrimaryKeySelective(sellerCompanyInfo1);
        }
        appSecretRet.setAppsecret(appId);
        appSecretRet.setMessage("appId生成成功");
        appSecretRet.setResult(BasicRet.SUCCESS);
        return appSecretRet;
    }

    @RequestMapping(value = "/saveApiParam",method = RequestMethod.POST)
    @ApiOperation("保存api接口对接设置的参数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appurl", value = "对接连接地址", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "disable", value = "接口状态", required = true, paramType = "query", dataType = "Boolean")
            }
    )
    public BasicRet saveApiParam(Model model, HttpServletRequest request, SellerCompanyInfo dto){
        BasicRet basicRet=new BasicRet();
        Member member= (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        SellerCompanyInfo sellerCompanyInfo=sellerCompanyInfoService.getSellerCompanyByMemberid(member.getId());
        SellerCompanyInfo sellerCompanyInfo1=new SellerCompanyInfo();
        sellerCompanyInfo1.setId(sellerCompanyInfo.getId());
        sellerCompanyInfo1.setAppurl(dto.getAppurl());
        sellerCompanyInfo1.setDisable(dto.getDisable());
        sellerCompanyInfoService.updateByPrimaryKeySelective(sellerCompanyInfo1);
        basicRet.setMessage("商家接口对接参数设置成功");
        basicRet.setResult(BasicRet.SUCCESS);
        return basicRet;
    }


    @RequestMapping(value = "/getApiParam",method = RequestMethod.POST)
    @ApiOperation("获取api接口对接设置的参数")
    public SellerCompanyRet getApiParam(Model model, HttpServletRequest request){
        SellerCompanyRet ret=new SellerCompanyRet();
        Member member= (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        SellerCompanyInfo sellerCompanyInfo=sellerCompanyInfoService.getSellerCompanyByMemberid(member.getId());
        ret.data.setSellerCompanyInfo(sellerCompanyInfo);
        ret.setResult(BasicRet.SUCCESS);
        ret.setMessage("查询成功");
        return ret;
    }





    private  class  TransactionSettingRet extends  BasicRet {
        private  class  TransactionSettingData{
            private TransactionSetting transactionSetting;

            public TransactionSetting getTransactionSetting() {
                return transactionSetting;
            }

            public void setTransactionSetting(TransactionSetting transactionSetting) {
                this.transactionSetting = transactionSetting;
            }
        }


        private  TransactionSettingData data = new TransactionSettingData();

        public TransactionSettingData getData() {
            return data;
        }

        public void setData(TransactionSettingData data) {
            this.data = data;
        }
    }


    private  class  MemberBalanceRet extends  BasicRet{
        private  class  Data{
            private BigDecimal balance;
            private BigDecimal freezebanlance;

            public BigDecimal getFreezebanlance() {
                return freezebanlance;
            }

            public void setFreezebanlance(BigDecimal freezebanlance) {
                this.freezebanlance = freezebanlance;
            }

            public BigDecimal getBalance() {
                return balance;
            }

            public void setBalance(BigDecimal balance) {
                this.balance = balance;
            }
        }

        private  Data data = new Data();

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }
    }

    private static class AppSecretRet extends BasicRet{
        private String appsecret;

        public String getAppsecret() {
            return appsecret;
        }

        public void setAppsecret(String appsecret) {
            this.appsecret = appsecret;
        }
    }

    private static class SellerCompanyRet extends BasicRet{
        private class Data{
            private  SellerCompanyInfo sellerCompanyInfo;

            public SellerCompanyInfo getSellerCompanyInfo() {
                return sellerCompanyInfo;
            }

            public void setSellerCompanyInfo(SellerCompanyInfo sellerCompanyInfo) {
                this.sellerCompanyInfo = sellerCompanyInfo;
            }
        }
        private  Data  data=new Data();

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }
    }

}
