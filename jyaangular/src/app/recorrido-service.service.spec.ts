import { TestBed } from '@angular/core/testing';

import { RecorridoServiceService } from './recorrido-service.service';

describe('RecorridoServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: RecorridoServiceService = TestBed.get(RecorridoServiceService);
    expect(service).toBeTruthy();
  });
});
