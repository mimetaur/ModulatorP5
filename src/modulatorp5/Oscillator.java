package modulatorp5;

import processing.core.*;

public class Oscillator<T extends Oscillator> {

	protected PApplet parent;
	
	protected float minRange, maxRange;
	protected float minRate, maxRate;
	
	protected float rate;
	protected float angle;
	protected float amount;

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
	
	protected void setup(PApplet p_, float minR_, float maxR_, float rate_, float amount_, float minRt_, float maxRt_) {
		parent = p_;
		minRange = minR_;
		maxRange = maxR_;
		rate = rate_;
		amount = amount_;
		minRate = minRt_;
		maxRate = maxRt_;
		angle = DEFAULT_ANGLE;
	}
	
	public float getMinRate() {
		return minRate;
	}
	
	public T setMinRate(float minRate) {
		this.minRate = minRate;
		return (T) this;
	}

	public float getMaxRate() {
		return maxRate;
	}

	public T setMaxRate(float maxRate) {
		this.maxRate = maxRate;
		return (T) this;
	}
	
	public float getMinRange() {
		return minRange;
	}

	public T setMinRange(float minRange) {
		this.minRange = minRange;
		return (T) this;
	}

	public float getMaxRange() {
		return maxRange;
	}

	public T setMaxRange(float maxRange) {
		this.maxRange = maxRange;
		return (T) this;
	}

	public float getRate() {
		return parent.constrain(rate, minRate, maxRate);
	}

	public T setRate(float newRate) {
		rate = newRate;
		return (T) this;
	}

	public float getAmount() {
		return parent.constrain(amount, MIN_AMOUNT, MAX_AMOUNT);
	}

	public T setAmount(float newAmount) {
		amount = newAmount;
		return (T) this;
	}

	public void advance() {
		angle = angle + getRate();
	}

	public float output() {
		float mappedOutput = parent.map(scaledOutput(), MIN_WAVE_OUTPUT, MAX_WAVE_OUTPUT, minRange, maxRange);
		return parent.constrain(mappedOutput, minRange, maxRange);
	}
	
	protected float waveOutput() {
		return parent.sin(angle);
	}

	protected float scaledOutput() {
		return waveOutput() * getAmount();
	}
	
	public void debug() {
		parent.println("Oscillator: " + this + " minRange: " + minRange + " maxRange: " + maxRange + " rate: " + rate + " amount: " + "amount");
	}
}
