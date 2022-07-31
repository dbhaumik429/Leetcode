public class MinWindowSubSequenceWithOrder {

    public String minWindow(String s1, String s2) {

        int start =-1;

        int sIndex=0;
        int tIndex=0;
        int sLen = s1.length();
        int len = sLen+1;


        while(sIndex < sLen){

            //System.out.println("Analyzing s1 " + s1.charAt(i) + ", and s2 " + s2.charAt(j) );

            if(s1.charAt(sIndex) == s2.charAt(tIndex)){

                tIndex++;

                if(tIndex == s2.length()){//contract the window from right to left

                    int end = sIndex + 1;
                    tIndex--;

                    while(tIndex >=0 ){

                        if(s1.charAt(sIndex) == s2.charAt(tIndex)){
                            tIndex--;
                        }

                        sIndex--;
                    }

                    //since j becomes negative
                    tIndex++;
                    sIndex++;

                    if( end - sIndex < len){

                        // System.out.println("i " + i + ", and st " + st );
                        len = end - sIndex ;
                        start = sIndex;
                    }
                }
            }

            sIndex++;
        }

        return start == -1 ? "" : s1.substring(start, start + len);

    }

    public static void main(String[] args) {

        String paragraph= "Paragraph: I want to work for Airbnb because they provide amazing benefits and " +
                "career potential to their employees, and their vision and message are stellar examples for other modern companies" +
                " to follow. The idea of belonging anywhere and championing the mission? That's a great goal.";

        String keywords = "and are for";

        MinWindowSubSequenceWithOrder minWindowSubSequenceWithOrder = new MinWindowSubSequenceWithOrder();

        System.out.println(minWindowSubSequenceWithOrder.minWindow(paragraph,keywords));

    }

}
