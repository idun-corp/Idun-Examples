import { TestBed } from '@angular/core/testing';

import { ProptechosService } from './proptechos.service';

describe('ProptechosService', () => {
  let service: ProptechosService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProptechosService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
