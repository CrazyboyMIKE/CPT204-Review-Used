import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Week06Answers {
    public static void main(String[] args) {
        System.out.println("Week 06 Answers");

        TodoTagManager manager = new TodoTagManager();
        Task algorithms = manager.addTask("Revise algorithms");
        Task coding = manager.addTask("Write Java solutions");
        Task report = manager.addTask("Prepare lab notes");

        manager.addTag(algorithms.getId(), "study");
        manager.addTag(algorithms.getId(), "exam");
        manager.addTag(algorithms.getId(), "study");
        manager.addTag(coding.getId(), "java");
        manager.addTag(coding.getId(), "study");
        manager.addTag(report.getId(), "writing");
        manager.addTag(report.getId(), "study");

        manager.printTaskById(coding.getId());
        manager.printAllTags();
        manager.printTasksByTag("study");
        manager.printTagFrequency();
        manager.printMostFrequentTag();
        manager.printSortedTags();
        manager.printSortedTagFrequency();

        manager.removeTask(report.getId());
        manager.printTagFrequency();
    }

    static class Task {
        private final int id;
        private final String title;
        private final Set<String> tags = new HashSet<>();

        public Task(int id, String title) {
            this.id = id;
            this.title = title;
        }

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public Set<String> getTags() {
            return tags;
        }

        @Override
        public String toString() {
            return "Task{id=" + id + ", title='" + title + "', tags=" + tags + "}";
        }
    }

    static class TodoTagManager {
        private final Map<Integer, Task> taskMap = new HashMap<>();
        private final Set<String> allTags = new HashSet<>();
        private final Map<String, Integer> tagFrequency = new HashMap<>();
        private int nextId = 1;

        // HashMap<Integer, Task> 让我们可以通过 id 直接找到任务。
        // 相比遍历 List，平均时间复杂度从 O(n) 降到 O(1)。
        public Task addTask(String title) {
            Task task = new Task(nextId++, title);
            taskMap.put(task.getId(), task);
            return task;
        }

        public Task findTaskById(int id) {
            return taskMap.get(id);
        }

        // 每个任务自己的 tags 用 HashSet 保存。
        // HashSet 自动阻止重复标签，所以同一个任务加两次 study 不会重复计数。
        public boolean addTag(int taskId, String tag) {
            Task task = findTaskById(taskId);
            if (task == null) {
                return false;
            }

            boolean added = task.getTags().add(tag);
            if (added) {
                allTags.add(tag);
                tagFrequency.put(tag, tagFrequency.getOrDefault(tag, 0) + 1);
            }
            return added;
        }

        public boolean removeTag(int taskId, String tag) {
            Task task = findTaskById(taskId);
            if (task == null) {
                return false;
            }

            boolean removed = task.getTags().remove(tag);
            if (removed) {
                decreaseTagFrequency(tag);
            }
            return removed;
        }

        // 删除任务时必须同步更新 taskMap、allTags 和 tagFrequency。
        // 这就是 Map/Set 维护一致性的重点：一个地方的数据变化，相关索引也要同步变化。
        public boolean removeTask(int taskId) {
            Task removed = taskMap.remove(taskId);
            if (removed == null) {
                return false;
            }

            for (String tag : new ArrayList<>(removed.getTags())) {
                decreaseTagFrequency(tag);
            }
            return true;
        }

        public void printTaskById(int id) {
            System.out.println("Lookup task: " + findTaskById(id));
        }

        public void printAllTags() {
            System.out.println("All unique tags: " + allTags);
        }

        // 按标签过滤需要检查每个任务是否包含目标标签。
        // 当前实现是 O(n)，Week 08 会用 tag index 优化。
        public void printTasksByTag(String tag) {
            System.out.println("Tasks with tag " + tag + ":");
            boolean found = false;
            for (Task task : taskMap.values()) {
                if (task.getTags().contains(tag)) {
                    System.out.println(task);
                    found = true;
                }
            }
            if (!found) {
                System.out.println("No matching tasks.");
            }
        }

        public void printTagFrequency() {
            System.out.println("Tag frequency: " + tagFrequency);
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

        public void printSortedTags() {
            TreeSet<String> sortedTags = new TreeSet<>(allTags);
            System.out.println("Sorted tags: " + sortedTags);
        }

        public void printSortedTagFrequency() {
            TreeMap<String, Integer> sortedFrequency = new TreeMap<>(tagFrequency);
            System.out.println("Sorted tag frequency: " + sortedFrequency);
        }

        private void decreaseTagFrequency(String tag) {
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
