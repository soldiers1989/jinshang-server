package project.jinshang.interceptor;

import mizuki.project.core.restserver.config.BasicRet;
import mizuki.project.core.restserver.util.JsonUtil;
import mizuki.project.core.restserver.util.ResponseUtil;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.config.security.JinshangSecurityConfig;
import project.jinshang.mod_member.bean.Admin;
import project.jinshang.mod_member.bean.AdminGroup;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * create : wyh
 * date : 2017/11/3
 */
public class AdminInterceptor extends HandlerInterceptorAdapter {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute(AppConstant.ADMIN_SESSION_NAME);

        if(admin==null){
            ResponseUtil.setCross(response,request);
            response.getWriter().print(JsonUtil.toJson(new BasicRet(BasicRet.TOKEN_ERR,"token error")));
            return false;
        }else{
            JinshangSecurityConfig.authAdminStore(admin);
            return true;
        }
    }


}
