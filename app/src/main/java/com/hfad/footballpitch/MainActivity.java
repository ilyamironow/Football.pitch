package com.hfad.footballpitch;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase db;
    private Cursor cursor;

    boolean usertodatabase(String login) {
        //checks if user exists
        boolean ch = false;
        @SuppressLint("Recycle") Cursor cursor2 = db.query("PEOPLE",
                new String[]{"_id", "LOGIN", "PASSWORD"}, "LOGIN" + " = ?", new String[]{(login)}, null, null, null);
        if (cursor2.getCount() > 0)
            ch = true;
        return ch;
    }

    boolean checkforpwd(String login, String password) {
        boolean ch = false;
        @SuppressLint("Recycle") Cursor cursor1 = db.query("PEOPLE",
                new String[]{"_id", "LOGIN", "PASSWORD"}, "LOGIN" + " = ? AND PASSWORD = ?", new String[]{(login),(password)}, null, null, null);
        if (cursor1.getCount() > 0)
            ch = true;
        return ch;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SQLiteOpenHelper footballpitchDatabaseHelper = new FootballpitchDatabaseHelper(this);
        try {
            db = footballpitchDatabaseHelper.getWritableDatabase();
            cursor = db.query("PEOPLE",
                    new String[]{"_id", "LOGIN"},
                    null, null, null, null, null);
        } catch (SQLException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    //Called when the user clicks the button
    public void onClickLoginRegister(View view) {
        //Получить ссылку на EditText с username
        EditText user = findViewById(R.id.username);
        // Получить ссылку на EditText с password
        EditText pass = findViewById(R.id.password);

        if (usertodatabase(user.getText().toString())) {
            //есть такой логин уже
            if (checkforpwd(user.getText().toString(), pass.getText().toString())) {
                //логин есть и пароль верный
                Toast toast = Toast.makeText(this, "Nice seeing you again, " + user.getText().toString(), Toast.LENGTH_SHORT);
                toast.show();
                //переходим на новую активность
                Intent intent = new Intent(this, Main2Activity.class);
                startActivity(intent);
            } else { //логин есть, но пароль неверный
                Toast toast = Toast.makeText(this, "Wrong password, " + user.getText().toString(), Toast.LENGTH_SHORT);
                toast.show();
                //снова вводит пароль
            }
        } else { //такого логина не было, т.е. новый пользователь
            //добавить в базу данных
            FootballpitchDatabaseHelper.insertPerson(db, user.getText().toString(), pass.getText().toString());

            Toast toast = Toast.makeText(this, "Welcome new recruit, " + user.getText().toString(), Toast.LENGTH_SHORT);
            toast.show();
            //переходим на новую активность
            Intent intent = new Intent(this, Main2Activity.class);
            startActivity(intent);
        }

    }
}
