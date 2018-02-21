import {async, TestBed} from '@angular/core/testing';
import {CommonModule} from '@angular/common';
import {AppComponent} from './app.component';
import {RouterTestingModule} from '@angular/router/testing';
import {MessagesComponent} from './messages/messages.component';
import {MessageService} from './message.service';
import {SuiModule} from "ng2-semantic-ui";

describe('AppComponent', () => {
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        CommonModule,
        RouterTestingModule.withRoutes([
          {path: '', component: AppComponent}
        ]),
        SuiModule
      ],
      providers: [MessageService],
      declarations: [
        AppComponent, MessagesComponent
      ],
    }).compileComponents();
  }));
  it('should create the app', async(() => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  }));
  it(`should have as title 'Heroes Battleground'`, async(() => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app.title).toEqual('Heroes Battleground');
  }));
  it('should render navigation menu', async(() => {
    const fixture = TestBed.createComponent(AppComponent);
    fixture.detectChanges();
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelector('div.ui').querySelector('a').textContent).toContain('Dashboard');
  }));
});
