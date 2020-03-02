package fr.vahren.engine.systems

import fr.vahren.GameCommand
import fr.vahren.engine.GameContext
import fr.vahren.engine.command.MoveCamera
import fr.vahren.engine.position
import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.base.BaseFacet
import org.hexworks.amethyst.api.entity.EntityType

object MovesCamera : BaseFacet<GameContext>() {

    override fun executeCommand(command: GameCommand<out EntityType>) =
            command.responseWhenCommandIs(MoveCamera::class) { cmd ->
                val (context, source, previousPosition) = cmd
                val world = context.world
                val screenPos = source.position - world.visibleOffset
                val halfHeight = world.visibleSize.yLength / 2
                val halfWidth = world.visibleSize.xLength / 2
                val currentPosition = source.position
                when {
                    previousPosition.y > currentPosition.y && screenPos.y < halfHeight -> {
                        world.scrollOneBackward()
                    }
                    previousPosition.y < currentPosition.y && screenPos.y > halfHeight -> {
                        world.scrollOneForward()
                    }
                    previousPosition.x > currentPosition.x && screenPos.x < halfWidth -> {
                        world.scrollOneLeft()
                    }
                    previousPosition.x < currentPosition.x && screenPos.x > halfWidth -> {
                        world.scrollOneRight()
                    }
                }
                Consumed
            }
}