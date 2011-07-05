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
		yCursor = VERTICAL_SPACER;
		groupName = groupName_;
		
		audioAnalyzer = AudioAnalyzer_;
		buildInterface();
		addInitialControls();
	}
	
	public int getHeight() {
		return controlGroup.getHeight();
	}
	
	public void buildInterface() {
		controlGroup = controlP5.addGroup(groupName, x, y);
	}
	
	public void addInitialControls() {
		addModeButtons("modeButtons", "Mode");
		addSlider("easingSlider", AudioAnalyzer.MIN_EASING, AudioAnalyzer.MAX_EASING, audioAnalyzer.getEasing(), "Easing");
		addSlider("scaleSlider", AudioAnalyzer.MIN_SCALE, AudioAnalyzer.MAX_SCALE, audioAnalyzer.getScale(), "Amount");
	}
	
	public Radio addModeButtons(String name, String label) {
		Radio radio = controlP5.addRadio(name, 0, yCursor);
		radio.add("Sub", AudioAnalyzer.MODE_SUB);
		radio.add("Bass", AudioAnalyzer.MODE_BASS);
		radio.add("Mids", AudioAnalyzer.MODE_MIDS);
		radio.add("Highs", AudioAnalyzer.MODE_HIGHS);
		radio.plugTo(this);
		radio.setDefaultValue(AudioAnalyzer.MODE_DEFAULT);
		radio.setLabel(label);
		radio.setGroup(controlGroup.name());
		advanceCursor(radio);
		return radio;
	}
	
	public Slider addSlider(String name, float minVal, float maxVal, float curVal, String label) {
		Slider slider = controlP5.addSlider(name, minVal, maxVal, curVal, 0, yCursor, SLIDER_WIDTH, SLIDER_HEIGHT);
		slider.setLabel(label);
		slider.setGroup(controlGroup.name());
		slider.plugTo(this);
		advanceCursor(slider);
		return slider;
	}
	
	public void advanceCursor(Controller controller) {
		yCursor = yCursor + (controller.getHeight() + VERTICAL_SPACER);
	}
	
	public void easingSlider(float newValue) {
		audioAnalyzer.setEasing(newValue);
	}
	
	public void scaleSlider(float newValue) {
		audioAnalyzer.setScale(newValue);
	}
	
	public void modeButtons(int newValue) {
		audioAnalyzer.setMode(newValue);
		parent.println(audioAnalyzer.getMode());
	}
}
