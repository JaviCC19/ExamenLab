package com.uvg.javiercc.labclase12.presentation.Screen1

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.uvg.javiercc.labclase12.data.network.KtorAssetsApi
import com.uvg.javiercc.labclase12.data.repository.AssetRepositoryImpl
import com.uvg.javiercc.labclase12.di.KtorDependencies
import com.uvg.javiercc.labclase12.domain.Model.DataError
import com.uvg.javiercc.labclase12.domain.Repository.AssetRepository
import com.uvg.javiercc.labclase12.domain.network.util.onError
import com.uvg.javiercc.labclase12.domain.network.util.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AssetListViewModel(
    private val assetRepo: AssetRepository
): ViewModel() {

    private val _state = MutableStateFlow(AssetListState())
    val state = _state.asStateFlow()

    init {
        getAssets()
    }

    fun getAssets() {
        viewModelScope.launch {
            _state.update { it.copy(
                isLoading = true,
                isGenericError = false,
                noInternetConnection = false
            )}

            assetRepo
                .getAllAssets()
                .onSuccess { assets ->
                    _state.update { it.copy(
                        isLoading = false,
                        data = assets
                    ) }
                }
                .onError { error ->
                    if (error == DataError.NO_INTERNET) {
                        _state.update { it.copy(
                            isLoading = false,
                            noInternetConnection = true
                        ) }
                    } else {
                        _state.update { it.copy(
                            isLoading = false,
                            isGenericError = true,
                        ) }
                    }
                }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val context = checkNotNull(this[APPLICATION_KEY])
                val api = KtorAssetsApi(KtorDependencies.provideHttpClient())
                val db = KtorDependencies.provideLocalDb(
                    context = context
                )
                AssetListViewModel(
                    assetRepo = AssetRepositoryImpl(
                        assetApi = api,
                        assetDao = db.AssetDao()
                    )
                )
            }
        }
    }

}