package fr.vahren.engine.command

import fr.vahren.GameCommand
import fr.vahren.GameEntity
import fr.vahren.engine.GameContext
import org.hexworks.amethyst.api.entity.EntityType

data class Destroy(override val context: GameContext,
                   override val source: GameEntity<EntityType>, // 1
                   val target: GameEntity<EntityType>,  // 2
                   val cause: String = "natural causes.") : GameCommand<EntityType>