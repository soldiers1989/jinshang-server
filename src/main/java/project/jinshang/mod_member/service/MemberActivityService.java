package project.jinshang.mod_member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_company.bean.BuyerCompanyInfo;
import project.jinshang.mod_member.MemberActivityMapper;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_member.bean.MemberActivity;

/**
 * Created by Administrator on 2018/5/10.
 */
@Service
public class MemberActivityService {

    @Autowired
    private MemberActivityMapper mam;

    public Member getMemberByMobile(String mobile){
        return mam.getMemberByMobile(mobile);
    }

    public BuyerCompanyInfo getMemberCompanyByMemberid(long id){
        return mam.getMemberCompanyByMemberid(id);
    }

    public MemberActivity getMemberActivityByMobile(String mobile){
        return mam.getMemberActivityByMobile(mobile);
    }

    public int addmemberActivity(MemberActivity memberActivity){
        return mam.addmemberActivity(memberActivity);
    }
}
