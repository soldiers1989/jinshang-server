package project.jinshang.mod_member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_member.MemberTokenMapper;
import project.jinshang.mod_member.bean.MemberToken;
import project.jinshang.mod_member.bean.MemberTokenExample;

/**
 * create : wyh
 * date : 2018/2/5
 */

@Service
public class MemberTokenService {

    @Autowired
    private MemberTokenMapper memberTokenMapper;

    public  void  insertSelective(MemberToken token){
        memberTokenMapper.insertSelective(token);
    }

    public  void deleteByToken(String token){
        MemberTokenExample example = new MemberTokenExample();
        MemberTokenExample.Criteria criteria = example.createCriteria();
        criteria.andTokenEqualTo(token);
        memberTokenMapper.deleteByExample(example);
    }


    public  MemberToken getByToken(String token){
        return  memberTokenMapper.getByToken(token);
    }

}
