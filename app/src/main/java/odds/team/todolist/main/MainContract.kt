package odds.team.todolist.main

import odds.team.todolist.model.Task

interface MainContract {
    interface View {
        fun displayTasks(taskList: List<Task>)
        fun displayNoTask()
        fun showToast(message: String)
        fun displayError(error: String)
    }

    interface Presenter {
        fun getMyTodoList()
        fun stop()
    }
}