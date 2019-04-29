/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morse.compiler.util;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import morse.compiler.engine.MorseAlpha;

/**
 *
 * @author borya
 */
public class ByteUtil {

    private static final byte[] BMODES = {(byte) 0xfd, (byte) 0xfe, (byte) 0xff};
    private static byte mode = 0;

    /**
     * @deprecated @param l
     * @param i
     * @return
     */
    private static Long addSymbol(long l, int i) {
        return (l << (i > 0xffff ? 24 : (i > 0xff ? 16 : 8))) + i;
    }

    /**
     * @deprecated @param txt
     * @param symbol
     * @return
     * @throws IOException
     */
    public static byte[] morseLetter(byte[] txt, MorseAlpha symbol) throws IOException {
        ByteBuffer buffer = ByteBuffer.wrap(txt);
        Long newTxtVal = addSymbol(buffer.getLong(), symbol.getVal());

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try (DataOutputStream dos = new DataOutputStream(baos)) {
            dos.writeLong(newTxtVal);
            return baos.toByteArray();
        }
    }

    /**
     * @deprecated @param txt
     * @param symbols
     * @return
     * @throws IOException
     */
    public static byte[] morseLetters(byte[] txt, MorseAlpha... symbols) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ByteBuffer buffer = ByteBuffer.wrap(txt);
        Long newTxtVal = addSymbol(buffer.getLong(), symbols[0].getVal());

        try (DataOutputStream dos = new DataOutputStream(baos)) {

            for (int i = 1; i < symbols.length; i++) {
                newTxtVal = addSymbol(newTxtVal, symbols[i].getVal());
            }

            dos.writeLong(newTxtVal);
            return baos.toByteArray();
        }
    }

    public static void addSymbol(List<Byte> l, int sym) {
        int bytes = sym > 0xffff ? 2 : (sym > 0xff ? 1 : 0);
        int[] bits = {0x000000ff, 0x0000ff00, 0x00ff0000};

        if (mode != BMODES[bytes]) {
            l.add(BMODES[bytes]);
            mode = BMODES[bytes];
        }

        for (int i = bytes; i >= 0; i--) {
            l.add((byte) ((sym & bits[i]) >> (8 * i)));
        }

    }

    public static int byteValue(byte b) {
        return (int) (b & 0xff);
    }

    public static byte[] morseLetter(MorseAlpha symbol) {
        List<Byte> buffer = new ArrayList<>();
        addSymbol(buffer, symbol.getVal());

        byte[] bArr = new byte[buffer.size()];
        for (int i = 0; i < buffer.size(); i++) {
            bArr[i] = buffer.get(i);
        }

        return bArr;
    }

    public static byte[] morseLetters(MorseAlpha... symbols) {
        List<Byte> buffer = new ArrayList<>();
        for (int i = 0; i < symbols.length; i++) {
            addSymbol(buffer, symbols[i].getVal());
            mode=0;
        }

        byte[] bArr = new byte[buffer.size()];
        for (int i = 0; i < buffer.size(); i++) {
            bArr[i] = buffer.get(i);
        }

        return bArr;
    }

    public static boolean writeToBinFile(String filename, byte[] data) {
        try (ByteArrayOutputStream bao = new ByteArrayOutputStream();
                FileOutputStream fao = new FileOutputStream(filename)) {
            bao.write(data);
            bao.writeTo(fao);
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }

        return true;
    }

    public static byte[] encode(String txt) {
        char[] chArr = txt.toCharArray();
        MorseAlpha[] maArr = new MorseAlpha[chArr.length];
        for (int i = 0; i < chArr.length; i++) {
            maArr[i] = MorseAlpha.getSymbol(chArr[i]);
        }
        return morseLetters(maArr);
    }

    public static int readBytes(byte[] bin, int pos, int symbols)
            throws ArrayIndexOutOfBoundsException {
        int res = 0;
        try {
            for (int i = pos; i < pos + symbols; i++) {
                res = (res << 8) + byteValue(bin[i]);
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            ex.printStackTrace();
            throw new ArrayIndexOutOfBoundsException("read beyond end of morse code");
        }
        return res;
    }

    public static String decode(byte[] morse) {
        char ch = '\0';
        StringBuilder sb = new StringBuilder();
        int mode = 0x00;

        for (int i = 0; i < morse.length;) {
            try {
                switch (morse[i]) {
                    case (byte) 0xfd: {
                        mode = 0xfd;
                        ch = MorseAlpha.getSymbol(readBytes(morse, i + 1, 1)).getCh();
                        i+=2;
                    }
                    break;
                    case (byte) 0xfe: {
                        mode = 0xfe;
                        ch = MorseAlpha.getSymbol(readBytes(morse, i + 1, 2)).getCh();
                        i += 3;
                    }
                    break;
                    case (byte) 0xff: {
                        mode = 0xff;
                        ch = MorseAlpha.getSymbol(readBytes(morse, i + 1, 3)).getCh();
                        i += 4;
                    }
                    break;
                    default: {
                        if (mode != 0) {
                            ch = MorseAlpha.getSymbol(
                                    readBytes(morse, i, mode == 0xfd ? 1 : (mode == 0xfe ? 2 : 3)))
                                    .getCh();
                            i += (mode == 0xfd ? 1 : (mode == 0xfe ? 2 : 3));
                        }
                        else {
                            throw new IllegalArgumentException("binary is no valid morse code");
                        }
                    }
                }
                sb.append(ch);
            } catch (NoSuchElementException ex) {
                ex.printStackTrace();
                throw new IllegalArgumentException("binary is no valid morse code", ex);
            }
        }

        return sb.toString();
    }
    
    public static byte[] concat(byte[]... messages) {
        int size = 4*messages.length;
        for(int i=0; i<messages.length; i++) {
            size += messages[i].length;
        }
        
        byte[] cat = new byte[size];
        byte[] start = morseLetter(MorseAlpha.SKA),
                end = morseLetter(MorseAlpha.SAR);
        int lnIndex=0;
        
        for(int i=-1; i<size;) {
            cat[++i] = start[0];
            cat[++i] = start[1];
            
            for(int j=0; i<messages[lnIndex].length; j++) {
                cat[++i] = messages[lnIndex][j];
            }
            lnIndex++;
            
            cat[++i] = end[0];
            cat[++i] = end[1];
        }
        
        return cat;
    }
}
