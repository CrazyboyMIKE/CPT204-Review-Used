import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class Week05Answers {
    public static void main(String[] args) {
        System.out.println("Week 05 Answers");

        TodoManager manager = new TodoManager();
        Task planning = manager.addTask("Plan revision", Priority.HIGH);
        Task coding = manager.addTask("Implement lab answers", Priority.MEDIUM);
        Task reading = manager.addTask("Read lecture notes", Priority.LOW);

        manager.editTask(coding.getId(), "Implement Java answers", Priority.HIGH);
        manager.markComplete(reading.getId());
        manager.moveToProcessingQueue(planning.getId());
        manager.moveToProcessingQueue(coding.getId());
        manager.addToPriorityQueue(planning.getId());
        manager.addToPriorityQueue(coding.getId());

        manager.printAllTasks();
        manager.printTasksByStatus(false);
        manager.printProcessingQueue();
        manager.processNextTask();
        manager.printPriorityQueue();
        manager.processHighestPriorityTask();
        manager.printRecentActivities();
        manager.printOverview();
    }

    enum Priority {
        HIGH,
        MEDIUM,
        LOW
    }

    static class Task {
        private final int id;
        private String title;
        private boolean completed;
        private Priority priority;

        public Task(int id, String title, Priority priority) {
            this.id = id;
            this.title = title;
            this.priority = priority;
        }

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public boolean isCompleted() {
            return completed;
        }

        public void setCompleted(boolean completed) {
            this.completed = completed;
        }

        public Priority getPriority() {
            return priority;
        }

        public void setPriority(Priority priority) {
            this.priority = priority;
        }

        @Override
        public String toString() {
            return "Task{id=" + id + ", title='" + title + "', completed=" + completed
                    + ", priority=" + priority + "}";
        }
    }

    static class TodoManager {
        private final List<Task> tasks = new ArrayList<>();
        private final Queue<Task> processingQueue = new LinkedList<>();
        private final Stack<String> activityHistory = new Stack<>();
        private final PriorityQueue<Task> priorityQueue = new PriorityQueue<>(
                Comparator.comparing(Task::getPriority).thenComparingInt(Task::getId));
        private int nextId = 1;

        // ArrayList 负责保存所有任务。
        // 添加任务时分配唯一 id，后续编辑、删除、移动都通过 id 找任务，避免标题重复导致歧义。
        public Task addTask(String title, Priority priority) {
            Task task = new Task(nextId++, title, priority);
            tasks.add(task);
            recordActivity("ADD task " + task.getId());
            return task;
        }

        public boolean editTask(int id, String newTitle, Priority newPriority) {
            Task task = findTaskById(id);
            if (task == null) {
                return false;
            }

            task.setTitle(newTitle);
            task.setPriority(newPriority);
            rebuildPriorityQueue();
            recordActivity("EDIT task " + id);
            return true;
        }

        public boolean removeTask(int id) {
            Task task = findTaskById(id);
            if (task == null) {
                return false;
            }

            tasks.remove(task);
            processingQueue.remove(task);
            priorityQueue.remove(task);
            recordActivity("REMOVE task " + id);
            return true;
        }

        public boolean markComplete(int id) {
            Task task = findTaskById(id);
            if (task == null) {
                return false;
            }

            task.setCompleted(true);
            recordActivity("COMPLETE task " + id);
            return true;
        }

        // FIFO 队列使用 offer、peek、poll。
        // offer 把任务放到队尾；peek 查看队头但不删除；poll 取出并删除队头。
        public boolean moveToProcessingQueue(int id) {
            Task task = findTaskById(id);
            if (task == null || processingQueue.contains(task)) {
                return false;
            }

            processingQueue.offer(task);
            recordActivity("MOVE_TO_QUEUE task " + id);
            return true;
        }

        public Task peekNextTask() {
            return processingQueue.peek();
        }

        public Task processNextTask() {
            Task task = processingQueue.poll();
            if (task != null) {
                recordActivity("PROCESS_QUEUE task " + task.getId());
            }
            System.out.println("Processed FIFO task: " + task);
            return task;
        }

        // PriorityQueue 会根据 comparator 自动让优先级最高的任务排在队头。
        // 因为 enum 顺序是 HIGH、MEDIUM、LOW，所以自然顺序正好符合本题需求。
        public boolean addToPriorityQueue(int id) {
            Task task = findTaskById(id);
            if (task == null || priorityQueue.contains(task)) {
                return false;
            }

            priorityQueue.offer(task);
            recordActivity("ADD_PRIORITY task " + id);
            return true;
        }

        public Task peekHighestPriorityTask() {
            return priorityQueue.peek();
        }

        public Task processHighestPriorityTask() {
            Task task = priorityQueue.poll();
            if (task != null) {
                recordActivity("PROCESS_PRIORITY task " + task.getId());
            }
            System.out.println("Processed priority task: " + task);
            return task;
        }

        public void printAllTasks() {
            System.out.println("All tasks:");
            for (Task task : tasks) {
                System.out.println(task);
            }
        }

        public void printTasksByStatus(boolean completed) {
            System.out.println("Tasks with completed=" + completed + ":");
            for (Task task : tasks) {
                if (task.isCompleted() == completed) {
                    System.out.println(task);
                }
            }
        }

        public void printProcessingQueue() {
            System.out.println("FIFO queue:");
            for (Task task : processingQueue) {
                System.out.println(task);
            }
        }

        public void printPriorityQueue() {
            System.out.println("Priority queue snapshot:");
            ArrayList<Task> snapshot = new ArrayList<>(priorityQueue);
            snapshot.sort(Comparator.comparing(Task::getPriority).thenComparingInt(Task::getId));
            for (Task task : snapshot) {
                System.out.println(task);
            }
        }

        // Stack 的 peek 查看最近活动，pop 撤出最近活动。
        // 这里的 display 不修改栈，所以从栈顶向栈底遍历即可。
        public void printRecentActivities() {
            System.out.println("Recent activities:");
            for (int i = activityHistory.size() - 1; i >= 0; i--) {
                System.out.println(activityHistory.get(i));
            }
        }

        public String undoLastActivityLogOnly() {
            if (activityHistory.isEmpty()) {
                return null;
            }
            return activityHistory.pop();
        }

        public void printOverview() {
            System.out.println("Overview:");
            printAllTasks();
            printProcessingQueue();
            printPriorityQueue();
            printRecentActivities();
        }

        private Task findTaskById(int id) {
            for (Task task : tasks) {
                if (task.getId() == id) {
                    return task;
                }
            }
            return null;
        }

        private void recordActivity(String activity) {
            activityHistory.push(activity);
        }

        private void rebuildPriorityQueue() {
            ArrayList<Task> snapshot = new ArrayList<>(priorityQueue);
            priorityQueue.clear();
            priorityQueue.addAll(snapshot);
        }
    }
}
