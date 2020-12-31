package odds.team.todolist.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import io.reactivex.Observable

@Dao
interface TaskDao {
    @get:Query("SELECT * FROM tasks")
    val all: Observable<List<Task>>

    @Insert(onConflict = REPLACE)
    fun insert(task: Task)

    @Update
    fun update(task: Task)

    @Query("DELETE FROM tasks WHERE id = :id")
    fun delete(id: Int?)

    @Query("DELETE FROM tasks")
    fun deleteAll()

}
