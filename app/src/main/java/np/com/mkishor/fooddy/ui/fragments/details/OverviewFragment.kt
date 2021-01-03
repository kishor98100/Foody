package np.com.mkishor.fooddy.ui.fragments.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import coil.load
import np.com.mkishor.fooddy.R
import np.com.mkishor.fooddy.data.models.FoodResult
import np.com.mkishor.fooddy.databinding.FragmentOverviewBinding
import org.jsoup.Jsoup

class OverviewFragment : Fragment() {

    private var _binding: FragmentOverviewBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOverviewBinding.inflate(inflater, container, false)

        val args = arguments
        val foodResult: FoodResult? = args?.getParcelable("recipeBundle")

        binding.detailsImageView.load(foodResult?.image)
        binding.detailsTitleTextView.text = foodResult?.title
        binding.detailsLikeTextView.text = foodResult?.aggregateLikes.toString()
        binding.detailsTimeTextView.text = foodResult?.readyInMinutes.toString()

        foodResult?.summary.let {
            val summary = Jsoup.parse(it).text()
            binding.detailsDescriptionTextView.text = summary
        }

        if (foodResult?.vegetarian == true) {
            binding.detailsVegetableImageView.setColorFilter(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.green
                )
            )
            binding.detailsVegetarianTextView.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.green
                )
            )
        }
        if (foodResult?.vegan == true) {
            binding.detailsVeganImageView.setColorFilter(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.green
                )
            )
            binding.detailsVeganTextView.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.green
                )
            )
        }
        if (foodResult?.dairyFree == true) {
            binding.detailsDairyFreeImageView.setColorFilter(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.green
                )
            )
            binding.detailsDairyFreeTextView.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.green
                )
            )
        }
        if (foodResult?.glutenFree == true) {
            binding.detailsGlutenFreeImageView.setColorFilter(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.green
                )
            )
            binding.detailsGlutenFreeTextView.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.green
                )
            )
        }
        if (foodResult?.veryHealthy == true) {
            binding.detailsHealthyImageView.setColorFilter(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.green
                )
            )
            binding.detailsHealthyTextView.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.green
                )
            )
        }
        if (foodResult?.cheap == true) {
            binding.detailsCheapImageView.setColorFilter(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.green
                )
            )
            binding.detailsCheapTextView.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.green
                )
            )
        }



        return binding.root
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}