import java.util.*;

class PG_기둥과보설치 {
    
    private int N;
    private Set<FrameIndex>[] sets = new Set[2];
    
    public int[][] solution(int n, int[][] build_frame) {
        N = n;
        sets[0] = new HashSet<>();
        sets[1] = new HashSet<>();                
        
        for (int[] frame : build_frame) {
            int x = frame[0];
            int y = frame[1];
            int a = frame[2];
            int b = frame[3];
            
            FrameIndex f = new FrameIndex(x, y, a);
            
            if(b == 0) {
                sets[a].remove(f);

                if(!isDeletePossible()) {
                    sets[a].add(f);
                }
                
            } else {
                if(isBulidPossible(f)){ 
                    sets[a].add(f);
                }
            }
        }
        
        List<FrameIndex> list = new ArrayList<>();
        list.addAll(sets[0]);
        list.addAll(sets[1]);
        
        Collections.sort(list);

        int[][] answer = new int[list.size()][];
        for (int i=0; i<list.size(); i++) {
            FrameIndex fi = list.get(i);
            answer[i] = new int[] {fi.x, fi.y, fi.type};
        }
        
        return answer;
    }
    
    private boolean isDeletePossible() {
        for (int i = 0; i < 2; i++) {
            for (FrameIndex fi : sets[i]) {
                if(!isBulidPossible(fi)) {
                    return false;
                }
            } 
        }
        return true;        
    }
    
    private boolean isBulidPossible(FrameIndex f) {
        
        if(f.type == 1) {   // 보
            if(f.y == 0) {
                return false;
            }
            
            if(sets[0].contains(new FrameIndex(f.x, f.y - 1, 0)) || 
                    sets[0].contains(new FrameIndex(f.x + 1, f.y - 1, 0))) {
                return true;
            }
            
            if(f.x == 0 || f.x == N - 1) {
                return false;
            }
            
            if(sets[1].contains(new FrameIndex(f.x - 1, f.y, 1)) && 
                    sets[1].contains(new FrameIndex(f.x + 1, f.y, 1))) {
                return true;
            }
            
        } else {    // 기둥
            if(f.y == 0) {
                return true;
            }
            
            if(sets[1].contains(new FrameIndex(f.x, f.y, 1)) ||
                    sets[1].contains(new FrameIndex(f.x - 1, f.y, 1)) ||
                    sets[0].contains(new FrameIndex(f.x, f.y - 1, 0))) {
                return true;
            }
            
            return false;
        }
        return false;
    }
    
    private class FrameIndex implements Comparable<FrameIndex> {
        int x, y, type;
        
        public FrameIndex(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }
        
        @Override
        public int compareTo(FrameIndex o) {
            if(this.x == o.x) {
                return this.y - o.y;
            }
            return this.x - o.x;
        }

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + type;
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			FrameIndex other = (FrameIndex) obj;
			if (type != other.type)
				return false;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}
    }
}