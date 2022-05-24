package gbchat.server;
import org.apache.commons.lang3.ArrayUtils;

public class hw3_6 {

    public static int[] case1(int array[]) {


        int newArray[] = ArrayUtils.subarray(array,ArrayUtils.lastIndexOf(array, 4) + 1,
                ArrayUtils.getLength(array)+1);
        if (array.equals(newArray)) throw new RuntimeException();
        return newArray;
    }
    public boolean case2(int array[]) {
        return  (ArrayUtils.contains(array, 1) || ArrayUtils.contains(array, 4));
    }
}
