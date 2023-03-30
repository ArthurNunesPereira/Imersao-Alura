package br.com.alura.linguagens.api;

public class Linguagem {
    private String tittle;
    private String image;
    private int ranking;

    public Linguagem(String tittle, String image, int ranking) {
        this.tittle = tittle;
        this.image = image;
        this.ranking = ranking;
    }

    public String getTittle() {
        return tittle;
    }
    public String getImage() {
        return image;
    }
    public int getRanking() {
        return ranking;
    }

    
}
