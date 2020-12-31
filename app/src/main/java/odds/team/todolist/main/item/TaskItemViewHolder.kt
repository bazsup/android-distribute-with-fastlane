package odds.team.todolist.main.item

import android.app.Application
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import odds.team.todolist.R
import odds.team.todolist.model.LocalDataSource
import odds.team.todolist.model.Task

fun ViewGroup.taskItemViewHolder(application: Application): TaskItemViewHolder {
    val viewHolder = TaskItemViewHolder(
        LayoutInflater
            .from(context)
            .inflate(R.layout.item_task_main, this, false)
    )
    val presenter = TaskItemPresenter(view = viewHolder, dataSource = LocalDataSource(application))
    viewHolder.setPresenter(presenter)

    return viewHolder
}

class TaskItemViewHolder(view: View) : RecyclerView.ViewHolder(view), TaskItemContract.View {
    private lateinit var presenter: TaskItemContract.Presenter

    private val checkbox: CheckBox = view.findViewById(R.id.task_checkBox)

    fun bindView(task: Task) {
        checkbox.text = task.title
        checkbox.isChecked = task.completed
        checkbox.setOnCheckedChangeListener { _, isChecked ->
            task.completed = isChecked
            presenter.updateTask(task)
        }
    }

    override fun setPresenter(presenter: TaskItemContract.Presenter) {
        this.presenter = presenter
    }
}