package com.example.jetpackcomposearrowtalkdemo.home

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.Recomposer
import androidx.fragment.app.viewModels
import androidx.ui.core.setContent
import androidx.ui.foundation.contentColor
import androidx.ui.material.MaterialTheme
import com.example.data.model.Photo
import com.example.jetpackcomposearrowtalkdemo.R
import com.example.jetpackcomposearrowtalkdemo.common.base.BaseFragment
import com.example.jetpackcomposearrowtalkdemo.common.other.lightThemeColors

class HomeFragment : BaseFragment(), HomeActions {

    private val viewModel: HomeViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (view as ViewGroup).setContent(Recomposer.current()) {
            MaterialTheme(colors = lightThemeColors) {
                HomeScreen(viewModel.state, this)
            }
        }
        viewModel.init()
    }

    override fun click(photo: Photo) =
        Toast.makeText(requireActivity(), "${photo.email}", Toast.LENGTH_SHORT).show()
}
