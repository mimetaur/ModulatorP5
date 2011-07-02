package modulatorp5;

import processing.core.PApplet;
import controlP5.*;

public class OscillatorControlPanel implements ControlPanel {
	
	private PApplet parent;
	private ControlP5 controlP5;
	private int x, y;
	private int yCursor;
	private String groupName;
	
	private ControlGroup controlGroup;
	
	Oscillator oscillator;
	
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
		yCursor = 0;
		groupName = groupName_;
		
		oscillator = oscillator_;
		
		buildInterface();
		addInitialControls();
	}
	
	public void buildInterface() {
		controlGroup = controlP5.addGroup(groupName, x, y);
	}
	
	public void addInitialControls() {
		addSlider("rateSlider", oscillator.getMinRate(), oscillator.getMaxRate(), oscillator.getRate(), "Rate");
		addSlider("amountSlider", oscillator.MIN_AMOUNT, oscillator.MAX_AMOUNT, oscillator.getAmount(), "Amount");
	}
	
	public Slider addSlider(String name, float minVal, float maxVal, float curVal, String label) {
		Slider slider = controlP5.addSlider(name, minVal, maxVal, curVal, 0, yCursor, SLIDER_WIDTH, SLIDER_HEIGHT);
		advanceCursorForSlider();
		slider.setLabel(label);
		slider.setGroup(controlGroup.name());
		slider.plugTo(this);
		return slider;
	}
	
	public void advanceCursorForSlider() {
		yCursor = yCursor + (SLIDER_HEIGHT + SLIDER_VERTICAL_SPACER);
	}
	
	public void rateSlider(float newRate) {
		oscillator.setRate(newRate);
	}
	
	public void amountSlider(float newAmount) {
		oscillator.setAmount(newAmount);
	}
	
}
