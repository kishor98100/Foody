package np.com.mkishor.fooddy.di.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import np.com.mkishor.fooddy.data.sources.local.FoodRecipeDao
import np.com.mkishor.fooddy.data.sources.local.RecipeDatabase
import np.com.mkishor.fooddy.utils.Constants.Companion.DATABASE_NAME
import javax.inject.Singleton


/**
 * @author:  Kishor Mainali
 * @date:  02/01/2021 20:39
 * @email:  mainalikishor@outlook.com
 *
 */
@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(
        @ApplicationContext context: Context
    ): RecipeDatabase =
        Room.databaseBuilder(context, RecipeDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration().build()


    @Singleton
    @Provides
    fun provideDao(database: RecipeDatabase): FoodRecipeDao = database.dao()

}