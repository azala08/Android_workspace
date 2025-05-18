package com.example.mymeditation.adapter

import android.Manifest
import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.mymeditation.R
import com.example.mymeditation.MediaPlayerManager
import com.example.mymeditation.MusicPlaybackNotifier
import com.example.mymeditation.activity.MusicPlayActivity
import com.example.mymeditation.model.MusicItem
import com.example.mymeditation.service.MusicService
import com.example.mymeditation.service.SleepMusicService

class MusicItemAdapter(
    var context: FragmentActivity,
    private var recyclerItems: MutableList<MusicItem>
) : RecyclerView.Adapter<MusicPlayerViewHolder>() {

//    private val musicBroadcastReceiver = object : BroadcastReceiver() {
//        @SuppressLint("NotifyDataSetChanged")
//        override fun onReceive(context: Context?, intent: Intent?) {
//            if (intent?.action == MusicService.ACTION_UPDATE_UI) {
//                val playingId = intent.getStringExtra("current_item_id")
//                val playing = intent.getBooleanExtra("is_playing", false)
//
//                PlaybackState.currentPlayingItemId = playingId
//                PlaybackState.isPlaying = playing
//
//                notifyDataSetChanged() // Refresh icons in this adapter
//            }
//        }
//    }


    private val musicBroadcastReceiver = object : BroadcastReceiver() {
        @SuppressLint("NotifyDataSetChanged")
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == MusicService.ACTION_UPDATE_UI) {
                val playingId = intent.getStringExtra("current_item_id")
                val playing = intent.getBooleanExtra("is_playing", false)

                PlaybackState.currentPlayingItemId = playingId
                PlaybackState.isPlaying = playing

                notifyDataSetChanged() // refresh icons immediately
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun onResume() {
        val intentFilter = IntentFilter(MusicService.ACTION_UPDATE_UI)
        ContextCompat.registerReceiver(context, musicBroadcastReceiver, intentFilter, ContextCompat.RECEIVER_EXPORTED)
        notifyDataSetChanged()
    }

    fun onPause() {
        //context.unregisterReceiver(musicBroadcastReceiver)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicPlayerViewHolder {
        val layout = LayoutInflater.from(context)
        val viewMusicPlayer = layout.inflate(R.layout.item_music, parent, false)
        return MusicPlayerViewHolder(viewMusicPlayer)
    }

    override fun getItemCount(): Int = recyclerItems.size

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: MusicPlayerViewHolder, position: Int) {
        val item = recyclerItems[position]
        holder.title.text = item.title
        holder.minute.text = item.duration

        val isCurrent = item.id == PlaybackState.currentPlayingItemId
        val isPlaying = PlaybackState.isPlaying

        holder.playIcon.setImageResource(
            if (isCurrent && isPlaying) R.drawable.fill_pause else R.drawable.play
        )
        Log.d("isCurrent && isPlaying","isCurrent" +isCurrent+"&& isPlaying"+isPlaying)
        holder.itemView.setOnClickListener {
            val intent = Intent(context, MusicPlayActivity::class.java).apply {
                putExtra("music_list", ArrayList(recyclerItems))
                putExtra("current_position", position)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(intent)
        }

        holder.playIcon.setOnClickListener {
            val isCurrentlyPlaying = isCurrent && isPlaying

            PlaybackState.currentPlayingItemId = item.id
            PlaybackState.isPlaying = !isCurrentlyPlaying

            notifyDataSetChanged()

            val intent = Intent(context, MusicService::class.java).apply {
                action = if (isCurrentlyPlaying) MusicService.ACTION_PLAY_PAUSE else MusicService.ACTION_START
                putParcelableArrayListExtra("music_list", ArrayList(recyclerItems))
                putExtra("current_position", position)
            }
            ContextCompat.startForegroundService(context, intent)
        }
    }

    fun cleanup() {
        recyclerItems.clear()
        notifyDataSetChanged()
    }

//    fun onResume() {
//        val filter = IntentFilter(MusicService.ACTION_UPDATE_UI)
//        ContextCompat.registerReceiver(context, musicBroadcastReceiver, filter, ContextCompat.RECEIVER_EXPORTED)
//
////        val intentFilter = IntentFilter(MusicService.ACTION_UPDATE_UI)
////        ContextCompat.registerReceiver(
////            context,
////            musicBroadcastReceiver,
////            intentFilter,
////            ContextCompat.RECEIVER_EXPORTED
////        )
//        notifyDataSetChanged()
//    }
//
//    fun onPause() {
//        context.unregisterReceiver(musicBroadcastReceiver)
//    }

    object PlaybackState {
        var currentPlayingItemId: String? = null
        var isPlaying: Boolean = false
    }
}

class MusicPlayerViewHolder(viewMusicPlayer: View) : RecyclerView.ViewHolder(viewMusicPlayer) {
    var title: TextView = viewMusicPlayer.findViewById(R.id.tvMusictitle)
    var minute: TextView = viewMusicPlayer.findViewById(R.id.duration)
    var playIcon: ImageView = viewMusicPlayer.findViewById(R.id.playIcon)
}