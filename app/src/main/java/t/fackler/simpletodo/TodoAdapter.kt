package t.fackler.simpletodo

import android.content.Context
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StrikethroughSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.TextView

class TodoAdapter(context: Context, initData: List<Todo>) : BaseAdapter() {

    private val dataSource = mutableListOf<Todo>()
    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    init {
        dataSource.addAll(initData)
    }

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    fun addTodo(todo: Todo) {
        dataSource.add(todo)
        notifyDataSetChanged()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        // todo switch to recycler view
        val createdView = inflater.inflate(R.layout.todo_item, parent, false)

        val titleView = createdView.findViewById(R.id.title) as TextView
        val checkBoxView = createdView.findViewById(R.id.checkbox) as CheckBox

        val item = getItem(position) as Todo

        titleView.setText(SpannableString(item.title), TextView.BufferType.SPANNABLE)
        checkBoxView.isChecked = item.done

        if (item.done) {
            val spannable = titleView.text as Spannable
            spannable.setSpan(
                StrikethroughSpan(),
                0,
                spannable.length,
                Spanned.SPAN_INCLUSIVE_EXCLUSIVE
            )
            titleView.setTextColor(Color.rgb(155, 155, 155))
        }

        checkBoxView.setOnCheckedChangeListener { _, newIsChecked ->
            item.done = newIsChecked
            notifyDataSetChanged()
        }

        return createdView
    }
}