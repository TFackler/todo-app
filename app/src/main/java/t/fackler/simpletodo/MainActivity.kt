package t.fackler.simpletodo

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addButton = findViewById<AppCompatButton>(R.id.new_todo_button)
        val addEditText = findViewById<EditText>(R.id.new_todo)
        val todoItemRecyclerView = findViewById<RecyclerView>(R.id.todo_list)

        val todos = mutableListOf(
            Todo("take out trash"),
            Todo("pay invoice #23"),
            Todo("build cool app"),
        )

        todoItemRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
        val adapter = TodoAdapter(this, todos)
        todoItemRecyclerView.adapter = adapter

        addButton.setOnClickListener {
            val newTodoTitle = addEditText.text.toString().trim()
            if (newTodoTitle.isNotBlank()) {
                adapter.addTodo(Todo(newTodoTitle))
                addEditText.setText("")
            }
        }
    }
}