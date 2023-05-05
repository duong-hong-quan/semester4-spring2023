#include <Keypad.h>
#include <Servo.h>  // Thêm thư viện servo motor
#include <Adafruit_Fingerprint.h>
#include <LiquidCrystal_I2C.h>
#define mySerial Serial1


const byte ROWS = 4;  // số hàng
const byte COLS = 4;  // số cột
const int ledPin = 2;


// Khai báo đối tượng servo
Servo myServo;
// SoftwareSerial mySerial(2, 3);
Adafruit_Fingerprint finger = Adafruit_Fingerprint(&mySerial);
LiquidCrystal_I2C lcd(0x27, 16, 2);  // Địa chỉ LCD 0x27, 16 cột, 2 hàng

// Khai báo chân của servo motor
const int servoPin = 5;
int switchPin = 4;  // Chân của côn  g tắc


// Khai báo các ký tự của keypad 4x4
char keys[ROWS][COLS] = {
  { '1', '2', '3', 'A' },
  { '4', '5', '6', 'B' },
  { '7', '8', '9', 'C' },
  { '*', '0', '#', 'D' }
};

// Khai báo các chân kết nối giữa keypad và Arduino
byte rowPins[ROWS] = { 13, 12, 11, 10 };
byte colPins[COLS] = { 9, 8, 7, 6 };

// Khai báo đối tượng Keypad
Keypad keypad = Keypad(makeKeymap(keys), rowPins, colPins, ROWS, COLS);

// Khai báo mật khẩu
const char passcode[4] = { '1', '2', '3', '4' };


void setup() {
  Serial.begin(9600);  // khởi tạo Serial
  myServo.attach(servoPin);
  pinMode(switchPin, INPUT_PULLUP);  // Thiết lập chân của công tắc là INPUT_PULLUP
  lcd.init();
  lcd.backlight();
  lcd.setCursor(0, 0);
  lcd.print("INPUT PASS OR");
  lcd.setCursor(0, 1);
  lcd.print("FINGERPRINT");
  delay(10000);
  lcd.clear();
  pinMode(ledPin, OUTPUT);  // thiết đặt chân ledPin là OUTPUT

  while (!Serial)
    ;
  finger.begin(57600);
  if (finger.verifyPassword()) {
    Serial.println("Found fingerprint sensor!");
  } else {
    Serial.println("Did not find fingerprint sensor :(");
    while (1) { delay(1); }
  }
}

void loop() {
  getFingerprintIDez();
  // delay(50);  //don't ned to run this at full speed.
  isSwitchPressed();
  enterPassword();
}
void enterPassword() {
  char key = keypad.getKey();  // đọc giá trị từ keypad

  if (key) {
    Serial.print(key);  // hiển thị giá trị đọc được trên Serial Monitor
    // Kiểm tra nếu người dùng nhập chuỗi "*ad" thì cho phép đổi mật khẩu
    if (key == '*') {
      char buffer[3];
      int i = 0;
      while (i < 2) {  // đọc 2 ký tự tiếp theo
        char nextKey = keypad.waitForKey();
        if (nextKey != NO_KEY) {  // nếu có phím được nhấn
          buffer[i] = nextKey;
          i++;
        }
      }
      if (buffer[0] == 'C' && buffer[1] == 'D') {  // nếu chuỗi "*ad" được nhập
        changePassword();
        return;
      }
    }


    // Kiểm tra mật khẩu
    String inputPassword = String(key);  // tạo chuỗi với ký tự đầu tiên của mật khẩu
    char tempKey;                        // khai báo biến tạm để lưu giá trị từ keypad
    for (int i = 1; i < 10; i++) {       // đọc tiếp các ký tự khác trong chuỗi nhập vào
      tempKey = keypad.waitForKey();
      if (tempKey) {                                 // nếu có ký tự được nhập từ keypad
        Serial.print(tempKey);                       // hiển thị ký tự đó trên Serial Monitor
        inputPassword += tempKey;                    // thêm ký tự đó vào chuỗi mật khẩu
        if (inputPassword.indexOf(passcode) >= 0) {  // nếu chuỗi nhập vào chứa mật khẩu
          unlockDoor();




          break;  // thoát khỏi vòng lặp khi đã mở khóa thành công
        }
      }
    }

    // Nếu nhập sai mật khẩu sau 10 ký tự được nhập
    Serial.println("Incorrect passcode");  // hiển thị thông báo trên Serial Monitor
  }
}
void changePassword() {
  lcd.clear();
  lcd.print("ENTER NEW PASS");
  Serial.println("Enter new passcode:");  // yêu cầu người dùng nhập mật khẩu mới
  char newPasscode[4];                    // khởi tạo mảng char chứa mật khẩu mới
  for (int i = 0; i < 4; i++) {
    newPasscode[i] = keypad.waitForKey();  // đọc lần lượt từng ký tự của mật khẩu mới
    Serial.print(newPasscode[i]);          // hiển thị ký tự đó trên Serial Monitor
  }
  memcpy(passcode, newPasscode, 4);  // sao chép mật khẩu mới vào mảng passcode
  lcd.clear();
  lcd.print("PASS CHANGED");

  Serial.println(" - Passcode changed");  // thông báo mật khẩu đã được thay đổi trên Serial Monitor
  delay(2000);
  lcd.clear();
}

void unlockDoor() {

  digitalWrite(ledPin, HIGH);  // bật đèn led
  lcd.clear();
  lcd.setCursor(0, 0);
  lcd.print("DOOR IS UNLOCKED");
  myServo.write(0);           // Quay servo motor ở góc 90 độ
  delay(5000);                // Dừng 1 giây
  myServo.write(90);          // Quay servo motor về góc 90 độ
  digitalWrite(ledPin, LOW);  // tắt đèn led
  lcd.clear();
  lcd.setCursor(0, 0);
  lcd.print("DOOR IS LOCKED");
  delay(3000);
  lcd.clear();
  lcd.setCursor(0, 0);
  lcd.print("INPUT PASS OR");
  lcd.setCursor(0, 1);
  lcd.print("FINGERPRINT");
}


int getFingerprintIDez() {
  uint8_t p = finger.getImage();
  if (p != FINGERPRINT_OK) {

    return -1;
  }

  p = finger.image2Tz();
  if (p != FINGERPRINT_OK) {

    return -1;
  }

  p = finger.fingerFastSearch();
  if (p != FINGERPRINT_OK) {

    return -1;
  }

  // found a match!

  Serial.print("Found ID #");
  Serial.println(finger.fingerID);
  unlockDoor();


  return finger.fingerID;
}
void isSwitchPressed() {
  bool currentState = digitalRead(switchPin);
  delay(200);  // tạm dừng 50ms để đảm bảo chắc chắn đọc được giá trị của nút
  bool isPressed = currentState == LOW && digitalRead(switchPin) == LOW;
  if (isPressed) {
    unlockDoor();
  }
}