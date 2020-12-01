package com.besugos.desafio3dha.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.besugos.desafio3dha.home.model.ComicModel
import com.besugos.desafio3dha.home.repository.MarvelRepository
import kotlinx.coroutines.Dispatchers

class ComicViewModel(val _repository: MarvelRepository) : ViewModel() {

    private var _comicsList: List<ComicModel> = listOf()
    private var _totalPages: Int = 0
    private var _offset: Int = 0
    private var _count: Int = 0

    fun getList() = liveData(Dispatchers.IO) {

        val response = _repository.getComics()
        _count = response.data.count
        if(response.data.total != 0) {
            _totalPages = response.data.total / _count
        } else{
            _totalPages = 0
        }

        _comicsList = response.data.results
        emit(response.data.results)

    }

    fun nextPage() = liveData(Dispatchers.IO) {
        if (_offset.plus(_count) <= _totalPages) {
            _offset = _offset.plus(_count)
            val response = _repository.getComics(_offset)
            emit(response.data.results)
        }
    }

    class ComicViewModelFactory(private val _repository: MarvelRepository): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ComicViewModel(_repository) as T
        }
    }
}