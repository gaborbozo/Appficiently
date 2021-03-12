package hu.bozgab.Controller;

import hu.bozgab.Entity.Exercise;
import hu.bozgab.Entity.User;
import hu.bozgab.Entity.Workout;
import hu.bozgab.Repository.ExerciseRepository;
import hu.bozgab.Repository.UserRepository;
import hu.bozgab.Repository.WorkoutRepository;
import hu.bozgab.Service.Interface.IWorkoutService;
import hu.bozgab.Service.UserDetailsImpl;
import hu.bozgab.Service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Qualifier("WorkoutService")
    IWorkoutService workoutService;

    ExerciseRepository er;
    WorkoutRepository wr;
    UserRepository ur;

    //Long type needed because of null
    Long currentWorkoutId = null;

    @Autowired
    public void setUr(UserRepository ur) { this.ur = ur; }

    @Autowired
    public void setEr(ExerciseRepository er) { this.er = er; }

    @Autowired
    public void setEr(WorkoutRepository wr) { this.wr = wr; }

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
    public String manageWorkout(Model model, HttpSession session){

        @SuppressWarnings("unchecked")
        List<Workout> workouts = (List<Workout>) session.getAttribute("WORKOUT_MANAGER");
        if(workouts == null) {
            workouts = new ArrayList<>();
            session.setAttribute("WORKOUT_MANAGER", workouts);
        }

        model.addAttribute("workout", new Workout());
        model.addAttribute("workouts", workouts);
        model.addAttribute("sizeOfList",workouts.size());
        model.addAttribute("exercises",workoutService.getAllExercises());

        return "workout/manageWorkout.html";
    }

    @RequestMapping("/loadWorkout")
    public String loadWorkout(){
        //Betölt id alapján amit a myworkouts ad át, globális változóban eltárol és a validateManageWorkoutnál a globális változó értékét figyelembe véve adunk a workout táblához edzést
        return "workout/manageWorkout.html";
    }

    @RequestMapping("/validateManageWorkout")
    public String validateManageWorkout(HttpSession session,@AuthenticationPrincipal UserDetailsImpl userDetails) {

        @SuppressWarnings("unchecked")
        List<Workout> workouts = (List<Workout>) session.getAttribute("WORKOUT_MANAGER");

        if(workouts == null) return "redirect:/manageWorkout";

        if(currentWorkoutId == null) {
            Long maxWorkoutId = workoutService.getMaxWorkoutId();
            if(maxWorkoutId == null){
                currentWorkoutId = (long)0;
            } else {
                currentWorkoutId = maxWorkoutId + 1;
            }
        }

        User currentUser = workoutService.getCurrentUser(userDetails.getId());

        for (Workout item: workouts){
            item.setUser(currentUser);
            item.setWorkout_id(currentWorkoutId);

            workoutService.saveWorkout(item);
        }

        currentWorkoutId = null;
        workouts.clear();

        return "redirect:/myWorkouts";
    }

    @RequestMapping("/addWorkoutItem")
    public String addWorkoutItem(HttpSession session, @RequestParam(name = "id") int id){

        @SuppressWarnings("unchecked")
        List<Workout> workouts = (List<Workout>) session.getAttribute("WORKOUT_MANAGER");
        if(workouts == null) return "redirect:/manageWorkout";
        if(id >= 0 && id <= workouts.size()) workouts.add(id, new Workout(workoutService.getAllExercises().get(0)));

        return "redirect:/manageWorkout";
    }

    @RequestMapping("/removeWorkoutItem")
    public String removeWorkoutItem(HttpSession session, @RequestParam(name = "id") int id) {

        @SuppressWarnings("unchecked")
        List<Workout> workouts = (List<Workout>) session.getAttribute("WORKOUT_MANAGER");
        if(workouts == null) return "redirect:/manageWorkout";
        if(id >= 0 && id <= workouts.size()-1) workouts.remove(id);

        return "redirect:/manageWorkout";
    }

    @RequestMapping("/saveWorkoutItemToList")
    public String saveWorkoutItemToList(HttpSession session, @RequestParam(name = "id") int id, @ModelAttribute Workout workout) {

        @SuppressWarnings("unchecked")
        List<Workout> workouts = (List<Workout>) session.getAttribute("WORKOUT_MANAGER");
        if(workouts == null) return "redirect:/manageWorkout";
        if(id >= 0 && id <= workouts.size()) workouts.set(id, workout);

        return "redirect:/manageWorkout";
    }

    @RequestMapping("/myWorkouts")
    public String myWorkouts(){


        return "workout/myWorkouts.html";
    }

    @RequestMapping("/test")
    public String test(@AuthenticationPrincipal UserDetailsImpl userDetails){
        System.out.println("___________TEST___________TEST___________TEST___________TEST___________TEST___________TEST___________TEST___________TEST___________TEST___________TEST___________TEST___________TEST___________");

        List<Workout> workouts = wr.findAllByUser_id(userDetails.getId());

        for(Workout item: workouts) System.out.println(item.getId());
        return "redirect:/index";
    }
}
