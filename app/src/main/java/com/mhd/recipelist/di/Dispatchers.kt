package com.mhd.recipelist.di

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val niaDispatcher: Dispatchers)

enum class Dispatchers {
    IO
}
