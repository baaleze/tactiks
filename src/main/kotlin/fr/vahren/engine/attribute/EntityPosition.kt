package fr.vahren.engine.attribute

import org.hexworks.amethyst.api.Attribute
import org.hexworks.cobalt.databinding.api.extension.createPropertyFrom
import org.hexworks.zircon.api.data.Position3D

class EntityPosition(initialPosition: Position3D = Position3D.unknown()) : Attribute { // 1
    private val positionProperty = createPropertyFrom(initialPosition) // 2

    var position: Position3D by positionProperty.asDelegate() // 3
}