package com.linkpets.security.properties;

/**
 * @author Xie Chenxi
 * @date 2019-03-29 13:49
 */
public interface ClientProperties {
    String RESOURCE_ID = "lpAuth";
    String CLIENT_ID = "lpAuth";
    String CLIENT_SECRET = "lpAuth";
    String[] GRANT_TYPES = {"password", "refresh_token"};
    String[] SCOPES = {"read", "write"};
    String AUTHORITIES = "oauth2";
}
