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
public class SFContactWebHandlers {
    
    private  static final String SF_URL = "https://na14.salesforce.com/services/data/v26.0";
    
    @Inject
    private SocialdentityDao socialdentityDao;
    
	@WebModelHandler(startsWith = "/listSFContacts")
	public void listSFContacts(@WebModel Map m,RequestContext rc) {
	    User user = rc.getUser(User.class);
	    String token = socialdentityDao.getSocialdentity(user.getId(), "SF").getToken();
	    String url = SF_URL+"/query?";
	    String sql = "SELECT Id, Name FROM Contact LIMIT 1000";
	    sql = URLEncoder.encode(sql);
	    String params = "q="+sql;
	    HttpMethod method = new GetMethod(url+params);
	    method.addRequestHeader("Authorization", "OAuth "+token);
	    method.addRequestHeader("X-PrettyPrint", "1");
	    String response = Client.send(method);
	    JSONObject opts = (JSONObject) JsonUtil.toMapAndList(response);
	    m.put("result", opts.get("records"));
	}
	
	@WebModelHandler(startsWith = "/getSFContact")
	public void getContact(@WebModel Map m,@WebParam("id") String id,RequestContext rc) {
	    User user = rc.getUser(User.class);
	    String token = socialdentityDao.getSocialdentity(user.getId(), "SF").getToken();
	    String url = SF_URL+"/query?";
	    String sql = "SELECT Id, Name FROM Contact WHERE Id='"+id+"'";
	    sql = URLEncoder.encode(sql);
	    String params = "q="+sql;
	    HttpMethod method = new GetMethod(url+params);
	    method.addRequestHeader("Authorization", "OAuth "+token);
	    method.addRequestHeader("X-PrettyPrint", "1");
	    String response = Client.send(method);
	    JSONObject opts = (JSONObject) JsonUtil.toMapAndList(response);
	    JSONArray array = (JSONArray) opts.get("records");
	    m.put("result", array.get(0));
	}
	
	@WebActionHandler
	public Object saveSFContact(@WebModel Map m,@WebParam("id") String id,@WebParam("name") String name,RequestContext rc) {
	    User user = rc.getUser(User.class);
	    String token = socialdentityDao.getSocialdentity(user.getId(), "SF").getToken();
	    Map map = new HashMap();
	    map.put("lastName", name);
	    String url = null;
	    PostMethod method = null;
	    if(id != null && id.length() > 0){
	        url = SF_URL+"/sobjects/Contact/"+id;
            method = new PostMethod(url+"?_HttpMethod=PATCH");
	    }else{
	        url = SF_URL+"/sobjects/Contact/";
	        method = new PostMethod(url);
	    }
	    method.addRequestHeader("Authorization", "OAuth "+token);
        method.addRequestHeader("X-PrettyPrint", "1");
        method.addRequestHeader("Content-Type","application/json");
        try {
            method.setRequestEntity(new StringRequestEntity(JSONObject.fromObject(map).toString(),
                "application/json", "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try{
            Client.send(method);
        }catch(Exception e){
            
        }
        return null;
	}
	
	@WebActionHandler
	public Object deleteSFContact(@WebModel Map m,@WebParam("id") String id,RequestContext rc) {
	    User user = rc.getUser(User.class);
        String token = socialdentityDao.getSocialdentity(user.getId(), "SF").getToken();
        String url = null;
        PostMethod method = null;
        if(id != null && id.length() > 0){
            url = SF_URL+"/sobjects/Contact/"+id;
            method = new PostMethod(url+"?_HttpMethod=DELETE");
            method.addRequestHeader("Authorization", "OAuth "+token);
            method.addRequestHeader("X-PrettyPrint", "1");
            try{
                Client.send(method);
            }catch(Exception e){
                
            }
        }
        return null;
	}
}
