package com.example.demo.ui.home

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.demo.R
import com.example.demo.databinding.FragmentHomeBinding
import com.example.demo.ui.home.adapter.HomeCategoryAdapter
import com.example.demo.ui.home.adapter.HomePopularAdapter
import com.example.demo.ui.home.adapter.HomeTrendingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    @Inject
    lateinit var sharedPreferences: SharedPreferences
    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModels<HomeViewModel> ({this})
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val appBarConfiguration = AppBarConfiguration(findNavController().graph)
        binding.toolBar.setupWithNavController(findNavController(),appBarConfiguration)
        binding.toolBar.navigationIcon = getDrawable(requireContext(),R.drawable.ic_arrow_back)
        binding.name = sharedPreferences.getString("name",null)

        val adapterCat = HomeCategoryAdapter()
        binding.recyclerCategories.adapter = adapterCat

        val adapterPopular = HomePopularAdapter()
        binding.recyclerPopular.adapter = adapterPopular

        val adapterTrending = HomeTrendingAdapter()
        binding.recyclerTrending.adapter = adapterTrending

        lifecycleScope.launch {
            viewModel.homeCatUiState.collect{
                if(!it.error.isNullOrEmpty())
                    Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                if(it.categoryUiState.isNotEmpty())
                    adapterCat.submitList(it.categoryUiState)
            }
        }

        lifecycleScope.launch {
            viewModel.homePopularUiState.collect{
                if(!it.error.isNullOrEmpty())
                    Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                if(it.popularUiState.isNotEmpty()) {
                    adapterPopular.submitList(it.popularUiState)
                }
            }
        }

        lifecycleScope.launch {
            viewModel.homeTrendingUiState.collect{
                if(!it.error.isNullOrEmpty())
                    Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                if(it.trendingUiState.isNotEmpty()) {
                    adapterTrending.submitList(it.trendingUiState)
                }
            }
        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}