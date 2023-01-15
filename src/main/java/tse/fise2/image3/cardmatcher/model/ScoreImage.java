package tse.fise2.image3.cardmatcher.model;

public class ScoreImage {
    String imageName;
    Double score;

    public ScoreImage(String imageName, Double score) {
        this.imageName = imageName;
        this.score = score;
    }

    public String getImageName() {

        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
