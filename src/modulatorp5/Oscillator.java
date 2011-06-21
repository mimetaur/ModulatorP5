package modulatorp5;

public interface Oscillator<T extends AbstractWaveModulator> extends Modulator {
	static final float MIN_AMOUNT = 0.0f;
	static final float MAX_AMOUNT = 1.0f;  
		    
	static final float DEFAULT_MIN_RATE = 0.0f;
	static final float DEFAULT_MAX_RATE = 1.0f;
	  
	static final float DEFAULT_RATE = 0.0f;
	static final float DEFAULT_ANGLE = 0.0f;
	
	static final float MIN_WAVE_OUTPUT = -1.0f;
	static final float MAX_WAVE_OUTPUT = 1.0f;
	
	public float getMinRate();
	public T setMinRate(float minRate);

	public float getMaxRate();
	public T setMaxRate(float maxRate);
	
	public float getMinRange();
	public T setMinRange(float minRange);

	public float getMaxRange();
	public T setMaxRange(float maxRange);

	public float getRate();
	public T setRate(float newRate);

	public float getAmount();
	public T setAmount(float newAmount);
}
