package com.padc.firebaseauthenication.mvp.views

import com.google.firebase.auth.FirebaseUser

interface MainView : BaseGoogleSignInView{

    fun navigateToDetail(user: FirebaseUser)

}