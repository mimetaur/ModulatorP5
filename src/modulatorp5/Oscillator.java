package modulatorp5;

import processing.core.*;

public class Oscillator {

	private PApplet parent;
	
	private float minRange, maxRange;
	private float minRate, maxRate;
	
	private float rate;
	private float angle;
	private float amount;

	public static final float MIN_AMOUNT = 0.0f;
	public static final float MAX_AMOUNT = 1.0f;  
		    
	public static final float DEFAULT_MIN_RATE = 0.0f;
	public static final float DEFAULT_MAX_RATE = 1.0f;
	  
	public static final float DEFAULT_RATE = 0.0f;
	public static final float DEFAULT_ANGLE = 0.0f;
	
	public static final float MIN_WAVE_OUTPUT = -1.0f;
	public static final float MAX_WAVE_OUTPUT = 1.0f;

	public Oscillator(PApplet p_) {
		setup(p_, MIN_WAVE_OUTPUT, MAX_WAVE_OUTPUT, DEFAULT_RATE, MIN_AMOUNT, DEFAULT_MIN_RATE, DEFAULT_MAX_RATE);
	}
	
	// Min Range, Max Range
	Oscillator(PApplet p_,float minR_, float maxR_) {
		setup(p_, minR_, maxR_, DEFAULT_RATE, MIN_AMOUNT, DEFAULT_MIN_RATE, DEFAULT_MAX_RATE);
	}

	// Min Range, Max Range, Starting Rate, Min Rate, Max Rate
	Oscillator(PApplet p_, float minR_, float maxR_, float rate_, float minRt_, float maxRt_) {
		setup(p_, minR_, maxR_, rate_, MIN_AMOUNT, minRt_, maxRt_);
	}

	// Min Range, Max Range, Starting Rate, Min Rate, Max Rate, Starting Amount
	Oscillator(PApplet p_, float minR_, float maxR_, float rate_, float minRt_, float maxRt_, float amount_) {
		setup(p_, minR_, maxR_, rate_, amount_, minRt_, maxRt_);
	}
	
	private void setup(PApplet p_, float minR_, float maxR_, float rate_, float amount_, float minRt_, float maxRt_) {
		parent = p_;
		minRange = minR_;
		maxRange = maxR_;
		rate = rate_;
		amount = amount_;
		minRate = minRt_;
		maxRate = maxRt_;
		angle = DEFAULT_ANGLE;
	}
	
	public void debug() {
		parent.println("Oscillator: " + this + " minRange: " + minRange + " maxRange: " + maxRange + " rate: " + rate + " amount: " + "amount");
	}
	
	public float getMinRate() {
		return minRate;
	}

	public Oscillator setMinRate(float minRate) {
		this.minRate = minRate;
		return this;
	}

	public float getMaxRate() {
		return maxRate;
	}

	public Oscillator setMaxRate(float maxRate) {
		this.maxRate = maxRate;
		return this;
	}
	
	public float getMinRange() {
		return minRange;
	}

	public Oscillator setMinRange(float minRange) {
		this.minRange = minRange;
		return this;
	}

	public float getMaxRange() {
		return maxRange;
	}

	public Oscillator setMaxRange(float maxRange) {
		this.maxRange = maxRange;
		return this;
	}

	public float getRate() {
		return parent.constrain(rate, minRate, maxRate);
	}

	public Oscillator setRate(float newRate) {
		rate = newRate;
		return this;
	}

	public float getAmount() {
		return parent.constrain(amount, MIN_AMOUNT, MAX_AMOUNT);
	}

	public Oscillator setAmount(float newAmount) {
		amount = newAmount;
		return this;
	}

	public void oscillate() {
		angle = angle + getRate();
	}

	public float output() {
		float mappedOutput = parent.map(scaledOutput(), MIN_WAVE_OUTPUT, MAX_WAVE_OUTPUT, minRange, maxRange);
		return parent.constrain(mappedOutput, minRange, maxRange);
	}

	private float waveOutput() {
		return parent.sin(angle);
	}

	private float scaledOutput() {
		return waveOutput() * getAmount();
	}
}
