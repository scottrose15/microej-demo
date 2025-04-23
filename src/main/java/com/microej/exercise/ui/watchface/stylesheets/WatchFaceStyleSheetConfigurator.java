package com.microej.exercise.ui.watchface.stylesheets;

import com.microej.exercise.ui.style.ClassIdentifiers;
import com.microej.exercise.ui.style.Fonts;
import com.microej.exercise.ui.style.StyleSheetConfigurator;
import com.microej.exercise.ui.watchface.widget.BatteryLevel;
import com.microej.exercise.ui.watchface.widget.DigitalClock;
import com.microej.exercise.ui.watchface.widget.IconLabel;

import ej.microui.display.Colors;
import ej.microui.display.Font;
import ej.mwt.style.EditableStyle;
import ej.mwt.style.background.RectangularBackground;
import ej.mwt.style.outline.FlexibleOutline;
import ej.mwt.stylesheet.cascading.CascadingStylesheet;
import ej.mwt.stylesheet.selector.ClassSelector;
import ej.mwt.stylesheet.selector.TypeSelector;

public class WatchFaceStyleSheetConfigurator implements StyleSheetConfigurator {
    @Override
    public void configureWidgetStyleSheet(CascadingStylesheet stylesheet) {
        // defines the style of the root container of the digital watchface
        EditableStyle style = stylesheet.getSelectorStyle(new ClassSelector(ClassIdentifiers.DIGITAL_WATCHFACE));
        style.setBackground(new RectangularBackground(Colors.BLACK));
        style.setPadding(new FlexibleOutline(10, 25, 10, 25));
    }
}