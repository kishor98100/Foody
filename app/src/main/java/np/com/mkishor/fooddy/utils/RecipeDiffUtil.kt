package np.com.mkishor.fooddy.utils

import androidx.recyclerview.widget.DiffUtil
import np.com.mkishor.fooddy.data.models.FoodResult


/**
 * @author:  Kishor Mainali
 * @date:  02/01/2021 19:21
 * @email:  mainalikishor@outlook.com
 *
 */
class RecipeDiffUtil(
    private val oldList: List<FoodResult>,
    private val newList: List<FoodResult>,
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] === newList[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]
}