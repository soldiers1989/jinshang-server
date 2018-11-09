package project.jinshang.mod_member.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import mizuki.project.core.restserver.config.BasicRet;
import org.apache.ibatis.annotations.Param;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import project.jinshang.common.bean.PageRet;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.utils.*;
import project.jinshang.mod_admin.mod_count.bean.MemberQueryParam;
import project.jinshang.mod_admin.mod_count.bean.MemberStatistcModel;
import project.jinshang.mod_admin.mod_inte.bean.IntegralRecord;
import project.jinshang.mod_admin.mod_inte.bean.IntegralSet;
import project.jinshang.mod_admin.mod_inte.service.IntegralService;
import project.jinshang.mod_common.service.MobileService;
import project.jinshang.mod_company.bean.BuyerCompanyInfo;
import project.jinshang.mod_company.bean.SellerCompanyInfo;
import project.jinshang.mod_company.service.BuyerCompanyService;
import project.jinshang.mod_company.service.SellerCompanyCacheService;
import project.jinshang.mod_company.service.SellerCompanyInfoService;
import project.jinshang.mod_member.MemberMapper;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_member.bean.MemberExample;
import project.jinshang.mod_member.bean.MemberGrade;
import project.jinshang.mod_member.bean.RegisteType;
import project.jinshang.mod_member.bean.dto.MemberAdminViewDto;
import project.jinshang.mod_member.bean.dto.MemberDto;
import project.jinshang.mod_shop.bean.ShopGrade;
import project.jinshang.mod_shop.service.ShopGradeService;
import project.jinshang.scheduled.mapper.AppTaskMapper;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;


/**
 * create : wyh
 * date : 2017/10/27
 */

@Service
public class MemberService {

    @Resource
    private MemberMapper memberMapper;

    @Autowired
    private MobileService mobileService;

    @Autowired
    private MemberGradeService memberGradeService;


    @Autowired
    private SellerCompanyInfoService sellerCompanyInfoService;

    @Autowired
    private ShopGradeService shopGradeService;

    @Autowired
    private BuyerCompanyService buyerCompanyService;

    @Autowired
    private IntegralService integralService;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private AppTaskMapper appTaskMapper;


    /**
     * 用户名是否已经被注册
     * @param userame
     * @return
     */
    public boolean exisUsername(String userame) {

        MemberExample example = new MemberExample();
        example.createCriteria().andUsernameEqualTo(userame);
        int count = memberMapper.countByExample(example);
        if (count == 0) {
            return false;
        }
        return true;
    }

    /**
     * 手机号是否已经被注册
     *
     * @param mobile
     * @return
     */
    public boolean exisMobile(String mobile) {
        MemberExample example = new MemberExample();
        example.createCriteria().andMobileEqualTo(mobile);
        int count = memberMapper.countByExample(example);
        if (count == 0) {
            return false;
        }
        return true;
    }


    /**
     * 注册用户
     *
     * @param member
     * @return
     */
    public ErrorMes registerMember(Member member) {

        ErrorMes errorMes = new ErrorMes();

        checkRegisterMember(errorMes, member);

        if (errorMes.getSize() != 0) {
            return errorMes;
        }

        if (exisUsername(member.getUsername())) {
            errorMes.addError("username", "该用户已存在");
        }

        if (!member.getMobile().equals(AppConstant.MOCK_MOBILE) && exisMobile(member.getMobile())) {
            errorMes.addError("mobile", "手机号已存在");
        }

        //客户输入的邀请码
        String customInvitecode = member.getInvitecode();

        if (errorMes.getSize() == 0) {
            member.setType((short) 0);
            member.setCompany(false);
            member.setIntegrals(new BigDecimal(0));
            member.setPasswordsalt(CommonUtils.genSalt());
            member.setPassword(CommonUtils.genMd5Password(member.getPassword(), member.getPasswordsalt()));
            member.setDisabled(false);
            member.setCreatedate(new Date());
            member.setReviewed(true);
            member.setParentid((long) 0);
            member.setParentname("");
            member.setFlag(true);
            member.setRegistesource(member.getRegistesource()!=null?member.getRegistesource(): RegisteType.TYPE03.getType());

            //生成邀请码
            String inviteCode = null;
            while (inviteCode == null) {
                String uuid = GenerateNo.getUUID().substring(0, 6);

                MemberExample memberExample = new MemberExample();
                MemberExample.Criteria criteria = memberExample.createCriteria();
                criteria.andInvitecodeEqualTo(uuid);
                int count = memberMapper.countByExample(memberExample);
                if (count == 0) {
                    inviteCode = uuid;
                }

            }

            member.setInvitecode(inviteCode);
            //查询是谁邀请的，设置邀请者的id
            if(StringUtils.hasText(customInvitecode)) {
                Member inviterMember = getByInvitecode(customInvitecode);
                if(inviterMember != null) {
                    member.setInviterid(inviterMember.getId());
                    //设置邀请人的二级邀请者id
                    if(null != inviterMember.getInviterid()){
                        member.setInviterid2(inviterMember.getInviterid());
                    }
                }


            }


            MemberGrade memberGrade = memberGradeService.getDefaultMemberGrade();
            if (memberGrade != null) {
                member.setGradleid((long) memberGrade.getId());
            }

            memberMapper.insertSelective(member);

            //注册送积分
            IntegralSet integralSet = integralService.getIntegralSetByType(Quantity.STATE_1);
            if (integralSet != null) {
                member.setIntegrals(integralSet.getScope());
                IntegralRecord record = new IntegralRecord();
                record.setMemberid(member.getId());
                record.setMembername(member.getUsername());
                record.setScope(integralSet.getScope());
                record.setType(Quantity.STATE_1);
                record.setCreatetime(new Date());
                record.setRegistertime(new Date());
                record.setRemark("注册得积分");
                integralService.updateMemberIntegral(member, record);
            }


            integralSet = integralService.getIntegralSetByType(Quantity.STATE_2);
            //查询是谁邀请的，送积分
            if (StringUtils.hasText(member.getInvitecode()) && integralSet != null && customInvitecode != null) {
                Member invMember = getByInvitecode(customInvitecode);
                if (invMember != null) {
                    Member updateMember = new Member();
                    updateMember.setId(invMember.getId());
                    updateMember.setIntegrals(invMember.getIntegrals().add(integralSet.getScope()));

                    IntegralRecord record = new IntegralRecord();
                    record.setMemberid(invMember.getId());
                    record.setMembername(invMember.getUsername());
                    record.setRemark("邀请得积分");
                    record.setScope(integralSet.getScope());
                    record.setType(Quantity.STATE_2);
                    record.setCreatetime(new Date());
                    record.setRegisterid(member.getId());
                    record.setRegistername(member.getUsername());
                    record.setRegistertime(member.getCreatedate());
                    record.setRegisterscope(member.getIntegrals());

                    integralService.updateMemberIntegral(updateMember, record);
                }
            }

        }

        return errorMes;
    }




    /**
     *新增通过手机号码注册用户
     * @author xiazy
     * @date  2018/6/12 15:18
     * @param member
     * @return project.jinshang.common.utils.ErrorMes
     */
    public ErrorMes registerMemberByMobile(Member member) {

        ErrorMes errorMes = new ErrorMes();

        if (!StringUtils.hasText(member.getMobile())) {
            errorMes.addError("mobile", "手机号不可为空");
            return errorMes;
        }

        member.setUsername(member.getMobile());
        if (exisUsername(member.getUsername())) {
            errorMes.addError("username", "该用户已存在");
        }

        if (!member.getMobile().equals(AppConstant.MOCK_MOBILE) && exisMobile(member.getMobile())) {
            errorMes.addError("mobile", "手机号已存在");
        }

        //客户输入的邀请码
        String customInvitecode = member.getInvitecode();

        if (errorMes.getSize() == 0) {
            member.setType((short) 0);
            member.setCompany(false);
            member.setIntegrals(new BigDecimal(0));
            member.setDisabled(false);
            member.setCreatedate(new Date());
            member.setReviewed(true);
            member.setParentid((long) 0);
            member.setParentname("");
            member.setFlag(true);
            member.setRegistesource(member.getRegistesource()!=null?member.getRegistesource():RegisteType.TYPE03.getType());

            //生成邀请码
            String inviteCode = null;
            while (inviteCode == null) {
                String uuid = GenerateNo.getUUID().substring(0, 6);

                MemberExample memberExample = new MemberExample();
                MemberExample.Criteria criteria = memberExample.createCriteria();
                criteria.andInvitecodeEqualTo(uuid);
                int count = memberMapper.countByExample(memberExample);
                if (count == 0) {
                    inviteCode = uuid;
                }

            }

            member.setInvitecode(inviteCode);
            //查询是谁邀请的，设置邀请者的id
            if(StringUtils.hasText(customInvitecode)) {
                Member inviterMember = getByInvitecode(customInvitecode);
                if(inviterMember != null) {
                    member.setInviterid(inviterMember.getId());
                    //设置邀请人的二级邀请者id
                    if(null != inviterMember.getInviterid()) {
                        member.setInviterid2(inviterMember.getInviterid());
                    }
                }

            }

            MemberGrade memberGrade = memberGradeService.getDefaultMemberGrade();
            if (memberGrade != null) {
                member.setGradleid((long) memberGrade.getId());
            }

            memberMapper.insertSelective(member);

            //注册送积分
            IntegralSet integralSet = integralService.getIntegralSetByType(Quantity.STATE_1);
            if (integralSet != null) {
                member.setIntegrals(integralSet.getScope());
                IntegralRecord record = new IntegralRecord();
                record.setMemberid(member.getId());
                record.setMembername(member.getUsername());
                record.setScope(integralSet.getScope());
                record.setType(Quantity.STATE_1);
                record.setCreatetime(new Date());
                record.setRegistertime(new Date());
                record.setRemark("注册得积分");
                integralService.updateMemberIntegral(member, record);
            }


            integralSet = integralService.getIntegralSetByType(Quantity.STATE_2);
            //查询是谁邀请的，送积分
            if (StringUtils.hasText(member.getInvitecode()) && integralSet != null && customInvitecode != null) {
                Member invMember = getByInvitecode(customInvitecode);
                if (invMember != null) {
                    Member updateMember = new Member();
                    updateMember.setId(invMember.getId());
                    updateMember.setIntegrals(invMember.getIntegrals().add(integralSet.getScope()));

                    IntegralRecord record = new IntegralRecord();
                    record.setMemberid(invMember.getId());
                    record.setMembername(invMember.getUsername());
                    record.setRemark("邀请得积分");
                    record.setScope(integralSet.getScope());
                    record.setType(Quantity.STATE_2);
                    record.setCreatetime(new Date());
                    record.setRegisterid(member.getId());
                    record.setRegistername(member.getUsername());
                    record.setRegistertime(member.getCreatedate());
                    record.setRegisterscope(member.getIntegrals());

                    integralService.updateMemberIntegral(updateMember, record);
                }
            }

        }

        return errorMes;
    }



    public Member getMemberByUsername(String username) {
//        MemberExample example = new MemberExample();
//        example.createCriteria().andUsernameEqualTo(username);
//
//        List<Member> list = memberMapper.selectByExample(example);
//        if (list != null && list.size() == 1) {
//            return list.get(0);
//        }
//
//        return null;
        return  memberMapper.getMemberByUsername(username);

    }


    public Member getMemberById(Long id) {
        return memberMapper.selectByPrimaryKey(id);
    }


    /**
     * 更新用户上次登录时间
     *
     * @param member_id
     */
    public void updateMemberLoginDate(long member_id) {
        Member member = new Member();
        member.setId(member_id);
        member.setLastlogindate(new Date());
        memberMapper.updateByPrimaryKeySelective(member);
    }

    /*
    会员停用
     */
    public void updateMemberdisabled(long id, boolean disabled) {
        Member member = new Member();
        member.setId(id);
        member.setDisabled(disabled);
        memberMapper.updateByPrimaryKeySelective(member);
    }

    /*
    修改登陆密码
     */
    public void updateMemberPassword(long id, String password, String passwordsalt) {
        Member member = new Member();
        member.setId(id);
        member.setPassword(CommonUtils.genMd5Password(password, passwordsalt));
        memberMapper.updateByPrimaryKeySelective(member);
    }

    /**
     * 修改支付密码
     *
     * @param id
     * @param paypassword
     */
    public void updateMemberPaypassword(long id, String paypassword, String paypasswordsalt) {
        Member member = new Member();
        member.setId(id);
        member.setPaypasswordsalt(paypasswordsalt);
        member.setPaypassword(CommonUtils.genMd5Password(paypassword, paypasswordsalt));
        memberMapper.updateByPrimaryKeySelective(member);
    }

    /**
     * 清除支付密码
     *
     * @param id
     * @param paypassword
     */
    public void cleanPaypassword(long id, String paypassword) {
        Member member = new Member();
        member.setId(id);
        member.setPaypassword("");
        memberMapper.updateByPrimaryKeySelective(member);
    }

    /**
     * 修改手机号码
     *
     * @param id
     * @param mobile
     */
    public void updateMemberMobile(long id, String mobile) {
        Member member = new Member();
        member.setId(id);
        member.setMobile(mobile);
        memberMapper.updateByPrimaryKeySelective(member);
    }

    public void updateMember(Member member) {
        memberMapper.updateByPrimaryKeySelective(member);
    }


    /**
     * 根据条件查询个数
     *
     * @param example
     * @return
     */
    public int countByExample(MemberExample example) {
        return memberMapper.countByExample(example);
    }


    private void checkRegisterMember(ErrorMes mes, Member member) {
        if (!StringUtils.hasText(member.getUsername())) {
            mes.addError("username", "用户名不可为空");
        }

        if (!StringUtils.hasText(member.getPassword())) {
            mes.addError("password", "密码不可为空");
        }

        if (!StringUtils.hasText(member.getMobile())) {
            mes.addError("mobile", "手机号不可为空");
        }
    }

    /**
     * 根据lableid查询
     *
     * @param lableid
     */
    public int selectCountByLableId(String lableid) {
        MemberExample example = new MemberExample();
        example.createCriteria().andLabelidLike(lableid);
        int count = memberMapper.countByExample(example);
        return count;
    }


    public Member getSubMember(Long parintid, String subUsername) {
        return memberMapper.getSubMember(parintid, subUsername);
    }


    public void add(Member member) {
        memberMapper.insert(member);
    }


    public void insertSelective(Member member) {
        memberMapper.insertSelective(member);
    }


    /**
     * 查询是否存在子帐号
     */
    public boolean queryExisSubAccount(String parentUsername, String subUsername) {
        int c = memberMapper.queryExisSubAccount(parentUsername, subUsername);
        if (c > 0) {
            return true;
        }

        return false;
    }


    /**
     * 获取卖家资金
     *
     * @param id
     * @return
     */
    public Map<String, BigDecimal> getSellerMemberBalance(long id) {
        return memberMapper.getSellerMemberBalance(id);
    }

    /**
     * 更改卖家帐号余额
     *
     * @param id
     * @param sellerBalance
     * @param sellerfreezebanlance
     */
//    public void updateSellerMemberBalance(long id, BigDecimal sellerBalance, BigDecimal sellerfreezebanlance) {
//        memberMapper.updateSellerMemberBalance(id, sellerBalance, sellerfreezebanlance);
//    }


    /**
     * 数据库直接加减
     *
     * @param id
     * @param sellerBalance
     * @param sellerfreezebanlance
     */
    public void updateSellerMemberBalanceInDb(long id, BigDecimal sellerBalance, BigDecimal sellerfreezebanlance) {
        memberMapper.updateSellerMemberBalanceInDb(id, sellerBalance, sellerfreezebanlance);
    }


    public void  updateIntegralInDb(long id,BigDecimal integrals,BigDecimal availableintegral){

    }


    /**
     * 更新卖家资金
     * @param id
     * @param sellerbanlance  卖家余额
     * @param sellerfreezebanlance  卖家冻结金额
     * @param goodsbanlance  货款金额
     * @param billmoney  开票金额
     * @return
     */
    public void updateSellerMemberBalanceInDb(long id,BigDecimal sellerbanlance,BigDecimal sellerfreezebanlance,
                                      BigDecimal goodsbanlance,BigDecimal billmoney){
        memberMapper.updateSellerMemberBalanceInDb2(id,sellerbanlance,sellerfreezebanlance,goodsbanlance,billmoney);
    }


    public void updateSellerMemberGoodsBalanceInDb(long id, BigDecimal goodsbalance) {
        memberMapper.updateSellerMemberGoodsBalanceInDb(id, goodsbalance);
    }


    /**
     * 更改买家帐号余额
     *
     * @param id
     * @param balance
     */
//    public void updateBuyerMemberBalance(long id, BigDecimal balance) {
//        memberMapper.updateBuyerMemberBalance(id, balance);
//    }


    public int updateBuyerMemberBalanceInDb(long id,BigDecimal balance){
        return  memberMapper.updateBuyerMemberBalanceInDb(id,balance);
    }

    /**
     * 修改授信资金
     * @param id
     * @param usedlimit
     * @param avalablelimit
     */
    public int updateBuyerMemberCreditBalanceInDb(long id,BigDecimal usedlimit,BigDecimal avalablelimit){
     return    memberMapper.updateBuyerMemberCreditBalanceInDb(id,usedlimit,avalablelimit);
    }




    /**
     * 授信还款
     * @param id
     * @param balance
     * @return
     */
    public void creditRepayment(long id, BigDecimal balance) {
        memberMapper.creditRepayment(id, balance);
    }


    /**
     * 更改买家基本信息
     */
    public void updatebuyerinfoByid(long id, String realname, String sex,String mobile,String oldMobile,
                                    String email, String faxes, String telephone,
                                    String address, String postcode, String invitecode, String wxpay, String qq,
                                    String hobby, String favicon, String alipay, String province, String city, String citysmall) {
        MemberExample memberExample = new MemberExample();
        Member info = new Member();
        info.setId(id);
        info.setRealname(realname);
        info.setSex(sex);
        if(oldMobile == null || oldMobile.isEmpty()){
            info.setMobile(mobile);
        }
        info.setEmail(email);
        info.setFaxes(faxes);
        info.setFavicon(telephone);
        info.setTelephone(telephone);
        info.setAddress(address);
        info.setPostcode(postcode);
        info.setInvitecode(invitecode);
        info.setWxpay(wxpay);
        info.setQq(qq);
        info.setHobby(hobby);
        info.setFavicon(favicon);
        info.setAlipay(alipay);
        info.setProvince(province);
        info.setCity(city);
        info.setCitysmall(citysmall);

        memberMapper.updateByPrimaryKeySelective(info);
    }


    public int updateByPrimaryKeySelective(Member member) {
        return memberMapper.updateByPrimaryKeySelective(member);
    }


    public void fillMember(Member member) {

        if (member.getGradleid() != null) {
            MemberGrade memberGrade = memberGradeService.getById(member.getGradleid().intValue());
            if (memberGrade != null) {
                member.setGradename(memberGrade.getGradename());
            }
        }

        SellerCompanyInfo companyInfo = sellerCompanyInfoService.getSellerCompanyByMemberid(member.getId());
        if (companyInfo != null && companyInfo.getShopgradeid() > 0) {
            ShopGrade shopGrade = shopGradeService.selectByPrimaryKey(companyInfo.getShopgradeid());
            if (shopGrade != null) {
                companyInfo.setShopgrade(shopGrade.getGradename());
            }


            Long[] categoryidArr = (Long[]) companyInfo.getBusinesscategory();
            if(categoryidArr != null){
                //提前预加载可发布的商品分类
                AdvanceSellerPublish t1 =  new AdvanceSellerPublish(sellerCompanyCacheService,member.getId());
                Thread thread = new Thread(t1);
                thread.start();
            }
        }


        BuyerCompanyInfo buyerCompanyInfo = buyerCompanyService.getBuyerCompanyInfoByMemberId(member.getId());
        member.setSellerCompanyInfo(companyInfo);
        member.setBuyerCompanyInfo(buyerCompanyInfo);
    }








    @Autowired
    private SellerCompanyCacheService sellerCompanyCacheService;



    public void fillMemberOther(Member old, Member newMe) {
        newMe.setLoginType(old.getLoginType());
        newMe.setFrom(old.getFrom());
    }


    public void delById(long id) {
        memberMapper.deleteByPrimaryKey(id);
    }

    public List<Member> selectByExample(MemberExample example) {
        return memberMapper.selectByExample(example);
    }


    /**
     * 根据条件一次进行模糊查询
     * @param username 用户名称
     * @param companyname 用户所属的公司名称
     * @param realname 用户姓名
     * @param mobile 用户手机号码
     * @param invoiceheadup 发票抬头
     * @return
     */
    public List<Map<String,Object>> findMembersByFuzzy(String username, String companyname, String realname, String mobile, String invoiceheadup){
        return memberMapper.findMembersByFuzzy(username,companyname,realname,mobile,invoiceheadup);
    }


    /**
     * 子帐号列表
     *
     * @param member
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageInfo listSubAccount(Member member, int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);

        List<Member> list = memberMapper.listSubAccount(member.getId());

        return new PageInfo(list);
    }


    public Member getByInvitecode(String invitecode) {
        MemberExample example = new MemberExample();
        MemberExample.Criteria criteria = example.createCriteria();
        criteria.andInvitecodeEqualTo(invitecode);

        List<Member> list = memberMapper.selectByExample(example);
        if (list.size() == 1) {
            return list.get(0);
        }

        return null;
    }


    public List<MemberStatistcModel> getMemberRegisterStatistic(MemberQueryParam param) {
        List<MemberStatistcModel> list = memberMapper.getMemberRegisterStatistic(param);
        return list;
    }

    public Member updateMemberFavicon(String favicon, Long memberId) {
        Member member = memberMapper.selectByPrimaryKey(memberId);
        member.setFavicon(favicon);
        memberMapper.updateByPrimaryKeySelective(member);
        return member;
    }


    /**
     * 注册购买率
     * @param startDate
     * @param endDate
     * @return
     */
    public  List<Map<String,Object>> getRegisterRate(Date startDate,Date endDate){
        return  memberMapper.getRegisterRate(startDate,endDate);
    }

    /**
     * 根据当前登陆的用户 查询自己邀请人
     * 邀请人就是真实姓名
     *
     * @param pageNo
     * @param pageSize
     * @param inviterid
     * @return
     */
    public PageInfo findInviteList(int pageNo, int pageSize, long inviterid) {
        if (pageNo != -1) {
            PageHelper.startPage(pageNo, pageSize);
        }
        List<Map<String, Object>> list = memberMapper.findInviteList(inviterid);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    public long countAllInvite(long inviterid) {
        MemberExample memberExample = new MemberExample();
        MemberExample.Criteria criteria = memberExample.createCriteria();
        criteria.andInviteridEqualTo(inviterid);

        return memberMapper.countByExample(memberExample);
    }

    /**
     * 显示会员列表 真实姓名和用户名
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageInfo findAllMemberList(int pageNo, int pageSize) {
        if (pageNo != -1) {
            PageHelper.startPage(pageNo, pageSize);
        }
        List<Map<String, Object>> list = memberMapper.findAllMemberList();
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }


    public Member getInviterIdByMemberId(Long memberid) {
        return  memberMapper.selectByPrimaryKey(memberid);
    }

    public List<Member> getAllMember(){
        return  appTaskMapper.getAllMember();
    }


    public int updateMemberClerknameByid(Long id, String clerkname) {
        return memberMapper.updateMemberClerknameByid(  id, clerkname);
    }



    public Member getMemberByid(Long id) {
        return memberMapper.getMemberByid(  id);
    }

    /**
     * 三级分销邀请人列表
     * @param memberDto
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageInfo selectInverterList(MemberDto memberDto, int pageNo, int pageSize) {
        if (pageNo != -1) {
            PageHelper.startPage(pageNo, pageSize);
        }
        JsonArray jsonarray = new JsonArray();


        int flag = 0;//是否是公司 0-全部，1-是，2-否
        if(memberDto.getCompanyType()>0){

            if(memberDto.getCompanyType() == Quantity.STATE_1){
                flag = 1;
            }else if(memberDto.getCompanyType() == Quantity.STATE_2){
                flag = 2;
            }


        }
        List<MemberDto> list1 =  memberMapper.selectInverterList(memberDto,flag);
        for (MemberDto memberDto1:list1) {
            JsonObject json =new JsonObject();

            JSONObject json1 = JSONObject.fromObject(memberDto1);
            String str = json1.toString();//将json对象转换为字符串
            json.addProperty("memberDto1",str);
                //统计该用户的一级和二级邀请人数
                //一级邀请人数
                //先查询自己下面的一级的id
                List<Member> firstList = memberMapper.selectFirstInviters(memberDto1.getId());
                memberDto1.setFirstinviternum(new Long(firstList.size()));
                //二级邀请人数
            if(firstList!=null&&firstList.size()>0) {
                for (Member member : firstList) {
                    //查询出二级
                    List<Member> secondList = memberMapper.selectSecondInviters(memberDto1.getId());
                    if (secondList != null && secondList.size() > 0) {
                        memberDto1.setSecondinviternum(new Long(secondList.size()));
                    } else {
                        memberDto1.setSecondinviternum(0l);
                    }
                }
            }else{
                memberDto1.setSecondinviternum(0l);
            }
            jsonarray.add(json);
        }
        PageInfo pageInfo = new PageInfo(list1);
        return pageInfo;
    }


    /**
     * 三级分销邀请人列表forexcel导出
     * @param memberDto
     * @return
     */
    public List<Map<String,Object>> selectInverterList1(MemberDto memberDto) {
        //存放结果
        List<Map<String,Object>> resList = new ArrayList<>();

        JsonArray jsonarray = new JsonArray();

        // List<Map<String,Object>> list1 = memberMapper.selectInverterList(memberDto);
        int flag = 0;//是否是公司 0-全部，1-是，2-否
        if(memberDto.getCompanyType()>0){
            if(memberDto.getCompanyType() == Quantity.STATE_1){
                flag = 1;
            }else if(memberDto.getCompanyType() == Quantity.STATE_2){
                flag = 2;
            }


        }
        List<MemberDto> list1 = memberMapper.selectInverterList(memberDto,flag);

        for (MemberDto memberDto1:list1) {
            JsonObject json =new JsonObject();

            JSONObject json1 = JSONObject.fromObject(memberDto1);
            String str = json1.toString();//将json对象转换为字符串
            json.addProperty("memberDto1",str);
            //统计该用户的一级和二级邀请人数
            //一级邀请人数
            //先查询自己下面的一级的id
            List<Member> firstList = memberMapper.selectFirstInviters(memberDto1.getId());
            memberDto1.setFirstinviternum(new Long(firstList.size()));
            //二级邀请人数
            if(firstList!=null&&firstList.size()>0) {
                for (Member member : firstList) {
                    //查询出二级
                    List<Member> secondList = memberMapper.selectSecondInviters(memberDto1.getId());
                    System.out.println("secondList" + secondList.size());
                    if (secondList != null && secondList.size() > 0) {
                        memberDto1.setSecondinviternum(new Long(secondList.size()));
                    } else {
                        memberDto1.setSecondinviternum(0l);
                    }
                }
            }else{
                memberDto1.setSecondinviternum(0l);
            }
            jsonarray.add(json);
        }

        //将List<T>转换为List<Map<String, Object>>
        List<Map<String, Object>> list2 = BeanUtils.objectsToMaps(list1);
        for(Map<String,Object> beanMap: list2){
            Map<String,Object> resMap = new HashMap<>();
            resMap.put("邀请码",beanMap.get("invitecode"));
            resMap.put("会员ID",beanMap.get("id"));
            resMap.put("真实姓名",beanMap.get("realname"));
            resMap.put("会员全称",beanMap.get("username"));
            resMap.put("会员等级",beanMap.get("gradename"));
            resMap.put("一级邀请人数量",beanMap.get("firstinviternum"));
            resMap.put("二级邀请人数量",beanMap.get("secondinviternum"));
            resList.add(resMap);
        }
        return  resList;
    }

    public PageInfo getMemberByInviterid(long inviterid,long type,int pageNo,int pageSize) {
        if (pageNo != -1) {
            PageHelper.startPage(pageNo, pageSize);
        }
        List<Map<String, Object>> list = null;
        if(type == 1) {
             list = memberMapper.getMemberByInviterid(inviterid,type);
        }else if(type == 2){
            list = memberMapper.getMemberByInviterid(inviterid,type);
        }

        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }


    public void updateMemberInviterid2ById(Long id, Long inviterid2) {
        memberMapper.updateMemberInviterid2ById(id,inviterid2);
    }

    /**
     * 导出一级邀请人或者二级邀请人 for excel
     * @param inviterid
     * @param type
     * @return
     */
    public List<Map<String,Object>> getMemberByInviterid1(long inviterid, long type) {
        //存放结果
        List<Map<String,Object>> resList = new ArrayList<>();


        List<Map<String, Object>> list = null;
        if(type == 1) {
            list = memberMapper.getMemberByInviterid(inviterid,type);
        }else if(type == 2){
            list = memberMapper.getMemberByInviterid(inviterid,type);
        }

        for(Map<String,Object> beanMap : list){
            Map<String,Object> resMap = new HashMap<>();
            resMap.put("会员名",beanMap.get("username"));
            resMap.put("真实姓名",beanMap.get("realname"));
            resMap.put("手机号",beanMap.get("mobile"));
            resMap.put("会员等级",beanMap.get("gradename"));
            resMap.put("邀请时间", DateUtils.format((Date) beanMap.get("createdate"),"yyyy/MM/dd HH:mm:ss"));
            resList.add(resMap);
        }


        return  resList;
    }

    public Member selectById(@Param("memberid") long memberid) {
       return memberMapper.selectByPrimaryKey(memberid);
    }

    public List<Map<String,Object>> getMemberInfoByUserName(String username,String clerkname,Long id,String companyname) {
        List<Map<String,Object>> list =  memberMapper.getMemberInfoByUserName(username,clerkname,id,companyname);
        return list;
    }


    public List<Member> getMemberByInvitecodes(List invitecodeList) {
      return  memberMapper.getMemberByInvitecodes(invitecodeList);
    }

    public List<Member> getMemmberByIdsList(long[] idsList) {
        return  memberMapper.getMemmberByIdsList(idsList);
    }

    public List<Member> selectMemberByInviterid(Long inviterid) {
        return memberMapper.selectMemberByInviterid(inviterid);
    }


    public Member selectMemberByMobile(String mobile) {
      return   memberMapper.selectMemberByMobile(mobile);
    }

    public void updateMemberLabelnameAndWaysalesman(String labelname,String realname,Long memberid) {
          memberMapper.updateMemberLabelnameAndWaysalesman(labelname,realname,memberid);
    }

    public void updateMemberByIds(StringBuffer ids){
        memberMapper.updateMemberByIds(ids);
    }

    public Member getMemberByUsernameOrId(String membername,long id) {
        return  memberMapper.getMemberByUsernameOrId(membername,id);
    }
}
