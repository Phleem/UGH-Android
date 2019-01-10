package ugh_technologies.universalgamehelper;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.io.File;

import ugh_technologies.universalgamehelper.Logging.Logger;
import ugh_technologies.universalgamehelper.Timer.TimerFragment;
import ugh_technologies.universalgamehelper.Counter.CounterFragment;
import ugh_technologies.universalgamehelper.Dice.DiceFragment;


public class StartActivity extends AppCompatActivity implements Drawer.OnDrawerItemClickListener {

    private Drawer drawer;
    FragmentManager fragmentManager = getSupportFragmentManager();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        //fragment shit
        Fragment fragment = new DefaultFragment();
        fragmentManager.beginTransaction().add(R.id.fragment_container, fragment).commit();

        //#ifdef LOGGING
//@        isStoragePermissionGranted();
//@        File file = new File("sdcard/log.file");
//@        file.delete();
        //#endif


        setupNavDrawer();
        initButtons();

    }

    private void setupNavDrawer() {

        drawer = new DrawerBuilder()
                .withActivity(this)
                .withTranslucentStatusBar(false)
                .withOnDrawerItemClickListener(this)
                .build();

        PrimaryDrawerItem emptyItem = new PrimaryDrawerItem();
        drawer.addItem(emptyItem);
        drawer.addItem(emptyItem);

        PrimaryDrawerItem item;
        //#ifdef COUNTER
//@            item = new PrimaryDrawerItem()
//@                    .withName("Counter")
//@                    .withTag("Counter");
//@            drawer.addItem(item);
        //#endif

        //#ifdef DICE
            item = new PrimaryDrawerItem()
                    .withName("Dice")
                    .withTag("Dice");
            drawer.addItem(item);
        //#endif

        //#ifdef TIMER
//@            item = new PrimaryDrawerItem()
//@                    .withName("Timer")
//@                    .withTag("Timer");
//@            drawer.addItem(item);
        //#endif

    }

    public boolean onItemClick(View view, int position, IDrawerItem drawerItem){
        Fragment fragment = null;
        switch (drawerItem.getTag().toString()) {

            //#ifdef COUNTER
//@            case "Counter"  : fragment = new CounterFragment();
//@                fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
//@                    break;
            //#endif

            //#ifdef DICE
            case "Dice" :  fragment = new DiceFragment();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
                break;
            //#endif

            //#ifdef TIMER
//@            case "Timer" :  fragment = new TimerFragment();
//@                fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
//@                    break;
            //#endif
        }


        drawer.closeDrawer();
        return true;
    }


    private void initButtons(){
        Button donateButton = findViewById(R.id.donate_button);
        donateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://paypal.me/DanielMicheel");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);

                startActivity(intent);
            }
        });

        Button shareButton = findViewById(R.id.share_button);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Universal Game Helper - Report");
                    shareIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
                    shareIntent.setType("text/plain");
                    startActivity(Intent.createChooser(shareIntent, "Share via"));
            }
        });
    }

    public static void logAction(String text){
        //#ifdef LOGGING
//@        Logger logger = new Logger();
//@        logger.writeToLog(text);
        //#endif
    }

    private boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                ActivityCompat.requestPermissions(this, permissions, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            return true;
        }
    }

}
