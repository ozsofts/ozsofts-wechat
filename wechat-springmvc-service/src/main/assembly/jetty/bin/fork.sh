#!/bin/bash
IP="[0-9]{1,3}.[0-9]{1,3}.17[23]."

base=`dirname $0` 
cd $base

STARTER=`ls ../start.jar`
IP=`/sbin/ifconfig |grep -P "$IP"|awk -F'addr:' '{print $2}'|awk '{print $1}'`
IP=`echo $IP|awk "{print $1}"`
FLAGS=" -Dappname=${artifactId} -Dorg.terracotta.quartz.skipUpdateCheck=true -Djetty.host="$IP" "

cmd="java $FLAGS -XX:MaxPermSize=128m -Xms256m -Xmx1024m -jar $STARTER" 
nohup $cmd &
