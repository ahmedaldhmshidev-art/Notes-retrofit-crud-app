package com.example.retrofit_crud_just_internet

import NoteViewModel
import NoteViewModelFactory
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.adapter.NoteAction
import com.example.notesapp.adapter.NoteAdapter
import com.example.notesapp.dialogs.AddEditNoteDialogFragment
import com.example.retrofit_crud_just_internet.Model.Note
import com.example.retrofit_crud_just_internet.VmRepo.RepositoryNote
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: NoteViewModel
    private lateinit var adapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //  ViewModel setup
        val repository = RepositoryNote()
        val factory = NoteViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[NoteViewModel::class.java]


        //  RecyclerView setup
        val rvNotes = findViewById<RecyclerView>(R.id.rvNoteId_main)

        adapter = NoteAdapter { note, action ->
            when(action) {
                NoteAction . EDITE  -> openEditDialog(note)
                NoteAction . DELETE -> viewModel.deletedNote(note.id)
            }
        }
        rvNotes.adapter = adapter
        rvNotes.layoutManager = LinearLayoutManager(this)

        //  Observe LiveData
        viewModel.noteLive.observe(this) { list ->
            adapter.submitList(list)
        }

        //  Load initial notes
        viewModel.fetchNote()


        viewModel.errorMessage.observe(this){ messageError ->
            if (messageError . isNotEmpty()) {
                Toast.makeText(this, "$messageError", Toast.LENGTH_SHORT).show()
            }

        }
        viewModel.message.observe(this) { msg ->
            if (msg.isNotEmpty()) {
                Snackbar.make(rvNotes, msg, Snackbar.LENGTH_SHORT).show()
            }
        }

        //  FloatingActionButton
        val fabAdd = findViewById<FloatingActionButton>(R.id.fabAddNoteId_main)
        fabAdd.setOnClickListener {
            openAddDialog()
        }

    }

    private fun openAddDialog() {
        val dialog = AddEditNoteDialogFragment.newInstance()
        dialog.show(supportFragmentManager, "AddNoteDialog")
    }

    private fun openEditDialog(note: Note) {
        val dialog = AddEditNoteDialogFragment.newInstance(note)
        dialog.show(supportFragmentManager, "EditNoteDialog")
    }
}
