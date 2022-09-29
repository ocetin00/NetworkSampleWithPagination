package com.oguzhancetin.networksample.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.oguzhancetin.networksample.databinding.FragmentFirstBinding
import com.oguzhancetin.networksample.model.Civilization
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val viewModel: FirstFragmentViewModel by viewModels()
    private lateinit var civilizationsRc: RecyclerView
    private lateinit var adapter: RcAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        adapter = RcAdapter(listOf<Civilization>())
        binding.rcCivilization.adapter = adapter
        binding.rcCivilization.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.civilizationList.observe(viewLifecycleOwner) {
            Log.e("civilization", it.toString())
            it?.let {
                adapter.updateData(it)
            }

        }
        /**
        binding.buttonFirst.setOnClickListener {
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
         **/
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}