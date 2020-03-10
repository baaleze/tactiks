package fr.vahren.engine.systems

import fr.vahren.GameCommand
import fr.vahren.engine.GameContext
import fr.vahren.engine.command.Dig
import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.base.BaseFacet
import org.hexworks.amethyst.api.entity.EntityType

object Diggable : BaseFacet<GameContext>() {

    override suspend fun executeCommand(command: GameCommand<out EntityType>) = command.responseWhenCommandIs(Dig::class) { (context, _, target) ->
        context.world.removeEntity(target)
        Consumed
    }
}
