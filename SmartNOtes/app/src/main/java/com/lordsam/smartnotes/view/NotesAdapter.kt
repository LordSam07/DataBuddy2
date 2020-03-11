package com.lordsam.smartnotes.view

import android.R
import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.lordsam.smartnotes.database.models.DatabaseHelper
import com.lordsam.smartnotes.database.models.Note
import java.text.ParseException
import java.text.SimpleDateFormat


class NotesAdapter(
    private val context: Context,
    notesList: List<Note>
) :
    RecyclerView.Adapter<NotesAdapter.MyViewHolder>() {
    private val notesList: List<Note>

    inner class MyViewHolder(view: View) : ViewHolder(view) {
        var note: TextView
        var dot: TextView
        var timestamp: TextView

        init {
            note = view.findViewById(R.id.note)
            dot = view.findViewById(R.id.dot)
            timestamp = view.findViewById(R.id.timestamp)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout., parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int
    ) {
        val note: Note = notesList[position]
        holder.note.setText(note.getNote())

        // Displaying dot from HTML character code
        holder.dot.text = Html.fromHtml("&#8226;")

        // Formatting and displaying timestamp
        holder.timestamp.text = formatDate(note.getTimestamp())
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    /**
     * Formatting timestamp to `MMM d` format
     * Input: 2018-02-21 00:15:42
     * Output: Feb 21
     */
    private fun formatDate(dateStr: String): String {
        try {
            val fmt = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            val date = fmt.parse(dateStr)
            val fmtOut = SimpleDateFormat("MMM d")
            return fmtOut.format(date)
        } catch (e: ParseException) {
        }
        return ""
    }

    init {
        this.notesList = notesList
    }
}