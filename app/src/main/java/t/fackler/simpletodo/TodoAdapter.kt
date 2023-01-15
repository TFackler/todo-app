package t.fackler.simpletodo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class TodoAdapter(context: Context, initData: List<String>) : BaseAdapter() {

    private val dataSource = mutableListOf<String>()
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

    fun addTodo(title: String) {
        dataSource.add(title)
        notifyDataSetChanged()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        // todo switch to recycler view
        val newView = inflater.inflate(R.layout.todo_item, parent, false)
        newView.findViewById<TextView>(R.id.title).text = getItem(position) as String
        return newView
    }
}