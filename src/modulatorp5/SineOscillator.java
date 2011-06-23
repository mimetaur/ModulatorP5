package modulatorp5;

import processing.core.PApplet;

public class SineOscillator extends AbstractOscillator<SineOscillator> {
	
	public SineOscillator(PApplet p_) {
		super(p_);
	}
	
	protected float waveOutput() {
		return parent.sin(angle);
	}
}
