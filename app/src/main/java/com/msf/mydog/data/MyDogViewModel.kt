package com.msf.mydog.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.msf.mydog.model.User
import com.msf.mydog.repository.MyDogRepository

class MyDogViewModel(application: Application): AndroidViewModel(application) {

    var msgErro: String? = null

    private val mutableUser: MutableLiveData<User> by lazy{
        MutableLiveData<User>()
    }
    val liveDataUser: LiveData<User>
        get() = mutableUser

    private val mutableLiveFeed: MutableLiveData<List<String>> by lazy {
        MutableLiveData<List<String>>()
    }
    val liveDataFeed: LiveData<List<String>>
        get() = mutableLiveFeed

    private val mutableLiveDataIsLooking: MutableLiveData<Boolean> = MutableLiveData()
    val liveDataIsLooking: LiveData<Boolean>
        get() = mutableLiveDataIsLooking

    fun signUp(email: String){
        MyDogRepository.signup(this.getApplication(),email, {
            mutableUser.postValue(it)
        }, {
            mutableUser.postValue(null)
            msgErro = it
        })
    }

    fun getFeed(dog: String) {
        isLooking()
        MyDogRepository.getFeed(this.getApplication(),liveDataUser.value!!.token, dog.toLowerCase(), {
            mutableLiveFeed.postValue(it)
        }, {
            mutableLiveFeed.postValue(null)
        })

    }

    private fun isLooking() {
        mutableLiveDataIsLooking.postValue(true)
    }
}