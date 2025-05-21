package com.example.mymeditation.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymeditation.adapter.DownloadedMusicAdapter
import com.example.mymeditation.databinding.FragmentMeditateDownloadCategoryBinding
import java.io.File

class MeditateDownloadCategoryFragment : Fragment() {

    private lateinit var binding: FragmentMeditateDownloadCategoryBinding
    private lateinit var adapter: DownloadedMusicAdapter
    private var downloadedSongs: MutableList<File> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMeditateDownloadCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = DownloadedMusicAdapter(requireContext(), downloadedSongs,
            onFavoriteClick = { file -> toggleFavorite(file) },
            onRenameClick = { file -> renameSong(file) },
            onDeleteClick = { file -> deleteSong(file) }
        )

        binding.rvDownloads.layoutManager = LinearLayoutManager(requireContext())
        binding.rvDownloads.adapter = adapter
        loadDownloadedSongs()
    }

    private fun loadDownloadedSongs() {
        val dir = File(requireContext().getExternalFilesDir(null), "MeditationDownloads")
        if (dir.exists()) {
            downloadedSongs.clear()
            downloadedSongs.addAll(dir.listFiles()?.toList() ?: emptyList())
            adapter.notifyDataSetChanged()
            binding.emptyState.visibility = if (downloadedSongs.isEmpty()) View.VISIBLE else View.GONE
        }
    }

    private fun toggleFavorite(file: File) {
        Toast.makeText(requireContext(), "Favorite toggled: ${file.name}", Toast.LENGTH_SHORT).show()
        // TODO: Save favorite state in shared prefs or Room
    }

    private fun renameSong(file: File) {
        val editText = android.widget.EditText(requireContext()).apply { setText(file.nameWithoutExtension) }

        AlertDialog.Builder(requireContext())
            .setTitle("Rename")
            .setView(editText)
            .setPositiveButton("Rename") { _, _ ->
                val newName = editText.text.toString().trim()
                if (newName.isNotEmpty()) {
                    val newFile = File(file.parent, "$newName.mp3")
                    if (file.renameTo(newFile)) {
                        Toast.makeText(requireContext(), "Renamed to $newName", Toast.LENGTH_SHORT).show()
                        loadDownloadedSongs()
                    } else {
                        Toast.makeText(requireContext(), "Rename failed", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun deleteSong(file: File) {
        AlertDialog.Builder(requireContext())
            .setTitle("Remove from downloads")
            .setMessage("Are you sure you want to delete ${file.name}?")
            .setPositiveButton("Delete") { _, _ ->
                if (file.delete()) {
                    Toast.makeText(requireContext(), "Deleted ${file.name}", Toast.LENGTH_SHORT).show()
                    loadDownloadedSongs()
                } else {
                    Toast.makeText(requireContext(), "Failed to delete", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}