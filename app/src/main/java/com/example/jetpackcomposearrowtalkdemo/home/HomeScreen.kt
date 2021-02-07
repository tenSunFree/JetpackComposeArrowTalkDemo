package com.example.jetpackcomposearrowtalkdemo.home

import androidx.compose.Composable
import androidx.lifecycle.LiveData
import androidx.ui.core.Modifier
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.Text
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.Row
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.livedata.observeAsState
import androidx.ui.material.ripple.ripple
import androidx.ui.res.stringResource
import androidx.ui.unit.dp
import com.example.data.model.Photo
import com.example.jetpackcomposearrowtalkdemo.R
import com.example.jetpackcomposearrowtalkdemo.common.base.Actions
import com.fortyseven.degrees.kotlinmeetup.common.model.ResponseState
import com.example.jetpackcomposearrowtalkdemo.common.composables.Divider
import com.example.jetpackcomposearrowtalkdemo.common.composables.ErrorView
import com.example.jetpackcomposearrowtalkdemo.common.composables.LoadingView
import com.example.jetpackcomposearrowtalkdemo.common.composables.VStack
import com.fortyseven.degrees.kotlinmeetup.common.model.ResponseFailure

interface HomeActions : Actions {
    fun click(photo: Photo)
}

@Composable
fun HomeScreen(
    liveData: LiveData<ResponseState<ResponseFailure, List<Photo>>>,
    actions: Actions
) = liveData.observeAsState().value?.let { state: ResponseState<ResponseFailure, List<Photo>> ->
    when (state) {
        is ResponseState.Loading -> LoadingView()
        is ResponseState.Data -> ListView(
            state.data,
            actions as HomeActions
        )
        is ResponseState.Error -> {
            ErrorView(
                when (state.error) {
                    is ResponseFailure.NoRepositories -> stringResource(R.string.errors_repositories_not_found)
                    is ResponseFailure.RepositoriesError -> state.error.throwable.message
                        ?: stringResource(R.string.errors_unknown)
                }
            )
        }
    }
}

@Composable
fun ListView(
    repositories: List<Photo>,
    actions: HomeActions
) = VStack {
    repositories.map {
        ItemViewClickable(it, actions)
        Divider()
    }
}

@Composable
fun ItemViewClickable(
    repo: Photo,
    actions: HomeActions
) = Clickable(
    modifier = Modifier.ripple(),
    onClick = { actions.click(repo) }
) {
    ItemView(repo = repo)
}

@Composable
fun ItemView(repo: Photo) =
    Column(modifier = Modifier.padding(16.dp).fillMaxWidth()) {
        Text(text = "Name: ${repo.name}", color = Color(0xFFF05837))
        Text(
            text = "Email: ${repo.email}",
            color = Color(0xFFF05837),
            maxLines = 3,
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(
            text = "Body: ${repo.body}",
            color = Color(0xFFF05837),
            maxLines = 3,
            modifier = Modifier.padding(top = 8.dp)
        )
    }