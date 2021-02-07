package com.fortyseven.degrees.kotlinmeetup.common.model

sealed class ResponseFailure {

    object NoRepositories : ResponseFailure()

    class RepositoriesError(val throwable: Throwable) : ResponseFailure()
}
