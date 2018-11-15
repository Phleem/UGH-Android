package ugh_technologies.universalgamehelper

import android.content.Intent
import android.content.res.Resources
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem


class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        val res: Resources = resources

        val config = res.getStringArray(R.array.configuration)

        setupNavDrawer(config)
        initButtons()


    }

    private fun setupNavDrawer(configuration:Array<String>) {
        val drawer = DrawerBuilder()
                .withActivity(this)
                .withTranslucentStatusBar(false)
                .withFullscreen(false)
                .build()
        for (feature in configuration) {
            val item = PrimaryDrawerItem().withName(feature)
            val clickListener = item.onDrawerItemClickListener
            drawer.addItem(item)
            drawer.onDrawerItemClickListener = clickListener
        }
    }

    private fun initButtons(){
        val donateButton = findViewById<Button>(R.id.donate_button)
        donateButton.setOnClickListener {

            val uri = Uri.parse("https://paypal.me/DanielMicheel")
            val intent = Intent(Intent.ACTION_VIEW, uri)

            startActivity(intent)
        }

        val shareButton = findViewById<Button>(R.id.share_button)
        shareButton.setOnClickListener {

            val shareIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_SUBJECT, "Universal Game Helper - Report")
            putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
            type = "text/plain"
            }

            startActivity(Intent.createChooser(shareIntent, "Share via"))
        }

    }
}
