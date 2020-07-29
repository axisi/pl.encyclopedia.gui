package gui_swing.ui.model;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.text.diff.CommandVisitor;
import org.apache.commons.text.diff.StringsComparator;

import java.io.File;
import java.text.BreakIterator;
import java.util.*;

public class FileDiff {
    private String oldContentNoHTLM1;
    private String newContentNoHTLM1;
    private List<String> text1 =new ArrayList<>();
    private List<String> text2 = new ArrayList<>();
    private  String newText1;
    private  String newText2;

    public String getNewText1() {
        return newText1;
    }

    public String getNewText2() {
        return newText2;
    }

    public FileDiff(String oldContentNoHTLM, String newContentNoHTLM) {



        this.oldContentNoHTLM1 = oldContentNoHTLM;
        this.newContentNoHTLM1 = newContentNoHTLM;
        BreakIterator iterator = BreakIterator.getSentenceInstance(Locale.UK);
        iterator.setText(oldContentNoHTLM1);
        int start = iterator.first();
        for (int end = iterator.next();end != BreakIterator.DONE; start = end, end = iterator.next()) {
            text1.add(oldContentNoHTLM1.substring(start,end));
        }
        BreakIterator iterator2 = BreakIterator.getSentenceInstance(Locale.UK);
        iterator2.setText(newContentNoHTLM1);
        int start2 = iterator2.first();
        for (int end = iterator2.next();end != BreakIterator.DONE; start2 = end, end = iterator2.next()) {
            text2.add(newContentNoHTLM1.substring(start2,end));
        }

        // = oldContentNoHTLM1.split("\\.");

       // text1 =  Arrays.asList(array1);
        /*if(text1.size()>1){

            int indexOfLastElement = text2.size() - 1;
            text1.remove(indexOfLastElement);
        }*/
        //array1 = newContentNoHTLM1.split("\\.");

        //text2 =  Arrays.asList(array1);
        /*if(text2.size()>1){
            int indexOfLastElement = text2.size() - 1;
            text2.remove(indexOfLastElement);
        }*/

        FileCommandsVisitor fileCommandsVisitor = new FileCommandsVisitor();
        Iterator it1 = text1.iterator();
        Iterator it2 = text2.iterator();
        while(it1.hasNext()||it2.hasNext()){
            String left = (it1.hasNext() ? it1.next().toString() : "") ;
            String right = (it2.hasNext() ? it2.next().toString() : "");
            left =(it1.hasNext()?left + ".":left);
            right =(it2.hasNext()?right+ ".":right);

            StringsComparator comparator = new StringsComparator(left, right);

            if (comparator.getScript().getLCSLength() > (Integer.max(left.length(), right.length()) * 0.4)) {

                comparator.getScript().visit(fileCommandsVisitor);
            }else{
                StringsComparator leftComparator = new StringsComparator(left, "");
                leftComparator.getScript().visit(fileCommandsVisitor);
                StringsComparator rightComparator = new StringsComparator("", right);
                rightComparator.getScript().visit(fileCommandsVisitor);
            }
        }
        newText1 = fileCommandsVisitor.getLeft();
        newText2 = fileCommandsVisitor.getRight();



    }
    class FileCommandsVisitor implements CommandVisitor<Character> {
        private static final String DELETION = "<span style=\"background-color: #FB504B\">${text}</span>";
        private static final String INSERTION = "<span style=\"background-color: #45EA85\">${text}</span>";

        private String left = "";
        private String right = "";


        @Override
        public void visitKeepCommand(Character character) {
            String toAppend =""+ character;
            left = left + toAppend;
            right = right + toAppend;
        }
        @Override
        public void visitInsertCommand(Character character) {


            String toAppend =""+ character;
            right = right + INSERTION.replace("${text}", "" + toAppend);
        }



        @Override
        public void visitDeleteCommand(Character character) {
            String toAppend =""+ character;
            left = left + DELETION.replace("${text}", "" + toAppend);
        }


        public String getLeft() {
            return left;
        }

        public String getRight() {
            return right;
        }
    }

}
