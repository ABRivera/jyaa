import { TestBed } from '@angular/core/testing';

import { DonacionServiceService } from './donacion-service.service';

describe('DonacionServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DonacionServiceService = TestBed.get(DonacionServiceService);
    expect(service).toBeTruthy();
  });
});
