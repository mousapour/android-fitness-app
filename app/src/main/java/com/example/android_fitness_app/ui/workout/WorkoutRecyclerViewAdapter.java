package com.example.android_fitness_app.ui.workout;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_fitness_app.Model.ExerciseSet;
import com.example.android_fitness_app.Model.Workout;
import com.example.android_fitness_app.R;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

public class WorkoutRecyclerViewAdapter extends RecyclerView.Adapter<WorkoutRecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<WorkoutListItem> list;

    public WorkoutRecyclerViewAdapter(Context context, List<WorkoutListItem> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(
                R.layout.workout_recyclerview_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.workoutTextView.setText(list.get(position).getWorkoutName());
        holder.dateTextView.setText(list.get(position).getDate());
        holder.volumeTextView.setText(list.get(position).getVolume());
        // set exercises views in exercisesLinearLayout
        LinkedHashMap<String, ArrayList<ExerciseSet>> exercises = list.get(position).getExercises();
        Set<String> keys = exercises.keySet();
        for (String key : keys) {
            ArrayList<ExerciseSet> sets = exercises.get(key);
            if (sets != null) {
                String leftText = sets.size() + " * " + key;
                String rightText = ExerciseSet.getBestSetString(sets);
                TextView leftTextView = new TextView(context);
                TextView rightTextView = new TextView(context);
                leftTextView.setText(leftText);
                rightTextView.setText(rightText);
                rightTextView.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
                ViewGroup.LayoutParams params = new LinearLayoutCompat.LayoutParams(
                        0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
                leftTextView.setLayoutParams(params);
                rightTextView.setLayoutParams(params);
                leftTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
                rightTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
                leftTextView.setTextColor(Color.BLACK);
                rightTextView.setTextColor(Color.BLACK);
                // put TextViews next to each other
                LinearLayoutCompat textViewsLinearLayout = new LinearLayoutCompat(context);
                textViewsLinearLayout.setLayoutParams(new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                textViewsLinearLayout.setOrientation(LinearLayoutCompat.HORIZONTAL);
                textViewsLinearLayout.addView(leftTextView);
                textViewsLinearLayout.addView(rightTextView);
                // add TextViews to linearLayout
                holder.exercisesLinearLayout.addView(textViewsLinearLayout);
            }
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView workoutTextView, dateTextView, volumeTextView;
        LinearLayoutCompat exercisesLinearLayout;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            workoutTextView = itemView.findViewById(R.id.workoutNameTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            volumeTextView = itemView.findViewById(R.id.volumeTextView);
            exercisesLinearLayout = itemView.findViewById(R.id.exercisesLinearLayout);
            cardView = itemView.findViewById(R.id.mainContainerCardView);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class WorkoutListItem {
        private final Workout workout;
        private final String workoutName;
        private final String date;
        private final String volume;
        private final LinkedHashMap<String, ArrayList<ExerciseSet>> exercises;

        @RequiresApi(api = Build.VERSION_CODES.O)
        public WorkoutListItem(Workout workout) {
            this.workout = workout;
            this.workoutName = workout.getName();
            this.date = workout.getDateString();
            this.volume = workout.getVolume() + " kg";
            this.exercises = workout.getExercises();
        }

        public String getWorkoutName() {
            return workoutName;
        }

        public String getDate() {
            return date;
        }

        public String getVolume() {
            return volume;
        }

        public LinkedHashMap<String, ArrayList<ExerciseSet>> getExercises() {
            return exercises;
        }
    }
}
