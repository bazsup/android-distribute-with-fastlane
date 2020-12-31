package odds.team.todolist.add

import odds.team.todolist.BaseTest
import odds.team.todolist.model.LocalDataSource
import odds.team.todolist.model.Task
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AddTaskPresenterTest : BaseTest() {

    @Mock
    private lateinit var mockActivity: AddTaskContract.View

    @Mock
    private lateinit var mockDataSource: LocalDataSource

    lateinit var underTest: AddTaskPresenter

    @Before
    fun setUp() {
        underTest = AddTaskPresenter(view = mockActivity, dataSource = mockDataSource)
    }

    @Test
    fun testAddTaskNoTitle() {
        underTest.addTask("")

        Mockito.verify(mockActivity).displayError("Task title cannot be empty")
    }

    @Captor
    private lateinit var taskArgumentCaptor: ArgumentCaptor<Task>

    @Test
    fun testAddTaskWithTitle() {
        underTest.addTask("Do homework")

        Mockito.verify(mockDataSource).insert(captureArg(taskArgumentCaptor))
        Assert.assertEquals("Do homework", taskArgumentCaptor.value.title)

        Mockito.verify(mockActivity).returnToMainScreen()
    }
}