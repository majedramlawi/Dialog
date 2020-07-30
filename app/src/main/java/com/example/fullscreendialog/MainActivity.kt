package com.example.fullscreendialog

//import android.support.design.widget.BottomSheetDialog;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentTransaction;
//import android.support.v7.app.AlertDialog;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;


import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.content.DialogInterface
import android.content.DialogInterface.OnMultiChoiceClickListener
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import java.text.DateFormat
import java.util.*


class MainActivity : AppCompatActivity(), CallbackListener {
    var calendar: Calendar = Calendar.getInstance()

    private var mBottomSheetDialog: BottomSheetDialog? = null

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//    }

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
            supportActionBar!!.title = "MainActivity"
            show_alert_dialog.setOnClickListener(View.OnClickListener { showAlertDialog() })
            show_multichoice_dialog.setOnClickListener(View.OnClickListener { showMultiChoiceDialog() })
            show_single_choice_dialog.setOnClickListener(View.OnClickListener { showSingleChoiceDialog() })
            show_date_picker_dialog.setOnClickListener(View.OnClickListener { showDatePickerDialog() })
            show_time_picker_dialog.setOnClickListener(View.OnClickListener { showTimePickerDialog() })
            show_bottom_sheet_dialog.setOnClickListener(View.OnClickListener { showBottomSheetDialog() })

            show_full_screen_dialog.setOnClickListener(View.OnClickListener {

//            val fragmentManager: FragmentManager = supportFragmentManager
//            val newFragment = FullscreenDialogFragment()
//            val transaction: FragmentTransaction = fragmentManager.beginTransaction()
//            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//            transaction.add(R.id.content, newFragment).addToBackStack(null).commit()


                val fragmentManager = supportFragmentManager
                val newFragment = FullscreenDialogFragment(this)

//            val transaction: FragmentTransaction = fragmentManager.beginTransaction()
//            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//            transaction.add(0, newFragment).addToBackStack(null).commit()

                newFragment.show(fragmentManager, "dialog")

        })

            show_alert_dialog_rotation.setOnClickListener(View.OnClickListener {

                AlertDialogFragment().show(
                supportFragmentManager,
                "sample"
            )
        })
    }

    private fun showBottomSheetDialog() {
        val bottomSheetLayout: View =
            layoutInflater.inflate(R.layout.bottom_sheet_dialog, null)
        bottomSheetLayout.findViewById<View>(R.id.button_close)
            .setOnClickListener { mBottomSheetDialog!!.dismiss() }
        bottomSheetLayout.findViewById<View>(R.id.button_ok).setOnClickListener {
            Toast.makeText(applicationContext, "Details clicked", Toast.LENGTH_SHORT)
                .show()
        }
        mBottomSheetDialog = BottomSheetDialog(this)
        mBottomSheetDialog!!.setContentView(bottomSheetLayout)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mBottomSheetDialog!!.window?.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
        mBottomSheetDialog!!.show()
        mBottomSheetDialog!!.setOnDismissListener(DialogInterface.OnDismissListener {
            mBottomSheetDialog = null
        })
    }

    private fun showTimePickerDialog() {
        val timePickerDialog = TimePickerDialog(
            this,
            OnTimeSetListener { timePicker, hourOfDay, minute ->
                calendar[Calendar.HOUR_OF_DAY] = hourOfDay
                calendar[Calendar.MINUTE] = minute
                val time =
                    DateFormat.getTimeInstance(DateFormat.SHORT)
                        .format(calendar.time)
                Log.d("MainActivity", "Selected time is $time")
            },
            calendar[Calendar.HOUR_OF_DAY],
            calendar[Calendar.MINUTE],
            true
        )
        timePickerDialog.show()
    }

    private fun showDatePickerDialog() {
        val datePickerDialog = DatePickerDialog(
            this,
            OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                calendar[Calendar.YEAR] = year
                calendar[Calendar.MONTH] = monthOfYear
                calendar[Calendar.DAY_OF_MONTH] = dayOfMonth
                val date =
                    DateFormat.getDateInstance(DateFormat.MEDIUM)
                        .format(calendar.time)
                Log.d("MainActivity", "Selected date is $date")
            },
            calendar[Calendar.YEAR],
            calendar[Calendar.MONTH],
            calendar[Calendar.DAY_OF_MONTH]
        )
        datePickerDialog.show()
    }

    private fun showSingleChoiceDialog() {
        val singleChoiceItems =
            resources.getStringArray(R.array.dialog_single_choice_array)
        val itemSelected = 0
        AlertDialog.Builder(this)
            .setTitle("Select your gender")
            .setSingleChoiceItems(
                singleChoiceItems,
                itemSelected,
                DialogInterface.OnClickListener { dialogInterface, selectedIndex -> })
            .setPositiveButton("Ok", null)
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun showAlertDialog() {
        val dialog: AlertDialog = AlertDialog.Builder(this, R.style.CustomDialogTheme)
            .setTitle("Nuke planet Jupiter?")
            .setMessage("Note that nuking planet Jupiter will destroy everything in there.")
            .setPositiveButton("Nuke",
                DialogInterface.OnClickListener { dialog, which ->
                    Log.d(
                        "MainActivity",
                        "Sending bombs to earth"
                    )
                })
            .setNegativeButton("Abort",
                DialogInterface.OnClickListener { dialog, which ->
                    Log.d(
                        "MainActivity",
                        "Aborting mission"
                    )
                })
            .setNeutralButton("Neutral", null)
            .create()
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()
    }

    private fun showMultiChoiceDialog() {
        val multiChoiceItems =
            resources.getStringArray(R.array.dialog_multi_choice_array)
        val checkedItems = booleanArrayOf(false, false, false, false)
        AlertDialog.Builder(this)
            .setTitle("Select your favourite movies")
            .setMultiChoiceItems(
                multiChoiceItems,
                checkedItems,
                OnMultiChoiceClickListener { dialog, index, isChecked ->
                    Log.d(
                        "MainActivity",
                        "click index is $isChecked"
                    )
                })
            .setPositiveButton("Ok", null)
            .setNegativeButton("Cancel", null)
            .show()
    }

    override fun onDataReceived(data: String) {
        txtDataFromDialog.text = data
    }
}