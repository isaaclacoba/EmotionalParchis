package com.multimediateam.parchisemocional.di

import android.content.Context
import com.multimediateam.parchisemocional.MainContract
import com.multimediateam.parchisemocional.data.DatabaseBuilder
import com.multimediateam.parchisemocional.data.DatabaseHelper
import com.multimediateam.parchisemocional.data.DatabaseHelperImpl
import com.multimediateam.parchisemocional.interactor.IMainInteractor
import com.multimediateam.parchisemocional.network.INetworkClient
import com.multimediateam.parchisemocional.repository.IDatabaseDataSource
import com.multimediateam.parchisemocional.repository.IMainRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier
import javax.inject.Singleton


@Module(includes = [DatabaseModule::class, CoroutineDispatcherModule::class])
@InstallIn(ApplicationComponent::class)
abstract class ParchisModule {
    @Binds
    abstract fun bindMainInteractor(
            iMainInteractor: IMainInteractor): MainContract.MainInteractor

    @Binds
    abstract fun bindMainRepository(
        iMainRepository: IMainRepository): MainContract.MainRepository

    @Binds
    abstract fun bindNetworkClient(INetworkClient: INetworkClient): MainContract.NetworkClient

    @Binds
    abstract fun bindDatabaseDataSource(
        iDatabaseDataSource: IDatabaseDataSource): MainContract.DatabaseDataSource
}

//From: https://www.valueof.io/blog/injecting-coroutines-dispatchers-with-dagger
@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class ViewModelScope

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class DefaultDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class IoDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MainDispatcher

@Module
@InstallIn(ApplicationComponent::class)
internal class CoroutineDispatcherModule {
    @DefaultDispatcher
    @Provides
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @IoDispatcher
    @Provides
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @MainDispatcher
    @Provides
    fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main
}

@Module
@InstallIn(ApplicationComponent::class)
internal class DatabaseModule {

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class DatabaseDataSource

    @Provides
    @Singleton
    fun providesDatabaseHelper(@ApplicationContext appContext: Context): DatabaseHelper =
        DatabaseHelperImpl(
            DatabaseBuilder.getInstance(
                appContext
            ))

    @DatabaseDataSource
    @Singleton
    @Provides
    fun providesDatabaseDataSource(
        emotionDB: DatabaseHelper): IDatabaseDataSource =
        IDatabaseDataSource(emotionDB)
}