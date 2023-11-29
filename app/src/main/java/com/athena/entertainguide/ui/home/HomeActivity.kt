package com.athena.entertainguide.ui.home

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.athena.entertainguide.component.tabbar.TabBarItemEnum
import com.athena.entertainguide.component.tabbar.TabBarItemFactory
import com.athena.entertainguide.component.tabbar.customviews.TabBarItem
import com.athena.entertainguide.databinding.ActivityHomeBinding
import com.athena.entertainguide.sharedPreferences.SharedPreferencesManager
import com.athena.entertainguide.sharedPreferences.keys.SharedPreferencesKeys
import com.athena.entertainguide.ui.base.BaseActivity
import com.athena.entertainguide.ui.entertainment.EntertainmentFragment
import com.athena.entertainguide.ui.initial.InitialFragment
import com.athena.entertainguide.ui.login.LoginFragment
import com.athena.entertainguide.ui.profile.ProfileFragment
import com.athena.entertainguide.ui.savelist.SaveListFragment
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : BaseActivity<ActivityHomeBinding>() {

    override val binding: ActivityHomeBinding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }

    private val tabBarItemFactory: TabBarItemFactory by inject()
    private val homeViewModel: HomeViewModel by viewModel()
    private val sharedPreferences: SharedPreferencesManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBottomNavigation(savedInstanceState != null)
        bindElements()
        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    private fun bindElements() {
        homeViewModel.tabBarPosition.bind(::bindTabBarPosition)
    }

    private fun bindTabBarPosition(position: Int) {
        when (position) {
            TabBarItemEnum.HOME.ordinal -> showDefaultFragment(InitialFragment.newInstance())
            TabBarItemEnum.ENTERTAINMENT.ordinal -> showDefaultFragment(EntertainmentFragment.newInstance())
            TabBarItemEnum.LIST.ordinal -> showDefaultFragment(SaveListFragment.newInstance())
            TabBarItemEnum.PROFILE.ordinal -> setupProfileOrLogin()
        }
    }

    private fun setupBottomNavigation(recreated: Boolean) = with(binding) {
        val items = tabBarItemFactory.createItems()
        tabBarView.setItems(items, TabBarItemEnum.HOME.ordinal, recreated)
        tabBarView.setMenuClickListener(::onTabBarItemSelected)
    }

    private fun setupProfileOrLogin() {
        val userLogged = sharedPreferences.getBoolean(SharedPreferencesKeys.LOGGED_USER.key)
        if (userLogged) {
            showDefaultFragment(ProfileFragment.newInstance())
        } else {
            showDefaultFragment(LoginFragment.newInstance())
        }
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