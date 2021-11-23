package com.samples.test.view.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.samples.test.R
import com.samples.test.common.delegate.FragmentBinding
import com.samples.test.databinding.FragmentBoardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameBoardFragment : Fragment() {

    private val dataBindings by FragmentBinding<FragmentBoardBinding>(R.layout.fragment_board)
    private val gameViewModel: GameViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ) = dataBindings.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBindings.lifecycleOwner = this
        dataBindings.viewModel = gameViewModel
    }
}
