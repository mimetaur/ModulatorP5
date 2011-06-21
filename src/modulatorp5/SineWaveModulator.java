package modulatorp5;

import processing.core.PApplet;

public class SineWaveModulator extends AbstractWaveModulator<SineWaveModulator> {
	
	public SineWaveModulator(PApplet p_) {
		super(p_);
	}
	
	protected float waveOutput() {
		return parent.sin(angle);
	}
}
