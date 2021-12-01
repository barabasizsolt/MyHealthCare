package com.example.myhealthcareapp.fragments.makeAppointment

import android.os.Bundle
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
import android.widget.Toast
import com.example.myhealthcareapp.MainActivity
import com.example.myhealthcareapp.fragments.BaseFragment
import com.example.myhealthcareapp.fragments.myAppointments.MyAppointmentsFragment
import com.example.myhealthcareapp.interfaces.OnItemClickListener
import com.example.myhealthcareapp.models.CustomDate
import com.example.myhealthcareapp.models.Hospital
import com.example.myhealthcareapp.models.MedicalDepartment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class BookAppointmentFragment : BaseFragment(),
    DatePickerDialog.OnDateSetListener,
    OnItemClickListener
{
    private lateinit var recyclerview: RecyclerView
    private lateinit var adapter: BookAppointmentAdapter
    private lateinit var dateButton: FloatingActionButton
    private lateinit var timeButton: FloatingActionButton
    private lateinit var bookAppointment: Button
    private lateinit var appointmentDate: TextView
    private lateinit var appointmentDateLayout: TextInputLayout
    private lateinit var appointmentTime: TextView
    private lateinit var appointmentTimeLayout: TextInputLayout
    private lateinit var calendar: Calendar
    private lateinit var formatter: SimpleDateFormat
    private lateinit var availableDays: MutableList<CustomDate>
    private lateinit var availableHours: MutableList<String>

    private lateinit var currentHospital: Hospital
    private lateinit var currentDepartment: MedicalDepartment
    private lateinit var currentMedic: Medic

    //Dummy List for medics
    private lateinit var medics: MutableList<Medic>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_book_appointment, container, false)

        calendar = Calendar.getInstance()
        formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        recyclerview = view.findViewById(R.id.medic_recyclerview)
        medics = generateDummyList()
        currentMedic = medics[0]
        adapter = BookAppointmentAdapter(medics, this)
        recyclerview.adapter = adapter
        recyclerview.setHasFixedSize(true)
        dateButton = view.findViewById(R.id.appointment_date_btn)
        timeButton = view.findViewById(R.id.appointment_time_btn)
        bookAppointment = view.findViewById(R.id.make_appointment_btn)
        appointmentDate = view.findViewById(R.id.appointment_date)
        appointmentDateLayout = view.findViewById(R.id.appointment_date_layout)
        appointmentTime = view.findViewById(R.id.appointment_time)
        appointmentTimeLayout = view.findViewById(R.id.appointment_time_layout)
        availableDays = mutableListOf()
        availableHours = mutableListOf()

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

        //Dummy data for available hours
        for(i in 10 until 20){
            val hour = "12:30 - 14:$i"
            availableHours.add(hour)
        }

        (mActivity as MainActivity).searchIcon.isVisible = false
        (mActivity as MainActivity).profileIcon.isVisible = true

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dateButton.setOnClickListener {
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

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
            var checkedItem = 1

            MaterialAlertDialogBuilder(requireContext())
                .setTitle(resources.getString(R.string.select_time_interval))
                .setNeutralButton(resources.getString(R.string.cancel)) { _, _ -> }
                .setPositiveButton(resources.getString(R.string.ok)) { _, _ ->
                    appointmentTime.text = availableHours[checkedItem]
                }
                .setSingleChoiceItems(availableHours.toTypedArray(), checkedItem) { _, which ->
                    checkedItem = which
                }
                .show()
        }
        bookAppointment.setOnClickListener {
            if(validateInput()){
                val summary = arrayOf(
                    "Hospital: " + "Policlinica 2", //Hospital name,
                    "Department: " + "Neurology", //Department name,
                    "Medic: " + currentMedic.name, //Medic name
                    "Date & Time: " + appointmentDate.text + ", " + appointmentTime.text, //Appointment date & time
                )

                MaterialAlertDialogBuilder(requireContext())
                    .setTitle(resources.getString(R.string.summary))
                    .setItems(summary) {_, _ ->}
                    .setNeutralButton(resources.getString(R.string.cancel)) { _, _ -> }
                    .setPositiveButton(resources.getString(R.string.accept)) { _, _ ->
                        Toast.makeText(requireContext(), "Appointment added", Toast.LENGTH_SHORT).show()
                        (mActivity as MainActivity).replaceFragment(MyAppointmentsFragment(), R.id.fragment_container)
                        (mActivity as MainActivity).bottomNavigation.selectedItemId = R.id.my_appointments
                    }
                    .show()
            }
        }
    }

    private fun generateDummyList(): MutableList<Medic> {
        val list : MutableList<Medic> = mutableListOf()
        for (i in 0 until 20) {
            val item = Medic(i, "Dr House$i")
            list += item
        }
        return list
    }

    override fun onDateSet(picker: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        Log.d("DATE", "$year $monthOfYear $dayOfMonth")
        var month = (monthOfYear + 1).toString()
        if(monthOfYear + 1 < 10){
            month = "0${(monthOfYear + 1)}"
        }
        var day = (dayOfMonth + 1).toString()
        if(dayOfMonth < 10){
            day = "0$dayOfMonth"
        }
        val date = "$year-$month-$day"
        appointmentDate.text = date
    }

    override fun onItemClick(position: Int) {
        currentMedic = medics[position]
        Log.d("Poz", currentMedic.toString())
        adapter.selectMedic(position)
        appointmentDate.text = null
        appointmentTime.text = null
       //TODO: call API with the newly selected medic
    }

    private fun validateInput(): Boolean {
        appointmentDateLayout.error = null
        appointmentTimeLayout.error = null

        when{
            appointmentDate.text.toString().isEmpty() -> {
                appointmentDateLayout.error = getString(R.string.error)
                return false
            }
            appointmentTime.text.toString().isEmpty() -> {
                appointmentTimeLayout.error = getString(R.string.error)
                return false
            }
        }
        return true
    }
}