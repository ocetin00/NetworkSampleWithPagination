package com.oguzhancetin.networksample.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.oguzhancetin.networksample.util.Utils
import com.oguzhancetin.networksample.databinding.FragmentUserListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class UserListFragment : Fragment() {

    private var _binding: FragmentUserListBinding? = null
    private val viewModel: UserListFragmentViewModel by viewModels()
    private lateinit var adapter: RcUserListAdapter

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserListBinding.inflate(inflater, container, false)

        adapter = RcUserListAdapter()
        binding.rcUser.adapter = adapter
        binding.rcUser.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(Utils.isNetworkAvailable(requireContext())) {
            adapter.retry()
        }
        else{
            Utils.showNetworkError(requireContext())
        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.usersPagingDataFlow.collectLatest {
                adapter.submitData(it)
                binding.progressBar.visibility = View.GONE

            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}