
package ltm18;

/**
 *
 * @author MinhPhuc
 */
public class CaeserCipher {

    public static String encrypt(String text, int key) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char charI = text.charAt(i);
            if (!Character.isLetter(charI)) {
                result.append(charI);
                continue;
            }
            int charCode = Character.isUpperCase(charI) ? 
                    StringUtils.UPPER_A_CODE : 
                    StringUtils.LOWER_A_CODE;
            char ch = (char) (((int) charI + key - charCode) % StringUtils.NUMBER_CHAR + charCode);
            result.append(ch);
        }
        return result.toString();
    }
    
    public static String decrypt(String cipher, int key) {
        key = 26 - key;
        return encrypt(cipher, key);
    }
}
