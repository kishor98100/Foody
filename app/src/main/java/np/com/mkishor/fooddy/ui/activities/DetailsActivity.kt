package np.com.mkishor.fooddy.ui.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.navArgs
import np.com.mkishor.fooddy.R
import np.com.mkishor.fooddy.databinding.ActivityDetailsBinding
import np.com.mkishor.fooddy.ui.adapters.DetailsPagerAdapter
import np.com.mkishor.fooddy.ui.fragments.details.IngredientsFragment
import np.com.mkishor.fooddy.ui.fragments.details.InstructionsFragment
import np.com.mkishor.fooddy.ui.fragments.details.OverviewFragment

class DetailsActivity : AppCompatActivity() {

    private val args: DetailsActivityArgs by navArgs()


    private lateinit var binding: ActivityDetailsBinding


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
        resultBundle.putParcelable("recipeBundle", args.foodResult)

        val adapter = DetailsPagerAdapter(
            resultBundle,
            fragments,
            titles,
            supportFragmentManager
        )
        binding.detailsViewPager.adapter = adapter
        binding.detailsTabLayout.setupWithViewPager(binding.detailsViewPager)


    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }
}