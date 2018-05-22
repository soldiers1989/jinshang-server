package project.jinshang.interceptor;

import mizuki.project.core.restserver.config.BasicRet;
import mizuki.project.core.restserver.util.JsonUtil;
import mizuki.project.core.restserver.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.constant.SellerAuthorityConst;
import project.jinshang.common.utils.CookieUtils;
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
public class MemberInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberTokenService memberTokenService;




    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(true);

        Member member = (Member) session.getAttribute(AppConstant.MEMBER_SESSION_NAME);


//        if(member == null){
//            String token =  CookieUtils.getValue(request.getCookies(),AppConstant.WAP_TOKEN_COOKIE_NAME);
//            if(token != null && !"".equals(token)){
//                MemberToken memberToken =  memberTokenService.getByToken(token);
//                if(memberToken != null){
//                    member =  memberService.getMemberById(memberToken.getMemberid());
//                    if(member != null) {
//                        member.setLoginType(memberToken.getLogintype());
//
//                        memberService.fillMember(member);
//                        session.setAttribute(AppConstant.MEMBER_SESSION_NAME,member);
//
//
//                        Member updateDateMember = new Member();
//                        updateDateMember.setId(member.getId());
//                        updateDateMember.setLastlogindate(new Date());
//                        memberService.updateByPrimaryKeySelective(updateDateMember);
//                    }
//                }
//            }
//        }


        if(member==null){
            ResponseUtil.setCross(response,request);
            response.getWriter().print(JsonUtil.toJson(new BasicRet(BasicRet.TOKEN_ERR,"token error")));
            return false;
        }else{
            String uri = request.getRequestURI();
            if(uri.startsWith("/jinshang-server/rest/seller") &&            //卖家用户，判断是否开通了卖家
                    !uri.startsWith("/jinshang-server/rest/seller/applyToShop")
               ){
                SellerCompanyInfo sellerCompanyInfo =  member.getSellerCompanyInfo();
                if(sellerCompanyInfo == null){
                    ResponseUtil.setCross(response,request);
                    response.getWriter().print(JsonUtil.toJson(new BasicRet(BasicRet.TOKEN_ERR,"未开通卖家权限")));
                    return  false;
                }


                if(sellerCompanyInfo.getValidate() != Quantity.STATE_1){
                    ResponseUtil.setCross(response,request);
                    String message = "";
                    if(sellerCompanyInfo.getValidate() == Quantity.STATE_0){
                        message =  "卖家开店申请处于审核状态";
                    }else if(sellerCompanyInfo.getValidate() == Quantity.STATE_2){
                        message =  "卖家开店申请审核失败，请重新提交资料";
                    }else if(sellerCompanyInfo.getValidate() == Quantity.STATE_3){
                        message =  "卖家已删除";
                    }
                    response.getWriter().print(JsonUtil.toJson(new BasicRet(BasicRet.ERR,message)));
                    return  false;
                }


                if(sellerCompanyInfo.getShopstate() == Quantity.STATE_1){
                    ResponseUtil.setCross(response,request);
                    response.getWriter().print(JsonUtil.toJson(new BasicRet(BasicRet.ERR,"卖家店铺处于关闭状态")));
                    return  false;
                }

                if(member.getLoginType().equals("main")){
                    member.setMenu(new String[]{SellerAuthorityConst.ALL});
                }
                JinshangSecurityConfig.authMemberStore(member);

            }else if(uri.startsWith("/jinshang-server/rest/buyer")){
                if(!"main".equals(member.getLoginType())){
                    ResponseUtil.setCross(response,request);
                    response.getWriter().print(JsonUtil.toJson(new BasicRet(BasicRet.ERR,"子帐号没有登录买家帐号的权限")));
                    return  false;
                }
            }

            return true;
        }
    }

}
