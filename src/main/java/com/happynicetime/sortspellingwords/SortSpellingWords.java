package com.happynicetime.sortspellingwords;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author evane
 */
public class SortSpellingWords {

    public static void main(String[] args) {
        if(args.length == 0){
            System.out.printf("SortSpellingWords [file]");
            return;
        }
        try{
            FileReader inputStream = new FileReader(args[0]);
            StringBuilder stringBuilder = new StringBuilder();
            int readByte;
            while((readByte = inputStream.read()) != -1){
                stringBuilder.append((char)readByte);
            }
            String text = stringBuilder.toString();
            String[] wordsArray = text.split(System.lineSeparator());
            String[] wordsArray2 = text.split("\n");
            if(wordsArray2.length > wordsArray.length){
                wordsArray = wordsArray2;
            }
            System.out.printf("Number of words: %d%n",wordsArray.length);
            List<String> wordsList2 = Arrays.asList(wordsArray);
            LinkedList<String> wordsList = new LinkedList<>(wordsList2);
            final String ABC = "abcdefghijklmnopqrstuvwxyz";
            for(int letter = 1;letter <= ABC.length();letter++){
                String spellingLetters = ABC.substring(0,letter);
                //System.out.println(spellingLetters);
                Iterator<String> iterator = wordsList.iterator();
                while(iterator.hasNext()){
                    String word = iterator.next();
                    boolean wordUsedOnlySpellingLetters = true;
                    for(int wordLetter = 0;wordLetter < word.length();wordLetter++){
                        boolean usedSpellingLetter = false;
                        for(int spellingLetter = 0;spellingLetter < spellingLetters.length();spellingLetter++){
                            if(word.charAt(wordLetter) == spellingLetters.charAt(spellingLetter)){
                                usedSpellingLetter = true;
                                break;
                            }
                        }
                        if(usedSpellingLetter == false){
                            wordUsedOnlySpellingLetters = false;
                            break;
                        }
                    }
                    if(wordUsedOnlySpellingLetters){
                        System.out.printf("%s%n",word);
                        iterator.remove();
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            Logger.getLogger(SortSpellingWords.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(SortSpellingWords.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}