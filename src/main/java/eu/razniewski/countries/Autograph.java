/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.razniewski.countries;

import java.util.Objects;
import org.bukkit.map.MapPalette;

/**
 *
 * @author adamr
 */
public class Autograph {
    private byte bgColor;
    private int xSign;
    private int ySign;
    private String additionalText;
    private String nickname;
    private String nicknamePrefix;

    public Autograph(byte bgColor, int xSign, int ySign, String additionalText, String nickname, String nicknamePrefix) {
        this.bgColor = bgColor;
        this.xSign = xSign;
        this.ySign = ySign;
        this.additionalText = additionalText;
        this.nickname = nickname;
        this.nicknamePrefix = nicknamePrefix;
    }

    public byte getBgColor() {
        return bgColor;
    }

    public int getxSign() {
        return xSign;
    }

    public int getySign() {
        return ySign;
    }

    public String getAdditionalText() {
        return additionalText;
    }

    public String getNickname() {
        return nickname;
    }

    public String getNicknamePrefix() {
        return nicknamePrefix;
    }

    @Override
    public String toString() {
        return "Autograph{" + "bgColor=" + bgColor + ", xSign=" + xSign + ", ySign=" + ySign + ", additionalText=" + additionalText + ", nickname=" + nickname + ", nicknamePrefix=" + nicknamePrefix + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.bgColor;
        hash = 23 * hash + this.xSign;
        hash = 23 * hash + this.ySign;
        hash = 23 * hash + Objects.hashCode(this.additionalText);
        hash = 23 * hash + Objects.hashCode(this.nickname);
        hash = 23 * hash + Objects.hashCode(this.nicknamePrefix);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Autograph other = (Autograph) obj;
        if (this.bgColor != other.bgColor) {
            return false;
        }
        if (this.xSign != other.xSign) {
            return false;
        }
        if (this.ySign != other.ySign) {
            return false;
        }
        if (!Objects.equals(this.additionalText, other.additionalText)) {
            return false;
        }
        if (!Objects.equals(this.nickname, other.nickname)) {
            return false;
        }
        if (!Objects.equals(this.nicknamePrefix, other.nicknamePrefix)) {
            return false;
        }
        return true;
    }

    
    
    
    
}
