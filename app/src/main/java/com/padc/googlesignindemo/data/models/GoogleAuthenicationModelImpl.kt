package com.padc.firebaseauthenication.data.models

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import java.util.*


object GoogleAuthenicationModelImpl : GoogleAuthenicationModel {

    const val TAG = "GoogleSignInAuth"

    private val auth = FirebaseAuth.getInstance()
    override val callbackManager : CallbackManager
    get() =  CallbackManager.Factory.create()

    override val currentUser: FirebaseUser?
        get() = auth.currentUser

    override fun isLoginUser(): Boolean {
        return currentUser!=null
    }

    override fun firebaseAuthWithGoogle(
        account: GoogleSignInAccount,
        onError: (String) -> Unit
    ): LiveData<FirebaseUser> {
        val liveData = MutableLiveData<FirebaseUser>()
        val credential = GoogleAuthProvider.getCredential(account.id,null)

        auth.signInWithCredential(credential).addOnCompleteListener {
            task ->
            if(task.isSuccessful)
            {
                Log.d(TAG, "signInWithCredential:success")
                val user = auth.currentUser
                liveData.value = user
            }else{
                Log.w(TAG, "signInWithCredential:failure", task.exception)
                onError(task.exception?.message ?: "")
            }
        }
        return liveData
    }

    override fun firebaseAuthWithEmailAndPassword(
        email: String,
        password: String,
        onError: (String) -> Unit,
        onSuccess : (FirebaseUser) -> Unit
    ): LiveData<FirebaseUser> {
        val liveData=MutableLiveData<FirebaseUser>()
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
            task ->
            if(task.isSuccessful){
                Log.d(TAG, "sign in with email and password:success")
                val user = auth.currentUser
                liveData.value = user
                onSuccess(user!!)
            }else{
                onError(task.exception?.message?: "")
                Log.w(TAG,"SignIn with email and password fail",task.exception)
            }
        }
        return liveData
    }


   override fun signInWithFacebook(context : Activity): LiveData<FirebaseUser> {
       FacebookSdk.sdkInitialize(context)
       val liveData = MutableLiveData<FirebaseUser>()
        LoginManager.getInstance()
            .logInWithReadPermissions(context, Arrays.asList("public_profile", "name", "email"))
        LoginManager.getInstance()
            .registerCallback(callbackManager,object : FacebookCallback<LoginResult>{
                override fun onSuccess(result: LoginResult?) {
                    result?.let {
                        liveData.value= handleFacebookAccessToken(result.accessToken)
                    }
                }
                override fun onCancel() {
                    Log.d(TAG, "Facebook Cancel")
                }

                override fun onError(error: FacebookException?) {
                    Log.d(TAG, "Facebook Error")
                }

            })
       return liveData
    }

    private fun handleFacebookAccessToken(token: AccessToken) : FirebaseUser? {
        var fuser : FirebaseUser?=null
        val credential = FacebookAuthProvider.getCredential(token.token)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    val user = auth.currentUser
                    fuser=user
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)

                }
            }
        return fuser
    }

    override fun logout() {
        auth.signOut()
    }


}