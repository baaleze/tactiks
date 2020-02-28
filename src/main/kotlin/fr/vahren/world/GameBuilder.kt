package fr.vahren.world

import fr.vahren.GameConfig
import fr.vahren.GameConfig.WORLD_SIZE
import fr.vahren.GameEntity
import fr.vahren.engine.EntityFactory
import fr.vahren.engine.type.Player
import org.hexworks.zircon.api.data.Position3D
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

        return Game.create(
                player = player,
                world = world)
    }

    private fun prepareWorld() = also {
        world.scrollUpBy(world.actualSize.zLength)
    }

    private fun addPlayer(): GameEntity<Player> {
        val player = EntityFactory.newPlayer()
        world.addAtEmptyPosition(player,
                offset = Position3D.defaultPosition().withZ(GameConfig.DUNGEON_LEVELS - 1),
                size = world.visibleSize.copy(zLength = 0))
        return player
    }

    companion object {

        fun defaultGame() = GameBuilder(
                worldSize = WORLD_SIZE).buildGame()
    }
}
