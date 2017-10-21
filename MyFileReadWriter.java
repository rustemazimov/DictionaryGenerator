/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package list.sorter;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;


/**
 *
 * @author Rustem
 */
public class MyFileReadWriter {
    private File readFile;
    private File writeFile;
    private PrintWriter writer;
    private FileOutputStream out;
    private XWPFDocument document;
    private XWPFRun run;

    public MyFileReadWriter(File readFile, File writeFile) throws FileNotFoundException {
        this.readFile = readFile;
        this.writeFile = writeFile;
        writer = new PrintWriter(this.writeFile);
        this.out = new FileOutputStream(this.writeFile);
        this.document = new XWPFDocument(); 
        XWPFParagraph n = document.createParagraph();
        run=n.createRun();
    }
    public PrintWriter getWriter(){
        return this.writer;
    }
    public List<String> readListOfWords() throws IOException{
        List<String> listOfWords = new ArrayList<>();
                FileInputStream fis = new FileInputStream(this.readFile.getAbsolutePath());

                XWPFDocument document = new XWPFDocument(fis);

                List<XWPFParagraph> paragraphs = document.getParagraphs();


                for (XWPFParagraph para : paragraphs) {
                    //System.out.println(para.getText());
                    String[] wordsInALine = para.getText().split(" ");
                    for(String aWordInALine: wordsInALine){
                        listOfWords.add(aWordInALine);
                    }
                }
                fis.close();
        return listOfWords;
          
    }
    public void writeWordToFile(String word) throws IOException{
        run.setText(word);
        run.addCarriageReturn();
    }
    public void finishWriting() throws IOException{
        document.write(out);    
        out.close();
    }
}
