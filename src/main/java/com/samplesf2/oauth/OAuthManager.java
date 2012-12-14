package com.samplesf2.oauth;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.methods.PostMethod;

import com.britesnow.snow.util.JsonUtil;
import com.samplesf2.util.Client;

public class OAuthManager {

    private static final String SF_CLIENT_ID        = "3MVG9rFJvQRVOvk79z9ValrBxtVe_HK2ixMMOU2_e0cB_CEDCqwYh3cOyFFfspBomQbEf5IIg7NtfQsXR22Bf";
    private static final String SF_CLIENT_SEC       = "6340786704019104633";
    private static final String SF_CALLBACK_URL  = "https://login.salesforce.com/services/oauth2/callback";
    private static final String SF_AUTHORIZE_URL = "https://login.salesforce.com/services/oauth2/authorize?response_type=code&client_id=" + SF_CLIENT_ID
                                                                         + "&client_serect="
                                                                         + SF_CLIENT_SEC
                                                                         + "&redirect_uri="+SF_CALLBACK_URL;
    private static final String SF_TOKEN_URL     = "https://login.salesforce.com/services/oauth2/token";

    public String authorize(String service) {
        return SF_AUTHORIZE_URL;
    }

    public AccessToken getToken(String code, String service) {

        PostMethod postMethod = null;
        postMethod = new PostMethod(SF_TOKEN_URL);
        postMethod.addParameter("client_id", SF_CLIENT_ID);
        postMethod.addParameter("client_secret", SF_CLIENT_SEC);
        postMethod.addParameter("grant_type", "authorization_code");
        postMethod.addParameter("redirect_uri", SF_CALLBACK_URL);
        code = URLDecoder.decode(code);
        postMethod.addParameter("code", code);
        postMethod.addParameter("format", "json");
        String response = Client.send(postMethod);
        JSONObject opts = (JSONObject) JsonUtil.toMapAndList(response);
        AccessToken accessToken = new AccessToken();
        accessToken.setAccessToken(opts.getString("access_token"));
        accessToken.setExpireIn(opts.getString("issued_at"));
        return accessToken;
    }


    public Map getMapByQueryString(String queryString) {
        Map map = new HashMap();
        String[] params = queryString.split("&");
        for (String entry : params) {
            if (entry != null && !entry.equals("")) {
                String[] keyValues = entry.split("=");
                if (keyValues[0] != null && !keyValues[0].equals("") && keyValues.length == 2 && keyValues[1] != null) {
                    map.put(keyValues[0], keyValues[1]);
                }
            }
        }

        return map;
    }

}
