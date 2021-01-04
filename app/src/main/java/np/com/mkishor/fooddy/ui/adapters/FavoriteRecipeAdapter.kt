package np.com.mkishor.fooddy.ui.adapters

import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import np.com.mkishor.fooddy.R
import np.com.mkishor.fooddy.data.entities.FavoriteRecipeEntity
import np.com.mkishor.fooddy.databinding.FavoriteRecipeRowBinding
import np.com.mkishor.fooddy.ui.fragments.favorites.FavoriteRecipesFragmentDirections
import np.com.mkishor.fooddy.ui.viewmodels.MainViewModel
import np.com.mkishor.fooddy.utils.RecipeDiffUtil


/**
 * @author:  Kishor Mainali
 * @date:  04/01/2021 12:20
 * @email:  mainalikishor@outlook.com
 *
 */
class FavoriteRecipeAdapter(
    private val requreActivity: FragmentActivity,
    private val mainViewModel: MainViewModel
) : RecyclerView.Adapter<FavoriteRecipeAdapter.FavoriteViewHolder>(),
    ActionMode.Callback {


    private lateinit var mActionMode: ActionMode
    private lateinit var rootView: View


    private var favoriteRecipes = emptyList<FavoriteRecipeEntity>()

    private var multiSelection = false
    private var myViewHolders = arrayListOf<FavoriteViewHolder>()
    private var selectedRecipes = arrayListOf<FavoriteRecipeEntity>()


    class FavoriteViewHolder(val binding: FavoriteRecipeRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(favoriteRecipeEntity: FavoriteRecipeEntity) {
            binding.favoriteRecipe = favoriteRecipeEntity
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): FavoriteViewHolder {
                val layoutInflator = LayoutInflater.from(parent.context)
                val binding = FavoriteRecipeRowBinding.inflate(layoutInflator, parent, false)
                return FavoriteViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder =
        FavoriteViewHolder.from(parent)

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        myViewHolders.add(holder)
        rootView = holder.itemView.rootView
        val selectedRecipe = favoriteRecipes[position]
        holder.bind(selectedRecipe)

        //single click listener
        holder.itemView.setOnClickListener {

            if (multiSelection) {
                applySelection(holder, selectedRecipe)
            } else {
                val action =
                    FavoriteRecipesFragmentDirections.actionFavoriteRecipesFragmentToDetailsActivity(
                        selectedRecipe.foodResult
                    )
                holder.itemView.findNavController().navigate(action)
            }


        }

        //long click listener
        holder.itemView.setOnLongClickListener {
            if (!multiSelection) {
                multiSelection = true
                requreActivity.startActionMode(this)
                applySelection(holder, selectedRecipe)

                true
            } else {
                multiSelection = false
                false
            }


        }

    }

    override fun getItemCount(): Int = favoriteRecipes.size


    override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
        mode?.menuInflater?.inflate(R.menu.favorite_contextual_menu, menu)
        mActionMode = mode!!
        applyStatusBarColor(R.color.darker)
        return true
    }

    override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
        return true

    }

    override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
        if (item?.itemId == R.id.delete_favorite_menu) {
            selectedRecipes.forEach {
                mainViewModel.deleteFavoriteRecipe(it)
            }
            showSnackBar("${selectedRecipes.size} Recipe/s removed")
            multiSelection = false
            selectedRecipes.clear()
            mode?.finish()
        }
        return true
    }

    override fun onDestroyActionMode(mode: ActionMode?) {
        myViewHolders.forEach {
            changeRecipeStyle(it, R.color.white, R.color.lightMediumGray)
        }
        multiSelection = false
        selectedRecipes.clear()
        applyStatusBarColor(R.color.purple_200)

    }

    private fun applySelection(
        viewHolder: FavoriteViewHolder,
        currentRecipe: FavoriteRecipeEntity
    ) {
        if (selectedRecipes.contains(currentRecipe)) {
            selectedRecipes.remove(currentRecipe)
            changeRecipeStyle(viewHolder, R.color.white, R.color.lightMediumGray)
            applyActionModeTitle()
        } else {
            selectedRecipes.add(currentRecipe)
            changeRecipeStyle(viewHolder, R.color.cardBackgroundLightColor, R.color.purple_200)
            applyActionModeTitle()

        }
    }

    private fun changeRecipeStyle(
        viewHolder: FavoriteViewHolder,
        backgroundColor: Int,
        strokeColor: Int
    ) {
        viewHolder.binding.favoriteRecipeRowLayout.setBackgroundColor(
            ContextCompat.getColor(
                requreActivity,
                backgroundColor
            )
        )
        viewHolder.binding.favoriteRecipeRowCardView.strokeColor = ContextCompat.getColor(
            requreActivity,
            strokeColor
        )
    }


    private fun applyActionModeTitle() {
        when (selectedRecipes.size) {
            0 -> {
                mActionMode.finish()
            }
            1 -> {
                mActionMode.title = "${selectedRecipes.size} Item Selected"
            }
            else -> {
                mActionMode.title = "${selectedRecipes.size} Items Selected"

            }
        }
    }


    fun setData(newData: List<FavoriteRecipeEntity>) {
        val diffUtil = RecipeDiffUtil(favoriteRecipes, newData)
        val result = DiffUtil.calculateDiff(diffUtil)
        favoriteRecipes = newData
        result.dispatchUpdatesTo(this)

    }

    private fun showSnackBar(message: String) {
        Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT).setAction("Okay") {}.show()
    }

    private fun applyStatusBarColor(color: Int) {
        requreActivity.window.statusBarColor = ContextCompat.getColor(requreActivity, color)
    }

    fun closeActionMode() {
        if (this::mActionMode.isInitialized) {
            mActionMode.finish()
        }
    }
}