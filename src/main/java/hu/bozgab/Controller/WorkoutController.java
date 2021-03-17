package hu.bozgab.Controller;

import hu.bozgab.Entity.Exercise;
import hu.bozgab.Entity.User;
import hu.bozgab.Entity.Workout;
import hu.bozgab.Entity.WorkoutInformation;
import hu.bozgab.Repository.ExerciseRepository;
import hu.bozgab.Repository.UserRepository;
import hu.bozgab.Repository.WorkoutRepository;
import hu.bozgab.Repository.WorkoutInformationRepository;
import hu.bozgab.Service.Interface.IWorkoutService;
import hu.bozgab.Service.UserDetailsImpl;
import hu.bozgab.Service.WorkoutManager;
import hu.bozgab.Service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class WorkoutController {

    @Qualifier("WorkoutService")
    IWorkoutService workoutService;

    private WorkoutManager workoutManager;

    ExerciseRepository er;
    WorkoutRepository wr;
    UserRepository ur;
    WorkoutInformationRepository workout_informationRepository;

    @Autowired
    public void setUr(UserRepository ur) { this.ur = ur; }

    @Autowired
    public void setEr(ExerciseRepository er) { this.er = er; }

    @Autowired
    public void setEr(WorkoutRepository wr) { this.wr = wr; }

    @Autowired
    public void setWorkout_informationRepository(WorkoutInformationRepository workout_informationRepository) { this.workout_informationRepository = workout_informationRepository; }

    @Autowired
    public void setWorkoutService(WorkoutService workoutService) { this.workoutService = workoutService; }

    @Autowired
    public void setWorkoutManager(WorkoutManager workoutManager) { this.workoutManager = workoutManager; }

    @RequestMapping("/createExercise")
    public String createExercise(Model model){

        model.addAttribute("exercise",new Exercise());

        return "workout/createExercise.html";
    }

    @PostMapping("/validateCreateExercise")
    public String validateCreateExercise(@ModelAttribute Exercise exercise, @AuthenticationPrincipal UserDetailsImpl userDetails){

        workoutService.saveExercise(exercise);

        return "redirect:/createExercise";
    }

    @RequestMapping("/manageWorkout")
    public String manageWorkout(Model model,@AuthenticationPrincipal UserDetailsImpl userDetails){

        model.addAttribute("workouts", workoutManager.getWorkouts());
        model.addAttribute("workout", new Workout());

        model.addAttribute("exercises",workoutService.getExercises());

        model.addAttribute("workoutName", workoutManager.getWorkoutName());
        model.addAttribute("workoutInformation", new WorkoutInformation());

        return "workout/manageWorkout.html";
    }

    @RequestMapping("/loadWorkout")
    public String loadWorkout(@RequestParam(name = "id") long id){


        if(workoutManager.getWorkouts().size() != 0)  return "redirect:/myWorkouts?activeManagement";

        WorkoutInformation workoutInformation = workoutService.findWorkoutInformationById(id);

        workoutManager.setWorkoutId(workoutInformation.getId());
        workoutManager.setWorkoutName(workoutInformation.getName());
        workoutManager.setWorkouts(workoutInformation.getWorkouts());

        return "redirect:/manageWorkout";
    }

    @PostMapping("/validateManageWorkout")
    public String validateManageWorkout(@AuthenticationPrincipal UserDetailsImpl userDetails) {


        if(workoutManager.getWorkouts().size() == 0) return "redirect:/manageWorkout?error";
        if(workoutManager.getWorkoutName().length() == 0) return "redirect:/manageWorkout?errorName";

        workoutService.saveWorkout(workoutManager, workoutService.getCurrentUser(userDetails.getUser()));

        workoutManager = new WorkoutManager();

        return "redirect:/myWorkouts";
    }

    @PostMapping("/modifyWorkoutName")
    public String modifyWorkoutName(@ModelAttribute WorkoutInformation workoutInformation) {

        workoutManager.setWorkoutName(workoutInformation.getName());

        return "redirect:/manageWorkout";
    }

    @RequestMapping("/addWorkoutItem")
    public String addWorkoutItem(@RequestParam(name = "id") int id,@AuthenticationPrincipal UserDetailsImpl userDetails){

        if(id >= 0 && id <= workoutManager.getWorkouts().size()) {
            Workout workout = new Workout(workoutService.getExercises().get(0));
            workoutManager.addWorkout(workout, id);
        }

        return "redirect:/manageWorkout";
    }

    @RequestMapping("/removeWorkoutItem")
    public String removeWorkoutItem(@RequestParam(name = "id") int id) {

        if(id >= 0 && id < workoutManager.getWorkouts().size() && workoutManager.getWorkouts().size() != 0) workoutManager.removeWorkout(id);
        if(workoutManager.getWorkouts().size() == 0) workoutManager = new WorkoutManager();

        return "redirect:/manageWorkout";
    }

    @RequestMapping("/saveWorkoutItemToList")
    public String saveWorkoutItemToList(@ModelAttribute Workout workout, @RequestParam(name = "id") int id) {

        if(id >= 0 && id < workoutManager.getWorkouts().size()) {
            if(workout.getCount() < 1 || workout.getCount() > 250) workout.setCount(1);
            if(workout.getComment() == "") workout.setComment(null);
            workoutManager.saveWorkout(workout, id);
        }

        return "redirect:/manageWorkout";
    }

    @RequestMapping("/myWorkouts")
    public String myWorkouts(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails){

        User user = workoutService.getCurrentUser(userDetails.getUser());
        model.addAttribute("workoutsInfromations", workoutService.getCurrentUser(userDetails.getUser()).getWorkoutInformation());

        return "workout/myWorkouts.html";
    }

    @RequestMapping("/test")
    public String test(@AuthenticationPrincipal UserDetailsImpl userDetails, HttpSession session){
        System.out.println("___________TEST___________TEST___________TEST___________TEST___________TEST___________TEST___________TEST___________TEST___________TEST___________TEST___________TEST___________TEST___________");



        return "redirect:/index";
    }
}
