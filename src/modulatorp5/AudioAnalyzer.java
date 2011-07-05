package modulatorp5;

import ddf.minim.*;
import ddf.minim.analysis.*;
import processing.core.*;

public class AudioAnalyzer implements Modulator {
	
	public static final int INPUT_SAMPLE_RATE = 44100;
	
	public static final int MIN_AVG_BAND = 1;
	
	public static final float MIN_EASING = 0.01f;
	public static final float MAX_EASING = 0.5f;
	public static final float DEFAULT_EASING = 0.1f;
	
	public static final float DEFAULT_OUTPUT = 0.0f;
	public static final float DEFAULT_MIN_RANGE = 0.0f;
	public static final float DEFAULT_MAX_RANGE = 1.0f;
	
	public static final float DEFAULT_SENSITIVITY = 1.0f;
	
	public static final int MODE_LOW = 0;
	public static final int MODE_MID = 1;
	public static final int MODE_HIGH = 2;
	
	public static final float MAX_SCALE = 1.0f;
	public static final float MIN_SCALE = 0.0f;
	
	protected PApplet parent;
	protected Minim minim;
	protected AudioInput in;
	protected FFT fft;
	
	protected float easing;
	protected float minRange, maxRange;
	protected int band;
	protected float output, nextOutput;
	protected int mode;
	protected int minBand, maxBand;
	protected float scale;

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
		mode = MODE_HIGH;
		scale = MIN_SCALE;
	}
		
	public float getScale() {
		return scale;
	}

	public AudioAnalyzer setScale(float newScale) {
		this.scale = parent.constrain(newScale, MIN_SCALE, MAX_SCALE);
		return this;
	}

	public int getMode() {
		return mode;
	}

	public AudioAnalyzer setMode(int mode) {
		this.mode = parent.constrain(mode, MODE_LOW, MODE_HIGH);
		return this;
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
		float raw = calculateAverage();
		float limit = calculateUpperLimit();

		float constrained = parent.constrain(raw, 0.0f, limit);
		float scaled = scaleFFTOutput(constrained);
		nextOutput = parent.map(scaled, 0.0f, limit, minRange, maxRange);
		calculateDiff();
	}
	
	protected float scaleFFTOutput(float value) {
		return value * getScale();
	}
	
	protected float calculateUpperLimit() {
		float limit = 1.0f;
		switch(mode) {
			case MODE_LOW:
				limit = 20.0f;
				break;
			case MODE_MID:
				limit = 2.0f;
				break;
			case MODE_HIGH:
				limit = 0.8f;
				break;
		}
		return limit;
	}
	
	protected float calculateAverage() {
		float average = 0.0f;
		switch (mode) {
			case MODE_LOW:  
				average = fft.calcAvg(20, 250); 
				break;
			case MODE_MID: 
				average = fft.calcAvg(250, 5000);
				break;
			case MODE_HIGH:
				average = fft.calcAvg(5000, 14000);
				break;
		}
		return average;
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
