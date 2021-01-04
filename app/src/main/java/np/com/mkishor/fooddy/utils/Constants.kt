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
        const val IMAGE_BASE_URL = "https://spoonacular.com/cdn/ingredients_100x100/"
        const val API_KEY = "87fadbb5cb9744d593e1897f92c9bd65"

        //API Query Keys
        const val QUERY_NUMBER = "number"
        const val QUERY_SEARCH = "query"
        const val QUERY_API_KEY = "apiKey"
        const val QUERY_TYPE = "type"
        const val QUERY_DIET = "diet"
        const val QUERY_RECIPE_INFORMATION = "addRecipeInformation"
        const val QUERY_FILL_INGREDIENTS = "fillIngredients"

        //ROOM database
        const val DATABASE_NAME = "recipes_database.db"
        const val RECIPE_TABLE = "recipes"
        const val FAVORITE_RECIPE_TABLE = "favorites"
        const val FOOD_JOKE_TABLE = "food_jokes"


        // Bottom Sheet Preferences
        const val PREFERENCE_NAME = "fooddy_preference"
        const val DEFAULT_MEAL_TYPE = "main course"
        const val DEFAULT_DIET_TYPE = "gluten free"
        const val DEFAULT_RECIPE_NUMBER = "50"
        const val PREFERENCE_MEAL_TYPE = "mealType"
        const val PREFERENCE_MEAL_TYPE_ID = "mealTypeId"
        const val PREFERENCE_DIET_TYPE = "dietType"
        const val PREFERENCE_DIET_TYPE_ID = "dietTypeId"


        //recipes
        const val RECIPE_BUNDLE_NAME = "recipeBundle"
    }
}