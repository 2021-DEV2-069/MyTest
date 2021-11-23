package com.samples.test.model

import com.samples.test.R

sealed class PlayerType(val playerNameResource: Int)

object XPlayer : PlayerType(R.string.x_player)
object OPlayer : PlayerType(R.string.o_player)