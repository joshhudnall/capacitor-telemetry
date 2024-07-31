package com.wearesupermagical.capacitortelemetry;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

import org.json.JSONObject;

@CapacitorPlugin(name = "Telemetry")
public class TelemetryPlugin extends Plugin {

    private Telemetry implementation = new Telemetry();

    @PluginMethod
    public void getData(PluginCall call) {
        JSObject ret = new JSObject();
        ret.put(
            "value",
            new JSONObject(
                implementation.getData(this.bridge.getContext())
            )
        );
        call.resolve(ret);
    }
}
