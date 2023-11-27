package com.athena.entertainguide.component.tabbar

import com.athena.entertainguide.R

internal enum class TabBarItemEnum(
    val iconResId: Int,
    val titleResId: Int,
) {
    HOME(
        iconResId = R.drawable.ic_home,
        titleResId = R.string.home
    ),
    ENTERTAINMENT(
        iconResId = R.drawable.ic_popcorn,
        titleResId = R.string.entertainment
    ),
    LIST(
        iconResId = R.drawable.ic_list,
        titleResId = R.string.list
    ),
    PROFILE(
        iconResId = R.drawable.ic_profile,
        titleResId = R.string.profile
    )
}