package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {

    lateinit var ref: DatabaseReference
    lateinit var artistList: MutableList<Artist>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        artistList = mutableListOf()
        ref = FirebaseDatabase.getInstance().getReference("artist")

        btnAdd.setOnClickListener {
            saveArtist()
        }

        ref.addValueEventListener(object: ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists()){
                    artistList.clear()
                    for (artist in p0.children){
                        val psn = artist.getValue(Artist::class.java)
                        artistList.add(psn!!)
                    }
                    val adapter = ArtistAdapter(applicationContext, R.layout.artist, artistList)
                    listArtist.adapter = adapter
                }
            }
        })
    }
    private fun saveArtist(){
        val name = editName.text.toString().trim()
        if(name.isEmpty()){
            editName.error = "Ingresa un Artista"
            return
        }

        val ref = FirebaseDatabase.getInstance().getReference("artist")
        val artistId = ref.push().key
        if (artistId != null) {
            val artist = artistId?.let { Artist(it, name)}
            ref.child(artistId).setValue(artist).addOnCompleteListener{
                Toast.makeText(this, "Se ha agregado un Artista", Toast.LENGTH_LONG).show()
            }
        }

    }
}
