package com.samplesf2.dao;

import com.samplesf2.entity.Socialdentity;

public class SocialdentityDao extends BaseHibernateDao<Socialdentity> {

    public Socialdentity getSocialdentity(Long user_id,String service) {
        Socialdentity socialdentity = (Socialdentity) daoHelper.findFirst("from Socialdentity u where u.service = ? and u.user_id = ? ", service,user_id);
        return socialdentity;
    }
    
}
