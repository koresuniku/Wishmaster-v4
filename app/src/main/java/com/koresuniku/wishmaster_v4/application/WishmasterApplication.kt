package com.koresuniku.wishmaster_v4.application

import android.app.Application
import com.koresuniku.wishmaster_v4.core.dagger.component.DaggerDashboardComponent
import com.koresuniku.wishmaster_v4.core.dagger.component.DaggerThreadListComponent
import com.koresuniku.wishmaster_v4.core.dagger.module.*
import com.koresuniku.wishmaster_v4.core.domain.Dvach

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

    override fun onCreate() {
        super.onCreate()
        mDaggerDashboardComponent = DaggerDashboardComponent.builder()
                .appModule(AppModule(this))
                .dashboardModule(DashboardModule())
                .databaseModule(DatabaseModule())
                .netModule(NetModule(Dvach.BASE_URL))
                .build() as DaggerDashboardComponent

        mDaggerThreadListComponent = DaggerThreadListComponent.builder()
                .appModule(AppModule(this))
                .threadListModule(ThreadListModule())
                .databaseModule(DatabaseModule())
                .netModule(NetModule(Dvach.BASE_URL))
                .build() as DaggerThreadListComponent
    }

    fun getDashBoardComponent() = mDaggerDashboardComponent

    fun getThreadListComponent() = mDaggerThreadListComponent
}