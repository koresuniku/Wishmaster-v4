package com.koresuniku.wishmaster.core.base

/**
 * Created by koresuniku on 03.10.17.
 */

interface IPresenter<in V: IMvpView> {
    fun bindView(mvpView: V)

    fun unbindView()
}