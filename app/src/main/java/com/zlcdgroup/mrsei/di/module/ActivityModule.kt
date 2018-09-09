package com.zlcdgroup.mrsei.di.module

import android.app.Activity
import android.content.Context
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import com.zlcdgroup.mrsei.di.qualifier.ActivityContext
import com.zlcdgroup.mrsei.di.scope.PerActivity
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * @ Description:
 * @author king
 * @ Date 2018/9/3 18:15
 * @version V1.0
 *
 * dagger2 里 如果@Model 注解的是抽象类时 @provides 标注的必须是静态方法
A @Module may not contain both non-static @Provides methods and abstract @Binds or @Multibinds declarations

java 方式这样写没有问题 而我们知道Kotlin 是没有static 标记显示是静态方法 需要写companion object 来包含静态成员

暂时能做到的方式就是避免 @Provides 不要写在抽象类里 把@Binds标记的抽象方法 写在另一个类里 然后 再用非抽象类 include 进来 如下
 */

@Module
abstract class ActivityModule {



    @Binds
    @ActivityContext
     abstract fun bindActivityContext(activity: AppCompatActivity): Context


    @Module(includes = arrayOf(ActivityModule ::class))
    class ApplicationProvidesModule {
        @Singleton
        @Provides
        fun provideContent(activity: AppCompatActivity):FragmentManager{
            return activity.supportFragmentManager
        }
    }


//    companion object {
//
//     @Provides
//     @PerActivity
//     fun activityFragmentManager(activity: AppCompatActivity): FragmentManager {
//        return activity.supportFragmentManager
//     }
//    }

//
//    @Provides
//    @PerActivity
//    fun activityFragmentManager(activity: AppCompatActivity): FragmentManager {
//        return activity.supportFragmentManager
//    }
}