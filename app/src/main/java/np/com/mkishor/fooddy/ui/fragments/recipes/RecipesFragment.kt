package np.com.mkishor.fooddy.ui.fragments.recipes

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import np.com.mkishor.fooddy.R
import np.com.mkishor.fooddy.data.models.FoodRecipe
import np.com.mkishor.fooddy.databinding.FragmentRecipesBinding
import np.com.mkishor.fooddy.ui.adapters.FoodRecipeAdapter
import np.com.mkishor.fooddy.ui.viewmodels.MainViewModel
import np.com.mkishor.fooddy.ui.viewmodels.RecipesViewModel
import np.com.mkishor.fooddy.utils.NetworkChangedListener
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

    private val args by navArgs<RecipesFragmentArgs>()

    private lateinit var networkChangedListener: NetworkChangedListener


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

        setHasOptionsMenu(true)

        setUpRecyclerView()
        readOfflineData()

        lifecycleScope.launch {

            networkChangedListener = NetworkChangedListener()

            networkChangedListener.checkNetworkAvailability(requireContext()).collect { status ->
                recipeViewModel.networkStatus = status
                recipeViewModel.showNetworkStatus()
            }
        }

        binding.recipesFab.setOnClickListener {
            if (recipeViewModel.networkStatus) {
                findNavController().navigate(R.id.action_recipesFragment_to_recipeBottomSheet)
            } else {
                recipeViewModel.showNetworkStatus()
            }
        }

        return binding.root
    }


    private fun setUpRecyclerView() {
        binding.recipesRecyclerView.adapter = adapter
        binding.recipesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        showShimmer()
    }

    private fun readOfflineData() {
        lifecycleScope.launch {
            mainViewModel.readRecipes.observeOnce(viewLifecycleOwner, { data ->
                if (data.isNotEmpty() && !args.backFromBottomSheet) {
                    Log.d(TAG, "readOfflineData: read from offline")
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
            handleResponse(response)
        })

    }


    private fun searchRecipes(searchQuery: String) {
        showShimmer()
        mainViewModel.searchRecipes(recipeViewModel.applySearchQueries(searchQuery))
        mainViewModel.searchRecipesResponse.observe(viewLifecycleOwner, { response ->
            handleResponse(response)
        })
    }

    private fun handleResponse(response: NetworkResult<FoodRecipe>?) {
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

    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.recipes_menu, menu)
        val search = menu.findItem(R.id.menu_search)
        val searchView = search.actionView as? SearchView
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    searchRecipes(query)
                    hideKeyboard(searchView)
                }
                return true

            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })


    }

    private fun hideKeyboard(searchView: SearchView) {
        val manager =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        manager.hideSoftInputFromWindow(searchView.windowToken, 0)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}