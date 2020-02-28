package fr.vahren.world

import fr.vahren.GameEntity
import fr.vahren.engine.type.Player

class Game(val world: World,
           val player: GameEntity<Player>) {

    companion object {

        fun create(world: World, player: GameEntity<Player>): Game {
            return Game(world, player)
        }
    }
}
