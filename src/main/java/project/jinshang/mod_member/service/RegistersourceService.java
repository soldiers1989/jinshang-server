package project.jinshang.mod_member.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_member.RegistersourceMapper;
import project.jinshang.mod_member.bean.Registersource;
import project.jinshang.mod_member.bean.RegistersourceExample;

import java.util.List;
import java.util.Map;

/**
 * create : zzy
 * date : 2018/07/03
 */
@Service
public class RegistersourceService {
    @Autowired
    private RegistersourceMapper registersourceMapper;

    public PageInfo selectAll(int pageNo, int pageSize, Registersource registersource) {
        if(pageNo != -1){
            PageHelper.startPage(pageNo, pageSize);
        }
        List<Map<String,Object>> list = registersourceMapper.selectAll(registersource);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    public void insertRegistersourceInfo(Registersource registersource) {
         registersourceMapper.insertSelective(registersource);
    }

    public Registersource selectById(long id) {
        return  registersourceMapper.selectByPrimaryKey(id);
    }

    public void updateRegistersourceInfo(Registersource registersource) {
        registersourceMapper.updateByPrimaryKeySelective(registersource);
    }

    public long countByExample(RegistersourceExample registersourceExample) {
        return  registersourceMapper.countByExample(registersourceExample);
    }
}
