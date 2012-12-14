package com.samplesf2.web;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;

import com.britesnow.snow.util.JsonUtil;
import com.britesnow.snow.web.RequestContext;
import com.britesnow.snow.web.handler.annotation.WebActionHandler;
import com.britesnow.snow.web.handler.annotation.WebModelHandler;
import com.britesnow.snow.web.param.annotation.WebModel;
import com.britesnow.snow.web.param.annotation.WebParam;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.samplesf2.dao.SocialdentityDao;
import com.samplesf2.entity.User;
import com.samplesf2.util.Client;

@Singleton
public class GGContactWebHandlers {
    
    private  static final String GG_URL = "http://www.google.com/m8/feeds/contacts/default/full";
    
    @Inject
    private SocialdentityDao socialdentityDao;
    
	@WebModelHandler(startsWith = "/listGGContacts")
	public void listGGContacts(@WebModel Map m,RequestContext rc) {
	    User user = rc.getUser(User.class);
	    String token = socialdentityDao.getSocialdentity(user.getId(), "GG").getToken();
	}
	
	@WebModelHandler(startsWith = "/getGGContact")
	public void getContact(@WebModel Map m,@WebParam("id") String id,RequestContext rc) {
	    User user = rc.getUser(User.class);
	    String token = socialdentityDao.getSocialdentity(user.getId(), "GG").getToken();
	}
	
	@WebActionHandler
	public Object saveGGContact(@WebModel Map m,@WebParam("id") String id,@WebParam("name") String name,RequestContext rc) {
	    User user = rc.getUser(User.class);
	    String token = socialdentityDao.getSocialdentity(user.getId(), "GG").getToken();
        return null;
	}
	
	@WebActionHandler
	public Object deleteGGContact(@WebModel Map m,@WebParam("id") String id,RequestContext rc) {
	    User user = rc.getUser(User.class);
        String token = socialdentityDao.getSocialdentity(user.getId(), "GG").getToken();
        return null;
	}
}
