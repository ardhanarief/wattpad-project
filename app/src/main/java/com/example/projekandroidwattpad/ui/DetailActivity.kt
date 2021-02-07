package com.example.projekandroidwattpad.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.projekandroidwattpad.R
import com.example.projekandroidwattpad.model.Novel
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DETAIL_NOVEL = "extra_detail_novel"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val novel: Novel? = intent.extras?.getSerializable(EXTRA_DETAIL_NOVEL) as Novel?

        novel?.let { itemNovel ->
            Glide.with(this).load(itemNovel.coverNovel).into(iv_detail_gambar)
            tv_detail_judul.text = itemNovel.judulNovel
            tv_detail_penulis.text = itemNovel.penulisNovel
            tv_detail_cerita.text = itemNovel.isiCerita
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}