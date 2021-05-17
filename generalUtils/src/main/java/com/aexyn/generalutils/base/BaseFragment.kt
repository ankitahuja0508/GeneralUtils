package com.aexyn.generalutils.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.aexyn.generalutils.BR
import com.aexyn.generalutils.extentions.setUI
import kotlinx.coroutines.flow.collect


abstract class BaseFragment<Binding : ViewDataBinding, ViewModel : BaseViewModel> : Fragment() {

    protected abstract val viewModel: ViewModel
    protected lateinit var binding: Binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if(!::binding.isInitialized){
            binding = DataBindingUtil.inflate(inflater, getLayoutResId(), container, false)
        }
        setUI(binding.root)
        return binding.root
    }

    /**
     * Get layout resource id which inflate in onCreateView.
     */
    @LayoutRes
    abstract fun getLayoutResId(): Int

    abstract fun getMaiViewModel(): BaseViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        doDataBinding()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    /**
     * Do your other stuff in init after binding layout.
     */
    abstract fun init()

    private fun doDataBinding() {
        binding.lifecycleOwner = viewLifecycleOwner // it is extra if you want to set life cycle owner in binding
        binding.setVariable(BR.mainViewModel, getMaiViewModel())
        binding.setVariable(BR.viewModel, viewModel)

        lifecycleScope.launchWhenStarted {
            viewModel.navigationEventFlow.collect {
                findNavController().navigate(it)
            }
        }

        init()
    }

}