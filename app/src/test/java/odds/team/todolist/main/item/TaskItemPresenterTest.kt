package odds.team.todolist.main.item

import odds.team.todolist.BaseTest
import odds.team.todolist.model.LocalDataSource
import odds.team.todolist.model.Task
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TaskItemPresenterTest : BaseTest() {
    @Mock
    private lateinit var mockViewHolder: TaskItemContract.View

    @Mock
    private lateinit var mockDataSource: LocalDataSource

    lateinit var underTest: TaskItemPresenter

    @Before
    fun setUp() {
        underTest = TaskItemPresenter(view = mockViewHolder, dataSource = mockDataSource)
    }

    @Test
    fun testUpdateTask() {
        val taskForUpdate = Task(title = "Title1")

        underTest.updateTask(taskForUpdate)

        Mockito.verify(mockDataSource).update(taskForUpdate)
    }
}