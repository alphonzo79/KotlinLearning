package rowley.kotlinlearning.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_settings.*
import kotlinx.android.synthetic.main.toolbar.*
import rowley.kotlinlearning.R
import rowley.kotlinlearning.extensions.DelegatesExt

class SettingsActivity : AppCompatActivity() {

    companion object {
        val PREF_KEY_ZIP_CODE = "zipCode"
        val DEFAULT_ZIP = 83687L
    }

    var zipCode by DelegatesExt.preference(this, PREF_KEY_ZIP_CODE, DEFAULT_ZIP)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        zipCodeInput.setText(zipCode.toString())
    }

    override fun onOptionsItemSelected(item: MenuItem) = when(item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> false
    }

    override fun onBackPressed() {
        super.onBackPressed()
        zipCode = zipCodeInput.text.toString().toLong()
    }
}
