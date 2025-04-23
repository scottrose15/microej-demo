package com.microej.exercise.ui.watchface.stylesheets;

import com.microej.exercise.ui.style.ClassIdentifiers;
import com.microej.exercise.ui.style.CustomColors;

import com.microej.exercise.ui.style.Images;
import com.microej.exercise.ui.style.StyleSheetConfigurator;

import ej.microui.display.Image;
import ej.mwt.style.EditableStyle;
import ej.mwt.style.background.ImageBackground;
import ej.mwt.style.background.RectangularBackground;
import ej.mwt.style.outline.FlexibleOutline;
import ej.mwt.stylesheet.cascading.CascadingStylesheet;
import ej.mwt.stylesheet.selector.ClassSelector;

public class WatchFaceStyleSheetConfigurator implements StyleSheetConfigurator {
    @Override
    public void configureWidgetStyleSheet(CascadingStylesheet stylesheet) {
        // defines the style of the root container of the digital watchface
        EditableStyle style = stylesheet.getSelectorStyle(new ClassSelector(ClassIdentifiers.DIGITAL_WATCHFACE));
        ImageBackground imageBackground = new ImageBackground(Image.getImage(Images.SWOOSH_WATCHFACE_BACKGROUND));

        style.setBackground(imageBackground);
        style.setPadding(new FlexibleOutline(10, 25, 10, 25));
    }
}