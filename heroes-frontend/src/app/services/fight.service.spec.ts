import { TestBed, inject } from '@angular/core/testing';

import { FightService } from './fight.service';
import {HttpClientModule} from "@angular/common/http";
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {MessageService} from "./message.service";

describe('FightService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientModule,
        HttpClientTestingModule
      ],
      providers: [FightService, MessageService]
    });
  });

  it('should be created', inject([FightService], (service: FightService) => {
    expect(service).toBeTruthy();
  }));
});
