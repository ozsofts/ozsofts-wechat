#!/bin/bash

base=`dirname $0` 
cd $base

STARTER=`ls ../start.jar`
FLAGS=" -Dappname=rake -Dorg.terracotta.quartz.skipUpdateCheck=true "

java $FLAGS -XX:MaxPermSize=128m -Xms256m -Xmx1024m -Xdebug -Xnoagent -Xrunjdwp:transport=dt_socket,address=8787,server=y,suspend=y -jar $STARTER
