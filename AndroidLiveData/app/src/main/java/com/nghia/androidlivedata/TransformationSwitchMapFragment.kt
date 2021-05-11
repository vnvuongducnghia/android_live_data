package com.nghia.androidlivedata

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import kotlinx.android.synthetic.main.fragment_transformation_switch_map.*

class TransformationSwitchMapFragment : Fragment() {

    private val changeObserver = Observer<String> { value ->
        value?.let {
            txt_fragment.text = it
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val activityParent = activity as TransformationSwitchMapActivity
        val transformSwitchedLiveData =
            Transformations.switchMap(activityParent.liveDataSwitch) {
            switchToB ->
                if(switchToB) {
                    activityParent.liveDataB
                }
                else{
                    activityParent.liveDataA
                }
            }

        // Register connect View and
        transformSwitchedLiveData.observe(this,changeObserver)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transformation_switch_map, container, false)
    }

}