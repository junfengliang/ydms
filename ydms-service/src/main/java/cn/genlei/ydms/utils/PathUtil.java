package cn.genlei.ydms.utils;

/**
 * @author nid
 */
public class PathUtil {
    final static String flag = "/";

    public static String join(String parent,String child){

        if(parent==null){
            return child;
        }
        if(child==null){
            return parent;
        }
        StringBuffer sb = new StringBuffer();
        sb.append(parent);
        if(child.startsWith(flag)){
            if(parent.endsWith(flag)){
                sb.append(child.substring(1));
            }else{
                sb.append(child);
            }
        }else{
            if(!parent.endsWith(flag)){
                sb.append(flag);
            }
            sb.append(child);
        }
        return sb.toString();
    }
}
