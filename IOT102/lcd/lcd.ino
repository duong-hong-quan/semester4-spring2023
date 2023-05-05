#include <Wire.h> 
#include <LiquidCrystal_I2C.h>

LiquidCrystal_I2C lcd(0x27, 16, 2);  // Địa chỉ LCD 0x27, 16 cột, 2 hàng

void setup()
{
  lcd.init();                    
  lcd.backlight(); 
 
}

void loop()
{
   lcd.setCursor(0, 0);
  lcd.print("NHOM 5");
   lcd.setCursor(0, 1);
  lcd.print("MAI DINH");

}