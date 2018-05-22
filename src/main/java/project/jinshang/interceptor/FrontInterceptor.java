package project.jinshang.interceptor;

import com.google.code.kaptcha.Constants;
import mizuki.project.core.restserver.config.BasicRet;
import mizuki.project.core.restserver.util.JsonUtil;
import mizuki.project.core.restserver.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.constant.SellerAuthorityConst;
import project.jinshang.common.utils.CookieUtils;
import project.jinshang.common.utils.GsonUtils;
import project.jinshang.common.utils.RedisUtils;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.config.security.JinshangSecurityConfig;
import project.jinshang.mod_company.bean.SellerCompanyInfo;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_member.bean.MemberToken;
import project.jinshang.mod_member.service.MemberService;
import project.jinshang.mod_member.service.MemberTokenService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * create : wyh
 * date : 2017/11/3
 */
@SessionAttributes({AppConstant.MEMBER_SESSION_NAME})
public class FrontInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberTokenService memberTokenService;

    @Autowired
    private RedisUtils redisUtils;


    @Autowired
    private SessionRepository repository;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(true);

        //ResponseUtil.setCross(response,request);

        Member member = (Member) session.getAttribute(AppConstant.MEMBER_SESSION_NAME);

        //解决Safari浏览器获取不到session
        String webToken = request.getParameter("webToken");
        if(member == null && StringUtils.hasText(webToken) && !webToken.equals("null") && !webToken.equals("undefined") ){
            Session session1 = repository.getSession(webToken);
            if(session1 != null) {
                //
                member = session1.getAttribute(AppConstant.MEMBER_SESSION_NAME);
                session.setAttribute(AppConstant.MEMBER_SESSION_NAME, member);

                //
                String imgcode =  session1.getAttribute(Constants.KAPTCHA_SESSION_KEY);
                session.setAttribute(Constants.KAPTCHA_SESSION_KEY,imgcode);
            }
        }


        if(member == null){
            String token =  CookieUtils.getValue(request.getCookies(),AppConstant.WAP_TOKEN_COOKIE_NAME);

//            String waptoken = request.getHeader("token");
//            if(StringUtils.hasText(waptoken) && !StringUtils.hasText(token)){
//                token = waptoken;
//            }

            if(token != null && !"".equals(token)){
                MemberToken memberToken =  memberTokenService.getByToken(token);
                if(memberToken != null){
                    member =  memberService.getMemberById(memberToken.getMemberid());
                    if(member != null) {
                        member.setLoginType(memberToken.getLogintype());

                        memberService.fillMember(member);
                        session.setAttribute(AppConstant.MEMBER_SESSION_NAME,member);

                        Member updateDateMember = new Member();
                        updateDateMember.setId(member.getId());
                        updateDateMember.setLastlogindate(new Date());
                        memberService.updateByPrimaryKeySelective(updateDateMember);
                    }
                }
            }
        }

            return true;
        }
}
