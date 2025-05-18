package com.example.mymeditation.adapter

import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mymeditation.databinding.ItemImageBinding
import java.io.File

class ImageAdapter(private val images: List<String>,
                   private val onImageClick: (String) -> Unit) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>()
{

    lateinit var binding: ItemImageBinding

    inner class ImageViewHolder(val imageView: ImageView) : RecyclerView.ViewHolder(imageView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val img = ImageView(parent.context)
        img.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300)
        img.scaleType = ImageView.ScaleType.CENTER_CROP
        return ImageViewHolder(img)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imagePath = images[position]
        Glide.with(holder.imageView.context)
            .load(File(imagePath))
            .into(holder.imageView)

        holder.imageView.setOnClickListener {
            onImageClick(imagePath)
        }
    }

    override fun getItemCount() = images.size
}