package np.com.mkishor.fooddy.ui.fragments.recipes.bottomsheet

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import np.com.mkishor.fooddy.databinding.RecipesBottomSheetBinding
import np.com.mkishor.fooddy.ui.viewmodels.RecipesViewModel
import np.com.mkishor.fooddy.utils.Constants.Companion.DEFAULT_DIET_TYPE
import np.com.mkishor.fooddy.utils.Constants.Companion.DEFAULT_MEAL_TYPE
import java.util.*


/**
 * @author:  Kishor Mainali
 * @date:  02/01/2021 21:41
 * @email:  mainalikishor@outlook.com
 *
 */
class RecipeBottomSheet : BottomSheetDialogFragment() {

    companion object {
        const val TAG = "RecipeBottomSheet"
    }

    private lateinit var binding: RecipesBottomSheetBinding
    private lateinit var recipeViewModel: RecipesViewModel

    private var mealTypeChip: String = DEFAULT_MEAL_TYPE
    private var mealTypeChipId: Int = 0
    private var dietTypeChip: String = DEFAULT_DIET_TYPE
    private var dietTypeChipId: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recipeViewModel = ViewModelProvider(requireActivity()).get(RecipesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RecipesBottomSheetBinding.inflate(inflater, container, false)


        recipeViewModel.readMealAndDietType.asLiveData().observe(viewLifecycleOwner, { value ->
            mealTypeChip = value.selectedMealType
            dietTypeChip = value.selectedDietType

            updateChip(value.selectedMealTypeId, binding.mealTypeChipGroup)
            updateChip(value.selectedDietTypeId, binding.dietTypeChipGroup)
        })


        binding.mealTypeChipGroup.setOnCheckedChangeListener { group, checkedId ->

            val chip = group.findViewById<Chip>(checkedId)
            val selectedMealType = chip.text.toString().toLowerCase(Locale.ROOT)
            mealTypeChip = selectedMealType
            mealTypeChipId = checkedId

        }
        binding.dietTypeChipGroup.setOnCheckedChangeListener { group, checkedId ->

            val chip = group.findViewById<Chip>(checkedId)
            val selectedDietType = chip.text.toString().toLowerCase(Locale.ROOT)
            dietTypeChip = selectedDietType
            dietTypeChipId = checkedId

        }


        binding.applyButton.setOnClickListener {
            recipeViewModel.saveMealAndDietType(
                mealTypeChip,
                mealTypeChipId,
                dietTypeChip,
                dietTypeChipId
            )

            val action = RecipeBottomSheetDirections.actionRecipeBottomSheetToRecipesFragment(true)
            findNavController().navigate(action)
        }


        return binding.root
    }

    private fun updateChip(chipId: Int, chipGroup: ChipGroup) {
        if (chipId != 0) {
            try {
                chipGroup.findViewById<Chip>(chipId).isChecked = true
            } catch (e: Exception) {
                Log.e(TAG, "updateChip: ", e)
            }
        }
    }
}