package com.example.myapplication.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.example.myapplication.databinding.FragmentSecondBinding
import com.example.myapplication.di.MovieViewModelFactory
import com.example.myapplication.di.RepositoryFactory
import com.example.myapplication.model.MovieDetails
import com.example.myapplication.model.MoviesTrending
import com.example.myapplication.model.Results
import com.example.myapplication.ui.movie.MovieViewModel
import com.example.myapplication.ui.movie.ProductionCompanyAdapter
import com.example.myapplication.ui.movie.RecommendedMovieAdapter

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class MovieDetailsFragment : Fragment() {

    private val args:MovieDetailsFragmentArgs by navArgs()
    private var _binding: FragmentSecondBinding? = null
    private lateinit var movieViewModel: MovieViewModel
    private lateinit var movieDetails: MovieDetails
    private lateinit var moviesTrending: MoviesTrending
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        movieViewModel = ViewModelProvider(this, MovieViewModelFactory(RepositoryFactory.createMovieRepository())).get(MovieViewModel::class.java)
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val result:Results=args.movie
        Log.i("movie",""+result.toString())

        movieViewModel.moviesNowList.observe(viewLifecycleOwner, Observer {
            movieDetails=it
            activity?.runOnUiThread {
                binding.recyclerView2.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                binding.recyclerView2.adapter = ProductionCompanyAdapter(requireContext(), movieDetails.production_companies)
            }
            })
        movieViewModel.moviesRecommended.observe(viewLifecycleOwner, Observer {
            moviesTrending=it
            activity?.runOnUiThread {
                binding.recyclerViewRecommended.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                binding.recyclerViewRecommended.adapter = RecommendedMovieAdapter(requireContext(), moviesTrending.results)
            }
        })
        binding.textViewTitle.setText(result.title)
        binding.ratingBar.rating=result.vote_average
        binding.textViewLanguage.text=result.original_language
        binding.textViewOverView.text=result.overview
        binding.textViewPopularity.text=""+result.popularity
        binding.textViewreleaseDate.text=result.release_date
        binding.textViewReleaseStatus.isGone=true
        try {
            var url:String=result.backdrop_path
            Glide.with(requireContext()).asBitmap().load("https://image.tmdb.org/t/p/original"+url).into(
                BitmapImageViewTarget(binding.imageViewBanner)
            )
        }catch (e: Exception){
            e.printStackTrace()
        }
        binding.buttonCast.setOnClickListener {
            val action = MovieDetailsFragmentDirections.actionSecondFragmentToCastnCrewFragment(result.id)
            findNavController().navigate(action)
        }
        binding.buttonReviews.setOnClickListener {
            val action = MovieDetailsFragmentDirections.actionSecondFragmentToReviewFragment(result.id)
            findNavController().navigate(action)
        }
        movieViewModel.getMovieDetails(result.id)
        movieViewModel.getMovieRecommended(result.id)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}