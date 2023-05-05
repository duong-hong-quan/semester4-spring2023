#include <Servo.h>  // Thư viện servo motor


Servo myServo;  // Tạo đối tượng servo

int switchPin = 2;  // Chân của công tắc
int servoPin = 9;   // Chân của servo motor

void setup() {
  myServo.attach(servoPin);          // Khởi tạo servo
  pinMode(switchPin, INPUT_PULLUP);  // Thiết lập chân của công tắc là INPUT_PULLUP
  Serial.begin(9600);
}

void loop() {
  if (isSwitchPressed() == true) {  // Kiểm tra xem công tắc đã được nhấn chưa
    unlockDoor();                // Mở khóa cửa
  } else {
    lockDoor();

  }
}

// Hàm kiểm tra xem công tắc đã được nhấn chưa
// bool isSwitchPressed() {
//   return digitalRead(switchPin) == LOW;
// }

bool isSwitchPressed() {
  bool currentState = digitalRead(switchPin);
  delay(50);  // tạm dừng 50ms để đảm bảo chắc chắn đọc được giá trị của nút
  bool isPressed = currentState == LOW && digitalRead(switchPin) == LOW;
  Serial.print(isPressed);
  return isPressed;
}



// Hàm mở khóa cửa bằng cách quay servo motor
void unlockDoor() {

  myServo.write(0);  // Quay servo motor ở góc 90 độ
  delay(5000);        // Dừng 1 giây

  // myServo.write(90);   // Quay servo motor về góc 0 độ

}

// Hàm  khóa cửa bằng cách quay servo motor
void lockDoor() {

  // myServo.write(0);   // Quay servo motor ở góc 0 độ
  // delay(1000);        // Dừng 1 giây
  myServo.write(90);  // Quay servo motor về góc 90 độ
}