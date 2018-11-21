package ugh_technologies.universalgamehelper

import android.content.Intent
import android.content.res.Resources
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.util.Log
import android.view.View
import android.widget.Button
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem
import ugh_technologies.universalgamehelper.Timer.TimerFragment


class StartActivity : AppCompatActivity(), Drawer.OnDrawerItemClickListener {

    lateinit var drawer: Drawer
    val fragmentManager:FragmentManager = supportFragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        //fragment shit
        val fragment = DefaultFragment()
        fragmentManager.beginTransaction().add(R.id.fragment_container, fragment).commit()

        //config shit
        val res: Resources = resources
        val config = res.getStringArray(R.array.configuration)

        setupNavDrawer(config)
        initButtons()

    }

    private fun setupNavDrawer(configuration:Array<String>) {

        drawer = DrawerBuilder()
                .withActivity(this)
                .withTranslucentStatusBar(false)
                .withOnDrawerItemClickListener { view, position, drawerItem -> onItemClick(view,position,drawerItem) }
                .build()

        for (feature in configuration) {

            val item = PrimaryDrawerItem()
                    .withName(feature)
                    .withTag(feature)
            drawer.addItem(item)

        }
    }

    override fun onItemClick(view: View?, position: Int, drawerItem: IDrawerItem<*, *>?): Boolean {

        when(drawerItem?.tag) {
            "Timer" -> {val fragment = TimerFragment();
                        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)}
            "Counter" -> Log.i("onClickTest", "worked")
        }
        return true
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
