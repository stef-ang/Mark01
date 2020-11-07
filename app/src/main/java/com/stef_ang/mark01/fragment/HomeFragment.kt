package com.stef_ang.mark01.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.GenericItem
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.stef_ang.mark01.Mark01Application
import com.stef_ang.mark01.R
import com.stef_ang.mark01.databinding.FragmentHomeBinding
import com.stef_ang.mark01.viewitem.HomeMovieVI
import com.stef_ang.mark01.viewmodel.home.HomeNowPlayingVM
import com.stef_ang.mark01.viewmodel.home.HomePopularVM
import com.stef_ang.mark01.viewmodel.home.HomeUpcomingVM
import com.stef_ang.mark01.viewmodel.home.HomeViewState
import javax.inject.Inject

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = requireNotNull(_binding)
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val nowPlayingVM by viewModels<HomeNowPlayingVM> { viewModelFactory }
    private val popularVM by viewModels<HomePopularVM> { viewModelFactory }
    private val upcomingVM by viewModels<HomeUpcomingVM> { viewModelFactory }

    private val itemAdapter = ItemAdapter<GenericItem>()
    private val fastAdapter = FastAdapter.with(itemAdapter)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as Mark01Application)
            .appComponent
            .homeComponent()
            .create()
            .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        initRecyclerView()
        observeLiveData()

        popularVM
        upcomingVM
        return _binding?.root
    }

    private fun initRecyclerView() {
        binding.recyclerView.apply {
            adapter = fastAdapter
        }
    }

    private fun observeLiveData() {
        nowPlayingVM.state.observe(viewLifecycleOwner, Observer {
            when (it.state) {
                is HomeViewState.State.Loading -> {
                    binding.imageStatus.apply {
                        visibility = View.VISIBLE
                        setImageResource(R.drawable.loading_animation)
                    }
                }
                is HomeViewState.State.Success -> {
                    itemAdapter.setNewList(it.movies?.map { movie -> HomeMovieVI(movie).also {
                        it.identifier = movie.id.toLong()
                    } } ?: emptyList())
                    binding.imageStatus.visibility = View.GONE
                }
                is HomeViewState.State.Error -> {
                    itemAdapter.setNewList(it.movies?.map { movie -> HomeMovieVI(movie).also {
                        it.identifier = movie.id.toLong()
                    } } ?: emptyList())
                    binding.imageStatus.visibility = View.GONE
                    Toast.makeText(context, (it.state as HomeViewState.State.Error).error, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}