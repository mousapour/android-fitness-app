package com.example.android_fitness_app.ui.workout;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.android_fitness_app.Model.Workout;
import com.example.android_fitness_app.R;
import com.example.android_fitness_app.databinding.ActivityAddWorkoutBinding;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class AddWorkoutActivity extends AppCompatActivity {

    private Workout workout;
    private ActivityAddWorkoutBinding binding;
    private TextView workoutNameTextView;
    private LinearLayoutCompat exercisesLinearLayout;
    private Button addExerciseButton;
    private Button finishWorkoutButton;
    private Button cancelWorkoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddWorkoutBinding.inflate(getLayoutInflater());
        setTitle("New Workout");
        setContentView(binding.getRoot());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        View root = binding.getRoot();

        workout = new Workout();
        workoutNameTextView = (TextView) root.findViewById(R.id.workoutNameTextView);
        exercisesLinearLayout = (LinearLayoutCompat) root.findViewById(R.id.addExercisesLinearLayout);
        addExerciseButton = (Button) root.findViewById(R.id.addExerciseButton);
        finishWorkoutButton = (Button) root.findViewById(R.id.finishWorkoutButton);
        cancelWorkoutButton = (Button) root.findViewById(R.id.cancelWorkoutButton);

        workoutNameTextView.setText(workout.getName());
        addExerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ExerciseFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.add(exercisesLinearLayout.getId(), fragment, "tag");
                transaction.commit();
            }
        });
        finishWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialAlertDialogBuilder alertDialogBuilder = new
                        MaterialAlertDialogBuilder(AddWorkoutActivity.this)
                        .setTitle("Finish Workout")
                        .setMessage("Are you sure?");
                alertDialogBuilder.setNegativeButton("No", (dialog, which) -> {
                });
                alertDialogBuilder.setPositiveButton("Yes", (dialog, which) -> {
                    saveWorkout();
                    finish();
                });
                alertDialogBuilder.show();
            }
        });
        cancelWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialAlertDialogBuilder alertDialogBuilder = new
                        MaterialAlertDialogBuilder(AddWorkoutActivity.this)
                        .setTitle("Cancel Workout")
                        .setMessage("Are you sure?");
                alertDialogBuilder.setNegativeButton("No", (dialog, which) -> {
                });
                alertDialogBuilder.setPositiveButton("Yes", (dialog, which) -> finish());
                alertDialogBuilder.show();
            }
        });
    }

    private void saveWorkout() {
        int exerciseCount = exercisesLinearLayout.getChildCount();
        for (int i = 0; i < exerciseCount; i++) {
            // TODO: save workout and add to workouts
        }
    }
}
