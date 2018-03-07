import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Store} from "@ngrx/store";
import {AppStore} from "../reducers/app-store";
import {AuthAction} from "../actions/actions";
import {AuthRequest} from "../model/auth-request";
import {Location} from "@angular/common";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;

  constructor(private fb: FormBuilder, private store: Store<AppStore>, private location: Location) {
  }

  ngOnInit() {
    this.loginForm = this.createLoginForm();
  }

  private createLoginForm() {
    return this.fb.group({
      email: ['', [Validators.required, Validators.minLength(2), Validators.email]],
      password: ['', [Validators.required, Validators.minLength(3)]]
    });
  }

  send(model: FormGroup): void {
    this.store.dispatch(AuthAction.authenticate(model.value as AuthRequest))
  }

  goBack() {
    this.location.back()
  }

}
