package com.utf8coding.healthcare.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.utf8coding.healthcare.data.EpidemicData
import com.utf8coding.healthcare.data.LifeIndexData
import com.utf8coding.healthcare.networkRelated.NetworkUtils
import com.utf8coding.healthcare.utils.GenerateTestContentUtils

class HealthFragmentViewModel : ViewModel() {
    var lifeIndexData: MutableLiveData<LifeIndexData> = MutableLiveData()
    var epidemicData: MutableLiveData<EpidemicData> = MutableLiveData()

    fun getLifeIndex(): MutableLiveData<LifeIndexData>{
        NetworkUtils.getLifeIndex(
            onSuccess = { mLifeIndexData: LifeIndexData ->
                lifeIndexData.value = mLifeIndexData
            },
            onFailure = {
                lifeIndexData.value = GenerateTestContentUtils.generateLifeIndexData()
            }
        )
        return lifeIndexData
    }
    
    fun getEpidemic(): MutableLiveData<EpidemicData>{
        NetworkUtils.getEpidemicData(
            onSuccess = { mEpidemicData: EpidemicData -> epidemicData.value = mEpidemicData },
            onFailure = { epidemicData.value = GenerateTestContentUtils.generateEpidemicData() }
        )
        return epidemicData
    }
}