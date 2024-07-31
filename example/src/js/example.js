import { Telemetry } from 'capacitor-telemetry';

window.getTelemetry = async () => {
    document.getElementById('output').innerHTML = JSON.stringify(await Telemetry.getData());
}
