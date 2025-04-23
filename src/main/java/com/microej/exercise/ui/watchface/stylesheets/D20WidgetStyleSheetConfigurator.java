package com.microej.exercise.ui.watchface.stylesheets;

import com.microej.exercise.ui.style.ClassIdentifiers;
import com.microej.exercise.ui.style.CustomColors;
import com.microej.exercise.ui.style.Fonts;
import com.microej.exercise.ui.style.StyleSheetConfigurator;
import com.microej.exercise.ui.watchface.widget.IconLabel;

import ej.microui.display.Colors;
import ej.mwt.style.EditableStyle;
import ej.mwt.stylesheet.cascading.CascadingStylesheet;
import ej.mwt.stylesheet.selector.ClassSelector;

public class D20WidgetStyleSheetConfigurator implements StyleSheetConfigurator {
    @Override
    public void configureWidgetStyleSheet(CascadingStylesheet stylesheet) {
        // defines the style of the heart rate value
        EditableStyle style = stylesheet.getSelectorStyle(new ClassSelector(ClassIdentifiers.D20_VALUE));
        style.setColor(Colors.CYAN);
        style.setFont(Fonts.getSmallFont());
        // sets the color to use for the icon with a custom extra field
        style.setExtraInt(IconLabel.EXTRA_FIELD_ICON_COLOR, Colors.WHITE);
    }
}
