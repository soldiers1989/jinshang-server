package project.jinshang.interceptor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.springframework.session.web.http.HttpSessionStrategy;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.utils.GsonUtils;
import project.jinshang.common.utils.RedisUtils;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_member.bean.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@SessionAttributes({AppConstant.MEMBER_SESSION_NAME})
public class SessionInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private RedisUtils redisUtils;


    @Autowired
    private SessionRepository repository;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {




//        System.out.println("sessionid:"+request.getSession().getId());
//        response.addHeader("P3P", "CP=CAO PSA OUR");
//        response.addHeader("P3P", "CP=IDC DSP COR CURa ADMa OUR IND PHY ONL COM STA");
        response.setHeader("P3P","CP=CAO OUR IDC DSP COR ADM DEVi TAIi PSA PSD IVAi IVDi CONi HIS OUR IND CNT");



        return  true;
    }

}
