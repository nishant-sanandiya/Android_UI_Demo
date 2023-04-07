import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.ui_demo.DummyData
import com.example.ui_demo.R

class SimpleListAdapter(context: Context, items: ArrayList<DummyData>) : BaseAdapter() {
    private val context: Context
    private val items: ArrayList<DummyData>

    override fun getCount(): Int {
        return items.size //returns total of items in the list
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?):
            View? {
        var convertView: View? = convertView
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                .inflate(R.layout.mylist, parent, false)
        }
        val currentItem = getItem(position) as DummyData
        val textViewItemName = convertView
            ?.findViewById(R.id.myListText) as TextView
        textViewItemName.setText(position.toString())
        return convertView
    }

    init {
        this.context = context
        this.items = items
    }
}