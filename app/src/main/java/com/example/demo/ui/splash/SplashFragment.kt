package com.example.demo.ui.splash

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.demo.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding
    @Inject
    lateinit var sharedPreferences: SharedPreferences
    private val viewModel by viewModels<SplashViewModel>({this})
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(layoutInflater)

        val token = sharedPreferences.getString("token",null)

        lifecycleScope.launch {
            viewModel.splashState.collect{
                if(it.navigateToHome){
                    findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
                    viewModel.navigateToHomeDone()
                }
                if(it.navigateToSignIn){
                    findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToSignInFragment())
                    viewModel.navigateToSignInDone()
                }
            }
        }

        if(token.isNullOrEmpty()){
            viewModel.navigateToSignIn()
        }else{
            viewModel.navigateToHome()
        }


        return binding.root
    }

}