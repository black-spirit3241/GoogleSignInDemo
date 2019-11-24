package com.padc.firebaseauthenication.data.models

import android.app.Activity
import androidx.lifecycle.LiveData
import com.facebook.CallbackManager
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseUser

interface GoogleAuthenicationModel{

    val currentUser : FirebaseUser?

    val callbackManager : CallbackManager

    fun isLoginUser(): Boolean

    fun firebaseAuthWithGoogle(account: GoogleSignInAccount, onError: (String)->Unit): LiveData<FirebaseUser>

    fun firebaseAuthWithEmailAndPassword(email:String,password : String, onError: (String) -> Unit,onSuccess:(FirebaseUser)->Unit):LiveData<FirebaseUser>

    fun signInWithFacebook(context: Activity):LiveData<FirebaseUser>

    fun logout()
}