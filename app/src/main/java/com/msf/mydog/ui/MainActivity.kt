package com.msf.mydog.ui

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.msf.mydog.R
import com.msf.mydog.data.MyDogViewModel
import com.msf.mydog.databinding.ActivityMainBinding

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataBinding: ActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
    }

}
