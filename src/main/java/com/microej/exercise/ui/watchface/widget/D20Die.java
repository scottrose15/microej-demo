package com.microej.exercise.ui.watchface.widget;

import com.microej.exercise.ui.util.Model;

import java.util.Random;

import ej.drawing.ShapePainter;
import ej.microui.display.Font;
import ej.microui.display.GraphicsContext;
import ej.microui.display.Image;
import ej.microui.display.Painter;
import ej.microui.event.Event;
import ej.microui.event.generator.Buttons;
import ej.microui.event.generator.Pointer;
import ej.mwt.Widget;
import ej.mwt.style.Style;
import ej.mwt.util.Alignment;
import ej.mwt.util.Size;

public class D20Die extends Widget {

    private final Image icon;

    /**
     * Creates the widget with a path to the icon resource to use and the text to display.
     *
     * @param iconPath
     *            the path to the icon to use.
     *            the text to use.
     */
    public D20Die(String iconPath) {
        super(true);
        this.icon = Image.getImage(iconPath);
    }

    @Override
    protected void renderContent(GraphicsContext g, int contentWidth, int contentHeight) {
        Model model = Model.getInstance();
        String d20RollAmount = String.valueOf(model.getD20RollAmount());
        // retrieves the style for this widget
        Style style = getStyle();
        Font font = style.getFont();
        int textColor = style.getColor();
        int iconColor = style.getExtraInt(IconLabel.EXTRA_FIELD_ICON_COLOR, textColor);

        // sets the color to use for coloring the image
        g.setColor(iconColor);

        // draws the icon
        int textWidth = font.stringWidth(d20RollAmount);
        int iconWidth = this.icon.getWidth();
        // computes the position of the anchor point of the image (top-left corner)
        int iconX = Alignment.computeLeftX(iconWidth, 0, contentWidth, style.getHorizontalAlignment());
        int iconY = Alignment.computeTopY(this.icon.getHeight(), 0, contentHeight, style.getVerticalAlignment());
        Painter.drawImage(g, this.icon, iconX, iconY);

        // set the color to use for the text
        g.setColor(textColor);

        // draws the text
        // computes the position of the anchor point of the text (top-left corner)
        int textOffset = (model.getD20RollAmount() < 10) ? 9 : 4;
        int textX = Alignment.computeLeftX(font.getHeight(), 0, contentWidth, style.getHorizontalAlignment()) + textOffset;
        int textY = Alignment.computeTopY(font.getHeight(), 0, contentHeight, style.getVerticalAlignment()) - 3;
        Painter.drawString(g, d20RollAmount, font, textX, textY);
    }

    @Override
    protected void computeContentOptimalSize(Size size) {
        // retrieves the style for this widget
        Style style = getStyle();
        Font font = style.getFont();

        // the optimal content width is the sum of the icon width and text width
        int width = this.icon.getWidth();
        // the optimal content height is the maximum between the icon height and text height
        int height = Math.max(this.icon.getHeight(), font.getHeight());

        size.setSize(width, height);
    }

    @Override
    public boolean handleEvent(int event) {
        Model model = Model.getInstance();
        int type = Event.getType(event);
        if (type == Pointer.EVENT_TYPE) {
            int action = Buttons.getAction(event);
            if (action == Buttons.RELEASED) {
                Random random = new Random();
                int nextRollAmount = (Math.abs(random.nextInt()) % 20) + 1;
                model.setD20RollAmount(nextRollAmount);
                this.requestRender();
                return true;
            }
        }
        return super.handleEvent(event);
    }
}
