package br.com.rescue_bots_android.controller;

import android.app.Activity;

public abstract class Controller {
	Activity activity = null;
	public Controller(Activity activity){
		this.activity = activity;
	}
}
