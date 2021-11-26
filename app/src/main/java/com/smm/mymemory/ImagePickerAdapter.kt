package com.smm.mymemory

import android.content.Context
import android.media.Image
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.smm.mymemory.models.BoardSize
import kotlin.math.min

class ImagePickerAdapter(
    private val context: Context,
    private val imageUris: List<Uri>,
    private val boardSize: BoardSize
) : RecyclerView.Adapter<ImagePickerAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.card_image, parent, false)
        val cardWidth = parent.width / boardSize.getWidth()
        val cardHight = parent.height / boardSize.getHeight()
        val cardSideLenght = min(cardHight, cardWidth)
        val layoutParams = view.findViewById<ImageView>(R.id.ivCustomImage).layoutParams
        layoutParams.height = cardSideLenght
        layoutParams.width = cardSideLenght
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position < imageUris.size){
            // user has picked an image so we must show that image to the user
            holder.bind(imageUris[position])
        }else{
            // user has not yet picked an image
            holder.bind()
        }
    }

    override fun getItemCount() = boardSize.getNumPairs()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val ivCustomImage = itemView.findViewById<ImageView>(R.id.ivCustomImage)

        fun bind(uri: Uri){
            ivCustomImage.setImageURI(uri)
            ivCustomImage.setOnClickListener(null)
        }

        fun bind(){
            ivCustomImage.setOnClickListener {
                // We're going to lunch an intent to select photos
            }
        }

    }
}

