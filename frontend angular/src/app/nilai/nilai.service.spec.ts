/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { NilaiService } from './nilai.service';

describe('NilaiService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [NilaiService]
    });
  });

  it('should ...', inject([NilaiService], (service: NilaiService) => {
    expect(service).toBeTruthy();
  }));
});
