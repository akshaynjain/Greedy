package com.example.myapplication.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.FragmentFirstBinding
import com.example.myapplication.di.HomeViewModelFactory
import com.example.myapplication.di.RepositoryFactory
import com.example.myapplication.model.MoviesNow
import com.example.myapplication.model.MoviesTrending
import com.example.myapplication.model.Results
import java.util.*


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var moviesNow: MoviesNow
    private lateinit var moviestrending: MoviesTrending
    private  var results: ArrayList<Results> = arrayListOf()
    private var _binding: FragmentFirstBinding? = null
    private var previousTotal = 0
    private var loading = true
    private val visibleThreshold = 5
    var firstVisibleItem = 0
    var visibleItemCount:Int = 0
    var totalItemCount:Int = 0
    private lateinit var mLayoutManager: LinearLayoutManager
    private lateinit var adapter: TrendingAdapter
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    // This is the loading time of the splash screen


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        homeViewModel = ViewModelProvider(this, HomeViewModelFactory(RepositoryFactory.createHomeRepository())).get(HomeViewModel::class.java)
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mLayoutManager =  LinearLayoutManager(context);
        binding.recyclerView.layoutManager = mLayoutManager

        adapter = TrendingAdapter(requireContext(), results!!)
        binding.recyclerView.adapter = adapter

        homeViewModel.moviesNowList.observe(viewLifecycleOwner, Observer{
            moviesNow=it
            activity?.runOnUiThread {
                binding.viewPager.adapter=BannerAdapter(requireContext(),moviesNow.results)
                binding.tabs.setupWithViewPager(binding.viewPager,true)
            }
        })
        homeViewModel.moviesTrendList.observe(viewLifecycleOwner, Observer {
            moviestrending=it
            if (results == null) {
                results = moviestrending.results
                adapter.data=results as ArrayList<Results>
                binding.recyclerView.adapter?.notifyDataSetChanged()
            } else {
                results!!.addAll(moviestrending.results)
                adapter.data = results as ArrayList<Results>
                binding.recyclerView.adapter?.notifyItemRangeChanged(firstVisibleItem, moviestrending.results.size)
            }

            adapter.onItemClick = {

                val action = HomeFragmentDirections.actionFirstFragmentToSecondFragment(it,it.title)
                findNavController().navigate(action)

//                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            }
        })
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                visibleItemCount = binding.recyclerView.getChildCount()
                totalItemCount = mLayoutManager.getItemCount()
                firstVisibleItem = mLayoutManager.findFirstVisibleItemPosition()
                if (loading) {
                    if (totalItemCount > previousTotal) {
                        loading = false
                        previousTotal = totalItemCount
                    }
                }
                if (!loading && totalItemCount - visibleItemCount
                    <= firstVisibleItem + visibleThreshold
                ) {
                    // End has been reached
                    Log.i("Yaeye!", "end called")
                    var page=++moviestrending.page
                    homeViewModel.getMoviesTrending((page))
                    // Do something
                    loading = true
                }
            }
        })
        homeViewModel.getMoviesNow()
        homeViewModel.getMoviesTrending(1)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}