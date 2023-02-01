package com.agelmahdi.cariad.poi_feature.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agelmahdi.cariad.core.util.Resource
import com.agelmahdi.cariad.poi_feature.domain.model.POIsState
import com.agelmahdi.cariad.poi_feature.domain.use_case.PoiUseCase
import com.agelmahdi.cariad.poi_feature.util.Constance.TIME_DELAY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class POIsViewModel @Inject constructor(
    private val getPOIsUC: PoiUseCase
) : ViewModel() {

    private val _stateFlow = MutableStateFlow(POIsState())
    var stateFlow: StateFlow<POIsState> = _stateFlow

    private var poiJob: Job? = null

    init {
        poiFlowStream()
    }

    fun poiFlowStream() {
        poiJob?.cancel()
        poiJob = viewModelScope.launch(Dispatchers.IO) {
            while (isActive) {
                getPOIsUC.fetch().collect {
                    when (it) {
                        is Resource.Success -> _stateFlow.value = POIsState(data = it.data)
                        is Resource.Error -> _stateFlow.value =
                            POIsState(hasError = true, errorMessage = it.message, data = it.data)
                        is Resource.Loading -> _stateFlow.value = POIsState(isLoading = true, data = it.data)
                    }
                }
                delay(TIME_DELAY)
            }
        }
    }
}