package com.example.demo.ui.auth.signin

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.demo.databinding.FragmentSignInBinding
import com.example.demo.ui.shared.saveUser
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SignInFragment : Fragment() {

    @Inject
    lateinit var sharedPreferences: SharedPreferences
    private lateinit var binding: FragmentSignInBinding
    private val viewModel by viewModels<SignInViewModel>({this})
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSignInBinding.inflate(layoutInflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        lifecycleScope.launch {
            viewModel.signInState.collect{
                if(it.navigateToSignUp) {
                    findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToSignUpFragment())
                    viewModel.navigateToSignUpDone()
                }
                if(!it.error.isNullOrEmpty())
                    Toast.makeText(context, it.error, Toast.LENGTH_SHORT).show()
                if(it.userUiState != null) {
                    saveUser(sharedPreferences,it.userUiState.token,it.userUiState.name)
                    findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToHomeFragment())
                }
            }
        }

        binding.btnLogIn.setOnClickListener {
            viewModel.signIn(binding.edtEmailSignIn.text.toString(),binding.edtPasswordSignIn.text.toString())
        }

        return binding.root
    }


}