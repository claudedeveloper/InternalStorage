package com.example.internalstorage;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        File f = getFilesDir();
        String path = f.getAbsolutePath();
        UIHelper.displayText(this, R.id.textView,path);
    }

    public void createFile(View v) throws IOException {
        String text = UIHelper.getText(this, R.id.editText);

        FileOutputStream fos = openFileOutput("mylove", MODE_PRIVATE);
        fos.write(text.getBytes());
        fos.close();

        UIHelper.displayText(this, R.id.textView, "File written to disk!");
    }

    public void readFile(View v) throws IOException {
        FileInputStream fis = openFileInput("mylove");
        BufferedInputStream bis = new BufferedInputStream(fis);
        StringBuffer b = new StringBuffer();
        while(bis.available() != 0) {
            char c = (char) bis.read();
            b.append(c);
        }

        UIHelper.displayText(this, R.id.textView, b.toString());
        bis.close();
        fis.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
