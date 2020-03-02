package fr.vahren.engine.systems

import fr.vahren.GameCommand
import fr.vahren.engine.GameContext
import fr.vahren.engine.command.MoveCamera
import fr.vahren.engine.command.MoveTo
import fr.vahren.engine.position
import fr.vahren.engine.tryActionsOn
import fr.vahren.engine.type.Player
import org.hexworks.amethyst.api.CommandResponse
import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.Pass
import org.hexworks.amethyst.api.Response
import org.hexworks.amethyst.api.base.BaseFacet
import org.hexworks.amethyst.api.entity.EntityType

object Movable : BaseFacet<GameContext>() {

    override fun executeCommand(command: GameCommand<out EntityType>) =
        command.responseWhenCommandIs(MoveTo::class) { (context, entity, position) ->
            val world = context.world
            val previousPosition = entity.position
            val targetBlock = world.fetchBlockAt(position)

            if (targetBlock.isPresent) {
                if (targetBlock.get().isOccupied) {
                    entity.tryActionsOn(context, targetBlock.get().occupier.get())
                } else if (world.moveEntity(entity, position)) {
                    if (entity.type == Player) {
                        CommandResponse(MoveCamera(context, entity, previousPosition))
                    } else Consumed
                } else Pass
            } else Pass
        }
}
