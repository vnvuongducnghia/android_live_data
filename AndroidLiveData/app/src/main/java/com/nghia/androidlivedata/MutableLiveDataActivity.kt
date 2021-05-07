package com.nghia.androidlivedata

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_mutable_live_data.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MutableLiveDataActivity : AppCompatActivity() {

    val liveDataA = MutableLiveData<String>()

    private val changeObserver = Observer<String> { value ->
        value?.let {
            txt_livedata_a.text = it
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mutable_live_data)
        title = "Mutable Live Data"

        if (savedInstanceState != null) {
            setFragmentControlButtonText()
        }

        // Register
        liveDataA.observe(this, changeObserver)

        btn_livedata_a.setOnClickListener {
            txt_livedata_a.resetLoader()
            GlobalScope.launch {
                delay(500)
                liveDataA.postValue((1..9999).random())
            }
        }

        btn_control_fragment.setOnClickListener {
            if (supportFragmentManager.backStackEntryCount == 0) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MutableLiveDataFragment())
                    .commit()
            } else {
                supportFragmentManager.popBackStack()
            }
        }

        supportFragmentManager.addOnBackStackChangedListener {
            setFragmentControlButtonText()
        }

    }

    private fun setFragmentControlButtonText() {
        if (supportFragmentManager.backStackEntryCount == 0) {
            btn_control_fragment.text = "Add Fragment"
        } else {
            btn_control_fragment.text = "Remove Fragment"
        }
    }
}