package sg.edu.ntu.taskflow_api;

import jakarta.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.ntu.taskflow_api.model.Task;
import sg.edu.ntu.taskflow_api.repository.TaskRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void savesAndLoadsTaskFromDatabase() {
        Task task = new Task();
        task.setTitle("Database test");
        task.setDescription("Check PostgreSQL persistence");
        task.setCompleted(false);

        Task saved = taskRepository.saveAndFlush(task);
        Long id = saved.getId();

        assertNotNull(id);

        entityManager.clear();

        Task loaded = taskRepository.findById(id).orElseThrow();

        assertEquals("Database test", loaded.getTitle());
        assertEquals("Check PostgreSQL persistence", loaded.getDescription());
        assertFalse(loaded.isCompleted());
    }
}