package com.athena.entertainguide.utils.numbers

import java.math.MathContext

fun toBigDecimalWithPrecision(number: Double?): Number =
    number?.toBigDecimal()?.round(MathContext(2)) ?: 0.0
