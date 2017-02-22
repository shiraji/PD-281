package com.github.shiraji.pd_281

import android.Manifest
import android.app.Activity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Toast
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.RuntimePermissions

@RuntimePermissions
class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById(R.id.aaa)
        button.setOnClickListener { MainActivityPermissionsDispatcher.oooWithCheck(this) }
    }

    @NeedsPermission(Manifest.permission.READ_CONTACTS)
    fun ooo() {
        val c = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null)
        if (c.moveToFirst()) {
            val name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY))
            Toast.makeText(this, name, Toast.LENGTH_SHORT).show()
        }
        c.close()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>?, grantResults: IntArray?) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        MainActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults)
    }
}
