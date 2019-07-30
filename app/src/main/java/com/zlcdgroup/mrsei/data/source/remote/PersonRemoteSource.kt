package com.zlcdgroup.mrsei.data.source.remote

import com.akingyin.base.call.ApiCallBack
import com.akingyin.base.net.config.CommonConstants.imei
import com.akingyin.base.rx.RxUtil
import com.akingyin.base.rx.retryWithDelay
import com.zlcdgroup.mrsei.data.entity.PersonEntity
import com.zlcdgroup.mrsei.data.source.IPersonSource
import com.zlcdgroup.mrsei.data.source.remote.api.LoginServerApi
import com.zlcdgroup.mrsei.data.source.remote.model.LoginResultModel
import com.zlcdgroup.mrsei.utils.RQ
import io.reactivex.disposables.Disposable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Inject



/**
 * @ Description:
 * @author king
 * @ Date 2018/10/8 15:15
 * @version V1.0
 */
class PersonRemoteSource @Inject constructor()  : IPersonSource{


    @Inject
    lateinit var  okHttpClient: OkHttpClient

    @Inject
    lateinit var  retrofits: Retrofit

    override fun getAllPersons(): List<PersonEntity> {
        return emptyList()
    }

    override fun delectOutTowMonth() {
    }

    override fun savePerson(personEntity: PersonEntity) {
    }

    override fun delectPerson(personEntity: PersonEntity) {
    }

    override fun getLastPerson(): PersonEntity? {
        return null
    }

     var disposable :Disposable?=null
    override fun login(name: String, pass: String, callBack: ApiCallBack<LoginResultModel>) {
        try {
            val   serverApi = retrofits.create(LoginServerApi::class.java)
           // val  serverApi = RetrofitUtils.createApi(LoginServerApi::class.java,OkHttpUtils.getOkHttpClientBuilder().build(),ApiHost.getHttp())
            val dataMap =  mutableMapOf<String ,String>()
            dataMap["account"] = name
            dataMap["password"] = pass

            val json = RQ.getJsonData("zlcd_mrmsei_login", "", imei, dataMap.toMap())
            disposable =   serverApi.login(json,RQ.getToken(json)).retryWithDelay(2,3).compose(RxUtil.IO_Main())
                  .subscribe({
                     if(it.status == 0){
                      callBack.call(it.data)
                  }else{
                      callBack.onError(it.msg)
                  }
                  },{
                      it.printStackTrace()
                      callBack.onError(it.message)
                  })

        }catch (e:Exception){
            e.printStackTrace()
            callBack.onError("出错了"+e.message)
        }

    }

    override fun logOut() {
    }

    override fun cancelSubscribe() {
        if(null != disposable && !disposable!!.isDisposed){
            disposable!!.dispose()
        }
    }
}