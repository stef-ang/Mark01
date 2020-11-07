package com.stef_ang.mark01.viewitem

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import com.stef_ang.mark01.R
import com.stef_ang.mark01.databinding.ItemHomeMovieSectionBinding
import com.stef_ang.mark01.domain.HomeMovie

class HomeMovieSectionVI(
    val title: String,
    val movies: List<HomeMovie>
) : AbstractBindingItem<ItemHomeMovieSectionBinding>() {

    private val itemAdapter = ItemAdapter<HomeMovieSmallVI>()
    lateinit var fastAdapter: FastAdapter<HomeMovieSmallVI>

    override val type: Int
        get() = R.id.item_home_movie_section

    override fun bindView(binding: ItemHomeMovieSectionBinding, payloads: List<Any>) {
        binding.textTitle.text = title
        binding.buttonAll.text = "See All"
        itemAdapter.setNewList(
            movies.map { movie ->
                HomeMovieSmallVI(movie).also {
                    it.identifier = movie.id.toLong()
                }
            }
        )
    }

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): ItemHomeMovieSectionBinding {
        fastAdapter = FastAdapter.with(itemAdapter)
        val binding = ItemHomeMovieSectionBinding.inflate(inflater, parent, false)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(parent?.context, RecyclerView.HORIZONTAL, false)
            adapter = fastAdapter
        }
        return binding
    }
}