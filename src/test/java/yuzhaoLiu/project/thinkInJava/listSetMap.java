package yuzhaoLiu.project.thinkInJava;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class listSetMap {

    @Test
    /**
     * @shortCut Ctrl+Alt+B IDEA查找接口实现类的快捷键
     * @features ArrayList实现了基于动态数组的数据结构,LinkedList基于链表的数据结构
     */
    public void testList(){
        ArrayList a = new ArrayList(50);
        System.out.println(getArrayListCapacity(a));
        List b = new LinkedList();
    }

    public static int getArrayListCapacity(ArrayList<?> arrayList) {
        Class<ArrayList> arrayListClass = ArrayList.class;
        try {
            Field field = arrayListClass.getDeclaredField("elementData");
            field.setAccessible(true);
            Object[] objects = (Object[])field.get(arrayList);
            return objects.length;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return -1;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return -1;
        }
    }

}
