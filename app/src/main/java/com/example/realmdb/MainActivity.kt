package com.example.realmdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.realmdb.DB.Student
import com.example.realmdb.databinding.ActivityMainBinding
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.Exception

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    var mRealm: Realm? = null
    private lateinit var mStudent: Student
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mStudent = Student()
        mRealm = Realm.getDefaultInstance()

        button_delete.setOnClickListener(this)
        button_insert.setOnClickListener(this)
        button_read.setOnClickListener(this)
        button_update.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            button_delete -> deleteData()
            button_insert -> addData()
            button_read -> readData()
            button_update -> updateData()
        }
    }

    private fun addData() {
        try {
            mStudent.apply {
                id = text_input_user_id.text.toString().toLong()
                name = text_input_username.text.toString()
                email = text_input_user_email.text.toString()
            }
            mRealm?.executeTransaction { mRealm?.copyToRealm(mStudent) }

            clearText()

        } catch (e: Exception) {
            Log.d("tag", "loi luc khoi tao")
        }
    }

    private fun readData() {
        var id: Long = text_input_user_id.text.toString().toLong()
        val mStudents: List<Student>? = mRealm!!.where(Student::class.java).equalTo("id",id)?.findAll()
        for (i in mStudents?.indices!!) {
            text_input_user_id.setText("${mStudents[i].id}")
            text_input_user_email.setText("${mStudents[i].email}")
            text_input_username.setText("${mStudents[i].name}")
        }

    }

    private fun deleteData() {

        var id: Long = text_input_user_id.text.toString().toLong()
        val student =
            mRealm!!.where(Student::class.java).equalTo("id", id).findFirst()
        mRealm!!.executeTransaction {
            student?.deleteFromRealm()
        }

    }

    private fun updateData() {
            val id: Int = text_input_user_id.text.toString().toInt()
            val student =
                mRealm!!.where(Student::class.java).equalTo("id", id).findFirst()

            text_input_username.setText(student?.name)
            text_input_user_email.setText(student?.email)

    }

    private fun clearText() {
        text_input_user_email.setText("")
        text_input_username.setText("")
        text_input_user_id.setText("")
    }

    override fun onDestroy() {
        super.onDestroy()
        mRealm?.close()
    }
}