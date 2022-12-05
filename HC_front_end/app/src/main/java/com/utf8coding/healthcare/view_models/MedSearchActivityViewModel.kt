package com.utf8coding.healthcare.view_models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.utf8coding.healthcare.data.MedData
import com.utf8coding.healthcare.networkRelated.NetworkService
import com.utf8coding.healthcare.networkRelated.NetworkUtils
import com.utf8coding.healthcare.utils.GenerateTestContentUtils

class MedSearchActivityViewModel: ViewModel()  {
    companion object{
        //filter modes:
        const val BY_NAME: Int = 1
        const val BY_PRODUCER: Int = 2
        const val BY_TYPE: Int = 3
    }
    var searchMode: Int = BY_NAME
    var medList: MutableLiveData<ArrayList<MedData>> = MutableLiveData<ArrayList<MedData>>(ArrayList())

    //这里的加载有点BUG（未知的重复调用observe，怀疑observe的调用）
    fun getMedListByName(medName: String){
        NetworkUtils.searchMedByName(medName,
            onSuccess = { mMedList: ArrayList<MedData> ->
                medList.value = mMedList
            },
            onFailure = {
                medList.value = ArrayList()
            })
    }

    fun getMedListByType(medType: String){
        NetworkUtils.searchMedByType(medType,
            onSuccess = { mMedList: ArrayList<MedData> ->
                medList.value = mMedList
            }
            , onFailure = {
                medList.value = ArrayList()
            })
    }

    fun getMedListByManufacturer(medManufacturer: String){
        NetworkUtils.searchMedByManufacturer(medManufacturer,
            onSuccess = {mMedList: ArrayList<MedData> ->
                medList.value = mMedList
            },
            onFailure = {
                medList.value = ArrayList()
            })
    }

}