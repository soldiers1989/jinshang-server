package project.jinshang.mod_member;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import project.jinshang.mod_member.bean.MemberExample;
import project.jinshang.mod_member.bean.MemberGrade;
import project.jinshang.mod_member.bean.MemberGradeExample;
import project.jinshang.mod_member.service.MemberGradeService;
import project.jinshang.mod_member.service.MemberService;

import java.util.List;

/**
 * create : wyh
 * date : 2017/11/3
 */
@RestController
@RequestMapping("/rest/admin/membergrade")
@Api(tags = "后台会员管理-会员等级",description = "会员等级相关接口")
@Transactional(rollbackFor = Exception.class)
public class MemberGradeAdminRestAction {

    @Autowired
    private MemberGradeService memberGradeService;


    @Autowired
    private MemberService memberService;



   @PostMapping("/get")
   @ApiOperation("根据会员等级id获取")
   public  MemberGradeRet get(@RequestParam(required =  true) int id){

       MemberGradeRet ret = new MemberGradeRet();

       MemberGrade memberGrade = memberGradeService.getById(id);

       if(memberGrade == null){
           ret.setMessage("会员等级不存在");
           ret.setResult(BasicRet.ERR);
           return  ret;
       }

       ret.data.memberGrade = memberGrade;
       ret.setResult(BasicRet.SUCCESS);
       return  ret;
   }




   private  class  MemberGradeRet extends  BasicRet{
       private class MemberGradeData {
           public MemberGrade getMemberGrade() {
               return memberGrade;
           }

           public void setMemberGrade(MemberGrade memberGrade) {
               this.memberGrade = memberGrade;
           }

           private   MemberGrade memberGrade;

       }


       private  MemberGradeData data = new MemberGradeData();

       public MemberGradeData getData() {
           return data;
       }

       public void setData(MemberGradeData data) {
           this.data = data;
       }
   }



    @RequestMapping(value = "/addMemberGrade",method = RequestMethod.POST)
    @ApiOperation(value = "新增会员等级")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "序号",name = "iserial",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(value = "名称",name = "gradename",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(value = "是否为默认{1:默认,0:否}",name = "idefault",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(value = "备注",name = "remark",required = false,paramType = "query",dataType = "string")
    })
    public BasicRet addMemberGrade(MemberGrade memberGrade){
        BasicRet basicRet = new BasicRet();

        if(memberGrade.getGradename() == null){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("名称不可为空");
            return  basicRet;
        }

        if(memberGrade.getIdefault() == 1){
            MemberGrade mg = new MemberGrade();
            mg.setIdefault(0);
            MemberGradeExample example = new MemberGradeExample();
            example.createCriteria().andIdefaultEqualTo(1);
            memberGradeService.updateByExampleSelective(mg,example);
        }

        if(memberGrade.getIserial() == null){
            memberGrade.setIserial(0);
        }

        if (memberGrade.getIdefault()== null){
            memberGrade.setIdefault(0);
        }

        memberGradeService.addMemberGrade(memberGrade);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("添加成功");
        return  basicRet;
    }


    @RequestMapping(value = "/deleteMenberById",method = RequestMethod.POST)
    @ApiOperation(value = "删除指定会员级别")
    public  BasicRet deleteMenberById(@RequestParam(required = true) int id){
        BasicRet basicRet=new BasicRet();

        MemberExample example = new MemberExample();
        example.createCriteria().andGradleidEqualTo((long)id);

        int count =  memberService.countByExample(example);

        if(count>0){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("有会员属于该级别，不可删除");
            return  basicRet;
        }


        MemberGrade memberGrade = memberGradeService.getById(id);
        if(memberGrade != null && memberGrade.getIdefault()==1){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("默认级别不可删除");
            return  basicRet;
        }


        memberGradeService.deleteMenberById(id);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("删除成功");
        return  basicRet;
    }




    @RequestMapping(value = "/updateByExampleSelective",method = RequestMethod.POST)
    @ApiOperation(value = "修改会员级别信息")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "ID",name="id",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(value = "序号",name = "iserial",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(value = "名称",name = "gradename",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(value = "是否为默认{1:默认,0:否}",name = "idefault",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(value = "备注",name = "remark",required = false,paramType = "query",dataType = "string")
    })
    public  BasicRet updateByExampleSelective(MemberGrade memberGrade){
        BasicRet basicRet=new BasicRet();


        if(memberGrade.getIdefault() == 1){
            MemberGrade mg = new MemberGrade();
            mg.setIdefault(0);
            MemberGradeExample example = new MemberGradeExample();
            example.createCriteria().andIdefaultEqualTo(1);
            memberGradeService.updateByExampleSelective(mg,example);
        }else{
            //查询是否有默认的，没有的话不可修改
            MemberGrade deMg =  memberGradeService.getDefaultMemberGrade();
            if(deMg.getId() == memberGrade.getId()){
                basicRet.setResult(BasicRet.ERR);
                basicRet.setMessage("至少有一个设置为默认");
                return  basicRet;
            }
        }


        memberGradeService.updateMemberGrade(memberGrade);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("修改成功");
        return basicRet;
    }
    @RequestMapping(value = "/listAll",method = RequestMethod.POST)
    @ApiOperation("全会员列表")
     public  GradeListRet listAll(){
        GradeListRet gradeListRet = new GradeListRet();

        gradeListRet.list =  memberGradeService.list();
        gradeListRet.setResult(BasicRet.SUCCESS);
        return  gradeListRet;
     }


     private class  GradeListRet extends  BasicRet{
         private  List<MemberGrade> list;

         public List<MemberGrade> getList() {
             return list;
         }

         public void setList(List<MemberGrade> list) {
             this.list = list;
         }
     }

}
