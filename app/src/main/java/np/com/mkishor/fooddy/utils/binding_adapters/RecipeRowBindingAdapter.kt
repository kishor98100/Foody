package np.com.mkishor.fooddy.utils.binding_adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import coil.load
import np.com.mkishor.fooddy.R


/**
 * @author:  Kishor Mainali
 * @date:  02/01/2021 19:29
 * @email:  mainalikishor@outlook.com
 *
 */
class RecipeRowBindingAdapter {

    companion object {

        @BindingAdapter("app:setLikes")
        @JvmStatic
        fun setNumberOfLikes(textView: TextView, likes: Int) {
            textView.text = likes.toString()
        }

        @BindingAdapter("app:setMinutes")
        @JvmStatic
        fun setNumberOfMinutes(textView: TextView, minutes: Int) {
            textView.text = minutes.toString()
        }


        @BindingAdapter("app:veganColor")
        @JvmStatic
        fun applyVeganColor(view: View, vegan: Boolean) {
            if (vegan) {
                when (view) {
                    is TextView -> {
                        view.run {
                            setTextColor(
                                ContextCompat.getColor(context, R.color.green)
                            )
                        }
                    }
                    is ImageView -> {
                        view.run {
                            setColorFilter(
                                ContextCompat.getColor(context, R.color.green)
                            )
                        }
                    }
                }
            }
        }

        @BindingAdapter("app:loadImage")
        @JvmStatic
        fun loadImageFromUrl(imageView: ImageView, imageUrl: String) {
            imageView.load(imageUrl) {
                crossfade(600)
                error(R.drawable.ic_error)
            }
        }

    }


}