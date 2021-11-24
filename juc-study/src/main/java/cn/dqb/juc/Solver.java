package cn.dqb.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Solver {
    final int N;
    final float[][] data;
    final CyclicBarrier barrier;
    float[] result;
    float total;

    class Worker implements Runnable {
        int myRow;

        Worker(int row) {
            myRow = row;
        }

        @Override
        public void run() {
            //while (!done()) {
            processRow(myRow);
            try {
                barrier.await();
            } catch (InterruptedException ex) {
                return;
            } catch (BrokenBarrierException ex) {
                return;
            }
            //}
        }

        private boolean done() {
            return myRow - 1 > N;
        }

        private void processRow(int myRow) {
            float[] datum = data[myRow];
            float sum = 0;
            for (float v : datum) {
                sum += v;
            }
            result[myRow] = sum;
        }
    }

    private void mergeRows() {
        for (float v : result) {
            total += v;
        }
    }

    public Solver(float[][] matrix) throws InterruptedException {
        data = matrix;
        N = matrix.length;
        result = new float[N];
        Runnable barrierAction =
                this::mergeRows;
        barrier = new CyclicBarrier(N, barrierAction);

        List<Thread> threads = new ArrayList<Thread>(N);
        for (int i = 0; i < N; i++) {
            Thread thread = new Thread(new Worker(i));
            threads.add(thread);
            thread.start();
        }

        // wait until done
        for (Thread thread : threads) {
            thread.join();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        float[][] matrix = {{1}, {2}, {4}, {1, 10}};
        Solver solver = new Solver(matrix);
        System.out.println(solver.total);
    }
}