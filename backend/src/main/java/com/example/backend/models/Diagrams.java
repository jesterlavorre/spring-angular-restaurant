package com.example.backend.models;

import java.util.List;

public class Diagrams {
    private List<DiagramBars> diagramBars;
    private List<DiagramPie> diagramPie;
    private List<DiagramHistogram> diagramHistogram;

    public Diagrams() {
    }

    public Diagrams(List<DiagramBars> diagramBars, List<DiagramPie> diagramPie,
            List<DiagramHistogram> diagramHistogram) {
        this.diagramBars = diagramBars;
        this.diagramPie = diagramPie;
        this.diagramHistogram = diagramHistogram;
    }

    public List<DiagramBars> getDiagramBars() {
        return diagramBars;
    }

    public void setDiagramBars(List<DiagramBars> diagramBars) {
        this.diagramBars = diagramBars;
    }

    public List<DiagramPie> getDiagramPie() {
        return diagramPie;
    }

    public void setDiagramPie(List<DiagramPie> diagramPie) {
        this.diagramPie = diagramPie;
    }

    public List<DiagramHistogram> getDiagramHistogram() {
        return diagramHistogram;
    }

    public void setDiagramHistogram(List<DiagramHistogram> diagramHistogram) {
        this.diagramHistogram = diagramHistogram;
    }

}
