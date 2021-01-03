package np.com.mkishor.fooddy.utils

import androidx.recyclerview.widget.DiffUtil
import np.com.mkishor.fooddy.data.models.ExtendedIngredient


/**
 * @author:  Kishor Mainali
 * @date:  03/01/2021 19:54
 * @email:  mainalikishor@outlook.com
 *
 */
class IngredientDiffUtil(
    private val oldList: List<ExtendedIngredient>,
    private val newList: List<ExtendedIngredient>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] === newList[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]
}