package fr.vahren.view

import fr.vahren.GameConfig
import org.hexworks.zircon.api.ComponentDecorations
import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.component.ComponentAlignment
import org.hexworks.zircon.api.component.renderer.ComponentDecorationRenderer
import org.hexworks.zircon.api.graphics.BoxType
import org.hexworks.zircon.api.grid.TileGrid
import org.hexworks.zircon.api.uievent.Processed
import org.hexworks.zircon.api.view.base.BaseView

class MainMenuView(private val tileGrid: TileGrid): BaseView(tileGrid, GameConfig.THEME) {

    override fun onDock() {
        val msg = "Welcome to Caves of Zircon."
        val header = Components.textBox(msg.length) // a text box can hold headers, paragraphs and list items
                .addHeader(msg) // we add a header
                .addNewLine() // and a new line
                .withAlignmentWithin(screen, ComponentAlignment.CENTER) // and align it to center
                .build() // finally we build the component
        val startButton = Components.button()
                // we align the button to the bottom center of our header
                .withAlignmentAround(header, ComponentAlignment.BOTTOM_CENTER)
                .withText("Start!") // its text is "Start!"
                .withDecorations(
                        ComponentDecorations.box(BoxType.SINGLE, "", ComponentDecorationRenderer.RenderingMode.INTERACTIVE),
                        ComponentDecorations.shadow())
                .build()
        startButton.onActivated {
            replaceWith(PlayView(tileGrid))
            Processed
        }

        screen.addComponent(header)
        screen.addComponent(startButton)
    }

}