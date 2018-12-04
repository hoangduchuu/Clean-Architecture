package grabteacher.com.mobileui.BrowseActivity;

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import grabteacher.com.mobileui.R

/**
 * Created by Huu Hoang on 02/12/2018
 */
class BrowseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browse)
        Toast.makeText(this, "abc abc", Toast.LENGTH_SHORT).show()
    }
}