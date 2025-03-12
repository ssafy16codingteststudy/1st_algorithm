import java.util.*;

class UserSolution {

    // 키 : 학년과 성별, 값 : 학생들을 저장할 treeSet
    private Map<String, TreeSet<Student>> map;
    // 현재 저장된 학생들을 id 로 검색하기 위한 map -> remove 에 사용
    private Map<Integer, Student> studentMap;

    public void init() {
        map = new HashMap<>();
        studentMap = new HashMap<>();

        for (int i = 1; i <= 3; i++) {
            String f = "female";
            String m = "male";

            // 성별 + 학년 문자열을 키값으로 함
            map.put(f + i, new TreeSet<>());
            map.put(m + i, new TreeSet<>());
        }

        return;
    }

    public int add(int mId, int mGrade, char mGender[], int mScore) {
        Student student = new Student(mId, mScore);

        String key = getKey(mGender, mGrade);

        map.get(key).add(student);
        studentMap.put(mId, student);

        // 정렬된 마지막 값 : 가장 점수가 높으면서, id 또한 높은 값
        return map.get(key).last().id;
    }

    public int remove(int mId) {
        // 입력받은 id 가 맵에 존재 한다면 삭제
        if (studentMap.containsKey(mId)) {
            Student remove = studentMap.remove(mId);

            for (int i = 1; i <= 3; i++) {
                for (int j = 0; j < 2; j++) {
                    String key = "";
                    if (j == 0) {
                        key = "female";
                    } else {
                        key = "male";
                    }

                    TreeSet<Student> tree = map.get(key + i);

                    // 6개의 treeSet 을 돌면서 해당 학생을 발견하면 삭제 후 리턴
                    if (tree.contains(remove)) {
                        tree.remove(remove);

                        if (tree.isEmpty()) {
                            return 0;
                        }
                        return tree.first().id;
                    }
                }
            }
        }

        return 0;
    }

    public int query(int mGradeCnt, int mGrade[], int mGenderCnt, char mGender[][], int mScore) {
        // 해당 집합 중 score 점수보다 큰 값중에서, 가장 작은 값을 반환
        Student target = new Student(1_000_000_001, 300_001);
        for (int i = 0; i < mGradeCnt; i++) {
            for (int j = 0; j < mGenderCnt; j++) {
                String key = getKey(mGender[j], mGrade[i]);

                TreeSet<Student> tree = map.get(key);

                // ceiling 함수 : 특정 값 이상인 요소들 중에서 가장 작은 요소를 반환, 없으면 null
                Student ceiling = tree.ceiling(new Student(0, mScore));

                // 6개를 전부 돌면서, 가장 작은 값 선별
                if (ceiling != null && target.compareTo(ceiling) > 0) {
                    target = ceiling;
                }
            }
        }

        if (target.id == 1_000_000_001 && target.score == 300_001) {
            return 0;
        }

        return target.id;
    }

    private String getKey(char[] mGender, int mGrade) {
        String key = "";
        for (char c : mGender) {
            if (c == '\0') {
                continue;
            }
            key += c;
        }
        key += mGrade;
        return key;
    }

    private class Student implements Comparable<Student> {
        int id;
        int score;

        public Student(int id, int score) {
            this.id = id;
            this.score = score;
        }

        @Override
        public int compareTo(Student o) {
            if (this.score == o.score) {
                return this.id - o.id;
            }
            return this.score - o.score;
        }
    }
}
