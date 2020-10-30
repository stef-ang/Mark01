package com.stef_ang.mark01.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.GenericItem
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.stef_ang.mark01.databinding.FragmentHomeBinding
import com.stef_ang.mark01.viewitem.HomeMovieVI
import com.stef_ang.mark01.viewmodel.HomeViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = requireNotNull(_binding)
    private val viewModel: HomeViewModel by lazy { ViewModelProvider(this).get(HomeViewModel::class.java) }

    private val itemAdapter = ItemAdapter<GenericItem>()
    private val fastAdapter = FastAdapter.with(itemAdapter)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        initRecyclerView()
        initRequest()
        observeLiveData()
        initOnClick()

        return _binding?.root
    }

    private fun initRecyclerView() {
//        val selectExtension = fastAdapter.getSelectExtension()
//        selectExtension.isSelectable = true
        binding.recyclerView.apply {
            adapter = fastAdapter
        }
    }

    private fun initRequest() {
        viewModel.fetchNowPlayingMovies()
    }

    private fun observeLiveData() {
        viewModel.rawData.observe(viewLifecycleOwner, Observer {
            binding.text.text = it
        })
        viewModel.movies.observe(viewLifecycleOwner, Observer { list ->
            itemAdapter.setNewList(list.map { HomeMovieVI(it) })
        })
    }

    private fun initOnClick() {
        binding.button.setOnClickListener {
            initRequest()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

    }
}