//package com.linkpets.config;
//
//
//import com.linkpets.security.properties.ClientProperties;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
//
///**
// * @author Xie Chenxi
// * @date 2019-03-29 11:20
// */
//@Configuration
//public class OAuth2ServerConfig {
//
//    @Configuration
//    @EnableAuthorizationServer
//    protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
//
//        @Autowired
//        AuthenticationManager authenticationManager;
//        @Autowired
//        RedisConnectionFactory redisConnectionFactory;
//
//
//        @Override
//        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//
//            String finalSecret = "{bcrypt}" + new BCryptPasswordEncoder().encode(ClientProperties.CLIENT_SECRET);
//            //配置客户端,用于password认证
//            clients.inMemory()
//                    .withClient(ClientProperties.CLIENT_ID)
//                    .authorizedGrantTypes(ClientProperties.GRANT_TYPES)
//                    .scopes(ClientProperties.SCOPES)
//                    .authorities(ClientProperties.AUTHORITIES)
//                    .secret(finalSecret);
//        }
//
//        @Override
//        public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
//            endpoints
//                    .tokenStore(new RedisTokenStore(redisConnectionFactory))
//                    .authenticationManager(authenticationManager)
//                    .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
//        }
//
//        @Override
//        public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
//            //允许表单认证
//            oauthServer.allowFormAuthenticationForClients();
//        }
//
//    }
//
//}
