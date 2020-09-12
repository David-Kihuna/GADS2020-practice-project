package com.example.gads20_practice_project.ui

import android.app.Dialog
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.gads20_practice_project.R
import com.example.gads20_practice_project.network.Api
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SubmitActivity : AppCompatActivity() {

    private var submitJob = Job()
    private val coroutineScope = CoroutineScope(
        submitJob + Dispatchers.Main
    )

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submit)

        val upButton = findViewById<ImageView>(R.id.up_button)
        upButton.setOnClickListener {
            finish()
        }

        val submitButton = findViewById<Button>(R.id.last_submit_button)
        submitButton.setOnClickListener{
            val dialog = Dialog(this)
            dialog.setContentView(R.layout.dialog_ask)
            dialog.show()

            if (dialog.isShowing) {
                val cancelButton = dialog.findViewById<ImageButton>(R.id.cancel)
                cancelButton.setOnClickListener{
                    dialog.dismiss()
                }

                val yesButton = dialog.findViewById<Button>(R.id.button_yes)
                yesButton.setOnClickListener{
                    dialog.dismiss()
                    coroutineScope.launch {
                        val firstName = findViewById<EditText>(R.id.first_name)
                        val lastName = findViewById<EditText>(R.id.last_name)
                        val email = findViewById<EditText>(R.id.email)
                        val link = findViewById<EditText>(R.id.link)
                        val call = Api.retrofitService.submit(
                            firstName.text.toString(),
                            lastName.text.toString(),
                            email.text.toString(),
                            link.text.toString()
                        )
                        call.enqueue(object: Callback<Void>{
                            override fun onFailure(call: Call<Void>, t: Throwable) {
                                dialog.setContentView(R.layout.dialog_failed)
                                dialog.show()
                            }

                            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                                dialog.setContentView(R.layout.dialog_success)
                                dialog.show()
                            }

                        })
                    }
                }
            }
        }

    }
}