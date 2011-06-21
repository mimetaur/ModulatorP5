package modulatorp5;

import processing.core.PApplet;

public class NoiseWaveModulator extends AbstractWaveModulator {

	public NoiseWaveModulator(PApplet p_) {
		super(p_);
	}
	
	protected float waveOutput() {
		return parent.noise(angle);
	}

}
