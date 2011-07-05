package modulatorp5;

import controlP5.Slider;

public interface ControlPanel {
	
	public static final int SLIDER_WIDTH = 200;
	public static final int SLIDER_HEIGHT = 20;
	public static final int VERTICAL_SPACER = 12;
	
	public void buildInterface();
	public void addInitialControls();
}
