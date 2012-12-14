package com.samplesf2.web;

import java.util.Date;
import java.util.Map;

import com.britesnow.snow.web.RequestContext;
import com.britesnow.snow.web.handler.annotation.WebActionHandler;
import com.britesnow.snow.web.handler.annotation.WebModelHandler;
import com.britesnow.snow.web.param.annotation.WebModel;
import com.britesnow.snow.web.param.annotation.WebParam;
import com.britesnow.snow.web.param.annotation.WebUser;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.samplesf2.dao.SocialdentityDao;
import com.samplesf2.entity.Socialdentity;
import com.samplesf2.entity.User;
import com.samplesf2.oauth.OAuthManager;

@Singleton
public class OAuthWebHandlers {
    @Inject
    private SocialdentityDao socialdentityDao;
	@Inject
	private OAuthManager oauthManager;

	@WebModelHandler(startsWith = "/authorize")
	public void authorize(@WebModel Map m,@WebParam("service") String service,RequestContext rc) {
		try {
			String authorizationUrl = oauthManager.authorize(service);
			rc.getRes().sendRedirect(authorizationUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@WebActionHandler
	public void setToken(@WebModel Map m,@WebUser User user,@WebParam("params") String params,@WebParam("service") String service, RequestContext rc) {
	    Map map = oauthManager.getMapByQueryString(params);
	    Long userId = user.getId();
        String code = (String) map.get("code");
        String accessToken = null;
        try {
            accessToken = oauthManager.getToken(code, service).getAccessToken();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Socialdentity socialdentity = socialdentityDao.getSocialdentity(userId, service);
        if(socialdentity == null){
            socialdentity = new Socialdentity();
            socialdentity.setUser_id(userId);
            socialdentity.setService(service);
        }
        socialdentity.setTokenDate(new Date());
        socialdentity.setToken(accessToken);
        socialdentityDao.save(socialdentity);
	}
}
