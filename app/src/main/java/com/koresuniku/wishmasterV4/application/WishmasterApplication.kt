package com.koresuniku.wishmasterV4.application

import android.app.Application
import com.bumptech.glide.Glide
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.model.GlideUrl
import com.koresuniku.wishmasterV4.core.dagger.component.*
import com.koresuniku.wishmasterV4.core.dagger.module.*
import com.koresuniku.wishmasterV4.core.domain.client.RetrofitHolder
import com.squareup.leakcanary.LeakCanary
import okhttp3.OkHttpClient
import java.io.InputStream
import javax.inject.Inject

/**
 * Created by koresuniku on 03.10.17.
 */

/**
 * Ребята, не стоит вскрывать этот код. Вы молодые, шутливые, вам все легко. Это не то.
 * Это не Чикатило и даже не архивы спецслужб. Сюда лучше не лезть. Серьезно, любой из вас будет жалеть.
 * Лучше закройте код и забудьте, что тут писалось. Я вполне понимаю, что данным сообщением вызову дополнительный интерес,
 * но хочу сразу предостеречь пытливых - стоп. Остальные просто не найдут.
 */

class WishmasterApplication @Inject constructor() : Application() {

    private lateinit var daggerNetComponent: DaggerNetComponent
    private lateinit var daggerDatabaseComponent: DaggerDatabaseComponent
    private lateinit var daggerSharedPreferencesComponent: DaggerSharedPreferencesComponent
    private lateinit var daggerDashboardComponent: DaggerDashboardComponent
    private lateinit var daggerThreadListComponent: DaggerThreadListComponent

    private lateinit var mNetModule: NetModule
    private lateinit var mDatabaseModule: DatabaseModule
    private lateinit var mSharedPreferencesModule: SharedPreferencesModule
    private lateinit var mSharedPreferencesUIParamsModule: SharedPreferencesUIParamsModule

    @Inject lateinit var okHttpClient: OkHttpClient
    @Inject lateinit var retrofitHolder: RetrofitHolder
    @Inject lateinit var sharedPreferencesStorage: SharedPreferencesStorage
    @Inject lateinit var sharedPreferencesUIParams: SharedPreferencesUIParams

    override fun onCreate() {
        super.onCreate()

        if (!LeakCanary.isInAnalyzerProcess(this)) LeakCanary.install(this)

        mNetModule = NetModule(this)
        mDatabaseModule = DatabaseModule(this)
        mSharedPreferencesModule = SharedPreferencesModule(this)
        mSharedPreferencesUIParamsModule = SharedPreferencesUIParamsModule()

        daggerNetComponent = DaggerNetComponent.builder()
                .netModule(mNetModule)
                .build() as DaggerNetComponent
        daggerDatabaseComponent = DaggerDatabaseComponent.builder()
                .databaseModule(mDatabaseModule)
                .build() as DaggerDatabaseComponent
        daggerSharedPreferencesComponent = DaggerSharedPreferencesComponent.builder()
                .sharedPreferencesModule(mSharedPreferencesModule)
                .sharedPreferencesUIParamsModule(mSharedPreferencesUIParamsModule)
                .build() as DaggerSharedPreferencesComponent

        daggerNetComponent.inject(this)
        daggerSharedPreferencesComponent.inject(this)

        SharedPreferencesInteractor.onApplicationCreate(this,
                sharedPreferencesStorage, retrofitHolder, sharedPreferencesUIParams)

        daggerDashboardComponent = DaggerDashboardComponent.builder()
                .dashboardModule(DashboardModule())
                .build() as DaggerDashboardComponent
        daggerThreadListComponent = DaggerThreadListComponent.builder()
                .threadListModule(ThreadListModule())
                .build() as DaggerThreadListComponent

        Glide.get(this).register(
                GlideUrl::class.java,
                InputStream::class.java,
                OkHttpUrlLoader.Factory(okHttpClient))
    }

    fun getDaggerNetComponent() = daggerNetComponent
    fun getDaggerDatabaseComponent() = daggerDatabaseComponent
    fun getDaggerSharedPreferencesComponent() = daggerSharedPreferencesComponent
    fun getDaggerDashboardComponent() = daggerDashboardComponent
    fun getDaggerThreadListComponent() = daggerThreadListComponent
}