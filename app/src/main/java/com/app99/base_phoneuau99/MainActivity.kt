package com.app99.base_phoneuau99

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.find_phones_button
import kotlinx.android.synthetic.main.activity_main.found_phones_button
import java.util.Calendar

class MainActivity : AppCompatActivity() {

  private val FIND_PHONE_REFERENCE = "findPhone"
  private val PROD = "prodUAU"
  private val DEV = "testDEV"

  private val finderRef = FirebaseDatabase.getInstance().getReference(PROD).child(FIND_PHONE_REFERENCE)

//  private val finderRef = FirebaseDatabase.getInstance().getReference(DEV).child(FIND_PHONE_REFERENCE)

  private val CHECKOUT = 99

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    initListeners()
  }

  private fun initListeners() {
    initClickListeners()
  }

  private fun initClickListeners() {
    find_phones_button.setOnClickListener { executeFinderPhone() }
    found_phones_button.setOnClickListener { executeFoundPhone() }
  }

  private fun getPhoneDate() = Calendar.getInstance().time

  private fun getDateInSecondsAddingSeconds(seconds: Int): Long {
    val calendar = Calendar.getInstance()
    calendar.time = getPhoneDate()
    calendar.add(Calendar.SECOND, seconds)

    return ((calendar.timeInMillis) / 1000)
  }

  private fun executeFinderPhone() {
    val SECONDS = 30

    finderRef.setValue(getDateInSecondsAddingSeconds(SECONDS))
    find_phones_button.visibility = View.GONE
    found_phones_button.visibility = View.VISIBLE
  }

  private fun executeFoundPhone() {
    finderRef.setValue(CHECKOUT)
    find_phones_button.visibility = View.VISIBLE
    found_phones_button.visibility = View.GONE
  }
}
