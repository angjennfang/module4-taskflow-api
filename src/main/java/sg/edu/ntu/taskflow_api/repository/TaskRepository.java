// package sg.edu.ntu.taskflow_api.repository;

// import org.springframework.stereotype.Repository;
// import sg.edu.ntu.taskflow_api.model.Task;
// import java.util.List;
// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.Map;

// @Repository
// public class TaskRepository {
//     private final Map<Long, Task> tasks = new HashMap<>();
//     private long nextId = 1;

//     public Task save(Task task) {
//         if (task.getId() == null) {
//         task.setId(nextId++);
//         }
//         tasks.put(task.getId(), task);
//         return task;
//     }

//     public void deleteById(Long id) {
//         tasks.remove(id);
//     }
    
//     public List<Task> findAll() {
//         return new ArrayList<>(tasks.values());
//     }

//     public Task findById(Long id) {
//         return tasks.get(id);
//     }
// }

package sg.edu.ntu.taskflow_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.ntu.taskflow_api.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}