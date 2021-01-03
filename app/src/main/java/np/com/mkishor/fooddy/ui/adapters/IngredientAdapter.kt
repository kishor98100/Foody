package np.com.mkishor.fooddy.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import np.com.mkishor.fooddy.data.models.ExtendedIngredient
import np.com.mkishor.fooddy.databinding.IngredientsRowLayoutBinding
import np.com.mkishor.fooddy.utils.IngredientDiffUtil


/**
 * @author:  Kishor Mainali
 * @date:  03/01/2021 19:48
 * @email:  mainalikishor@outlook.com
 *
 */
class IngredientAdapter : RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder>() {


    private var ingredients = emptyList<ExtendedIngredient>()


    class IngredientViewHolder(private val binding: IngredientsRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(ingredient: ExtendedIngredient) {
            binding.ingredient = ingredient
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): IngredientViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = IngredientsRowLayoutBinding.inflate(layoutInflater, parent, false)
                return IngredientViewHolder(binding)
            }
        }

    }

    fun setData(newData: List<ExtendedIngredient>) {
        val ingredientDiffUtil = IngredientDiffUtil(ingredients, newData)
        val diffUtilResult = DiffUtil.calculateDiff(ingredientDiffUtil)
        ingredients = newData
        diffUtilResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder =
        IngredientViewHolder.from(parent)

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) =
        holder.bind(ingredients[position])

    override fun getItemCount(): Int = ingredients.size
}