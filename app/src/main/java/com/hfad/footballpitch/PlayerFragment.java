package com.hfad.footballpitch;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import androidx.fragment.app.ListFragment;

public class PlayerFragment extends ListFragment {
    private SQLiteDatabase db;
    private Cursor cursor;
    //Main2Activity mnc = new Main2Activity();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        SQLiteOpenHelper footballpitchDatabaseHelper = new FootballpitchDatabaseHelper(getContext());
        db = footballpitchDatabaseHelper.getWritableDatabase();
        Cursor cursor1 = db.rawQuery("SELECT * FROM NOTES WHERE CLASS = ?", new String[]{("PITCH")}, null);
        String[] array = new String[cursor1.getCount()];
        int i = 0;
        while (cursor1.moveToNext()) {
            String playernote = cursor1.getString(cursor1.getColumnIndex("NOTETEXT"));
            array[i] = playernote;
            i++;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(inflater.getContext(),
                android.R.layout.simple_list_item_1,
                array);
        setListAdapter(adapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
