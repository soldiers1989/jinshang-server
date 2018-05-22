package project.jinshang.mod_member;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.jinshang.common.bean.MemberLogOperator;
import project.jinshang.common.constant.AdminAuthorityConst;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.mod_member.bean.Admin;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_member.bean.MemberLabel;
import project.jinshang.mod_member.bean.MemberLabelExample;
import project.jinshang.mod_member.bean.dto.MemberLabelQueryDto;
import project.jinshang.mod_member.bean.dto.MemberLabelViewDto;
import project.jinshang.mod_member.service.MemberLabelService;
import project.jinshang.mod_member.service.MemberService;
import project.jinshang.mod_product.service.MemberOperateLogService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

/**
 * create : wyh
 * date : 2017/11/3
 */

@RestController
@RequestMapping("/rest/admin/member")
@Api(tags = "后台管理会员-会员标签",description = "会员管理相关接口")
@Transactional(rollbackFor = Exception.class)
public class MemberLabelRestAction {
    @Autowired
    private MemberLabelService memberLabelService;
    @Autowired
    private MemberService memberService;

    @Autowired
    protected MemberLogOperator memberLogOperator;

    @Autowired
    private MemberOperateLogService memberOperateLogService;


    @RequestMapping(value = "/addLable",method = RequestMethod.POST)
    @ApiOperation(value = "会员标签添加")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "labelname",value = "标签名",required = true,paramType = "query",dataType = "string")
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.LABELMANAGEMENT + "')")
    public BasicRet addLable(@RequestParam(required = true) String labelname, Model model, HttpServletRequest request){
        BasicRet basicRet=new BasicRet();

        Admin admin= (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);

        MemberLabelExample example =  new MemberLabelExample();
        MemberLabelExample.Criteria criteria = example.createCriteria();
        criteria.andLabelnameEqualTo(labelname);
        int count  = memberLabelService.countByExample(example);
        if(count>0){
            return  new BasicRet(BasicRet.ERR,"该标签已存在");
        }

        MemberLabel label = new MemberLabel();
        label.setLabelname(labelname);
        memberLabelService.addLable(label);


        //保存日志
        memberLogOperator.saveMemberLog(null,admin,"添加会员标签："+labelname,request,memberOperateLogService);

        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("标签添加成功");
        return basicRet;
    }

    @RequestMapping(value = "/deleteLable",method = RequestMethod.POST)
    @ApiOperation(value = "删除标签")
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.LABELMANAGEMENT + "')")
    public  BasicRet deleteLable(@RequestParam (required = true) long id,Model model,HttpServletRequest request){
            BasicRet basicRet=new BasicRet();

            Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);

            MemberLabel label =  memberLabelService.getById(id);
            if(label == null){
                return  new BasicRet(BasicRet.ERR,"标签不存在");
            }

            int Count= memberService.selectCountByLableId(id+",");
            if (Count!=0){
                basicRet.setResult(BasicRet.ERR);
                basicRet.setMessage("该标签还有成员账号，不能删除！");
            }else {
                memberLabelService.deleteLable(id);

                //保存日志
                memberLogOperator.saveMemberLog(null,admin,"删除会员标签："+label.getLabelname(),request,memberOperateLogService);

                basicRet.setResult(BasicRet.SUCCESS);
                basicRet.setMessage("标签删除成功");
            }


         return  basicRet;

    }



    @RequestMapping(value = "/updateLable",method = RequestMethod.POST)
    @ApiOperation("修改标签")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "标签ID",required = true,dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "labelname",value = "标签名",required = true,dataType = "string",paramType = "query")
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.LABELMANAGEMENT + "')")
    public  BasicRet updateLable(@RequestParam(required = true) long id ,
            @RequestParam(required = true) String labelname,Model model,HttpServletRequest request){
        BasicRet basicRet=new BasicRet();

        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);

        MemberLabel dbMemberLabel = memberLabelService.getByLabelName(labelname);
        if(dbMemberLabel != null && !dbMemberLabel.getId().equals(id)){
            return  new BasicRet(BasicRet.ERR,"该名称的标签已存在");
        }


        MemberLabel updateLabel = new MemberLabel();
        updateLabel.setId(id);
        updateLabel.setLabelname(labelname);

        memberLabelService.updateLable(updateLabel);

        //保存日志
        memberLogOperator.saveMemberLog(null,admin,"修改会员标签："+labelname,request,memberOperateLogService);


        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("标签修改成功");
        return basicRet;
    }


    @RequestMapping(value = "/listAllLable",method = RequestMethod.POST)
    @ApiOperation("列出所有标签列表")
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.LABELMANAGEMENT + "')")
    public LableListRet listLable(@RequestParam(required = false,defaultValue = "") String labelname){
        LableListRet lableListRet =  new LableListRet();
        MemberLabelQueryDto queryDto = new MemberLabelQueryDto();
        queryDto.setLabelname(labelname);

        List<MemberLabelViewDto> list = memberLabelService.getLabelList(queryDto);

        if(list.size()>0){
            for(MemberLabelViewDto dto : list){
                dto.setCount(memberLabelService.getMemberLabelCount(dto.getId()));
            }
        }

        lableListRet.list = list;

        lableListRet.setResult(BasicRet.SUCCESS);
        return  lableListRet;
    }


    private  class  LableListRet extends  BasicRet{
       private   List<MemberLabelViewDto> list;

        public List<MemberLabelViewDto> getList() {
            return list;
        }

        public void setList(List<MemberLabelViewDto> list) {
            this.list = list;
        }
    }




}
