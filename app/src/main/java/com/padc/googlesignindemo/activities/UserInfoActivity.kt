package com.padc.googlesignindemo.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseUser
import com.padc.firebaseauthenication.mvp.presenters.UserInfoPresenter
import com.padc.firebaseauthenication.mvp.views.UserInfoView
import com.padc.googlesignindemo.R
import kotlinx.android.synthetic.main.activity_user_info.*

class UserInfoActivity : BaseActivity(), UserInfoView {

    companion object{
        fun newIntent(context : Context): Intent {
            return Intent(context,UserInfoActivity::class.java)
        }
    }

    private lateinit var presenter : UserInfoPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        presenter= UserInfoPresenter()
        presenter.initPresenter(this)
        presenter.checkLoginUser()


        btnLogOut.setOnClickListener {
            presenter.logOut()
        }
    }

    override fun bindDetail(user: FirebaseUser) {
//        Glide.with(this)
//            .load(user.photoUrl)
//            .into(ivProfile)
//
        tvUserId.append(user.uid)
//        tvUserName.append(user.displayName)
//        tvUserEmail.append(user.email)
    }

    override fun onLogOutClick() {
        showMessage("User log out")
        finish()
    }
}
