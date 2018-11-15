package ugh_technologies.universalgamehelper

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.Gravity
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        setupNavDrawer()


    }

    private fun setupNavDrawer() {
        val item1:PrimaryDrawerItem = PrimaryDrawerItem().withName("Test1")
        val item2:SecondaryDrawerItem = SecondaryDrawerItem().withName("Test2")
        val drawer = DrawerBuilder()
                .withActivity(this)
                .withTranslucentStatusBar(false)
                .addDrawerItems(item1, item2)
                .withFullscreen(false)
                .build()

    }
}
