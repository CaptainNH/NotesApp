package com.example.notesapp

import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.notesapp.databinding.NewNoteBinding
import java.util.*

class NewNote : DialogFragment() {
    private var _binding: NewNoteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val callingActivity = activity as MainActivity
        val inflater = callingActivity.layoutInflater
        _binding = NewNoteBinding.inflate(inflater)
        val builder = AlertDialog.Builder(callingActivity)
            .setView(binding.root)
            .setMessage(resources.getString(R.string.add_new_note))
        binding.btnCancel.setOnClickListener {
            dismiss()
        }
        binding.btnOK.setOnClickListener {
            val title = binding.editTitle.text.toString()
            val contents = binding.editContents.text.toString()
            if (title.isNotEmpty() && contents.isNotEmpty()) {
                val note = Note(title, contents, Date())
                callingActivity.createNewNote(note)
                Toast.makeText(
                    callingActivity,
                    resources.getString(R.string.note_saved),
                    Toast.LENGTH_SHORT
                ).show()
                dismiss()
            } else {
                Toast.makeText(
                    callingActivity,
                    resources.getString(R.string.note_empty),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        return builder.create()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}