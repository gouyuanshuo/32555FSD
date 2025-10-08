package edu.uts.uniapp.util;

public final class RegexConstants {
    private RegexConstants() {}

    /** 邮箱需以 @university.com 结尾 */
    public static final String EMAIL = ".+@university\\.com";

    /** 密码：以大写字母开头，后接 ≥5 个字母，最后接 ≥3 个数字 */
    public static final String PASSWORD = "^[A-Z][a-zA-Z]{5,}\\d{3,}$";
    // 如果你更严格地理解为“至少五个字母（含首字母）”，把上面改成 {4,} 即可：
    // public static final String PASSWORD = "^[A-Z][a-zA-Z]{4,}\\d{3,}$";
}
