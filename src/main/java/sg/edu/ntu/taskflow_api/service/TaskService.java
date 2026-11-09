package sg.edu.ntu.taskflow_api.service;

import org.springframework.stereotype.Service;
import sg.edu.ntu.taskflow_api.repository.TaskRepository;
import sg.edu.ntu.taskflow_api.model.Task;
import java.util.List;
import org.springframework.ai.chat.client.ChatClient;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final ChatClient chatClient;

    public TaskService(
            TaskRepository taskRepository,
            ChatClient.Builder chatClientBuilder) {
        this.taskRepository = taskRepository;
        this.chatClient = chatClientBuilder.build();
    }

    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }

    public Task findTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public Task createTask(Task task) {
        task.setId(null);
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task task) {
        Task existing = taskRepository.findById(id).orElse(null);
        if (existing == null) {
            return null;
        }
        existing.setTitle(task.getTitle());
        existing.setDescription(task.getDescription());
        existing.setCompleted(task.isCompleted());
        return taskRepository.save(existing);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public Task markTaskAsComplete(Long id) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task == null) {
            return null;
        }
        task.setCompleted(true);
        return taskRepository.save(task);
    }

    public String summarizeTasks() {
        String taskList = findAllTasks().stream()
                .map(task -> String.format(
                        "%s — %s",
                        task.getTitle(),
                        task.isCompleted() ? "done" : "pending"))
                .collect(Collectors.joining("\n"));

        return chatClient.prompt()
                .user("Give a short plain-English summary of these tasks. "
                        + "If there are no tasks, say so.\n" + taskList)
                .call()
                .content();
    }
}
