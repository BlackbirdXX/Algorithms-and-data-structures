package Task03;

public class ListReverse {

    public static class LinkList {

        static Node head;
        static Node tail;

        public static class Node {
            int value;
            Node next;
            Node prev;
        }

        public void addNode(int value) {
            Node newNode = new Node();
            newNode.value = value;
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                newNode.next = head;
                head.prev = newNode;
                head = newNode;
            }
        }

        public void print() {
            System.out.println("Чтение слева на право : ");
            Node node = head;
            while (node != null) {
                System.out.print(node.value + " ");
                node = node.next;
            }
            System.out.println();
        }

        public void reversePrint() {
            System.out.println("Чтение справа на лево : ");
            Node node = tail;
            while (node != null) {
                System.out.print(node.value + " ");
                node = node.prev;
            }
            System.out.println();
        }

        public void reverseList() {
            if (head != null && head.next != null) {
                Node tempNode = head;
                head = tail;
                tail = tempNode;
                Node curNode = head;
                while (curNode != null) {
                    tempNode = curNode.next;
                    curNode.next = curNode.prev;
                    curNode.prev = tempNode;
                    curNode = curNode.next;
                }
            }
        }
    }

    public static void main(String[] args) {

        LinkList newList = new LinkList();
        for (int i = 0; i < 10; i++) {
            newList.addNode(i);
        }

        newList.print();

        newList.reverseList();

        newList.print();
        newList.reversePrint();
    }

}
