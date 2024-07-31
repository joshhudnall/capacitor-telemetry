import { Telemetry } from 'capacitor-telemetry';

window.testEcho = () => {
    const inputValue = document.getElementById("echoInput").value;
    Telemetry.echo({ value: inputValue })
}
