package com.athena.entertainguide.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.athena.entertainguide.sharedPreferences.SharedPreferencesManager
import com.athena.entertainguide.sharedPreferences.keys.SharedPreferencesKeys.PUSH_NOTIFICATION_CHECKED
import com.athena.entertainguide.ui.base.BaseViewModel
import com.athena.entertainguide.utils.date.DateProvider
import java.util.concurrent.TimeUnit

internal class HomeViewModel(
    private val sharedPreference: SharedPreferencesManager,
    private val dateProvider: DateProvider
) : BaseViewModel() {

    private val _tabBarPosition = MutableLiveData<Int>()
    val tabBarPosition: LiveData<Int> = _tabBarPosition

    fun onTabSelected(position: Int) {
        _tabBarPosition.value = position
    }

    fun shouldCheckForNotificationPermission(): Boolean =
        if (sharedPreference.contains(PUSH_NOTIFICATION_CHECKED.key)) {
            val lastCheckTime = sharedPreference.getLong(PUSH_NOTIFICATION_CHECKED.key)
            val oneDayInMs = TimeUnit.DAYS.toMillis(1)
            dateProvider.currentDate.time > lastCheckTime + oneDayInMs
        } else {
            true
        }

    fun notificationPermissionChecked() {
        sharedPreference.putLong(PUSH_NOTIFICATION_CHECKED.key, dateProvider.currentDate.time)
    }
}