/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morse.compiler.engine;

import java.util.NoSuchElementException;

/**
 *
 * @author borya
 */
public enum MorseAlpha {

    WPAUSE(0b00, (char) 0x0020),
    A(0b0110, (char) 0x0041), BTA(0b0110100110, (char) 0x00c0), AE(0b01100110, (char) 0x00c4), B(0b10010101, (char) 0x0042), C(0b10011001, (char) 0x0043),
    CH(0b10101010, (char) 0x00c7), D(0b100101, (char) 0x0044), E(0b01, (char) 0x0045), EBT(0b0110010110, (char) 0x00c8), ET(0b0101100101, (char) 0x00c9),
    F(0b01011001, (char) 0x0046), G(0b101001, (char) 0x0047), H(0b01010101, (char) 0x0048), I(0b0101, (char) 0x0049), J(0b01101010, (char) 0x004a), K(0b100110, (char) 0x004b),
    L(0b01100101, (char) 0x004c), M(0b1010, (char) 0x004d), N(0b1001, (char) 0x004e), NT(0b1010011010, (char) 0x00d1), O(0b101010, (char) 0x004f), OE(0b10101001, (char) 0x00d6),
    P(0b01101001, (char) 0x0050), Q(0b10100110, (char) 0x0051), R(0b011001, (char) 0x0052), S(0b010101, (char) 0x0053), SZ(0b01010110100101, (char) 0x00df),
    T(0b10, (char) 0x0054), U(0b010110, (char) 0x0055), UE(0b01011010, (char) 0x00dc), V(0b01010110, (char) 0x0056), W(0b011010, (char) 0x0057), X(0b10010110, (char) 0x0058),
    Y(0b10011010, (char) 0x0059), Z(0b10100101, (char) 0x005a),
    ONE(0b0110101010, (char) 0x0031), TWO(0b0101101010, (char) 0x0032), THREE(0b0101011010, (char) 0x0033), FOUR(0b0101010110, (char) 0x0034),
    FIVE(0b0101010101, (char) 0x0035), SIX(0b1001010101, (char) 0x0036), SEVEN(0b1010010101, (char) 0x0037), EIGHT(0b1010100101, (char) 0x0038),
    NINE(0b1010101001, (char) 0x0039), ZERO(0b1010101010, (char) 0x0030),
    AAA(0b011001100110, (char) 0x002e), MIM(0b101001011010, (char) 0x002c), OS(0b101010010101, (char) 0x003a), NNN(0b100110011001, (char) 0x003b),
    IMI(0b010110100101, (char) 0x003f), BA(0b100101010110, (char) 0x002d), UK(0b010110100110, (char) 0x005f), KN(0b1001101001, (char) 0x0028),
    KK(0b100110100110, (char) 0x0029), JN(0b011010101001, (char) 0x0027), BT(0b1001010110, (char) 0x0060), AR(0b0110011001, (char) 0x002b),
    DN(0b1001011001, (char) 0x002f), AC(0b011010011001, (char) 0x0040),
    SKA(0b1001100110, '\0'), SBT(0b1001010110, (char) 0x0016), SAR(0b0110011001, (char) 0x0085), SVE(0b0101011001, (char) 0x000a),
    SSK(0b010101100110, (char) 0x0004), SOS(0b010101101010010101, (char) 0x0091), SHH(0b0101010101010101, (char) 0x0018);

    private int val;
    private char ch;

    private MorseAlpha(int val, char ch) {
        this.val = val;
        this.ch = ch;
    }

//    public static final int LPAUSE = 0b00;
//    public static final int WPAUSE = 0xff;
//    public static final int DIT = 0b01;
//    public static final int DAH = 0b10;
//    
//    public static final int A = 0b0110;
//    public static final int BTA = 0b0110100110;
//    public static final int AE = 0b01100110;
//    public static final int B = 0b10010101;
//    public static final int C = 0b10011001;
//    public static final int CH = 0b10101010;
//    public static final int D = 0b100101;
//    public static final int E = 0b01;
//    public static final int BTE = 0b0110010110;
//    public static final int TE = 0b0101100101;
//    public static final int F = 0b01011001;
//    public static final int G = 0b101001;
//    public static final int H = 0b01010101;
//    public static final int I = 0b0101;
//    public static final int J = 0b01101010;
//    public static final int K = 0b100110;
//    public static final int L = 0b01100101;
//    public static final int M = 0b1010;
//    public static final int N = 0b1001;
//    public static final int NT = 0b1010011010;
//    public static final int O = 0b101010;
//    public static final int OE = 0b10101001;
//    public static final int P = 0b01101001;
//    public static final int Q = 0b10100110;
//    public static final int R = 0b011001;
//    public static final int S = 0b010101;
//    public static final int SZ = 0b01010110100101;
//    public static final int T = 0b10;
//    public static final int U = 0b010110;
//    public static final int UE = 0b01011010;
//    public static final int V = 0b01010110;
//    public static final int W = 0b011010;
//    public static final int X = 0b10010110;
//    public static final int Y = 0b10011010;
//    public static final int Z = 0b10100101;
//    
//    
//    public static final int ONE = 0b0110101010;
//    public static final int TWO = 0b0101101010;
//    public static final int THREE = 0b0101011010;
//    public static final int FOUR = 0b0101010110;
//    public static final int FIVE = 0b0101010101;
//    public static final int SIX = 0b1001010101;
//    public static final int SEVEN = 0b1010010101;
//    public static final int EIGHT = 0b1010100101;
//    public static final int NINE = 0b1010101001;
//    public static final int ZERO = 0b1010101010;
//    
//    public static final int AAA = 0b011001100110;
//    public static final int MIM = 0b101001011010;
//    public static final int OS = 0b101010010101;
//    public static final int NNN = 0b100110011001;
//    public static final int IMI = 0b010110100101;
//    public static final int BA = 0b100101010110;
//    public static final int UK = 0b010110100110;
//    public static final int KN = 0b1001101001;
//    public static final int KK = 0b100110100110;
//    public static final int JN = 0b011010101001;
//    public static final int BT = 0b1001010110;
//    public static final int AR = 0b0110011001;
//    public static final int DN = 0b1001011001;
//    public static final int AC = 0b011010011001;
//    
//    public static final int SKA = 0b1001100110;
//    public static final int SBT = 0b1001010110;
//    public static final int SAR = 0b0110011001;
//    public static final int SVE = 0b0101011001;
//    public static final int SSK = 0b010101100110;
//    public static final int SOS = 0b010101101010010101;
//    public static final int SHH = 0b0101010101010101;
    public int getVal() {
        return val;
    }

    public char getCh() {
        return ch;
    }

    public static MorseAlpha getSymbol(char ch) {
        Class<MorseAlpha> c = MorseAlpha.class;
        MorseAlpha[] maArr = c.getEnumConstants();

        for (int i = 0; i < maArr.length; i++) {
            if (ch == maArr[i].getCh() || (ch - 32) == maArr[i].getCh()/*Character.toString(ch).equalsIgnoreCase(Character.toString(maArr[i].getCh()))*/) {
                return maArr[i];
            }
        }
        throw new NoSuchElementException("invalid morse character");
    }

    public static MorseAlpha getSymbol(int val) {
        Class<MorseAlpha> c = MorseAlpha.class;
        MorseAlpha[] maArr = c.getEnumConstants();

        for (int i = 0; i < maArr.length; i++) {
            if (val == maArr[i].getVal()) {
                return maArr[i];
            }
        }
        throw new NoSuchElementException("invalid morse character: int(" + val + ")");
    }
}
