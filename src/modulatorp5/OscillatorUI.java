package modulatorp5;

import processing.core.PApplet;
import controlP5.*;

public class OscillatorUI {
	protected PApplet parent;
	
	protected ControlP5 controlP5;
	protected ControlGroup controlGroup;
	protected Oscillator osc;

	public final int SLIDER_WIDTH = 160;
	public final int SLIDER_HEIGHT = 20;
	public final int VERTICAL_DISTANCE = new Float(SLIDER_HEIGHT * 0.66).intValue();

	OscillatorUI(PApplet parent_, ControlP5 controlP5_, Oscillator osc_) {
		setup(parent_, controlP5_, osc_, 0, 0, "Osc");
	}

	OscillatorUI(PApplet parent_, ControlP5 controlP5_, Oscillator osc_, int x_, int y_) {
		setup(parent_, controlP5_, osc_, x_, y_, "Osc");  
	}

	OscillatorUI(PApplet parent_, ControlP5 controlP5_, Oscillator osc_, int x_, int y_, String groupName_) {
		setup(parent_, controlP5_, osc_, x_, y_, groupName_);  
	}

	public int getHeight() {
		return controlGroup.getHeight();
	}

	protected void setup(PApplet p_, ControlP5 c_, Oscillator o_, int x_, int y_, String g_) {
		parent = p_;
		controlP5 = c_;
		osc = o_;
		setupUI(x_, y_, g_);
	}

	protected void setupUI(int x, int y, String groupName) {
		controlGroup = controlP5.addGroup(groupName, x, y);

		setupRateSlider();
		setupAmountSlider();
	}

	protected void setupRateSlider() {
		Slider s1 = controlP5.addSlider("newRate", osc.getMinRate(), osc.getMaxRate(), osc.getRate(), 0, 0, SLIDER_WIDTH, SLIDER_HEIGHT);
		s1.setLabel(controlGroup.name() + " Rate");
		s1.plugTo(this);
		s1.setGroup(controlGroup.name());
	}

	protected void setupAmountSlider() {
		Slider s2 = controlP5.addSlider("newAmount", Oscillator.MIN_AMOUNT, Oscillator.MAX_AMOUNT, osc.getAmount(), 0, SLIDER_HEIGHT + VERTICAL_DISTANCE, SLIDER_WIDTH, SLIDER_HEIGHT);
		s2.setLabel(controlGroup.name() + " Amount");
		s2.plugTo(this);
		s2.setGroup(controlGroup.name());
	}

	public void newRate(float rate_) {
		osc.setRate(rate_);
	}

	public void newAmount(float amount_) {
		osc.setAmount(amount_);
	} 
}
