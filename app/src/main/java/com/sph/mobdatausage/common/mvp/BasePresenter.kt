package com.sph.mobdatausage.common.mvp

import io.reactivex.disposables.CompositeDisposable

open class BasePresenter<T : BaseView> : Presenter<T> {

    var view: T? = null
    val compositeDisposable = CompositeDisposable()

    override fun attachView(mvpView: T) {
        this.view = mvpView
    }

    override fun detachView() {
        view = null
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.clear()
        }
    }
}
