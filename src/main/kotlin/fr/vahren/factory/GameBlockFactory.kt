package fr.vahren.factory

import fr.vahren.world.GameBlock

object GameBlockFactory {

    fun floor() = GameBlock(GameTileFactory.FLOOR)

    fun wall() = GameBlock(GameTileFactory.WALL)

}
