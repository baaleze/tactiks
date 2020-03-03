package fr.vahren.engine.attribute

import fr.vahren.GameConfig
import org.hexworks.amethyst.api.Attribute

data class FungusSpread(
        var spreadCount: Int = 0,
        val maximumSpread: Int = GameConfig.MAXIMUM_FUNGUS_SPREAD) : Attribute
