import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Prova_09_09 extends PApplet {

float x1, y1, maxX;
float x2, y2;
int cont1;
float Wxmin, Wxmax, Wymin, Wymax;
float Vxmin, Vxmax, Vymin, Vymax;
int PontosVermelho=0, PontosVerde=0;
boolean acerto1=false, acerto2=false;

public void setup(){
  
  stroke(255); textSize(20);
  
  Vxmin=0;
  Vxmax=width;
  Vymin=0;
  Vymax=height;
  
  x1=-30;
  maxX=+30;
  
  x2=-2*PI;
  y2=0;
  cont1=0;
}

public float XS(float XW){
    float xs = ((Vxmax-Vxmin)/(Wxmax-Wxmin))*(XW-Wxmin)+Vxmin;
    return xs;
}

public float YS(float YW){
    float ys = ((Vymax-Vymin)/(Wymax-Wymin))*(YW-Wymin)+Vymin;
    return ys;
}

public void ativaWorld1(){
  Wxmin = -18; Wxmax = +18; Wymin = 1200; Wymax = 0;
}

public void ativaWorld2(){
  Wxmin = -3;  Wxmax = +3;  Wymin = -2;  Wymax = +5;
}

public void draw() {
  noStroke();
  fill(150, 200, 180);
  rect(0, 0, width, height/2 );
  fill(0); text(PontosVermelho,10,20);

  fill(180, 100, 250);
  rect(0, height/2, width, height/2 );
  fill(0); text(PontosVerde,10,height/2+20);
  
  ativaWorld1();
  if (x1>maxX) x1=-30;
  x1=x1+0.25f;
  y1=280+0.75f*pow(x1,2);
  fill(255,0,0);
  ellipse(XS(x1),height-YS(y1),50,50);
  
  ativaWorld2();
  if (cont1>200) {x2=-2*PI; y2=0; cont1=0;}
  x2=x2+PI/60;
  y2=cos(x2);
  cont1++;
  fill(0,255,0);
  ellipse(XS(x2),height-YS(y2),50,50);
}

public void mousePressed(){
  ativaWorld1();
  if(mouseX>XS(x1)-25 && mouseX<XS(x1)+25 && mouseY>height-YS(y1)-25 && mouseY<height-YS(y1)+25)
  {
    PontosVermelho+=1;
    fill(0); text(PontosVermelho,10,20);
    acerto1=true;
  } else acerto1=false;
 
  ativaWorld2();
  if(mouseX>XS(x2)-25 && mouseX<XS(x2)+25 && mouseY>height-YS(y2)-25 && mouseY<height-YS(y2)+25)
  {
    PontosVerde+=1;
    fill(0); text(PontosVerde,10,height/2+20);
    acerto2=true;  
  } else acerto2=false;
}
  public void settings() {  size(700,500); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Prova_09_09" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
