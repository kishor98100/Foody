package np.com.mkishor.fooddy.ui.viewmodels

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import np.com.mkishor.fooddy.data.entities.FavoriteRecipeEntity
import np.com.mkishor.fooddy.data.entities.FoodRecipeEntity
import np.com.mkishor.fooddy.data.models.FoodRecipe
import np.com.mkishor.fooddy.data.repositories.FoodRecipeRepository
import np.com.mkishor.fooddy.utils.NetworkResult
import retrofit2.Response


/**
 * @author:  Kishor Mainali
 * @date:  02/01/2021 18:19
 * @email:  mainalikishor@outlook.com
 *
 */
class MainViewModel @ViewModelInject constructor(
    private val repository: FoodRecipeRepository,
    application: Application
) : AndroidViewModel(application) {


    /** ROOM DATABASE **/
    val readRecipes: LiveData<List<FoodRecipeEntity>> = repository.local.readRecipes().asLiveData()
    val readFavoritesRecipes: LiveData<List<FavoriteRecipeEntity>> =
        repository.local.readFavoriteRecipes().asLiveData()

    private fun insertRecipes(recipeEntity: FoodRecipeEntity) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.local.insertFoodRecipes(recipeEntity)
        }

    fun insertFavoriteRecipes(favoriteRecipeEntity: FavoriteRecipeEntity) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.local.insertFavoriteRecipes(favoriteRecipeEntity)

        }

    fun deleteFavoriteRecipe(favoriteRecipeEntity: FavoriteRecipeEntity) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.local.deleteFavoriteRecipe(favoriteRecipeEntity)

        }

    fun deleteAllFavorites() =
        viewModelScope.launch(Dispatchers.IO) {
            repository.local.deleteAllFavorites()

        }


    /** RETROFIT */


    var recipesResponse: MutableLiveData<NetworkResult<FoodRecipe>> = MutableLiveData()
    var searchRecipesResponse: MutableLiveData<NetworkResult<FoodRecipe>> = MutableLiveData()

    fun getRecipes(queries: Map<String, String>) = viewModelScope.launch {
        getRecipeSafeCall(queries)
    }

    fun searchRecipes(queries: Map<String, String>) = viewModelScope.launch {
        searchRecipeSafeCall(queries)
    }

    private suspend fun searchRecipeSafeCall(queries: Map<String, String>) {
        searchRecipesResponse.value = NetworkResult.Loading()
        if (hasInternetConnection()) {
            try {
                val response = repository.remote.searchRecipes(queries)
                searchRecipesResponse.value = handleFoodRecipesResponse(response)
            } catch (e: Exception) {
                searchRecipesResponse.value = NetworkResult.Error(e.localizedMessage)
            }

        } else {
            recipesResponse.value = NetworkResult.Error("No Internet Connection.")
        }

    }


    private suspend fun getRecipeSafeCall(queries: Map<String, String>) {
        recipesResponse.value = NetworkResult.Loading()
        if (hasInternetConnection()) {

            try {
                val response = repository.remote.getRecipes(queries)
                recipesResponse.value = handleFoodRecipesResponse(response)

                val foodRecipe = recipesResponse.value!!.data
                if (foodRecipe != null) {
                    offlineCacheData(foodRecipe)
                }

            } catch (e: Exception) {
                recipesResponse.value = NetworkResult.Error(e.localizedMessage)

            }

        } else {
            recipesResponse.value = NetworkResult.Error("No Internet Connection.")
        }

    }

    private fun offlineCacheData(foodRecipe: FoodRecipe) {
        val recipeEntity = FoodRecipeEntity(foodRecipe)
        insertRecipes(recipeEntity)

    }

    private fun handleFoodRecipesResponse(response: Response<FoodRecipe>): NetworkResult<FoodRecipe>? {

        when {
            response.message().toString().contains("timeout") -> {
                return NetworkResult.Error("TimeOut")
            }
            response.code() == 402 -> {
                return NetworkResult.Error("API Key limited")
            }
            response.body()!!.results.isNullOrEmpty() -> {
                return NetworkResult.Error("Recipes Not Found")
            }
            response.isSuccessful -> {
                val foodRecipe = response.body()
                return NetworkResult.Success(foodRecipe!!)
            }
            else -> {
                return NetworkResult.Error(response.message())
            }
        }
    }

    private fun hasInternetConnection(): Boolean {

        val connectivityManager =
            getApplication<Application>().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities =
                connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            val networkInfo = connectivityManager.activeNetworkInfo ?: return false
            return networkInfo.isAvailable && networkInfo.isConnected
        }

    }


}