package project.jinshang.mod_admin.mod_paymode.service;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import project.jinshang.mod_admin.mod_paymode.PayModeMapper;
import project.jinshang.mod_admin.mod_paymode.bean.PayMode;
import project.jinshang.mod_admin.mod_paymode.bean.PayModeExample;
import java.util.List;

/**
 * Created by Administrator on 2017/11/9.
 * 支付方式service
 */
@Service
public class PayModeService {

    @Autowired
    PayModeMapper payModeMapper;

    public void updatePayMode(PayMode payMode){

        payModeMapper.updateByPrimaryKeySelective(payMode);
    }

    public List<PayMode> loadAllPayMode(PayModeExample example){
        List<PayMode> list = payModeMapper.selectByExample(example);
        return list;
    }
}
