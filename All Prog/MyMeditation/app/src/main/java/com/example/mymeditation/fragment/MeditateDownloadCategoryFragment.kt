package com.example.mymeditation.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.PopupMenu
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.mymeditation.MusicViewModelFactory
import com.example.mymeditation.MusicViewModel
import com.example.mymeditation.R
import com.example.mymeditation.adapter.DownloadAdapter
import com.example.mymeditation.databinding.FragmentMeditateDownloadCategoryBinding
import com.example.mymeditation.model.DownloadMusicEntity
import com.example.mymeditation.model.LikedMusicEntity
import kotlinx.coroutines.launch
import java.io.File

class MeditateDownloadCategoryFragment : Fragment() {

    private lateinit var binding: FragmentMeditateDownloadCategoryBinding
    private lateinit var adapter: DownloadAdapter

    private val viewModel: MusicViewModel by lazy {
        ViewModelProvider(
            this,
            MusicViewModelFactory(requireActivity().application)
        )[MusicViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMeditateDownloadCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = DownloadAdapter { song, anchor ->
            showPopup(song, anchor)
        }
        binding.rvDownloads.adapter = adapter

        viewModel.downloadedSongs.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun showPopup(song: DownloadMusicEntity, anchor: View) {
        val popup = PopupMenu(requireContext(), anchor)
        popup.menuInflater.inflate(R.menu.download_menu, popup.menu)

        lifecycleScope.launch {
            val isFav = viewModel.isFavorite(song.audioResId)
            popup.menu.findItem(R.id.action_fav).title =
                if (isFav) "Remove from Favorites" else "Add to Favorites"
        }

        popup.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_fav -> {
                    lifecycleScope.launch {
                        val isFav = viewModel.isFavorite(song.audioResId)
                        if (isFav) {
                            viewModel.removeFavorite(song.audioResId)
                            Toast.makeText(requireContext(), "Removed from Favorites", Toast.LENGTH_SHORT).show()
                        } else {
                            viewModel.addFavorite(
                                LikedMusicEntity(song.audioResId, song.title)
                            )
                            Toast.makeText(requireContext(), "Added to Favorites", Toast.LENGTH_SHORT).show()
                        }
                    }
                    true
                }

                R.id.action_delete -> {
                    lifecycleScope.launch {
                        // 1. Remove from DB
                        viewModel.removeDownloaded(song.audioResId)

                        // 2. Remove the actual file
                        val fileName = "${song.audioResId}.mp3"
                        val deleted = deleteSongFile(fileName)

                        if (deleted) {
                            Toast.makeText(requireContext(), "Download Removed", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(requireContext(), "Removed from DB, but file not found", Toast.LENGTH_SHORT).show()
                        }

                        // 3. Notify UI to update
                        LocalBroadcastManager.getInstance(requireContext())
                            .sendBroadcast(Intent("DOWNLOAD_UPDATED").putExtra("id", song.audioResId))
                    }
                    true
                }

                else -> false
            }
        }

        popup.show()
    }

    private fun deleteSongFile(fileName: String): Boolean {
        val internalFile = File(requireContext().filesDir, fileName)
        val externalFile = File(requireContext().getExternalFilesDir(null), fileName)

        Log.d("DeleteDebug", "Internal: ${internalFile.absolutePath}")
        Log.d("DeleteDebug", "External: ${externalFile.absolutePath}")

        return when {
            internalFile.exists() -> internalFile.delete()
            externalFile.exists() -> externalFile.delete()
            else -> false
        }
    }
}
