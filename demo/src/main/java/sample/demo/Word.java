package sample.demo;

public class Word {
    private String word_targe;
    private String word_explain;

    public Word(String word_targe, String word_explain) {
        this.word_targe = word_targe;
        this.word_explain = word_explain;
    }

    public void setWord_targe(String word_targe) {
        this.word_targe = word_targe;
    }

    public String getWord_targe() {
        return word_targe;
    }

    public void setWord_explain(String word_explain) {
        this.word_explain = word_explain;
    }

    public String getWord_explain() {
        return word_explain;
    }

}
