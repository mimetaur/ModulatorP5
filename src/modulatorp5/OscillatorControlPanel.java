package modulatorp5;

import processing.core.PApplet;
import controlP5.*;

public class OscillatorControlPanel implements ControlPanel, ControlListener {
	
	private PApplet parent;
	private ControlP5 controlP5;
	private int x, y;
	private int yCursor;
	private String groupName;
	
	private ControlGroup controlGroup;
	
	Oscillator oscillator;
	
	public static final int RATE_SLIDER_ID = 1;
	public static final int AMOUNT_SLIDER_ID = 2;

	OscillatorControlPanel(PApplet parent_, ControlP5 controlP5_, Oscillator oscillator_) {
		this(parent_, controlP5_, 0, 0, "Entity", oscillator_);
	}
	
	OscillatorControlPanel(PApplet parent_, ControlP5 controlP5_, int x_, int y_, Oscillator oscillator_) {
		this(parent_, controlP5_, x_, y_, "Entity", oscillator_);
	}
	
	OscillatorControlPanel(PApplet parent_, ControlP5 controlP5_, int x_, int y_, String groupName_, Oscillator oscillator_) {
		parent = parent_;
		controlP5 = controlP5_;
		x = x_;
		y = y_;
		yCursor = VERTICAL_SPACER;
		groupName = groupName_;
		
		oscillator = oscillator_;
		
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
		addSlider("rateSlider", oscillator.getMinRate(), oscillator.getMaxRate(), oscillator.getRate(), "Rate", RATE_SLIDER_ID);
		addSlider("amountSlider", oscillator.MIN_AMOUNT, oscillator.MAX_AMOUNT, oscillator.getAmount(), "Amount", AMOUNT_SLIDER_ID);
	}
	
	public Slider addSlider(String name, float minVal, float maxVal, float curVal, String label, int id) {
		String uniqueName = name + this.hashCode();
		Slider slider = controlP5.addSlider(uniqueName, minVal, maxVal, curVal, 0, yCursor, SLIDER_WIDTH, SLIDER_HEIGHT);
		slider.setLabel(label);
		slider.setGroup(controlGroup.name());
		slider.setId(id);
		slider.addListener(this);
		advanceCursorForSlider();
		return slider;
	}
	
	public void advanceCursorForSlider() {
		yCursor = yCursor + (SLIDER_HEIGHT + VERTICAL_SPACER);
	}
	
	public void controlEvent(ControlEvent event) {
		int controllerId = event.controller().id();
		float controllerValue = event.controller().value();

		switch (controllerId) {
		case RATE_SLIDER_ID:
			oscillator.setRate(controllerValue);
			break;
		case AMOUNT_SLIDER_ID:
			oscillator.setAmount(controllerValue);
			break;
		}
	}
	
}
