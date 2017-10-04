package com.koresuniku.wishmaster_v4.core.base

/**
 * Created by koresuniku on 03.10.17.
 */

open class BasePresenter<in V: IMvpView> : IPresenter<V> {
    var mView: IMvpView? = null

    override fun attachView(mvpView: V) {
        this.mView = mvpView
    }

    override fun detachView() {
        this.mView = null
    }
}