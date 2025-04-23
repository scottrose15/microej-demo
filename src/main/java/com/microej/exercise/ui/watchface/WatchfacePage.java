/*
 * Java
 *
 * Copyright 2019-2024  MicroEJ Corp. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found with this software.
 */
package com.microej.exercise.ui.watchface;

import com.microej.exercise.ui.style.ClassIdentifiers;
import com.microej.exercise.ui.style.Fonts;
import com.microej.exercise.ui.style.Images;
import com.microej.exercise.ui.style.StyleSheetConfigurator;
import com.microej.exercise.ui.util.Model;
import com.microej.exercise.ui.util.Page;
import com.microej.exercise.ui.util.TimeHelper;
import com.microej.exercise.ui.watchface.stylesheets.BatteryWidgetStyleSheetConfigurator;
import com.microej.exercise.ui.watchface.stylesheets.DigitalClockWidgetStyleSheetConfigurator;
import com.microej.exercise.ui.watchface.stylesheets.DistanceWidgetStyleSheetConfigurator;
import com.microej.exercise.ui.watchface.stylesheets.HeartRateWidgetStyleSheetConfigurator;
import com.microej.exercise.ui.watchface.stylesheets.StepWidgetStyleSheetConfigurator;
import com.microej.exercise.ui.watchface.stylesheets.WatchFaceStyleSheetConfigurator;
import com.microej.exercise.ui.watchface.widget.BatteryLevel;
import com.microej.exercise.ui.watchface.widget.DigitalClock;
import com.microej.exercise.ui.watchface.widget.IconLabel;
import com.microej.exercise.ui.watchface.widget.WatchHands;

import java.util.ArrayList;

import ej.bon.Timer;
import ej.microui.display.Colors;
import ej.microui.display.Font;
import ej.mwt.Widget;
import ej.mwt.style.EditableStyle;
import ej.mwt.style.background.RectangularBackground;
import ej.mwt.style.outline.FlexibleOutline;
import ej.mwt.stylesheet.cascading.CascadingStylesheet;
import ej.mwt.stylesheet.selector.ClassSelector;
import ej.mwt.stylesheet.selector.TypeSelector;
import ej.widget.basic.Label;
import ej.widget.container.Dock;
import ej.widget.container.LayoutOrientation;
import ej.widget.container.List;
import ej.widget.container.SimpleDock;

/**
 * A page that represents a watchface.
 */
public class WatchfacePage extends Page {

	private static final float TEN = 10;

	private IconLabel heartRate;

	private IconLabel steps;

	private IconLabel distance;

	private BatteryLevel battery;

	private DigitalClock clock;

	@Override
	public Widget getWidget() {
		Widget digital = createDigital();
		digital.addClassSelector(ClassIdentifiers.DIGITAL_WATCHFACE);

		return digital;
	}

	/**
	 * Creates the widget that represents the digital watchface.
	 *
	 * <p>
	 * This digital watchface is composed of 5 widgets:
	 * <ul>
	 * <li>the current heart rate value,</li>
	 * <li>the daily step count,</li>
	 * <li>the daily distance,</li>
	 * <li>a digital clock,</li>
	 * <li>a battery level indicator.</li>
	 * </ul>
	 */
	private Widget createDigital() {
		initializeWidgets();
		return createWatchFaceLayout();
	}

	/**
	 * Creates the hierarchical layout of the watch face UI, composed of widgets
	 * such as battery level, step count, distance, clock, and heart rate.
	 *
	 * @return A vertical {@code Widget} container representing the complete watch face layout.
	 */
	private Widget createWatchFaceLayout(){
		List WatchFaceList = new List(LayoutOrientation.VERTICAL);
		WatchFaceList.addChild(this.battery);

		List StepsAndDistanceList = new List(LayoutOrientation.HORIZONTAL);

		StepsAndDistanceList.addChild(this.steps);
		StepsAndDistanceList.addChild(this.distance);

		WatchFaceList.addChild(StepsAndDistanceList);

		WatchFaceList.addChild(this.clock);

		WatchFaceList.addChild(heartRate);
		return WatchFaceList;
	}

	/**
	 * Initializes all the watch face widgets using data retrieved from the {@code Model} instance.
	 */
	private void initializeWidgets(){
		Model model = Model.getInstance();
		initializeHeartRateWidget(String.valueOf(model.getHeartRate()));
		initializeStepsWidget(String.valueOf(model.getStepCount()));
		initializeDistanceWidget(formatDistance(model.getDistance()));
		initializeClockWidget(TimeHelper.getTimer());
		initializeBatteryWidget(model.getBatteryLevel());
	}

	/**
	 * Initializes the heart rate widget using the given heart rate string and applies styling.
	 *
	 * @param heartRateString The heart rate value to display on the widget.
	 */
	private void initializeHeartRateWidget(String heartRateString){
		this.heartRate = new IconLabel(Images.HEART_ICON, heartRateString);
		this.heartRate.addClassSelector(ClassIdentifiers.HEART_RATE_VALUE);
	}

	/**
	 * Initializes the steps widget with the given step count string and applies styling.
	 *
	 * @param stepsString The number of steps to display.
	 */
	private void initializeStepsWidget(String stepsString){
		this.steps = new IconLabel(Images.SHOE_ICON, stepsString);
		this.steps.addClassSelector(ClassIdentifiers.STEP_VALUE);
	}

	/**
	 * Initializes the distance widget using the given distance string and applies styling.
	 *
	 * @param distanceString The formatted distance value to display.
	 */
	private void initializeDistanceWidget(String distanceString){
		this.distance = new IconLabel(Images.LOCALIZATION_ICON, distanceString);
		this.distance.addClassSelector(ClassIdentifiers.DISTANCE_VALUE);
	}

	/**
	 * Initializes the digital clock widget using the provided timer instance.
	 *
	 * @param timer A {@code Timer} instance that provides time updates.
	 */
	private void initializeClockWidget(Timer timer){
		this.clock = new DigitalClock(timer);
	}

	/**
	 * Initializes the battery level widget with the provided battery percentage.
	 *
	 * @param batteryLevel The battery level as an integer percentage (0-100).
	 */
	private void initializeBatteryWidget(int batteryLevel){
		this.battery = new BatteryLevel(batteryLevel);
	}

	/**
	 * Creates the widget that represents the analog watchface.
	 *
	 * <p>
	 * This analog watchface is composed of one widget that draws the watch hands.
	 */
	private Widget createAnalog() {
		WatchHands watchHands = new WatchHands(TimeHelper.getTimer());
		watchHands.addClassSelector(ClassIdentifiers.ANALOG_WATCHFACE);
		return watchHands;
	}

	@Override
	public void populateStylesheet(CascadingStylesheet stylesheet) {
		// TODO Fix project configuration to use Java version 17 so that streams can be used
		java.util.List<StyleSheetConfigurator> StyleSheetConfigurators = new ArrayList<>();
		StyleSheetConfigurators.add(new WatchFaceStyleSheetConfigurator());
		StyleSheetConfigurators.add(new BatteryWidgetStyleSheetConfigurator());
		StyleSheetConfigurators.add(new DigitalClockWidgetStyleSheetConfigurator());
		StyleSheetConfigurators.add(new DistanceWidgetStyleSheetConfigurator());
		StyleSheetConfigurators.add(new HeartRateWidgetStyleSheetConfigurator());
		StyleSheetConfigurators.add(new StepWidgetStyleSheetConfigurator());
		for(StyleSheetConfigurator styleSheetConfigurators: StyleSheetConfigurators){
			styleSheetConfigurators.configureWidgetStyleSheet(stylesheet);
		}
	}

	/**
	 * Notifies that the business model changed and that the GUI might require an update of its content.
	 *
	 * <p>
	 * This method is called whenever the observed object (here <code>com.microej.exercise.ui.util.Model</code>) is
	 * changed.
	 */
	@Override
	public void update() {
		super.update();

		// retrieves the business model
		Model model = Model.getInstance();

		// updates the widgets
		this.heartRate.setText(String.valueOf(model.getHeartRate()));
		this.heartRate.requestRender();

		this.steps.setText(String.valueOf(model.getStepCount()));
		this.steps.requestRender();

		this.distance.setText(formatDistance(model.getDistance()));
		this.distance.requestRender();

		this.battery.setLevel(model.getBatteryLevel());
		this.battery.requestRender();
	}

	private static String formatDistance(float value) {
		int intPart = (int) value;
		int decimal = (int) ((value - intPart) * TEN);
		return new StringBuilder().append(intPart).append('.').append(decimal).append(" km").toString(); //$NON-NLS-1$
	}
}
