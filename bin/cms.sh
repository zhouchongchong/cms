#!/usr/bin/bash

SERVER_HOME="/home/zhouchong/service/cms/"

export CLASSPATH=.:$JAVA_HOME/lib:$JAVA_HOME/jre/lib:$SERVER_HOME/lib

start(){
    JAVA=$(which java)

    for jar in $SERVER_HOME/lib/*.jar; do
        CLASSPATH=$CLASSPATH:$jar
    done

    CLASSPATH=${SERVER_HOME}config:$CLASSPATH

    CLASSPATH=${SERVER_HOME}config:$CLASSPATH
        JVM_OPTS=" \
                -ea \
                -Duser.timezone=GMT+08 \
                -Djava.security.egd=file:/dev/urandom \
                -Xms1G \
                -Xmx1G \
                -XX:+UseParNewGC \
                -XX:+UseConcMarkSweepGC \
                -XX:+CMSParallelRemarkEnabled \
                -XX:SurvivorRatio=8 \
                -Dcom.sun.management.jmxremote=true  \
                -Djava.rmi.server.hostname=10.11.34.173  \
                -Dcom.sun.management.jmxremote.port=6666  \
                -Dcom.sun.management.jmxremote.ssl=false  \
                -Dcom.sun.managementote.ssl=false  \
                -Dcom.sun.management.jmxremote.authenticate=false  \
                -XX:+UnlockCommercialFeatures -XX:+FlightRecorder \
                -XX:CMSInitiatingOccupancyFraction=75 \
                -XX:+UseCMSInitiatingOccupancyOnly \
                -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=60073 \
                -XX:MaxTenuringThreshold=1 \
                -XX:+HeapDumpOnOutOfMemoryError \
                -Dfile.encoding=utf-8 "

        nohup $JAVA $JVM_OPTS com.cloudminds.cms.CmsApplication > ${SERVER_HOME}logs/aias.log 2>&1 &
}

stop() {
    Processid=`ps -ef | grep cms.CmsApplication/ | grep java | wc -l`
    if [ $Processid -ge 1 ]; then
       (lsof -i:60073 |grep LISTEN | awk '{print $2}') | xargs kill -9
       echo "Stopping CMS service..."
    else
       echo "no CMS processid"
    fi

}

restart() {
    stop
    start
}

rh_status() {
    Processid=`ps -ef | grep aias.server.InitApplication | grep java | awk '{print $2}'`
    RETVAL=$?
    if [ ! -z $Processid ]; then
           echo "CMS running"
               RETVAL=0
    else
           echo "CMS stopped"
               RETVAL=3
    fi
}

case "$1" in
    start)
        start
        ;;
    stop)
        stop
        ;;
    restart)
        restart
        ;;
    status)
        rh_status
        ;;
    *)
        echo $"Usage: $0 {start|stop|status|restart}"
        exit 2
esac
