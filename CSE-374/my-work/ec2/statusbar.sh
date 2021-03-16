#!/usr/bin/sh

touch __SENSORS_BATT
touch __SENSORS_SYS

BATT() {
  CHARGE=$(cat /sys/class/power_supply/BAT0/capacity)
  STATUS=$(cat /sys/class/power_supply/BAT0/status)
  if [ "$STATUS" = "Charging" ]; then
    FMTD=$(printf "BAT: %d%% [%s]" "$CHARGE" "$STATUS")
  else
    FMTD=$(printf "BAT: %d%%" "$CHARGE")
  fi
  echo "$FMTD" > __SENSORS_BATT
}

VOLU() {
  printf "VOL: %d%%" "$(amixer sget Master | tail -n1 | sed -r "s/.*\[(.*)%\].*/\1/")"
}

TIME() {
  printf "%s" "$(date)"
}

SENSORS() {
  MEM=$(free -h | grep Mem | awk '{print $3,"/", $2}')
  CPU_TEMP=$(sensors | grep Package | awk '{print $4}')
  CPU_USAG=$(top -bn1 | grep Cpu | awk '{print $2}')
  FMTD=$(printf "MEM: %s CPU: %s%% (%s)" "$MEM" "$CPU_USAG" "$CPU_TEMP")
  echo "$FMTD" > __SENSORS_SYS
}

# Not used for performance and clutter reasons, but nice to have.
# I will spin this off into its own little script
NET() {
  PRIVATE=$(nmcli -a | grep 'inet4 192' | awk '{print $2}')
  PUBLIC=$(curl -s https://ipinfo.io/ip)
  printf "INT: %s PUB: %s" "$PRIVATE" "$PUBLIC"
}

parallel_10s() {
  while true
  do
    SENSORS &
    BATT &
    sleep 5
  done
}

parallel_10s &

while true
do
  OUT=$(printf " %s | %s | %s | %s " "$(cat __SENSORS_SYS)" "$(cat __SENSORS_BATT)" "$(VOLU)" "$(TIME)")
  echo "Setting: $OUT"
  xsetroot -name "$OUT"
  sleep 0.2
done

