import {Component, Input, OnInit} from '@angular/core';
import {Observation} from '../../common/rectypes';
import {ProptechosService} from '../../services/proptechos.service';
import * as Highcharts from 'highcharts';
import {Options} from 'highcharts';
import * as _ from 'lodash';

@Component({
  selector: 'app-latest-observation',
  templateUrl: './latest-observation.component.html',
  styleUrls: ['./latest-observation.component.scss']
})
export class LatestObservationComponent implements OnInit {

  @Input() sensorId: string;
  observation: Observation;

  highcharts = Highcharts;
  updateFlag = false;
  chartOptions: Options = this.buildChartOptions();

  private temporaryList: Array<IObservation> = new Array<IObservation>();

  constructor(private apiService: ProptechosService) { }

  ngOnInit(): void {
  }

  getObservation(): void {
    const self = this;
    this.apiService.getLatestObservation(this.sensorId).subscribe((data) => {
      this.observation = data;
      const obs = {
        time: new Date(data.observationTime).getTime(),
        value: data.value
      };
      if (!_.find(this.temporaryList, obs)) {
        this.temporaryList.push(obs);
      }
      const result = this.temporaryList
        .sort(self.sortObservation)
        .map((o) => {
        return [o.time, o.value];
      });
      self.chartOptions.series = [{
        name: 'Sensor: ' + self.sensorId,
        type: undefined,
        data: result
      }];
      self.updateFlag = true;
    });
  }

  private buildChartOptions(): Options {
    return {
      chart: {
        type: 'spline'
      },
      title: {
        text: 'Latest observation values'
      },
      xAxis: {
        type: 'datetime',
        dateTimeLabelFormats: {
          month: '%e. %b',
          year: '%b'
        },
        title: {
          text: 'Date'
        }
      },
      yAxis: {
        title: {
          text: 'Observation value'
        }
      },
      tooltip: {
        headerFormat: '<b>{series.name}</b><br>',
        pointFormat: '{point.x:%e. %b}: {point.y:.2f}'
      },
      series: [{
        color: '#3f51b5',
        name: 'Observation values',
        type: undefined,
        data: []
      }]
    };
  }

  private sortObservation = (a: IObservation, b: IObservation) => {
    if (a.time < b.time) {
      return -1;
    }
    if (a.time > b.time) {
      return 1;
    }
    return 0;
  }
}

interface IObservation {
  time: number;
  value: any;
}
