#include <Keypad.h>
#include <Servo.h>  // Thêm thư viện servo motor
#include <Wire.h>
#include <LiquidCrystal_I2C.h>

LiquidCrystal_I2C lcd(0x27, 16, 2);
#define ledRedPin 4
#define ledGreenPin 5
const byte ROWS = 4;  // số hàng
const byte COLS = 4;  // số cột



// Khai báo đối tượng servo
Servo myServo;

// Khai báo chân của servo motor
const int servoPin = 5;

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
  lcd.init();
  lcd.backlight();
  lcd.setCursor(0, 0);
  lcd.print("Input pass");
  delay(3000);
  lcd.clear();
}

void loop() {

  enterPassword();
}
bool enterPassword() {

  char key = keypad.getKey();  // đọc giá trị từ keypad

  if (key) {
    Serial.print(key);  // hiển thị giá trị đọc được trên Serial Monitor

    // Kiểm tra nếu người dùng nhập chuỗi "*ad" thì cho phép đổi mật khẩu
    if (key == '*') {
      char buffer[3];
      int i = 0;
      while (i < 2) {  // đọc 2 ký tự tiếp theo
        char nextKey = keypad.getKey();
        if (nextKey != NO_KEY) {  // nếu có phím được nhấn
          buffer[i] = nextKey;
          i++;
        }
      }
      if (buffer[0] == 'A' && buffer[1] == 'D') {  // nếu chuỗi "*ad" được nhập
        changePassword();
        return true;
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
          Serial.println(" - Unlocked");  // hiển thị thông báo trên Serial Monitor
          delay(5000);                    // chờ 5 giây
          lockDoor();
        return true;

          // break;  // thoát khỏi vòng lặp khi đã mở khóa thành công
        }
      }
    }

    // Nếu nhập sai mật khẩu sau 10 ký tự được nhập
    lockDoor();
    Serial.println(" - Incorrect passcode");  // hiển thị thông báo trên Serial Monitor
    return false;
  }
}

void changePassword() {
  Serial.println("Enter new passcode:");  // yêu cầu người dùng nhập mật khẩu mới
  
  char newPasscode[4];  // khởi tạo mảng char chứa mật khẩu mới
  for (int i = 0; i < 4; i++) {
    newPasscode[i] = keypad.waitForKey();  // đọc lần lượt từng ký tự của mật khẩu mới
    Serial.print(newPasscode[i]);          // hiển thị ký tự đó trên Serial Monitor
  }
  memcpy(passcode, newPasscode, 4);       // sao chép mật khẩu mới vào mảng passcode
  Serial.println(" - Passcode changed");  // thông báo mật khẩu đã được thay đổi trên Serial Monitor
}

void unlockDoor() {
  digitalWrite(ledRedPin, LOW);
  digitalWrite(ledGreenPin, HIGH);
  myServo.write(0);  // Quay servo motor ở góc 90 độ
  lcd.print("Door unlocked!");

  delay(2000);  // Dừng 1 giây
  lcd.clear();
}

// Hàm  khóa cửa bằng cách quay servo motor
void lockDoor() {
  digitalWrite(ledRedPin, HIGH);
  digitalWrite(ledGreenPin, LOW);
  myServo.write(90);  // Quay servo motor về góc 90 độ
  lcd.print("Door locked!");

  delay(500);  // Dừng 1 giây
  lcd.clear();
}