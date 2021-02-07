package com.example.projekandroidwattpad.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.projekandroidwattpad.NavigationActivity
import com.example.projekandroidwattpad.R
import com.example.projekandroidwattpad.model.User
import com.example.projekandroidwattpad.utill.Constant.BASE_URL
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        tv_register.setOnClickListener {
            startActivity(
                Intent(this, RegisterActivity::class.java)
            )
        }

        btn_login.setOnClickListener {
            val queue = Volley.newRequestQueue(this)
            val url = "$BASE_URL/user/login"

            val jsonObject = JSONObject()
            jsonObject.apply {
                put("email", et_email.text.toString())
                put("password", et_password.text.toString())
            }

            val jsonObjectRequest = JsonObjectRequest(
                Request.Method.POST, url, jsonObject,
                { json ->
                    val user = json.getJSONObject("data")

                    if (user.toString() != "null") {
                        Toast.makeText(this, "Berhasil masuk", Toast.LENGTH_SHORT).show()

                        val intent = Intent(this, NavigationActivity::class.java)
                        val dataUser =
                            User(
                                email = user.getString("email"),
                                name = user.getString("name")
                            )
                        intent.apply {
                            putExtra(NavigationActivity.EXTRA_USER_SERIALIZE, dataUser)
                        }
                        startActivity(intent)
                    }
                }, {
                    Toast.makeText(this, "${it.message}", Toast.LENGTH_SHORT).show()
                }
            )
            queue.add(jsonObjectRequest)
        }
    }
}