package fr.vahren.factory

import fr.vahren.world.GameBlock

object GameBlockFactory {

    fun floor() = GameBlock(GameTilesFactory.FLOOR)

    fun wall() = GameBlock(GameTilesFactory.WALL)

}
