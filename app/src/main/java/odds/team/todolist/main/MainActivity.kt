package odds.team.todolist.main

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import odds.team.todolist.R
import odds.team.todolist.add.AddTaskActivity
import odds.team.todolist.model.LocalDataSource
import odds.team.todolist.model.Task

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var presenter: MainContract.Presenter
    private lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupView()
        setupPresenter()
    }

    override fun onStart() {
        super.onStart()
        presenter.getMyTodoList()
    }

    override fun onStop() {
        super.onStop()
        presenter.stop()
    }

    private fun setupView() {
        fab.setOnClickListener {
            openNewTaskScreen()
        }
        supportActionBar?.title = "All Lists"
    }

    private fun setupPresenter() {
        val dataSource = LocalDataSource(application)
        presenter = MainPresenter(this, dataSource)
    }

    override fun displayTasks(taskList: List<Task>) {
        mainAdapter = MainAdapter(taskList, application)
        todoList_recycleView.adapter = mainAdapter

        todoList_recycleView.visibility = View.VISIBLE
        noTask_layout.visibility = View.INVISIBLE
    }

    override fun displayNoTask() {
        todoList_recycleView.visibility = View.INVISIBLE
        noTask_layout.visibility = View.VISIBLE
    }

    private fun openNewTaskScreen() {
        val addTaskIntent = Intent(this@MainActivity, AddTaskActivity::class.java)
        startActivityForResult(addTaskIntent, ADD_TASK_ACTIVITY_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode != ADD_TASK_ACTIVITY_REQUEST_CODE) {
            return
        }

        if (resultCode == Activity.RESULT_OK) {
            showToast("Task successfully added.")
        } else {
            displayError("Task could not be added")
        }
    }

    override fun showToast(message: String) {
        Toast.makeText(this@MainActivity, message, Toast.LENGTH_LONG).show()
    }

    override fun displayError(error: String) {
        showToast(error)
    }

    companion object {
        const val ADD_TASK_ACTIVITY_REQUEST_CODE = 1
        const val RESULT_BACK_PRESSED = 1
    }
}