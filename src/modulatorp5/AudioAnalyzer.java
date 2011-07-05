package modulatorp5;

import ddf.minim.*;
import ddf.minim.analysis.*;
import processing.core.*;

public class AudioAnalyzer implements Modulator {
	
	public static final int INPUT_SAMPLE_RATE = 22050;
	
	public static final int MIN_AVG_BAND = 1;
	
	public static final float MIN_EASING = 0.01f;
	public static final float MAX_EASING = 0.2f;
	public static final float DEFAULT_EASING = 0.05f;
	
	public static final float DEFAULT_OUTPUT = 0.0f;
	public static final float DEFAULT_MIN_RANGE = 0.0f;
	public static final float DEFAULT_MAX_RANGE = 1.0f;
	
	public static final float DEFAULT_SENSITIVITY = 1.0f;
	
	protected PApplet parent;
	protected Minim minim;
	protected AudioInput in;
	protected FFT fft;
	
	protected float easing;
	protected float minRange, maxRange;
	protected int band;
	protected float output, nextOutput;
	protected float sensitivity;

	public AudioAnalyzer(PApplet parent_, Minim minim_, AudioInput in_) {
		parent = parent_;
		minim = minim_;
		in = in_;
		
		fft = new FFT(in.bufferSize(), INPUT_SAMPLE_RATE);
		fft.logAverages(22, 3);
		
		output = DEFAULT_OUTPUT;
		nextOutput = DEFAULT_OUTPUT;
		easing = DEFAULT_EASING;
		band = MIN_AVG_BAND;
		minRange = DEFAULT_MIN_RANGE;
		maxRange = DEFAULT_MAX_RANGE;
		sensitivity = DEFAULT_SENSITIVITY;
	}
	
	public float getMaxAvgBand() {
		return (fft.avgSize() - 1);
	}
	
	public int getBand() {
		return band;
	}

	public AudioAnalyzer setBand(int band) {
		this.band = band;
		return this;
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

	public void advance() {
		fft.forward(in.mix);
		analyze();
	}
	
	protected void analyze() {
		float raw = fft.getAvg(band);
		float constrained = parent.constrain(raw, 0.0f, 30.0f);
		parent.println("Raw: " + raw + " Constrained: " + constrained);
		nextOutput = parent.map(constrained, 0, 30.0f, minRange, maxRange);
		calculateDiff();
	}
	
	protected void calculateDiff() {
		float diff = nextOutput - output;
		if (parent.abs(diff) > 1.0f) {
			output += diff * easing;
		}
	}

	public float output() {
		return output;
	}
}
