package fr.vahren.world

import fr.vahren.GameConfig
import org.hexworks.zircon.api.data.Size3D

class Game(val world: World) {

    companion object {

        fun create(worldSize: Size3D = GameConfig.WORLD_SIZE,
                   visibleSize: Size3D = GameConfig.WORLD_VISIBLE_SIZE) = Game(WorldBuilder(worldSize)
                .makeCaves()
                .build(visibleSize))
    }
}
