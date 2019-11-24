package com.padc.firebaseauthenication.mvp.presenters

import com.padc.firebaseauthenication.data.models.GoogleAuthenicationModel
import com.padc.firebaseauthenication.data.models.GoogleAuthenicationModelImpl
import com.padc.firebaseauthenication.mvp.views.UserInfoView

class UserInfoPresenter : BasePresenter<UserInfoView>(){
    val model: GoogleAuthenicationModel = GoogleAuthenicationModelImpl

    fun checkLoginUser(){
        if(model.isLoginUser())
            mView.bindDetail(model.currentUser!!)
    }

    fun logOut(){
        model.logout()
        mView.onLogOutClick()
    }
}