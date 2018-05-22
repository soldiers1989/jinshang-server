package project.jinshang.mod_product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_product.PdbailLogMapper;
import project.jinshang.mod_product.bean.PdbailLog;

/**
 * create : wyh
 * date : 2017/11/16
 */

@Service
public class PdbailLogService {

    @Autowired
    private PdbailLogMapper pdbailLogMapper;

    public  void  add(PdbailLog pdbailLog){
        pdbailLogMapper.insert(pdbailLog);
    }


    public  PdbailLog getLastLogByPdidType(long pdid,short type,long memberid){
        return  pdbailLogMapper.getLastLogByPdidType(pdid,type,memberid);
    }

}
