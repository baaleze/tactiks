package fr.vahren.engine.systems

import fr.vahren.GameEntity
import fr.vahren.engine.GameContext
import fr.vahren.engine.command.MoveTo
import fr.vahren.engine.position
import org.hexworks.amethyst.api.base.BaseBehavior
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.zircon.api.uievent.KeyCode
import org.hexworks.zircon.api.uievent.KeyboardEvent

object InputReceiver : BaseBehavior<GameContext>() {

    override fun update(entity: GameEntity<out EntityType>, context: GameContext): Boolean {
        val (_, _, uiEvent, player) = context // 1
        val currentPos = player.position
        if (uiEvent is KeyboardEvent) {  // 2
            val newPosition = when (uiEvent.code) { // 3
                KeyCode.KEY_Z -> currentPos.withRelativeY(-1)
                KeyCode.KEY_Q -> currentPos.withRelativeX(-1)
                KeyCode.KEY_S -> currentPos.withRelativeY(1)
                KeyCode.KEY_D -> currentPos.withRelativeX(1)
                else -> {
                    currentPos // 4
                }
            }
            player.executeCommand(MoveTo(context, player, newPosition)) // 5
        }
        return true
    }
}