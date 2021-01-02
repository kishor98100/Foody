package np.com.mkishor.fooddy.ui.fragments.recipes.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import np.com.mkishor.fooddy.R


/**
 * @author:  Kishor Mainali
 * @date:  02/01/2021 21:41
 * @email:  mainalikishor@outlook.com
 *
 */
class RecipeBottomSheet : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.recipes_bottom_sheet, container, false)
    }
}