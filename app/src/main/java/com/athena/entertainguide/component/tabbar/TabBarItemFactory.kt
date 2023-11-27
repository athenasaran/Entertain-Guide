package com.athena.entertainguide.component.tabbar

import android.content.res.Resources
import com.athena.entertainguide.component.tabbar.customviews.TabBarItem

/**
 * Factory class for creating TabBarItem instances.
 *
 * @property resources The Resources instance to access localized strings and resources.
 */
internal class TabBarItemFactory(private val resources: Resources) {

    /**
     * Creates a list of HomeNavigationItem instances based on the HomeBottomNavigationItemEnum values and their feature toggles.
     *
     * @return List of HomeNavigationItem instances filtered by the feature toggles.
     */
    fun createItems(): List<TabBarItem> {
        val menuItems = TabBarItemEnum.values().map { item ->
            TabBarItem(
                iconRes = item.iconResId,
                text = resources.getString(item.titleResId),
                itemID = item.ordinal,
                isEnabled = true, //on future feature toggle that can be used
                enum = item
            )
        }.filter { it.isEnabled }

        return menuItems
    }
}