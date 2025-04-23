package com.microej.exercise.ui.watchface.stylesheets;

import com.microej.exercise.ui.style.Fonts;
import com.microej.exercise.ui.style.StyleSheetConfigurator;
import com.microej.exercise.ui.watchface.widget.DigitalClock;

import ej.microui.display.Colors;
import ej.mwt.style.EditableStyle;
import ej.mwt.stylesheet.cascading.CascadingStylesheet;
import ej.mwt.stylesheet.selector.TypeSelector;

public class DigitalClockWidgetStyleSheetConfigurator implements StyleSheetConfigurator {
    @Override
    public void configureWidgetStyleSheet(CascadingStylesheet stylesheet) {
        // defines the style of the digital clock
        EditableStyle style = stylesheet.getSelectorStyle(new TypeSelector(DigitalClock.class));
        style.setColor(Colors.WHITE);
        style.setFont(Fonts.getLargeFont());
        // sets the font to use for the seconds with a custom extra field
        style.setExtraObject(DigitalClock.EXTRA_FIELD_SECONDS_FONT, Fonts.getSmallFont());
    }
}
