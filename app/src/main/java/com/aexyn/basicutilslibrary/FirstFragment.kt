package com.aexyn.basicutilslibrary

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.aexyn.basicutilslibrary.databinding.FragmentFirstBinding
import com.aexyn.generalutils.base.BaseFragment
import com.aexyn.generalutils.base.BaseViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class FirstFragment : BaseFragment<FragmentFirstBinding, FirstFragmentViewModel>() {

    override val viewModel: FirstFragmentViewModel by viewModels()

    override fun getLayoutResId(): Int {
        return R.layout.fragment_first
    }

    override fun getMaiViewModel(): BaseViewModel {
        val mainActivityViewModel: MainActivityViewModel by activityViewModels()
        return mainActivityViewModel
    }

    override fun init() {
        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }
}