package com.nghia.androidlivedata

import java.util.*

/**
 * Created by nghia.vuong on 07,May,2021
 */
fun IntRange.random() = (Random().nextInt((endInclusive + 1) - start) + start)
    .toString().padStart(4, '0')