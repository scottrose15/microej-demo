.. image:: https://shields.microej.com/endpoint?url=https://repository.microej.com/packages/badges/sdk_6.0.json
   :alt: sdk_6.0 badge
   :align: left

.. image:: https://shields.microej.com/endpoint?url=https://repository.microej.com/packages/badges/arch_8.0.json
   :alt: arch_7.18 badge
   :align: left

.. image:: https://shields.microej.com/endpoint?url=https://repository.microej.com/packages/badges/gui_3.json
   :alt: gui_3 badge
   :align: left

Overview
========

This project is a fork of the the Micro EJ coding challenges to learn the basics of UI development.
The base code is from the step 1 branch of the tutorial series. More information on the structure of the starter code can be found at:

https://github.com/MicroEJ/Tutorial-UI

In this fork of the tutorial code, I implemented the following changes

1. Added 5 of their premade widgets to the watch face.
2. Added a background image to the watch face.
3. Created a 'D20' Widget that, when you tap on it, simulates rolling a twenty sided die and plays a dice rolling animation.
4. Refactored some of their code that applied styles to their widgets.

Here is a gif of the UI updates

.. image:: https://github.com/scottrose15/microej-demo/blob/step/1/MicroEJ%20Watch.gif
   :alt: sdk_6.0 badge
   :align: left

Note: This version of the training is compatible with `MICROEJ SDK 6 <https://docs.microej.com/en/latest/SDK6UserGuide/index.html>`_ only.



Run on the Simulator
--------------------

First, make sure to install MICROEJ SDK 6 as described in the `online documentation <https://docs.microej.com/en/latest/SDK6UserGuide/index.html>`_.

1. Open the Gradle pane
2. Under ``wearable`` > ``Tasks`` > ``microej``, double-click on ``runOnSimulator``

Note:
You can also do the same in the CLI, use the command ``.\gradlew.bat runOnSimulator`` (use ``gradlew`` on Linux/macOS)



Requirements
============

This project requires the following Foundation Libraries:

    EDC-1.3, BON-1.4, MICROUI-3.2, DRAWING-1.0

Dependencies
============

_All dependencies are retrieved transitively by Gradle.

Source
======

N/A.

Restrictions
============

None.



.. ReStructuredText
.. Copyright 2022-2024 MicroEJ Corp. All rights reserved.
.. Use of this source code is governed by a BSD-style license that can be found with this software.
