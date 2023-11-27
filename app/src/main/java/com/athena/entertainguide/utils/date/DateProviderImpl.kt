package com.athena.entertainguide.utils.date

import java.util.Calendar
import java.util.Date

class DateProviderImpl : DateProvider {

    override val currentDate: Date
        get() = Calendar.getInstance().time
}