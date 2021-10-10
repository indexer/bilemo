package com.indexer.bilemo.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.indexer.bilemo.BR
import com.indexer.bilemo.R
import com.indexer.bilemo.databinding.FragmentUserListBinding
import com.indexer.bilemo.user.adapter.UserListAdapter
import com.indexer.bilemo.user.enitity.UserResponse
import com.indexer.bilemo.user.viewmodel.UserListViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UserListFragment : Fragment() {

    lateinit var userListBinding: FragmentUserListBinding
    private val userListAdapter = UserListAdapter { user -> userItemClick(user) }
    private val userListViewModel: UserListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        userListBinding = FragmentUserListBinding.inflate(layoutInflater, container, false)
        userListBinding.lifecycleOwner = this
        userListBinding.setVariable(BR.userListViewModel, userListViewModel)
        val toolbar: Toolbar = userListBinding.toolbarUserList
        val activity = activity as AppCompatActivity?
        activity?.setSupportActionBar(toolbar)
        activity?.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        return userListBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userListViewModel.getAllUserList()
        userListBinding.userList.adapter = userListAdapter
        userListViewModel.userList.observe(viewLifecycleOwner, {
            userListAdapter.submitList(it)
        })

        userListBinding.logoutUser.setOnClickListener {
            userListViewModel.saveRememberState(false)
            findNavController().navigateUp()
        }

    }

    private fun userItemClick(user: UserResponse) {
        val bundle = Bundle()
        bundle.putInt(getString(R.string.userId), user.id)
        findNavController()
            .navigate(R.id.action_userListFragment_to_userDetailFragment, bundle)
    }


}