package hu.bozgab.Controller;

import hu.bozgab.Entity.Exercise;
import hu.bozgab.Service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WorkoutController {

    WorkoutService workoutService;

    @Autowired
    public void setWorkoutService(WorkoutService workoutService) { this.workoutService = workoutService; }

    @RequestMapping("/create")
    public String create(){

        workoutService.savework(new Exercise("Fekvőtámasz"));

        return "index.html";
    }
}
