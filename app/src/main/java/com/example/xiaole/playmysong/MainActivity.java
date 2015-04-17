package com.example.xiaole.playmysong;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class MainActivity extends ActionBarActivity {

    public String songName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        songName ="ridersinblack";
        Button playButton = (Button)findViewById(R.id.button);
        playButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                    playSong();
            }
        });
    }

    public void playSong(){
       copy2sdcard();
       Intent intent = new Intent("MyCustomIntent");
        // add data to the Intent
       intent.putExtra("name", songName);
       intent.setAction("com.example.xiaole.BOOT_COMPLETED");
       sendBroadcast(intent);
    }
    private void copy2sdcard() {
        final int songID = R.raw.ridersinblack;

        String path = Environment.getExternalStorageDirectory() + "/mySongs";
        File dir = new File(path);
        if (dir.mkdirs() || dir.isDirectory()) {
            path = path + "/" + songName;
        }
        File file = new File(path);
        if (file.exists()){
            Log.i("File already exist", songName);
        }
        else {
            InputStream in = getResources().openRawResource(songID);
            FileOutputStream out = null;
            try {
                out = new FileOutputStream(path);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            byte[] buff = new byte[1024];
            int read = 0;
            try {
                while ((read = in.read(buff)) > 0) {
                    out.write(buff, 0, read);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
