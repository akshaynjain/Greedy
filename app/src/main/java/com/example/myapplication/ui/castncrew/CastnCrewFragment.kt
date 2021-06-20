package com.example.myapplication.ui.castncrew

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.myapplication.databinding.FragmentCastnCrewBinding
import com.example.myapplication.di.CastNCrewViewModelfactory
import com.example.myapplication.di.RepositoryFactory
import com.example.myapplication.model.Credits


class CastnCrewFragment : Fragment() {

    private val args: CastnCrewFragmentArgs by navArgs()
    private var _binding: FragmentCastnCrewBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CastNCrewViewModel
    private lateinit var credits: Credits

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel=ViewModelProvider(
            this,
            CastNCrewViewModelfactory(RepositoryFactory.createCastnCrewRepository())
        ).get(CastNCrewViewModel::class.java)
        _binding = FragmentCastnCrewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        val layoutManager = GridLayoutManager(context,2)
//        layoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS;
        binding.recyclerView.layoutManager=layoutManager

        viewModel.moviesCast.observe(viewLifecycleOwner, Observer {
            credits = it
            activity?.runOnUiThread {
                binding.recyclerView.adapter = CastCrewAdapter(requireContext(), credits.cast)
            }
        })
        viewModel.getMovieCastnCrew(args.movieId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}