package com.example.a98611.test.gestureLock;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LockPatternUtils {
	
	//private static final String TAG = "LockPatternUtils";
	private final static String KEY_LOCK_PWD = "lock_pwd";
	
	
	private static Context mContext;
	
	private static SharedPreferences preference;
	
	//private final ContentResolver mContentResolver;
	
	 public LockPatternUtils(Context context) {
	        mContext = context;
	        preference = PreferenceManager.getDefaultSharedPreferences(mContext);
	       // mContentResolver = context.getContentResolver();
	 }
	
	 /**
     * Deserialize a pattern.
     * @param string The pattern serialized with {@link #patternToString}
     * @return The pattern.
     */
    public static List<LockPatternView.Cell> stringToPattern(String string) {
        List<LockPatternView.Cell> result = new ArrayList<LockPatternView.Cell>();

        final byte[] bytes = string.getBytes();
        for (int i = 0; i < bytes.length; i++) {
            byte b = bytes[i];
            result.add(LockPatternView.Cell.of(b / 3, b % 3));
        }
        return result;
    }

    /**
     * Creates a String representation of the byte[] passed. 
     * The result is surrounded by brackets ("[]"), each element is converted to a String via the String.valueOf(int) and 
     * separated by ", ". If the array is null, then "null" is returned.
     * 
     * Serialize a pattern.
     * @param pattern The pattern.
     * @return The pattern in string form.
     */
    public static String patternToString(List<LockPatternView.Cell> pattern) {
        if (pattern == null) {
            return "";
        }
        final int patternSize = pattern.size();

        byte[] res = new byte[patternSize];
        for (int i = 0; i < patternSize; i++) {
            LockPatternView.Cell cell = pattern.get(i);
            res[i] = (byte) (cell.getRow() * 3 + cell.getColumn());
        }
        return Arrays.toString(res);
    }
    /**
     * 返回格式为 01234... 的字符串
     * 
     * @param pattern
     * @return
     */
    public static String patternToString2(List<LockPatternView.Cell> pattern){
    	if (pattern == null || pattern.size()==0) {
            return null;
        }
    	StringBuffer sb = new StringBuffer();
        final int patternSize = pattern.size();

        byte[] res = new byte[patternSize];
        for (int i = 0; i < patternSize; i++) {
            LockPatternView.Cell cell = pattern.get(i);
            res[i] = (byte) (cell.getRow() * 3 + cell.getColumn());
            sb.append(res[i]);
        }
		return sb.toString();
    	
    }
    /**
     * 保存手势密码到本地preference
     * @param pattern
     */
    public void saveLockPattern(List<LockPatternView.Cell> pattern){
    	Editor editor = preference.edit();
    	editor.putString(KEY_LOCK_PWD, patternToString2(pattern));
    	editor.commit();
    }
    /**
     *  DESUtils.encrypt(MD5Util.getMD5String())加密过后的密码
     * @param enString DESUtils.encrypt(MD5Util.getMD5String())
     */
    public void saveLockPattern2(String enString){
    	Editor editor = preference.edit();
    	editor.putString(KEY_LOCK_PWD, enString);
    	editor.commit();
    }
    /**
     *  DESUtils.encrypt(MD5Util.getMD5String())加密过后的密码
     * @param enString DESUtils.encrypt(MD5Util.getMD5String())
     */
    public void saveLockPattern2(String userId, String enString){
    	Editor editor = preference.edit();
    	editor.putString(KEY_LOCK_PWD, "Key"+userId+"Value"+enString);
    	editor.commit();
    }
    /**
     * 获取加密过的手势密码，经过DESUtils.encrypt(MD5Util.getMD5String())加密
     * @return String  return "" if this Patern does not exist
     */
    public String getLockPaternString(){
    	return preference.getString(KEY_LOCK_PWD, "");
    }
    
    /**
     * -1 没有设置原密码，1 密码一致，0密码错误
     * @param pattern
     * @return
     */
    public int checkPattern(List<LockPatternView.Cell> pattern) {
    	String stored = getLockPaternString();
    	if(!TextUtils.isEmpty(stored)){
    		return stored.equals(patternToString2(pattern))?1:0;
    	}
    	return -1;
    }
    /**
     * -1 没有设置原密码，1 密码一致，0密码错误
     * @param pattern
     * @return
     */
    public int checkPattern2(List<LockPatternView.Cell> pattern) {
    	String stored = getLockPaternString();
    	if(!TextUtils.isEmpty(stored)){
            return stored.equals(pattern)?1:0;
//    		return stored.equals(DESUtils.getInstance().setKEY(DESUtils.KEY_LockPwd).encrypt(MD5Util.getMD5String(patternToString2(pattern))))?1:0;
    	}
    	return -1;
    }

    /**
     * -1 没有设置原密码，1 密码一致，0密码错误
     * @param pattern
     * @return
     */
    /*public int checkPattern3(LockInfodb lockInfodb, String phone, List<LockPatternView.Cell> pattern) {
    	String stored = Util.getLockUserPhoneById(lockInfodb, phone);
    	if(!TextUtils.isEmpty(stored)){
            return stored.equals((patternToString2(pattern)))?1:0;
//    		return stored.equals(DESUtils.getInstance().setKEY(DESUtils.KEY_LockPwd).encrypt(MD5Util.getMD5String(patternToString2(pattern))))?1:0;
    	}
    	return -1;
    }*/
    /**
     * 清空所有的手势密码
     */
    /**
     * 
     */
//    public void clearLock() {
//    	saveLockPattern2(null);
//    }
    
    /**
     * 清空手势
     * @param lockInfodb
     * @param phone
     */
  /*  public void clearLock(LockInfodb lockInfodb,String phone){
    	lockInfodb.delete(phone);
    }*/
  

}
