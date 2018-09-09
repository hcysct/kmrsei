package com.akingyin.base

import android.content.Context
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import com.classic.common.MultipleStatusView
import es.dmoral.toasty.Toasty

/**
 * @ Description:
 * @author king
 * @ Date 2018/9/7 16:03
 * @version V1.0
 */
abstract class SimpleActivity : AppCompatActivity() ,IBaseView{

    protected   var multipleStatusView : MultipleStatusView?=null
    // Log tag
    protected var TAG_LOG: String? = null


    protected var mContext: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        initInjection()
        super.onCreate(savedInstanceState)
        AppManager.getInstance()!!.addActivity(this)
        TAG_LOG = this.localClassName
        setContentView(getLayoutId())
        mContext = this

        initializationData(savedInstanceState)
        initView()
        startRequest()
        initStatusViewListion()

    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        onSaveInstanceData(outState)
    }

    protected fun setToolBar(toolbar: Toolbar, title: String) {
        toolbar.setTitle(title)

        setSupportActionBar(toolbar)
        if (null != supportActionBar) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setDisplayShowHomeEnabled(true)
            toolbar.setNavigationOnClickListener(View.OnClickListener { onBackPressed() })
        }

    }

    /**
     * dagger2注入
     */
    abstract   fun   initInjection()

    private   fun   initStatusViewListion(){
        multipleStatusView?.setOnClickListener(mRetryClickListener)
    }

    open   var  mRetryClickListener : View.OnClickListener = View.OnClickListener {
        startRequest()
    }


    /**
     * 加载布局
     */
    @LayoutRes
    abstract    fun    getLayoutId():Int

    /**
     * 初始化数据
     */
    abstract    fun    initializationData(savedInstanceState : Bundle?)


    /**
     * 保存当前状态
     */
    abstract   fun    onSaveInstanceData(outState: Bundle?)


    /**
     * 初始化View
     */
    abstract    fun    initView()


    /**
     * 开始请求
     */
    abstract    fun     startRequest()


    /**
     * 打开软键盘
     */
    fun   openKeyBord(mEditText: EditText, mContext: Context){
        var imm = mContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN)
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)

    }

    /**
     * 关阀软键盘
     */
    fun   closeKeyBord(mEditText: EditText, mContext: Context){
        val   imm  = mContext.getSystemService(Context.INPUT_METHOD_SERVICE)  as InputMethodManager
        imm.hideSoftInputFromWindow(mEditText.windowToken,0)
    }

    override fun showMessage(msg: String?) {
        if (msg != null) {
            Toasty.info(this,msg, Toast.LENGTH_SHORT).show()
        }
    }

    override fun showSucces(msg: String?) {
        if (msg != null) {
            Toasty.success(this,msg, Toast.LENGTH_SHORT).show()
        }
    }

    override fun showError(msg: String?) {
        if (msg != null) {
            Toasty.error(this,msg, Toast.LENGTH_SHORT).show()
        }
    }

    override fun showWarning(msg: String?) {
        if (msg != null) {
            Toasty.warning(this,msg, Toast.LENGTH_SHORT).show()
        }
    }

    override fun close() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showTips(msg: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLoadDialog(msg: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoadDialog() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun dismissLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun onDestroy() {
        AppManager.getInstance()?.finishActivity(this)
        super.onDestroy()
    }

}