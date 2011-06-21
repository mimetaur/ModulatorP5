package modulatorp5;

import processing.core.PApplet;

public class CosineWaveModulator extends AbstractWaveModulator<CosineWaveModulator> {
	
	public CosineWaveModulator(PApplet p_) {
		super(p_);
	}
	
	protected float waveOutput() {
		return parent.sin(angle);
	}
}
