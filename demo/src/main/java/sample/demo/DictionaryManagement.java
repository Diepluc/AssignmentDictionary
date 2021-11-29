package sample.demo;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DictionaryManagement {
    Dictionary dictionary;

    public DictionaryManagement(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    /** nhap du lieu tu file. */
    public void insertFromFile() {
        String fileName = "src/dictionaries.txt";
        BufferedReader reader = null;
        try {
            String line = "";
            reader = new BufferedReader(new FileReader(fileName));
            if (reader.readLine() == null) {
                System.out.println("File dang trong, moi ban them tu vao file");
            }
            while ((line = reader.readLine()) != null) {
                String[] wordOfLine = line.split("\t");
                String word_tagre = wordOfLine[0];
                String word_explain = wordOfLine[1];
                Word word = new Word(word_tagre, word_explain);
                dictionary.addWord(word);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                System.out.println("Nhap file thanh cong");
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * tim nghia cua tu.
     */
    public String dictionaryLookup(String wordTarge) {
        int pos = dictionary.searchPosWord(wordTarge);
        if (pos == -1) {
            System.out.println("Khong co tu nay trong tu dien");
        } else {
            List<Word> words = dictionary.getWords();
            Word word = words.get(pos);
            return word.getWord_explain();
        }
        return "";
    }

    /**
     * tim vi tri tra, sau do tim danh sach.
     * @param subWord tu de tra
     * @return danh sach tra tu
     */
    public List<String> dictionarySearcher(String subWord) {
        List<Word> words = dictionary.getWords();
        int posStart = dictionary.searchPosSubWord(subWord);
        List<String> arrSub = new ArrayList<>();

        int left = posStart;
        int right = posStart;
        while (left >= 0) {
            Word word = words.get(left);
            String strTest1 = word.getWord_targe();
            if (strTest1.startsWith(subWord)) left--;
            else break;
        }

        while (right < words.size()) {
            Word word = words.get(right);
            String strTest2 = word.getWord_targe();
            if (strTest2.startsWith(subWord)) right++;
            else break;
        }

        for (int i = left + 1; i < right; i++) {
            Word word = words.get(i);
            arrSub.add(word.getWord_targe());
        }
        return arrSub;
    }
}
