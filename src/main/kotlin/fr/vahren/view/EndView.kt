package fr.vahren.view

import org.hexworks.zircon.api.ColorThemes
import org.hexworks.zircon.api.ComponentDecorations
import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.component.ComponentAlignment
import org.hexworks.zircon.api.component.renderer.ComponentDecorationRenderer
import org.hexworks.zircon.api.graphics.BoxType
import org.hexworks.zircon.api.grid.TileGrid
import org.hexworks.zircon.api.view.base.BaseView

class EndView(private val tileGrid: TileGrid, private val lost: Boolean) : BaseView(tileGrid, ColorThemes.arc()) {

    override fun onDock() {
        val msg = if (lost) "You LOST" else "You won!"
        val header = Components.textBox(30)
                .addHeader(msg)
                .withAlignmentWithin(screen, ComponentAlignment.CENTER)
                .build()
        val restartButton = Components.button()
                .withAlignmentAround(header, ComponentAlignment.BOTTOM_LEFT)
                .withText("Restart")
                .withDecorations(
                        ComponentDecorations.box(BoxType.SINGLE, "<<", ComponentDecorationRenderer.RenderingMode.INTERACTIVE),
                        ComponentDecorations.shadow())
                .build()
        val exitButton = Components.button()
                .withAlignmentAround(header, ComponentAlignment.BOTTOM_RIGHT)
                .withText("Quit")
                .withDecorations(
                        ComponentDecorations.box(BoxType.DOUBLE, "xx", ComponentDecorationRenderer.RenderingMode.INTERACTIVE),
                        ComponentDecorations.shadow())
                .build()

        restartButton.onActivated {
            replaceWith(PlayView(tileGrid))
        }

        exitButton.onActivated {
            System.exit(0)
        }

        screen.addComponent(header)
        screen.addComponent(restartButton)
        screen.addComponent(exitButton)
    }
}