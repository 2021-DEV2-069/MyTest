package com.samples.test.view.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.samples.test.R
import com.samples.test.common.delegate.FragmentBinding
import com.samples.test.databinding.FragmentBoardBinding
import com.samples.test.model.Board
import com.samples.test.view.game.adapter.GameAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameBoardFragment : Fragment() {

    private val dataBindings by FragmentBinding<FragmentBoardBinding>(R.layout.fragment_board)
    private val gameViewModel: GameViewModel by viewModels()
    private lateinit var adapter: GameAdapter
    private val onBoardStateChange = Observer<Board> { board ->
        adapter.submitList(board.cells.map { it.copy() })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ) = dataBindings.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(dataBindings) {
            lifecycleOwner = this@GameBoardFragment
            viewModel = gameViewModel
            adapter = GameAdapter {
                gameViewModel.onCellClick(it)
            }
            gameAdapter = adapter
        }
        initViewStateObservers()
    }

    private fun initViewStateObservers() {
        gameViewModel.boardState.observe(viewLifecycleOwner, onBoardStateChange)
    }

}
