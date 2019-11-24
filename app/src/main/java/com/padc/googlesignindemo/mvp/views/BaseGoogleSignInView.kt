package com.padc.firebaseauthenication.mvp.views

import android.content.Intent
import com.google.firebase.auth.FirebaseUser
import com.padc.googlesignindemo.mvp.views.BaseView

interface BaseGoogleSignInView : BaseView {

    fun navigateToGoogleSignInScreen(signInIntent: Intent, rcGoogleSign: Int)
    fun showGoogleLoginError(message: String)
    fun showGoogleLoginSuccess(user: FirebaseUser)
}