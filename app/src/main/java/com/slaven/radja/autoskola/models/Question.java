package com.slaven.radja.autoskola.models;

/**
 * Created by Computer on 06/08/2014.
 */
public class Question {

    private int ID;
    private String QUESTION;
    private String OPTA;
    private String OPTB;
    private String OPTC;
    private String ANSWER_ONE;
    private String ANSWER_TWO;
    private int img_ID;
    private boolean hasImage;
    private int correctAnswerOne;
    private int correctAnswerTwo;
    private int oneOrTwoAnswers;

    public Question() {
        ID = 0;
        QUESTION = "";
        OPTA = "";
        OPTB = "";
        OPTC = "";
        ANSWER_ONE = "";
        ANSWER_TWO = "";
        img_ID = 0;
        hasImage = false;
        correctAnswerOne = -1;
        correctAnswerTwo = -1;
        oneOrTwoAnswers = 0;


    }

    public Question(String QUESTION, String OPTA, String OPTB, String OPTC, String ANSWER_ONE, String ANSWER_TWO,
                    int img_ID, boolean hasImage, int correctAnswerOne, int correctAnswerTwo, int oneOrTwoAnswers) {
        this.ID = ID;
        this.QUESTION = QUESTION;
        this.OPTA = OPTA;
        this.OPTB = OPTB;
        this.OPTC = OPTC;
        this.ANSWER_ONE = ANSWER_ONE;
        this.ANSWER_TWO = ANSWER_TWO;
        this.img_ID = img_ID;
        this.hasImage = hasImage;
        this.correctAnswerOne = correctAnswerOne;
        this.correctAnswerTwo = correctAnswerTwo;
        this.oneOrTwoAnswers = oneOrTwoAnswers;
    }



    public int getID() {
        return ID;
    }

    public String getQUESTION() {
        return QUESTION;
    }

    public String getOPTA() {
        return OPTA;
    }

    public String getOPTB() {
        return OPTB;
    }

    public String getOPTC() {
        return OPTC;
    }

    public int getImg_ID() {return img_ID; }

    public String getANSWER_ONE() {return ANSWER_ONE;}

    public String getANSWER_TWO() {return ANSWER_TWO;}

    public int getCorrectAnswerTwo() {return correctAnswerTwo;}

    public int getCorrectAnswerOne() {return correctAnswerOne;}

    public int getOneOrTwoAnswers() {return oneOrTwoAnswers;}


    public void setID(int id) {
        ID = id;
    }

    public void setQUESTION(String qUESTION) {
        QUESTION = qUESTION;
    }

    public void setOPTA(String oPTA) {
        OPTA = oPTA;
    }

    public void setOPTB(String oPTB) {
        OPTB = oPTB;
    }

    public void setOPTC(String oPTC) {
        OPTC = oPTC;
    }

    public void setHasImage(boolean hasImage) {
        this.hasImage = hasImage;
    }

    public void setImg_ID(int img_ID) {this.img_ID = img_ID;}

    public void setANSWER_ONE(String ANSWER_ONE) { this.ANSWER_ONE = ANSWER_ONE;}

    public void setANSWER_TWO(String ANSWER_TWO) {this.ANSWER_TWO = ANSWER_TWO;}

    public void setCorrectAnswerOne(int correctAnswerOne) { this.correctAnswerOne = correctAnswerOne;}

    public void setCorrectAnswerTwo(int correctAnswerTwo) {this.correctAnswerTwo = correctAnswerTwo;}

    public void setOneOrTwoAnswers(int oneOrTwoAnswers) {this.oneOrTwoAnswers = oneOrTwoAnswers;}

    public boolean hasImage() {
        return hasImage;
    }

    public boolean isHasImage() {
        return hasImage;
    }





}
