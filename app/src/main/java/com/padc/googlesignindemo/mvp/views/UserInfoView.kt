package com.padc.firebaseauthenication.mvp.views

import com.google.firebase.auth.FirebaseUser
import com.padc.googlesignindemo.mvp.views.BaseView

interface UserInfoView: BaseView {
    fun bindDetail(user : FirebaseUser)
    fun onLogOutClick()
}