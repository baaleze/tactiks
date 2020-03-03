package fr.vahren.engine.command

import fr.vahren.GameEntity
import fr.vahren.engine.GameContext
import org.hexworks.amethyst.api.entity.EntityType

data class Attack(override val context: GameContext,
                  override val source: GameEntity<EntityType>,
                  override val target: GameEntity<EntityType>) : EntityAction<EntityType, EntityType>
