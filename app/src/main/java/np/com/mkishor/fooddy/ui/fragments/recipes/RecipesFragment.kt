package np.com.mkishor.fooddy.ui.fragments.recipes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import np.com.mkishor.fooddy.R
import np.com.mkishor.fooddy.databinding.FragmentRecipesBinding
import np.com.mkishor.fooddy.ui.adapters.FoodRecipeAdapter
import np.com.mkishor.fooddy.ui.viewmodels.MainViewModel
import np.com.mkishor.fooddy.ui.viewmodels.RecipesViewModel
import np.com.mkishor.fooddy.utils.NetworkResult
import np.com.mkishor.fooddy.utils.observeOnce

@AndroidEntryPoint
class RecipesFragment : Fragment() {

    companion object {
        const val TAG = "RecipesFragment"
    }

    private var _binding: FragmentRecipesBinding? = null
    private val binding get() = _binding!!
    private val adapter by lazy {
        FoodRecipeAdapter()
    }
    private lateinit var mainViewModel: MainViewModel
    private lateinit var recipeViewModel: RecipesViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        recipeViewModel = ViewModelProvider(requireActivity()).get(RecipesViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecipesBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.mainViewModel = mainViewModel

        setUpRecyclerView()
        readOfflineData()


        binding.recipesFab.setOnClickListener {
            findNavController().navigate(R.id.action_recipesFragment_to_recipeBottomSheet)
        }
        
        return binding.root
    }


    private fun setUpRecyclerView() {
        binding.recipesRecyclerView.adapter = adapter
        binding.recipesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        showShimmer()
    }

    private fun readOfflineData() {
        Log.d(TAG, "readOfflineData: read from offline")
        lifecycleScope.launch {
            mainViewModel.readRecipes.observeOnce(viewLifecycleOwner, { data ->
                if (data.isNotEmpty()) {
                    adapter.setData(data[0].foodRecipe)
                    hideShimmer()
                } else {
                    requestApi()
                }
            })
        }

    }


    private fun loadFromCache() {
        lifecycleScope.launch {
            mainViewModel.readRecipes.observe(viewLifecycleOwner, { data ->
                if (data.isNotEmpty()) {
                    adapter.setData(data[0].foodRecipe)
                }
            })
        }
    }

    private fun showShimmer() {
        binding.recipesRecyclerView.showShimmer()
    }

    private fun hideShimmer() {
        binding.recipesRecyclerView.hideShimmer()
    }

    private fun requestApi() {
        Log.d(TAG, "requestApi: api called")
        mainViewModel.getRecipes(recipeViewModel.applyQueries())
        mainViewModel.recipesResponse.observe(viewLifecycleOwner, { response ->
            when (response) {
                is NetworkResult.Success -> {
                    hideShimmer()
                    response.data?.let {
                        adapter.setData(it)
                    }
                }
                is NetworkResult.Error -> {
                    hideShimmer()
                    loadFromCache()
                    Toast.makeText(
                        requireContext(),
                        response.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()

                }
                is NetworkResult.Loading -> {
                    showShimmer()
                }
            }
        })

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}