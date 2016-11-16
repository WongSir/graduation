/** 
**com.lulei.util.ParseMD5 
**/  
 /**   
 *@Description: ���ַ���ת��ΪMD5 
 */   
package news;    

public class ParseMD5 extends Encrypt{  

    /** 
     * @param str 
     * @return 
     * @Author: lulei   
     * @Description:  32λСдMD5 
     */  
    public static String parseStrToMd5L32(String str){  
        return encrypt(str, MD5);  
    }  
      
    /** 
     * @param str 
     * @return 
     * @Author: lulei   
     * @Description: 32λ��дMD5 
     */  
    public static String parseStrToMd5U32(String str){  
        String reStr = parseStrToMd5L32(str);  
        if (reStr != null){  
            reStr = reStr.toUpperCase();  
        }  
        return reStr;  
    }  
      
    /** 
     * @param str 
     * @return 
     * @Author: lulei   
     * @Description: 16λСдMD5 
     */  
    public static String parseStrToMd5U16(String str){  
        String reStr = parseStrToMd5L32(str);  
        if (reStr != null){  
            reStr = reStr.toUpperCase().substring(8, 24);  
        }  
        return reStr;  
    }  
      
    /** 
     * @param str 
     * @return 
     * @Author: lulei   
     * @Description: 16λ��дMD5 
     */  
    public static String parseStrToMd5L16(String str){  
        String reStr = parseStrToMd5L32(str);  
        if (reStr != null){  
            reStr = reStr.substring(8, 24);  
        }  
        return reStr;  
    }  
      
    /** 
     * @param md5L16 
     * @return 
     * @Author:lulei   
     * @Description: ��16λ��md5ת��Ϊlongֵ 
     */  
    public static long parseMd5L16ToLong(String md5L16){  
        if (md5L16 == null) {  
            throw new NumberFormatException("null");  
        }  
        md5L16 = md5L16.toLowerCase();  
        byte[] bA = md5L16.getBytes();  
        long re = 0L;  
        for (int i = 0; i < bA.length; i++) {  
            //����һλ���ַ�ʱ���Ƚ�ǰ���ַ�����Ľ������4λ  
            re <<= 4;  
            //0-9����  
            byte b = (byte) (bA[i] - 48);  
            //A-F��ĸ  
            if (b > 9) {  
                b = (byte) (b - 39);  
            }  
            //��16���Ƶ��ַ�  
            if (b > 15 || b < 0) {  
                throw new NumberFormatException("For input string '" + md5L16);  
            }  
            re += b;  
        }  
        return re;  
    }  
      
    /** 
     * @param str16 
     * @return 
     * @Author:lulei   
     * @Description: ��16���Ƶ��ַ���ת��Ϊlongֵ 
     */  
    public static long parseString16ToLong(String str16){  
        if (str16 == null) {  
            throw new NumberFormatException("null");  
        }  
        //��ת��ΪСд  
        str16 = str16.toLowerCase();  
        //����ַ�����0x��ͷ��ȥ��0x  
        str16 = str16.startsWith("0x") ? str16.substring(2) : str16;  
        if (str16.length() > 16) {  
            throw new NumberFormatException("For input string '" + str16 + "' is to long");  
        }  
        return parseMd5L16ToLong(str16);  
    }  
      
    /** 
     * @param str 
     * @return 
     * @Author:lulei   
     * @Description: ���ַ�������md5 16λ���ܣ�������ת��Ϊlongֵ 
     */  
    public static long parseStringToMd516long(String str) {  
        return parseString16ToLong(parseStrToMd5L16(str));  
    }  
      
    public static void main(String[] args) {  
        System.out.println(ParseMD5.parseStrToMd5L32("nihao"));  
    }  
}  