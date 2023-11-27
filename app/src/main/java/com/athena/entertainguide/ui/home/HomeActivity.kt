package com.athena.entertainguide.ui.home

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.athena.entertainguide.component.tabbar.TabBarItemEnum
import com.athena.entertainguide.component.tabbar.TabBarItemFactory
import com.athena.entertainguide.component.tabbar.customviews.TabBarItem
import com.athena.entertainguide.databinding.ActivityHomeBinding
import com.athena.entertainguide.ui.BaseActivity
import com.athena.entertainguide.ui.initial.InitialFragment
import com.athena.entertainguide.utils.notifications.checkNotificationPermission
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : BaseActivity<ActivityHomeBinding>() {

    override val binding: ActivityHomeBinding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }

    private val tabBarItemFactory: TabBarItemFactory by inject()
    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBottomNavigation(savedInstanceState != null)
        bindElements()
        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    override fun onStart() {
        super.onStart()
        if (homeViewModel.shouldCheckForNotificationPermission()) {
            checkNotificationPermission {
                homeViewModel.notificationPermissionChecked()
            }
        }
    }

    private fun bindElements() {
        homeViewModel.tabBarPosition.bind(::bindTabBarPosition)
    }

    private fun bindTabBarPosition(position: Int) {
        when (position) {
            TabBarItemEnum.HOME.ordinal -> showDefaultFragment(InitialFragment.newInstance())
            TabBarItemEnum.ENTERTAINMENT.ordinal -> showDefaultFragment(InitialFragment.newInstance())
            TabBarItemEnum.LIST.ordinal -> showDefaultFragment(InitialFragment.newInstance())
            TabBarItemEnum.PROFILE.ordinal -> showDefaultFragment(InitialFragment.newInstance())
        }
    }

    private fun setupBottomNavigation(recreated: Boolean) = with(binding) {
        val items = tabBarItemFactory.createItems()
        tabBarView.setItems(items, TabBarItemEnum.HOME.ordinal, recreated)
        tabBarView.setMenuClickListener(::onTabBarItemSelected)
    }

    private fun onTabBarItemSelected(tabBarItem: TabBarItem) {
        homeViewModel.onTabSelected(tabBarItem.itemID)
    }

    private fun showDefaultFragment(selectedFragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, selectedFragment)
            .commitNow()
    }

    private val onBackPressedCallback: OnBackPressedCallback =
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (homeViewModel.tabBarPosition.value == TabBarItemEnum.HOME.ordinal) {
                    finish()
                } else {
                    binding.tabBarView.setSelectedItem(TabBarItemEnum.HOME.ordinal)
                }
            }
        }
}