package com.example.fullscreendialog

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
//import android.support.v4.app.DialogFragment;
//import android.support.v7.app.AlertDialog;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment


class AlertDialogFragment : DialogFragment(), DialogInterface.OnClickListener {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity!!)
        return builder.setTitle("Activate superpowers?")
            .setMessage("By activating superpowers, you'll have more powers to save the world.")
            .setPositiveButton("Activate", this)
            .setNegativeButton("Cancel", this)
            .create()
    }

    override fun onClick(dialog: DialogInterface, which: Int) {
        Toast.makeText(activity, "which is $which", Toast.LENGTH_LONG).show()
    }
}