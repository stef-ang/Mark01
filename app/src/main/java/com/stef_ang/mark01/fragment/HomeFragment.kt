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
import com.mikepenz.fastadapter.diff.FastAdapterDiffUtil
import com.stef_ang.mark01.Mark01Application
import com.stef_ang.mark01.R
import com.stef_ang.mark01.databinding.FragmentHomeBinding
import com.stef_ang.mark01.domain.HomeMovie
import com.stef_ang.mark01.viewitem.HomeMovieSectionVI
import com.stef_ang.mark01.viewmodel.home.HomeLoadingVM
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
    private val loadingVM by viewModels<HomeLoadingVM>()

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

        initView()
        observeLiveData()

        return _binding?.root
    }

    private fun initView() {
        binding.recyclerView.apply {
            adapter = fastAdapter
        }
        binding.imageStatus.setImageResource(R.drawable.loading_animation)
    }

    private fun observeLiveData() {
        loadingVM.isLoading.observe(viewLifecycleOwner, Observer {
            binding.imageStatus.visibility = if (it) View.VISIBLE else View.GONE
        })

        nowPlayingVM.state.observe(viewLifecycleOwner, Observer {
            when (it.state) {
                is HomeViewState.State.Loading -> loadingVM.setData1(true)
                is HomeViewState.State.Success -> {
                    mainRender()
                    loadingVM.setData1(false)
                }
                is HomeViewState.State.Error -> {
                    mainRender()
                    loadingVM.setData1(false)
                    Toast.makeText(context, (it.state as HomeViewState.State.Error).error, Toast.LENGTH_SHORT).show()
                }
            }
        })

        popularVM.state.observe(viewLifecycleOwner, Observer {
            when (it.state) {
                is HomeViewState.State.Loading -> loadingVM.setData2(true)
                is HomeViewState.State.Success -> {
                    mainRender()
                    loadingVM.setData2(false)
                }
                is HomeViewState.State.Error -> {
                    mainRender()
                    loadingVM.setData2(false)
                    Toast.makeText(context, (it.state as HomeViewState.State.Error).error, Toast.LENGTH_SHORT).show()
                }
            }
        })

        upcomingVM.state.observe(viewLifecycleOwner, Observer {
            when (it.state) {
                is HomeViewState.State.Loading -> loadingVM.setData3(true)
                is HomeViewState.State.Success -> {
                    mainRender()
                    loadingVM.setData3(false)
                }
                is HomeViewState.State.Error -> {
                    mainRender()
                    loadingVM.setData3(false)
                    Toast.makeText(context, (it.state as HomeViewState.State.Error).error, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun mainRender() {
        val items = mutableListOf<GenericItem>()
        nowPlayingVM.state.value?.movies?.let {
            items.add(renderHomeSection(getString(R.string.title_now_playing), it))
        }
        popularVM.state.value?.movies?.let {
            items.add(renderHomeSection(getString(R.string.title_popular), it))
        }
        upcomingVM.state.value?.movies?.let {
            items.add(renderHomeSection(getString(R.string.title_upcoming), it))
        }
        // prevent blink on render
        FastAdapterDiffUtil[itemAdapter] = items
    }

    private fun renderHomeSection(title: String, movies: List<HomeMovie>): HomeMovieSectionVI {
        return HomeMovieSectionVI(title, movies)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}