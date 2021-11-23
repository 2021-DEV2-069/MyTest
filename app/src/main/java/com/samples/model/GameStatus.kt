package com.samples.model

sealed interface GameStatus

class GameOnGoing(val nextPlayer: PlayerType) : GameStatus
object GameDraw : GameStatus
class GameWin(val player: PlayerType) : GameStatus