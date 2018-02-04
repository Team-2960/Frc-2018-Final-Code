#include <Adafruit_NeoPixel.h>

#include <Wire.h>

String Code = "not";
void setup() {
  // put your setup code here, to run once:

  Serial.begin(115200);  
  Wire.begin(4);
  Wire.onReceive(receiveLEDCode);
}

void loop() {
  // put your main code here, to run repeatedly:
  delay(100);
}

void receiveLEDCode(int howMany){
  while ( Wire.available() > 0) 
  {
    char n = (char)Wire.read();
    if(((int)n)>((int)(' ')))
    Code += n;
  }

  Serial.print(Code);
}

void sendled(String )

