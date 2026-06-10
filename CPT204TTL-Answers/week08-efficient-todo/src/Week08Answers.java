import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

public class Week08Answers {
    public static void main(String[] args) {
        System.out.println("Week 08 Answers");

        EfficientTodoManager manager = new EfficientTodoManager();
        Task revision = manager.addTask("Revise graphs", Priority.HIGH);
        Task coding = manager.addTask("Practice Java", Priority.MEDIUM);
        Task notes = manager.addTask("Summarize lectures", Priority.LOW);

        manager.addTag(revision.getId(), "exam");
        manager.addTag(revision.getId(), "graph");
        manager.addTag(coding.getId(), "java");
        manager.addTag(coding.getId(), "exam");
        manager.addTag(notes.getId(), "writing");

        manager.markCompleted(notes.getId());
        manager.addToFifoQueue(revision.getId());
        manager.addToFifoQueue(coding.getId());
        manager.addToPriorityQueue(revision.getId());
        manager.addToPriorityQueue(coding.getId());

        manager.printTasksByTag("exam");
        manager.printTasksByStatus(false);
        manager.printAllTags();
        manager.printTagFrequency();
        manager.printSortedTags();
        manager.printMostFrequentTag();
        manager.processNextFifoTask();
        manager.processHighestPriorityTask();
        manager.printComplexityAudit();
    }

    enum Priority {
        HIGH,
        MEDIUM,
        LOW
    }

    static class Task {
        private final int id;
        private final String title;
        private boolean completed;
        private final Priority priority;
        private final Set<String> tags = new HashSet<>();

        public Task(int id, String title, Priority priority) {
            this.id = id;
            this.title = title;
            this.priority = priority;
        }

        public int getId() {
            return id;
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

        public Set<String> getTags() {
            return tags;
        }

        @Override
        public String toString() {
            return "Task{id=" + id + ", title='" + title + "', completed=" + completed
                    + ", priority=" + priority + ", tags=" + tags + "}";
        }
    }

    static class EfficientTodoManager {
        private final Map<Integer, Task> taskMap = new HashMap<>();
        private final Map<String, Set<Integer>> tasksByTag = new HashMap<>();
        private final Map<Boolean, Set<Integer>> tasksByCompletion = new HashMap<>();
        private final Set<String> allTags = new HashSet<>();
        private final Map<String, Integer> tagFrequency = new HashMap<>();
        private final Queue<Task> fifoQueue = new LinkedList<>();
        private final Set<Integer> queuedTaskIds = new HashSet<>();
        private final PriorityQueue<Task> priorityQueue = new PriorityQueue<>(
                Comparator.comparing(Task::getPriority).thenComparingInt(Task::getId));
        private final Set<Integer> priorityQueuedTaskIds = new HashSet<>();
        private int nextId = 1;

        public EfficientTodoManager() {
            tasksByCompletion.put(false, new HashSet<>());
            tasksByCompletion.put(true, new HashSet<>());
        }

        // 主表 taskMap 负责通过 id 找任务，平均 O(1)。
        // 状态索引 tasksByCompletion 会同步记录任务属于 completed 还是 incomplete。
        public Task addTask(String title, Priority priority) {
            Task task = new Task(nextId++, title, priority);
            taskMap.put(task.getId(), task);
            tasksByCompletion.get(false).add(task.getId());
            return task;
        }

        public Task lookupTaskById(int id) {
            return taskMap.get(id);
        }

        // 添加标签时同时维护三个结构：
        // 1. task.tags：任务自己的标签集合。
        // 2. tasksByTag：从标签快速找到任务 id。
        // 3. tagFrequency/allTags：直接得到统计信息，不需要每次重新扫描所有任务。
        public boolean addTag(int taskId, String tag) {
            Task task = lookupTaskById(taskId);
            if (task == null) {
                return false;
            }

            boolean added = task.getTags().add(tag);
            if (!added) {
                return false;
            }

            tasksByTag.computeIfAbsent(tag, ignored -> new HashSet<>()).add(taskId);
            allTags.add(tag);
            tagFrequency.put(tag, tagFrequency.getOrDefault(tag, 0) + 1);
            return true;
        }

        public boolean removeTask(int taskId) {
            Task task = taskMap.remove(taskId);
            if (task == null) {
                return false;
            }

            tasksByCompletion.get(task.isCompleted()).remove(taskId);
            queuedTaskIds.remove(taskId);
            priorityQueuedTaskIds.remove(taskId);
            fifoQueue.remove(task);
            priorityQueue.remove(task);

            for (String tag : new ArrayList<>(task.getTags())) {
                removeTaskFromTagIndex(taskId, tag);
            }
            return true;
        }

        // 标记完成时不再遍历所有任务，而是从 incomplete set 移到 completed set。
        // 因为 HashSet 删除和添加平均都是 O(1)，状态索引更新非常快。
        public boolean markCompleted(int taskId) {
            Task task = lookupTaskById(taskId);
            if (task == null || task.isCompleted()) {
                return false;
            }

            tasksByCompletion.get(false).remove(taskId);
            task.setCompleted(true);
            tasksByCompletion.get(true).add(taskId);
            return true;
        }

        public void printTasksByTag(String tag) {
            System.out.println("Indexed tag filter for " + tag + ":");
            Set<Integer> ids = tasksByTag.getOrDefault(tag, Set.of());
            for (int id : ids) {
                System.out.println(taskMap.get(id));
            }
        }

        public void printTasksByStatus(boolean completed) {
            System.out.println("Indexed status filter completed=" + completed + ":");
            for (int id : tasksByCompletion.get(completed)) {
                System.out.println(taskMap.get(id));
            }
        }

        public void printAllTags() {
            System.out.println("All tags: " + allTags);
        }

        public void printTagFrequency() {
            System.out.println("Tag frequency: " + tagFrequency);
        }

        public void printSortedTags() {
            System.out.println("Sorted tags: " + new TreeSet<>(allTags));
        }

        public void printMostFrequentTag() {
            String bestTag = null;
            int bestCount = 0;

            for (Map.Entry<String, Integer> entry : tagFrequency.entrySet()) {
                if (entry.getValue() > bestCount) {
                    bestTag = entry.getKey();
                    bestCount = entry.getValue();
                }
            }

            System.out.println("Most frequent tag: " + bestTag + " = " + bestCount);
        }

        // queuedTaskIds 是辅助 Set，用来让重复检查从 Queue.contains 的 O(q) 变成 O(1)。
        // 真正的处理顺序仍然由 fifoQueue 保存。
        public boolean addToFifoQueue(int taskId) {
            Task task = lookupTaskById(taskId);
            if (task == null || queuedTaskIds.contains(taskId)) {
                return false;
            }

            fifoQueue.offer(task);
            queuedTaskIds.add(taskId);
            return true;
        }

        public Task processNextFifoTask() {
            Task task = fifoQueue.poll();
            if (task != null) {
                queuedTaskIds.remove(task.getId());
            }
            System.out.println("Processed FIFO task: " + task);
            return task;
        }

        // priorityQueuedTaskIds 的作用和 queuedTaskIds 相同：快速判断是否已经在优先队列中。
        public boolean addToPriorityQueue(int taskId) {
            Task task = lookupTaskById(taskId);
            if (task == null || priorityQueuedTaskIds.contains(taskId)) {
                return false;
            }

            priorityQueue.offer(task);
            priorityQueuedTaskIds.add(taskId);
            return true;
        }

        public Task processHighestPriorityTask() {
            Task task = priorityQueue.poll();
            if (task != null) {
                priorityQueuedTaskIds.remove(task.getId());
            }
            System.out.println("Processed priority task: " + task);
            return task;
        }

        public void printComplexityAudit() {
            System.out.println("Complexity audit:");
            System.out.println("lookupTaskById: average O(1)");
            System.out.println("viewTasksByStatus: O(k), k is matching tasks");
            System.out.println("filterTasksByTag: average O(1 + k)");
            System.out.println("viewAllTags: O(u), u is unique tags");
            System.out.println("viewTagFrequency: O(u)");
            System.out.println("viewSortedTags: O(u log u)");
            System.out.println("moveTaskToQueue duplicate check: average O(1)");
            System.out.println("addTaskToPriorityQueue duplicate check: average O(1)");
        }

        private void removeTaskFromTagIndex(int taskId, String tag) {
            Set<Integer> ids = tasksByTag.get(tag);
            if (ids != null) {
                ids.remove(taskId);
                if (ids.isEmpty()) {
                    tasksByTag.remove(tag);
                }
            }

            int count = tagFrequency.getOrDefault(tag, 0);
            if (count <= 1) {
                tagFrequency.remove(tag);
                allTags.remove(tag);
            } else {
                tagFrequency.put(tag, count - 1);
            }
        }
    }
}
