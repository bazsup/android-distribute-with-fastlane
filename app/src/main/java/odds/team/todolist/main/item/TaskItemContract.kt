package odds.team.todolist.main.item

import odds.team.todolist.model.Task

interface TaskItemContract {
    interface View {
        fun setPresenter(presenter: Presenter)
    }

    interface Presenter {
        fun updateTask(task: Task)
    }
}