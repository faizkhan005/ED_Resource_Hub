package com.example.edresourcehub;

public class putPDF {

    public String nameFile;
    public String url;

    public putPDF() {
    }

    public putPDF(String nameFile, String url) {
        this.nameFile = nameFile;
        this.url = url;
    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
