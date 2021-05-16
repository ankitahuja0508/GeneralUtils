package com.aexyn.generalutils.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.activityViewModels
import com.aexyn.generalutils.BR
import com.aexyn.generalutils.MainActivityViewModel
import com.aexyn.generalutils.extentions.setUI
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


abstract class BaseDialogFragment<Binding : ViewDataBinding, ViewModel : BaseViewModel> : BottomSheetDialogFragment() {

    protected abstract val viewModel: ViewModel
    protected lateinit var binding: Binding
    private val mainActivityViewModel = getMaiViewModel()

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
        binding.setVariable(BR.mainViewModel, mainActivityViewModel)
        binding.setVariable(BR.viewModel, viewModel)
        init()
    }

}