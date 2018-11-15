package ugh_technologies.universalgamehelper

import android.content.res.Resources
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem


class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        val res: Resources = resources

        val config = res.getStringArray(R.array.configuration)

        setupNavDrawer(config)


    }

    private fun setupNavDrawer(configuration:Array<String>) {
        val drawer = DrawerBuilder()
                .withActivity(this)
                .withTranslucentStatusBar(false)
                .withFullscreen(false)
                .build()
        for (feature in configuration) {
            var item = PrimaryDrawerItem().withName(feature)
            drawer.addItem(item)
        }
    }
}
