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
import controlP5.*;
import ddf.minim.*;

public class ModulatorP5 {
	
	// parent is a reference to the parent sketch
	PApplet parent;
	ControlP5 controlP5;
	Minim minim;
	AudioInput in;
	
	public final static String VERSION = "##version##";
	
	private ArrayList<Modulator> allModulators;

	public ModulatorP5(PApplet theParent) {
		parent = theParent;
		parent.registerDraw(this);
		controlP5 = new ControlP5(parent);
		minim = new Minim(parent);
		in = minim.getLineIn();
		allModulators = new ArrayList<Modulator>();
		welcomeMessage();
	}
	
	public ModulatorP5(PApplet theParent, Minim minim_, AudioInput in_) {
		parent = theParent;
		parent.registerDraw(this);
		minim = minim_;
		in = in_;
		allModulators = new ArrayList<Modulator>();
		welcomeMessage();
	}
	
	public ModulatorP5(PApplet theParent, ControlP5 controlP5_) {
		parent = theParent;
		parent.registerDraw(this);
		controlP5 = controlP5_;
		minim = new Minim(parent);
		in = minim.getLineIn();
		allModulators = new ArrayList<Modulator>();
		welcomeMessage();
	}
	
	public ModulatorP5(PApplet theParent, ControlP5 controlP5_, Minim minim_, AudioInput in_) {
		parent = theParent;
		parent.registerDraw(this);
		controlP5 = controlP5_;
		minim = minim_;
		in = in_;
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
	
	public SineOscillator createSineOscillator() {
		SineOscillator newOsc = new SineOscillator(parent);
		allModulators.add(newOsc);
		return newOsc;
	}
	
	public CosineOscillator createCosineOscillator() {
		CosineOscillator newOsc = new CosineOscillator(parent);
		allModulators.add(newOsc);
		return newOsc;
	}
	
	public NoiseOscillator createNoiseOscillator() {
		NoiseOscillator newOsc = new NoiseOscillator(parent);
		allModulators.add(newOsc);
		return newOsc;
	}
	
	public OscillatorControlPanel createControlPanel(Oscillator oscillator) {
		OscillatorControlPanel cp = new OscillatorControlPanel(parent, controlP5, oscillator);
		return cp;
	}
	
	public OscillatorControlPanel createControlPanel(int x, int y, Oscillator oscillator) {
		OscillatorControlPanel cp = new OscillatorControlPanel(parent, controlP5, x, y, oscillator);
		return cp;
	}
	
	public OscillatorControlPanel createControlPanel(int x, int y, String name, Oscillator oscillator) {
		OscillatorControlPanel cp = new OscillatorControlPanel(parent, controlP5, x, y, name, oscillator);
		return cp;	
	}
	
	public AudioAnalyzerControlPanel createControlPanel(AudioAnalyzer audioAnalyzer) {
		AudioAnalyzerControlPanel cp = new AudioAnalyzerControlPanel(parent, controlP5, audioAnalyzer);
		return cp;
	}
	
	public AudioAnalyzerControlPanel createControlPanel(int x, int y, AudioAnalyzer audioAnalyzer) {
		AudioAnalyzerControlPanel cp = new AudioAnalyzerControlPanel(parent, controlP5, x, y, audioAnalyzer);
		return cp;
	}
	
	public AudioAnalyzerControlPanel createControlPanel(int x, int y, String name, AudioAnalyzer audioAnalyzer) {
		AudioAnalyzerControlPanel cp = new AudioAnalyzerControlPanel(parent, controlP5, x, y, name, audioAnalyzer);
		return cp;	
	}
	
	public AudioAnalyzer createAudioAnalyzer() {
		AudioAnalyzer newAnalyzer = new AudioAnalyzer(parent, minim, in);
		allModulators.add(newAnalyzer);
		return newAnalyzer;
	}
}

