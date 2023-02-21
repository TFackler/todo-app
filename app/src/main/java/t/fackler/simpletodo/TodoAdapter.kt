package t.fackler.simpletodo

import android.content.Context
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StrikethroughSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter(context: Context, private val dataSource: MutableList<Todo>) :
    RecyclerView.Adapter<TodoViewHolder>() {

    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val itemView = inflater.inflate(R.layout.todo_item, parent, false)
        return TodoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val item = dataSource[position]

        holder.titleView.setText(SpannableString(item.title), TextView.BufferType.SPANNABLE)
        holder.checkBoxView.isChecked = item.done

        if (item.done) {
            val spannable = holder.titleView.text as Spannable
            spannable.setSpan(
                StrikethroughSpan(),
                0,
                spannable.length,
                Spanned.SPAN_INCLUSIVE_EXCLUSIVE
            )
            holder.titleView.setTextColor(Color.rgb(155, 155, 155))
        }

        holder.checkBoxView.setOnCheckedChangeListener { _, newIsChecked ->
            item.done = newIsChecked
            // TODO: master/21.02.2023 move to ui thread, otherwise will crash
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int = dataSource.size

    fun addTodo(todo: Todo) {
        dataSource.add(todo)
        notifyItemInserted(dataSource.size - 1)
    }
}