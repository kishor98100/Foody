package np.com.mkishor.fooddy.ui.viewmodels

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import np.com.mkishor.fooddy.utils.Constants.Companion.API_KEY
import np.com.mkishor.fooddy.utils.Constants.Companion.QUERY_API_KEY
import np.com.mkishor.fooddy.utils.Constants.Companion.QUERY_DIET
import np.com.mkishor.fooddy.utils.Constants.Companion.QUERY_FILL_INGREDIENTS
import np.com.mkishor.fooddy.utils.Constants.Companion.QUERY_NUMBER
import np.com.mkishor.fooddy.utils.Constants.Companion.QUERY_RECIPE_INFORMATION
import np.com.mkishor.fooddy.utils.Constants.Companion.QUERY_TYPE


/**
 * @author:  Kishor Mainali
 * @date:  02/01/2021 20:01
 * @email:  mainalikishor@outlook.com
 *
 */
class RecipesViewModel @ViewModelInject constructor(
    application: Application
) : AndroidViewModel(application) {


    fun applyQueries(): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()
        queries[QUERY_NUMBER] = "50"
        queries[QUERY_API_KEY] = API_KEY
        queries[QUERY_TYPE] = "snack"
        queries[QUERY_DIET] = "vegan"
        queries[QUERY_RECIPE_INFORMATION] = "true"
        queries[QUERY_FILL_INGREDIENTS] = "true"
        return queries

    }
}