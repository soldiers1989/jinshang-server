package project.jinshang.mod_fx;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.mod_common.bean.BasicExtRet;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_member.service.MemberService;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = {"/rest/admin/fxupdate"})
@Api(tags = "后台邀请者一级二级邀请者id修改", description = "后台邀请者一级二级邀请者id修改接口")
@SessionAttributes(AppConstant.ADMIN_SESSION_NAME)
public class FxUpdateAction {
    @Autowired
    private MemberService memberService;


    //------------------------------------- 临时使用 将指定的97个地推人员 inviterid inviterid2设置为指定人 11596
    @GetMapping("/update")
    @ApiOperation(value = "将指定的97个地推人员 inviterid inviterid2设置为指定人 11596")
    public BasicRet update() {
        BasicExtRet basicRet = new BasicExtRet();
        String[] invitecodes = {"698fc6","7cf08c","9a1f7b","e6e4b5","d21e18","498c28","8a10a4","681293","0b627b","4dc503","e8bb82","e01b45","576ddb","e707f5","0f466b","e0d10c","e891c2","a8daf9"
                ,"1faf62","abd604","f17f23","79ff3b","d59f94","0c568d","b0e4a5","d80cbd","65e5d3","060785","0e31ed","28aeb8","fdd0b7","9dce62","c03837","4b26ab","1e3480","d9b179","ee2544","a8acd8","6eeb07","6ac20e","1a74f5","5e3773",
                "685465","a1539a","8ad703","cbf01a","21489e","f79810","f139ce","df08d9","e76c0c","4e6ec1","36fcb1","65c744","382022","befa2d","968150","cdac51","163c85","bf496a","e752e9","cbb5b5","2d6f9b","df8c52","bb8ecb","5f1eed","1d7fab"
                ,"543008","22f8cc","e3f285","7616ff","1a9c9d","4f62b2","b9ee00","bded68","198d66","40fb8e","47bdfa","484dfc","7d5d42","a209be","939484","b55238","25f020","93cd2a","53c41d","e2dad6","ee0900","4ba258","07d2ed","0dd415","6b77da","879577","17c390","88c856","c929bc","c6fefd"};
        //String[] invitecodes = {"086b6c","5f67b7"};
        List invitecodeList = Arrays.asList(invitecodes);
        //查询97个邀请码的会员id
        List<Member> idsList = memberService.getMemberByInvitecodes(invitecodeList);
        //再查询他们下级ID
        long[] longs=idsList.stream().mapToLong(Member::getId).toArray();
        List<Member> idslisttemp = memberService.getMemmberByIdsList(longs);
        for (Member member:idslisttemp) {
            Member member1 = new Member();
            //更新一级邀请者的id为11596
            member1.setInviterid(11596l);
            member1.setId(member.getId());
            memberService.updateMember(member1);
            //
            List<Member> list = memberService.selectMemberByInviterid(member.getId());
            for (Member member2: list) {
                Member member3 = new Member();
                //更新二级邀请者的id为11596
                member3.setInviterid2(11596l);
                member3.setId(member2.getId());
                memberService.updateMember(member3);
            }
        }
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("更新成功");
        basicRet.setData(idslisttemp);
        return basicRet;
    }
}
