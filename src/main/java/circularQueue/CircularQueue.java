package circularQueue;

public class CircularQueue {

    int[] q;
    int front = 0;
    int rear = -1;
    int len = 0;

    public CircularQueue(int k) {
        this.q = new int[k];
    }

    public boolean enQueue(int value) {
        // 공간이 존재하면 삽입
        if (!this.isFull()) {
            // rear 포인터 한칸 앞으로 이동, 최대 크키 초과시 나머지 위치로 이동
            this.rear = (this.rear + 1) % this.q.length;
            // rear 위치에 값 삽입
            this.q[rear] = value;
            // 현재 큐의 크기 계산
            this.len ++;

            return true;
        } else {
            return false;
        }
    }

    public boolean deQueue() {
        // 비어있지 않으면 삭제 진행
        if (!this.isEmpty()) {
            // front 포인터 한칸 앞으로 이동, 최대크기 초과시 나머지 위치로 이동
            this.front = (this.front + 1) % this.q.length;
            // 현재 큐 크기 계산
            this.len --;

            return true;
        } else {
            return  false;
        }
    }

    // 맨 앞을 갖고온다
    public int front() {
        return (this.isEmpty()) ? - 1 : this.q[this.front];
    }

    // 맨 뒤 값을 갖고온다
    public int rear() {
        return (this.isEmpty() ? -1 : this.q[this.rear]);
    }

    // 현재 큐 크기가 0이면 비어있음
    public boolean isEmpty() {
        return this.len == 0;
    }

    // 현재 큐 크기가 전체 큐와 일치하면 포화상태
    public boolean isFull() {
        return this.len == this.q.length;
    }

    public static void main(String[] args) {
        // 크기 3지정
        CircularQueue circularQueue = new CircularQueue(3);
        circularQueue.enQueue(1);
        circularQueue.enQueue(2);
        circularQueue.enQueue(3);
        System.out.println(circularQueue.isFull());
        circularQueue.deQueue();
        circularQueue.enQueue(4);
        System.out.println(circularQueue.rear());
//        System.out.println(circularQueue.deQueue());
        System.out.println(circularQueue.front());
    }
}

