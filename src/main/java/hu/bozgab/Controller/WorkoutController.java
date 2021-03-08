package hu.bozgab.Controller;

import hu.bozgab.Entity.Exercise;
import hu.bozgab.Entity.Workout;
import hu.bozgab.Service.Interface.IWorkoutService;
import hu.bozgab.Service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class WorkoutController {

    int counter = 1;

    @Qualifier("WorkoutService")
    IWorkoutService workoutService;

    @Autowired
    public void setWorkoutService(WorkoutService workoutService) { this.workoutService = workoutService; }

    @RequestMapping("/createExercise")
    public String createExercise(Model model){

        model.addAttribute("exercise",new Exercise());

        return "workout/createExercise.html";
    }

    @PostMapping("/validateCreateExercise")
    public String validateCreateExercise(@ModelAttribute Exercise exercise){

        workoutService.saveExercise(exercise);

        return "redirect:/createExercise";
    }

    @RequestMapping("/manageWorkout")
    public String createWorkout(Model model, HttpSession session){

        @SuppressWarnings("unchecked")
        List<Workout> workouts = (List<Workout>) session.getAttribute("WORKOUT_MANAGER");
        if(workouts == null) {
            workouts = new ArrayList<>();
            session.setAttribute("WORKOUT_MANAGER", workouts);
        }

        model.addAttribute("workouts", workouts);

        return "workout/manageWorkout.html";
    }

    @RequestMapping("/addWorkoutItem")
    public String create(HttpSession session, @RequestParam(name = "id") int id){

        System.out.println("ITT VAN TESSÉK");
        System.out.println(id);


        @SuppressWarnings("unchecked")
        List<Workout> workouts = (List<Workout>) session.getAttribute("WORKOUT_MANAGER");

        if(id >= 0 && id <= workouts.size()) workouts.add(id, new Workout(String.valueOf(counter)));
counter++;
        return "redirect:/manageWorkout";
    }


}
