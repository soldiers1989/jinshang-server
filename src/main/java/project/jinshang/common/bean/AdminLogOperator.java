package project.jinshang.common.bean;

import org.springframework.stereotype.Component;
import project.jinshang.mod_member.bean.Admin;
import project.jinshang.mod_product.bean.AdminOperateLog;
import project.jinshang.mod_product.service.AdminOperateLogService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class AdminLogOperator {
    /**
     * 保存操作日志
     * @param admin
     * @param content
     * @param type
     * @param tablesname
     * @param request
     */
    public void saveAdminLog(Admin admin, String content,Short type,String tablesname, HttpServletRequest request, AdminOperateLogService adminOperateLogService){
        AdminOperateLog adminOperateLog = new AdminOperateLog();
        adminOperateLog.setContent(content);
        adminOperateLog.setType(type);
        adminOperateLog.setTablesname(tablesname);
        String ip = RequestIpGet.getIpAddr(request);
        adminOperateLog.setIpaddress(ip);
        if(admin!=null){
            adminOperateLog.setAdminid(admin.getId());
            adminOperateLog.setAdminname(admin.getUsername());
        }
        adminOperateLog.setCreatetime(new Date());
        adminOperateLogService.saveAdminOperateLog(adminOperateLog);
    }
}
