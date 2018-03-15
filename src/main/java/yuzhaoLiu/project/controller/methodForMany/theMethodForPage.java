package yuzhaoLiu.project.controller.methodForMany;

public class theMethodForPage {

    public static int methodForPage(int allRecords , int currentoffset , int length){
        int toIndex = 0 ;
        if (allRecords >= length + currentoffset) {
            toIndex = currentoffset + length;
        } else {
            toIndex = allRecords;
        }
        return toIndex ;
    }

}
