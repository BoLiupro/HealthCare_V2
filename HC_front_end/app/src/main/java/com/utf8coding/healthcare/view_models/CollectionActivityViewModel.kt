package com.utf8coding.healthcare.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.utf8coding.healthcare.data.ArticleData
import com.utf8coding.healthcare.networkRelated.NetworkUtils

class CollectionActivityViewModel: ViewModel() {
    val collectionList: MutableLiveData<ArrayList<ArticleData>> = MutableLiveData(ArrayList())
    fun getCollection(userId: Int): MutableLiveData<ArrayList<ArticleData>>{
        NetworkUtils.getCollection(userId,
            onSuccess =  {
                collectionList.value = it
            }, {})
        return collectionList
    }
}