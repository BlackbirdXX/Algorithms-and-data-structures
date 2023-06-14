package Task02;

public class PyramidSort {
    
    private static void throwHeap(int[] array, int size, int depth) {
        int older = depth;
        int left = 2*depth+1;
        int right = 2*depth+2;
        if(left < size && array[left] > array[older]){
            older = left;
        }
        if(right < size && array[right] > array[older]){
            older = right;
        }
        if(older != depth){
            int temp = array[depth];
            array[depth] = array[older];
            array[older] = temp;
            throwHeap(array, size, older);
        }
    }

    public static int[] pyramidSort(int[] array) {
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            throwHeap(array, array.length, i);
        }

        for (int i = array.length - 1; i > 0; i--) {
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            throwHeap(array, i, 0);
        }
        return array;
    }
   
    public static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] array = new int[]{8,1,4,2,7,9,6,0,3,5};
        printArray(array);
        pyramidSort(array);
        printArray(array);
        
    }
}
