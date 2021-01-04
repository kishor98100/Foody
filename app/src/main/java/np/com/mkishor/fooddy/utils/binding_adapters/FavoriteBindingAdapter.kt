package np.com.mkishor.fooddy.utils.binding_adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
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
        const val TAG = "FAVORITE_BINDING_ADAPTER"


        @BindingAdapter("app:setData", "app:adapter", requireAll = false)
        @JvmStatic
        fun setDataAndViewVisibility(
            view: View,
            favorites: List<FavoriteRecipeEntity>?,
            adapter: FavoriteRecipeAdapter?
        ) {
            if (favorites.isNullOrEmpty()) {
                when (view) {
                    is ImageView -> {
                        view.run {
                            visibility = View.VISIBLE
                        }
                    }
                    is TextView -> {
                        view.run {
                            visibility = View.VISIBLE
                        }
                    }
                    is RecyclerView -> {
                        view.run {
                            visibility = View.GONE
                        }
                    }
                }
            } else {
                when (view) {
                    is ImageView -> {
                        view.run {
                            visibility = View.GONE
                        }
                    }
                    is TextView -> {
                        view.run {
                            visibility = View.GONE
                        }
                    }
                    is RecyclerView -> {
                        view.run {
                            visibility = View.VISIBLE
                            adapter?.setData(favorites)
                        }
                    }
                }
            }
        }

    }
}