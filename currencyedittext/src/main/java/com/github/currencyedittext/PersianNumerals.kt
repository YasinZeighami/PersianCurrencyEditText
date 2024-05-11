package com.github.currencyedittext

class PersianNumerals {
    companion object {
        val units = arrayOf(
            "", "یک", "دو", "سه", "چهار", "پنج", "شش", "هفت", "هشت", "نه", "ده",
            "یازده", "دوازده", "سیزده", "چهارده", "پانزده", "شانزده", "هفده", "هجده", "نوزده"
        )
        val tens = arrayOf("", "", "بیست", "سی", "چهل", "پنجاه", "شصت", "هفتاد", "هشتاد", "نود")
        val hundreds =
            arrayOf("", "صد", "دویست", "سیصد", "چهارصد", "پانصد", "ششصد", "هفتصد", "هشتصد", "نهصد")
        val thousands = arrayOf(
            "", "هزار", "میلیون", "میلیارد", "بیلیون", "بیلیارد", "تریلیون", "تریلیارد",
            "کوآدریلیون", "کادریلیارد", "کوینتیلیون", "کوانتینیارد", "سکستیلیون", "سکستیلیارد"
        )
    }
}