/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morse.compiler.engine;

/**
 *
 * @author borya
 */
public final class MorseCode {
    private byte[] hex;
    private String content;
    
    public MorseCode(byte[] hex) {
        this.hex = hex;
    }

    public MorseCode(String content) {
        this.content = content;
    }

    public byte[] getHex() {
        return hex;
    }

    public void setHex(byte[] hex) {
        this.hex = hex;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
}
