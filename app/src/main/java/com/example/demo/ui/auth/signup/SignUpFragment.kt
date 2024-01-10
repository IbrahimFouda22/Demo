package com.example.demo.ui.auth.signup

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
import com.example.demo.databinding.FragmentSignUpBinding
import com.example.demo.ui.shared.saveUser
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    @Inject
    lateinit var sharedPreferences: SharedPreferences
    private lateinit var binding: FragmentSignUpBinding
    private val viewModel by viewModels<SignUpViewModel>({ this })
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(layoutInflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        lifecycleScope.launch {
            viewModel.signUpState.collect {
                if (it.navigateToSignUp) {
                    findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToSignInFragment())
                    viewModel.navigateToSignInDone()
                }
                if (!it.error.isNullOrEmpty())
                    Toast.makeText(context, it.error, Toast.LENGTH_SHORT).show()
                if (it.userUiState != null) {
                    saveUser(sharedPreferences, it.userUiState.token,it.userUiState.name)
                    findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToHomeFragment())
                }
            }
        }

        binding.btnSignUp.setOnClickListener {
            viewModel.signUp(
                binding.edtEmailSignUp.text.toString(),
                binding.edtNameSignUp.text.toString(),
                binding.edtPasswordSignUp.text.toString(),
                binding.edtPhoneSignUp.text.toString(),
                binding.edtConfirmPasswordSignUp.text.toString()
            )
        }

        return binding.root
    }

}