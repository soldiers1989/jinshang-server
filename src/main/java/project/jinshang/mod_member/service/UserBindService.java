package project.jinshang.mod_member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_member.UserBindingMapper;
import project.jinshang.mod_member.bean.UserBinding;

/**
 * create : wyh
 * date : 2018/2/24
 */

@Service
public class UserBindService {

    @Autowired
    private UserBindingMapper userBindingMapper;

    public void insertSelective(UserBinding userBinding){
        userBindingMapper.insertSelective(userBinding);
    }

    public UserBinding getByOpenid(String openid,Short type){
        return  userBindingMapper.getByOpenid(openid,type);
    }

    /**
     * 查询已经使用该openid绑定的帐号的个数
     * @param openid
     * @param type
     * @return
     */
    public int getBindingCount(String openid,Short type){
        return  userBindingMapper.getBindingCount(openid,type);
    }


    /**
     * 查询用户的绑定信息
     * @param memberid
     * @param type
     * @return
     */
    public  UserBinding getByMemberid(long memberid,Short type){
        return  userBindingMapper.getByMemberid(memberid,type);
    }






}
