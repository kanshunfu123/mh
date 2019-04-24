package com.xiaowei.mh.common.util.hex;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by pc1 on 13-11-14.
 */
public class JcStringUtils {

    private static Pattern pattern = Pattern.compile("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");

    public static boolean isNullOrBlank(String str) {
        if (org.apache.commons.lang3.StringUtils.isNotBlank(str) && org.apache.commons.lang3.StringUtils.isNotEmpty(str)) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isNotNullOrBlank(String str) {
        return !isNullOrBlank(str);
    }


    /**
     * 判断是否为数字
     *
     * @param attr
     * @return
     */
    public static boolean isNumeric(String attr) {
        if (!isNullOrBlank(attr) && org.apache.commons.lang3.StringUtils.isNumeric(attr)) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否为数字
     *
     * @param attr
     * @param flag true 全部数字，false 只验证是否为正整数
     * @return
     */
    public static boolean isNumeric(String attr,Boolean flag) {
        if(flag){
            if (!isNullOrBlank(attr) && isNumericForAll(attr)) {
                return true;
            }
        }else{
            if (!isNullOrBlank(attr) && org.apache.commons.lang3.StringUtils.isNumeric(attr)) {
                return true;
            }
        }

        return false;
    }

    /**
     *
     * @param str
     * @return
     */
    public static boolean isNumericForAll(String str){
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }

    public static List<Long> transToList(String params) {
        if (JcStringUtils.isNullOrBlank(params)) {
            return null;
        }

        String[] params_array = params.split(",");
        List<Long> params_list = new ArrayList<Long>();
        for (String k : params_array) {
            if (!JcStringUtils.isNullOrBlank(k) && JcStringUtils.isNumeric(k)) {
                params_list.add(Long.valueOf(k));
            }
        }

        return params_list;
    }

    public static List<Integer> transToListInteger(String params) {
        if (JcStringUtils.isNullOrBlank(params)) {
            return null;
        }

        String[] params_array = params.split(",");
        List<Integer> params_list = new ArrayList<Integer>();
        for (String k : params_array) {
            if (!JcStringUtils.isNullOrBlank(k) && JcStringUtils.isNumeric(k)) {
                params_list.add(Integer.valueOf(k));
            }
        }

        return params_list;
    }

    public static List<Long> transToList(Long[] args) {
        if (args == null) {
            return null;
        }
        List<Long> params_list = new ArrayList<Long>();
        for (Long k : args) {
            if (k != null) {
                params_list.add(k);
            }
        }
        return params_list;
    }

    public static Long[] transToArray(List<Long> params) {
        if (params == null) {
            return null;
        }

        Long[] result = new Long[params.size()];
        for (int i = 0; i < params.size(); i++) {
            result[i] = params.get(i);
        }
        return result;
    }

    /**
     * 将 234234,234,234,234 型的字符串转成 Long []
     *
     * @param params
     * @return
     */
    public static Long[] trans(String params) {
        if (JcStringUtils.isNullOrBlank(params)) {
            return null;
        }

        String[] params_array = params.split(",");
        List<Long> params_list = new ArrayList<Long>();
        for (String k : params_array) {
            if (!JcStringUtils.isNullOrBlank(k) && JcStringUtils.isNumeric(k)) {
                params_list.add(Long.valueOf(k));
            }
        }

        return (Long[]) params_list.toArray(new Long[params_list.size()]);
    }

    /**
     * @param ids
     * @return
     */
    public static List<Long> trans(Long[] ids) {
        if (ids == null) {
            return null;
        }
        List<Long> result = new ArrayList<Long>();
        for (int i = 0; i < ids.length; i++) {
            result.add(ids[i]);
        }
        return result;
    }


    /**
     * 将 List<> 转成 Long []
     *
     * @param list
     * @return
     */
    public static Long[] tarns(List<Long> list) {
        if (list == null) {
            return null;
        }

        Long[] result = new Long[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    /**
     * 将 Long [] 转换成 123,455,7878,999 形式的数列字符串
     *
     * @param ids
     * @return
     */
    public static String progression(Long[] ids) {
        StringBuffer sb = new StringBuffer();
        for (Long i : ids) {
            sb.append(i).append(",");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    public static String progression(List<Long> ids) {
        StringBuffer sb = new StringBuffer();
        for (Long i : ids) {
            sb.append(i).append(",");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    public static String progressionListStr2Str(List<String> params) {
        StringBuffer sb = new StringBuffer();
        for (String i : params) {
            sb.append(i).append(",");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
    /**
     * 数列式字符串，追加目标
     *
     * @param source
     * @param target
     * @return
     */
    public static String progressionAppendTarget(String source, String target) {
        if (source == null) {
            source = "";
        }

        if ("".equals(source)) {
            return target;
        }

        String[] _source = source.split(",");
        for (String temp : _source) {
            if (temp.equals(target)) {
                return source;
            }
        }

        if (source.endsWith(",")) {
            return source + target;
        }
        return source + "," + target;
    }

    /**
     * 数列式字符串，从中间去掉某些字符串
     *
     * @param source
     * @param target
     * @return
     */
    public static String progressionRemoveTarget(String source, String target) {
        if (source == null) {
            source = "";
        }

        String[] _source = source.split(",");
        List<String> result = new ArrayList<String>();
        for (String temp : _source) {
            if (target.equals(temp)) {
                continue;
            }
            result.add(temp);
        }

        StringBuffer sb = new StringBuffer();
        for (String t : result) {
            sb.append(t).append(",");
        }
        return sb.toString();
    }

    /**
     * 数列式字符串，检查是否包括该字符串
     *
     * @param source
     * @param target
     * @return
     */
    public static boolean progressionIsContain(String source, String target) {
        if (source == null) {
            source = "";
        }

        String[] _source = source.split(",");
        for (String temp : _source) {
            if (target.equals(temp)) {
                return true;
            }
        }
        return false;
    }

    public static String fetchAttributeFromHTML(String source, String tag, String attribute) {
        Document doc = Jsoup.parse(source, "UTF-8");
        Elements tags = doc.getElementsByTag(tag);
        for (Element ele : tags) {
            if (ele.hasAttr(attribute)) {
                return ele.attr(attribute);
            }
        }
        return null;
    }

    public static String fetchAttributeFromJSON(String source, String attribute) {
        source = source.replaceAll("\"", "").replace("}", "").replace("{", "");
        String[] sets = source.split(",");
        for (String temp : sets) {
            String[] pairs = temp.split(":");
            if (pairs.length <= 1) {
                continue;
            }
            if (attribute.equals(pairs[pairs.length - 2])) {
                return pairs[pairs.length - 1];
            }
        }
        return null;
    }

    public static int fetchPasswordComplexity(String source) {
        String pattern_1 = "^[0-9]+$";
        String pattern_2 = "^[a-z]+$";
        String pattern_3 = "^[A-Z]+$";
        String pattern_4 = "^[\\~\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)\\_\\+\\`\\-\\=\\{\\}\\[\\]\\:\\|\\;\\'\\<\\>\\?\\,\\.\\/\\']+$";

        String pattern_12 = "^[0-9a-z]+$";
        String pattern_13 = "^[0-9A-Z]+$";
        String pattern_14 = "^[0-9\\~\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)\\_\\+\\`\\-\\=\\{\\}\\[\\]\\:\\|\\;\\'\\<\\>\\?\\,\\.\\/\\']+$";
        String pattern_23 = "^[a-zA-Z]+$";
        String pattern_24 = "^[a-z\\~\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)\\_\\+\\`\\-\\=\\{\\}\\[\\]\\:\\|\\;\\'\\<\\>\\?\\,\\.\\/\\']+$";
        String pattern_34 = "^[A-Z\\~\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)\\_\\+\\`\\-\\=\\{\\}\\[\\]\\:\\|\\;\\'\\<\\>\\?\\,\\.\\/\\']+$";

        String pattern_123 = "^[0-9a-zA-Z]+$";
        String pattern_234 = "^[a-zA-Z\\~\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)\\_\\+\\`\\-\\=\\{\\}\\[\\]\\:\\|\\;\\'\\<\\>\\?\\,\\.\\/\\']+$";
        String pattern_124 = "^[0-9a-z\\~\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)\\_\\+\\`\\-\\=\\{\\}\\[\\]\\:\\|\\;\\'\\<\\>\\?\\,\\.\\/\\']+$";
        String pattern_134 = "^[0-9A-Z\\~\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)\\_\\+\\`\\-\\=\\{\\}\\[\\]\\:\\|\\;\\'\\<\\>\\?\\,\\.\\/\\']+$";

        String pattern_1234 = "^[0-9a-zA-Z\\~\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)\\_\\+\\`\\-\\=\\{\\}\\[\\]\\:\\|\\;\\'\\<\\>\\?\\,\\.\\/\\']+$";

        if (source.matches(pattern_1)
                || source.matches(pattern_2)
                || source.matches(pattern_3)
                || source.matches(pattern_4)) {
            return 0;
        }

        if (source.matches(pattern_12)
                || source.matches(pattern_13)
                || source.matches(pattern_14)
                || source.matches(pattern_23)
                || source.matches(pattern_24)
                || source.matches(pattern_34)) {
            return 1;
        }

        if (source.matches(pattern_123)
                || source.matches(pattern_124)
                || source.matches(pattern_234)
                || source.matches(pattern_134)
                || source.matches(pattern_1234)) {
            return 2;
        }

        return 99;
    }

    public static void main(String[] args) {
        //String sour = "{\"employee\":{\"cid\":1,\"dids\":\"2\",\"wname\":\"王一,3,4\",\"uid\":14,\"wid\":3,\"isleaders\":\"1\",\"updatetime\":1385107783,\"createtime\":1385107783,\"status\":1},\"result\":\"1\",\"message\":null,\"code\":null}";
        //System.out.println (JcStringUtils.fetchAttributeFromJSON (sour, "name"));

        /**
         String password = "@#$@#BBBCCCccc98789";
         System.out.println ("密码复杂度：" + fetchPasswordComplexity (password));

         SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         System.out.println (sf.format (new Date(1389253300000L)));
         */
        String k = ",234,2,5,76,34,asdfasdf,,,,";
        Long[] result = JcStringUtils.trans(k);
        for (Long i : result) {
            System.out.println("- " + i);
        }
    }
}
