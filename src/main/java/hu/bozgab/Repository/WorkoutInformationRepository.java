package hu.bozgab.Repository;

import hu.bozgab.Entity.WorkoutInformation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WorkoutInformationRepository extends CrudRepository<WorkoutInformation,Long> {

    WorkoutInformation findById(long id);

    List<WorkoutInformation> findAllByUserId(long id);

}
