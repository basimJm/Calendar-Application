package com.mstart.calendarapplication.core.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.mstart.calendarapplication.domin.model.response.HijriMonthRemoteModel
import com.mstart.calendarapplication.domin.model.response.HijriWeekDayRemoteModel
import java.util.Locale

object HijriBindingAdapters {
    @JvmStatic
    @BindingAdapter("hijriMonth")
    fun setHijriMonth(textView: TextView, hijriMonth: HijriMonthRemoteModel?) {
        if (Locale.getDefault().language.startsWith("ar")) {
            textView.text = hijriMonth?.ar
        } else {
            textView.text = hijriMonth?.en
        }
    }

    @JvmStatic
    @BindingAdapter("hijriWeekday")
    fun setHijriWeekday(textView: TextView, hijriWeekday: HijriWeekDayRemoteModel?) {
        if (Locale.getDefault().language.startsWith("ar")) {
            textView.text = hijriWeekday?.ar
        } else {
            textView.text = hijriWeekday?.en
        }
    }
}
