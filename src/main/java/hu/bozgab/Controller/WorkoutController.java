package hu.bozgab.Controller;

import hu.bozgab.Entity.Exercise;
import hu.bozgab.Service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WorkoutController {

    WorkoutService workoutService;

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
}
