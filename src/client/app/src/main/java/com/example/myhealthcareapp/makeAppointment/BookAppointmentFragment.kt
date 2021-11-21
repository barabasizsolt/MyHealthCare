package com.example.myhealthcareapp.makeAppointment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myhealthcareapp.R
import com.example.myhealthcareapp.adapters.BookAppointmentAdapter
import com.example.myhealthcareapp.models.Medic
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import android.util.Log
import com.example.myhealthcareapp.models.CustomDate
import java.text.SimpleDateFormat
import java.util.*

class BookAppointmentFragment : Fragment(),
    DatePickerDialog.OnDateSetListener
{
    private lateinit var recyclerview: RecyclerView
    private lateinit var dateButton: FloatingActionButton
    private lateinit var timeButton: FloatingActionButton
    private lateinit var bookAppointment: Button
    private lateinit var appointmentDate: TextView
    private lateinit var appointmentTime: TextView
    private lateinit var calendar: Calendar
    private lateinit var formatter: SimpleDateFormat
    private lateinit var availableDays: MutableList<CustomDate>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_book_appointment, container, false)

        setupUI(view)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dateButton.setOnClickListener {
            val year = calendar.get(Calendar.YEAR) ;
            val month = calendar.get(Calendar.MONTH);
            val day = calendar.get(Calendar.DAY_OF_MONTH);
            val hour = calendar.get(Calendar.HOUR_OF_DAY);
            val minute = calendar.get(Calendar.MINUTE);

            val datePickerDialog = DatePickerDialog.newInstance(this, year, month, day)
            datePickerDialog.isThemeDark = true
            datePickerDialog.showYearPickerFirst(false)
            datePickerDialog.setTitle("Set appointment date")

            // Setting Min Date to today date
            val minDate = Calendar.getInstance()
            datePickerDialog.minDate = minDate

            // Setting Max Date to next 1 years
            val maxDate = Calendar.getInstance()
            maxDate[Calendar.YEAR] = year + 1
            datePickerDialog.maxDate = maxDate

            //Disable all SUNDAYS, SATURDAYS and occupied dates between Min and Max Dates
            var loopDate: Calendar = minDate
            while (minDate.before(maxDate)) {
                val dayOfWeek = loopDate[Calendar.DAY_OF_WEEK]
                val customDate = CustomDate(loopDate[Calendar.YEAR], loopDate[Calendar.MONTH], loopDate[Calendar.DATE])
                if (dayOfWeek == Calendar.SUNDAY || dayOfWeek == Calendar.SATURDAY || !availableDays.contains(customDate)) {
                    val disabledDays = arrayOfNulls<Calendar>(1)
                    disabledDays[0] = loopDate
                    datePickerDialog.disabledDays = disabledDays
                }
                minDate.add(Calendar.DATE, 1)
                loopDate = minDate
            }

            datePickerDialog.show(childFragmentManager, "DatePickerDialog")
        }
        timeButton.setOnClickListener {
        }
        bookAppointment.setOnClickListener {

        }
    }

    private fun setupUI(view: View){
        calendar = Calendar.getInstance()
        formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        recyclerview = view.findViewById(R.id.medic_recyclerview)
        recyclerview.adapter = BookAppointmentAdapter(generateDummyList())
        recyclerview.setHasFixedSize(true)
        dateButton = view.findViewById(R.id.appointment_date_btn)
        timeButton = view.findViewById(R.id.appointment_time_btn)
        bookAppointment = view.findViewById(R.id.make_appointment_btn)
        appointmentDate = view.findViewById(R.id.appointment_date)
        appointmentTime = view.findViewById(R.id.appointment_time)
        availableDays = mutableListOf()

        //Dummy Data for available days
        for(i in 10 until 20) {
            val date = "2021-12-$i"
            formatter.parse(date)
            val cal = formatter.calendar
            val customDate = CustomDate(cal[Calendar.YEAR], cal[Calendar.MONTH], cal[Calendar.DATE])
            availableDays.add(customDate)
        }
        for(i in 2 until 25) {
            val date = "2022-01-$i"
            formatter.parse(date)
            val cal = formatter.calendar
            val customDate = CustomDate(cal[Calendar.YEAR], cal[Calendar.MONTH], cal[Calendar.DATE])
            availableDays.add(customDate)
        }
    }

    private fun generateDummyList(): MutableList<Medic> {
        val list : MutableList<Medic> = mutableListOf()
        for (i in 0 until 20) {
            val item = Medic(i, "Dr House")
            list += item
        }
        return list
    }

    override fun onDateSet(view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        Log.d("DATE", "$year $monthOfYear $dayOfMonth")
    }
}