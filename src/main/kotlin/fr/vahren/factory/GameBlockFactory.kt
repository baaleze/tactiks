package fr.vahren.factory

import fr.vahren.engine.EntityFactory
import fr.vahren.world.GameBlock

object GameBlockFactory {

    fun floor() = GameBlock(GameTileFactory.FLOOR)

    fun wall() = GameBlock.createWith(EntityFactory.newWall())


}
