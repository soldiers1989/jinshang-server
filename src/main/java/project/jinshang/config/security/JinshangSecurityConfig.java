package project.jinshang.config.security;

import mizuki.project.core.restserver.config.security.SecurityConfig;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import project.jinshang.mod_member.bean.Admin;
import project.jinshang.mod_member.bean.Member;

import java.util.HashSet;
import java.util.Set;

/**
 * create : wyh
 * date : 2017/11/3
 */


public class JinshangSecurityConfig extends SecurityConfig {

    public static void authAdminStore(Admin admin){
        /* 每次请求这里都要执行 每次都是新的 TODO */
        /* security 作用于当前线程 */
        Set<GrantedAuthority> authorities = new HashSet<>();

        String[] roles = (String[]) admin.getRoles();
        if(roles != null) {
            for (String p : roles) {
                authorities.add(new SimpleGrantedAuthority(p));
            }
        }
        Authentication req = new UsernamePasswordAuthenticationToken(admin.getId(), admin.getPassword(),authorities);
        SecurityContextHolder.getContext().setAuthentication(req);
    }



    public static void authMemberStore(Member member){
        /* 每次请求这里都要执行 每次都是新的 TODO */
        /* security 作用于当前线程 */
        Set<GrantedAuthority> authorities = new HashSet<>();

        String[] roles = (String[]) member.getMenu();

        if(roles != null) {
            for (String p : roles) {
                authorities.add(new SimpleGrantedAuthority(p));
            }
        }
        Authentication req = new UsernamePasswordAuthenticationToken(member.getId(), member.getPassword(),authorities);
        SecurityContextHolder.getContext().setAuthentication(req);
    }




}
