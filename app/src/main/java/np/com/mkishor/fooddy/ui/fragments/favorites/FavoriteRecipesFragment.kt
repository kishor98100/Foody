package np.com.mkishor.fooddy.ui.fragments.favorites

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import np.com.mkishor.fooddy.R
import np.com.mkishor.fooddy.databinding.FragmentFavoriteRecipesBinding
import np.com.mkishor.fooddy.ui.adapters.FavoriteRecipeAdapter
import np.com.mkishor.fooddy.ui.viewmodels.MainViewModel

@AndroidEntryPoint
class FavoriteRecipesFragment : Fragment() {

    private var _binding: FragmentFavoriteRecipesBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel: MainViewModel by viewModels()
    private val adapter by lazy { FavoriteRecipeAdapter(requireActivity(), mainViewModel) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteRecipesBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = mainViewModel
        binding.adapter = adapter

        setHasOptionsMenu(true)

        setUpRecyclerView()

        mainViewModel.readFavoritesRecipes.observe(viewLifecycleOwner, { favorites ->
            adapter.setData(favorites)
        })

        return binding.root
    }

    private fun setUpRecyclerView() {
        binding.favoriteRecipesRecyclerView.adapter = adapter
        binding.favoriteRecipesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.favorite_menu, menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete_all_favorites) {
            mainViewModel.deleteAllFavorites()
            Snackbar.make(binding.root, "All Recipes Removed", Snackbar.LENGTH_SHORT)
                .setAction("OKAY") {}.show()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        adapter.closeActionMode()
    }


}