package project.jinshang.mod_product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_product.AdminOperateLogMapper;
import project.jinshang.mod_product.bean.AdminOperateLog;

@Service
public class AdminOperateLogService {
    @Autowired
    private AdminOperateLogMapper adminOperateLogMapper;

    /**
     * 保存管理员操作日志
     * @param adminOperateLog
     */
    public void saveAdminOperateLog(AdminOperateLog adminOperateLog){
        adminOperateLogMapper.insertSelective(adminOperateLog);
    }
}
