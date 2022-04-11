import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UtilsService {

  constructor() { }

  static nonNull(object: object): boolean {
    return object !== null && object !== undefined;
  }
}
