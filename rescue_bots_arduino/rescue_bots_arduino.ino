#include "Arduino.h"
#include <Servo.h>

Servo servo;
int wheel_left_front = 2;
int wheel_left_rear = 3;
int wheel_right_front = 4;
int wheel_right_rear = 5;

char android = 'y';
char incomingByte = 0;


void setup() {
  servo.attach(9);
  pinMode(wheel_left_front, OUTPUT);
  pinMode(wheel_left_rear, OUTPUT);
  pinMode(wheel_left_front, OUTPUT);
  pinMode(wheel_left_front, OUTPUT);


  Serial.begin(9600);
  while (!Serial){
    ;
  }
/*                     /~@@~\,
 _______ . _\_\___/\ __ /\___|_|_ . _______
/ ____  |=|      \  <_+>  /      |=|  ____ \
~|    |\|=|======\\______//======|=|/|    |~
 |_   |    \      |      |      /    |    |
  \==-|     \     |  2D  |     /     |----|~~)
  |   |      |    |      |    |      |____/~/
  |   |       \____\____/____/      /    / /
  |   |         {----------}       /____/ /
  |___|        /~~~~~~~~~~~~\     |_/~|_|/
   \_/        [/~~~~~||~~~~~\]     /__|\
   | |         |    ||||    |     (/|[[\)
   [_]        |     |  |     |
              |_____|  |_____|
              (_____)  (_____)
              |     |  |     |
              |     |  |     |
              |/~~~\|  |/~~~\|
              /|___|\  /|___|\
             <_______><_______>
*/

  Serial.println("-----------------------------------------------");
  Serial.println("  ");
  Serial.println("RescueBots");
  Serial.println("TCC Engenharia de Controle e Automacao");
  Serial.println("Aron Scolaro");
  Serial.println("  ");
  Serial.println("-----------------------------------------------");
  Serial.println("  ");
  Serial.println("Sistema Pronto");
  Serial.println("  ");
  Serial.println("-----------------------------------------------");
  Serial.println("  ");
  Serial.println("Digite o char desejado, se nao");
  Serial.println("conhece as funcoes digite 'z' para");
  Serial.println("exibir a lista. ");
  Serial.println("  ");
  Serial.println("Pode tambem controlar usando o joystick");
  Serial.println("ou entrar no modo de busca automatica.");
  Serial.println("  ");
  Serial.println("Vamos la!");
  Serial.println("  ");
  Serial.println("Entre com a opcao desejada:  ");
  Serial.println("  ");
  Serial.println("-----------------------------------------------");
  Serial.println("  ");



}

void loop(){

  //Serial.println("connect()");
  if (Serial.available() > 0) {
    // read the incoming byte:
    incomingByte = Serial.read();
    //Filtro de entrada -> a = 97, z = 122
    if (incomingByte < 97){
      incomingByte = 121;
    }
    android = incomingByte;
    //Serial.print("Received: ");
    //Serial.println(incomingByte, DEC);
    if ((incomingByte >=97 ) && (incomingByte < 122) && (incomingByte != 121)){
      Serial.print("Adroid send: ");
      Serial.println(android);
    }
    }

  // Go ahead!
  if (android == 'a'){
    Serial.println("Para frente");
    digitalWrite(wheel_left_front, HIGH);
    digitalWrite(wheel_right_front, HIGH);
    digitalWrite(wheel_left_rear, LOW);
    digitalWrite(wheel_right_rear, LOW);
    servo.write(80);
  }

  // Turn right - front
  if (android == 'b'){
    Serial.println("Virando para a esquerda");
    digitalWrite(wheel_left_front, HIGH);
    digitalWrite(wheel_right_front, LOW);
    digitalWrite(wheel_left_rear, LOW);
    digitalWrite(wheel_right_rear, LOW);
    servo.write(80);
  }

  // Turn left - front
  if (android == 'c'){
    Serial.println("Virando para a direita");
    digitalWrite(wheel_left_front, LOW);
    digitalWrite(wheel_right_front, HIGH);
    digitalWrite(wheel_left_rear, LOW);
    digitalWrite(wheel_right_rear, LOW);
    servo.write(80);
  }

  // Rear
  if (android == 'd'){
    Serial.println("Re");
    digitalWrite(wheel_left_front, LOW);
    digitalWrite(wheel_right_front, LOW);
    digitalWrite(wheel_left_rear, HIGH);
    digitalWrite(wheel_right_rear, HIGH);
    servo.write(80);
  }

  // Turn left - rear
  if (android == 'e'){
    Serial.println("Virando a esquerda com re");
    digitalWrite(wheel_left_front, LOW);
    digitalWrite(wheel_right_front, LOW);
    digitalWrite(wheel_left_rear, HIGH);
    digitalWrite(wheel_right_rear, LOW);
    servo.write(80);
  }

  // Turn right - rear
  if (android == 'f'){
    Serial.println("virando a direita com re");
    digitalWrite(wheel_left_front, LOW);
    digitalWrite(wheel_right_front, LOW);
    digitalWrite(wheel_left_rear, LOW);
    digitalWrite(wheel_right_rear, HIGH);
    servo.write(80);

  }

  // Turn left - break
  if (android == 'g'){
    Serial.println("Freando");
    digitalWrite(wheel_left_front, LOW);
    digitalWrite(wheel_right_front, LOW);
    digitalWrite(wheel_left_rear, LOW);
    digitalWrite(wheel_right_rear, LOW);
    servo.write(80);
  }

  if (android == 'h'){
    Serial.println("abrindo garra");
    digitalWrite(wheel_left_front, LOW);
    digitalWrite(wheel_right_front, LOW);
    digitalWrite(wheel_left_rear, LOW);
    digitalWrite(wheel_right_rear, LOW);
    servo.write(60);

  }

  if (android == 'i'){
    Serial.println("fechando garra");
    digitalWrite(wheel_left_front, LOW);
    digitalWrite(wheel_right_front, LOW);
    digitalWrite(wheel_left_rear, LOW);
    digitalWrite(wheel_right_rear, LOW);
    servo.write(80);
  }

  if (android == 'z'){
    Serial.println("----------------------");
    Serial.println("Lista:");
    Serial.println("a = Para frente");
    Serial.println("b = Direita");
    Serial.println("c = Esquerda");
    Serial.println("d = Re");
    Serial.println("e = Esquerda com re");
    Serial.println("f = Direita com re");
    Serial.println("g = Parar");
    Serial.println("----------------------");
  }
  delay(80);
}


