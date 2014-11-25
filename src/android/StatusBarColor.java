package net.ekuwang.cordova.plugin.statusbar;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;

import android.graphics.Color;
import android.view.WindowManager;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.view.WindowManager.LayoutParams;
import android.util.Log;

public class StatusBarColor extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callback) throws JSONException {
	// grab the correct methods

	if (action.equalsIgnoreCase("setColor")) {
	    if (VERSION.SDK_INT > VERSION_CODES.KITKAT) {
		cordova.getActivity().runOnUiThread(new Runnable() {
		    public void run() {
			cordova.getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			cordova.getActivity().getWindow().setStatusBarColor(Color.rgb(args.getInt(0), args.getInt(1), args.getInt(2)));
		    }
		});
		callback.success();
	    } else {
		callback.error("not supported");
	    }
	    return true;
	} else {
	    callback.error("Unknown Action: " + action);
	    return false;
	}
    }
}
