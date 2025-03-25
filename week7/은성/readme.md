## 1. `while (start < end)` – 반개구간([start, end)) 방식

### 탐색 구간 정의

- 후보가 존재하는 구간을 `[start, end)`로 정의한다.
- **end**는 정답이 될 수 없는 “경계” 값이다.

### 업데이트 규칙

1. **중간값(mid) 계산**
    
    ```java
    int mid = (start + end) / 2;
    ```
    
2. **조건 검사**
    - **조건 만족 시:**
        
        정답 후보가 될 수 있으므로, 더 작은 값이 있을 가능성을 열어두기 위해 `end = mid;`로 설정한다.
        
    - **조건 불만족 시:**
        
        정답은 `mid`보다 큰 값이어야 하므로, `start = mid + 1;`로 설정한다.
        

### 루프 종료 후

- 루프는 `start`와 `end`가 동일해질 때 종료된다.
- 종료 시 `start` (또는 `end`)의 값이 최종 정답이 된다.

### 예시 코드

```java
int start = L, end = R; // L은 최소 후보, R은 정답이 될 수 없는 값
while (start < end) {
    int mid = (start + end) / 2;
    if (조건(mid)) {  // mid가 조건을 만족하면
        end = mid;   // 후보 범위를 [start, mid)로 줄인다.
    } else {
        start = mid + 1;  // 조건을 만족하지 않으므로 후보 범위를 [mid+1, end)로 좁힌다.
    }
}
// start == end가 최종 정

```

---

## 2. `while (start <= end)` – 폐구간([start, end]) 방식

### 탐색 구간 정의

- 후보가 포함되는 구간을 `[start, end]`로 정의한다.
- 이 경우, `start`와 `end` 모두 정답 후보가 될 수 있다.

### 업데이트 규칙

1. **중간값(mid) 계산**
    
    ```java
    int mid = (start + end) / 2;
    ```
    
2. **조건 검사**
    - **조건 만족 시:**
        - `mid`가 정답 후보가 될 수 있으므로, 현재 후보로 저장한다 (예: `answer = mid;`).
        - 더 좋은 후보(보통 더 작은 값)를 찾기 위해, `end = mid - 1;`로 업데이트한다.
    - **조건 불만족 시:**
        - 정답은 `mid`보다 큰 값이어야 하므로, `start = mid + 1;`로 업데이트한다.

### 루프 종료 후

- 루프는 `start`가 `end`보다 커질 때 종료된다.
- 미리 저장한 `answer` 변수에 최종 정답이 저장된다.

### 예시 코드

```java
int start = L, end = R; // L과 R은 모두 후보가 될 수 있는 값
int answer = -1; // 초기값은 상황에 맞게 설정
while (start <= end) {
    int mid = (start + end) / 2;
    if (조건(mid)) {  // mid가 조건을 만족하면
        answer = mid; // 현재 mid를 정답 후보로 저장
        end = mid - 1;  // 더 좋은 후보(보통 더 작은 값)를 찾기 위해 탐색 범위를 [start, mid-1]로 좁힌다.
    } else {
        start = mid + 1;
    }
}
// answer에 최종 정답이 저장됨.
```

---

## 핵심 차이점 정리

- **`while (start < end)` 방식 (반개구간)**
    - 탐색 구간: `[start, end)`
    - 조건 만족 시: `end = mid;` (mid 포함)
    - 루프 종료 후: `start == end`가 정답이다.
- **`while (start <= end)` 방식 (폐구간)**
    - 탐색 구간: `[start, end]`
    - 조건 만족 시: `answer`를 갱신하고 `end = mid - 1;`로 후보에서 제거
    - 루프 종료 후: 미리 저장된 `answer`가 최종 정답이다.

이 두 방식을 상황에 맞게 선택하고, 탐색 구간 정의에 따라 경계값 업데이트가 일관되도록 작성하면 올바른 이분 탐색 구현이 가능하다.