package com.samples.test.common

import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.children
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.samples.test.R
import com.samples.test.model.*
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
            is GameWin -> {
                textView.text = with(textView.context) {
                    val playerName = getString(gameStatus.player.playerNameResource)
                    getString(R.string.winner, playerName)
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

@BindingAdapter("cellItemView")
fun cellItemView(
    cellView: FrameLayout,
    cell: Cell?,
) {
    cell?.let {
        val cellState = it.state
        with(cellView.children.first() as ImageView) {
            setImageResource(
                when (cellState) {
                    OSelected -> R.drawable.ic_o
                    XSelected -> R.drawable.ic_x
                    UnSelected -> 0
                }
            )
        }
    }
}