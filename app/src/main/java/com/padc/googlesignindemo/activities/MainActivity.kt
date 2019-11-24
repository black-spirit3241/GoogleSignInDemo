package com.padc.googlesignindemo.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import androidx.lifecycle.ViewModelProviders
import com.google.firebase.auth.FirebaseUser
import com.padc.firebaseauthenication.mvp.presenters.MainPresenter
import com.padc.firebaseauthenication.mvp.views.MainView
import com.padc.googlesignindemo.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() , MainView {

    private lateinit var presenter : MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpListener()
        presenter= ViewModelProviders.of(this).get(MainPresenter::class.java).apply {
            initPresenter(this@MainActivity)
        }
    }

    fun setUpListener(){
        btnLogin.setOnClickListener {
            if(TextUtils.isEmpty(edtEmail.text.toString()) || TextUtils.isEmpty(edtPassword.text.toString())){
                showMessage("Enter Email & Password")
            }else
                presenter.onEmailSignInClick(edtEmail.text.toString(),edtPassword.text.toString())
        }

        btnGoogleSignin.setOnClickListener {
            presenter.onGoogleSignInClick(this)
        }

        btnFacebookLogin.setOnClickListener {
            presenter.onFacebookLoginClick(this@MainActivity)
        }
    }

    override fun navigateToDetail(user: FirebaseUser) {
        startActivity(UserInfoActivity.newIntent(this))
    }


    override fun navigateToGoogleSignInScreen(signInIntent: Intent, rcGoogleSign: Int) {
        startActivityForResult(signInIntent,rcGoogleSign)
    }

    override fun showGoogleLoginError(message: String) {
        showMessage(message)
    }

    override fun showGoogleLoginSuccess(user: FirebaseUser) {
        navigateToDetail(user)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        presenter.onActivityResult(requestCode, resultCode, data, this)
    }
}
