package modulatorp5;

import processing.core.PApplet;

public class CosineOscillator extends Oscillator<CosineOscillator> {
	
	public CosineOscillator(PApplet p_) {
		super(p_);
	}
	
	protected float waveOutput() {
		return parent.sin(angle);
	}
}
