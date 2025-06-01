package com.example.filehandlinginternalstorage;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {
    Button createFile, writeFile, readFile, saveText, dontSave, closeEdittext;
    ListView filesList;
    LinearLayout data_container, writeButtonContainer, readButtonContainer;
    String fileName = "test.txt";
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        createFile = findViewById(R.id.create_file);
        writeFile = findViewById(R.id.write_file);
        readFile = findViewById(R.id.read_file);

        writeButtonContainer = findViewById(R.id.write_button);
        saveText = findViewById(R.id.save_text);
        dontSave = findViewById(R.id.dont_save_text);

        readButtonContainer = findViewById(R.id.read_button);
        closeEdittext = findViewById(R.id.close_edittext);

        filesList = findViewById(R.id.file_list);

        data_container = findViewById(R.id.multi_line_layout);

        File[] files = getFilesDir().listFiles();

        if (files != null)
            filesList.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Arrays.asList(files)));

        createFile.setOnClickListener(this::setCreateFile);
        writeFile.setOnClickListener(this::writeFile);
        readFile.setOnClickListener(this::readFile);

        saveText.setOnClickListener(this::saveText);
        dontSave.setOnClickListener(this::dontSave);

        closeEdittext.setOnClickListener(this::closeEdittext);

    }

    void setCreateFile(View view) {
        File file = new File(getFilesDir(), fileName);
        try {
            if (file.createNewFile())
                Toast.makeText(this, "File created: " + file.getName(), Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "File "+file.getName()+" already exists.", Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void addView() {
        data_container.removeView(editText);
        data_container.setVisibility(View.GONE);
        editText = new EditText(getApplicationContext());
        editText.setMinLines(4);
        editText.setMaxLines(4);
        editText.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        editText.setGravity(View.TEXT_ALIGNMENT_TEXT_START);
        data_container.addView(editText);
        data_container.setVisibility(View.VISIBLE);
    }

    void writeFile(View view) {
        if (data_container.getVisibility() == View.VISIBLE) {
            data_container.setVisibility(View.GONE);
        } else {
            addView();
            writeButtonContainer.setVisibility(View.VISIBLE);
            readButtonContainer.setVisibility(View.GONE);
        }
    }

    void saveText(View view) {
        String text = editText.getText().toString();
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = openFileOutput(fileName, MODE_PRIVATE);
            fileOutputStream.write(text.getBytes());
            Toast.makeText(this, "Text saved.", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    Toast.makeText(this, "Error closing file.", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        }
        data_container.removeView(editText);
        data_container.setVisibility(View.GONE);
    }

    void dontSave(View view) {
        data_container.removeView(editText);
        data_container.setVisibility(View.GONE);
    }

    void readFile(View view) {
        if (data_container.getVisibility() == View.VISIBLE) {
            data_container.setVisibility(View.GONE);
        } else {
            writeButtonContainer.setVisibility(View.GONE);
            readButtonContainer.setVisibility(View.VISIBLE);
            addView();
            FileInputStream fileInputStream = null;
            try {
                fileInputStream = openFileInput(fileName);
                StringBuilder text = new StringBuilder();
                int i;
                while ((i = fileInputStream.read()) != -1) {
                    text.append((char) i);
                }
                editText.setText(text.toString());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e) {
                        Toast.makeText(this, "Error closing file.", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    void closeEdittext(View view) {
        data_container.removeView(editText);
        data_container.setVisibility(View.GONE);
    }

}