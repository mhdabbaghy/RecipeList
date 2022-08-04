package com.mhd.recipelist.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {
    @Provides
    @Dispatcher(com.mhd.recipelist.di.Dispatchers.IO)
    fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO
}
