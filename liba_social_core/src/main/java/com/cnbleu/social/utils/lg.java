package com.cnbleu.social.utils;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class lg {

    /**
     * Priority constant for the println method; use Log.v.
     */
    public static final int VERBOSE = Log.VERBOSE;

    /**
     * Priority constant for the println method; use Log.d.
     */
    public static final int DEBUG = Log.DEBUG;

    /**
     * Priority constant for the println method; use Log.i.
     */
    public static final int INFO = Log.INFO;

    /**
     * Priority constant for the println method; use Log.w.
     */
    public static final int WARN = Log.WARN;

    /**
     * Priority constant for the println method; use Log.e.
     */
    public static final int ERROR = Log.ERROR;

    /**
     * Priority constant for the println method.
     */
    public static final int ASSERT = Log.ASSERT;

    public static boolean RELEASE = false;

    private static String sTag = "lg";
    public static boolean sDebug = true;
    public static boolean sTraceMethd = true;

    private lg() {
        // hide
    }

    /**
     * Enable the debug.
     * <p/>
     * <b><font color='red'>Note!!</font></b>
     * Under none debug mode, WARN & ERROR log will be printed.
     *
     * @param debug true or false.
     */
    public static void setDebug(boolean debug) {
        lg.sDebug = debug;
    }

    /**
     * Enable trace method info.
     *
     * @param traceMethd true or false.
     */
    public static void setTraceMethd(boolean traceMethd) {
        lg.sTraceMethd = traceMethd;
    }

    /**
     * Set the application's tag.
     * <p/>
     * Defautl is 'lg'. You can filter the log info by 'lg' or your application's tag.
     *
     * @param tag Your application's tag.
     */
    public static void setAppTag(String tag) {
        lg.sTag = tag;
    }

    /**
     * Log verbose message
     *
     * @param msg the message.
     */
    public static void v(String msg) {
        v(null, msg, (Throwable) null);
    }


    /**
     * Log verbose message
     *
     * @param msg the message.
     * @param tr  the exception
     */
    public static void v(String msg, Throwable tr) {
        v("", msg, tr);
    }

    /**
     * Log verbose message
     *
     * @param tag the message tag
     * @param msg the message.
     */
    public static void v(String tag, String msg) {
        v(tag, msg, (Throwable) null);
    }

    /**
     * Log verbose message
     *
     * @param tag the message tag.
     * @param tr  the exception.
     * @param msg the message.
     */
    public static void v(String tag, String msg, Throwable tr) {
        println(VERBOSE, tag, msg, tr);
    }

    /**
     * Log verbose message
     *
     * @param tag    the message tag.
     * @param format the message formater.
     * @param msgs   the messages.
     */
    public static void v(String tag, String format, Object... msgs) {
        v(tag, getFormatMsg(format, msgs), (Throwable) null);
    }


    /**
     * Log debug message
     *
     * @param msg the message.
     */
    public static void d(String msg) {
        d(null, msg, (Throwable) null);
    }


    /**
     * Log debug message
     *
     * @param msg the message.
     * @param tr  the exception
     */
    public static void d(String msg, Throwable tr) {
        d("", msg, tr);
    }

    /**
     * Log debug message
     *
     * @param tag the message tag
     * @param msg the message.
     */
    public static void d(String tag, String msg) {
        d(tag, msg, (Throwable) null);
    }

    /**
     * Log debug message
     *
     * @param tag the message tag.
     * @param tr  the exception.
     * @param msg the message.
     */
    public static void d(String tag, String msg, Throwable tr) {
        println(DEBUG, tag, msg, tr);
    }

    /**
     * Log debug message
     *
     * @param tag    the message tag.
     * @param format the message formater.
     * @param msgs   the messages.
     */
    public static void d(String tag, String format, Object... msgs) {
        d(tag, getFormatMsg(format, msgs), (Throwable) null);
    }


    /**
     * Log info message
     *
     * @param msg the message.
     */
    public static void i(String msg) {
        i(null, msg, (Throwable) null);
    }


    /**
     * Log info message
     *
     * @param msg the message.
     * @param tr  the exception
     */
    public static void i(String msg, Throwable tr) {
        i("", msg, tr);
    }

    /**
     * Log info message
     *
     * @param tag the message tag
     * @param msg the message.
     */
    public static void i(String tag, String msg) {
        i(tag, msg, (Throwable) null);
    }

    /**
     * Log info message
     *
     * @param tag the message tag.
     * @param tr  the exception.
     * @param msg the message.
     */
    public static void i(String tag, String msg, Throwable tr) {
        println(INFO, tag, msg, tr);
    }

    /**
     * Log info message
     *
     * @param tag    the message tag.
     * @param format the message formater.
     * @param msgs   the messages.
     */
    public static void i(String tag, String format, Object... msgs) {
        i(tag, getFormatMsg(format, msgs), (Throwable) null);
    }


    /**
     * Log warn message
     *
     * @param msg the message.
     */
    public static void w(String msg) {
        w(null, msg, (Throwable) null);
    }


    /**
     * Log warn message
     *
     * @param msg the message.
     * @param tr  the exception
     */
    public static void w(String msg, Throwable tr) {
        w("", msg, tr);
    }

    /**
     * Log warn message
     *
     * @param tag the message tag
     * @param msg the message.
     */
    public static void w(String tag, String msg) {
        w(tag, msg, (Throwable) null);
    }

    /**
     * Log warn message
     *
     * @param tag the message tag.
     * @param tr  the exception.
     * @param msg the message.
     */
    public static void w(String tag, String msg, Throwable tr) {
        println(WARN, tag, msg, tr);
    }

    /**
     * Log warn message
     *
     * @param tag    the message tag.
     * @param format the message formater.
     * @param msgs   the messages.
     */
    public static void w(String tag, String format, Object... msgs) {
        w(tag, getFormatMsg(format, msgs), (Throwable) null);
    }


    /**
     * Log error message
     *
     * @param msg the message.
     */
    public static void e(String msg) {
        e(null, msg, (Throwable) null);
    }


    /**
     * Log error message
     *
     * @param msg the message.
     * @param tr  the exception
     */
    public static void e(String msg, Throwable tr) {
        e("", msg, tr);
    }

    /**
     * Log error message
     *
     * @param tag the message tag
     * @param msg the message.
     */
    public static void e(String tag, String msg) {
        e(tag, msg, (Throwable) null);
    }

    /**
     * Log error message
     *
     * @param tag the message tag.
     * @param tr  the exception.
     * @param msg the message.
     */
    public static void e(String tag, String msg, Throwable tr) {
        println(ERROR, tag, msg, tr);
    }

    /**
     * Log error message
     *
     * @param tag    the message tag.
     * @param format the message formater.
     * @param msgs   the messages.
     */
    public static void e(String tag, String format, Object... msgs) {
        e(tag, getFormatMsg(format, msgs), (Throwable) null);
    }


    /**
     * Get the formated message.
     *
     * @param format the message formater.
     * @param msgs   the messages.
     *
     * @return The formated string meesage.
     */
    public static String getFormatMsg(String format, Object... msgs) {
        try {
            return String.format(format, msgs);
        } catch (Exception e) {
            return String.valueOf(msgs);
        }
    }

    /**
     * 获取类名与方法名
     */
    private static String getLogTag() {
        StringBuilder builder = new StringBuilder();
        try {
            StackTraceElement stes[] = Thread.currentThread().getStackTrace();
            StackTraceElement ste = stes[6];
            final String steStr = ste.toString();
            String fileName = ste.getFileName();
            builder.append(fileName.substring(0, fileName.lastIndexOf("") + 1));
            builder.append(ste.getMethodName());
            builder.append(steStr.substring(steStr.lastIndexOf("("), steStr.length()));
        } catch (Exception e) {
            // ignore
        }
        return builder.toString();
    }

    /**
     * {@link Log#println(int, String, String)}
     *
     * @param priority {@link #VERBOSE}, {@link #DEBUG}, {@link #WARN}, {@link #ERROR}, {@link #ASSERT}
     * @param tag      the tag of message
     * @param msg      the message
     */
    public static void println(int priority, String tag, String msg) {
        lg.println(priority, tag, msg, null);
    }

    /**
     * {@link Log#println(int, String, String)}
     * <p/>
     * /**
     * {@link Log#println(int, String, String)}
     *
     * @param priority {@link #VERBOSE}, {@link #DEBUG}, {@link #WARN}, {@link #ERROR}, {@link #ASSERT}
     * @param tag      the tag of message
     * @param msg      the message
     * @param tr       the exception
     */
    public static void println(int priority, String tag, String msg, Throwable tr) {
        // Under the none debug model, only debug WARN & ERROR log.
        if (!sDebug && (WARN != priority && ERROR != priority)) {
            return;
        }

        StringBuilder builder = new StringBuilder();
        builder.append("[");
        builder.append(sTag);
        builder.append("]");

        if (!TextUtils.isEmpty(tag)) {
            if (tag.contains("[")) {
                builder.append(tag);
            } else {
                builder.append("[");
                builder.append(tag);
                builder.append("]");
            }
        }

        builder.append(msg);
        builder.append("\n");
        builder.append(Log.getStackTraceString(tr));

        String logTag = "";
        if (sTraceMethd) {
            logTag = getLogTag();
        }

        Log.println(priority, logTag, builder.toString());
    }

    /**
     * Get format string from list.
     *
     * @param list Instance of List.>
     *
     * @return Formated list string.
     */
    public static String getString(List<?> list) {
        if (null == list) {
            return "NULL";
        }

        final int size = list.size();
        StringBuilder builder = new StringBuilder();
        builder.append("\r\t");
        builder.append("List, size=");
        builder.append(size);
        builder.append(";");

        builder.append("\r\t{");
        for (int i = 0; i < size; i++) {
            builder.append("\r\t\t");
            builder.append(i);
            builder.append(": ");
            builder.append(list.get(i).toString());
            builder.append(",");
        }
        builder.append("\r\t}");
        return builder.toString();
    }

    /**
     * Get formated map string.
     *
     * @param map Instance of Map.
     *
     * @return Formated map string.
     */
    public static String getString(Map<?, ?> map) {
        if (null == map) {
            return "NULL";
        }

        final int size = map.size();
        StringBuilder builder = new StringBuilder();
        builder.append("\r\t");
        builder.append("Map, size=");
        builder.append(size);
        builder.append(";");

        builder.append("\r\t{");
        for (Map.Entry entry : map.entrySet()) {
            builder.append("\r\t\t");
            builder.append(entry.getKey().toString());
            builder.append(" = ");
            builder.append(entry.getValue().toString());
            builder.append(",");
        }
        builder.append("\r\t}");

        return builder.toString();
    }

    /**
     * Get formated bundle string.
     *
     * @param bundle Instance of Bundle.
     *
     * @return Formated bundle string.
     */
    public static String getString(Bundle bundle) {
        if (null == bundle) {
            return "NULL";
        }

        final Set<String> keySet = bundle.keySet();
        final int size = keySet.size();
        StringBuilder builder = new StringBuilder();
        builder.append("\r\t");
        builder.append("Bundle, size=");
        builder.append(size);
        builder.append(",");

        builder.append("\r\t{");
        for (String key : keySet) {
            builder.append("\r\t\t");
            builder.append(key);
            builder.append(" = ");
            builder.append(bundle.get(key));
            builder.append(",");
        }
        builder.append("\r\t}");

        return builder.toString();
    }

    // TODO
    public static String getString(String json) {
        if (null == json) {
            return "NULL";
        }

        StringBuilder builder = new StringBuilder();
        builder.append("\r\t");
        builder.append("JSONObject,");
        builder.append("\r\t\t");
        String message = null;
        try {
            if (json.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(json);
                message = jsonObject.toString(12);
            } else if (json.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(json);
                message = jsonArray.toString(12);
            }
        } catch (JSONException e) {
            message = "String convert json error!!";
        } finally {
            if (TextUtils.isEmpty(message)) {
                message = "NULL";
            }
        }

        builder.append(message);

        return builder.toString();
    }

}