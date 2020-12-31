package odds.team.todolist.add

import odds.team.todolist.model.LocalDataSource
import odds.team.todolist.model.Task

class AddTaskPresenter(
    private val view: AddTaskContract.View,
    private val dataSource: LocalDataSource
) : AddTaskContract.Presenter {
    override fun addTask(title: String) {
        if (title.isEmpty()) {
            view.displayError("Task title cannot be empty")
        } else {
            val task = Task(title = title)
            dataSource.insert(task)
            view.returnToMainScreen()
        }
    }

}