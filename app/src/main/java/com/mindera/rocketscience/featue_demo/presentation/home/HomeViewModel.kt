package com.mindera.rocketscience.featue_demo.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mindera.rocketscience.featue_demo.data.remote.manager.SpaceXManager
import com.mindera.rocketscience.featue_demo.presentation.home.filter.FilterUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val apiManager: SpaceXManager
) : ViewModel() {

    private val _filterState = MutableStateFlow(FilterUiState())
    private val _companyInfo = MutableStateFlow(CompanyInfo())
    private val _allLaunches = MutableStateFlow<List<Launch>>(listOf())
    private val _homeState = MutableStateFlow(
        HomeUIState(
            companyInfo = _companyInfo.value,
            launchList = _allLaunches.value
        )
    )
    val homeState = _homeState.asStateFlow()

    init {
        viewModelScope.launch {
            apiManager.getCompanyInfo()?.let { companyInfo ->
                _companyInfo.update { companyInfo }
            }
            _filterState.value.let {
                apiManager.getAllLaunches(
                    offset = it.offset,
                    startDate = it.startDate,
                    endDate = it.endDate,
                    launchSuccess = it.isSuccessful
                )?.let { launchList ->
                    _allLaunches.update { launchList }
                }
            }
            _homeState.update {
                HomeUIState(
                    companyInfo = _companyInfo.value,
                    launchList = _allLaunches.value
                )
            }
        }
    }


    fun nextPage() {
        viewModelScope.launch {
            _filterState.update { it.copy(offset = it.offset?.plus(20)) }
            _filterState.value.let { filterUiState ->
                apiManager.getAllLaunches(
                    offset = filterUiState.offset,
                    startDate = filterUiState.startDate,
                    endDate = filterUiState.endDate,
                    launchSuccess = filterUiState.isSuccessful
                )?.let { list ->
                    _allLaunches.update { it + list }
                    _homeState.update {
                        if (_filterState.value.orderAscending == true){
                            it.copy(launchList = _allLaunches.value.sortedBy { it.date })
                        }else{
                            it.copy(launchList = _allLaunches.value.sortedByDescending { it.date })
                        }
                    }
                }
            }
        }
    }

    fun applyFilter(
        startDate: String,
        endDate: String,
        isSuccessful: Boolean,
        isAscending: Boolean
    ) {
        viewModelScope.launch {
            _filterState.update {
                FilterUiState(
                    offset = 0,
                    startDate = startDate.ifEmpty { null },
                    endDate = endDate.ifEmpty { null },
                    isSuccessful = isSuccessful,
                    orderAscending = isAscending,
                )
            }
            _filterState.value.let {
                apiManager.getAllLaunches(
                    offset = it.offset,
                    startDate = it.startDate,
                    endDate = it.endDate,
                    launchSuccess = it.isSuccessful
                )?.let { list ->
                    _allLaunches.update { list }
                    _homeState.update {
                        if (_filterState.value.orderAscending == true){
                            it.copy(launchList = _allLaunches.value.sortedBy { it.date })
                        }else{
                            it.copy(launchList = _allLaunches.value.sortedByDescending { it.date })
                        }
                    }
                }
            }
        }
    }
}