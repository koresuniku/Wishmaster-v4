package com.koresuniku.wishmaster.core.base

import com.koresuniku.wishmaster.application.WishmasterApplication

/**
 * Created by koresuniku on 26.12.17.
 */

interface IActivityMvpView : IMvpView {
    fun getWishmasterApplication(): WishmasterApplication
}