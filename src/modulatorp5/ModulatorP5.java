/**
 * you can put a one sentence description of your library here.
 *
 * ##copyright##
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General
 * Public License along with this library; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA  02111-1307  USA
 * 
 * @author		##author##
 * @modified	##date##
 * @version		##version##
 */

package modulatorp5;


import processing.core.*;

/**
 * This is a template class and can be used to start a new processing library or tool.
 * Make sure you rename this class as well as the name of the example package 'template' 
 * to your own library or tool naming convention.
 * 
 * @example Hello 
 * 
 * (the tag @example followed by the name of an example included in folder 'examples' will
 * automatically include the example in the javadoc.)
 *
 */

public class ModulatorP5 {
	
	// myParent is a reference to the parent sketch
	PApplet parent;
	
	public final static String VERSION = "##version##";

	public ModulatorP5(PApplet theParent) {
		parent = theParent;
		welcomeMessage();
	}
	
	private void welcomeMessage() {
		System.out.println("##name## ##version## by ##author##");
	}
	
	public Oscillator createOscillator() {
		Oscillator newOsc = new Oscillator(parent, Oscillator.MIN_WAVE_OUTPUT, Oscillator.MAX_WAVE_OUTPUT, Oscillator.DEFAULT_RATE, Oscillator.MIN_AMOUNT, Oscillator.DEFAULT_MIN_RATE, Oscillator.DEFAULT_MAX_RATE);
		return newOsc;
	}
	
	// Min Range, Max Range
	public Oscillator createOscillator(float minR_, float maxR_) {
		Oscillator newOsc = new Oscillator(parent, minR_, maxR_, Oscillator.DEFAULT_RATE, Oscillator.MIN_AMOUNT, Oscillator.DEFAULT_MIN_RATE, Oscillator.DEFAULT_MAX_RATE);
		return newOsc;
	}
  
	// Min Range, Max Range, Starting Rate
	public Oscillator createOscillator(float minR_, float maxR_, float rate_) {
		Oscillator newOsc = new Oscillator(parent, minR_, maxR_, rate_, Oscillator.MIN_AMOUNT, Oscillator.DEFAULT_MIN_RATE, Oscillator.DEFAULT_MAX_RATE);
		return newOsc;
	}

	// Min Range, Max Range, Starting Rate, Min Rate, Max Rate
	public Oscillator createOscillator(float minR_, float maxR_, float rate_, float minRt_, float maxRt_) {
		Oscillator newOsc = new Oscillator(parent, minR_, maxR_, rate_, Oscillator.MIN_AMOUNT, minRt_, maxRt_);
		return newOsc;
	}

	// Min Range, Max Range, Starting Rate, Min Rate, Max Rate, Starting Amount
	public Oscillator createOscillator(float minR_, float maxR_, float rate_, float minRt_, float maxRt_, float amount_) {
		Oscillator newOsc = new Oscillator(parent, minR_, maxR_, rate_, amount_, minRt_, maxRt_);
		return newOsc;
	}
}

