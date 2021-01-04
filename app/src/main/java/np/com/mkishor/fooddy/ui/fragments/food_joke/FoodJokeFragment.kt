package np.com.mkishor.fooddy.ui.fragments.food_joke

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import np.com.mkishor.fooddy.R
import np.com.mkishor.fooddy.databinding.FragmentFoodJokeBinding
import np.com.mkishor.fooddy.ui.viewmodels.MainViewModel
import np.com.mkishor.fooddy.utils.Constants.Companion.API_KEY
import np.com.mkishor.fooddy.utils.NetworkResult

@AndroidEntryPoint
class FoodJokeFragment : Fragment() {
    companion object {
        const val TAG = "FOOD_JOKE_FRAGMENT"
    }

    private val mainViewModel: MainViewModel by viewModels()

    private var _binding: FragmentFoodJokeBinding? = null
    private val binding get() = _binding!!

    private var foodJoke = "No Food Joke"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFoodJokeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = mainViewModel

        setHasOptionsMenu(true)
        requestFoodJoke()

        return binding.root
    }

    private fun requestFoodJoke() {
        mainViewModel.getFoodJoke(API_KEY)
        mainViewModel.foodJokeResponse.observe(viewLifecycleOwner, { result ->
            when (result) {
                is NetworkResult.Loading -> {
                    Log.d(TAG, "requestFoodJoke: LOADING")
                }
                is NetworkResult.Error -> {
                    fetchFromCache()
                    Toast.makeText(requireContext(), result.message.toString(), Toast.LENGTH_SHORT)
                        .show()
                }
                is NetworkResult.Success -> {
                    foodJoke = result.data?.text!!
                    binding.foodJokeTextView.text = result.data?.text
                }
            }

        })
    }

    private fun fetchFromCache() {
        lifecycleScope.launch {
            mainViewModel.readFoodJoke.observe(viewLifecycleOwner, { database ->
                if (!database.isNullOrEmpty()) {
                    foodJoke = database[0].foodJoke.text
                    binding.foodJokeTextView.text = database[0].foodJoke.text
                }
            })
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.food_joke_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.share_food_joke) {
            val shareIntent = Intent().apply {
                this.action = Intent.ACTION_SEND
                this.putExtra(Intent.EXTRA_TEXT, foodJoke)
                this.type = "text/plain"
            }
            startActivity(shareIntent)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}