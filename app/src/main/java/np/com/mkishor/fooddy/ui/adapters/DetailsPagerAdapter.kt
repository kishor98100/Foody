package np.com.mkishor.fooddy.ui.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


/**
 * @author:  Kishor Mainali
 * @date:  03/01/2021 14:19
 * @email:  mainalikishor@outlook.com
 *
 */
class DetailsPagerAdapter(
    private val resultBundle: Bundle,
    private val fragments: ArrayList<Fragment>,
    private val title: ArrayList<String>,
    fm: FragmentManager
) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getCount(): Int = fragments.size

    override fun getItem(position: Int): Fragment {
        fragments[position].arguments = resultBundle
        return fragments[position]
    }

    override fun getPageTitle(position: Int): CharSequence? = title[position]
}