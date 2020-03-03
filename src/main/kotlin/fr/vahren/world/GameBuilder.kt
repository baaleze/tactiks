package fr.vahren.world

import fr.vahren.GameConfig
import fr.vahren.GameConfig.DUNGEON_LEVELS
import fr.vahren.GameConfig.FUNGI_PER_LEVEL
import fr.vahren.GameConfig.WORLD_SIZE
import fr.vahren.GameEntity
import fr.vahren.engine.EntityFactory
import fr.vahren.engine.type.Player
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.zircon.api.data.Position3D
import org.hexworks.zircon.api.data.Size
import org.hexworks.zircon.api.data.Size3D

class GameBuilder(val worldSize: Size3D) {

    private val visibleSize = Size3D.create(
            xLength = GameConfig.WINDOW_WIDTH - GameConfig.SIDEBAR_WIDTH,
            yLength = GameConfig.WINDOW_HEIGHT - GameConfig.LOG_AREA_HEIGHT,
            zLength = 1)

    val world = WorldBuilder(worldSize) // 3
            .makeCaves()
            .build(visibleSize = visibleSize)

    fun buildGame(): Game {

        prepareWorld()

        val player = addPlayer()
        addFungi()

        return Game.create(
                player = player,
                world = world)
    }

    private fun prepareWorld() = also {
        world.scrollUpBy(world.actualSize.zLength)
    }

    private fun addPlayer(): GameEntity<Player> {
        return EntityFactory.newPlayer().addToWorld(DUNGEON_LEVELS - 1, world.visibleSize.to2DSize())
    }

    private fun addFungi() = also {
        repeat(world.actualSize.zLength) { level ->
            repeat(FUNGI_PER_LEVEL) {
                EntityFactory.newFungus().addToWorld(level)
            }
        }
    }


    private fun <T : EntityType> GameEntity<T>.addToWorld(
            atLevel: Int,
            atArea: Size = world.actualSize.to2DSize()): GameEntity<T> {
        world.addAtEmptyPosition(this,
                offset = Position3D.defaultPosition().withZ(atLevel),
                size = Size3D.from2DSize(atArea))
        return this
    }


    companion object {

        fun defaultGame() = GameBuilder(
                worldSize = WORLD_SIZE).buildGame()
    }
}
