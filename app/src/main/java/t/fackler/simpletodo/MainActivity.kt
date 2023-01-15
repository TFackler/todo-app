package t.fackler.simpletodo

import android.os.Bundle
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addButton = findViewById<AppCompatButton>(R.id.new_todo_button)
        val addEditText = findViewById<EditText>(R.id.new_todo)
        val todoItemList = findViewById<ListView>(R.id.todo_list)

        val todos = mutableListOf("take out trash", "pay invoice #23", "build cool app")

        val adapter = TodoAdapter(this, todos)
        todoItemList.adapter = adapter

        addButton.setOnClickListener {
            val newTodoTitle = addEditText.text.toString().trim()
            if (newTodoTitle.isNotBlank()) {
                adapter.addTodo(newTodoTitle)
                addEditText.setText("")
            }
        }
    }
}