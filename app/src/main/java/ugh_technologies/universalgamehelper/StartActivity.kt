package ugh_technologies.universalgamehelper

import android.Manifest
import android.content.Intent
import android.content.res.Resources
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.view.View
import android.widget.Button
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem
import ugh_technologies.universalgamehelper.Timer.TimerFragment
import ugh_technologies.universalgamehelper.Counter.CounterFragment
import ugh_technologies.universalgamehelper.Dice.DiceFragment
import android.Manifest.permission
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.support.v4.app.ActivityCompat
import android.content.pm.PackageManager
import android.os.Build
import java.io.File


class StartActivity : AppCompatActivity(), Drawer.OnDrawerItemClickListener {

    lateinit var drawer: Drawer
    val fragmentManager: FragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        //fragment shit
        val fragment = DefaultFragment()
        fragmentManager.beginTransaction().add(R.id.fragment_container, fragment).commit()

        if(FeatureConfiguration.logger) {
            isStoragePermissionGranted()
            val file = File("sdcard/log.file")
            file.delete()
        }

        setupNavDrawer()
        initButtons()



    }

    private fun setupNavDrawer() {

        drawer = DrawerBuilder()
                .withActivity(this)
                .withTranslucentStatusBar(false)
                .withOnDrawerItemClickListener { view, position, drawerItem -> onItemClick(view,position,drawerItem) }
                .build()

        val emptyItem = PrimaryDrawerItem()
        drawer.addItem(emptyItem)
        drawer.addItem(emptyItem)

        if (FeatureConfiguration.counter){
            val item = PrimaryDrawerItem()
                    .withName("Counter")
                    .withTag("Counter")
            drawer.addItem(item)
        }

        if (FeatureConfiguration.dice){
            val item = PrimaryDrawerItem()
                    .withName("Dice")
                    .withTag("Dice")
            drawer.addItem(item)
        }

        if (FeatureConfiguration.timer){
            val item = PrimaryDrawerItem()
                    .withName("Timer")
                    .withTag("Timer")
            drawer.addItem(item)
        }
    }

    override fun onItemClick(view: View?, position: Int, drawerItem: IDrawerItem<*, *>?): Boolean {

        when(drawerItem!!.tag){
            "Counter" -> { if (FeatureConfiguration.counter){val fragment = CounterFragment()
                fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()}}
            "Dice" -> { if (FeatureConfiguration.dice){ val fragment = DiceFragment()
                fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()}}
            "Timer" -> { if (FeatureConfiguration.timer){ val fragment = TimerFragment();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()}}
        }

        drawer.closeDrawer()
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

    fun isStoragePermissionGranted(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                return true
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
                return false
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            return true
        }
    }

    override fun onDestroy() {
        super.onDestroy()

    }
}
