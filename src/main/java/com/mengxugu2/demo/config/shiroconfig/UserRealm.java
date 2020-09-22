package com.mengxugu2.demo.config.shiroconfig;


import com.mengxugu2.demo.entities.User;
import com.mengxugu2.demo.mapper.UserMapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

/**
 * @program: demo
 * @description:
 * @author: tgw
 * @create: 2020-09-20 17:51
 */
public class UserRealm  extends AuthorizingRealm {
    @Autowired
    UserMapper userMapper;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("tgw1-doGetAuthorizationInfo");
        return null;
    }
   //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("tgw2-doGetAuthenticationInfo");
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String  principal = (String) token.getPrincipal();
        User user = userMapper.getUserByUsername(principal);
        //if(null==user.getUsername())
        if(!ObjectUtils.isEmpty(user))
        {
            return new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(), ByteSource.Util.bytes(user.getSalt()),this.getName());
        }

        return null;
    }
}
