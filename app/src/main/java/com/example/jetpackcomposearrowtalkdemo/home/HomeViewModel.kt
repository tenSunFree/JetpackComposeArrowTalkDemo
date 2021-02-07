package com.example.jetpackcomposearrowtalkdemo.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.fx.IO
import arrow.fx.extensions.fx
import arrow.fx.extensions.io.async.effectMap
import arrow.integrations.kotlinx.unsafeRunScoped
import com.example.data.model.Photo
import com.example.data.repository.HomeRepository
import com.fortyseven.degrees.kotlinmeetup.common.model.ResponseState
import com.fortyseven.degrees.kotlinmeetup.common.model.ResponseFailure
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {

    private val _state =
        MutableLiveData<ResponseState<ResponseFailure, List<Photo>>>(
            ResponseState.Loading()
        )

    val state: LiveData<ResponseState<ResponseFailure, List<Photo>>>
        get() = _state

    fun init() {
        getArrowRepos().unsafeRunScoped(viewModelScope) {}
    }

    private fun getArrowRepos() = arrowReposUseCase().attempt()
        .continueOn(Dispatchers.Main)
        .effectMap {
            it.fold(
                ifLeft = { throwable ->
                    _state.postValue(
                        ResponseState.Error(
                            ResponseFailure.RepositoriesError(throwable)
                        )
                    )
                },
                ifRight = { _state.postValue(it) }
            )
        }

    private fun arrowReposUseCase(): IO<ResponseState<ResponseFailure, List<Photo>>> =
        IO.fx {
            continueOn(Dispatchers.IO)
            val repositories: List<Photo> = homeRepository.getComments().bind()
            if (repositories.isNotEmpty()) {
                ResponseState.Data<ResponseFailure, List<Photo>>(repositories)
            } else {
                ResponseState.Error<ResponseFailure, List<Photo>>(
                    ResponseFailure.NoRepositories
                )
            }
        }
}
