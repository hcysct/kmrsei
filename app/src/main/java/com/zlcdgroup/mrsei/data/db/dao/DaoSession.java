package com.zlcdgroup.mrsei.data.db.dao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.zlcdgroup.mrsei.data.entity.ImageTextEntity;
import com.zlcdgroup.mrsei.data.entity.PersonEntity;
import com.zlcdgroup.mrsei.data.entity.UserEntity;

import com.zlcdgroup.mrsei.data.db.dao.ImageTextEntityDao;
import com.zlcdgroup.mrsei.data.db.dao.PersonEntityDao;
import com.zlcdgroup.mrsei.data.db.dao.UserEntityDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig imageTextEntityDaoConfig;
    private final DaoConfig personEntityDaoConfig;
    private final DaoConfig userEntityDaoConfig;

    private final ImageTextEntityDao imageTextEntityDao;
    private final PersonEntityDao personEntityDao;
    private final UserEntityDao userEntityDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        imageTextEntityDaoConfig = daoConfigMap.get(ImageTextEntityDao.class).clone();
        imageTextEntityDaoConfig.initIdentityScope(type);

        personEntityDaoConfig = daoConfigMap.get(PersonEntityDao.class).clone();
        personEntityDaoConfig.initIdentityScope(type);

        userEntityDaoConfig = daoConfigMap.get(UserEntityDao.class).clone();
        userEntityDaoConfig.initIdentityScope(type);

        imageTextEntityDao = new ImageTextEntityDao(imageTextEntityDaoConfig, this);
        personEntityDao = new PersonEntityDao(personEntityDaoConfig, this);
        userEntityDao = new UserEntityDao(userEntityDaoConfig, this);

        registerDao(ImageTextEntity.class, imageTextEntityDao);
        registerDao(PersonEntity.class, personEntityDao);
        registerDao(UserEntity.class, userEntityDao);
    }
    
    public void clear() {
        imageTextEntityDaoConfig.clearIdentityScope();
        personEntityDaoConfig.clearIdentityScope();
        userEntityDaoConfig.clearIdentityScope();
    }

    public ImageTextEntityDao getImageTextEntityDao() {
        return imageTextEntityDao;
    }

    public PersonEntityDao getPersonEntityDao() {
        return personEntityDao;
    }

    public UserEntityDao getUserEntityDao() {
        return userEntityDao;
    }

}
