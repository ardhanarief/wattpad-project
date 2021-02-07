package com.example.projekandroidwattpad.ui.romance

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.projekandroidwattpad.R
import com.example.projekandroidwattpad.adapter.NovelRecyclerView
import com.example.projekandroidwattpad.model.Novel
import com.example.projekandroidwattpad.ui.DetailActivity
import com.example.projekandroidwattpad.utill.Constant
import kotlinx.android.synthetic.main.fragment_romance.*

class RomanceFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_romance, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val queue = Volley.newRequestQueue(requireContext())
        val url = "${Constant.BASE_URL}/novel?genre=romance"

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { json ->
                val responseFilms = json.getJSONArray("data")
                val listFilms = ArrayList<Novel>()

                for (i in 0 until responseFilms.length()) {
                    val item = responseFilms.getJSONObject(i)

                    val novel = Novel(
                        judulNovel = item.getString("judul_novel"),
                        genreNovel = item.getString("genre_novel"),
                        coverNovel = item.getString("cover_novel"),
                        penulisNovel = item.getString("penulis_novel"),
                        isiCerita = item.getString("isi_cerita")
                    )
                    listFilms.add(novel)
                }

                val adapter = NovelRecyclerView(listFilms)

                rv_romance_novel.apply {
                    layoutManager = LinearLayoutManager(requireContext())
                    this.adapter = adapter
                }

                adapter.apply {
                    setOnItemClickListener { film ->
                        val intent = Intent(requireContext(), DetailActivity::class.java)
                        intent.putExtra(DetailActivity.EXTRA_DETAIL_NOVEL, film)

                        startActivity(intent)
                    }
                    notifyDataSetChanged()
                }

            }, {
                Toast.makeText(
                    requireContext(),
                    "${it.networkResponse.statusCode}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        )
        queue.add(jsonObjectRequest)
    }
}