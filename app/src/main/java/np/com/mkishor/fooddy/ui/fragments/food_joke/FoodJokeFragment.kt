package np.com.mkishor.fooddy.ui.fragments.food_joke

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import np.com.mkishor.fooddy.databinding.FragmentFoodJokeBinding

@AndroidEntryPoint
class FoodJokeFragment : Fragment() {

    private lateinit var binding: FragmentFoodJokeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFoodJokeBinding.inflate(inflater, container, false)
        return binding.root
    }


}