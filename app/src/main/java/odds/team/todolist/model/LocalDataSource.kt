package odds.team.todolist.model

import android.app.Application
import io.reactivex.Observable

import kotlin.concurrent.thread


open class LocalDataSource(application: Application) {
    private val taskDao: TaskDao
    open val allTasks: Observable<List<Task>>

    init {
        val db = LocalDatabase.getInstance(application)
        taskDao = db.taskDao()
        allTasks = taskDao.all
    }

    open fun insert(task: Task) {
        thread {
            taskDao.insert(task)
        }
    }

    open fun update(task: Task) {
        thread {
            taskDao.update(task)
        }
    }
}