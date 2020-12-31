package odds.team.todolist.add

interface AddTaskContract {
    interface View {
        fun returnToMainScreen()
        fun showToast(message: String)
        fun displayError(error: String)
    }

    interface Presenter {
        fun addTask(title: String)
    }
}