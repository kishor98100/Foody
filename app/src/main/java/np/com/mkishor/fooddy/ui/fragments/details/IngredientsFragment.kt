package np.com.mkishor.fooddy.ui.fragments.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import np.com.mkishor.fooddy.data.models.FoodResult
import np.com.mkishor.fooddy.databinding.FragmentIngredientsBinding
import np.com.mkishor.fooddy.ui.adapters.IngredientAdapter


class IngredientsFragment : Fragment() {

    private var _binding: FragmentIngredientsBinding? = null
    private val binding get() = _binding!!

    private val adapter: IngredientAdapter by lazy { IngredientAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentIngredientsBinding.inflate(inflater, container, false)


        val args = arguments
        val foodResult: FoodResult? = args?.getParcelable("recipeBundle")

        setUpRecyclerView()
        foodResult?.extendedIngredients?.let {
            adapter.setData(it)
        }


        return binding.root
    }


    private fun setUpRecyclerView() {
        binding.ingredientsRecyclerView.adapter = adapter
        binding.ingredientsRecyclerView.layoutManager = LinearLayoutManager(requireContext())

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}