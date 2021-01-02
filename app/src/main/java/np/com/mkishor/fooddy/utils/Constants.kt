package np.com.mkishor.fooddy.utils


/**
 * @author:  Kishor Mainali
 * @date:  02/01/2021 15:46
 * @email:  mainalikishor@outlook.com
 *
 */
class Constants {
    companion object {
        const val BASE_URL = "https://api.spoonacular.com"
        const val API_KEY = "87fadbb5cb9744d593e1897f92c9bd65"

        //API Query Keys


        const val QUERY_NUMBER = "number"
        const val QUERY_API_KEY = "apiKey"
        const val QUERY_TYPE = "type"
        const val QUERY_DIET = "diet"
        const val QUERY_RECIPE_INFORMATION = "addRecipeInformation"
        const val QUERY_FILL_INGREDIENTS = "fillIngredients"

        //ROOM database
        const val DATABASE_NAME = "recipes_database.db"
        const val RECIPE_TABLE = "recipes"
    }
}