package com.nghia.androidlivedata

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_mutable_live_data.*

class MutableLiveDataFragment : Fragment() {

    private val changeObserver = Observer<String> { value ->
        value?.let { txt_fragment.text = it }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MutableLiveDataActivity).liveDataA.observe(this, changeObserver)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mutable_live_data, container, false)
    }

}