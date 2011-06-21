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
import java.util.ArrayList;

public class ModulatorP5 {
	
	// myParent is a reference to the parent sketch
	PApplet parent;
	
	public final static String VERSION = "##version##";
	
	private ArrayList<Modulator> allModulators;

	public ModulatorP5(PApplet theParent) {
		parent = theParent;
		parent.registerDraw(this);
		
		allModulators = new ArrayList<Modulator>();
		
		welcomeMessage();
	}
	
	public void draw() {
		for (Modulator m : allModulators) {
			m.advance();
		}
	}
	
	private void welcomeMessage() {
		System.out.println("##name## ##version## by ##author##");
	}
	
	public SineWaveModulator createSineOscillator() {
		SineWaveModulator newOsc = new SineWaveModulator(parent);
		allModulators.add(newOsc);
		return newOsc;
	}
	
	public CosineWaveModulator createCosineOscillator() {
		CosineWaveModulator newOsc = new CosineWaveModulator(parent);
		allModulators.add(newOsc);
		return newOsc;
	}
	
	public NoiseWaveModulator createNoiseOscillator() {
		NoiseWaveModulator newOsc = new NoiseWaveModulator(parent);
		allModulators.add(newOsc);
		return newOsc;
	}
}

