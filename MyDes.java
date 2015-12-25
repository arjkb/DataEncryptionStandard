
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

    static int[] splice(int[] A, int start, int size)   {
        int[] R = new int[size];
        for(int i = 0; i < size; i++)   {
            R[i] = A[start + i];
        }
        return R;
    }

    static int[] convertToBinArray(int num) {
        int[] result = null;
        switch(num) {
            case 0: result = new int[] {0,0,0,0}; break;
            case 1: result = new int[] {0,0,0,1}; break;
            case 2: result = new int[] {0,0,1,0}; break;
            case 3: result = new int[] {0,0,1,1}; break;
            case 4: result = new int[] {0,1,0,0}; break;
            case 5: result = new int[] {0,1,0,1}; break;
            case 6: result = new int[] {0,1,1,0}; break;
            case 7: result = new int[] {0,1,1,1}; break;
            case 8: result = new int[] {1,0,0,0}; break;
            case 9: result = new int[] {1,0,0,1}; break;
            case 10: result = new int[] {1,0,1,0}; break;
            case 11: result = new int[] {1,0,1,1}; break;
            case 12: result = new int[] {1,1,0,0}; break;
            case 13: result = new int[] {1,1,0,1}; break;
            case 14: result = new int[] {1,1,1,0}; break;
            case 15: result = new int[] {1,1,1,1}; break;
        }
        return result;
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
        int[] result = null;
        int[] ER = applyPermutation(R, Permutation.E);
        int[] temp = Utilities.xor(K, ER);
        int[] temp_6bit = null;
        int[] temp_4bit = null;

        for(int i = 0, k = 1; i < 48; i += 6, k++)  {
            temp_6bit = Utilities.splice(temp, i, 6);
            temp_4bit = sprocess(i+1, temp_6bit);

            result = Utilities.combineArray(result, temp_4bit);
        }

        applyPermutation(result, Permutation.P);

        return result;
    }

    static int[] sprocess(int sboxnum, int [] A)  {
        /* Convert the 6 bits in A to
         * 4 bits after processing through S-Box
         */

        int row = 0;
        int col = 0;
        int num = 0;

        int[] result = null;

        row = (A[5] * (int)Math.pow(2, 0)) + (A[0] * (int)Math.pow(2, 1));

        for(int i = 0; i < 4; i++)  {
            col += A[4-i] * Math.pow(2, i);
        }

        switch(sboxnum) {
            case 1: num = S_Box.S1[row][col]; break;
            case 2: num = S_Box.S2[row][col]; break;
            case 3: num = S_Box.S3[row][col]; break;
            case 4: num = S_Box.S4[row][col]; break;
            case 5: num = S_Box.S5[row][col]; break;
            case 6: num = S_Box.S6[row][col]; break;
            case 7: num = S_Box.S7[row][col]; break;
            case 8: num = S_Box.S8[row][col]; break;
        }
        result = Utilities.convertToBinArray(num);
        return result;
    } // end of sprocess()
}

class S_Box {
    static int[][] S1 = {
        {14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7}, 
        {0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8},
        {4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0},
        {15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}  };

    static int[][] S2 = {
        {15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10},
        {3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5},
        {0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15},
        {13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 12, 9}  };

    static int[][] S3 = {
        {10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8},
        {13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1},
        {13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7},
        {1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12}  };

    static int[][] S4 = {
        {7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15},
        {13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9},
        {10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4},
        {3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14}  };

    static int[][] S5 = {
        {2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9},
        {14, 11, 2, 12, 4, 6, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6},
        {4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14},
        {11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3}  };
    
    static int[][] S6 = {
        {12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11},
        {10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8},
        {9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6},
        {4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13}  };

    static int[][] S7 = {
        {4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1},
        {13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6},
        {1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2},
        {6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12}  };

    static int[][] S8 = {
        {13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7},
        {1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2},
        {7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8},
        {2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11}  }; 

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

    static int[] P = {  16, 7, 20, 21,
                        29, 12, 28, 17,
                        1, 15, 23, 26,
                        5, 18, 31, 10,
                        2, 8, 24, 14,
                        32, 27, 3, 9,
                        19, 13, 30, 6,
                        22, 11, 4, 25   };
}
