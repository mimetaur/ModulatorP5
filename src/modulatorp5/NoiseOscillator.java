package modulatorp5;

import processing.core.PApplet;

public class NoiseOscillator extends AbstractOscillator {

	public NoiseOscillator(PApplet p_) {
		super(p_);
	}
	
	protected float waveOutput() {
		return parent.noise(angle);
	}

}
