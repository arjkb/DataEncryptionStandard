
interface Const {
    public int LEFT = 0;
    public int RIGHT = 1;
}

class Utilities implements Const {   
    static void printArray(int C[], String message, int width)    {
        System.out.print("\n PRINTING: " + message + " " + C.length + "\t");
        for(int i = 0, counter = 1; i < C.length; i++, counter++) {
            System.out.print(C[i]);
            if(counter % width == 0)  {
                System.out.print(" ");
            }
        }
    }

    static void printArray(int C[]) {
        printArray(C, "", 7);
    }

    static void printArray(int C[], String message) {
        printArray(C, message, 7);
    }

    static int[] getArray(int []array, int half)  {
        int start = 0;
        int end = 0;

        int[] result = new int[array.length/2];

        if( half == Const.LEFT )  {
            start = 0;
            end = array.length/2;
        } else if( half == Const.RIGHT )  {
            start = array.length/2;
            end = array.length;
        }

        for(int i = start; i < end; i++)   {
            result[i - start] = array[i];
        }
        return result;
    }
    
    static int[] shiftLeft(int array[], int count) {
        int temp_elem = 0; // to store the element at the border
        for(int j = 0; j < count; j++)  {
            temp_elem = array[0];
            for(int i = 1; i < array.length; i++)   {
                array[i - 1] = array[i];
            }
            array[array.length - 1] = temp_elem;
        }
        return array;
    }

    static int[] combineArray(int first[], int second[])    {
        int[] result = new int[first.length + second.length];
        
        int i = 0;
        int j = 0;

        while(j < first.length) {
            result[i++] = first[j++];
        }
        
        j = 0;
        while(j < second.length)    {
            result[i++] = second[j++];
        }
        return result;
    }

    static int[] xor(int[] Aa, int[] Bb)    {
        int[] R = new int[Aa.length];

        for(int i = 0; i < R.length; i++)   {
            R[i] = xor_bitwise(Aa[i], Bb[i]);
        }

        return R;
    }

    static private int xor_bitwise(int a, int b)   {
        if((a == 0) && (b == 0))    {
            return 0;
        }
        else if((a == 0) && (b == 1))   {
            return 1;
        }
        else if((a == 1) && (b == 0))   {
            return 1;
        }
        else if((a == 1) && (b == 1))   {
            return 0;
        }
        else    {
            return -1; // This is an error condition
        }
    }
}

public class MyDes implements Const {
	

    public static void main(String args[])  {

        int[] M = { 0,0,0,0, 0,0,0,1, 0,0,1,0, 0,0,1,1,
        0,1,0,0, 0,1,0,1, 0,1,1,0, 0,1,1,1, 
        1,0,0,0, 1,0,0,1, 1,0,1,0, 1,0,1,1, 
        1,1,0,0, 1,1,0,1, 1,1,1,0, 1,1,1,1 };

        int[] IPM = null;
        int[][] L = new int[17][];
        int[][] R = new int[17][];
        
        int C[][] = new int[17][];
        int D[][] = new int[17][];

        int[][] K = new int[17][];
        int[] temp = null;

        K[0] = new int [] { 0,0,0,1,0,0,1,1,
                    0,0,1,1,0,1,0,0,
                    0,1,0,1,0,1,1,1,
                    0,1,1,1,1,0,0,1,
                    1,0,0,1,1,0,1,1,
                    1,0,1,1,1,1,0,0,
                    1,1,0,1,1,1,1,1,
                    1,1,1,1,0,0,0,1 };

        final int[] SHIFT_COUNT = {0, 1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1};

//        int KK[] = createSubkeys(K[0], );
	    int KK[] = DES.applyPermutation(K[0], Permutation.P1);

        Utilities.printArray(K[0], "ORIGINAL KEYS");
        Utilities.printArray(KK, "PERMUTED KEYS");

        C[0] = Utilities.getArray(KK, Const.LEFT);
        D[0] = Utilities.getArray(KK, Const.RIGHT);
        
        for(int i = 1; i <= 16; i++)  {
            /* Loop to compute the keys */

            C[i] = Utilities.shiftLeft(C[i-1], SHIFT_COUNT[i]);
            D[i] = Utilities.shiftLeft(D[i-1], SHIFT_COUNT[i]);

            temp = Utilities.combineArray(C[i], D[i]);
            System.out.println();

            K[i] = DES.applyPermutation(temp, Permutation.P2);

            Utilities.printArray(C[i], " C " + i);
            Utilities.printArray(D[i], " D " + i);
            Utilities.printArray(temp, " TEMP ");
            Utilities.printArray(K[i], " K " + i);
        }

        // Apply initial permutation on M
        IPM = DES.applyPermutation(M, Permutation.IP);

        Utilities.printArray(IPM, "IPM", 4);

        // Divide IPM into left and right half of 32 bits
        L[0] = Utilities.getArray(IPM, Const.LEFT);
        R[0] = Utilities.getArray(IPM, Const.RIGHT);

        Utilities.printArray(L[0], "L0", 4);
        Utilities.printArray(R[0], "R0", 4);

        int[] ER = DES.applyPermutation(R[0], Permutation.E);

        Utilities.printArray(ER, "ER", 6);

        temp = Utilities.xor(K[1], ER);
        Utilities.printArray(temp, "XORed temp", 6);

    }
}

class DES    {
	static int[] applyPermutation(int[] sourceArray, int[] P)	{
		int[] result = new int[P.length];
		
		for(int i = 0; i < P.length; i++)	{
			result[i] = sourceArray[P[i] - 1];
		}
		return result;
	}

    static int[] func(int R[], int K[]) {
        return null;
    }
}

class Permutation	{
    static int []P1 = {  57, 49, 41, 33, 25, 17, 9, 
        1, 58, 50, 42, 34, 26, 18,
        10, 2, 59, 51, 43, 35, 27,
        19, 11, 3, 60, 52, 44, 36,          
        63, 55, 47, 39, 31, 23, 15, 
        7, 62, 54, 46, 38, 30, 22,
        14, 6, 61, 53, 45, 37, 29,
        21, 13, 5, 28, 20, 12, 4 };
    
    static int[] P2 = { 14, 17, 11, 24, 1, 5,
	    3, 28, 15, 6, 21, 10,
		23, 19, 12, 4, 26, 8,
		16, 7, 27, 20, 13, 2,
	    41, 52, 31, 37, 47, 55,
		30, 40, 51, 45, 33, 48,
		44, 49, 39, 56, 34, 53,
		46, 42, 50, 36, 29, 32  };

    static int[] IP = { 58, 50, 42, 34, 26, 18, 10, 2,
        60, 52, 44, 36, 28, 20, 12, 4,
        62, 54, 46, 38, 30, 22, 14, 6,
        64, 56, 48, 40, 32, 24, 16, 8,
        57, 49, 41, 33, 25, 17, 9, 1,
        59, 51, 43, 35, 27, 19, 11, 3,
        61, 53, 45, 37, 29, 21, 13, 5,
        63, 55, 47, 39, 31, 23, 15, 7   };
    
    static int[] E = { 32, 1, 2, 3, 4, 5,
        4, 5, 6, 7, 8, 9,
        8, 9, 10, 11, 12, 13,
        12, 13, 14, 15, 16, 17,
        16, 17, 18, 19, 20, 21, 
        20, 21, 22, 23, 24, 25,
        24, 25, 26, 27, 28, 29,
        28, 29, 30, 31, 32, 1   };
}
