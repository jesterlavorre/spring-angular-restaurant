import { DiagramBars } from './DiagramBars';
import { DiagramHistogram } from './DiagramHistogram';
import { DiagramPie } from './DiagramPie';

export class Diagrams {
  diagramBars: DiagramBars[];
  diagramPie: DiagramPie[];
  diagramHistogram: DiagramHistogram[];

  constructor(
    diagramBars: DiagramBars[],
    diagramPie: DiagramPie[],
    diagramHistogram: DiagramHistogram[]
  ) {
    this.diagramBars = diagramBars;
    this.diagramPie = diagramPie;
    this.diagramHistogram = diagramHistogram;
  }
}
