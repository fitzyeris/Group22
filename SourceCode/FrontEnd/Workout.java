package com.example.boxing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.FirebaseDatabase;

public class Workout{
	final FirebaseDatabase database = FirebaseDatabase.getInstance();
  private DatabaseReference ref = database.getReference("server/saving-data/fireblog");
	DatabaseReference workoutRef = ref.child("data");

	// A stack is used so that the most recent workout data is at the top of the list
	Stack<Workout> workoutHistory = new Stack<Workout>();

	public long startTime, elapsedTime, elapsedSeconds, secondsDisplay, elapsedMinutes;
	public String workoutType;
	public String typeOfPunch;
	public int numPunches;
	public int difficulty;

	public Workout(long startTime){
		this.startTime = startTime;
	}

	public void getWorkoutTimes(){
		this.elapsedTime = System.currentTimeMillis() - startTime;
		this.elapsedSeconds = elapsedTime / 1000;
		this.secondsDisplay = elapsedSeconds % 60;
		this.elapsedMinutes = elapsedSeconds / 60;
	}

	// Saves data to database
	public void saveWorkout(Workout data){
		workoutHistory.push(data);
		usersRef.setValueAsync(workoutHistory);
	}

}
