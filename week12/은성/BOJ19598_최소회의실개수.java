package week12.은성;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ19598_최소회의실개수 {
    // 19598 최소 회의실 개수
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Course> queue = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            String[] split = br.readLine().split(" ");
            int a = Integer.parseInt(split[0]);
            int b = Integer.parseInt(split[1]);

            queue.add(new Course(a, b));
        }

        System.out.println(getCourseNumber(queue));
    }

    private static int getCourseNumber(PriorityQueue<Course> queue) {
        PriorityQueue<Course> courses = new PriorityQueue<>();

        while (!queue.isEmpty()) {
            Course poll = queue.poll();
            Course newCourse = new Course(poll.endTime, poll.startTime);

            if (courses.isEmpty()) {
                courses.add(newCourse);
                continue;
            }

            Course getMinCourse = courses.poll();
            if (newCourse.endTime < getMinCourse.startTime) {
                courses.add(getMinCourse);
            }
            courses.add(newCourse);
        }

        return courses.size();
    }

    private static class Course implements Comparable<Course> {
        int startTime;
        int endTime;

        public Course(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        @Override
        public int compareTo(Course o) {
            if (this.startTime == o.startTime) {
                return this.endTime - o.endTime;
            }
            return this.startTime - o.startTime;
        }
    }
}