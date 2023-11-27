package com.athena.entertainguide.component.tabbar.customviews

import androidx.annotation.DrawableRes
import com.athena.entertainguide.component.tabbar.TabBarItemEnum

internal data class TabBarItem(
    @DrawableRes val iconRes: Int,
    val text: String,
    val itemID: Int,
    val isEnabled: Boolean,
    val enum: TabBarItemEnum
)
