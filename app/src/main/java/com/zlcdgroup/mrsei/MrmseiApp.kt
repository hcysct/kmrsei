package com.zlcdgroup.mrsei

import android.content.Context
import android.os.Build
import android.text.TextUtils
import android.widget.Toast
import androidx.multidex.MultiDex
import com.akingyin.base.BaseApp
import com.akingyin.base.ext.Ext
import com.akingyin.base.ext.spGetString
import com.akingyin.base.ext.spSetString
import com.akingyin.base.net.mode.ApiHost
import com.akingyin.base.net.okhttp.OkHttpUtils
import com.akingyin.map.BdMapApp
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.ConvertUtils
import com.blankj.utilcode.util.Utils
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import com.umeng.commonsdk.UMConfigure
import com.zlcdgroup.mrsei.data.db.help.DbCore
import com.zlcdgroup.mrsei.di.component.DaggerAppComponent
import com.zlcdgroup.mrsei.di.module.ClientModule
import com.zlcdgroup.mrsei.di.module.GlobalConfigModule
import com.zlcdgroup.mrsei.utils.RetrofitConfig
import com.zlcdgroup.mrsei.utils.ThemeHelper
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit


/**
 * @ Description:
 * @author king
 * @ Date 2018/9/3 17:27
 * @version V1.0
 */
class MrmseiApp :BaseApp() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val  configmodel : GlobalConfigModule.GlobalProvideModule =GlobalConfigModule.Builder().okhttpConfiguration(object :ClientModule.ClientProvideModule.OkhttpConfiguration{
            override fun configOkhttp(context: Context, builder: OkHttpClient.Builder) {
                println("configOkhttp")
                builder.addInterceptor(OkHttpUtils.httpLoggingInterceptor)

            }
        }).retrofitConfiguration(object :ClientModule.ClientProvideModule.RetrofitConfiguration{
            override fun configRetrofit(context: Context, builder: Retrofit.Builder) {
              builder.baseUrl(ApiHost.getHost())
              println("configRetrofit")
            }
        }).addInterceptor(HttpLoggingInterceptor())
                .build()

       return  DaggerAppComponent.builder().applicationContext(this).application(this).globalConfigModule(configmodel).build()
    }

    override fun onCreate() {
        super.onCreate()

        Ext.with(this)
        DbCore.init(this)
        Utils.init(this)
        println("px=${ConvertUtils.dp2px(100F)}")
        DbCore.enableQueryBuilderLog()
        BdMapApp.get().initBaiDuMap(this)
        registerReceiver(BdMapApp.get().receiver,BdMapApp.get().getiFilter())
        spSetString("ApiUrl","http://114.215.108.130:38280/mrmsei/")
        if(BuildConfig.DEBUG){
            ARouter.openLog()
            ARouter.openDebug()
            val formatStrategy = PrettyFormatStrategy.newBuilder()
                    .showThreadInfo(false)  //是否选择显示线程信息，默认为true
                    .methodCount(0)         //方法数显示多少行，默认2行
                    .methodOffset(5)        //隐藏方法内部调用到偏移量，默认5
                    .tag("AndroidDev")        //自定义TAG全部标签，默认PRETTY_LOGGER
                    .build()
            Logger.addLogAdapter(AndroidLogAdapter(formatStrategy))
        }
        ARouter.init(this)
        showDebugDBAddressLogToast(this)
        ApiHost.setHost("http://114.215.108.130:38280/mrmsei/")
        RetrofitConfig.getDefaultService()
        val  theme = spGetString("themePref")
        println("theme=$theme")
        if(TextUtils.isEmpty(theme)){
            ThemeHelper.applyTheme(ThemeHelper.DEFAULT_MODE)
        }else{
            ThemeHelper.applyTheme(theme)
        }

        Constants.MODEL = Build.MODEL
        UMConfigure.setLogEnabled(true)
        UMConfigure.init(this,UMConfigure.DEVICE_TYPE_PHONE,"5cd152274ca357112b000a24")

    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)

        MultiDex.install(base)

    }

    fun showDebugDBAddressLogToast(context: Context) {
        if (BuildConfig.DEBUG) {
            println("showDebugDBAddressLogToast")
            try {
                val debugDB = Class.forName("com.amitshekhar.DebugDB")
                val getAddressLog = debugDB.getMethod("getAddressLog")
                val value = getAddressLog.invoke(null)
                Toast.makeText(context, value as String, Toast.LENGTH_LONG).show()
            } catch (ignore: Exception) {
                ignore.printStackTrace()
            }

        }
    }

    override fun initInjection() {
     // applicationInjector()

    }

    override fun onTerminate() {
        super.onTerminate()
        unregisterReceiver(BdMapApp.get().receiver)
    }
}