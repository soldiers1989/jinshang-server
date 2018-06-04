package project.jinshang.mod_member;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_admin.mod_count.bean.MemberQueryParam;
import project.jinshang.mod_admin.mod_count.bean.MemberStatistcModel;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_member.bean.MemberExample;
import project.jinshang.mod_member.bean.dto.MemberAdminQueryDto;
import project.jinshang.mod_member.bean.dto.MemberAdminViewDto;
import project.jinshang.mod_member.provider.MemberProvider;
import project.jinshang.mod_server.bean.ServerPageModel;
import project.jinshang.mod_server.bean.ServerQueryParam;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface MemberMapper {

    int countByExample(MemberExample example);

    @CacheEvict(value = {"jinshang-member"},allEntries = true)
    int deleteByPrimaryKey(Long id);


    int insert(Member record);

    int insertSelective(Member record);

    List<Member> selectByExample(MemberExample example);

    @Cacheable(value = "jinshang-member",key = "'jinshang-member-selectByPrimaryKey-id:'+#p0")
    Member selectByPrimaryKey(Long id);

    @CacheEvict(value = {"jinshang-member"},allEntries = true)
    int updateByExampleSelective(@Param("record") Member record, @Param("example") MemberExample example);

    @CacheEvict(value = {"jinshang-member"},allEntries = true)
    int updateByExample(@Param("record") Member record, @Param("example") MemberExample example);

    @CacheEvict(value = {"jinshang-member"},key = "'jinshang-member-selectByPrimaryKey-id:'+#p0.id")
    int updateByPrimaryKeySelective(Member record);

    @CacheEvict(value = {"jinshang-member"},key ="'jinshang-member-selectByPrimaryKey-id:'+#p0.id")
    int updateByPrimaryKey(Member record);


    @Select("select * from member where parentid=#{parentid} and userName=#{subUsername} and flag=false limit 1")
    Member getSubMember(@Param("parentid") Long parentid,@Param("subUsername") String subUsername);


    @Select("select count(*) from member where parentname=#{parentUsername} and username=#{subUsername}")
    int queryExisSubAccount(@Param("parentUsername") String parentUsername,@Param("subUsername") String subUsername);


    @Select("select sellerbanlance,sellerfreezebanlance from member where id=#{id}")
    Map<String,BigDecimal> getSellerMemberBalance(long id);

    @Select("select * from member where username=#{username} limit 1 ")
    Member getMemberByUsername(String username);

//    @CacheEvict(value = {"jinshang-member"},key ="'jinshang-member-selectByPrimaryKey-id:'+#p0")
//    @Update("update member set sellerbanlance=#{sellerBalance},sellerfreezebanlance=#{sellerfreezebanlance} where id=#{id}")
//    int updateSellerMemberBalance(@Param("id") long id,@Param("sellerBalance") BigDecimal sellerBalance,@Param("sellerfreezebanlance") BigDecimal sellerfreezebanlance);


    @CacheEvict(value = {"jinshang-member"},key ="'jinshang-member-selectByPrimaryKey-id:'+#p0")
    @Update("update member set sellerbanlance=sellerbanlance + #{sellerBalance},sellerfreezebanlance=sellerfreezebanlance + #{sellerfreezebanlance} where id=#{id}")
    int updateSellerMemberBalanceInDb(@Param("id") long id,@Param("sellerBalance") BigDecimal sellerBalance,@Param("sellerfreezebanlance") BigDecimal sellerfreezebanlance);


    @CacheEvict(value = {"jinshang-member"},key ="'jinshang-member-selectByPrimaryKey-id:'+#p0")
    @Update("update member set goodsbanlance= goodsbanlance + #{goodsbalance} where id=#{id}")
    int  updateSellerMemberGoodsBalanceInDb(@Param("id") long id,@Param("goodsbalance") BigDecimal goodsbalance);


    @CacheEvict(value = {"jinshang-member"},key ="'jinshang-member-selectByPrimaryKey-id:'+#p0")
    @Update("update member set integrals= integrals + #{integrals},availableintegral=availableintegral + #{availableintegral} where id=#{id}")
    int updateIntegralInDb(@Param("id") long id,@Param("integrals") BigDecimal integrals, @Param("availableintegral") BigDecimal availableintegral);


//    @CacheEvict(value = {"jinshang-member"},key ="'jinshang-member-selectByPrimaryKey-id:'+#p0")
//    @Update("update member set balance=#{balance} where id=#{id}")
//    int updateBuyerMemberBalance(@Param("id") long id,@Param("balance") BigDecimal balance);


    @CacheEvict(value = {"jinshang-member"},key ="'jinshang-member-selectByPrimaryKey-id:'+#p0")
    @Update("update member set balance=balance + #{balance} where id=#{id}")
    int updateBuyerMemberBalanceInDb(@Param("id") long id,@Param("balance") BigDecimal balance);


    @CacheEvict(value = {"jinshang-member"},key ="'jinshang-member-selectByPrimaryKey-id:'+#p0")
    @Update("update member set usedlimit=usedlimit + #{usedlimit},availablelimit=availablelimit + #{availablelimit} where id=#{id}")
    int updateBuyerMemberCreditBalanceInDb(@Param("id") long id,@Param("usedlimit") BigDecimal usedlimit,@Param("availablelimit") BigDecimal availablelimit);


    /**
     * 更新卖家资金
     * @param id
     * @param sellerbanlance  卖家余额
     * @param sellerfreezebanlance  卖家冻结金额
     * @param goodsbanlance  货款金额
     * @param billmoney  开票金额
     * @return
     */
    @CacheEvict(value = {"jinshang-member"},key ="'jinshang-member-selectByPrimaryKey-id:'+#p0")
    @Update("update member set sellerbanlance=sellerbanlance + #{sellerbanlance},sellerfreezebanlance=sellerfreezebanlance + #{sellerfreezebanlance}," +
            "goodsbanlance=goodsbanlance + #{goodsbanlance},billmoney=billmoney + #{billmoney} where id=#{id}")
    int updateSellerMemberBalanceInDb2(@Param("id") long id,@Param("sellerbanlance") BigDecimal sellerbanlance,@Param("sellerfreezebanlance") BigDecimal sellerfreezebanlance,
                                      @Param("goodsbanlance") BigDecimal goodsbanlance,@Param("billmoney") BigDecimal billmoney);


    @SelectProvider(type = MemberProvider.class,method = "adminList")
    List<MemberAdminViewDto> adminListMemberInfo(@Param("dto")MemberAdminQueryDto dto);


    /**
     * 后台导出会员Excel
     * @param dto
     * @return
     */
    @SelectProvider(type = MemberProvider.class,method = "adminListForExcelExport")
    List<Map<String,Object>> adminListMemberInfoForExcelExport(@Param("dto")MemberAdminQueryDto dto);




    /**
     * 授信还款
     * @param id
     * @param balance
     * @return
     */
    @CacheEvict(value = {"jinshang-member"},key ="'jinshang-member-selectByPrimaryKey-id:'+#p0")
    @Update("update member set balance=balance-#{balance},usedlimit=usedlimit-#{balance},availablelimit=availablelimit+#{balance} where id=#{id}")
    int creditRepayment(@Param("id") long id,@Param("balance") BigDecimal balance);


    @Select("select m.*,g.groupname from member m left join sellergroup g on m.sellergroupid=g.id where m.parentid=#{parentid} ")
    List<Member> listSubAccount(@Param("parentid") long parentid);




    /**
     * 获取用户注册统计
     * @param param
     * @return
     */
    @SelectProvider(type = MemberMapper.StatisProvider.class, method = "queryMemberRegisterByParam")
    List<MemberStatistcModel> getMemberRegisterStatistic(MemberQueryParam param);




    public class StatisProvider {

        private final String TBL_MEMBER = "MEMBER member";

        public String queryMemberRegisterByParam(MemberQueryParam param) {
            SQL sql = new SQL().SELECT("to_char(member.createdate, 'yyyy-mm-dd') as createtime ,count(*) as registernum").FROM(TBL_MEMBER);

            sql.WHERE("member.flag=true");
            //开始时间
            Date startTime = param.getStartTime();
            if(startTime!=null){
                sql.WHERE("member.createdate >=#{startTime}");
            }
            //结束时间
            Date endTime = param.getEndTime();
            if(endTime!=null){
                Calendar c = Calendar.getInstance();
                c.setTime(endTime);
                c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
                Date tomorrow = c.getTime();
                param.setEndTime(tomorrow);
                sql.WHERE("member.createdate <=#{endTime}");
            }
            sql.GROUP_BY("to_char(member.createdate, 'yyyy-mm-dd')");
            sql.ORDER_BY("to_char(member.createdate, 'yyyy-mm-dd')");
            return sql.toString();
        }
    }


    @SelectProvider(type = MemberMapper.MemberServerProvider.class,method = "queryMemberServer")
    List<ServerPageModel> getMemberServerList(ServerQueryParam param);

    public class MemberServerProvider{

        private final String TBL_MEMBER="MEMBER meb";
        private final String TBL_BUYER_COMPANY_INFO = "buyercompanyinfo bcinfo ON meb.id = bcinfo.memberid";
        private final String TBL_MEMBER_GRADE = "membergrade gd ON gd.id = meb.gradleid";

        public String queryMemberServer(ServerQueryParam param){
            SQL sql = new SQL().SELECT("meb.id,meb.realname,case when bcinfo.companyname is null then meb.realname ELSE bcinfo.companyname END as companyname,gd.gradename,meb.company,meb.mobile,meb.email,meb.createdate,meb.disabled").FROM(TBL_MEMBER);
            sql.LEFT_OUTER_JOIN(TBL_BUYER_COMPANY_INFO);
            sql.LEFT_OUTER_JOIN(TBL_MEMBER_GRADE);

            sql.WHERE("meb.services=true");

            if(param.getId()!=null){
                sql.WHERE(" meb.id=#{id}");
            }
            if(StringUtils.hasText(param.getCompanyname())){
                String companyname = "%"+param.getCompanyname()+"%";
                param.setCompanyname(companyname);
                sql.WHERE("case when bcinfo.companyname is null then meb.realname like #{companyname} ELSE bcinfo.companyname like #{companyname} end");
            }

            if(StringUtils.hasText(param.getMobile())){
                String mobile = "%"+param.getMobile()+"%";
                param.setMobile(mobile);
                sql.WHERE("meb.mobile like #{mobile}");
            }

            sql.ORDER_BY("meb.id desc");

            return sql.toString();
        }
    }

    @Select("SELECT " +
            "meb.id,meb.realname,bcinfo.companyname,gd.gradename,meb.mobile,meb.email " +
            "FROM " +
            "MEMBER meb " +
            "LEFT JOIN buyercompanyinfo bcinfo ON meb.id = bcinfo.memberid " +
            "LEFT JOIN membergrade gd ON gd.id = meb.gradleid " +
            "where meb.id=#{id}")
    ServerPageModel getSingleMemberById(@Param("id") Long id);


    /**
     * 注册购买率
     * @param startDate
     * @param endDate
     * @return
     */
    @Select("select t1.*,(select count(id) from member  where isbuy=2 and\n" +
            " to_char(createdate,'YYYY-MM-DD')=t1.registerday ) as buycount from (\n" +
            "select to_char(m1.createdate,'YYYY-MM-DD') as registerday,count(to_char(createdate,'YYYY-MM-DD')) as registercount\n" +
            "from \"member\" m1 where flag=TRUE and " +
            "createdate>=#{startDate} and createdate<=#{endDate} group by to_char(createdate,'YYYY-MM-DD') \n" +
            ") as t1 ORDER BY registerday asc")
    List<Map<String,Object>> getRegisterRate(@Param("startDate") Date startDate,@Param("endDate") Date endDate);



    @Select("<script>select m.username,m.createdate " +
            "from member m " +
            "<where> 1=1 " +
            "<if test=\"inviterid != null \">and m.inviterid = #{inviterid} </if>" +
            "</where> order by m.id desc" +
            "</script>")
    List<Map<String,Object>> findInviteList(@Param("inviterid") long inviterid);

    /**
     * 显示会员列表 真实姓名和用户名
     * @return
     */
    @Select("<script>select m.id,m.realname,m.username " +
            "from member m " +
            "<where> 1=1 " +
            "</where> order by m.id desc" +
            "</script>")
    List<Map<String,Object>> findAllMemberList();


    @Select("<script>SELECT m.id,m.username,m.realname,b.companyname,m.mobile,m.clerkname  FROM  member m left join buyercompanyinfo b on m.id=b.memberid where 1=1 " +
            "<if test='id != null and id != \"\" '>AND m.id=#{id}</if>"+
            "<if test='companyname != null and companyname != \"\"'>AND b.companyname LIKE CONCAT('%',#{companyname,jdbcType=VARCHAR},'%')</if>"+
            "<if test='realname != null and realname != \"\"'>AND m.realname LIKE CONCAT('%',#{realname,jdbcType=VARCHAR},'%')</if>"+
            "<if test='mobile != null and mobile != \"\"'>AND m.mobile LIKE CONCAT('%',#{mobile,jdbcType=VARCHAR},'%')</if>"+
            "</script>")
    List<MemberAdminViewDto> findNotAddMembers(@Param("id") Long id,@Param("companyname") String companyname,@Param("realname") String realname,@Param("mobile") String mobile);


    @Select("<script>SELECT m.id,m.username,m.realname,b.companyname,m.mobile FROM  member m left join buyercompanyinfo b on m.id=b.memberid where 1=1 " +
            "<if test='id != null and id != \"\" '>AND m.id=#{id}</if>"+
            "<if test='companyname != null and companyname != \"\"'>AND b.companyname LIKE CONCAT('%',#{companyname,jdbcType=VARCHAR},'%')</if>"+
            "<if test='realname != null and realname != \"\"'>AND m.realname LIKE CONCAT('%',#{realname,jdbcType=VARCHAR},'%')</if>"+
            "<if test='mobile != null and mobile != \"\"'>AND m.mobile LIKE CONCAT('%',#{mobile,jdbcType=VARCHAR},'%')</if>"+
            "and m.id IN(SELECT userid FROM adminuser where  adminid=#{adminid})"+
            "</script>")
    List<MemberAdminViewDto> findManageMemberList(@Param("id") Long id,@Param("companyname") String companyname,@Param("realname") String realname,@Param("mobile") String mobile,@Param("adminid") Long adminid);


    @Select("<script>SELECT DISTINCT BG.invoiceheadup,m.id,m.username,m.realname,b.companyname,m.mobile  FROM  member m left join buyercompanyinfo b on m.id=b.memberid LEFT JOIN billingrecord BG ON m.id=BG.memberid where 1=1 " +
            "<if test='username != null and username != \"\" '>AND m.username LIKE CONCAT('%',#{username,jdbcType=VARCHAR},'%')</if>"+
            "<if test='companyname != null and companyname != \"\"'>AND b.companyname LIKE CONCAT('%',#{companyname,jdbcType=VARCHAR},'%')</if>"+
            "<if test='realname != null and realname != \"\"'>AND m.realname LIKE CONCAT('%',#{realname,jdbcType=VARCHAR},'%')</if>"+
            "<if test='mobile != null and mobile != \"\"'>AND m.mobile LIKE CONCAT('%',#{mobile,jdbcType=VARCHAR},'%')</if>"+
            "<if test='invoiceheadup !=null and invoiceheadup != \"\"'>AND BG.invoiceheadup LIKE CONCAT('%',#{invoiceheadup,jdbcType=VARCHAR},'%')</if>"+
            "</script>")
    List<Map<String,Object>> findMembersByFuzzy(@Param("username") String username,@Param("companyname") String companyname,@Param("realname") String realname,@Param("mobile") String mobile,@Param("invoiceheadup") String invoiceheadup);

    @Update("update member set clerkname=#{clerkname}where id=#{id}")
    int  updateMemberClerknameByid(@Param("id") Long id,@Param("clerkname") String clerkname);




}