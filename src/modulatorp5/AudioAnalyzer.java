package modulatorp5;

import ddf.minim.*;
import ddf.minim.analysis.*;
import processing.core.*;

public class AudioAnalyzer implements Modulator {
	
	public static final int INPUT_SAMPLE_RATE = 22050;
	
	public static final float MIN_FREQ_BAND = 20.0f;
	public static final float MAX_FREQ_BAND = 5000.0f;
	
	public static final float MIN_EASING = 0.01f;
	public static final float MAX_EASING = 0.2f;
	public static final float DEFAULT_EASING = 0.05f;
	
	public static final float DEFAULT_OUTPUT = 0.0f;
	public static final float DEFAULT_MIN_RANGE = 0.0f;
	public static final float DEFAULT_MAX_RANGE = 1.0f;
	public static final float DEFAULT_LOW_BAND = 500.0f;
	public static final float DEFAULT_HIGH_BAND = 600.0f;
	
	protected PApplet parent;
	protected Minim minim;
	protected AudioInput in;
	protected FFT fft;
	
	protected float easing;
	protected float minRange, maxRange;
	protected float lowBand, highBand;
	protected float output, nextOutput;
	protected float sensitivity;

	public AudioAnalyzer(PApplet parent_, Minim minim_, AudioInput in_) {
		parent = parent_;
		minim = minim_;
		in = in_;
		
		fft = new FFT(in.bufferSize(), INPUT_SAMPLE_RATE);
		
		output = DEFAULT_OUTPUT;
		nextOutput = DEFAULT_OUTPUT;
		easing = DEFAULT_EASING;
		lowBand = DEFAULT_LOW_BAND;
		highBand = DEFAULT_HIGH_BAND;
		minRange = DEFAULT_MIN_RANGE;
		maxRange = DEFAULT_MAX_RANGE;
		sensitivity = 1.0f;
	}
	
	
	
	public float getSensitivity() {
		return sensitivity;
	}

	public AudioAnalyzer setSensitivity(float sensitivity) {
		this.sensitivity = sensitivity;
		return this;
	}

	public float getEasing() {
		return easing;
	}

	public AudioAnalyzer setEasing(float easing) {
		this.easing = parent.constrain(easing, MIN_EASING, MAX_EASING);
		return this;
	}

	public float getMinRange() {
		return minRange;
	}

	public AudioAnalyzer setMinRange(float minRange) {
		this.minRange = minRange;
		return this;
	}

	public float getMaxRange() {
		return maxRange;
	}

	public AudioAnalyzer setMaxRange(float maxRange) {
		this.maxRange = maxRange;
		return this;
	}

	public float getLowBand() {
		return lowBand;
	}

	public AudioAnalyzer setLowBand(float lowBand) {
		this.lowBand = parent.constrain(lowBand, MIN_FREQ_BAND, MAX_FREQ_BAND);
		return this;
	}

	public float getHighBand() {
		return highBand;
	}

	public AudioAnalyzer setHighBand(float highBand) {
		this.highBand = parent.constrain(highBand, MIN_FREQ_BAND, MAX_FREQ_BAND);
		return this;
	}

	public void advance() {
		fft.forward(in.mix);
		analyze();
		parent.println("Output: " + output);
	}
	
	protected void analyze() {
		float raw = fft.calcAvg(lowBand, highBand) * sensitivity;
		parent.println("Raw: " + raw);
		parent.println("Min: " + minRange + " Max: " + maxRange);
		nextOutput = parent.constrain(raw, minRange, maxRange);
		parent.println("nextOutput: " + nextOutput);
		calculateDiff();
	}
	
	protected void calculateDiff() {
		float diff = nextOutput - output;
		parent.println("Diff: " + diff);
		if (parent.abs(diff) > 1.0f) {
			output += diff * easing;
		}
	}

	public float output() {
		return output;
	}
}
