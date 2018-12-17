package ugh_technologies.universalgamehelper;

import android.content.Intent;
import android.net.Uri;
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

import ugh_technologies.universalgamehelper.Notes.NotesFragment;
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
            item = new PrimaryDrawerItem()
                    .withName("Counter")
                    .withTag("Counter");
            drawer.addItem(item);
        //#endif

        //#ifdef DICE
            item = new PrimaryDrawerItem()
                    .withName("Dice")
                    .withTag("Dice");
            drawer.addItem(item);
        //#endif

        //#ifdef TIMER
            item = new PrimaryDrawerItem()
                    .withName("Timer")
                    .withTag("Timer");
            drawer.addItem(item);
        //#endif

        //#ifdef NOTES
        item = new PrimaryDrawerItem()
                .withName("Notes")
                .withTag("Notes");
        drawer.addItem(item);
        //#endif
    }

    public boolean onItemClick(View view, int position, IDrawerItem drawerItem){
        Fragment fragment = null;
        switch (drawerItem.getTag().toString()) {

            //#ifdef COUNTER
            case "Counter"  : fragment = new CounterFragment();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
            //#endif

            //#ifdef DICE
            case "Dice" :  fragment = new DiceFragment();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
            //#endif

            //#ifdef TIMER
            case "Timer" :  fragment = new TimerFragment();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
            //#endif

            // #ifdef NOTES
                case "Notes" :  fragment = new NotesFragment();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
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
}
