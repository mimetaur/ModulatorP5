package modulatorp5;

import controlP5.Slider;

public interface ControlPanel {
	
	public static final int SLIDER_WIDTH = 200;
	public static final int SLIDER_HEIGHT = 20;
	public static final int SLIDER_VERTICAL_SPACER = new Float(SLIDER_HEIGHT * 0.66).intValue();
	
	public void buildInterface();
	
	public void addInitialControls();
	
	public Slider addSlider(String name, float minVal, float maxVal, float curVal, String label);
	
	public void advanceCursorForSlider();
	
}
