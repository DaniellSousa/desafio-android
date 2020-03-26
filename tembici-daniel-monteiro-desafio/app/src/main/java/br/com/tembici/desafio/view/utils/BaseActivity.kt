package br.com.tembici.desafio.view.utils

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import br.com.tembici.desafio.R

/**
 * @author Daniel Monteiro
 *
 * @since on 25/032020
 */

open class BaseActivity: AppCompatActivity() {

    var dialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun setUpInicialConfigActivity() {
        createProgressDialog()
    }

    @SuppressLint("InflateParams")
    fun createProgressDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_progress_pattern, null)

        dialogBuilder.setView(dialogView)
        dialogBuilder.setTitle(R.string.str_title_progress_loading)
        dialogBuilder.setCancelable(false)

        dialog = dialogBuilder.create()
    }

    fun showProgressDialog() {
        dialog!!.show()
    }

    fun dismissProgressDailog() {
        dialog!!.dismiss()
    }

    fun showMessage(activity: AppCompatActivity, title: Int, message: Int, image: Int, isFinish: Boolean) {
        val dialogBuilder = AlertDialog.Builder(this)

        dialogBuilder.setTitle(title)
        dialogBuilder.setMessage(message)
        dialogBuilder.setCancelable(false)
        dialogBuilder.setIcon(image)

        dialogBuilder.setPositiveButton("OK",
            DialogInterface.OnClickListener { dialog, which ->

                dialog.dismiss()

                if (isFinish)
                    activity.finish()

            })

        dialogBuilder.create().show()
    }

    fun showMessage(activity: AppCompatActivity, title: Int, message: String, image: Int, isFinish: Boolean) {
        val dialogBuilder = AlertDialog.Builder(this)

        dialogBuilder.setTitle(title)
        dialogBuilder.setMessage(message)
        dialogBuilder.setCancelable(false)
        dialogBuilder.setIcon(image)

        dialogBuilder.setPositiveButton("OK",
            DialogInterface.OnClickListener { dialog, which ->

                dialog.dismiss()

                if (isFinish)
                    activity.finish()

            })

        dialogBuilder.create().show()
    }

    fun showMessageToast(activity: AppCompatActivity, msg: Int, isLong: Boolean) {
        if (isLong)
            Toast.makeText(activity, msg, Toast.LENGTH_LONG).show()
        else
            Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }
}