/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package list.sorter;

import java.awt.List;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;


/**
 *
 * @author Rustem Azimov
 */
public class WordListManupulator {
    private List resultListView;
    private java.util.List<String> unsortedArray;
    private MyFileReadWriter readWriter;

    public WordListManupulator(List resultListView, MyFileReadWriter readWriter) throws IOException {
        this.resultListView = resultListView;
        this.unsortedArray = readWriter.readListOfWords();
        this.readWriter = readWriter;
    }
    public void sortWriteAndShowResult(boolean isDuplicates) throws IOException{
        Object[] readedData = this.unsortedArray.toArray();
        Arrays.sort(readedData, 0, readedData.length);
        print(readedData);
        HashSet<String> uniqueItems = new HashSet<>();
        int seriesOfWord = 0;
        for(Object item : readedData){
            String element = ((String) item).toLowerCase();
            if(isDuplicates)
            {
                if(uniqueItems.add(element))
                {
                    this.readWriter.writeWordToFile((++seriesOfWord) + ") " + element);
                    this.resultListView.add((++seriesOfWord) + ") " + element);
                }
            }
            else
            {
                this.readWriter.writeWordToFile((++seriesOfWord) + ") " + element);
                this.resultListView.add((++seriesOfWord) + ") " + element);
            }
            
            
        }
        readWriter.getWriter().close();
        this.readWriter.finishWriting();
    }
    private static void print(Object[] array){
        for(Object item: array){
        }
    }
}
