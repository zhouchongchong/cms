#!/usr/bin/bash

JAVA_HOME=/usr/java/jdk1.8.0_201-amd64
CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOEM/lib/tools.jar
PATH=$JAVA_HOME/bin:$PATH
export JAVA_HOME
export CLASSPATH
export PATH
