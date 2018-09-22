package project.jinshang.mod_admin.mod_returnreason.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_admin.mod_returnreason.ReturnReasonMapper;
import project.jinshang.mod_admin.mod_returnreason.bean.ReturnReason;
import project.jinshang.mod_admin.mod_returnreason.bean.ReturnReasonExample;

import java.util.List;
import java.util.Map;

@Service
public class ReturnReasonService {
    @Autowired
    private ReturnReasonMapper returnReasonMapper;

    public long countByExample(ReturnReasonExample returnReasonExample) {
        return  returnReasonMapper.countByExample(returnReasonExample);
    }

    public void insertReturnReasonInfo(ReturnReason returnReason) {
        returnReasonMapper.insertSelective(returnReason);
    }

    public ReturnReason getDetailById(Long id) {
        return  returnReasonMapper.selectByPrimaryKey(id);
    }

    public void updateReturnReasonInfo(ReturnReason returnReason) {
        returnReasonMapper.updateByPrimaryKeySelective(returnReason);
    }

    public void deleteReturnReasonById(Long id) {
        returnReasonMapper.deleteByPrimaryKey(id);
    }

    public PageInfo getListByPage(int pageNo, int pageSize, ReturnReason returnReason) {
        if(pageNo != -1){
            PageHelper.startPage(pageNo, pageSize);
        }
        List<Map<String,Object>> list = returnReasonMapper.selectByObject(returnReason);

        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }


    public ReturnReason selectByReturnReason(String returnbackreason) {
        return  returnReasonMapper.selectByReturnReason(returnbackreason);
    }

    public PageInfo getListByPage1(int pageNo, int pageSize, ReturnReason returnReason) {
        if(pageNo != -1){
            PageHelper.startPage(pageNo, pageSize);
        }
        List<Map<String,Object>> list = returnReasonMapper.selectByObject1(returnReason);

        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }
}
