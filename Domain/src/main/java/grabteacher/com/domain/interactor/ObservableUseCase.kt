package grabteacher.com.domain.interactor

import grabteacher.com.domain.executor.PostExecutionThread
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

abstract class ObservableUseCase<T, in Params> constructor(private var postExecutionThread: PostExecutionThread) {

    private val disposables = CompositeDisposable()

    protected abstract fun buildUseCaseObservable(params: Params? = null): Observable<T>


    open fun execute(observer: DisposableObserver<T>, params: Params? = null) {
        val observable = this.buildUseCaseObservable(params)
                .subscribeOn(Schedulers.io())
                .observeOn(postExecutionThread.scheduler)

        addDisposable(observable.subscribeWith(observer))

    }

    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    private fun dispose(){
        disposables.dispose()
    }

}