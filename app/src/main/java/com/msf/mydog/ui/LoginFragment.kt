package com.msf.mydog.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.msf.mydog.R
import com.msf.mydog.data.MyDogViewModel
import com.msf.mydog.databinding.FragmentLoginBinding


class LoginFragment : Fragment(){
    private lateinit var dataBinding: FragmentLoginBinding
    private lateinit var viewModel: MyDogViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login,container, false)
        viewModel = ViewModelProviders.of(requireActivity())[MyDogViewModel::class.java]
        return dataBinding.root
    }

    override fun onResume() {
        super.onResume()
        dataBinding.login.setOnClickListener {
            dataBinding.email.apply {
                if(text.isNullOrEmpty()){
                    error = getString(R.string.email_empty)
                }
                if(!text.matches(EMAIL_REGEX.toRegex())){
                    error = getString(R.string.invalid_email)
                }
                if(error.isNullOrEmpty() ){
                    viewModel.signUp(text.toString())
                }
            }
        }
        viewModel.liveDataUser.observe(this, Observer {
            if(it == null){
                dataBinding.email.error = viewModel.msgErro
            } else {
                viewModel.getFeed("husky")
                dataBinding.root.findNavController().navigate(R.id.action_loginFragment_to_collectionFragment)
            }
        })
    }

    companion object {
        private const val EMAIL_REGEX = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}"
    }
}