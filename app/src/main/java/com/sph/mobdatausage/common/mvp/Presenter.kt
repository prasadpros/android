package com.sph.mobdatausage.common.mvp

interface Presenter<V : BaseView> {
    fun attachView(mvpView: V)
    fun detachView()
}
