package project.jinshang.mod_member;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import mizuki.project.core.restserver.config.BasicRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.jinshang.common.bean.PageRet;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.utils.Base64Utils;
import project.jinshang.common.utils.CommonUtils;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_member.bean.Admin;
import project.jinshang.mod_member.bean.AdminGroup;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_member.service.AdminGroupService;
import project.jinshang.mod_member.service.AdminService;
import project.jinshang.mod_member.service.MemberService;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * create : wyh
 * date : 2017/10/30
 */
@RestController
@SessionAttributes({AppConstant.ADMIN_SESSION_NAME,AppConstant.MEMBER_SESSION_NAME})
@Api(tags = "后台管理员模块",description = "后台管理员信息相关接口")
@RequestMapping("/rest/admin")
public class AdminRestAction {


    @Autowired
    private AdminGroupService adminGroupService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private MemberService memberService;


    @ApiOperation("登录 123=MTIz 123456=MTIzNDU2")
    @RequestMapping(value =  "/login",method = RequestMethod.POST)
    public BasicRet login(String username, String password, HttpSession session){
        BasicRet basicRet =  new BasicRet();

        password = Base64Utils.decode(password);

       Admin admin =  adminService.getByUserName(username);

       if(admin == null || !admin.getPassword().equals(CommonUtils.genMd5Password(password,admin.getPasswordsalt()))){
           basicRet.setResult(BasicRet.ERR);
           basicRet.setMessage("登陆失败");
       }else{

           session.setAttribute(AppConstant.ADMIN_SESSION_NAME,admin);

           basicRet.setMessage("登陆成功");
           basicRet.setResult(BasicRet.SUCCESS);
       }

        return  basicRet;
    }


    @PostMapping("/updatePwd")
    @ApiOperation("修改密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "oldpwd",value = "旧密码",required = true,paramType = "query" ),
            @ApiImplicitParam(name = "newpwd",value = "新密码",required = true,paramType = "query" ),
    })
    public BasicRet updatePwd(@RequestParam(required = true) String oldpwd,@RequestParam(required = true) String newpwd,Model model){
        BasicRet basicRet =  new BasicRet();

        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);

         admin =  adminService.getById(admin.getId());

        if(admin == null || !admin.getPassword().equals(CommonUtils.genMd5Password(oldpwd,admin.getPasswordsalt()))) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("登陆失败");
            return  basicRet;
        }

        Admin updateAdmin = new Admin();
        updateAdmin.setPassword(CommonUtils.genMd5Password(newpwd,admin.getPasswordsalt()));
        updateAdmin.setId(admin.getId());
        adminService.updateByPrimaryKeySelective(updateAdmin);

        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("修改成功");
        return  basicRet;
    }




    @PostMapping("/updateAdmin")
    @ApiOperation("修改管理员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "realname",value = "姓名",required = true,paramType = "query" ),
            @ApiImplicitParam(name = "mobile",value = "手机号",required = true,paramType = "query" ),
            @ApiImplicitParam(name = "groupid",value = "组id",required = true,paramType = "query",dataType = "int" ),
//            @ApiImplicitParam(name = "parentid",value = "parentid",required = false,defaultValue = "0",paramType = "query",dataType = "int"),
    })
    public  BasicRet updateAdmin(@RequestParam(required = true) long id,
                                 @RequestParam(required = true) String realname,
                                 @RequestParam(required = true) String mobile,
                                 @RequestParam(required = true) int groupid,
                                 @RequestParam(required = false) String password,
                                 @RequestParam(required = true) String roles[], Model model){

        BasicRet basicRet =  new BasicRet();

        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);


        Admin dbAdmin  =  adminService.getById(id);
        if(dbAdmin == null){
            basicRet.setMessage("要修改的管理员不存在");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }

        Admin updateAdmin =new Admin();
        updateAdmin.setId(id);
        updateAdmin.setRealname(realname);
        updateAdmin.setMobile(mobile);
        updateAdmin.setGroupid(groupid);
        updateAdmin.setRoles(roles);


        AdminGroup adminGroup = adminGroupService.getById(new Long(updateAdmin.getGroupid()));
        if (adminGroup == null) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("该组不存在");
            return basicRet;
        }
        updateAdmin.setGroupname(adminGroup.getName());

        if(StringUtils.hasText(password)){
            updateAdmin.setPassword(CommonUtils.genMd5Password(password,dbAdmin.getPasswordsalt()));
        }


        adminService.updateByPrimaryKeySelective(updateAdmin);

        if(admin.getId().equals(id)){
            dbAdmin =  adminService.getById(id);
            model.addAttribute(AppConstant.ADMIN_SESSION_NAME,dbAdmin);
        }

        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("修改成功");
        return  basicRet;
    }



    @PostMapping("/deleteAdmin")
    @ApiOperation("删除管理员")
    public  BasicRet deleteAdmin(@RequestParam(required = true) long id){
        BasicRet basicRet =  new BasicRet();
        adminService.deleteById(id);

        basicRet.setMessage("删除成功");
        basicRet.setResult(BasicRet.SUCCESS);
        return  basicRet;
    }



    @PostMapping("/listAdmin")
    @ApiOperation("管理员列表")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "姓名",name = "realname",paramType = "query",dataType = "string"),
    })
    public PageRet listAdmin(@RequestParam(required = true,defaultValue = "1") int pageNo,
                             @RequestParam(required = true,defaultValue = "20") int pageSize,
                             @RequestParam(required = false,defaultValue = "") String realname){
        PageRet pageRet =  new PageRet();
        Admin admin = new Admin();
        admin.setRealname(realname);
        PageInfo pageInfo = adminService.listByPage(pageNo,pageSize,admin);

        pageRet.data.setPageInfo(pageInfo);
        pageRet.setResult(BasicRet.SUCCESS);
        return  pageRet;
    }


    @PostMapping("/getAdmin")
    @ApiOperation("获取管理员信息")
    public  AdminDataRet getAdmin(long id){
        AdminDataRet adminDataRet = new AdminDataRet();

        Admin admin =  adminService.getById(id);
        if(admin == null){
            adminDataRet.setMessage("该管理员不存在");
            adminDataRet.setResult(BasicRet.ERR);
            return  adminDataRet;
        }

        adminDataRet.setResult(BasicRet.SUCCESS);
        adminDataRet.data =  admin;

        return  adminDataRet;
    }



    private  class  AdminDataRet extends  BasicRet{
        private  Admin data;

        public Admin getData() {
            return data;
        }

        public void setData(Admin data) {
            this.data = data;
        }
    }




    @RequestMapping(value = "/addAdmin",method = RequestMethod.POST)
    @ApiOperation(value ="添加管理员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "用户名",required = true,paramType = "query"),
            @ApiImplicitParam(name = "password",value = "密码",required = true,paramType = "query" ),
            @ApiImplicitParam(name = "realname",value = "姓名",required = true,paramType = "query" ),
            @ApiImplicitParam(name = "mobile",value = "手机号",required = true,paramType = "query" ),
            @ApiImplicitParam(name = "groupid",value = "组id",required = false,paramType = "query",dataType = "int" ),
            @ApiImplicitParam(name = "parentid",value = "parentid",required = false,defaultValue = "0",paramType = "query",dataType = "int"),
    })
    public  BasicRet addAdmin(Admin admin,@ApiParam(required = true) @RequestParam(required = true) String roles[]){
        BasicRet basicRet = new BasicRet();

        if(admin.getGroupid() >0) {
            AdminGroup adminGroup = adminGroupService.getById(new Long(admin.getGroupid()));
            if (adminGroup == null) {
                basicRet.setResult(BasicRet.ERR);
                basicRet.setMessage("该组不存在");
                return basicRet;
            }
            admin.setGroupname(adminGroup.getName());
        }
        Admin dbAdmin =  adminService.getByUserName(admin.getUsername());
        if(dbAdmin != null){
            return  new BasicRet(BasicRet.ERR,"该管理员名已存在，不可重复添加");
        }

        admin.setRoles(roles);

        adminService.addAdmin(admin);
        return basicRet.setResult(BasicRet.SUCCESS);
    }

    @RequestMapping(value =  "/addGroup",method = RequestMethod.POST)
    @ApiOperation(value = "添加管理员组")
    public  BasicRet addGroup(@ApiParam(required = true) @RequestParam(required = true) String name,
                              @ApiParam(required = true) @RequestParam(required = true) String[] roles ){
        AdminGroup adminGroup = new AdminGroup();
        adminGroup.setName(name);
        adminGroup.setRoles(roles);
        adminGroupService.addGroup(adminGroup);
        BasicRet basicRet = new BasicRet();
        basicRet.setMessage("添加成功");
        basicRet.setResult(BasicRet.SUCCESS);
        return  basicRet;
    }


    @RequestMapping(value = "/listGroups",method = RequestMethod.POST)
    @ApiOperation(value = "列出所有组")
    public PageRet listGroups(@RequestParam(required = true,defaultValue = "1") int pageNo,
                              @RequestParam(required = true,defaultValue = "20")  int pageSize){
         PageRet pageRet =  new PageRet();
         PageInfo pageInfo = adminGroupService.listByPage(pageNo,pageSize);
         pageRet.data.setPageInfo(pageInfo);
         pageRet.setResult(BasicRet.SUCCESS);
         return pageRet;
    }




    @PostMapping("/getGroupsById")
    @ApiOperation("获取组信息")
    public GroupsDataRet getGroupsById(@RequestParam(required = true) long id){
        GroupsDataRet groupsDataRet =  new GroupsDataRet();

        AdminGroup adminGroup =  adminGroupService.getById(id);
        if(adminGroup == null){
            groupsDataRet.setMessage("该组不存在");
            groupsDataRet.setResult(BasicRet.ERR);
            return  groupsDataRet;
        }

        groupsDataRet.setResult(BasicRet.SUCCESS);
        groupsDataRet.setData(adminGroup);
        return  groupsDataRet;
    }

    private  class  GroupsDataRet extends  BasicRet{
        private  AdminGroup data;

        public AdminGroup getData() {
            return data;
        }

        public void setData(AdminGroup data) {
            this.data = data;
        }
    }




    @RequestMapping(value="/deleteGroup",method = RequestMethod.POST)
    @ApiOperation(value = "删除组")
    public  BasicRet deleteGroup(@RequestParam(required = true) int id){
        BasicRet basicRet=new BasicRet();
        int Count=adminService.selectCountByGroupId(id);
        if (Count!=0){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("该组下还有成员账号，不能删除！");
            return  basicRet;
        }else {
            adminGroupService.deleteGroup(id);
        }
        basicRet.setResult(BasicRet.SUCCESS);
        return basicRet;
    }

    @RequestMapping(value = "/updateGroup",method = RequestMethod.POST)
    @ApiOperation(value="修改组")
    public  BasicRet updateGroup(@RequestParam(required = true) long id,
                                 @RequestParam(required = true) String name,
                                 @RequestParam(required = true) String[] roles
                                ){
        BasicRet basicRet=new BasicRet();

        AdminGroup adminGroup =  adminGroupService.getById(id);

        if(adminGroup == null){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("该组不存在");
            return  basicRet;
        }

        adminGroup.setName(name);
        adminGroup.setRoles(roles);
        adminGroupService.updateGroup(adminGroup);
        basicRet.setMessage("修改成功");
        basicRet.setResult(BasicRet.SUCCESS);
        return  basicRet;
    }


    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    @ApiOperation(value = "退出登录")
    public  BasicRet logout(HttpSession session,Model model){
        model.asMap().remove(AppConstant.ADMIN_SESSION_NAME);
        session.removeAttribute(AppConstant.ADMIN_SESSION_NAME);
        BasicRet basicRet = new BasicRet();
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("退出成功");
        return  basicRet;
    }




    @RequestMapping(value = "/buyerlogin",method = RequestMethod.POST)
    public BasicRet buyerlogin(@RequestParam(required = true) long memberid,HttpSession session,Model model) {
        BasicRet basicRet = new BasicRet();
        Member member = memberService.getMemberById(memberid);

        if (member == null) {
            basicRet.setMessage("用户不存在");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        } else {
            member.setFrom("admin");
            member.setLoginType("main");

            if (member.getDisabled() == true) {
                basicRet.setResult(BasicRet.ERR);
                basicRet.setMessage("帐号被禁用");
                return basicRet;
            }

            memberService.fillMember(member);

            model.addAttribute(AppConstant.MEMBER_SESSION_NAME, member);
            basicRet.setMessage("登陆成功");
            basicRet.setResult(BasicRet.SUCCESS);

            Member updateDateMember = new Member();
            updateDateMember.setId(member.getId());
            updateDateMember.setLastlogindate(new Date());

            return basicRet;
        }
    }

}
