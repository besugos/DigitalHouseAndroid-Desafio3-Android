package com.besugos.desafio3dha.home.view

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
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
    private var _comics = mutableListOf<ComicModel>()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val manager = GridLayoutManager(this, 3)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        _comics = mutableListOf<ComicModel>()
        _listAdapter = ComicAdapter(_comics) {

            val intent = Intent(this@HomeActivity, DetailActivity::class.java)
            with(intent) {
                putExtra("TITLE", it.title)
                putExtra("DESCRIPTION", it.description)
                putExtra("PAGE_COUNT", it.pageCount.toString())
                putExtra("DATE", it.dates[1].date)
                putExtra("PRICE", it.prices[0].price.toString())
                putExtra("THUMBNAIL", it.thumbnail?.getThumbPath())

                startActivity(this)
            }
        }

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = manager
            adapter = _listAdapter
        }

        viewModelProvider()

        getList()

        showLoading(true)

        setScrollView()
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

    private fun showResults(list: List<ComicModel>?) {

        list?.let { _comics.addAll(it)}
        _listAdapter.notifyDataSetChanged()

    }


    private fun setScrollView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    val target = recyclerView.layoutManager as GridLayoutManager?
                    val totalItemCount = target!!.itemCount
                    val lastVisible = target.findLastVisibleItemPosition()
                    val lastItem = lastVisible + 6 >= totalItemCount

                    if (totalItemCount > 0 && lastItem) {
//                        showLoading(true)
                        _viewModel.nextPage().observe({ lifecycle }, {
//                            showResults(_comics)
                            _comics.addAll(it)
                            _listAdapter.notifyDataSetChanged()

                        })
                    }
                }
            })
        }
    }

