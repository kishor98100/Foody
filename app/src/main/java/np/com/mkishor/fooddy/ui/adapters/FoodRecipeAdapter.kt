package np.com.mkishor.fooddy.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import np.com.mkishor.fooddy.data.models.FoodRecipe
import np.com.mkishor.fooddy.data.models.FoodResult
import np.com.mkishor.fooddy.databinding.RecipeRowLayoutBinding
import np.com.mkishor.fooddy.utils.RecipeDiffUtil


/**
 * @author:  Kishor Mainali
 * @date:  02/01/2021 19:08
 * @email:  mainalikishor@outlook.com
 *
 */
class FoodRecipeAdapter : RecyclerView.Adapter<FoodRecipeAdapter.FoodRecipeViewHolder>() {

    private var recipes = emptyList<FoodResult>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodRecipeViewHolder {
        return FoodRecipeViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: FoodRecipeViewHolder, position: Int) {
        val currentRecipe = recipes[position]
        holder.bind(currentRecipe)

    }

    override fun getItemCount(): Int = recipes.size

    fun setData(newData: FoodRecipe) {
        val recipeDiffUtil = RecipeDiffUtil(recipes, newData.results)
        val diffUtilResult = DiffUtil.calculateDiff(recipeDiffUtil)
        recipes = newData.results
        diffUtilResult.dispatchUpdatesTo(this)
    }


    class FoodRecipeViewHolder(private val binding: RecipeRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(foodResult: FoodResult) {
            binding.foodResult = foodResult
            binding.executePendingBindings()
        }


        companion object {
            const val TAG = "RECIPE_ADAPTER"
            fun from(parent: ViewGroup): FoodRecipeViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RecipeRowLayoutBinding.inflate(layoutInflater, parent, false)
                return FoodRecipeViewHolder(binding)
            }
        }
    }
}