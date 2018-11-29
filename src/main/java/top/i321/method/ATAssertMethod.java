package top.i321.method;

import org.testng.Assert;

public class ATAssertMethod {

    public static boolean assertEquals(String str1, String str2) {
        boolean result = true;
        try {
            Assert.assertEquals(str1, str2);
        } catch (AssertionError e) {
            result = false;
        }
        return result;
    }

    public static boolean assertNotEquals(String str1, String str2) {
        boolean result = true;
        try {
            Assert.assertNotEquals(str1, str2);
        } catch (AssertionError e) {
            result = false;
        }
        return result;
    }

    public static boolean assertNull(String str1) {
        boolean result = true;
        try {
            Assert.assertNull(str1);
        } catch (AssertionError e) {
            result = false;
        }
        return result;
    }

    public static boolean assertNotNull(String str1) {
        boolean result = true;
        try {
            Assert.assertNotNull(str1);
        } catch (AssertionError e) {
            result = false;
        }
        return result;
    }

    public static boolean assertTrue(boolean boo) {
        boolean result = true;
        try {
            Assert.assertTrue(boo);
        } catch (AssertionError e) {
            result = false;
        }
        return result;
    }

    public static boolean assertFalse(boolean boo) {
        boolean result = true;
        try {
            Assert.assertFalse(boo);
        } catch (AssertionError e) {
            result = false;
        }
        return result;
    }

    public static boolean assertInclude(String str1, String str2) {
        int i = str1.indexOf(str2);
        return i == -1 ? false : true;
    }

    public static boolean assertExclude(String str1, String str2) {
        int i = str1.indexOf(str2);
        return i != -1 ? false : true;
    }

    public static void main(String[] args) {
        System.out.println(assertExclude("1234", "5"));
    }

}
