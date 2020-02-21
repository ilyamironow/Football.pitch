package com.hfad.footballpitch;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class Main2Activity extends AppCompatActivity {
    //private ShareActionProvider shareActionProvider;
    private FloatingActionButton fab;
    private SQLiteDatabase db;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        /*SQLiteOpenHelper footballpitchDatabaseHelper = new FootballpitchDatabaseHelper(this);
        try {
            db = footballpitchDatabaseHelper.getWritableDatabase();
            cursor = db.query("NOTES",
                    new String[]{"_id", "CLASS"},
                    null, null, null, null, null);
        } catch (SQLException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
        //public String[] f() {
            Cursor cursor1 = db.rawQuery("SELECT * FROM NOTES WHERE CLASS = ?", new String[]{("PLAYER")}, null);
            String[] array = new String[cursor1.getCount()];
            int i = 0;
            while (cursor1.moveToNext()) {
                String playernote = cursor1.getString(cursor1.getColumnIndex("NOTETEXT"));
                array[i] = playernote;
                i++;
            }*/
            //return array;
        //}
        //f();
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //Связывание SectionsPagerAdapter с ViewPager
        SectionsPagerAdapter pagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        ViewPager pager = findViewById(R.id.pager);
        pager.setAdapter(pagerAdapter);
        //Связывание ViewPager с TabLayout
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(pager);

        // init fab Button
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //add new note
                onAddNewNote();
            }
        });
        //return array;
    }


    private void onAddNewNote() {
        startActivity(new Intent(this, EditNoteActivity.class));
    }

    /*private void setShareActionIntent(String text) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        shareActionProvider.setShareIntent(intent);
    }*/

    private class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new PitchFragment();
                case 1:
                    return new PlayerFragment();
            }
            return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getResources().getText(R.string.player_tab);
                case 1:
                    return getResources().getText(R.string.pitch_tab);
            }
            return null;
        }
    }
}