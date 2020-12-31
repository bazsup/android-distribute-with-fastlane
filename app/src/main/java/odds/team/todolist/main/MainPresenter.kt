package odds.team.todolist.main

import android.util.Log
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import odds.team.todolist.model.LocalDataSource
import odds.team.todolist.model.Task

class MainPresenter(
    private val view: MainContract.View,
    private val dataSource: LocalDataSource
) : MainContract.Presenter {
    private val TAG = "MainPresenter"
    private val compositeDisposable = CompositeDisposable()

    private val myTasksObservable: Observable<List<Task>>
        get() = dataSource.allTasks

    private val observer: DisposableObserver<List<Task>>
        get() = object : DisposableObserver<List<Task>>() {

            override fun onNext(taskList: List<Task>) {
                if (taskList.isEmpty()) {
                    view.displayNoTask()
                } else {
                    view.displayTasks(taskList)
                }
            }

            override fun onError(@NonNull e: Throwable) {
                Log.d(TAG, "Error$e")
                e.printStackTrace()
                view.displayError("Error fetching task list")
            }

            override fun onComplete() {
                //Log.d(TAG, "Completed")
            }
        }


    override fun getMyTodoList() {
        val myTasksDisposable = myTasksObservable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(observer)

        compositeDisposable.add(myTasksDisposable)
    }

    override fun stop() {
        compositeDisposable.clear()
    }

}