#include <Adafruit_Fingerprint.h>
#include <Servo.h>  // Thêm thư viện servo motor

// Khai báo chân kết nối với vân tay
#define fingerRX 2
#define fingerTX 3
#define ledRedPin 4
#define ledGreenPin 5
// Khai báo đối tượng vân tay
// Nếu board mega 2560 thì thay bằng &serial1
SoftwareSerial mySerial(2, 3);

Adafruit_Fingerprint finger = Adafruit_Fingerprint(&Serial);

// Khai báo đối tượng servo
Servo myServo;

// Khai báo chân của servo motor
const int servoPin = 9;

void setup() {
  // Khởi tạo kết nối Serial
  Serial.begin(9600);
  while (!Serial)
    ;  // chờ kết nối

  // Khởi tạo kết nối với vân tay
  finger.begin(57600);
  pinMode(ledRedPin, OUTPUT);
  pinMode(ledGreenPin, OUTPUT);

  // Khởi tạo chân của servo motor
  myServo.attach(servoPin);
  checkSensorFingerPrint();
}

void loop() {
  if (getFingerprintFeature()) {
    compareSavedFingerPrint();
  }
}
void checkSensorFingerPrint() {
  // Kiểm tra xem vân tay có đang hoạt động không
  if (finger.verifyPassword()) {
    Serial.println("Fingerprint sensor is active.");
  } else {
    Serial.println("Fingerprint sensor is not responding. Please check wiring.");
    while (1)
      ;  // Dừng chương trình
  }
}
void compareSavedFingerPrint() {
  // So sánh đặc trưng với danh sách vân tay đã được đăng ký
  int fingerprintID = finger.fingerFastSearch();
  if (fingerprintID == FINGERPRINT_OK) {
    Serial.println("Fingerprint found. Access granted.");
    unlockDoor();  // Mở khóa cửa bằng cách quay servo motor
  } else {
    lockDoor();
    Serial.println("Fingerprint not found. Access denied.");
  }

  delay(1000);  // Chờ 1 giây trước khi quét vân tay tiếp theo
}
bool getFingerprintFeature() {
  Serial.println("Please place your finger on the sensor.");
  while (finger.getImage() != FINGERPRINT_OK)
    ;

  if (finger.image2Tz() != FINGERPRINT_OK) {
    Serial.println("Failed to convert image to template.");
    return false;
  }

  return true;
}

// Hàm mở khóa cửa bằng cách quay servo motor
void unlockDoor() {
  digitalWrite(ledRedPin, LOW);
  digitalWrite(ledGreenPin, HIGH);
  myServo.write(0);  // Quay servo motor ở góc 90 độ
  delay(1000);       // Dừng 1 giây
}

// Hàm  khóa cửa bằng cách quay servo motor
void lockDoor() {
  digitalWrite(ledRedPin, HIGH);
  digitalWrite(ledGreenPin, LOW);

  myServo.write(90);  // Quay servo motor về góc 90 độ
}