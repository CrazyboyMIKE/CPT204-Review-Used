import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Eight Queens 八皇后问题。
 *
 * 覆盖 Week 8 Backtracking：
 * 逐行放皇后；如果当前选择导致冲突，就撤销并尝试下一列。
 */
public class EightQueensBacktracking {

    public static void main(String[] args) {
        List<int[]> solutions = solveEightQueens();
        System.out.println("Number of Eight Queens solutions: " + solutions.size());
        System.out.println("First solution:");
        printBoard(solutions.get(0));
    }

    /**
     * queens[row] = column。
     *
     * 例如 queens[2] = 5 表示第 2 行的皇后放在第 5 列。
     */
    public static List<int[]> solveEightQueens() {
        int[] queens = new int[8];
        Arrays.fill(queens, -1);

        List<int[]> solutions = new ArrayList<>();
        search(0, queens, solutions);
        return solutions;
    }

    /**
     * 尝试在 row 这一行放皇后。
     *
     * 如果 row == 8，说明 0..7 行都放好了，找到一个完整解。
     */
    private static void search(int row, int[] queens, List<int[]> solutions) {
        if (row == queens.length) {
            solutions.add(Arrays.copyOf(queens, queens.length));
            return;
        }

        for (int column = 0; column < queens.length; column++) {
            queens[row] = column;

            // 如果当前位置合法，就继续放下一行。
            if (isValid(row, queens)) {
                search(row + 1, queens, solutions);
            }

            // 回溯：撤销当前选择，尝试下一列。
            queens[row] = -1;
        }
    }

    /**
     * 检查第 row 行新放的皇后是否和前面行冲突。
     *
     * 不需要检查同行，因为算法每行只放一个皇后。
     */
    private static boolean isValid(int row, int[] queens) {
        for (int previousRow = 0; previousRow < row; previousRow++) {
            // 同列冲突。
            if (queens[previousRow] == queens[row]) {
                return false;
            }

            // 对角线冲突：行差的绝对值 == 列差的绝对值。
            if (Math.abs(queens[previousRow] - queens[row]) == row - previousRow) {
                return false;
            }
        }

        return true;
    }

    public static void printBoard(int[] queens) {
        for (int row = 0; row < queens.length; row++) {
            for (int column = 0; column < queens.length; column++) {
                System.out.print(queens[row] == column ? "Q " : ". ");
            }
            System.out.println();
        }
    }
}
