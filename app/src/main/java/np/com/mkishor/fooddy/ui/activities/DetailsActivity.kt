package np.com.mkishor.fooddy.ui.activities

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.navArgs
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import np.com.mkishor.fooddy.R
import np.com.mkishor.fooddy.data.entities.FavoriteRecipeEntity
import np.com.mkishor.fooddy.databinding.ActivityDetailsBinding
import np.com.mkishor.fooddy.ui.adapters.DetailsPagerAdapter
import np.com.mkishor.fooddy.ui.fragments.details.IngredientsFragment
import np.com.mkishor.fooddy.ui.fragments.details.InstructionsFragment
import np.com.mkishor.fooddy.ui.fragments.details.OverviewFragment
import np.com.mkishor.fooddy.ui.viewmodels.MainViewModel
import np.com.mkishor.fooddy.utils.Constants.Companion.RECIPE_BUNDLE_NAME

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    companion object {
        const val TAG = "DETAILS_ACTIVITY"
    }

    private val args: DetailsActivityArgs by navArgs()


    private lateinit var binding: ActivityDetailsBinding

    private val mainViewModel: MainViewModel by viewModels()

    private var recipeSaved = false
    private var savedRecipeId = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.detailsToolbar)
        binding.detailsToolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)


        val fragments = ArrayList<Fragment>()
        fragments.add(OverviewFragment())
        fragments.add(IngredientsFragment())
        fragments.add(InstructionsFragment())


        val titles = ArrayList<String>()
        titles.add("Overview")
        titles.add("Ingredients")
        titles.add("Instructions")

        val resultBundle = Bundle()
        resultBundle.putParcelable(RECIPE_BUNDLE_NAME, args.foodResult)

        val pagerAdapter = DetailsPagerAdapter(
            resultBundle,
            fragments,
            this
        )

        binding.detailsViewPager.apply {
            adapter = pagerAdapter

        }
        TabLayoutMediator(binding.detailsTabLayout, binding.detailsViewPager) { tab, position ->
            tab.text = titles[position]
        }.attach()


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.details_menu, menu)
        val menuItem = menu?.findItem(R.id.add_to_favorite)
        checkSavedRecipe(menuItem!!)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.add_to_favorite && !recipeSaved) {
            saveToFavorites(item)
        } else if (item.itemId == R.id.add_to_favorite && recipeSaved) {
            removeFromFavorites(item)
        } else if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun checkSavedRecipe(menuItem: MenuItem) {
        mainViewModel.readFavoritesRecipes.observe(this, { favorites ->
            try {
                for (savedRecipe in favorites) {
                    if (savedRecipe.foodResult.id == args.foodResult.id) {
                        changeMenuItemColor(menuItem, R.color.yellow)
                        savedRecipeId = savedRecipe.id
                        recipeSaved = true
                    } else {
                        changeMenuItemColor(menuItem, R.color.white)
                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "checkSavedRecipe: ", e)
            }

        })

    }

    private fun saveToFavorites(item: MenuItem) {
        val favoriteRecipeEntity = FavoriteRecipeEntity(0, args.foodResult)
        mainViewModel.insertFavoriteRecipes(favoriteRecipeEntity)
        changeMenuItemColor(item, R.color.yellow)
        showSnackBar("Recipe Saved")
        recipeSaved = true

    }

    private fun removeFromFavorites(item: MenuItem) {
        val favoriteRecipeEntity = FavoriteRecipeEntity(
            savedRecipeId,
            args.foodResult
        )
        mainViewModel.deleteFavoriteRecipe(favoriteRecipeEntity)
        changeMenuItemColor(item, R.color.white)
        showSnackBar("Removed From Favorites")
        recipeSaved = false

    }

    private fun showSnackBar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).setAction(
            "Ok"
        ) { }.show()

    }

    private fun changeMenuItemColor(item: MenuItem, color: Int) {
        item.icon.setTint(ContextCompat.getColor(this, color))

    }
}