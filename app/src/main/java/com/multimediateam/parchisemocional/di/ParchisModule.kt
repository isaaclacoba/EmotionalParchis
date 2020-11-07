package com.multimediateam.parchisemocional.di

import android.content.Context
import com.multimediateam.parchisemocional.MainContract
import com.multimediateam.parchisemocional.data.DatabaseBuilder
import com.multimediateam.parchisemocional.data.DatabaseHelper
import com.multimediateam.parchisemocional.data.DatabaseHelperImpl
import com.multimediateam.parchisemocional.interactor.IMainInteractor
import com.multimediateam.parchisemocional.presenter.IMainPresenter
import com.multimediateam.parchisemocional.repository.IDatabaseDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Qualifier


@Module(includes = [DatabaseModule::class])
@InstallIn(ApplicationComponent::class)
abstract class ParchisModule {

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class DatabaseDataSource

    @Binds
    abstract fun bindMainPresenter(
        iMainPresenter: IMainPresenter
    ): MainContract.MainPresenter


    @Binds
    abstract fun bindMainInteractor(
            iMainInteractir: IMainInteractor): MainContract.MainInteractor

    @Binds
    @DatabaseDataSource
    abstract fun bindDataBaseDatasource(
        databaseDataSource: IDatabaseDataSource): MainContract.DatabaseDataSource
}

@Module
@InstallIn(ApplicationComponent::class)
internal class DatabaseModule {
    @Provides
    fun providesDatabaseHelper(@ApplicationContext appContext: Context): DatabaseHelper =
        DatabaseHelperImpl(
            DatabaseBuilder.getInstance(
                appContext
            ))
}