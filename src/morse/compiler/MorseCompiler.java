/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morse.compiler;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import morse.compiler.util.ByteUtil;

/**
 *
 * @author borya
 */
public class MorseCompiler {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        /*System.out.println(Long.toHexString(Long.MAX_VALUE));
            byte[] msg = new byte[16];
            try {
            //msg = ByteUtil.morseLetter(ByteUtil.morseLetter(ByteUtil.morseLetter(ByteUtil.morseLetter(msg, MorseAlpha.L), MorseAlpha.O), MorseAlpha.V), MorseAlpha.E);
            msg = ByteUtil.morseLetters(msg, MorseAlpha.N, MorseAlpha.D, MorseAlpha.SKA, MorseAlpha.L, MorseAlpha.O, MorseAlpha.V, MorseAlpha.E);
            } catch (IOException ex) {
            Logger.getLogger(MorseCompiler.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            ByteBuffer bb = ByteBuffer.wrap(msg);
            
            
            System.out.println(Long.toHexString(bb.getLong()));
            
            Arrays.stream(MorseAlpha.class.getEnumConstants())
            .forEach(e -> System.out.println(e.name() + "(" + Long.toHexString(e.getVal()) + ")"));*/
 /*
            List<Byte> l = new ArrayList<>();
            
            ByteUtil.addSymbol(l, 0xffffa);
            
            l.stream().mapToInt(ByteUtil::byteValue).forEach(System.out::println);
         */
        // TODO: Byte Array in eine große Ganzzahl verwandeln ohne den Verlust des Vorzeichens -> Stringverkettung!
        /*
            if(ByteUtil.writeToBinFile("love.bin", ByteUtil.encode("LoVE u HONEY"))) {
            System.out.println("Datei erfolgreich geschrieben.");
            }
            else System.out.println("Fehler.");
         */
         
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./laternen.txt")))) {
            String content = br.lines().reduce("", (s1,s2) -> s1 + "\n" + s2);
            byte[] encData = ByteUtil.encode(content);
            ByteUtil.writeToBinFile("./love.bin", encData);
        } catch (IOException ex) {
            Logger.getLogger(MorseCompiler.class.getName()).log(Level.SEVERE, null, ex);
        }

//        try {
//            Files.lines(Paths.get("./laternen.txt")).forEach(System.out::println);
//        } catch (IOException ex) {
//            Logger.getLogger(MorseCompiler.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        byte[] encData = ByteUtil.encode("Ich gehe jetzt schlafen, denn das habe ich mir verdient");
//        System.out.println(ByteUtil.decode(encData));

        //Arrays.stream(bArr).mapToInt(ByteUtil::byteValue).mapToObj(Integer::toHexString).forEach(System.out::println);
    }

}
