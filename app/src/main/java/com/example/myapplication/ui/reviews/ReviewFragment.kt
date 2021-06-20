package com.example.myapplication.ui.reviews

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentSecondBinding
import com.example.myapplication.databinding.ReviewFragmentBinding
import com.example.myapplication.di.MovieViewModelFactory
import com.example.myapplication.di.RepositoryFactory
import com.example.myapplication.di.ReviewsViewModelFactory
import com.example.myapplication.model.MovieDetails
import com.example.myapplication.model.MoviesTrending
import com.example.myapplication.model.Reviews
import com.example.myapplication.ui.MovieDetailsFragmentArgs
import com.example.myapplication.ui.home.BannerAdapter
import com.example.myapplication.ui.movie.MovieViewModel

class ReviewFragment : Fragment() {

    private val args: ReviewFragmentArgs by navArgs()
    private var _binding: ReviewFragmentBinding? = null
    private lateinit var movieReviews: Reviews
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var viewModel: ReviewViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = ReviewFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, ReviewsViewModelFactory(RepositoryFactory.createReviewRepository())).get(ReviewViewModel::class.java)
        viewModel.moviesReviews.observe(viewLifecycleOwner, Observer {
            movieReviews=it
            activity?.runOnUiThread {
                binding.recyclerViewReview.layoutManager = LinearLayoutManager(context)
                binding.recyclerViewReview.adapter= ReviewsAdapter(requireContext(),movieReviews.results)
            }
        })
        viewModel.getMovieReviews(args.movieId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}