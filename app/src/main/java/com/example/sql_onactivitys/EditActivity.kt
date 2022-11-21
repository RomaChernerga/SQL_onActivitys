package com.example.sql_onactivitys

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.sql_onactivitys.data.MyPhBookManager
import com.google.android.material.floatingactionbutton.FloatingActionButton

class EditActivity : AppCompatActivity() {

    private val imageRequestCode = 10
    var tempImageUri = "empty"
    private val dbManager = MyPhBookManager(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        visibleImage()
        editImage()
        saveContact()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val imageAvatar = findViewById<ImageView>(R.id.image_avatar)
        if (resultCode == Activity.RESULT_OK && requestCode == imageRequestCode) {
            imageAvatar?.setImageURI(data?.data)
            tempImageUri = data?.data.toString()
        }
    }





    private fun visibleImage() {
        val btnAddImage = findViewById<FloatingActionButton>(R.id.btn_addImage)
        val myImageLayout = findViewById<ConstraintLayout>(R.id.myImageLayout)
        btnAddImage.setOnClickListener {
            myImageLayout?.visibility = View.VISIBLE
            btnAddImage.visibility = View.GONE
        }

        val imBtnDelete = findViewById<ImageButton>(R.id.imBtn_delete)
        imBtnDelete.setOnClickListener {
            myImageLayout?.visibility = View.GONE
            btnAddImage.visibility = View.VISIBLE
        }
    }

    private fun editImage() {
        val imBtnEdit = findViewById<ImageButton>(R.id.imBtn_edit)
        imBtnEdit.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, imageRequestCode)
        }
    }

    private fun saveContact() {
        val btnSave = findViewById<FloatingActionButton>(R.id.btn_save)
        val nameText = findViewById<EditText>(R.id.editText_name).toString()
        val phoneText = findViewById<EditText>(R.id.editText_phone).toString()
        btnSave?.setOnClickListener {
            if(nameText != "" && phoneText != "") {
                dbManager.insertDB(nameText, phoneText, tempImageUri)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        dbManager.openBD()
    }

    override fun onDestroy() {
        super.onDestroy()
        dbManager.closeDB()
    }
}