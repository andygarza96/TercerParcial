package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class ArtistAdapter(val mCtx: Context, val layoutResId: Int, val artistList: List<Artist>)
    : ArrayAdapter<Artist>(mCtx, layoutResId, artistList){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(layoutResId, null)

        val textViewName = view.findViewById<TextView>(R.id.textViewName)
        val artist = artistList[position]
        textViewName.text = artist.name
        return view;
    }

}