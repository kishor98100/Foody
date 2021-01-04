package np.com.mkishor.fooddy.utils.binding_adapters

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import np.com.mkishor.fooddy.data.entities.FavoriteRecipeEntity
import np.com.mkishor.fooddy.ui.adapters.FavoriteRecipeAdapter


/**
 * @author:  Kishor Mainali
 * @date:  04/01/2021 12:36
 * @email:  mainalikishor@outlook.com
 *
 */
class FavoriteBindingAdapter {
    companion object {

        @BindingAdapter("app:setData", "app:adapter", requireAll = false)
        @JvmStatic
        fun setDataAndViewVisibility(
            view: View,
            favorites: List<FavoriteRecipeEntity>?,
            adapter: FavoriteRecipeAdapter?
        ) {


            if (favorites.isNullOrEmpty()) {

                view.run {
                    visibility = if (this is RecyclerView) {
                        View.GONE
                    } else {
                        View.VISIBLE
                    }
                }

            } else {
                view.run {
                    visibility = if (this is RecyclerView) {
                        adapter?.setData(favorites)
                        View.VISIBLE
                    } else {
                        View.GONE
                    }
                }

            }
        }

    }
}