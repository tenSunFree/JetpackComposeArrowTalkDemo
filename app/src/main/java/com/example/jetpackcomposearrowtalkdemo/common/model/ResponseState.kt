package com.fortyseven.degrees.kotlinmeetup.common.model

sealed class ResponseState<Error, Success> {
    class Loading<Error, Success> : ResponseState<Error, Success>()

    class Data<Error, Success>(val data: Success) : ResponseState<Error, Success>()

    class Error<Error, Success>(val error: Error) : ResponseState<Error, Success>()
}
