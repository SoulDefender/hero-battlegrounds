import {async, ComponentFixture, fakeAsync, TestBed, tick} from '@angular/core/testing';
import {CommonModule} from '@angular/common';
import {AppComponent} from './app.component';
import {RouterTestingModule} from '@angular/router/testing';
import {MessagesComponent} from './messages/messages.component';
import {MessageService} from './services/message.service';
import {SuiModule} from "ng2-semantic-ui";
import {Store, StoreModule} from "@ngrx/store";
import {authReducer} from "./reducers/auth";
import {LoginComponent} from "./login/login.component";
import {HeroActions} from "./actions/actions";
import {heroes} from "./in-memory-data.service";
import {AppStore} from "./reducers/app-store";
import {FightCapComponent} from "./fight-cap/fight-cap.component";
import {Subject} from "rxjs/Subject";
import {BehaviorSubject} from "rxjs/BehaviorSubject";
import {of} from "rxjs/observable/of";
import Spy = jasmine.Spy;

describe('AppComponent', () => {

  let store: Store<AppStore>;
  let app: AppComponent;
  let storeSpy: Spy;
  let fixture: ComponentFixture<AppComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        CommonModule,
        RouterTestingModule.withRoutes([
          {path: '', component: AppComponent}
        ]),
        SuiModule,
        StoreModule.forRoot(
          {
            "auth": authReducer
          }
        )
      ],
      providers: [MessageService],
      declarations: [
        AppComponent, MessagesComponent
      ],
    }).compileComponents();
  }));

  beforeEach(() => {
    store = TestBed.get(Store);
    store.dispatch(HeroActions.loadHeroesSuccess(heroes));
    storeSpy = spyOn(store, 'select');
    spyOn(store, 'dispatch').and.callThrough();
    storeSpy.and.returnValue(of({authenticated: false}));
    fixture = TestBed.createComponent(AppComponent);
    app = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create the app', async(() => {
     expect(app).toBeTruthy();
  }));


  it(`should have as title 'Heroes Battleground'`, async(() => {
       expect(app.title).toEqual('Heroes Battleground');
  }));

  it('should render partial navigation menu when unauthorized', async(() => {
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelector('div.ui').querySelector('a').textContent).toContain('Battleground');
    expect(compiled.querySelector('div.ui').querySelectorAll('a')[1].textContent).toContain('Log In');
  }));

  it('should render full navigation menu when authorized', fakeAsync(() => {
    storeSpy.and.returnValue(of({authenticated: true}));
    app.ngOnInit();
    fixture.detectChanges();
    tick();
    fixture.detectChanges();
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelector('div.ui')
      .querySelectorAll('a')[1].textContent).toContain('Heroes');
    expect(compiled.querySelector('div.ui')
      .querySelectorAll('a')[2].textContent).toContain('Dashboard');
  }));

});
