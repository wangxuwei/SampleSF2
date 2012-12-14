package com.samplesf2.dao;


import com.samplesf2.entity.BaseEntity;

// DO NOT PUT SINGLETON ON THIS ONE, we need to have one instance per Entity
public class GenericDao extends BaseHibernateDao<BaseEntity> {

    public void setPersistentClass(Class persitentClass) {
        this.persistentClass = persitentClass;
    }
}
