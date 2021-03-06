package com.zlcdgroup.mrsei.ui.fragment

import android.os.Bundle
import com.akingyin.base.SimpleFragment
import com.zlcdgroup.mrsei.R
import kotlinx.android.synthetic.main.fragment_test.*

/**
 *
 * 在androidX 中fragment 只需要在onResume 做懒加载即可
 * @ Description:
 * @author king
 * @ Date 2020/1/10 14:43
 * @version V1.0
 */
class FragmentLayzTest : SimpleFragment() {

    override fun getLayoutId()= R.layout.fragment_test

    companion object{
        fun   newInstance(str:String):FragmentLayzTest{
            return FragmentLayzTest().apply {
                arguments = Bundle().apply {
                    putString("data",str)
                }
            }
        }
    }

    override fun initEventAndData() {
        tv_info.text = TAG
        println("initEventAndData->${arguments?.getString("data")}")
    }
    private  val   TAG  by  lazy {
        arguments?.getString("data")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("onCreate=${TAG}")

    }

    override fun onStart() {
        super.onStart()
        println("onStart=${TAG}")
    }

    override fun onResume() {
        super.onResume()
        lazyLoadDataIfPrepared()
        println("onResume=${TAG}")
    }

    private   var  hasLoadData = false
    private fun lazyLoadDataIfPrepared() {
        if ( !hasLoadData) {
            lazyLoad()
            hasLoadData = true
        }
    }

    private   fun   lazyLoad(){
        println("第一次懒加载=${TAG}")
    }


    override fun onPause() {
        super.onPause()
        println("onPause=${TAG}")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        println("onDestroyView=${TAG}")
    }

    override fun onStop() {
        super.onStop()
        println("onStop=${TAG}")
    }


}