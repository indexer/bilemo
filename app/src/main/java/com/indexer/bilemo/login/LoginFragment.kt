package com.indexer.bilemo.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.indexer.bilemo.BR
import com.indexer.bilemo.R
import com.indexer.bilemo.databinding.FragmentLoginBinding
import com.indexer.bilemo.login.entities.LoginUser
import com.indexer.bilemo.login.model.LoginUserUI
import com.indexer.bilemo.login.viewmodel.LoginViewModel
import com.indexer.bilemo.utils.getHashFromPasswordString
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val loginViewModel: LoginViewModel by viewModels()
    private lateinit var fragmentLoginDataBinding: FragmentLoginBinding
    var isValid = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentLoginDataBinding = FragmentLoginBinding.inflate(
            layoutInflater, container, false
        )
        fragmentLoginDataBinding.lifecycleOwner = this
        fragmentLoginDataBinding.setVariable(BR.loginModel, loginViewModel)
        return fragmentLoginDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginViewModel.user.observe(viewLifecycleOwner, {
            loginViewModel.saveRememberState(it.isRememberLogin)
            if (checkValidUserName(it) && checkValidPassword(it)) {
                val hasPassword = getHashFromPasswordString(it.getUserPassword)
                lifecycleScope.launch(Dispatchers.IO) {
                    val loginUser = LoginUser(it.getUserName, hasPassword, it.userSelectedCountry)
                    val user = loginViewModel.getLoginUser(loginUser)
                    isValid = user
                }
            }
        })


        if (loginViewModel.getLoginState() == true) {
            findNavController().navigate(R.id.action_loginFragment_to_userListFragment)
        }

        fragmentLoginDataBinding.login.setOnClickListener {
            if (isValid) {
                findNavController().navigate(R.id.action_loginFragment_to_userListFragment)
            }
        }
    }

    private fun checkValidPassword(it: LoginUserUI): Boolean {
        var isValid = false
        if (fragmentLoginDataBinding.password.editText?.text?.isNotEmpty() == true) {
            when {
                !it.isPasswordLengthGreaterThanSeven -> {
                    fragmentLoginDataBinding.password.error = getString(R.string.invalid_password)
                }
                it.isNotContainDigitCase -> {
                    fragmentLoginDataBinding.password.error =
                        getString(R.string.invalid_password_need_digit)
                }
                it.isNotContainLowerCase -> {
                    fragmentLoginDataBinding.password.error =
                        getString(R.string.invalid_password_need_lower)
                }
                it.isNotContainUpperCase -> {

                    fragmentLoginDataBinding.password.error =
                        getString(R.string.invalid_password_need_upper)
                }
                it.isNotContainSpecialCharCase -> {
                    fragmentLoginDataBinding.password.error =
                        getString(R.string.invalid_password_need_special)
                }
                else -> {
                    fragmentLoginDataBinding.password.error = null
                    isValid = true
                }
            }
            return isValid
        }
        return isValid
    }

    private fun checkValidUserName(it: LoginUserUI): Boolean {
        var isUserNameValid = false
        if (fragmentLoginDataBinding.username.editText?.text?.isNotEmpty() == true) {
            if (!it.isCorrectUserName) {
                fragmentLoginDataBinding.username.error = getString(R.string.invalid_username)
            } else if (!it.hasMinLengthForUserName) {
                fragmentLoginDataBinding.username.error = getString(R.string.invalid_username)
            } else {
                isUserNameValid = true
                fragmentLoginDataBinding.username.error = null
            }
        }
        return isUserNameValid
    }

}