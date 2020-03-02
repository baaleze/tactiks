package fr.vahren.engine.command

import fr.vahren.GameCommand
import fr.vahren.GameEntity
import fr.vahren.engine.GameContext
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.zircon.api.data.Position3D

data class MoveTo(override val context: GameContext,
                  override val source: GameEntity<EntityType>,
                  val position: Position3D) : GameCommand<EntityType>