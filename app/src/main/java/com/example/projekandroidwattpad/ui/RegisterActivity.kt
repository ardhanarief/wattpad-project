package com.example.projekandroidwattpad.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.projekandroidwattpad.R
import com.example.projekandroidwattpad.utill.Constant
import kotlinx.android.synthetic.main.activity_register.*
import org.json.JSONObject

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        tv_back.setOnClickListener {
            finish()
        }

        btn_register.setOnClickListener {
            val queue = Volley.newRequestQueue(this)
            val url = "${Constant.BASE_URL}/user/register"

            val jsonObject = JSONObject()
            jsonObject.apply {
                put("name", et_name.text.toString())
                put("email", et_email.text.toString())
                put("password", et_password.text.toString())
            }

            val jsonObjectRequest = JsonObjectRequest(
                Request.Method.POST, url, jsonObject,
                {
                    Toast.makeText(this, "Berhasil mendaftar", Toast.LENGTH_SHORT).show()

                    finish()
                }, {
                    Toast.makeText(this, "${it.message}", Toast.LENGTH_SHORT).show()
                }
            )

            queue.add(jsonObjectRequest)
        }
    }
}