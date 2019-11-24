package com.padc.firebaseauthenication.mvp.presenters

import android.app.Activity
import android.content.Context
import com.padc.firebaseauthenication.data.models.GoogleAuthenicationModelImpl
import com.padc.firebaseauthenication.mvp.views.MainView

class MainPresenter : BaseGoogleSignInPresenter<MainView>() {
    private val userModel: GoogleAuthenicationModelImpl = GoogleAuthenicationModelImpl

    fun onGoogleSignInClick(context: Context) {
        googleSingIn(context)
    }

    fun onEmailSignInClick(email: String, password: String) {
            userModel.firebaseAuthWithEmailAndPassword(email, password, {
                mView.showGoogleLoginError(it)
            }, {
                    mView.showGoogleLoginSuccess(it)
                })

    }

    fun onFacebookLoginClick(context: Activity){
        userModel.signInWithFacebook(context)
    }

}