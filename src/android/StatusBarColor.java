package ch.theelysianfields.cordova.plugin.statusbar;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;

import android.graphics.Color;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.view.WindowManager;

public class StatusBarColor extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callback) throws JSONException {

	if (action.equalsIgnoreCase("setColor")) {
	    if (VERSION.SDK_INT > VERSION_CODES.KITKAT) {
		final JSONArray finalargs = args;
		cordova.getActivity().runOnUiThread(new Runnable() {
		    @Override
		    public void run() {
			try {
			    cordova.getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			    cordova.getActivity().getWindow().setStatusBarColor(Color.rgb(finalargs.getInt(0), finalargs.getInt(1), finalargs.getInt(2)));
			} catch (JSONException e) {
			    // TODO: handle exception
			}
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
