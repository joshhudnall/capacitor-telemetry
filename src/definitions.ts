export interface TelemetryPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
