package odds.team.todolist.main.item

import odds.team.todolist.model.LocalDataSource
import odds.team.todolist.model.Task

class TaskItemPresenter(
    private val view: TaskItemContract.View,
    private val dataSource: LocalDataSource
) : TaskItemContract.Presenter {
    override fun updateTask(task: Task) {
        dataSource.update(task)
    }

}
