package modulatorp5;

import processing.core.PApplet;
import controlP5.*;

public class AudioAnalyzerControlPanel implements ControlPanel, ControlListener {

	private PApplet parent;
	private ControlP5 controlP5;
	private int x, y;
	private int yCursor;
	private String groupName;
	
	private ControlGroup controlGroup;
	
	private static final int MODE_BUTTONS_ID = 1;
	private static final int EASING_SLIDER_ID = 2;
	private static final int SCALE_SLIDER_ID = 3;
	
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
		addModeButtons("modeButtons", "Mode", MODE_BUTTONS_ID);
		addSlider("easingSlider", AudioAnalyzer.MIN_EASING, AudioAnalyzer.MAX_EASING, audioAnalyzer.getEasing(), "Easing", EASING_SLIDER_ID);
		addSlider("scaleSlider", AudioAnalyzer.MIN_SCALE, AudioAnalyzer.MAX_SCALE, audioAnalyzer.getScale(), "Amount", SCALE_SLIDER_ID);
	}
	
	public Radio addModeButtons(String name, String label, int id) {
		String uniqueName = name + this.hashCode();
		Radio radio = controlP5.addRadio(uniqueName, 0, yCursor);
		radio.add("Sub", AudioAnalyzer.MODE_SUB);
		radio.add("Bass", AudioAnalyzer.MODE_BASS);
		radio.add("Mids", AudioAnalyzer.MODE_MIDS);
		radio.add("Highs", AudioAnalyzer.MODE_HIGHS);
		radio.setDefaultValue(AudioAnalyzer.MODE_DEFAULT);
		radio.setLabel(label);
		radio.setGroup(controlGroup.name());
		radio.setId(id);
		radio.addListener(this);
		advanceCursor(radio);
		return radio;
	}
	
	public Slider addSlider(String name, float minVal, float maxVal, float curVal, String label, int id) {
		String uniqueName = name + this.hashCode();
		Slider slider = controlP5.addSlider(uniqueName, minVal, maxVal, curVal, 0, yCursor, SLIDER_WIDTH, SLIDER_HEIGHT);
		slider.setLabel(label);
		slider.setGroup(controlGroup.name());
		slider.setId(id);
		slider.addListener(this);
		advanceCursor(slider);
		return slider;
	}
	
	public void advanceCursor(Controller controller) {
		yCursor = yCursor + (controller.getHeight() + VERTICAL_SPACER);
	}
	
	public void controlEvent(ControlEvent event) {
		int controllerId = event.controller().id();
		float controllerValue = event.controller().value();

		switch (controllerId) {
		case MODE_BUTTONS_ID:
			parent.println("Setting mode:" + controllerValue);
			audioAnalyzer.setMode( (int) controllerValue );
			break;
		case EASING_SLIDER_ID:
			audioAnalyzer.setEasing(controllerValue);
			break;
		case SCALE_SLIDER_ID:
			audioAnalyzer.setScale(controllerValue);
			break;
		}
	}
}
