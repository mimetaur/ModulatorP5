import modulatorp5.*;

ModulatorP5 hello;

void setup() {
  size(400,400);
  smooth();
  
  hello = new ModulatorP5(this);
  
  PFont font = createFont("",40);
  textFont(font);
}

void draw() {
  background(0);
  fill(255);
  text(hello.sayHello(), 40, 200);
}