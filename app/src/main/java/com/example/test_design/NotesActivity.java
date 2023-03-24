package com.example.test_design;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class NotesActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    private ListView notesListView;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        drawerLayout = findViewById(R.id.drawer_activity);

        notesListView = findViewById(R.id.notes_list_view);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        notesListView.setAdapter(adapter);

        notesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String fileName = adapter.getItem(position);
                if (fileName != null) {
                    displayNotePopup(fileName);
                }
            }
        });

        notesListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String fileName = adapter.getItem(position);
                if (fileName != null) {
                    confirmDeleteFile(fileName);
                }
                return true;
            }
        });

        loadSavedNotes();

    }

    private void loadSavedNotes() {
        String[] savedNotes = fileList();
        for (String note : savedNotes) {
                adapter.add(note);
        }
    }

    private void confirmDeleteFile(String fileName) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete note file?");
        builder.setMessage("Are you sure you want to delete the note file \"" + fileName + "\"?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteNoteFile(fileName);
            }
        });

        builder.setNegativeButton("No", null);
        builder.show();
    }

    private void deleteNoteFile(String fileName) {
        if (deleteFile(fileName)) {
            adapter.remove(fileName);
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "Note file deleted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error deleting note file", Toast.LENGTH_SHORT).show();
        }
    }


    private void displayNotePopup(String fileName) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(fileName);

        try {
            FileInputStream fis = openFileInput(fileName);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);

            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }

            builder.setMessage(sb.toString());

            br.close();
            isr.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
            builder.setMessage("Error reading note file");
        }

        builder.setPositiveButton("OK", null);
        builder.show();
    }

    public void saveNoteButtonClicked(View view) {
        EditText noteEditText = findViewById(R.id.note_edit_text);
        String noteText = noteEditText.getText().toString().trim();

        if (noteText.isEmpty()) {
            Toast.makeText(this, "Note is empty", Toast.LENGTH_SHORT).show();
            return;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter note file name:");

        final EditText input = new EditText(this);
        builder.setView(input);

        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String fileName = input.getText().toString().trim();
                if (fileName.isEmpty()) {
                    Toast.makeText(NotesActivity.this, "File name cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    FileOutputStream fos = openFileOutput(fileName, MODE_PRIVATE);
                    OutputStreamWriter osw = new OutputStreamWriter(fos);
                    osw.write(noteText);

                    osw.close();
                    fos.close();

                    adapter.add(fileName);
                    adapter.notifyDataSetChanged();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(NotesActivity.this, "Error saving note file", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setNegativeButton("Cancel", null);
        builder.show();
    }
}


