package com.koresuniku.wishmaster_v4.application

import android.app.Application
import com.bumptech.glide.Glide
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.model.GlideUrl
import com.koresuniku.wishmaster_v4.core.dagger.component.DaggerApplicationComponent
import com.koresuniku.wishmaster_v4.core.dagger.component.DaggerDashboardComponent
import com.koresuniku.wishmaster_v4.core.dagger.component.DaggerSharedPreferencesComponent
import com.koresuniku.wishmaster_v4.core.dagger.component.DaggerThreadListComponent
import com.koresuniku.wishmaster_v4.core.dagger.module.*
import com.koresuniku.wishmaster_v4.core.domain.Dvach
import com.koresuniku.wishmaster_v4.core.domain.client.RetrofitHolder
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

    private lateinit var mDaggerDashboardComponent: DaggerDashboardComponent
    private lateinit var mDaggerThreadListComponent: DaggerThreadListComponent
    private lateinit var mDaggerApplicationComponent: DaggerApplicationComponent
    private lateinit var mSharedPreferencesComponent: DaggerSharedPreferencesComponent

    private lateinit var mAppModule: AppModule
    private lateinit var mNetModule: NetModule
    private lateinit var mDatabaseModule: DatabaseModule
    private lateinit var mSharedPreferencesModule: SharedPreferencesModule
    private lateinit var mSharedPreferencesUIParamsModule: SharedPreferencesUIParamsModule

    //@Inject lateinit var okHttpClient: OkHttpClient
    //@Inject lateinit var sharedPreferencesStorage: SharedPreferencesStorage
    //@Inject lateinit var retrofitHolder: RetrofitHolder
    //@Inject lateinit var sharedPreferencesUIParams: SharedPreferencesUIParams

    override fun onCreate() {
        super.onCreate()

        if (!LeakCanary.isInAnalyzerProcess(this)) LeakCanary.install(this)

        mAppModule = AppModule(this)
        mNetModule = NetModule(this)
        mDatabaseModule = DatabaseModule(this)
        mSharedPreferencesModule = SharedPreferencesModule(this)
        mSharedPreferencesUIParamsModule = SharedPreferencesUIParamsModule()

        mDaggerApplicationComponent = DaggerApplicationComponent.builder()
                .appModule(mAppModule)
                .netModule(mNetModule)
                .build() as DaggerApplicationComponent
        mDaggerApplicationComponent.inject(this)

        mSharedPreferencesComponent = DaggerSharedPreferencesComponent.builder()
                .sharedPreferencesModule(mSharedPreferencesModule)
                .sharedPreferencesUIParamsModule(mSharedPreferencesUIParamsModule)
                .build() as DaggerSharedPreferencesComponent

        SharedPreferencesInteractor.onApplicationCreate(this,
                sharedPreferencesStorage, retrofitHolder, sharedPreferencesUIParams)

        mDaggerDashboardComponent = DaggerDashboardComponent.builder()
                .appModule(mAppModule)
                .dashboardModule(DashboardModule())
                .databaseModule(mDatabaseModule)
                .netModule(mNetModule)
                .build() as DaggerDashboardComponent

        mDaggerThreadListComponent = DaggerThreadListComponent.builder()
                .appModule(mAppModule)
                .threadListModule(ThreadListModule())
                .databaseModule(mDatabaseModule)
                .netModule(mNetModule)
                .build() as DaggerThreadListComponent



        Glide.get(this).register(
                GlideUrl::class.java,
                InputStream::class.java,
                OkHttpUrlLoader.Factory(okHttpClient))

    }

    fun getDashBoardComponent() = mDaggerDashboardComponent
    fun getThreadListComponent() = mDaggerThreadListComponent
}