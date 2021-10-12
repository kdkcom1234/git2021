CURRENT_PID=$(ps -ef | grep java | grep "$1*" | awk '{print $2}')
echo "$CURRENT_PID"

if [ -z $CURRENT_PID ]; then
	echo "> the [$1] app is not running"
else
	echo "> kiil -9 $CURRENT_PID"
	kill -9 $CURRENT_PID
	sleep 1
fi

echo "> run the [$1] app"
nohup java -jar `ls $1*.jar` &> "$1.log" &
