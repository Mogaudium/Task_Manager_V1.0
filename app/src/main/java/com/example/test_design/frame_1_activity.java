
package com.example.test_design;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

	public class frame_1_activity extends AppCompatActivity {
		DrawerLayout drawerLayout;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		/*
		//Hide default android studio toolbar
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getSupportActionBar().hide();
		*/

		setContentView(R.layout.activity_main);

		drawerLayout = findViewById(R.id.drawer);

	}

	public void ClickMenu(View view){
		openDrawer(drawerLayout);
	}

	public static void openDrawer(DrawerLayout drawerLayout){
		drawerLayout.openDrawer(GravityCompat.START);
	}

	public void ClickTasks(View view){
		redirectActivity(this, TasksActivity.class);
	}

	public void ClickNotes(View view){
		redirectActivity(this, NotesActivity.class);
	}

	public static void redirectActivity(Activity activity, Class Class){
		Intent intent = new Intent(activity,Class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		activity.startActivity(intent);
	}

	}
	
	