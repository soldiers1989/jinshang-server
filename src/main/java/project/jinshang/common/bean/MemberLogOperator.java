package project.jinshang.common.bean;

import org.springframework.stereotype.Component;
import project.jinshang.mod_member.bean.Admin;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_product.bean.MemberOperateLog;
import project.jinshang.mod_product.service.MemberOperateLogService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class MemberLogOperator {


    /**
     * 保存操作日志
     * @param member
     * @param admin
     * @param content
     * @param request
     */
    public void saveMemberLog(Member member, Admin admin, String content, HttpServletRequest request, MemberOperateLogService memberOperateLogService){
        MemberOperateLog memberOperateLog = new MemberOperateLog();
        memberOperateLog.setContent(content);
        String ip = RequestIpGet.getIpAddr(request);
        memberOperateLog.setIpaddress(ip);
        if(member!=null){
            memberOperateLog.setMemberid(member.getId());
            memberOperateLog.setMembername(member.getUsername());
        }
        if(admin!=null){
            memberOperateLog.setMemberid(admin.getId());
            memberOperateLog.setMembername(admin.getUsername());
        }
        memberOperateLog.setCreatetime(new Date());
        memberOperateLogService.saveMemberOperateLog(memberOperateLog);

    }
    /**
     * 保存操作日志
     * @param member
     * @param admin
     * @param content
     * @param request
     */
    public void saveMemberLog(Member member, Admin admin, String content, String url,HttpServletRequest request, MemberOperateLogService memberOperateLogService){

        MemberOperateLog memberOperateLog = new MemberOperateLog();
        memberOperateLog.setUrl(url);
        memberOperateLog.setContent(content);
        String ip = RequestIpGet.getIpAddr(request);
        memberOperateLog.setIpaddress(ip);
        if(member!=null){
            memberOperateLog.setMemberid(member.getId());
            memberOperateLog.setMembername(member.getUsername());
        }
        if(admin!=null){
            memberOperateLog.setMemberid(admin.getId());
            memberOperateLog.setMembername(admin.getUsername());
        }
        memberOperateLog.setCreatetime(new Date());
        memberOperateLogService.saveMemberOperateLog(memberOperateLog);

    }
}
