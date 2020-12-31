package odds.team.todolist.main

import io.reactivex.Observable
import odds.team.todolist.BaseTest
import odds.team.todolist.RxImmediateSchedulerRule
import odds.team.todolist.model.LocalDataSource
import odds.team.todolist.model.Task
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainPresenterTest : BaseTest() {
    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    private lateinit var mockActivity: MainContract.View

    @Mock
    private lateinit var mockDataSource: LocalDataSource

    private lateinit var underTest: MainPresenter

    @Before
    fun setUp() {
        underTest = MainPresenter(view = mockActivity, dataSource = mockDataSource)
    }

    @Test
    fun testGetMyTasksList() {
        val myDummyTasks = dummyAllTasks
        Mockito.doReturn(Observable.just(myDummyTasks)).`when`(mockDataSource).allTasks

        underTest.getMyTodoList()

        Mockito.verify(mockDataSource).allTasks
        Mockito.verify(mockActivity).displayTasks(myDummyTasks)
    }

    @Test
    fun testGetMyTasksListWithNoTask() {
        Mockito.doReturn(Observable.just(emptyList<Task>())).`when`(mockDataSource).allTasks

        underTest.getMyTodoList()

        Mockito.verify(mockDataSource).allTasks
        Mockito.verify(mockActivity).displayNoTask()
    }

    // Helper functions

    private val dummyAllTasks: List<Task>
        get() {
            return listOf(
                Task(title = "Title1"),
                Task(title = "Title2"),
                Task(title = "Title3")
            )
        }
}
