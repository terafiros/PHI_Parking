# PHI_Parking
Automated parking system

App responsible for the comunication with hardware presence detector.

To change the state of the visual, use the command:
 
"mosquitto_pub -h test.mosquitto.org -t teste/app2 -m int/color"

int - integer, 1 to 12
color - enum "verde"/"vermelho"
