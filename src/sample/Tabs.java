package sample;

import javafx.scene.control.Tab;

// A template of what our tab object should look like
abstract class Tabs {
    private Tab tab = new Tab("Empty Tab");

    void init() {}

    Tab getTab() { return this.tab; }
}
