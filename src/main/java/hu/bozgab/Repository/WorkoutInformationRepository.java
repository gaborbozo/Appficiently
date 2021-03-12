package hu.bozgab.Repository;

import hu.bozgab.Entity.WorkoutInformation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface WorkoutInformationRepository extends CrudRepository<WorkoutInformation,Long> {

    @Query("SELECT max(item.id) FROM WorkoutInformation item")
    Long findMaxId();

    WorkoutInformation findById(long id);

}
