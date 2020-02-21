package com.hfad.footballpitch;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class EditNoteActivity extends AppCompatActivity {
    private SQLiteDatabase db;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        //FootballpitchDatabaseHelper.insertNote();
    }

    public void onClickfab(View view) {
        //Получить ссылку на EditText с username
        EditText text = findViewById(R.id.input_note);
        // Получить ссылку на Spinner
        Spinner color = findViewById(R.id.color);
        // Получить вариант, выбранный в Spinner
        String beerType = String.valueOf(color.getSelectedItem());
        SQLiteOpenHelper footballpitchDatabaseHelper = new FootballpitchDatabaseHelper(this);
        try {
            db = footballpitchDatabaseHelper.getWritableDatabase();
            cursor = db.query("NOTES",
                    new String[]{"_id", "CLASS"},
                    null, null, null, null, null);
        } catch (SQLException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
        if (beerType == "player") {
            FootballpitchDatabaseHelper.insertNote(db, "PLAYER", String.valueOf(text.getText()));
        } else {
            FootballpitchDatabaseHelper.insertNote(db, "PITCH", String.valueOf(text.getText()));
        }

    }
}
