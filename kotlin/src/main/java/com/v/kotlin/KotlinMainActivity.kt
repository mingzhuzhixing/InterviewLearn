package com.v.kotlin

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.v.kotlin.base.BaseActivity
import com.v.kotlin.presenter.EmptyPresenter

class KotlinMainActivity : BaseActivity<EmptyPresenter>() {

    override fun getLayoutId() = R.layout.activity_kotlin_main

    override fun initData() {

    }

    override fun createPresenter(): EmptyPresenter {
        return EmptyPresenter()
    }

    override fun processLogical() {
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setItemIconTintList(null)
        val appBarConfiguration: AppBarConfiguration = AppBarConfiguration.Builder(
            R.id.navigation_home,
            R.id.navigation_collect,
            R.id.navigation_person
        ).build()
        val navController: NavController =
            Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController)
    }
}