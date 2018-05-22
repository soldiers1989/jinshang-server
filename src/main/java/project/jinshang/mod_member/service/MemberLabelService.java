package project.jinshang.mod_member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_member.MemberLabelMapper;
import project.jinshang.mod_member.bean.MemberLabel;
import project.jinshang.mod_member.bean.MemberLabelExample;
import project.jinshang.mod_member.bean.dto.MemberLabelQueryDto;
import project.jinshang.mod_member.bean.dto.MemberLabelViewDto;

import java.util.List;
import java.util.Map;

/**
 * create : wyh
 * date : 2017/11/3
 */

@Service
public class MemberLabelService {

    @Autowired
    private MemberLabelMapper memberLabelMapper;

    public void  addLable(MemberLabel memberLabel){
        memberLabelMapper.insert(memberLabel);
    }


    public  MemberLabel getById(long id){
        return  memberLabelMapper.selectByPrimaryKey(id);
    }

    public void deleteLable(long id){
       memberLabelMapper.deleteByPrimaryKey(id);
   }

    public void  updateLable(MemberLabel memberLabel){
       memberLabelMapper.updateByPrimaryKeySelective(memberLabel);
    }


    public  int countByExample(MemberLabelExample example){
        return  memberLabelMapper.countByExample(example);
    }

    public  List<MemberLabel> selectByExample(MemberLabelExample example) {
        return memberLabelMapper.selectByExample(example);
    }


    public  MemberLabel getByLabelName(String name){
        return  memberLabelMapper.getByLabelName(name);
    }



    public  List<MemberLabelViewDto> getLabelList(MemberLabelQueryDto queryDto){
        return  memberLabelMapper.getLabelList(queryDto);
    }


    public  int getMemberLabelCount(long id){
        return  memberLabelMapper.getMemberLabelCount(id);
    }


}
