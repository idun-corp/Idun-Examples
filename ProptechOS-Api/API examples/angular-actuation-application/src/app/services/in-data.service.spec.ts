import { TestBed } from '@angular/core/testing';

import { InDataService } from './in-data.service';

describe('InDataService', () => {
  let service: InDataService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(InDataService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
