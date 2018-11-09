package project.jinshang.mod_member;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.cache.annotation.CacheEvict;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_admin.mod_count.bean.MemberQueryParam;
import project.jinshang.mod_admin.mod_count.bean.MemberStatistcModel;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_member.bean.MemberExample;
import project.jinshang.mod_member.bean.dto.MemberAdminQueryDto;
import project.jinshang.mod_member.bean.dto.MemberAdminViewDto;
import project.jinshang.mod_member.bean.dto.MemberDto;
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

    //    @Cacheable(value = "jinshang-member",key = "'jinshang-member-selectByPrimaryKey-id:'+#p0")
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
    Member getSubMember(@Param("parentid") Long parentid, @Param("subUsername") String subUsername);


    @Select("select count(*) from member where parentname=#{parentUsername} and username=#{subUsername}")
    int queryExisSubAccount(@Param("parentUsername") String parentUsername, @Param("subUsername") String subUsername);


    @Select("select sellerbanlance,sellerfreezebanlance from member where id=#{id}")
    Map<String,BigDecimal> getSellerMemberBalance(long id);

    @Select("select * from member where username=#{username} limit 1 ")
    Member getMemberByUsername(String username);

    @Select("<script>select m.* " +
            "from member m " +
            "<where> 1=1 " +
            "<if test=\"username != null and username !=''\">and m.username = #{username} </if>" +
            "<if test=\"id != null and id !=''\">and m.id = #{id} </if>" +
            "</where> order by m.id desc limit 1" +
            "</script>")
    Member getMemberByUsernameOrId(@Param("username") String username,@Param("id") long id);

//    @CacheEvict(value = {"jinshang-member"},key ="'jinshang-member-selectByPrimaryKey-id:'+#p0")
//    @Update("update member set sellerbanlance=#{sellerBalance},sellerfreezebanlance=#{sellerfreezebanlance} where id=#{id}")
//    int updateSellerMemberBalance(@Param("id") long id,@Param("sellerBalance") BigDecimal sellerBalance,@Param("sellerfreezebanlance") BigDecimal sellerfreezebanlance);


    @CacheEvict(value = {"jinshang-member"},key ="'jinshang-member-selectByPrimaryKey-id:'+#p0")
    @Update("update member set sellerbanlance=sellerbanlance + #{sellerBalance},sellerfreezebanlance=sellerfreezebanlance + #{sellerfreezebanlance} where id=#{id}")
    int updateSellerMemberBalanceInDb(@Param("id") long id, @Param("sellerBalance") BigDecimal sellerBalance, @Param("sellerfreezebanlance") BigDecimal sellerfreezebanlance);


    @CacheEvict(value = {"jinshang-member"},key ="'jinshang-member-selectByPrimaryKey-id:'+#p0")
    @Update("update member set goodsbanlance= goodsbanlance + #{goodsbalance} where id=#{id}")
    int  updateSellerMemberGoodsBalanceInDb(@Param("id") long id, @Param("goodsbalance") BigDecimal goodsbalance);


    @CacheEvict(value = {"jinshang-member"},key ="'jinshang-member-selectByPrimaryKey-id:'+#p0")
    @Update("update member set integrals= integrals + #{integrals},availableintegral=availableintegral + #{availableintegral} where id=#{id}")
    int updateIntegralInDb(@Param("id") long id, @Param("integrals") BigDecimal integrals, @Param("availableintegral") BigDecimal availableintegral);


//    @CacheEvict(value = {"jinshang-member"},key ="'jinshang-member-selectByPrimaryKey-id:'+#p0")
//    @Update("update member set balance=#{balance} where id=#{id}")
//    int updateBuyerMemberBalance(@Param("id") long id,@Param("balance") BigDecimal balance);


    @CacheEvict(value = {"jinshang-member"},key ="'jinshang-member-selectByPrimaryKey-id:'+#p0")
    @Update("update member set balance=balance + #{balance} where id=#{id}")
    int updateBuyerMemberBalanceInDb(@Param("id") long id, @Param("balance") BigDecimal balance);


    @CacheEvict(value = {"jinshang-member"},key ="'jinshang-member-selectByPrimaryKey-id:'+#p0")
    @Update("update member set usedlimit=usedlimit + #{usedlimit},availablelimit=availablelimit + #{availablelimit} where id=#{id}")
    int updateBuyerMemberCreditBalanceInDb(@Param("id") long id, @Param("usedlimit") BigDecimal usedlimit, @Param("availablelimit") BigDecimal availablelimit);


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
            "goodsbanlance=goodsbanlance + #{goodsbanlance},billmoney=billmoney + #{billmoney} where id=#{id} and sellerbanlance + #{sellerbanlance}>=0 and sellerfreezebanlance + #{sellerfreezebanlance}>=0 " +
            " and goodsbanlance + #{goodsbanlance}>=0 and billmoney + #{billmoney}>=0 ")
    int updateSellerMemberBalanceInDb2(@Param("id") long id, @Param("sellerbanlance") BigDecimal sellerbanlance, @Param("sellerfreezebanlance") BigDecimal sellerfreezebanlance,
                                       @Param("goodsbanlance") BigDecimal goodsbanlance, @Param("billmoney") BigDecimal billmoney);


    @SelectProvider(type = MemberProvider.class,method = "adminList")
    List<MemberAdminViewDto> adminListMemberInfo(@Param("adminrealname")String adminrealname,@Param("dto") MemberAdminQueryDto dto);


    /**
     * 后台导出会员Excel
     * @param dto
     * @return
     */
    @SelectProvider(type = MemberProvider.class,method = "adminListForExcelExport")
    List<Map<String,Object>> adminListMemberInfoForExcelExport(@Param("adminrealname")String adminrealname,@Param("dto") MemberAdminQueryDto dto);

    /**
     * 后台导出会员Excel 统计会员总数
     * @param dto
     * @return
     */
    /*@SelectProvider(type = MemberProvider.class,method = "getAdminCount")
    long getAdminCount(@Param("dto")MemberAdminQueryDto dto);*/


    /**
     * 授信还款
     * @param id
     * @param balance
     * @return
     */
    @CacheEvict(value = {"jinshang-member"},key ="'jinshang-member-selectByPrimaryKey-id:'+#p0")
    @Update("update member set balance=balance-#{balance},usedlimit=usedlimit-#{balance},availablelimit=availablelimit+#{balance} where id=#{id}")
    int creditRepayment(@Param("id") long id, @Param("balance") BigDecimal balance);


    @Select("select m.*,g.groupname from member m left join sellergroup g on m.sellergroupid=g.id where m.parentid=#{parentid} ")
    List<Member> listSubAccount(@Param("parentid") long parentid);




    /**
     * 获取用户注册统计
     * @param param
     * @return
     */
    @SelectProvider(type = StatisProvider.class, method = "queryMemberRegisterByParam")
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


    @SelectProvider(type = MemberServerProvider.class,method = "queryMemberServer")
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
                //sql.WHERE("case when bcinfo.companyname is null then meb.realname like #{companyname} ELSE bcinfo.companyname like #{companyname} end");
                sql.WHERE("(bcinfo.companyname like #{companyname} or meb.realname like #{companyname} or meb.username like #{companyname})");
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
    List<Map<String,Object>> getRegisterRate(@Param("startDate") Date startDate, @Param("endDate") Date endDate);



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
    @Select("<script>select m.id,m.realname,m.username,m.mobile " +
            "from member m " +
            "<where> 1=1 " +
            "</where> order by m.id desc" +
            "</script>")
    List<Map<String,Object>> findAllMemberList();


    /**
     * 三级分销邀请人列表
     * @param memberDto
     * @return
     */
    @Select("<script>select m.id,m.realname,m.username,m.gradleid,m.invitecode,m.nick,m.invitecode,m.inviterid,m.isbuy,m.telephone,g.gradename,m.company " +
            "from member m " +
            "left join membergrade g on g.id = m.gradleid " +
            "<where> 1=1 " +
            "<if test=\"memberDto.id != null  \">and m.id = #{memberDto.id} </if>" +
            "<if test=\"memberDto.username != null  \">and m.username LIKE '%${memberDto.username}%'</if>" +
            "<if test=\"memberDto.realname != null  \">and m.realname LIKE '%${memberDto.realname}%'</if>" +
            "<if test=\"memberDto.clerkname != null  \">and m.clerkname LIKE '%${memberDto.clerkname}%'</if>" +
            "<if test=\"memberDto.mobile != null  \">and m.mobile LIKE '%${memberDto.mobile}%'</if>" +
            "<if test=\"memberDto.registDateStart != null \">and m.createdate &gt;= #{memberDto.registDateStart} </if>" +
            "<if test=\"memberDto.registDateEnd != null \">and m.createdate &lt;= #{memberDto.registDateEnd} </if>" +
            "<if test=\"memberDto.loginDateStart != null \">and m.lastlogindate &gt;= #{memberDto.loginDateStart} </if>" +
            "<if test=\"memberDto.loginDateEnd != null \">and m.lastlogindate &lt;= #{memberDto.loginDateEnd} </if>" +
            "<if test=\"memberDto.labelid != null \">and m.labelid = #{memberDto.labelid} </if>" +
            "<if test=\"memberDto.gradleid != null \">and m.gradleid = #{memberDto.gradleid} </if>" +
            "<if test=\"memberDto.isbuy != null \">and m.isbuy = #{memberDto.isbuy} </if>" +
            "<if test=\"memberDto.invitecode != null \">and m.invitecode = #{memberDto.invitecode} </if>" +
            "<if test=\"flag == 1 and flag != null \">and m.company = true </if>" +
            "<if test=\"flag == 2 and flag != null  \">and m.company = false </if>" +
            "</where> order by m.id desc" +
            "</script>")
    List<MemberDto> selectInverterList(@Param("memberDto") MemberDto memberDto,@Param("flag") long flag);



    /**
     * 三级分销邀请人列表for excel导出
     * @param memberDto
     * @return
     */
    @Select("<script>select m.id,m.realname,m.username,m.gradleid,m.invitecode,m.nick,m.invitecode,m.inviterid,m.isbuy,m.telephone,g.gradename " +
            "from member m " +
            "left join membergrade g on g.id = m.gradleid " +
            "<where> 1=1 " +
            "<if test=\"memberDto.id != null  \">and m.id = #{memberDto.id} </if>" +
            "<if test=\"memberDto.username != null  \">and m.username LIKE '%${memberDto.username}%'</if>" +
            "<if test=\"memberDto.realname != null  \">and m.realname LIKE '%${memberDto.realname}%'</if>" +
            "<if test=\"memberDto.clerkname != null  \">and m.clerkname LIKE '%${memberDto.clerkname}%'</if>" +
            "<if test=\"memberDto.mobile != null  \">and m.mobile LIKE '%${memberDto.mobile}%'</if>" +
            "<if test=\"memberDto.registDateStart != null \">and m.createdate &gt;= #{memberDto.registDateStart} </if>" +
            "<if test=\"memberDto.registDateEnd != null \">and m.createdate &lt;= #{memberDto.registDateEnd} </if>" +
            "<if test=\"memberDto.loginDateStart != null \">and m.lastlogindate &gt;= #{memberDto.loginDateStart} </if>" +
            "<if test=\"memberDto.loginDateEnd != null \">and m.lastlogindate &lt;= #{memberDto.loginDateEnd} </if>" +
            "<if test=\"memberDto.labelid != null \">and m.labelid = #{memberDto.labelid} </if>" +
            "<if test=\"memberDto.gradleid != null \">and m.gradleid = #{memberDto.gradleid} </if>" +
            "<if test=\"memberDto.isbuy != null \">and m.isbuy = #{memberDto.isbuy} </if>" +
            "<if test=\"memberDto.invitecode != null \">and m.invitecode = #{memberDto.invitecode} </if>" +
            "</where> order by m.id desc" +
            "</script>")
    List<Map<String,Object>> selectInverterList1(@Param("memberDto") MemberDto memberDto);

    @Select("<script>select count(0) " +
            "from member m " +
            "<where> 1=1 " +
            "<if test=\"inviterid != null \">and m.inviterid = #{inviterid} </if>" +
            "</where> order by m.id desc" +
            "</script>")
    int selectCountByInviterId(@Param("inviterid") long inviterid);



    @Select("<script>select m.id,m.username,m.realname,m.clerkname,m.mobile,m.labelid,m.gradleid " +
            "from member m " +
            "<where> 1=1 " +
            "<if test=\"inviterid != null \">and m.inviterid = #{inviterid} </if>" +
            "</where> order by m.id desc" +
            "</script>")
    List<Member> selectFirstInviters(@Param("inviterid") long inviterid);

    @Select("<script>select m.id,m.username,m.realname,m.clerkname,m.mobile,m.labelid,m.gradleid " +
            "from member m " +
            "<where> 1=1 " +
            "<if test=\"inviterid != null \">and m.inviterid = #{inviterid} </if>" +
            "</where> order by m.id desc" +
            "</script>")
    List<Map<String,Object>> selectFirstInviters1(@Param("inviterid") long inviterid);

    @Select("<script>select m.id,m.username,m.realname,m.clerkname,m.mobile,m.labelid,m.gradleid " +
            "from member m " +
            "<where> 1=1 " +
            "<if test=\"inviterid2 != null \">and m.inviterid2 = #{inviterid2} </if>" +
            "</where> order by m.id desc" +
            "</script>")
    List<Member> selectSecondInviters(@Param("inviterid2")long inviterid2);

    @Select("<script>select m.id,m.username,m.realname,m.clerkname,m.mobile,m.labelid,m.gradleid " +
            "from member m " +
            "<where> 1=1 " +
            "<if test=\"inviterid2 != null \">and m.inviterid2 = #{inviterid2} </if>" +
            "</where> order by m.id desc" +
            "</script>")
    List<Member> selectSecondInviters1(@Param("inviterid2")long inviterid2);

    @Select("<script>select m.id,m.username,m.realname,m.mobile,m.invitecode,m.inviterid,m.createdate,g.gradename " +
            "from member m " +
            "left join membergrade g on g.id = m.gradleid " +
            "<where> 1=1 " +
            "<if test=\"type == 1 \">and m.inviterid = #{inviterid} </if>" +
            "<if test=\"type ==2  \">and m.inviterid2 = #{inviterid} </if>" +
            "</where> order by m.id desc" +
            "</script>")
    List<Map<String,Object>> getMemberByInviterid(@Param("inviterid") long inviterid,@Param("type") long type);


    @Select("<script>SELECT m.id,m.username,m.realname,m.createdate,b.companyname,m.mobile,m.clerkname,m.labelname  FROM  member m left join buyercompanyinfo b on m.id=b.memberid where 1=1 " +
            "<if test='id != null and id != \"\" '>AND m.id=#{id}</if>"+
            "<if test='companyname != null and companyname != \"\"'>AND b.companyname LIKE CONCAT('%',#{companyname,jdbcType=VARCHAR},'%')</if>"+
            "<if test='realname != null and realname != \"\"'>AND m.realname LIKE CONCAT('%',#{realname,jdbcType=VARCHAR},'%')</if>"+
            "<if test='mobile != null and mobile != \"\"'>AND m.mobile LIKE CONCAT('%',#{mobile,jdbcType=VARCHAR},'%')</if>"+
            "<if test='clerkname != null and clerkname != \"\"'>AND m.clerkname LIKE CONCAT('%',#{clerkname,jdbcType=VARCHAR},'%')</if>"+
            "<if test='disStatus ==0 and disStatus !=null'>and (m.clerkname is null or m.clerkname = '') </if>"+
            "<if test='disStatus ==1 and disStatus !=null'>and (m.clerkname  is not null and m.clerkname !='') </if>"+
            " order by m.createdate desc"+
            "</script>")
    List<MemberAdminViewDto> findNotAddMembers(@Param("id") Long id, @Param("companyname") String companyname,@Param("realname") String realname, @Param("mobile") String mobile,@Param("disStatus")Long disStatus,@Param("clerkname")String clerkname);

    @Select("<script>SELECT m.id,m.username,m.realname,m.createdate,b.companyname,m.mobile,m.clerkname,m.waysalesman,m.labelname  FROM  member m left join buyercompanyinfo b on m.id=b.memberid where 1=1 " +
            "<if test='id != null and id != \"\" '>AND m.id=#{id}</if>"+
            "<if test='companyname != null and companyname != \"\"'>AND b.companyname LIKE CONCAT('%',#{companyname,jdbcType=VARCHAR},'%')</if>"+
            "<if test='realname != null and realname != \"\"'>AND m.realname LIKE CONCAT('%',#{realname,jdbcType=VARCHAR},'%')</if>"+
            "<if test='mobile != null and mobile != \"\"'>AND m.mobile LIKE CONCAT('%',#{mobile,jdbcType=VARCHAR},'%')</if>"+
            "<if test='labelname != null and labelname != \"\"'>AND m.labelname = #{labelname}</if>"+
            "<if test='labelname == null or labelname == \"\"'>AND (m.labelname is null or m.labelname = '')</if>"+
            "<if test='disStatus ==0 and disStatus !=null'>and (m.waysalesman is null or m.waysalesman = '') </if>"+
            "<if test='disStatus ==1 and disStatus !=null'>and (m.waysalesman is not null and m.waysalesman !='')</if>"+
            " order by m.createdate desc"+
            "</script>")
    List<MemberAdminViewDto> findNotAddWaysalesman(@Param("id") Long id, @Param("companyname") String companyname, @Param("realname") String realname, @Param("mobile") String mobile,@Param("labelname") String labelname,@Param("disStatus")Long disStatus);


    @Select("<script>SELECT m.id,m.username,m.realname,m.createdate,b.companyname,m.mobile,m.labelname FROM  member m left join buyercompanyinfo b on m.id=b.memberid where 1=1 " +
            "<if test='id != null and id != \"\" '>AND m.id=#{id}</if>"+
            "<if test='companyname != null and companyname != \"\"'>AND b.companyname LIKE CONCAT('%',#{companyname,jdbcType=VARCHAR},'%')</if>"+
            "<if test='clerkname != null and clerkname != \"\"'>AND m.clerkname LIKE CONCAT('%',#{clerkname,jdbcType=VARCHAR},'%')</if>"+
            "<if test='realname != null and realname != \"\"'>AND m.realname LIKE CONCAT('%',#{realname,jdbcType=VARCHAR},'%')</if>"+
            "<if test='mobile != null and mobile != \"\"'>AND m.mobile LIKE CONCAT('%',#{mobile,jdbcType=VARCHAR},'%')</if>"+
            "</script>")
    List<MemberAdminViewDto> findManageMemberList(@Param("id") Long id, @Param("companyname") String companyname, @Param("clerkname") String clerkname,@Param("realname")String realname, @Param("mobile") String mobile, @Param("adminid") Long adminid);


    @Select("<script>SELECT m.id,m.username,m.realname,m.createdate,b.companyname,m.mobile,m.waysalesman,m.labelname FROM  member m left join buyercompanyinfo b on m.id=b.memberid where 1=1 " +
            "<if test='id != null and id != \"\" '>AND m.id=#{id}</if>"+
            "<if test='companyname != null and companyname != \"\"'>AND b.companyname LIKE CONCAT('%',#{companyname,jdbcType=VARCHAR},'%')</if>"+
            "<if test='realname != null and realname != \"\"'>AND m.realname LIKE CONCAT('%',#{realname,jdbcType=VARCHAR},'%')</if>"+
            "<if test='mobile != null and mobile != \"\"'>AND m.mobile LIKE CONCAT('%',#{mobile,jdbcType=VARCHAR},'%')</if>"+
            "<if test='labelname != null and labelname != \"\"'>AND m.labelname LIKE CONCAT('%',#{labelname,jdbcType=VARCHAR},'%')</if>"+
            "and m.id IN(SELECT userid FROM adminwaysalesman where  adminid=#{adminid})"+
            "</script>")
    List<MemberAdminViewDto> findManageWaysalesmanList(@Param("id") Long id, @Param("companyname") String companyname, @Param("realname") String realname, @Param("mobile") String mobile, @Param("adminid") Long adminid,@Param("labelname") String labelname);





    @Select("<script>SELECT DISTINCT BG.invoiceheadup,m.id,m.username,m.realname,b.companyname,m.mobile  FROM  member m left join buyercompanyinfo b on m.id=b.memberid LEFT JOIN billingrecord BG ON m.id=BG.memberid where 1=1 " +
            "<if test='username != null and username != \"\" '>AND m.username LIKE CONCAT('%',#{username,jdbcType=VARCHAR},'%')</if>"+
            "<if test='companyname != null and companyname != \"\"'>AND b.companyname LIKE CONCAT('%',#{companyname,jdbcType=VARCHAR},'%')</if>"+
            "<if test='realname != null and realname != \"\"'>AND m.realname LIKE CONCAT('%',#{realname,jdbcType=VARCHAR},'%')</if>"+
            "<if test='mobile != null and mobile != \"\"'>AND m.mobile LIKE CONCAT('%',#{mobile,jdbcType=VARCHAR},'%')</if>"+
            "<if test='invoiceheadup !=null and invoiceheadup != \"\"'>AND BG.invoiceheadup LIKE CONCAT('%',#{invoiceheadup,jdbcType=VARCHAR},'%')</if>"+
            "</script>")
    List<Map<String,Object>> findMembersByFuzzy(@Param("username") String username, @Param("companyname") String companyname, @Param("realname") String realname, @Param("mobile") String mobile, @Param("invoiceheadup") String invoiceheadup);

    @Update("update member set clerkname=#{clerkname}where id=#{id}")
    int  updateMemberClerknameByid(@Param("id") Long id, @Param("clerkname") String clerkname);



    @Select("select * from member WHERE id=#{id}")
    Member getMemberByid(@Param("id") Long id);


    @Update("<script>update member  set inviterid2 = #{inviterid2} " +
            "<where> 1=1 " +
            "<if test=\"id != null \">and id = #{id} </if>" +
            "</where> " +
            "</script>")
    void updateMemberInviterid2ById(@Param("id") Long id, @Param("inviterid2") Long inviterid2);


    @Select("<script>select m.id,m.username,m.realname,m.clerkname,m.mobile,m.labelid,m.gradleid,m.nick,m.company,s.companyname,s.province,s.city,s.citysmall,s.address " +
            "from member m " +
            "left join sellercompanyinfo s on s.memberid = m.id " +
            "<where> 1=1 " +
            "<if test=\"username != null \">and (m.username like  '%${username}%' or  m.realname like '%${username}%') </if>" +
            "<if test=\"id != null and id !=0 \">and m.id = #{id} </if>" +
            "<if test=\"companyname != null  \">and s.companyname like  '%${companyname}%' </if>" +
            "<if test=\"clerkname != null \"> and m.clerkname = #{clerkname}  </if>" +
            "</where> order by m.id desc" +
            "</script>")
    List<Map<String,Object>> getMemberInfoByUserName(@Param("username") String username,@Param("clerkname") String clerkname,@Param("id") Long id,@Param("companyname")String companyname);





    @Select({"<script>",
            "select m.id from member m ",
            "where m.invitecode in ",
            "<foreach item='item' index='index' collection='invitecodeList'",
            "open='(' separator=',' close=')'>",
            "#{item}",
            "</foreach>",
            "</script>"
    })
    List<Member> getMemberByInvitecodes(@Param("invitecodeList") List invitecodeList);


    @Select({"<script>",
            "select m.id,m.username,m.realname,m.mobile,m.inviterid,m.inviterid2,m.invitecode from member m ",
            "where m.inviterid in ",
            "<foreach item='item' index='index' collection='ids'",
            "open='(' separator=',' close=')'>",
            "#{item}",
            "</foreach>",
            "</script>"
    })
    List<Member> getMemmberByIdsList(@Param("ids") long[] ids);

    @Select("select m.* from member m where m.inviterid = #{inviterid} ")
    List<Member> selectMemberByInviterid(@Param("inviterid") Long inviterid);


    @Select("select m.id,m.username,m.realname,m.mobile from member m where m.mobile= #{mobile}  limit 1")
    Member selectMemberByMobile(@Param("mobile") String mobile);

    @Update("update member set labelname=#{labelname},waysalesman = #{realname} where id=#{memberid}")
    void updateMemberLabelnameAndWaysalesman(@Param("labelname") String labelname,@Param("realname") String realname,@Param("memberid") Long memberid);

    @Update("update member set clerkname = null where id in (${ids})")
    void updateMemberByIds(@Param("ids") StringBuffer ids);

}