# !/bin/sh

# I got the regexp for the sed on the next line from https://stackoverflow.com/a/49552792
PRIVATE=$(ip -o route get to 8.8.8.8 | sed -n 's/.*src \([0-9.]\+\).*/\1/p')
PUBLIC=$(curl -s https://ipinfo.io/ip)

printf "PRIVATE IP: %s\n" "$PRIVATE"
printf "PUBLIC IP:  %s\n" "$PUBLIC"

STRENGTH=$(iwconfig wlp2s0 | grep "Link Quality" | awk '{$1=$1;print}')
dBm=$(iwconfig wlp2s0 | tail -n4 | awk '{print $4}' | grep -o '[0-9]*')
SSID=$(iwconfig wlp2s0 | head -n1 | awk {'print $2 " " $3 " " $4 '} | awk '{$1=$1;print}')
APINFO=$(iwconfig wlp2s0 | sed '2!d' | awk '{$1=$1;print}')

if [ $dBm -ge -30 ] ; then
  ST="[||||| - Amazing]"
elif [ $dBm -lt -30 && $dBm -ge -67 ] ; then
  ST="[|||| - Very Good]"
elif [ $dBm -lt -67 && $dBm -ge -70 ] ; then
  ST="[||| - Okay]"
elif [ $dBm -lt -70 && $dBm -ge -80 ] ; then
  ST="[|| - Not Good]"
else
  ST="[| - Unstable]"
fi

printf "STRENGTH:   %s %s\n" "$STRENGTH" "$ST"
printf "SSID:       %s\n" "$SSID"
printf "AP INFO:    %s\n" "$APINFO"
