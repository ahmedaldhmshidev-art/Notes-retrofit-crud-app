package com.example.notesapp.dialogs

import NoteViewModel
import NoteViewModelFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.retrofit_crud_just_internet.Model.Note
import com.example.retrofit_crud_just_internet.R
import com.example.retrofit_crud_just_internet.VmRepo.RepositoryNote

class AddEditNoteDialogFragment : DialogFragment() {

    private var note: Note? = null

    private lateinit var viewModel: NoteViewModel

    private lateinit var etTitle: EditText
    private lateinit var etContent: EditText
    private lateinit var btnSave: Button

    companion object {
        fun newInstance(note: Note? = null): AddEditNoteDialogFragment {
            val fragment = AddEditNoteDialogFragment()
            fragment.note = note
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_edit_note, container, false)

        // ===== ربط الحقول بالواجهة =====
        etTitle = view.findViewById(R.id.etTitle)
        etContent = view.findViewById(R.id.etContent)
        btnSave = view.findViewById(R.id.btnSave)

        // ===== ViewModel =====
        val repository = RepositoryNote()
        val factory = NoteViewModelFactory(repository)
        viewModel = ViewModelProvider(requireActivity(), factory)[NoteViewModel::class.java]

        // ===== إذا كان تعديل، نملأ الحقول =====
        note?.let {
            etTitle.setText(it.title)
            etContent.setText(it.content)
            btnSave.text = "تعديل"
        }

        // ===== زر الحفظ =====
        btnSave.setOnClickListener {
            val title = etTitle.text.toString().trim()
            val content = etContent.text.toString().trim()

            if (title.isEmpty() || content.isEmpty()) return@setOnClickListener

            if (note == null) {
                // إضافة ملاحظة جديدة
                val newNote = Note("",title = title, content = content, date = getCurrentDate())
                viewModel.addNewNote(newNote)
            } else {
                // تعديل ملاحظة موجودة
                val updatedNote = note!!.copy(title = title, content = content, date = getCurrentDate())
                viewModel.updateNote(note!!.id, updatedNote)
            }

            dismiss() // إغلاق الـ Dialog
        }

        return view
    }

//    دالة ثابتة لجلب الوفت الحالي منظم
    private fun getCurrentDate(): String {
        val sdf = java.text.SimpleDateFormat("yyyy-MM-dd HH:mm", java.util.Locale.getDefault())
        return sdf.format(java.util.Date())
    }
}
