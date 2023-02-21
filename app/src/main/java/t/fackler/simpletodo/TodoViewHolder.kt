package t.fackler.simpletodo

import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val titleView = view.findViewById(R.id.title) as TextView
    val checkBoxView = view.findViewById(R.id.checkbox) as CheckBox
}