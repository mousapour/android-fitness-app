package com.example.android_fitness_app.ui.workout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_fitness_app.Model.Exercise;
import com.example.android_fitness_app.Model.ExerciseSet;
import com.example.android_fitness_app.Model.Workout;
import com.example.android_fitness_app.R;
import com.example.android_fitness_app.databinding.FragmentWorkoutBinding;

import java.util.ArrayList;

public class WorkoutFragment extends Fragment {

    private FragmentWorkoutBinding binding;
    private Button addWorkoutButton;
    private RecyclerView workoutRecyclerView;
    private WorkoutRecyclerViewAdapter adapter;
    private ArrayList<WorkoutRecyclerViewAdapter.WorkoutListItem> workoutListItems;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        WorkoutViewModel workoutViewModel =
                new ViewModelProvider(this).get(WorkoutViewModel.class);

        binding = FragmentWorkoutBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addWorkoutButton = view.findViewById(R.id.addWorkoutButton);
        workoutRecyclerView = view.findViewById(R.id.workoutRecyclerView);
        workoutRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        workoutListItems = new ArrayList<>();
//        ArrayList<Workout> workouts = Workout.getWorkouts();
//        for (Workout workout : workouts) {
//            workoutListItems.add(new WorkoutRecyclerViewAdapter.WorkoutListItem(workout));
//        }
        Workout workout = new Workout();
        Exercise exercise = new Exercise("test-1", "info");
        ExerciseSet set = new ExerciseSet(exercise);
        set.setReps(12);
        set.setWeight(12.5);
        workout.addSet(exercise.getName(), set);
        set = new ExerciseSet(exercise);
        set.setReps(10);
        set.setWeight(20);
        workout.addSet(exercise.getName(), set);
        set = new ExerciseSet(exercise);
        set.setReps(1);
        set.setWeight(50);
        workout.addSet(exercise.getName(), set);
        workoutListItems.add(new WorkoutRecyclerViewAdapter.WorkoutListItem(workout));
        workoutListItems.add(new WorkoutRecyclerViewAdapter.WorkoutListItem(workout));
        workoutListItems.add(new WorkoutRecyclerViewAdapter.WorkoutListItem(workout));

        adapter = new WorkoutRecyclerViewAdapter(getActivity(), workoutListItems);
        workoutRecyclerView.setAdapter(adapter);

        addWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchActivities();
            }
        });
    }

    private void switchActivities() {
        Intent switchActivityIntent = new Intent(getActivity(), AddWorkoutActivity.class);
        startActivity(switchActivityIntent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}