import { TestBed } from '@angular/core/testing';

import { FichapacienteService } from './fichapaciente.service';

describe('FichapacienteService', () => {
  let service: FichapacienteService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FichapacienteService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
