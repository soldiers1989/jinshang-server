package project.jinshang.mod_member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_member.MemberGradeMapper;
import project.jinshang.mod_member.bean.MemberGrade;
import project.jinshang.mod_member.bean.MemberGradeExample;

import java.util.List;

/**
 * create : wyh
 * date : 2017/11/3
 */

@Service
public class MemberGradeService {

    @Autowired
    private MemberGradeMapper memberGradeMapper;


    public void addMemberGrade(MemberGrade mg){
        memberGradeMapper.insert(mg);
    }


    public  void  deleteMenberById(int id){
        memberGradeMapper.deleteByPrimaryKey(id);
    }


    public  void updateMemberGrade(MemberGrade mg){
        memberGradeMapper.updateByPrimaryKey(mg);
    }


    public MemberGrade getById(int id){
        return  memberGradeMapper.selectByPrimaryKey(id);
    }


    public List<MemberGrade> list(){
        MemberGradeExample example = new MemberGradeExample();
        example.setOrderByClause(" iserial asc ");
        return  memberGradeMapper.selectByExample(example);
    }


    public  void  updateByExampleSelective(MemberGrade memberGrade,MemberGradeExample example){
        memberGradeMapper.updateByExampleSelective(memberGrade,example);
    }


    /**
     * 获取默认的级别信息
     * @return
     */
    public  MemberGrade getDefaultMemberGrade(){
        MemberGradeExample example =  new MemberGradeExample();
        example.createCriteria().andIdefaultEqualTo(1);
        List<MemberGrade> list = memberGradeMapper.selectByExample(example);
        if(list != null && list.size()>0){
            return  list.get(0);
        }

        return  null;
    }

}
