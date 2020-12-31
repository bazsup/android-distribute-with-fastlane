package odds.team.todolist.main

import android.app.Application
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import odds.team.todolist.main.item.TaskItemViewHolder
import odds.team.todolist.main.item.taskItemViewHolder
import odds.team.todolist.model.Task

class MainAdapter(
    private val taskList: List<Task>,
    private val application: Application
) : RecyclerView.Adapter<TaskItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskItemViewHolder {
        return parent.taskItemViewHolder(application)
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    override fun onBindViewHolder(holder: TaskItemViewHolder, position: Int) {
        holder.bindView(taskList[position])
    }
}
