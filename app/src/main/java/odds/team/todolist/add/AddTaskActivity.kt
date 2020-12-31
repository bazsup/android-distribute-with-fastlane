package odds.team.todolist.add

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_task.*
import odds.team.todolist.R
import odds.team.todolist.model.LocalDataSource

class AddTaskActivity : AppCompatActivity(), AddTaskContract.View {
    private lateinit var presenter: AddTaskContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        setupPresenter()
        setupView()
    }

    private fun setupPresenter() {
        val dataSource = LocalDataSource(application)
        presenter = AddTaskPresenter(this, dataSource)
    }

    private fun setupView() {
        supportActionBar?.title = "New Task"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.doneMenuItem) {
            val title = title_textView.text.toString()
            presenter.addTask(title)
        }

        return super.onOptionsItemSelected(item)
    }

    override fun returnToMainScreen() {
        setResult(Activity.RESULT_OK)
        finish()
    }

    override fun showToast(message: String) {
        Toast.makeText(this@AddTaskActivity, message, Toast.LENGTH_LONG).show()
    }

    override fun displayError(error: String) {
        showToast(error)
    }
}