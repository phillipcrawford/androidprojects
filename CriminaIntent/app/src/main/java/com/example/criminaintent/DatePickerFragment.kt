package com.example.criminaintent

import android.app.DatePickerDialog
import android.app.Dialog
import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs

class DatePickerFragment : DialogFragment() {

    private val arg: DatePickerFragmentArgs by navArgs()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        calendar.time = arg.crimeDate
        val initialYear = calendar.get(Calendar.YEAR)
        val initialMonth = calendar.get(Calendar.MONTH)
        val initialDay = calendar.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(
            requireContext(),
            dateListener,
            initialYear,
            initialMonth,
            initialDay
        )
    }

    companion object {
        const val REQUEST_KEY_DATE = "REQUEST_KEY_DATE"
    }
}