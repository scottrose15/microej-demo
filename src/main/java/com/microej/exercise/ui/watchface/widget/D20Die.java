package com.microej.exercise.ui.watchface.widget;

import com.microej.exercise.ui.util.Model;

import java.util.Random;

import ej.bon.Util;
import ej.drawing.TransformPainter;
import ej.microui.display.Font;
import ej.microui.display.GraphicsContext;
import ej.microui.display.Image;
import ej.microui.display.Painter;
import ej.microui.event.Event;
import ej.microui.event.generator.Buttons;
import ej.microui.event.generator.Pointer;
import ej.mwt.Widget;
import ej.mwt.animation.Animation;
import ej.mwt.style.Style;
import ej.mwt.util.Alignment;
import ej.mwt.util.Size;

public class D20Die extends Widget implements Animation {

    /**
     * Image icon for the 20 sided Die
     */
    private final Image icon;

    /**
     * Is true when an animation is underway, and false otherwise
     */
    private boolean isAnimating = false;

    /**
     * Defines the angle of rotation for the Die icon
     */
    private int angleOfRotation = 0;

    /**
     * Stores the time at which an animation was started so the elapsed time can be calculated
     */
    private long startTime;

    /**
     * The time elapsed since an animation has started
     */
    private long elapsedTime;

    /**
     * Duration of the die roll animation in milliseconds.
     */
    private static final long animationDuration = 2_000;

    /**
     * Speed multiplier for the rotation animation.
     */
    private static final int animationSpeedConstant = 5;

    /**
     * Random number generator used for generating roll outcomes.
     */
    private final Random random;

    /**
     * Creates the widget with a path to the icon resource to use and the text to display.
     *
     * @param iconPath
     *            the path to the icon to use.
     */
    public D20Die(String iconPath) {
        super(true);
        this.icon = Image.getImage(iconPath);
        this.random = new Random();
    }

    @Override
    protected void renderContent(GraphicsContext g, int contentWidth, int contentHeight) {
        Model model = Model.getInstance();
        Style style = getStyle();
        drawDie(g, contentWidth, contentHeight, style);
        if (!this.isAnimating){
            drawRollAmount(g, model, contentWidth, contentHeight, style);
        }
    }

    /**
     * Draws the die icon, applying the current rotation angle if animating.
     * @param g - The GraphicsContext
     * @param contentWidth - the width of content
     * @param contentHeight - the height of content
     * @param style - the style for the content
     */
    private void drawDie(GraphicsContext g, int contentWidth, int contentHeight, Style style) {
        int textColor = style.getColor();
        int iconColor = style.getExtraInt(IconLabel.EXTRA_FIELD_ICON_COLOR, textColor);

        // sets the color to use for coloring the image
        g.setColor(iconColor);

        // draws the icon
        int iconWidth = this.icon.getWidth();
        int iconHeight = this.icon.getHeight();
        // computes the position of the anchor point of the image (top-left corner)
        int iconX = Alignment.computeLeftX(iconWidth, 0, contentWidth, style.getHorizontalAlignment());
        int iconY = Alignment.computeTopY(iconHeight, 0, contentHeight, style.getVerticalAlignment());

        int rotationCenterX = iconX + (iconWidth / 2);
        int rotationCenterY = iconY + (iconHeight / 2);

        //Painter.drawImage(g, this.icon, iconX, iconY);
        TransformPainter.drawRotatedImageBilinear(g,this.icon,iconX,iconY, rotationCenterX, rotationCenterY, this.angleOfRotation);
    }

    /**
     * Draws the roll result value centered on the die.
     * @param g - The GraphicsContext
     * @param model - the model for the data
     * @param contentWidth - the width of content
     * @param contentHeight - the height of content
     * @param style - the style for the content
     */
    private void drawRollAmount(GraphicsContext g, Model model, int contentWidth, int contentHeight, Style style){
        Font font = style.getFont();
        int textColor = style.getColor();
        // set the color to use for the text
        g.setColor(textColor);
        int d20RollAmount = model.getD20RollAmount();

        // computes the position of the anchor point of the text (top-left corner)
        int textOffsetX = (d20RollAmount < 10) ? 9 : 4;
        int textOffsetY = -3;
        int textX = Alignment.computeLeftX(font.getHeight(), 0, contentWidth, style.getHorizontalAlignment()) + textOffsetX;
        int textY = Alignment.computeTopY(font.getHeight(), 0, contentHeight, style.getVerticalAlignment()) + textOffsetY;
        Painter.drawString(g, String.valueOf(d20RollAmount) , font, textX, textY);
    }

    @Override
    protected void computeContentOptimalSize(Size size) {
        size.setSize(this.icon.getWidth(), this.icon.getHeight());
    }

    @Override
    public boolean handleEvent(int event) {
        int type = Event.getType(event);
        if (type == Pointer.EVENT_TYPE) {
            int action = Buttons.getAction(event);
            if (action == Buttons.RELEASED) {
                rollD20();
                return true;
            }
        }
        return super.handleEvent(event);
    }

    /**
     * Generates a random roll between 1 and 20 and starts the animation.
     */
    private void rollD20(){
        Model model = Model.getInstance();
        int nextRollAmount = (Math.abs(this.random.nextInt()) % 20) + 1;
        model.setD20RollAmount(nextRollAmount);
        startAnimation();
    }


    /**
     * Initializes and starts the roll animation.
     */
    private void startAnimation(){
        this.isAnimating = true;
        // start animation
        getDesktop().getAnimator().startAnimation(this);
        // save start time
        this.startTime = Util.platformTimeMillis();
        // set widget initial state
        this.elapsedTime = 0;
    }

    /**
     * Stops the roll animation and resets rotation.
     */
    private void stopAnimation(){
        this.angleOfRotation = 0;
        this.isAnimating = false;
        this.requestRender();
    }

    @Override
    protected void onHidden() {
        getDesktop().getAnimator().stopAnimation(this);
    }
    @Override
    public boolean tick(long platformTimeMillis) {
        updateDieRotationAngle(platformTimeMillis);
        requestRender();
        // return whether to continue or to stop the animation
        if (this.elapsedTime > D20Die.animationDuration){
            stopAnimation();
            return false;
        }
        return true;
    }

    /**
     * Updates the die's rotation angle based on the elapsed animation time.
     *
     * @param platformTimeMillis the current platform time in milliseconds.
     */
    private void updateDieRotationAngle(long platformTimeMillis){
        this.elapsedTime = platformTimeMillis - this.startTime;
        double percentThroughRotation = (double) this.elapsedTime / (double)  D20Die.animationDuration;
        this.angleOfRotation= (int) (percentThroughRotation * 360) * D20Die.animationSpeedConstant;
    }
}
