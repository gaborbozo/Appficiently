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
    public void setWorkoutService(WorkoutService workoutService) { this.workoutService = workoutService; }

    @Autowired
    public void setWorkout_informationRepository(WorkoutInformationRepository workout_informationRepository) { this.workout_informationRepository = workout_informationRepository; }

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

        @SuppressWarnings("unchecked")
        String workoutName = (String) session.getAttribute("WORKOUT_NAME");
        if(workoutName == null){
            workoutName = "Edzés";
            session.setAttribute("WORKOUT_NAME", workoutName);
        }

        model.addAttribute("workouts", workouts);
        model.addAttribute("workout", new Workout());

        model.addAttribute("exercises",workoutService.getAllExercises());

        model.addAttribute("workoutName", workoutName);
        model.addAttribute("workoutInformation", new WorkoutInformation());

        return "workout/manageWorkout.html";
    }

    @RequestMapping("/loadWorkout")
    public String loadWorkout(){
        //Betölt id alapján amit a myworkouts ad át, globális változóban eltárol és a validateManageWorkoutnál a globális változó értékét figyelembe véve adunk a workout táblához edzést
        return "redirect:/myWorkouts";
    }

    @RequestMapping("/validateManageWorkout")
    public String validateManageWorkout(@AuthenticationPrincipal UserDetailsImpl userDetails, HttpSession session) {

        @SuppressWarnings("unchecked")
        List<Workout> workouts = (List<Workout>) session.getAttribute("WORKOUT_MANAGER");

        if(workouts == null) return "redirect:/manageWorkout";

        Long currentWorkoutId = (Long)session.getAttribute("WORKOUT_ID");

        WorkoutInformation workoutInformation;
        if(currentWorkoutId == null) {
            workoutInformation = new WorkoutInformation(workoutService.getCurrentUser(userDetails.getId()),(String)session.getAttribute("WORKOUT_NAME"),new Date());
        } else {
            workoutInformation = workoutService.findWorkoutInformationById(currentWorkoutId.longValue());
        }

        workoutInformation.setWorkouts(workouts);

        workoutService.insertWorkout(workoutInformation);

        currentWorkoutId = null;
        workouts.clear();

        return "redirect:/myWorkouts";
    }

    @PostMapping("/modifyWorkoutName")
    public String modifyWorkoutName(HttpSession session, @ModelAttribute WorkoutInformation workoutInformation) {

        if(workoutInformation.getName().length() == 0) return "redirect:/manageWorkout";
        session.setAttribute("WORKOUT_NAME", workoutInformation.getName());

        return "redirect:/manageWorkout";
    }

    @RequestMapping("/addWorkoutItem")
    public String addWorkoutItem(HttpSession session, @RequestParam(name = "id") int id){

        @SuppressWarnings("unchecked")
        List<Workout> workouts = (List<Workout>) session.getAttribute("WORKOUT_MANAGER");
        if(workouts == null) return "redirect:/manageWorkout";
        if(id >= 0 && id <= workouts.size()) {
            workouts.add(id, new Workout(workoutService.getAllExercises().get(0)));
        }

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
    public String test(@AuthenticationPrincipal UserDetailsImpl userDetails, HttpSession session){
        System.out.println("___________TEST___________TEST___________TEST___________TEST___________TEST___________TEST___________TEST___________TEST___________TEST___________TEST___________TEST___________TEST___________");

        return "redirect:/index";
    }
}
