package JavaProjects;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SortingVisualizer extends JPanel {

    private static final int WIDTH = 1200;
    private static final int HEIGHT = 800;
    private static final int BAR_WIDTH = 8;
    private static final int DELAY = 10; // Milliseconds

    private int[] array;
    private int currentIndex;
    private int swapIndex;

    public SortingVisualizer(int[] array) {
        this.array = array;
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.WHITE);
        this.currentIndex = 0;
        this.swapIndex = -1;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < array.length; i++) {
            Color color = Color.GRAY;
            if (i == currentIndex) {
                color = Color.RED;
            }
            if (i == swapIndex) {
                color = Color.GREEN;
            }
            g.setColor(color);
            g.fillRect(i * BAR_WIDTH, HEIGHT - array[i], BAR_WIDTH, array[i]);
        }
    }

    /*
     * Bubble sort
     */

    public void bubbleSort() throws InterruptedException {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                currentIndex = j;
                swapIndex = -1;
                repaint();
                Thread.sleep(DELAY);
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapIndex = j + 1;
                    repaint();
                    Thread.sleep(DELAY);
                }
            }
        }
        currentIndex = -1;
        swapIndex = -1;
        repaint();
    }

    /*
     * Selection sort
     */
    public void selectionSort(int arr[]) throws InterruptedException {
        for (int i = 0; i < arr.length - 1; i++) {
            int minPos = i;
            for (int j = i + 1; j < arr.length; j++) {
                currentIndex = j;
                swapIndex = minPos;
                repaint();
                Thread.sleep(DELAY);
                if (arr[minPos] > arr[j]) {
                    minPos = j;
                }
            }

            // swap
            int temp = arr[minPos];
            arr[minPos] = arr[i];
            arr[i] = temp;
            swapIndex = i;
            repaint();
            Thread.sleep(DELAY);
        }
        currentIndex = -1;
        swapIndex = -1;
        repaint();
    }

    /*
     * insertion sort
     */

    public void insertionSort(int[] arr) throws InterruptedException {
        for (int i = 1; i < arr.length; i++) {
            int curr = arr[i];
            int prv = i - 1;

            while (prv >= 0 && arr[prv] > curr) {
                arr[prv + 1] = arr[prv];
                prv--;
            }

            arr[prv + 1] = curr;
        }
    }

    /*
     * Merge sort
     */

    public void mergeSort(int si, int ei) throws InterruptedException {
        if (si < ei) {
            int mid = si + (ei - si) / 2;
            mergeSort(si, mid);
            mergeSort(mid + 1, ei);
            merge(si, mid, ei);
        }
    }

    private void merge(int si, int mid, int ei) throws InterruptedException {
        int temp[] = new int[ei - si + 1];

        int i = si;
        int j = mid + 1;
        int k = 0;

        while (i <= mid && j <= ei) {
            currentIndex = i;
            swapIndex = j;
            repaint();
            Thread.sleep(DELAY);
            if (array[i] < array[j]) {
                temp[k++] = array[i++];
            } else {
                temp[k++] = array[j++];
            }
        }

        // For left part elements
        while (i <= mid) {
            temp[k++] = array[i++];
        }

        // for right part elements
        while (j <= ei) {
            temp[k++] = array[j++];
        }

        for (k = 0, i = si; k < temp.length; k++, i++) {
            currentIndex = i;
            swapIndex = -1;
            repaint();
            Thread.sleep(DELAY);
            array[i] = temp[k];
        }
        currentIndex = -1;
        swapIndex = -1;
        repaint();
    }

    /*
     * Quick sort
     */

    public void quickSort(int si, int ei) throws InterruptedException {
        if (si >= ei) {
            return;
        }

        int pIdx = partition(array, si, ei); // This is pivot index
        quickSort(si, pIdx - 1); // left
        quickSort(pIdx, ei); // right
    }

    private int partition(int[] array, int si, int ei) throws InterruptedException {
        int pivot = array[ei];
        int i = si - 1; // to make place for elements smaller than pivot

        for (int j = si; j < ei; j++) {
            currentIndex = j;
            swapIndex = ei;
            repaint();
            Thread.sleep(DELAY);
            if (array[j] <= pivot) {
                i++;
                // swap
                int temp = array[j];
                array[j] = array[i];
                array[i] = temp;
                swapIndex = i;
                repaint();
                Thread.sleep(DELAY);
            }
        }

        i++;
        // swap -> pivot to right place
        int temp = pivot;
        array[ei] = array[i];
        array[i] = temp;
        swapIndex = i + 1;
        repaint();
        Thread.sleep(DELAY);
        currentIndex = -1;
        swapIndex = -1;
        repaint();
        return i;
    }

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        int[] array = new int[WIDTH / BAR_WIDTH];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(HEIGHT);
        }
        SortingVisualizer visualizer = new SortingVisualizer(array);
        JFrame frame = new JFrame("Sorting Visualizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(visualizer);
        frame.pack();
        frame.setVisible(true);
        // visualizer.bubbleSort();
        visualizer.mergeSort(0, array.length - 1);
        // visualizer.quickSort(0, array.length - 1);
        // visualizer.quickSort(0, array.length - 1);
    }
}
