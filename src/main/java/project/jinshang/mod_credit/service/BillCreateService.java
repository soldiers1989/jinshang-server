package project.jinshang.mod_credit.service;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_credit.BillCreateMapper;
import project.jinshang.mod_credit.bean.BillCreate;
import project.jinshang.mod_credit.bean.BillCreateExample;

import java.util.List;

/**
 * create : wyh
 * date : 2017/12/18
 */

@Service
public class BillCreateService {

    @Autowired
    private BillCreateMapper billCreateMapper;

    public BillCreate getById(Integer id){
        return  billCreateMapper.selectByPrimaryKey(id);
    }


    public  void updateByPrimaryKeySelective(BillCreate billCreate){
        billCreateMapper.updateByPrimaryKeySelective(billCreate);
    }


    public  void  insertSelective(BillCreate billCreate){
        billCreateMapper.insertSelective(billCreate);
    }


    public  BillCreate getLast(Long memberid){
        return  billCreateMapper.getLast(memberid);
    }


    public List<String> getBillCreateTimeList(Long memberid, Integer count){
        return  billCreateMapper.getBillCreateTimeList(memberid,count);
    }




    /**
     * 将过期未缴清的设置为已过期状态
     */
    public  void setExpire(){
        billCreateMapper.setExpire();
    }

    /**
     * 将违约的授信账单设置为违约账单，并且填充违约金额
     */
    public  void  fillIllegalData(){
        billCreateMapper.fillIllegalData();
    }


    public List<BillCreate> selectByExample(BillCreateExample example){
        return billCreateMapper.selectByExample(example);
    }
}
