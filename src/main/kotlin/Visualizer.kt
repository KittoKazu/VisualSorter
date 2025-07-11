package nl.kittokazu

import javafx.scene.layout.Pane

/*
Interface for making a class with a visualization render for clean code and to showcase inheritance.
 */
interface Visualizer {
    fun init (values: IntArray, chartHeight: Double, container: Pane)
    fun track (i: Int,j: Int) // Makes sure every Visualizer has an init and a track function.
}