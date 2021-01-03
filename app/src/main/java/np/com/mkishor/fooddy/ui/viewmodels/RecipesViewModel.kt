package np.com.mkishor.fooddy.ui.viewmodels

import android.app.Application
import android.widget.Toast
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import np.com.mkishor.fooddy.data.repositories.DataStoreRepository
import np.com.mkishor.fooddy.utils.Constants.Companion.API_KEY
import np.com.mkishor.fooddy.utils.Constants.Companion.DEFAULT_DIET_TYPE
import np.com.mkishor.fooddy.utils.Constants.Companion.DEFAULT_MEAL_TYPE
import np.com.mkishor.fooddy.utils.Constants.Companion.DEFAULT_RECIPE_NUMBER
import np.com.mkishor.fooddy.utils.Constants.Companion.QUERY_API_KEY
import np.com.mkishor.fooddy.utils.Constants.Companion.QUERY_DIET
import np.com.mkishor.fooddy.utils.Constants.Companion.QUERY_FILL_INGREDIENTS
import np.com.mkishor.fooddy.utils.Constants.Companion.QUERY_NUMBER
import np.com.mkishor.fooddy.utils.Constants.Companion.QUERY_RECIPE_INFORMATION
import np.com.mkishor.fooddy.utils.Constants.Companion.QUERY_SEARCH
import np.com.mkishor.fooddy.utils.Constants.Companion.QUERY_TYPE


/**
 * @author:  Kishor Mainali
 * @date:  02/01/2021 20:01
 * @email:  mainalikishor@outlook.com
 *
 */
class RecipesViewModel @ViewModelInject constructor(
    private val dataStoreRepository: DataStoreRepository,
    application: Application
) : AndroidViewModel(application) {


    private var mealType = DEFAULT_MEAL_TYPE
    private var dietType = DEFAULT_DIET_TYPE

    var readMealAndDietType = dataStoreRepository.readMealAndDietType

    var networkStatus = false


    fun saveMealAndDietType(
        mealType: String,
        mealTypeId: Int,
        dietType: String,
        dietTypeId: Int
    ) = viewModelScope.launch(Dispatchers.IO) {
        dataStoreRepository.saveMealAndDietType(mealType, mealTypeId, dietType, dietTypeId)
    }


    fun applyQueries(): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()


        viewModelScope.launch {
            readMealAndDietType.collect { value ->
                mealType = value.selectedMealType
                dietType = value.selectedDietType
            }
        }
        queries[QUERY_NUMBER] = DEFAULT_RECIPE_NUMBER
        queries[QUERY_API_KEY] = API_KEY
        queries[QUERY_TYPE] = mealType
        queries[QUERY_DIET] = dietType
        queries[QUERY_RECIPE_INFORMATION] = "true"
        queries[QUERY_FILL_INGREDIENTS] = "true"
        return queries

    }

    fun applySearchQueries(query: String): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()


        viewModelScope.launch {
            readMealAndDietType.collect { value ->
                mealType = value.selectedMealType
                dietType = value.selectedDietType
            }
        }
        queries[QUERY_SEARCH] = query
        queries[QUERY_NUMBER] = DEFAULT_RECIPE_NUMBER
        queries[QUERY_API_KEY] = API_KEY
        queries[QUERY_RECIPE_INFORMATION] = "true"
        queries[QUERY_FILL_INGREDIENTS] = "true"
        return queries

    }


    fun showNetworkStatus() {
        if (!networkStatus) {
            Toast.makeText(getApplication(), "No Internet Connection", Toast.LENGTH_SHORT).show()
        }
    }


}