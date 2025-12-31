package com.example.notesapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit_crud_just_internet.Model.Note
import com.example.retrofit_crud_just_internet.Model.setSafeOnClickListener
import com.example.retrofit_crud_just_internet.R
import com.google.android.material.button.MaterialButton

enum class NoteAction { EDITE , DELETE }
class NoteAdapter( private val onItemClick: (note: Note, action: NoteAction) -> Unit
) : ListAdapter<Note, NoteAdapter.NoteViewHolder>(DiffCallback())
{


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)
    }
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = getItem(position)
        holder.build(note)
    }

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTitle: TextView = itemView.findViewById(R.id.tvTitleId_Cord)
        private val tvContent: TextView = itemView.findViewById(R.id.tvContentId_cord)
        private val tvDate: TextView = itemView.findViewById(R.id.tvDateId_cord)
        private val btnEdit: MaterialButton = itemView.findViewById(R.id.btnEdit_id_cord)
        private val btnDelete: MaterialButton = itemView.findViewById(R.id.btnDeleted_id_cord)

        fun build(note: Note) {
            tvTitle  .text = note.title
            tvContent.text = note.content
            tvDate   .text = note.date

//            ItemOnClick
            btnEdit  .setSafeOnClickListener { onItemClick(note, NoteAction.EDITE) }
            btnDelete.setSafeOnClickListener { onItemClick(note, NoteAction.DELETE) }
        }
    }
}
//  من اجل تحويل ال adapter ال listAdapter يتحدث تماتيك
class DiffCallback : DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Note, newItem: Note) = oldItem == newItem
}

