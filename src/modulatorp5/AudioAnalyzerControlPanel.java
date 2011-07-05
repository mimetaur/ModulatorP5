package modulatorp5;

import processing.core.PApplet;
import controlP5.*;

public class AudioAnalyzerControlPanel implements ControlPanel {

	private PApplet parent;
	private ControlP5 controlP5;
	private int x, y;
	private int yCursor;
	private String groupName;
	
	private ControlGroup controlGroup;
	
	AudioAnalyzer audioAnalyzer;
	
	AudioAnalyzerControlPanel(PApplet parent_, ControlP5 controlP5_, AudioAnalyzer AudioAnalyzer_) {
		this(parent_, controlP5_, 0, 0, "Entity", AudioAnalyzer_);
	}
	
	AudioAnalyzerControlPanel(PApplet parent_, ControlP5 controlP5_, int x_, int y_, AudioAnalyzer AudioAnalyzer_) {
		this(parent_, controlP5_, x_, y_, "Entity", AudioAnalyzer_);
	}
	
	AudioAnalyzerControlPanel(PApplet parent_, ControlP5 controlP5_, int x_, int y_, String groupName_, AudioAnalyzer AudioAnalyzer_) {
		parent = parent_;
		controlP5 = controlP5_;
		x = x_;
		y = y_;
		yCursor = 0;
		groupName = groupName_;
		
		audioAnalyzer = AudioAnalyzer_;
		
		buildInterface();
		addInitialControls();
	}
	
	public void buildInterface() {
		controlGroup = controlP5.addGroup(groupName, x, y);
	}
	
	public void addInitialControls() {
		addSlider("bandSlider", AudioAnalyzer.MIN_AVG_BAND, audioAnalyzer.getMaxAvgBand(), audioAnalyzer.getBand(), "Freq Band");
		addSlider("easingSlider", AudioAnalyzer.MIN_EASING, AudioAnalyzer.MAX_EASING, audioAnalyzer.getEasing(), "Easing");
		addSlider("sensitivitySlider", 0.0f, 100.0f, audioAnalyzer.getSensitivity(), "Sensitivity");
	}
	
	public Slider addSlider(String name, float minVal, float maxVal, float curVal, String label) {
		Slider slider = controlP5.addSlider(name, minVal, maxVal, curVal, 0, yCursor, SLIDER_WIDTH, SLIDER_HEIGHT);
		advanceCursorForSlider();
		slider.setLabel(label);
		slider.setGroup(controlGroup.name());
		slider.plugTo(this);
		return slider;
	}
	
	public void bandSlider(int newValue) {
		audioAnalyzer.setBand(newValue);
	}
	
	public void advanceCursorForSlider() {
		yCursor = yCursor + (SLIDER_HEIGHT + SLIDER_VERTICAL_SPACER);
	}
	
	public void easingSlider(float newValue) {
		audioAnalyzer.setEasing(newValue);
	}
	
	public void sensitivitySlider(float newValue) {
		audioAnalyzer.setSensitivity(newValue);
	}
}
