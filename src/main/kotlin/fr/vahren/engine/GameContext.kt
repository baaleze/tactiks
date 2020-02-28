package fr.vahren.engine

import fr.vahren.GameEntity
import fr.vahren.engine.type.Player
import fr.vahren.world.World
import org.hexworks.amethyst.api.Context
import org.hexworks.zircon.api.screen.Screen
import org.hexworks.zircon.api.uievent.UIEvent

data class GameContext(val world: World,
                       val screen: Screen,
                       val uiEvent: UIEvent,
                       val player: GameEntity<Player>) : Context