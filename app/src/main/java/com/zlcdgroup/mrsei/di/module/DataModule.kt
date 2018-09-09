package com.zlcdgroup.mrsei.di.module

import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import com.zlcdgroup.mrsei.data.db.dao.DaoSession
import com.zlcdgroup.mrsei.data.db.dao.UserEntityDao
import com.zlcdgroup.mrsei.data.db.help.DbCore
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @ Description:
 * @author king
 * @ Date 2018/9/4 11:23
 * @version V1.0
 */

@Module
abstract class DataModule {

    @Module(includes = arrayOf(DataModule ::class))
    class DataProvidesModule {
        @Singleton
        @Provides
        fun  getDaoSession():DaoSession{
            return   DbCore.getDaoSession()
        }

        @Singleton
        @Provides
        fun  getUserDao(daoSession: DaoSession):UserEntityDao{
            return daoSession.userEntityDao
        }
    }





}