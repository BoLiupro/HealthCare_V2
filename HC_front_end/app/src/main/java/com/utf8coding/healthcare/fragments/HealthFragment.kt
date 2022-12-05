package com.utf8coding.healthcare.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import com.utf8coding.healthcare.R
import com.utf8coding.healthcare.data.LifeIndexData
import com.utf8coding.healthcare.view_models.HealthFragmentViewModel

class HealthFragment : BaseFragment() {

    private lateinit var viewModel: HealthFragmentViewModel
    private val outBorderCountText: TextView?
        get() {
            return view?.findViewById(R.id.outBorderCount)
        }
    private val infoTimeText: TextView?
        get() {
            return view?.findViewById(R.id.timeText)
        }
    private val infectionWithNoSymptomText: TextView?
        get() {
            return view?.findViewById(R.id.infectionWithNoSymptom)
        }
    private val diagnosedText: TextView?
        get() {
            return view?.findViewById(R.id.diagnosed)
        }
    private val curedText: TextView?
        get() {
            return view?.findViewById(R.id.cured)
        }
    private val fluIndexText: TextView?
        get() {
            return view?.findViewById(R.id.fluIndex)
        }
    private val clothIndexText: TextView?
        get() {
            return view?.findViewById(R.id.clothIndex)
        }

    private val radioIndexText: TextView?
        get() {
            return view?.findViewById(R.id.radioIndex)
        }

    private val allergyIndexText: TextView?
        get() {
            return view?.findViewById(R.id.allergyIndex)
        }

    private val spotIndexText: TextView?
        get() {
            return view?.findViewById(R.id.sportIndex)
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[HealthFragmentViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_health, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLifeIndex().observe(viewLifecycleOwner) {

        }
        viewModel.getEpidemic().observe(viewLifecycleOwner){
            infoTimeText?.text = "(${it.time})"
            outBorderCountText?.text = it.outOfBorder
            infectionWithNoSymptomText?.text = it.noSymp
            diagnosedText?.text = it.diagnosed
            curedText?.text = it.cured
        }
        viewModel.getLifeIndex().observe(viewLifecycleOwner){
            fluIndexText?.text = it.fluIndex
            clothIndexText?.text = it.clothIndex
            radioIndexText?.text = it.radioIndex
            allergyIndexText?.text = it.allergyIndex
            spotIndexText?.text = it.sportIndex
        }
    }

    override fun refresh() {
        viewModel.getEpidemic().observe(viewLifecycleOwner){
            outBorderCountText?.text = it.outOfBorder
            infectionWithNoSymptomText?.text = it.noSymp
            diagnosedText?.text = it.diagnosed
            curedText?.text = it.cured
        }
        viewModel.getLifeIndex().observe(viewLifecycleOwner){
            fluIndexText?.text = it.fluIndex
            clothIndexText?.text = it.clothIndex
            radioIndexText?.text = it.radioIndex
            allergyIndexText?.text = it.allergyIndex
            spotIndexText?.text = it.sportIndex
        }
    }

    //tools
    private fun makeToast(msg: String){
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }
    private fun makeILog(msg: String){
        Log.i("HealthFragment:", msg)
    }
}