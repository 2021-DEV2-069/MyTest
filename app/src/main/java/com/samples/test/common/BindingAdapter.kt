package com.samples.test.common

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.samples.test.R
import com.samples.test.model.GameOnGoing
import com.samples.test.model.GameStatus
import com.samples.test.view.game.adapter.GameAdapter

@BindingAdapter("gameStatusText")
fun setGameStatusText(
    textView: TextView,
    gameStatus: GameStatus?,
) {
    gameStatus?.let {
        when (gameStatus) {
            is GameOnGoing -> {
                textView.text = with(textView.context) {
                    val playerName = getString(gameStatus.nextPlayer.playerNameResource)
                    getString(R.string.player_turn, playerName)
                }
            }
            else -> {
            }
        }
    }
}

@BindingAdapter("bindGameAdapter")
fun bindGameAdapter(gameRecyclerView: RecyclerView, gameAdapter: GameAdapter?) {
    gameRecyclerView.addItemDecoration(DividerItemDecoration(gameRecyclerView.context,
        DividerItemDecoration.HORIZONTAL))
    gameRecyclerView.addItemDecoration(DividerItemDecoration(gameRecyclerView.context,
        DividerItemDecoration.VERTICAL))
    gameRecyclerView.itemAnimator = null
    gameRecyclerView.layoutManager = GridLayoutManager(gameRecyclerView.context, BOARD_SIZE)
    gameRecyclerView.adapter = gameAdapter
}