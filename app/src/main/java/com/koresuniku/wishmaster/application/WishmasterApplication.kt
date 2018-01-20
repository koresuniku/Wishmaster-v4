package com.koresuniku.wishmaster.application

import android.app.Application
import com.bumptech.glide.Glide
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.model.GlideUrl
import com.koresuniku.wishmaster.core.dagger.component.*
import com.koresuniku.wishmaster.core.dagger.module.*
import com.koresuniku.wishmaster.core.domain.client.RetrofitHolder
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

class WishmasterApplication : Application() {

    private lateinit var mDaggerNetComponent: DaggerNetComponent
    private lateinit var mDaggerDatabaseComponent: DaggerDatabaseComponent
    private lateinit var mDaggerSharedPreferencesComponent: DaggerSharedPreferencesComponent
    private lateinit var mDaggerDashboardComponent: DaggerDashboardComponent
    private lateinit var mDaggerThreadListComponent: DaggerThreadListComponent

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

        mDaggerNetComponent = DaggerNetComponent.builder()
                .netModule(mNetModule)
                .build() as DaggerNetComponent
        mDaggerDatabaseComponent = DaggerDatabaseComponent.builder()
                .databaseModule(mDatabaseModule)
                .build() as DaggerDatabaseComponent
        mDaggerSharedPreferencesComponent = DaggerSharedPreferencesComponent.builder()
                .sharedPreferencesModule(mSharedPreferencesModule)
                .sharedPreferencesUIParamsModule(mSharedPreferencesUIParamsModule)
                .build() as DaggerSharedPreferencesComponent
        mDaggerDashboardComponent = DaggerDashboardComponent.builder()
                .dashboardModule(DashboardModule())
                .build() as DaggerDashboardComponent
        mDaggerThreadListComponent = DaggerThreadListComponent.builder()
                .threadListModule(ThreadListModule())
                .build() as DaggerThreadListComponent

        mDaggerNetComponent.inject(this)
        mDaggerSharedPreferencesComponent.inject(this)

        SharedPreferencesInteractor.onApplicationCreate(this,
                sharedPreferencesStorage, retrofitHolder, sharedPreferencesUIParams)

        Glide.get(this).register(
                GlideUrl::class.java,
                InputStream::class.java,
                OkHttpUrlLoader.Factory(okHttpClient))
    }

    fun getNetComponent() = mDaggerNetComponent
    fun getDatabaseComponent() = mDaggerDatabaseComponent
    fun getSharedPreferencesComponent() = mDaggerSharedPreferencesComponent
    fun getDashboardComponent() = mDaggerDashboardComponent
    fun getThreadListComponent() = mDaggerThreadListComponent
}