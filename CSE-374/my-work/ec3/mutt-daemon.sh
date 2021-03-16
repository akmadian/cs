#!/usr/bin/sh
# Checks for new mail every 5 minutes, if there is new mail, sends
#   a notification with herbe
eval __MAIL="/home/ari/.config/dwm/__MAIL"
touch $__MAIL
ALWAYS_NOTIFY=true

MAIL() {
  UNREAD_GMAIL=$(find ~/.local/share/mail/akmadian@gmail.com/INBOX/new -type f | wc -l)
  UNREAD=$(find ~/.local/share/mail/ari@madian.co/INBOX/new -type f | wc -l)

  FMTD=$(printf "📫 gm: %d co: %d" "$UNREAD_GMAIL" "$UNREAD")
  echo "$FMTD" > $__MAIL
}

while true
do
  UNREAD_GM=$(find ~/.local/share/mail/akmadian@gmail.com/INBOX/new -type f | wc -l)
  UNREAD_CO=$(find ~/.local/share/mail/ari@madian.co/INBOX/new -type f | wc -l)
  echo "Current Unread - Gmail: $UNREAD_GM madian.co: $UNREAD_CO"

  echo "Fetching New Mail"
  herbe "Fetching New Mail." &
  mbsync -a &
  wait
  echo "Done."
  NEW_UNREAD_GM=$(find ~/.local/share/mail/akmadian@gmail.com/INBOX/new -type f | wc -l)
  NEW_UNREAD_CO=$(find ~/.local/share/mail/ari@madian.co/INBOX/new -type f | wc -l)
  echo "New Unread - Gmail: $NEW_UNREAD_GM madian.co: $NEW_UNREAD_CO"

  GM_DIFF=`expr $NEW_UNREAD_GM - $UNREAD_GM`
  CO_DIFF=`expr $NEW_UNREAD_CO - $UNREAD_CO`
  echo "Diffs - Gmail: $GM_DIFF madian.co: $CO_DIFF"

  echo "Updating Status Bar"
  MAIL

  if [ $GM_DIFF -gt 0 ] || [ $CO_DIFF -gt 0 ]; then
    NOTIF_TEXT="New Mail Received"

    if [ $GM_DIFF -gt 0 ]; then
      NOTIF_GM="akmadian@gmail.com ($GM_DIFF)"
    fi
    if [ $CO_DIFF -gt 0 ]; then
      NOTIF_CO="ari@madian.co ($CO_DIFF)"
    fi

    herbe "$NOTIF_TEXT" "${NOTIF_GM:-}" "${NOTIF_CO:-}" &
  fi

  echo "Sleeping..."
  sleep 300
done

