package com.microej.exercise.ui.watchface.stylesheets;

import com.microej.exercise.ui.style.StyleSheetConfigurator;
import com.microej.exercise.ui.watchface.widget.BatteryLevel;

import ej.microui.display.Colors;
import ej.mwt.style.EditableStyle;
import ej.mwt.stylesheet.cascading.CascadingStylesheet;
import ej.mwt.stylesheet.selector.TypeSelector;

public class BatteryWidgetStyleSheetConfigurator implements StyleSheetConfigurator {
    @Override
    public void configureWidgetStyleSheet(CascadingStylesheet stylesheet) {
        // defines the style of the battery level indicator
        EditableStyle style = stylesheet.getSelectorStyle(new TypeSelector(BatteryLevel.class));
        style.setColor(Colors.WHITE);
    }
}
