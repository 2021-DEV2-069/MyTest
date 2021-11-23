package com.samples.test.common

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.samples.test.R
import com.samples.test.model.GameOnGoing
import com.samples.test.model.GameStatus

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