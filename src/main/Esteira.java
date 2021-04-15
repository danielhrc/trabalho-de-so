public class Esteira {

    private double tempoBraco=0.5;
    private double tempoPacote=5;
    private double tempoGeral;
    public Esteira(){

    }



    public double getTempoBraco() {
        return tempoBraco;
    }

    public void setTempoBraco(double tempoBraco) {
        this.tempoBraco = tempoBraco;
    }

    public double getTempoGeral() {
        return tempoGeral;
    }

    public void setTempoGeral(double tempoGeral) {
        this.tempoGeral = tempoGeral;
    }

    public double getTempoPacote() {
        return tempoPacote;
    }

    public void setTempoPacote(double tempoPacote) {
        this.tempoPacote = tempoPacote;
    }

    public void addTempoGeral(double tempo) {
        this.tempoGeral=this.tempoGeral+tempo;
    }
}
