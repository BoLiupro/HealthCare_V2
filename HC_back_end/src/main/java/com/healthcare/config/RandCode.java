package com.healthcare.config;

import java.util.Random;

public class RandCode {
    public String generateFixedLengthNum(int length) {	// 获取绝对值
         length = Math.abs(length);
         Random random = new Random();	// 获取随机数，去除随机数前两位(0.)
         String randomValue = String.valueOf(random.nextDouble()).substring(2);
         String value = "";
         int maxLength = randomValue.length();	// 获取随机数字符串长度，并计算需要生成的长度与字符串长度的差值
         int diff = length - maxLength;
         if (diff > 0) {		// 如果差值大于0，则说明需要生成的串长大于获取的随机数长度，此时需要将最大长度设置为当前随机串的长
              length = maxLength;		// 同时递归调用该随机数获取方法，获取剩余长度的随机数
              value += generateFixedLengthNum(diff);
         }	// 获取最终的随机数
          value = randomValue.substring(0, length) + value;
          return value;
    }
}
