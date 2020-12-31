package odds.team.todolist

import org.mockito.ArgumentCaptor

open class BaseTest {
    fun <T> captureArg(argumentCaptor: ArgumentCaptor<T>): T = argumentCaptor.capture()
}