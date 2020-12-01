package com.besugos.desafio3dha.home.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.besugos.desafio3dha.R
import com.besugos.desafio3dha.home.model.ComicModel
import com.besugos.desafio3dha.home.repository.MarvelRepository
import com.besugos.desafio3dha.home.viewmodel.ComicViewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var _viewModel: ComicViewModel
    private lateinit var _listAdapter: ComicAdapter
    private lateinit var _recyclerView: RecyclerView
    private var _comics = mutableListOf<ComicModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val viewGridManager = GridLayoutManager(this, 3)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        _comics = mutableListOf<ComicModel>()
        _listAdapter = ComicAdapter(_comics) {
            val intent = Intent(this@HomeActivity, DetailActivity::class.java)
//            intent.putExtra("COMICS_ID", it.id)
//            intent.putExtra("COMICS_DESCRIPTION", it.descricao)
            startActivity(intent)
        }

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewGridManager
            adapter = _listAdapter
        }

        viewModelProvider()
        getList()

        showLoading(true)
//        setScrollView()
    }

    private fun viewModelProvider() {
        _viewModel = ViewModelProvider(
            this,
            ComicViewModel.ComicViewModelFactory(MarvelRepository())
        ).get(ComicViewModel::class.java)
    }

    private fun getList() {
        _viewModel.getList().observe({ lifecycle }, {
            _comics.addAll(it)
            _listAdapter.notifyDataSetChanged()
            showLoading(false)
        })
    }

    private fun showLoading(isLoading: Boolean) {
        val viewLoading = findViewById<View>(R.id.loading)
        if (isLoading) {
            viewLoading.visibility = View.VISIBLE
        } else {
            viewLoading.visibility = View.GONE
        }
    }

    private fun setScrollView() {
        _recyclerView.run {
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    val target = recyclerView.layoutManager as GridLayoutManager?
                    val totalItemCount = target!!.itemCount
                    val lastVisible = target.findLastVisibleItemPosition()
                    val lastItem = lastVisible + 6 >= totalItemCount

                    if (totalItemCount > 0 && lastItem) {
                        showLoading(true)
                        _viewModel.nextPage().observe({ lifecycle }, {
                            _comics.addAll(it)
                            _listAdapter.notifyDataSetChanged()
                            showLoading(false)
                        })
                    }
                }
            })
        }
    }
}
