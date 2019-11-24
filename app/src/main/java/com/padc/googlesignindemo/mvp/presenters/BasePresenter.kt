package com.padc.firebaseauthenication.mvp.presenters


import androidx.lifecycle.ViewModel
import com.padc.googlesignindemo.mvp.views.BaseView

abstract class BasePresenter<T : BaseView> : ViewModel(){

    protected lateinit var mView:T
    fun initPresenter(view : T){
        this.mView=view
    }
}