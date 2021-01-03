package np.com.mkishor.fooddy.ui.fragments.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import np.com.mkishor.fooddy.data.models.FoodResult
import np.com.mkishor.fooddy.databinding.FragmentInstructionsBinding


class InstructionsFragment : Fragment() {

    private var _binding: FragmentInstructionsBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInstructionsBinding.inflate(inflater, container, false)


        val args = arguments
        val foodResult: FoodResult? = args?.getParcelable("recipeBundle")

        binding.instructionsWebView.webViewClient = object : WebViewClient() {

        }
        binding.instructionsWebView.webChromeClient = object : WebChromeClient() {

        }

        val websiteUrl: String = foodResult!!.sourceUrl
        binding.instructionsWebView.loadUrl(websiteUrl)


        return binding.root
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}